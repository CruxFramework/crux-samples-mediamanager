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

import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.screen.ScreenSearchArtist;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.openqa.selenium.NoSuchElementException;

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class PTSearchArtist
{
	private static ScreenSearchArtist screenSearchArtist;

	/** 
	 * Realiza uma pesquisa pelo nome do artista, se o artista não existir retorna null;
	 * @param name
	 * @return
	 */
	public static Artist searchArtist(String name)
	{
		Artist a = new Artist();
		screenSearchArtist = new ScreenSearchArtist();
		screenSearchArtist.getTxtName().fill(name);
		screenSearchArtist.getBtnSearch().click();
		try
		{
			a = screenSearchArtist.getFirstItemSearchTable();
		}
		catch (NoSuchElementException e)
		{
			a = null;
		}
		return a;
	}

	/** 
	 * Retorna a menssagem "No Results Not Found." e confirma o pop up
	 * @return
	 */
	public static String getMessageNoResultsNotFound()
	{
		String msg = screenSearchArtist.getPopUpNoResultsFound().getDivText().getText();
		screenSearchArtist.getPopUpNoResultsFound().getBtnOk().click();
		return msg;
	}

	/** 
	 * Busca pelo artista a, e edita suas informações conforme as do artista b
	 * @param a
	 * @param b
	 */
	public static void editArtist(Artist a, Artist b)
	{
		PTSearchArtist.searchArtist(a.getName());
		screenSearchArtist.getBtnEditArtist().click();
		PTAddArtist.populateFields(b);
		PTAddArtist.getScreenAddArtist().getBtnSaveChanges().click();
		PTAddArtist.getScreenAddArtist().getPopUpSuccessfullySavedSaveChanges().getBtnOk().click();
	}

	/** 
	 * Realiza o procedimento de deletar um artista
	 * @param nameArtist
	 * @return
	 */
	public static String deleteArtist(String nameArtist)
	{
		searchArtist(nameArtist);
		screenSearchArtist.getBtnDeleteArtist().click();
		screenSearchArtist.getPopUpDelete().getBtnOk().click();
		return screenSearchArtist.getPopUpSucessDelete().getDivText().getText();
	}

	/** 
	 * Rertorna o titulo da pagina, Search Artist
	 * @return
	 */
	public static String getPageTitle()
	{
		screenSearchArtist = new ScreenSearchArtist();
		return screenSearchArtist.getPageTitle().getName();
	}

	public static ScreenSearchArtist getScreenSearchArtist()
	{
		return screenSearchArtist;
	}

	public static void includeArtistsinToDataBase() throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("artista2", "Brazil", "Country"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("artista3", "Mexico", "Reggae"));
	}
}
