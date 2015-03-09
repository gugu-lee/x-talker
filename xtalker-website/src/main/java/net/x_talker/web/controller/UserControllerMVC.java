package net.x_talker.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.x_talker.web.dao.IUserDao;
import net.x_talker.web.entity.ActionResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserControllerMVC {
	private static Logger logger = Logger.getLogger("A1");
	private static final String CURRENT_NODE_LABEL = "CurrentNode";
	@Autowired
	private IUserDao userDao; 

	@RequestMapping(value = "/web/user/register", method = RequestMethod.POST)
	public String register(@RequestParam("userName") String userName,@RequestParam("email") String email,
			@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword,HttpServletRequest request)
	{
		UserService service = new UserService();
		ActionResult result = new ActionResult();
		if (!password.equals(rePassword)){
			result.setErrorMessage("password and re-password is not same.");
			result.setErrorNo(4);
			request.setAttribute("result", result);
			return "user/registerFailed";
		}
		result = service.register(userDao, userName+"@x-talker.net", password, email);
		if (result.getErrorNo()!=0){
			request.setAttribute("result", result);
			return "user/registerFailed";
		}
		
		request.getSession().setAttribute("CurrentUser", userName+"@x-talker.net");
		return "redirect:/web/user/account.form";
	}
	

	@RequestMapping(value = "/web/user/resetPassword", method = RequestMethod.POST)
	public String resetPassword(
			@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword,HttpServletRequest request)
	{
		UserService service = new UserService();
		ActionResult result = new ActionResult();
		if (!password.equals(rePassword)){
			result.setErrorMessage("password and re-password is not same.");
			result.setErrorNo(4);
			request.setAttribute("result", result);
			return "user/resetPasswordFailed";
		}
		result = service.resetPassword(userDao, (String)request.getSession().getAttribute("CurrentUser"), password);
		if (result.getErrorNo()!=0){
			request.setAttribute("result", result);
			return "user/resetPasswordFailed";
		}
		return "user/resetPasswordSuccess";
	}
	
	@RequestMapping(value = "/web/user/login", method = RequestMethod.POST)
	public String login(@RequestParam("identity") String identity,@RequestParam("k") String password,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserService service = new UserService();
		identity += "@x-talker.net";
		ActionResult result = service.login(userDao,identity, password);
		if (result.getErrorNo()==0){
			session.setAttribute("CurrentUser", identity);
			return "index";
		}
		request.setAttribute("result", result);
		return "user/loginFailed";
	}
	
	@RequestMapping(value = "/web/user/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		request.getSession().removeAttribute("CurrentUser");
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request)
	{
		String node = request.getQueryString();
		//request.setAttribute(CURRENT_NODE_LABEL, node);
		logger.info(node);
		return "index";
	}
	
	@RequestMapping(value = "/web/user/account", method = RequestMethod.GET)
	public String account(HttpServletRequest request)
	{
		//String currentUser = session.getAttribute("");
		request.setAttribute(CURRENT_NODE_LABEL, "account");
		UserService service = new UserService();
		ActionResult result = service.fetch(userDao, (String)request.getSession().getAttribute("CurrentUser"));
		request.setAttribute("accountResult", result);
		return "user/account";
	}
	@RequestMapping(value = "/web/user/registerView", method = RequestMethod.GET)
	public String registerView(HttpServletRequest request)
	{
		request.setAttribute(CURRENT_NODE_LABEL, "home");
		return "user/register";
	}
	/*
	@RequestMapping(value = "/web/user/resetPasswordView", method = RequestMethod.GET)
	public String resetPasswordView()
	{
		return "user/resetPasswordView";
	}
	*/
	
}
