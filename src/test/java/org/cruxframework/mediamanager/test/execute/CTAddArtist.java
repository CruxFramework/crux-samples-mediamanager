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

import org.cruxframework.mediamanager.test.dataprovider.PVAddArtist;
import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddArtist;
import org.cruxframework.mediamanager.test.procedure.PTSearchArtist;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: This class contains test cases for the screen "Add Artist" The providers of inputs are methods of class PVAddArtist
 * class.
 * 
 * @see {org.cruxframework.mediamanager.test.dataprovider.PVAddArtist }
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "addArtist" }, priority = 3)
public class CTAddArtist
{

	/**
	 * Adds an artist and verifies that the pop up with the message "Successfully saved!" is being displayed.
	 * 
	 * @param artist Artist to be added
	 */
	@Test(enabled = true, dataProvider = "PV001_Artists", dataProviderClass = PVAddArtist.class)
	public void p001AddArtist(Artist artist)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String msgSucess = PTAddArtist.addArtist(artist);
		Assert.assertEquals(msgSucess, "Successfully saved!");
		Assert.assertEquals(PTAddArtist.isDisplayedPopUpSavedAddArtist(), false);
	}

	/**
	 * Adds an artist and then immediately changes the data and verifies that pop up with the message "Successfully saved!" was displayed.
	 * 
	 * @param artist Artist data to be added
	 * @param newValues ​values ​​to be used to change the data of the artist wich was added
	 */
	@Test(enabled = false, dataProvider = "PV002_AddAndChangeArtists", dataProviderClass = PVAddArtist.class)
	public void p002AddAndSaveChanges(Artist artist, Artist newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String sucessChange = PTAddArtist.addAndChangerArtist(artist, newValues);
		Assert.assertEquals(sucessChange, "Successfully saved!");
	}

	/**
	 * Adds an artist and then immediately performs a search to check if the artist added contained in the data base.
	 * 
	 * @param artist data of artist that will be added
	 */
	@Test(enabled = true, dataProvider = "PV003_AddAndSearchArtists", dataProviderClass = PVAddArtist.class)
	public void p003AddArtistAndSearchArtist(Artist artist)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(artist);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(artist.getName());
		Assert.assertEquals(artist, resultSearch);
	}

	/**
	 * Modifies the data of an artist and then performs a search to verify that the data was properly changed in the data base.
	 * 
	 * @param artist Data of artist to be added
	 * @param newValues values ​​to be used to change the data of the newly added artist
	 */
	@Test(enabled = true, dataProvider = "PV004_ChangeAndSearchArtist", dataProviderClass = PVAddArtist.class)
	public void p004ChangeAndSearchArtist(Artist artist, Artist newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addAndChangerArtist(artist, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(newValues.getName());
		Assert.assertEquals(newValues, resultSearch);
	}

	/**
	 * Leave some fields without filling, making sure the pop up with mensgem "Fill all fields." is displayed.
	 * 
	 * @param artist data fields to be filled
	 */
	@Test(enabled = true, dataProvider = "PV005_FieldCleanToAdd", dataProviderClass = PVAddArtist.class)
	public void p005FieldCleanToAdd(Artist artist)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String msgFillAllFields = PTAddArtist.addArtist(artist);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}

	/**
	 * Leave some fields without filling to modify the data of an artist added and check if pop up "Fill all fields." is displayed.
	 * 
	 * @param artist Artist to be added.
	 * @param newValues values ​​to be used to change the data of the newly added artist.
	 */
	@Test(enabled = true, dataProvider = "PV006_FieldCleanToChange", dataProviderClass = PVAddArtist.class)
	public void p006FieldCleanToChange(Artist artist, Artist newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String msgFillAllFields = PTAddArtist.addAndChangerArtist(artist, newValues);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
		Assert.assertEquals(PTAddArtist.isDisplayedPopUpSavedAddArtist(), false);
	}
}
