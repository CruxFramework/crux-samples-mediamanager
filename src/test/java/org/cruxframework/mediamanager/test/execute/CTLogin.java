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
import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: Testes para validação do login do media manager. O Login deve ser realizado somente para o usuário
 * e senha admin, admin
 * 
 * A massa de testes usada: PVLogin
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: completar comentário de métodos
@Test(groups = { "mediaManager", "login" }, priority = 2)
public class CTLogin
{
	/** 
	 * Valida mesagem de usuario e senha incorretos
	 * @param ct
	 * @param user
	 * @param password
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001_LoginFail", dataProviderClass = PVLogin.class, groups = { "branch" })
	public void P001_LoginFail(final String ct, String user, String password) throws InterruptedException
	{
		SetUp.DRIVER.navigate().refresh();
		String loginFail = Navegation.loginFail(user, password);
		Assert.assertEquals(loginFail, "Username and password invalid!");
	}

	/** 
	 * Valida login com sucesso
	 * @param ct
	 * @param user
	 * @param password
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001_LoginSucess", dataProviderClass = PVLogin.class, groups = { "branch" })
	public void P002_LoginSucess(final String ct, String user, String password) throws InterruptedException
	{
		Navegation.loginSucess(user, password);
		Assert.assertEquals(SetUp.BROWSER.isTextPresent("Statistics"), true);
	}
}
