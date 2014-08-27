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
package org.cruxframework.mediamanager.test.dataprovider;

import org.cruxframework.mediamanager.test.model.Artist;
import org.testng.annotations.DataProvider;

/**
 * Class description:  
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos 
public class PVSearchArtist
{
	@DataProvider(name = "PV001_SearchArtist")
	public static Object[][] PV001_SearchArtist()
	{
		return new Object[][] { { "CT001", "Artista01P006", new Artist("Artista01P006","Mexico","Metal")},
				{ "CT002", "Artista02P006", new Artist("Artista02P006","England","Jazz") }, };
	}

	@DataProvider(name = "PV002_ArtistNoExist")
	public static Object[][] PV002_ArtistNoExist()
	{
		return new Object[][] { { "CT001", "Not Exist" }, };
	}

	@DataProvider(name = "PV003_DeleteArtist")
	public static Object[][] PV003_DeleteArtist()
	{
		return new Object[][] { { "CT001", "ArtistForDelete" }, };
	}

	@DataProvider(name = "PV004_EditAndSearchArtist")
	public static Object[][] PV004_EditAndSearchArtist()
	{
		return new Object[][] {
		{ "CT001", new Artist("ArtistForEdit", "United States", "Metal"), new Artist("Mamonas Assassinas", "Jamaica", "Blues") }, };
	}

	@DataProvider(name = "PV005_ChangeValuesSearch")
	public static Object[][] PV005_ChangeValuesSearch()
	{
		return new Object[][] { { "CT001", "Artista01P006", "Artista02P006",
				new Artist("Artista01P006", "Mexico", "Metal"), new Artist("Artista02P006", "England", "Jazz") }, };
	}
}
