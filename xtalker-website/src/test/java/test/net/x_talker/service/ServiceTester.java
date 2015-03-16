package test.net.x_talker.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;  

import javax.servlet.http.HttpServletResponse;  
  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.MediaType;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import org.springframework.test.context.web.WebAppConfiguration;  
import org.springframework.test.web.servlet.MockMvc;  
import org.springframework.test.web.servlet.ResultHandler;  
import org.springframework.test.web.servlet.ResultMatcher;  
import org.springframework.ui.Model;  
import org.springframework.test.context.transaction.TransactionConfiguration;  
import org.springframework.transaction.annotation.Transactional;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.context.WebApplicationContext;  

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration  
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml","file:src/main/webapp/WEB-INF/applicationContext*.xml"})  
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否  
@TransactionConfiguration(defaultRollback = true)  
//记得要在XML文件中声明事务哦~~~我是采用注解的方式  
@Transactional  
public class ServiceTester {

	 @Autowired  
	    private WebApplicationContext wac;  
	  
	    private MockMvc mockMvc;  
	    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 this.mockMvc = webAppContextSetup(this.wac).build(); 
	}


	
	@Test
	public void testRegister() throws Exception
	{
		mockMvc.perform((post("/service/user/register.form")
				 .param("userName", "guzhangli")
				 .param("email", "gulilan_2011@yahoo.com")
				 .param("inputPassword", "gulilan_2011@yahoo.com")
				 .param("reInputPassword", "gulilan_2011@yahoo.com"))) 
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
		mockMvc.perform((post("/service/user/register.form")
				 .param("userName", "_guzhangli")
				 .param("email", "gulilan_2011@yahoo.com")
				 .param("inputPassword", "gulilan_2011@yahoo.com")
				 .param("reInputPassword", "gulilan_2011@yahoo.com"))) 
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
		mockMvc.perform((post("/service/user/register.form")
				 .param("userName", "guzhangli&^")
				 .param("email", "gulilan_2011@yahoo.com")
				 .param("inputPassword", "gulilan_2011@yahoo.com")
				 .param("reInputPassword", "gulilan_2011@yahoo.com"))) 
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
		mockMvc.perform((post("/service/user/register.form")
				 .param("userName", "gulilan_")
				 .param("email", "gulilan_2011@yahoo.com")
				 .param("inputPassword", "gulilan_2011@yahoo.com")
				 .param("reInputPassword", "gulilan_2011@yahoo.com"))) 
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
		mockMvc.perform((post("/service/user/register.form")
				 .param("userName", "gulilan_")
				 .param("email", "gulilan@yahoo.com")
				 .param("inputPassword", "gulilan@yahoo.com")
				 .param("reInputPassword", "gulilan@yahoo.com"))) 
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
	}
	
	@Test
	public void testLoginOK() throws Exception
	{
		mockMvc.perform((post("/service/user/login.form")
				 .param("identity", "gulilan")
				 .param("k", "`=\\9[yR.fb'G")))  
				 	.andExpect(status().isOk())
				 	.andDo(print()); 
	}
	
	/*
	@Test
	public void testResetPassword() throws Exception
	{
		 mockMvc.perform((post("/service/user/systemResetPassword.form")
				 .param("username", "gulilan")
				 .param("email", "gulilan_2011@yahoo.com")))  
				 	.andExpect(status().isOk())
				 	.andDo(print());  
	}
	*/

}
