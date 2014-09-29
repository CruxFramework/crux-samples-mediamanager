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

import org.cruxframework.mediamanager.test.model.Media;
import org.testng.annotations.DataProvider;

/**
 * Class description: Class description: This class contains methods that return the inputs for testing the class CTAddMedia for each method
 * of test exist a method provider of input, the name of a methods provider reference a method of test, e.g the method PV001_LoginFail in
 * class PVLogin provide input for test P001_LoginFail in class CTLogin.
 * 
 * Each method of this class returns an array object, each row of this array represents an input to a method of testing.
 * 
 * @author guilherme.alecrim
 */
// CHECKSTYLE:OFF
public class PVAddMedia
{
	@DataProvider(name = "PV001_Medias")
	public static Object[][] PV001_Medias()
	{
		return new Object[][] { { new Media("CD", "Dirt", "Artista01P006") } };
	}

	@DataProvider(name = "PV002_SaveChanges")
	public static Object[][] PV002_SaveChanges()
	{
		return new Object[][] { { new Media("CD", "Evil Empire", "Artista01P006"), new Media("DVD", "Unplugged MTV", "Artista02P006") } };
	}

	@DataProvider(name = "PV003_AddAndSearchMedia")
	public static Object[][] PV003_AddAndSearchMedia()
	{
		return new Object[][] { { new Media("DVD", "Black Rain", "Artista01P006") } };
	}

	@DataProvider(name = "PV004_ChangeAndSearchMedia")
	public static Object[][] PV004_ChangeAndSearchMedia()
	{
		return new Object[][] { { new Media("CD", "MediaForEdit3", "Artista01P006"),
		    new Media("DVD", "EditNameMediaTest4", "Artista02P006") } };
	}

	@DataProvider(name = "PV005_FieldCleanToAdd")
	public static Object[][] PV005_FieldCleanToAdd()
	{
		return new Object[][] { { new Media("", "", "") }, { new Media("", "Media", "") }, { new Media("", "", "Artista01P006") },
		    { new Media("CD", "", "") } };
	}

	@DataProvider(name = "PV006_FieldCleanToChange")
	public static Object[][] PV006_FieldCleanToChange()
	{
		return new Object[][] { { new Media("CD", "NameMediaTest5", "Artista01P006"), new Media("", "", "") }, };
	}
}
