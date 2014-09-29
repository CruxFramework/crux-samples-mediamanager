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

import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddArtist;
import org.cruxframework.mediamanager.test.procedure.PTAddMedia;
import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Class description: This class will perform the configuration parameters for the tests, such as adding data in the database, and set the
 * parameters of the browser and the builder. It is will be executed before all tests.
 * 
 * @author guilherme.alecrim
 */
@Test(priority = 1)
public class CTConfiguration
{
	/**
	 * This method instantiates the browser with the url of the system, the url is passed within testng.xml file.
	 * 
	 * @param url URL of the application
	 * @throws Exception unexpected exception
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "url" })
	public void setUp(String url) throws Exception
	{
		SetUp.setUP(url);
		registeringMassData();
	}

	/**
	 * This method acesss the system and insert some datas necessary for some tests.
	 */
	private void registeringMassData()
	{
		Navegation.loginSucess("admin", "admin");
		System.out.println(">>>>>> adding  artists ...");
		artists();
		System.out.println(">>>>>> adding medias ...");
		medias();
		Navegation.signOut();
		System.out.println(">>>>>> Sucess at  add data. Executing tests ...");
	}

	/**
	 * Execute procedures for add artists
	 */
	private void artists()
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("Artista01P006", "Mexico", "Metal"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("Artista02P006", "England", "Jazz"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("ArtistForDelete", "England", "Jazz"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("ArtistForEdit", "United States", "Metal"));
	}

	/**
	 * Execute procedures for add medias
	 */
	private void medias()
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD", "MediaForDelete1", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD", "MediaForDelete2", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD", "MediaForBorrowed", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD", "MediaForSearch", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD", "MediaForSearch2", "Artista02P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD", "MediaForEdit", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD", "MediaForEdit2", "Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD", "MediaForEdit3", "Artista01P006"));
	}
}
