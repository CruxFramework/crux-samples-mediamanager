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
 * Class description: This class contains methods that return the inputs for testing the class CTAddArtist for each method of test exist a
 * method provider of input, the name of a methods provider reference a method of test, e.g the method PV001_LoginFail in class PVLogin
 * provide input for test P001_LoginFail in class CTLogin.
 * 
 * Each method of this class returns an array object, each row of this array represents an input to a method of testing
 * 
 * @author guilherme.alecrim
 */
// CHECKSTYLE:OFF
public class PVAddArtist
{
	@DataProvider(name = "PV001_Artists")
	public static Object[][] PV001_Artists()
	{
		return new Object[][] { { new Artist("Mega death", "United States", "Metal") }, };
	}

	@DataProvider(name = "PV002_AddAndChangeArtists")
	public static Object[][] PV002_AddAndChangeArtists()
	{
		return new Object[][] { { new Artist("Alice in Chains", "United States", "Metal"), new Artist("Edit Artist", "France", "Rock") }, };
	}

	@DataProvider(name = "PV003_AddAndSearchArtists")
	public static Object[][] PV003_AddAndSearchArtists()
	{
		return new Object[][] { { new Artist("Teste3", "United States", "Metal") } };
	}

	@DataProvider(name = "PV004_ChangeAndSearchArtist")
	public static Object[][] PV004_ChangeAndSearchArtist()
	{
		return new Object[][] { { new Artist("Test4", "United States", "Metal"), new Artist("Test5", "England", "Reggae") } };
	}

	@DataProvider(name = "PV005_FieldCleanToAdd")
	public static Object[][] PV005_FieldCleanToAdd()
	{
		return new Object[][] { { new Artist("", "", "") }, { new Artist("preenchido", "", "") }, { new Artist("", "Brazil", "") },
		    { new Artist("", "", "Blues") } };
	}

	@DataProvider(name = "PV006_FieldCleanToChange")
	public static Object[][] PV006_FieldCleanToChange()
	{
		return new Object[][] { { new Artist("A1P006", "United States", "Metal"), new Artist("", "", "") },
		    { new Artist("A2P006", "United States", "Metal"), new Artist("preenchido", "", "") },
		    { new Artist("A3P006", "United States", "Metal"), new Artist("", "Brazil", "") },
		    { new Artist("A4P006", "United States", "Metal"), new Artist("", "", "Blues") } };
	}
}
