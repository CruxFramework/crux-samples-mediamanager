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
 * Class description: Procedimentos  simulam da davegação do usuario pelo sistema. 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class Navegation
{
	private static ScreenLogin login;
	private static Button menu;

	public static void loginSucess(String user, String password) throws InterruptedException
	{
		login = new ScreenLogin();
		login.getLogin().fill(user);
		login.getPassword().fill(password);
		login.getAcess().click();
		Thread.sleep(5000);
	}

	public static String loginFail(String user, String password) throws InterruptedException
	{
		login = new ScreenLogin();
		login.getLogin().fill(user);
		login.getPassword().fill(password);
		login.getAcess().click();
		String loginInvalid = login.getPopUpPasswordInvalid().getDivText().getText();
		login.getPopUpPasswordInvalid().getBtnOk().click();
		return loginInvalid;
	}

	public static void signOut()
	{
		login.signOut().click();
	}

	public static void acessMenu(EnumMenu menuOption) throws InterruptedException
	{
		SetUp.DRIVER.navigate().refresh();
		menu = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		Thread.sleep(5000);
		loadyByMenuOption(menuOption);
	}

	private static void loadyByMenuOption(EnumMenu menuOption) throws InterruptedException
	{
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
		waitFields();
	}

	public static void waitFields() throws InterruptedException
	{
		Thread.sleep(5000);
	}
}
