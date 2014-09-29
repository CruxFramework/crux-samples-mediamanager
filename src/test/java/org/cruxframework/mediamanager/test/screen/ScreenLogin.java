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


package org.cruxframework.mediamanager.test.screen;
import org.cruxframework.mediamanager.test.procedure.SetUp;

import br.ufmg.dcc.saotome.beholder.ui.form.Button;
import br.ufmg.dcc.saotome.beholder.ui.form.TextField;

/**
 * Class description: This class represent the screen Login
 * @author guilherme.alecrim
 */
public class ScreenLogin
{
	/*
	 * This parameters equivalent   the elements in screen  
	 * This element are providers by Beholder  Framework
	 */
	private TextField login;
	private TextField password;
	private Button acess;
	private Button logoff;
	private PopUp popUpPasswordInvalid;

	
	/*
	 *  constructor  initialize all elements 
	 */
	public ScreenLogin()
	{
		login = SetUp.getBuilder().uiComponentBuilderInstance().textFieldInstance();
		password = SetUp.getBuilder().uiComponentBuilderInstance().textFieldInstance();
		acess = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		logoff = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		popUpPasswordInvalid = new PopUp(
				"/html/body/div[5]/div",
				"/html/body/div[5]/div/div/div[2]/div/div",
				"/html/body/div[5]/div/div/div[2]/div/button"		
				);
	}
	/*
	 * accessors methods, this methods performed scan in the html before return the element
	 * this scan is necessary because the element can not is displayed in screen to the access
	 */

	public TextField getLogin()
	{
		login.loadById("login_loginTextBox");
		return login;
	}

	public TextField getPassword()
	{
		password.loadById("login_passwordTextBox");
		return password;
	}

	public Button getAcess()
	{
		acess.loadById("login_loginButton");
		return acess;
	}

	public Button signOut()
	{
		logoff.loadById("menu_signOutButton");
		return logoff;
	}

	public PopUp getPopUpPasswordInvalid()
	{										
		return popUpPasswordInvalid;
	}
}
