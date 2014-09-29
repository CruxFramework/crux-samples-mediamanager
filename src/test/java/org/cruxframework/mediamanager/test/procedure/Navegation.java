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

import br.ufmg.dcc.saotome.beholder.ui.form.Button;

/**
 * Class description: This class implements the procedures which simulate the navegation of user by system.
 * 
 * @author guilherme.alecrim
 */
public class Navegation
{
	private static ScreenLogin login;

	/**
	 * Realize login with success in media manager.
	 * 
	 * @param user valid login of user, e.g admin
	 * @param password password valid of user, e.g admin
	 */
	public static void loginSucess(String user, String password)
	{
		getScreenLogin().getLogin().fill(user);
		getScreenLogin().getPassword().fill(password);
		getScreenLogin().getAcess().click();
		waitFiveSeconds();
	}

	/**
	 * Realize login with insuccess in media manager.
	 * 
	 * @param user invalid login of user, e.g userInvalid
	 * @param password invalid password of user, e.g invalidPass123
	 * @return message
	 */
	public static String loginFail(String user, String password)
	{
		loginSucess(user, password);
		String loginInvalid = getScreenLogin().getPopUpPasswordInvalid().getMenssagePopUp();
		getScreenLogin().getPopUpPasswordInvalid().confirmPopUp();
		return loginInvalid;
	}

	/**
	 * Exit mediamanager, click in button singOut.
	 */
	public static void signOut()
	{
		getScreenLogin().signOut().click();
		waitFiveSeconds();
	}

	/**
	 * Acess a option of menu.
	 * 
	 * @param menuOption option
	 */
	public static void acessMenu(EnumMenu menuOption)
	{
		refreshBrowser();
		Button menu = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		waitFiveSeconds();
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
		waitFiveSeconds();
	}

	/**
	 * Stop current thread in 5 seconds, for wait loading browser.
	 */
	public static void waitFiveSeconds()
	{
		try
		{
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*****************************************
	 * utilities
	 *****************************************/

	private static ScreenLogin getScreenLogin()
	{
		if (login == null)
		{
			login = new ScreenLogin();
		}
		return login;
	}

	private static void refreshBrowser()
	{
		SetUp.getDriver().navigate().refresh();
	}
}
