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

import org.cruxframework.mediamanager.test.dataprovider.PVSearchArtist;
import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTSearchArtist;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *Class description: This class performs the tests  of the "Search Artist " screen. 
 *The providers of inputs  are methods of class PVSearchArtist
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVSearchArtist}
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "searchArtist" }, priority = 5)
public class CTSearchArtist
{
	/**
	 * Search for a registered artist and check if it is being displayed in the result table.
	 * @param nameArtist: artist name to search
	 * @param artist: artist expected with result of the search
	 */
	@Test(enabled = true, dataProvider = "PV001_SearchArtist", dataProviderClass = PVSearchArtist.class)
	public void P001_SearchArtist( String nameArtist, Artist artist)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(nameArtist);
		Assert.assertEquals(resultSearch, artist);
	}

	/**
	 *Search for an artist that does not exist in the base and validates the pop up with the message "No results found." is displayed.
	 * @param nameArtist: artist name to search
	 */
	@Test(enabled = true, dataProvider = "PV002_ArtistNoExist", dataProviderClass = PVSearchArtist.class)
	public void P002_ArtistNoExist( String nameArtist)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.searchArtist(nameArtist);
		String msgNotFound = PTSearchArtist.getMessageNoResultsNotFound();
		Assert.assertEquals(msgNotFound, "No results found.");
		Assert.assertEquals(PTSearchArtist.isDisplayedPopUp(), false);
	}

	/**
	 *Delete an artist  and check the pop up with  the message   "Record successfully deleted" was displayed
	 * @param nameArtist: artist name to search
	 */
	@Test(enabled = true, dataProvider = "PV003_DeleteArtist", dataProviderClass = PVSearchArtist.class)
	public void P003_DeleteArtist( String nameArtist)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		String sucess = PTSearchArtist.deleteArtist(nameArtist);
		Assert.assertEquals(sucess, "Record successfully deleted!");
		Assert.assertEquals(PTSearchArtist.isDisplayedPopUpSucessDelete(), false);
		
	}

	/**
	 *Search for an artist and edit your data, then immediately a new search is 
	 *performed to validate if the data was properly modified.
	 * @param artist: artist to be  search
	 * @param newValues: new values for artist searched
	 */
	@Test(enabled = true, dataProvider = "PV004_EditAndSearchArtist", dataProviderClass = PVSearchArtist.class)
	public void P004_EditAndSearchArtist( Artist artist, Artist newValues)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.editArtist(artist, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.searchArtist(artist.getName());
		String msgNotFound = PTSearchArtist.getScreenSearchArtist().getPopUpNoResultsFound().getMenssagePopUp();
		PTSearchArtist.getScreenSearchArtist().getPopUpNoResultsFound().confirmPopUp();
		Assert.assertEquals(msgNotFound, "No results found.");
		Artist resultSearch = PTSearchArtist.searchArtist(newValues.getName());
		Assert.assertEquals(newValues, resultSearch);
	}

	/**
	 * Conducts two surveys in sequence to see if the results table has been updated.
	 * @param firstQuery: first query 
	 * @param secondQuery: second query
	 * @param resultFirstQuery: result expected in the first query
	 * @param resultSecondQuery: result expected in the second query
	 */
	@Test(enabled = true, dataProvider = "PV005_ChangeValuesSearch", dataProviderClass = PVSearchArtist.class)
	public void P005_ChangeValuesSearch(String firstQuery, String secondQuery, Artist resultFirstQuery,
			Artist resultSecondQuery)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist fq = PTSearchArtist.searchArtist(firstQuery);
		Assert.assertEquals(resultFirstQuery, fq );
		Artist sq = PTSearchArtist.searchArtist(secondQuery);
		Assert.assertEquals(resultSecondQuery,sq);
	}
}
