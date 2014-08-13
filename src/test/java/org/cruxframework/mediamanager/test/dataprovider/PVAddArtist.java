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
 * Class description: Massa de dados para os testes de Add Artist  
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim : comentar métodos
public class PVAddArtist
{
	@DataProvider(name = "PV001_Artists")
	public static Object[][] PV001_Artists()
	{
		return new Object[][] { { "CT001", new Artist("Mega death", "United States", "Metal") },
				{ "CT002", new Artist("James Brown", "United States", "Blues") },
				{ "CT003", new Artist("Red Hot Chili Pepers", "United States", "Rock") },
		};
	}

	@DataProvider(name = "PV002_AddAndChangeArtists")
	public static Object[][] PV002_AddAndChangeArtists()
	{
		return new Object[][] { { "CT001", new Artist("Alice in Chains", "United States", "Metal"),
				new Artist("Edit Artist", "France", "Rock") },
		};
	}

	@DataProvider(name = "PV003_AddAndSearchArtists")
	public static Object[][] PV003_AddAndSearchArtists()
	{
		return new Object[][] { { "CT001", new Artist("Teste3", "United States", "Metal") } };
	}

	@DataProvider(name = "PV004_ChangeAndSearchArtist")
	public static Object[][] PV004_ChangeAndSearchArtist()
	{
		return new Object[][] { { "CT001", new Artist("Test4", "United States", "Metal"),
				new Artist("Test5", "England", "Reggae") } };
	}

	@DataProvider(name = "PV005_FieldCleanToAdd")
	public static Object[][] PV005_FieldCleanToAdd()
	{
		return new Object[][] { { "CT001", new Artist("", "", "") }, { "CT002", new Artist("preenchido", "", "") },
				{ "CT003", new Artist("", "Brazil", "") }, { "CT004", new Artist("", "", "Blues") } };
	}

	@DataProvider(name = "PV006_FieldCleanToChange")
	public static Object[][] PV006_FieldCleanToChange()
	{
		return new Object[][] { { "CT001", new Artist("Artista01P006", "United States", "Metal"), new Artist("", "", "") },
				{ "CT002", new Artist("Artista02P006", "United States", "Metal"), new Artist("preenchido", "", "") },
				{ "CT003", new Artist("Artista03P006", "United States", "Metal"), new Artist("", "Brazil", "") },
				{ "CT004", new Artist("Artista04P006", "United States", "Metal"), new Artist("", "", "Blues") } };
	}
}
