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
 * Class description:  This class contains methods that return the inputs for testing the class CTSearchArtist
 * for each method of test exist a method provider of input, the name of a methods provider reference 
 * a method of test, e.g the method PV001_LoginFail in class PVLogin provide input for test P001_LoginFail in class CTLogin
 * @author guilherme.alecrim
 */
public class PVSearchArtist
{
	
	
	/*
	 * Each method of this class returns an array object, 
	 * each row of this array represents an input to 
	 * a method of testing
	 */
	
	@DataProvider(name = "PV001_SearchArtist")
	public static Object[][] PV001_SearchArtist()
	{
		return new Object[][] { 
				{  "Artista01P006", new Artist("Artista01P006","Mexico","Metal")},
				{  "Artista02P006", new Artist("Artista02P006","England","Jazz") }, 
				};
	}

	@DataProvider(name = "PV002_ArtistNoExist")
	public static Object[][] PV002_ArtistNoExist()
	{
		return new Object[][] { 
				{  "Not Exist" },
				};
	}

	@DataProvider(name = "PV003_DeleteArtist")
	public static Object[][] PV003_DeleteArtist()
	{
		return new Object[][] { 
				{  "ArtistForDelete" },
				};
	}

	@DataProvider(name = "PV004_EditAndSearchArtist")
	public static Object[][] PV004_EditAndSearchArtist()
	{
		return new Object[][] {
		{ new Artist("ArtistForEdit", "United States", "Metal"), new Artist("Mamonas Assassinas", "Jamaica", "Blues") }, };
	}

	@DataProvider(name = "PV005_ChangeValuesSearch")
	public static Object[][] PV005_ChangeValuesSearch()
	{
		return new Object[][] { 
				{"Artista01P006", "Artista02P006", new Artist("Artista01P006", "Mexico", "Metal"), new Artist("Artista02P006", "England", "Jazz") }, };
	}
}
