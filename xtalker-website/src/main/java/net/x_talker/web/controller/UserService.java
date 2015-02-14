package net.x_talker.web.controller;

import net.x_talker.web.entity.ActionResult;
import net.x_talker.web.entity.UserConf;
import net.x_talker.web.dao.IUserDao;

import net.x_talker.web.entity.IMPI;
import net.x_talker.web.entity.IMSU;
import net.x_talker.web.entity.IMPU;
import net.x_talker.web.entity.IMPI_IMPU;
import net.x_talker.web.entity.IMPU_VisitedNetwork;
import net.x_talker.web.entity.UserConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class UserService {

	
	public ActionResult register(IUserDao userDao,String identity,String k)
	{
		
		ActionResult result = new ActionResult();
		UserConf userConf = new UserConf();

		boolean isExist = userDao.isExistIMPI(identity);
		System.out.println(isExist);
		if (isExist)
		{


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
