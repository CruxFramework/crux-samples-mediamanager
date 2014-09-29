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

import org.cruxframework.mediamanager.test.dataprovider.PVAddMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddMedia;
import org.cruxframework.mediamanager.test.procedure.PTSearchMedia;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: This class performs the test cases to "Add media" screen. Their test cases depend of the artists added by in the
 * "Add Artist" screen The providers of inputs are methods of class PVAddArtist class.
 * 
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVAddMedia }
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "addMedia" }, priority = 4)
public class CTAddMedia
{
	/**
	 * Adds a media and Checks if the pop-up with a MESSAGE of "saved successfully!" this being displayed.
	 * 
	 * @param media media which be will added
	 */
	@Test(enabled = true, dataProvider = "PV001_Medias", dataProviderClass = PVAddMedia.class)
	public void p001AddMedia(Media media)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgSucess = PTAddMedia.addMedia(media);
		Assert.assertEquals(msgSucess, "Successfully saved!");
		Assert.assertEquals(PTAddMedia.isDisplayedPopUp(), false);
	}

	/**
	 * Adds a media and then immediately change the data that have been added.
	 * 
	 * @param media  media which be will added
	 * @param newValues new values for media added
	 */
	@Test(enabled = true, dataProvider = "PV002_SaveChanges", dataProviderClass = PVAddMedia.class)
	public void p002AddAndChangeMedia(Media media, Media newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgSaveChanges = PTAddMedia.saveChanges(media, newValues);
		Assert.assertEquals(msgSaveChanges, "Successfully saved!");
	}

	/**
	 * Add a media and check if it appears in data base.
	 * 
	 * @param media : media which be will added
	 */
	@Test(enabled = true, dataProvider = "PV003_AddAndSearchMedia", dataProviderClass = PVAddMedia.class)
	public void p003AddAndSearchMedia(Media media)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(media);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		QueryMedia queryMedia = new QueryMedia(media.getType(), media.getName(), "");
		Media resultSearch = PTSearchMedia.searchMedia(queryMedia).getMedia();
		Assert.assertEquals(media, resultSearch);
	}

	/**
	 * Modifies a media and verifies that the changes made ​​appears in the database.
	 * 
	 * @param media media which be will added
	 * @param newValues new values for media added
	 */
	@Test(enabled = true, dataProvider = "PV004_ChangeAndSearchMedia", dataProviderClass = PVAddMedia.class)
	public void p004ChangeAndSearchMedia(Media media, Media newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.saveChanges(media, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		QueryMedia queryMedia = new QueryMedia(newValues.getType(), newValues.getName(), "");
		Media resultSearch = PTSearchMedia.searchMedia(queryMedia).getMedia();
		Assert.assertEquals(newValues, resultSearch);
	}

	/**
	 * Leave without filling some fields when adding media and check if pop up "Fill all fields." is displayed.
	 * 
	 * @param media media which be will added
	 */
	@Test(enabled = true, dataProvider = "PV005_FieldCleanToAdd", dataProviderClass = PVAddMedia.class)
	public void p005FieldCleanToAdd(Media media)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgFillAllFields = PTAddMedia.addMedia(media);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}

	/**
	 * Add media and try to change your attributes leaving some field without fill.
	 * 
	 * @param media media which be will added
	 * @param newValues new values for media added
	 */
	@Test(enabled = true, dataProvider = "PV006_FieldCleanToChange", dataProviderClass = PVAddMedia.class)
	public void p006FieldCleanToChange(Media media, Media newValues)
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgFillAllFields = PTAddMedia.saveChanges(media, newValues);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}
}
