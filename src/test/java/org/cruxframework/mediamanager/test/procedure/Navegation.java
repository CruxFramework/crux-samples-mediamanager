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
package org.cruxframework.mediamanager.test.procedure;

import org.cruxframework.mediamanager.test.screen.ScreenLogin;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ufmg.dcc.saotome.beholder.ui.form.Button;

/**
 * Class description: Procedimentos  simulam da davegação do usuario pelo sistema. 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class Navegation
{
	private static ScreenLogin login;

	public static void loginSucess(String user, String password)
	{
		getScreenLogin().getLogin().fill(user);
		getScreenLogin().getPassword().fill(password);
		getScreenLogin().getAcess().click();
		waitInitiate();
	}

	public static String loginFail(String user, String password) 
	{
		loginSucess(user,password);
		String loginInvalid = getScreenLogin().getPopUpPasswordInvalid().getMenssagePopUp();
		getScreenLogin().getPopUpPasswordInvalid().confirmPopUp();
		return loginInvalid;
	}

	public static void signOut()
	{
		getScreenLogin().signOut().click();
		waitInitiate();
	}

	public static void acessMenu(EnumMenu menuOption)
	{
		refreshBrowser();
		Button menu = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		waitInitiate();
		switch (menuOption)
		{
			case ADD_ARTIST:
				menu.loadById("menu_addArtistButton");
				break;
			case ADD_MEDIA:
				menu.loadById("menu_addMediaButton");
				break;
			case SEARCH_ARTIST:
				menu.loadById("menu_searchArtistButton");
				break;
			case SEARCH_MEDIA:
				menu.loadById("menu_searchMediaButton");
				break;
			case STATISTICS:
				menu.loadById("menu_StatsButton");
			default:
				break;
		}
		menu.click();
		waitInitiate();
		
		
	}

	

	public static void waitInitiate() 
	{
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ScreenLogin getScreenLogin()
	{
		if(login == null)
		{
			login = new ScreenLogin();
		}
		return login; 
	}
	
	
	public static void refreshBrowser()
	{
		SetUp.DRIVER.navigate().refresh();
	}
	
	
	public static void webDriverWait()
	{
		new WebDriverWait(SetUp.DRIVER, 5);
	}
}
