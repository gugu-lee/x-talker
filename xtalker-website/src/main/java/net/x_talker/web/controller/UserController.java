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
	
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	
	public  UserConf create()
	{
		UserConf userConf = new UserConf();
		/*
		System.out.println("create user");
		
		System.out.println(impi.getId());
		*/
		boolean isExist = userDao.isExistIMPI("1001@x-talker.net");
		System.out.println(isExist);
		if (isExist)
		{
			userConf.setErrorMessage("user name is exist");
			return userConf;
		}
		userConf.setIdentity("1001@x-talker.net");
		/*
		IMSU imsu = new IMSU();
		imsu.setName("1001");
		imsu.setDiameter_name("");
		imsu.setScscf_name("");
		imsu.setId_capabilities_set(1);
		imsu.setId_preferred_scscf_set(1);
		userDao.insertIMSU(imsu);
		
		IMPI  impi = new IMPI();
		impi.setId_imsu(imsu.getId());
		impi.setIdentity("1001@x-talker.net");
		impi.setK("1001".getBytes());
		userDao.insertIMPI(impi);
		
		IMPU impu = new IMPU();
		impu.setIdentity("sip:1001@x-talker.net");
		userDao.insertIMPU(impu);
		
		IMPI_IMPU impi_impu = new IMPI_IMPU();
		impi_impu.setId_impi(impi.getId());
		impi_impu.setId_impu(impu.getId());
		impi_impu.setUser_state(1);
		userDao.insertIMPI_IMPU(impi_impu);
		
		IMPU_VisitedNetwork v = new IMPU_VisitedNetwork();
		v.setId_impu(impu.getId());
		v.setId_visited_network(1);
		userDao.insertIMPU_VisitedNetwork(v);
		*/
		return userConf;
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
