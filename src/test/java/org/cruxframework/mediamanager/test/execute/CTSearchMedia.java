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

import org.cruxframework.mediamanager.test.dataprovider.PVSearchMedia;
import org.cruxframework.mediamanager.test.model.LineTableSearchMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTSearchMedia;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: This class performs the tests of the "Search Media " screen. The providers of inputs are methods of class
 * PVSearchMedia
 * 
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVSearchMedia}
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "searchMedia" }, priority = 6)
public class CTSearchMedia
{
	/**
	 * Search for an average contained in the database, and checks if media is displayed in the results table.
	 * 
	 * @param media media expected in the search
	 * @param query attributes of the search
	 */
	@Test(enabled = false, dataProvider = "PV001_SearchMediaWhitExist", dataProviderClass = PVSearchMedia.class)
	public void p001SearchMediaWhitExist(Media media, QueryMedia query)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		LineTableSearchMedia ltsm = PTSearchMedia.searchMedia(query);
		Media resultSearch = ltsm.getMedia();
		Assert.assertEquals(resultSearch, media);
	}

	/**
	 * Search by a media that is not in the database and verifies that the pop up with the message "No results found." is displayed.
	 * 
	 * @param query attributes of the search
	 */
	@Test(enabled = false, dataProvider = "PV002_SearchMediaWhitNotExist", dataProviderClass = PVSearchMedia.class)
	public void p002SearchMediaWhitNotExist(QueryMedia query)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String noResultsFound = PTSearchMedia.searchMediaWithNotExist(query);
		Assert.assertEquals(noResultsFound, "No results found.");
	}

	/**
	 * Edit data from a media and verifies if the pop up with the message "Successfully saved!" is displayed.
	 * 
	 * @param query attributes of the search
	 * @param newValues new values for media searched
	 */
	@Test(enabled = false, dataProvider = "PV003_EditMedia", dataProviderClass = PVSearchMedia.class)
	public void p003EditMedia(QueryMedia query, Media newValues)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String sucessEditMedia = PTSearchMedia.editMedia(query, newValues);
		Assert.assertEquals(sucessEditMedia, "Successfully saved!");
	}

	/**
	 * Deletes a media and verifies that pop up with the message "Record successfully deleted!" is displayed.
	 * 
	 * @param query attributes of the search
	 */
	@Test(enabled = false, dataProvider = "PV004_DeleteMedia", dataProviderClass = PVSearchMedia.class)
	public void p004DeleteMedia(QueryMedia query)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String sucessOnDelete = PTSearchMedia.deleteMedia(query);
		Assert.assertEquals(sucessOnDelete, "Record successfully deleted!");
	}

	/**
	 * Edit data from a media and conducts a search to verify that the data was performs changed in the database.
	 * 
	 * @param media media for edit
	 * @param newValues new values for media
	 */
	@Test(enabled = false, dataProvider = "PV005_EditAndSearchMedia", dataProviderClass = PVSearchMedia.class)
	public void p005EditAndSearchMedia(Media media, Media newValues)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		media = PTSearchMedia.editAndSearchMedia(media, newValues);
		Assert.assertEquals(media, newValues);
	}

	/**
	 * Delete a media and conducts a search to see if the media is performs deleted from the database.
	 * 
	 * @param query media for delete
	 */
	@Test(enabled = false, dataProvider = "PV006_DeleteAndSearchMedia", dataProviderClass = PVSearchMedia.class)
	public void p006DeleteAndSearchMedia(QueryMedia query)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String noResultsFound = PTSearchMedia.deleteAndSearhcMedia(query);
		Assert.assertEquals(noResultsFound, "No results found.");
	}

	/**
	 * Borrow one media.
	 * 
	 * @param query attributes of the search
	 * @param name client name
	 * @param date date of the borrow
	 */
	@Test(enabled = false, dataProvider = "PV007_LendBorrwed", dataProviderClass = PVSearchMedia.class)
	public void p007LendBorrwed(QueryMedia query, String name, String date)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(query, name, date);
		LineTableSearchMedia lsm = PTSearchMedia.searchMedia(query);
		Assert.assertEquals(lsm.getPerson(), "Guilherme Alecrim");
	}

	/**
	 * Check if not marking the checkbox "Borrowed" lock the others inputs of the LendMedia screen.
	 * 
	 * @param query attributes of the search
	 */
	@Test(enabled = false, dataProvider = "PV008_LendOnBorred", dataProviderClass = PVSearchMedia.class)
	public void p008LendOnBorred(QueryMedia query)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMediaUnborrowed(query);
		Assert.assertEquals(PTSearchMedia.getScreenSearchMedia().getLendMedia().getTxtName().getValue(), "");
		Assert.assertEquals(PTSearchMedia.getScreenSearchMedia().getLendMedia().getTxtDate().getValue(), "");
	}

	/**
	 * Checks whether the LendMedia screen fill a larger date than the current is displayed the date pop up.
	 * 
	 * @param query attributes of the search
	 * @param name client name
	 * @param date date of the borrow
	 */
	@Test(enabled = false, dataProvider = "PV009_InvalidDate", dataProviderClass = PVSearchMedia.class)
	public void p009InvalidDate(QueryMedia query, String name, String date)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(query, name, date);
		String msg = PTSearchMedia.getScreenSearchMedia().getLendMedia().getPopUpDateInvalid().getDivText().getText();
		Assert.assertEquals(msg, "The date you are trying to use is greater than the actual date.");
	}

	/**
	 * Performs two search in sequence and checks if the result table has been updated with the results of the second search .
	 * 
	 * @param firstQuery First search
	 * @param secondQuery Second search
	 * @param resultFirstQuery Result expect of first search
	 * @param resultSecondQuery Result expect of second search
	 */
	@Test(enabled = true, dataProvider = "PV010_ChangeValuesOfSearch", dataProviderClass = PVSearchMedia.class)
	public void p010ChangeValuesOfSearch(QueryMedia firstQuery, QueryMedia secondQuery, LineTableSearchMedia resultFirstQuery,
	    LineTableSearchMedia resultSecondQuery)
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		LineTableSearchMedia lt = PTSearchMedia.searchMedia(firstQuery);
		Assert.assertEquals(lt, resultFirstQuery);
		lt = PTSearchMedia.searchMedia(secondQuery);
		Assert.assertEquals(lt, resultSecondQuery);
	}
}
