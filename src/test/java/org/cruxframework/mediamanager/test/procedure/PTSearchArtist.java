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
import org.openqa.selenium.NoSuchElementException;

/**
 * Class description: This class implements the procedures in 'Search Artist' screen.
 * 
 * @author guilherme.alecrim
 */
public class PTSearchArtist
{
	private static ScreenSearchArtist screenSearchArtist;

	/**
	 * Search for one artist by the name.
	 * 
	 * @param name name of artist
	 * @return artist searched
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
	 * Confirm the pop up with message "No Results Not Found.".
	 * 
	 * @return message "No Results Not Found."
	 */
	public static String getMessageNoResultsNotFound()
	{
		String msg = getScreenSearchArtist().getPopUpNoResultsFound().getMenssagePopUp();
		getScreenSearchArtist().getPopUpNoResultsFound().confirmPopUp();
		return msg;
	}

	/**
	 * Search by artist and edit your datas with new values.
	 * 
	 * @param artist artist for search
	 * @param newValues new values or artist searched
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
	 * Delete one artist.
	 * 
	 * @param nameArtist name of artist for delete
	 * @return success delete message
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

	/**
	 * instantiate the screenSearchArtist.
	 * 
	 * @return screenSearchArtist instantiate
	 */
	public static ScreenSearchArtist getScreenSearchArtist()
	{
		if (screenSearchArtist == null)
		{
			screenSearchArtist = new ScreenSearchArtist();
		}
		return screenSearchArtist;
	}

	/**
	 * Check if pop up 'No Results Found' is displayed.
	 * 
	 * @return true: pop up is displayed false: pop up not is displayed
	 */
	public static boolean isDisplayedPopUp()
	{
		return getScreenSearchArtist().getPopUpNoResultsFound().isDisplayedPopUp();
	}

	/**
	 * Check if pop up 'Success Delete' is displayed.
	 * 
	 * @return true: pop up is displayed false: pop up not is displayed
	 */
	public static boolean isDisplayedPopUpSucessDelete()
	{
		return getScreenSearchArtist().getPopUpSucessDelete().isDisplayedPopUp();
	}
}
