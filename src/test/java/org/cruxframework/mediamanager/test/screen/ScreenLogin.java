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
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class ScreenLogin
{
	private TextField login;
	private TextField password;
	private Button acess;
	private Button logoff;
	private PopUp popUpPasswordInvalid;

	public ScreenLogin()
	{
		login = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		password = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		acess = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		logoff = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		popUpPasswordInvalid = new PopUp(
				"/html/body/div[5]/div",
				"/html/body/div[5]/div/div/div[2]/div/div",
				"/html/body/div[5]/div/div/div[2]/div/button"		
				);
	}

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
