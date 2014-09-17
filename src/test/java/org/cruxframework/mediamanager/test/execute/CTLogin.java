/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cruxframework.mediamanager.test.execute;

import org.cruxframework.mediamanager.test.dataprovider.PVLogin;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTStatistics;
import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *Class description: tests to validate the access to the media manager. 
 *The login must be performed only to the user : admin  and  password:  admin 
 * The providers of inputs  are methods of class PVLogin class 
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVLogin }
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "login" }, priority = 2)
public class CTLogin
{
	/** 
	 * Login with  invalid user, the system should displayed pop up with message "Username and password invalid!"
	 * @param user : user invalid
	 * @param password: password of the user 
	 */
	@Test(enabled = true, dataProvider = "PV001_LoginFail", dataProviderClass = PVLogin.class)
	public void P001_LoginFail( String user, String password)  
	{
		SetUp.DRIVER.navigate().refresh();
		String loginFail = Navegation.loginFail(user, password);
		Assert.assertEquals(loginFail, "Username and password invalid!");
	}

	/** 
	 * Login whit user valid, the system should allow the user access  
	 * @param user: user altentic in system
	 * @param password: password of the user authenticate
	 */
	@Test(enabled = true, dataProvider = "PV001_LoginSucess", dataProviderClass = PVLogin.class)
	public void P002_LoginSucess( String user, String password)  
	{
		Navegation.loginSucess(user, password);
		Assert.assertEquals(PTStatistics.getNameScreenStatistics(), "Statistics");
	}
}
