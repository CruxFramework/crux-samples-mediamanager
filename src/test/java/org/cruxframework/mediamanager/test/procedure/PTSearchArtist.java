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
		Artist artist = new Artist();
		getScreenSearchArtist().getTxtName().fill(name);
		getScreenSearchArtist().getBtnSearch().click();
		try
		{
			artist = getScreenSearchArtist().getFirstItemSearchTable();
		}
		catch (NoSuchElementException e)
		{
			System.out.println("NoSuchElementException: não foi encontrado artista como nome: " + name);
			
		}
		return artist;
	}

	/** 
	 * Retorna a menssagem "No Results Not Found." e confirma o pop up
	 * @return
	 */
	public static String getMessageNoResultsNotFound()
	{
		String msg = getScreenSearchArtist().getPopUpNoResultsFound().getMenssagePopUp();
		getScreenSearchArtist().getPopUpNoResultsFound().confirmPopUp();
		return msg;
	}

	/** 
	 * Busca pelo artista artist, e edita suas informações com os valores de newValues
	 * @param artist : artista que será adicionado
	 * @param newValues: novos valores que serão alterados no artista recém adicionado
	 */
	public static void editArtist(Artist artist, Artist newValues)
	{
		PTSearchArtist.searchArtist(artist.getName());
		getScreenSearchArtist().getBtnEditArtist().click();
		PTAddArtist.populateFields(newValues);
		PTAddArtist.getScreenAddArtist().getBtnSaveChanges().click();
		PTAddArtist.getScreenAddArtist().getPopUp().getBtnOk().click();
	}

	/** 
	 * Realiza o procedimento de deletar um artista
	 * @param nameArtist
	 * @return
	 */
	public static String deleteArtist(String nameArtist)
	{
		searchArtist(nameArtist);
		getScreenSearchArtist().getBtnDeleteArtist().click();
		getScreenSearchArtist().getPopUpDelete().confirmPopUp();
		String msg = getScreenSearchArtist().getPopUpSucessDelete().getMenssagePopUp();
		getScreenSearchArtist().getPopUpSucessDelete().confirmPopUp();
		return msg;
	}


	public static ScreenSearchArtist getScreenSearchArtist()
	{
		if(screenSearchArtist == null)
		{
			screenSearchArtist = new ScreenSearchArtist();
		}
		return screenSearchArtist;
	}

	public static boolean isDisplayedPopUp()
	{
		return getScreenSearchArtist().getPopUpNoResultsFound().isDisplayedPopUp();
	}
	
	public static boolean isDisplayedPopUpSucessDelete()
	{
		return getScreenSearchArtist().getPopUpSucessDelete().isDisplayedPopUp();
	}
	
	public static void includeArtistsinToDataBase() 
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("artista2", "Brazil", "Country"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("artista3", "Mexico", "Reggae"));
	}
}
