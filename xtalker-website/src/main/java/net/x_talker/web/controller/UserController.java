package net.x_talker.web.controller;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.x_talker.web.dao.*;
import net.x_talker.web.entity.ActionResult;
import net.x_talker.web.entity.IMPI;
import net.x_talker.web.util.StringManager;

@RestController
public class UserController {
	@Autowired
	private IUserDao userDao;

	private StringManager sm = StringManager
			.getManager("net.x_talker.web.controller");
	//private GenericValidator v = new GenericValidator();

	@RequestMapping(value = "/service/user/register", method = RequestMethod.POST)
	public ActionResult register(@RequestParam("userName") String userName,
			@RequestParam("email") String email,
			@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword,
			HttpServletRequest request) {

		ActionResult result = new ActionResult();

		if (! GenericValidator.isInRange(userName.getBytes().length, 6, 16)){
			result.setErrorMessage(sm.getString("error.4", "username",6,16));
			result.setErrorNo(4);
			return result;
		};
		if ( !GenericValidator.isInRange(password.getBytes().length, 8, 32)){
			result.setErrorMessage(sm.getString("error.4", "password",8,32));
			result.setErrorNo(4);
			return result;
		};
		
		if (!GenericValidator.matchRegexp(userName, "$[a-zA-Z][a-zA-Z0-9_]*")){
			result.setErrorMessage(sm.getString("error.6", "username"));
			result.setErrorNo(6);
			return result;
		}
		if (!EmailValidator.getInstance().isValid(email)){
			result.setErrorMessage(sm.getString("error.7"));
			result.setErrorNo(7);
			return result;
		}
		
		UserService service = new UserService();

		if (!password.equals(rePassword)) {
			result.setErrorMessage(sm.getString("error.5"));
			result.setErrorNo(5);
			
			return result;
		}

		result = service.register(userDao, userName + "@x-talker.net",
				password, email);
		if (result.getErrorNo() != 0) {
			request.setAttribute("result", result);
			return result;
		}
		return result;
	}

	/*@RequestMapping(value = "/service/user/fetch", method = RequestMethod.GET)
	public ActionResult fetchIMPI(@RequestParam("identity") String identity) {
		UserService service = new UserService();
		ActionResult result = service.fetch(userDao, identity);
		return result;
	}
*/
	@RequestMapping(value = "/service/user/login", method = RequestMethod.POST)
	public ActionResult login(@RequestParam("identity") String identity,
			@RequestParam("k") String password) {

		UserService service = new UserService();
		identity += "@x-talker.net";
		ActionResult result = service.login(userDao, identity, password);
		/*
		 * if (result.getErrorNo()==0){ return result; }
		 */
		return result;
	}

	@RequestMapping(value = "/service/user/resetPassword", method = RequestMethod.GET)
	public ActionResult resetPassword(
			@RequestParam("identity") String identity,
			@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword) {
		UserService service = new UserService();
		ActionResult result = new ActionResult();
		if (!password.equals(rePassword)) {
			result.setErrorMessage("password and re-password is not same.");
			result.setErrorNo(4);
			return result;
		}
		result = service.resetPassword(userDao, identity, password);
		// if (result.getErrorNo()!=0){

		// return result;
		// }
		return result;
	}

	@RequestMapping(value = "/service/user/systemResetPassword", method = RequestMethod.POST)
	public ActionResult systemResetPassword(
			@RequestParam("username") String username,
			@RequestParam("email") String email) {
		
		ActionResult result = new ActionResult();
		
		UserService service = new UserService();
		
		result = service.fetchByEmail(userDao, email);
		if (result.errorNo!=0){
			return result;
		}
		IMPI impi = (IMPI)result.getResult();
		
		String newPassword = genRandomNum(12);
		result=service.systemResetPassword(userDao, email, newPassword);
		if (result.errorNo!=0){
			return result;
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.x-talker.net");
		Session session = Session.getInstance(props, null);

		try {
			MimeMessage msg = new MimeMessage(session);
			System.out.println("to:"+email);
			msg.setFrom("no-replay@x-talker.net");
			msg.setRecipients(Message.RecipientType.TO,
					email);
			msg.setSubject("your password is reseted.");
			msg.setSentDate(new Date());
			String mailContent = sm.getString("mail.content", impi.getIdentityName(),newPassword);
			msg.setText(mailContent);
			Transport.send(msg, " no-replay", "1234");
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
		}
		return result;
	}

	private String genRandomNum(int pwd_len) {
		// 94是因为ascii中有94个可见字符
		final int maxNum = 94;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			pwd.append((char) (33 + i));
			count++;
		}

		return pwd.toString();
	}
	public static void main(String[] args)
	{
		System.out.println(new UserController().genRandomNum(12));
	}

}
