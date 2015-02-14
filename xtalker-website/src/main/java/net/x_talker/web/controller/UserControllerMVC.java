package net.x_talker.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.x_talker.web.dao.IUserDao;
import net.x_talker.web.entity.ActionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class UserControllerMVC {
	@Autowired
	private IUserDao userDao; 
	@RequestMapping(value = "/web/user/registerView", method = RequestMethod.GET)
	public String registerView()
	{
		
		return "user/register";
	}
	
	@RequestMapping(value = "/web/user/login", method = RequestMethod.POST)
	public String login(@RequestParam("identity") String identity,@RequestParam("k") String password,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserService service = new UserService();
		ActionResult result = service.login(userDao,identity, password);
		if (result.getErrorNo()==0){
			session.setAttribute("CurrentUser", identity);
			return "index";
		}
		return "loginFailed";
	}
	
	@RequestMapping(value = "/web/user/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		request.getSession().removeAttribute("CurrentUser");
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}
	
}
