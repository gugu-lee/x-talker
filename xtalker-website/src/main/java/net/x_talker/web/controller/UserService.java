package net.x_talker.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import net.x_talker.web.entity.ActionResult;
import net.x_talker.web.entity.UserConf;
import net.x_talker.web.dao.IUserDao;

import net.x_talker.web.entity.IMPI;
import net.x_talker.web.entity.IMSU;
import net.x_talker.web.entity.IMPU;
import net.x_talker.web.entity.IMPI_IMPU;
import net.x_talker.web.entity.IMPU_VisitedNetwork;
import net.x_talker.web.entity.UserConf;



public class UserService {

	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	public ActionResult register(IUserDao userDao,String identity,String k,String email)
	{
		
		ActionResult result = new ActionResult();
		UserConf userConf = new UserConf();
		boolean isExist = userDao.isExistIMPI(identity);
		if (isExist)
		{
			result.setErrorMessage("\""+identity+"\"is exist.");
			result.setErrorNo(3);
			return result;
		}
		
		userConf.setIdentity(identity);
		
		IMSU imsu = new IMSU();
		imsu.setName(IMPI.getIdentityName(identity));
		imsu.setDiameter_name("");
		imsu.setScscf_name("");
		imsu.setId_capabilities_set(1);
		imsu.setId_preferred_scscf_set(1);
		userDao.insertIMSU(imsu);
		
		IMPI  impi = new IMPI();
		impi.setId_imsu(imsu.getId());
		impi.setIdentity(identity);
		impi.setK(k.getBytes());
		impi.setEmail(email);
		userDao.insertIMPI(impi);
		
		IMPU impu = new IMPU();
		impu.setIdentity("sip:"+identity);
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
		
		return result;
		
	}
	
	public ActionResult fetch(IUserDao userDao ,String identity)
	{
		ActionResult result = new ActionResult();
		IMPI impi = userDao.fetchIMPI(identity);
		if (impi==null){
			result.setErrorNo(2);
			result.setErrorMessage("No such identity");
			return result;
		}
		result.setResult(impi);
		return result;
	}
	public ActionResult resetPassword(IUserDao userDao ,String identity,String newPassword)
	{
		ActionResult result = new ActionResult();
		IMPI impi = new IMPI();
		impi.setIdentity(identity);
		impi.setK(newPassword.getBytes());
		userDao.resetPassword(impi);
		return result;
	}
	public ActionResult login(IUserDao userDao ,String identity,String password)
	{
		//result.errorNo is 0 by default.
		ActionResult result = new ActionResult();
		
		boolean isExistIMPI = userDao.isExistIMPI(identity);
		if (!isExistIMPI){
			System.out.println("user is not exist");
			result.setErrorMessage("username or password is invalide.");
			result.setErrorNo(1);
			return result;
		}
		
		String passwordInDb = userDao.fetchPassword(identity);
		
		System.out.println(passwordInDb);
		
		if (!password.equals(passwordInDb)){
			System.out.println("password error");
			result.setErrorMessage("username or password is invalide.");
			result.setErrorNo(1);
			return result;
		}
		
		return result;
		
	}
}
