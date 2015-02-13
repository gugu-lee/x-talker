package net.x_talker.web.dao;

//import org.mybatis.spring.annotation.*;
import net.x_talker.web.entity.*;

public interface IUserDao {

	public void insertIMPI(IMPI impi);
	public void insertIMPU(IMPU impu);
	public void insertIMPI_IMPU(IMPI_IMPU impi_impu);
	public void insertIMSU(IMSU imsu);
	public void insertIMPU_VisitedNetwork(IMPU_VisitedNetwork v);
	

	public boolean isExistIMPI(String impiIdentify);
	
	//public void fetch(int id);
	//public void resetPassword(String oldPassword,String newPassword);
}
