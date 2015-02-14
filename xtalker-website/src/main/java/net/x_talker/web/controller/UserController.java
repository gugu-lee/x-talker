package net.x_talker.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import net.x_talker.web.dao.*;
import net.x_talker.web.entity.IMPI;
import net.x_talker.web.entity.IMSU;
import net.x_talker.web.entity.IMPU;
import net.x_talker.web.entity.IMPI_IMPU;
import net.x_talker.web.entity.IMPU_VisitedNetwork;
import net.x_talker.web.entity.UserConf;

@RestController
public class UserController {
	@Autowired
	private IUserDao userDao; 
	
	@RequestMapping(value = "/service/user/register", method = RequestMethod.POST)
	public  UserConf create()
	{
		
		return null;
	}
	
	@RequestMapping(value = "/user/fetch", method = RequestMethod.GET)  
	public IMPI fetchIMPI(int id)
	{
		return null;
	}
	
	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
	public boolean resetPassword(String oldPassword,String newPassword)
	{
		return true;
	}
}
