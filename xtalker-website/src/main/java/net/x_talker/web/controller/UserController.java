package net.x_talker.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.x_talker.web.dao.*;
import net.x_talker.web.entity.ActionResult;

@RestController
public class UserController {
	@Autowired
	private IUserDao userDao; 
	
	@RequestMapping(value = "/service/user/register", method = RequestMethod.POST)
	public  ActionResult register(@RequestParam("userName") String userName,
			@RequestParam("email") String email,
			@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword,
			HttpServletRequest request)
	{
		UserService service = new UserService();
		ActionResult result = new ActionResult();
		if (!password.equals(rePassword)){
			result.setErrorMessage("password and re-password is not same.");
			result.setErrorNo(4);
			request.setAttribute("result", result);
			return result;
		}
		result = service.register(userDao, userName+"@x-talker.net", password, email);
		if (result.getErrorNo()!=0){
			request.setAttribute("result", result);
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = "/service/user/fetch", method = RequestMethod.GET)  
	public ActionResult fetchIMPI(@RequestParam("identity") String identity)
	{
		UserService service = new UserService();
		ActionResult result = service.fetch(userDao, identity);
		return result;
	}
	
	@RequestMapping(value = "/service/user/login", method = RequestMethod.POST)
	public ActionResult login(@RequestParam("identity") String identity,@RequestParam("k") String password)
	{

		UserService service = new UserService();
		identity += "@x-talker.net";
		ActionResult result = service.login(userDao,identity, password);
		/*
		if (result.getErrorNo()==0){
			return result;
		}*/
		return result;
	}
	
	@RequestMapping(value = "/service/user/resetPassword", method = RequestMethod.GET)
	public ActionResult resetPassword(@RequestParam("identity") String identity,@RequestParam("inputPassword") String password,
			@RequestParam("reInputPassword") String rePassword)
	{
		UserService service = new UserService();
		ActionResult result = new ActionResult();
		if (!password.equals(rePassword)){
			result.setErrorMessage("password and re-password is not same.");
			result.setErrorNo(4);
			return result;
		}
		result = service.resetPassword(userDao, identity, password);
		//if (result.getErrorNo()!=0){

		//	return result;
		//}
		return result;
	}
}
