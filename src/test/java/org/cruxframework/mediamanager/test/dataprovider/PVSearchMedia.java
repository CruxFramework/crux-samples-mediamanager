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

import org.cruxframework.mediamanager.test.model.LineTableSearchMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.testng.annotations.DataProvider;

/**
 * Class description: This class contains methods that return the inputs for testing the class CTSearchMedia for each method of test exist a
 * method provider of input, the name of a methods provider reference a method of test, e.g the method PV001_LoginFail in class PVLogin
 * provide input for test P001_LoginFail in class CTLogin.
 * 
 * Each method of this class returns an array object, each row of this array represents an input to a method of testing
 * 
 * @author guilherme.alecrim
 */
// CHECKSTYLE:OFF
public class PVSearchMedia
{
	@DataProvider(name = "PV001_SearchMediaWhitExist")
	public static Object[][] PV001_SearchMediaWhitExist()
	{
		return new Object[][] { { new Media("CD", "MediaForSearch", "Artista01P006"), new QueryMedia("", "MediaForSearch", "") },
		    { new Media("CD", "MediaForSearch", "Artista01P006"), new QueryMedia("", "MediaForS", "") } };
	}

	@DataProvider(name = "PV002_SearchMediaWhitNotExist")
	public static Object[][] PV002_SearchMediaWhitNotExist()
	{
		return new Object[][] { { new QueryMedia("DVD", "NOT EXIST", "") } };
	}

	@DataProvider(name = "PV003_EditMedia")
	public static Object[][] PV003_EditMedia()
	{
		return new Object[][] { { new QueryMedia("DVD", "MediaForEdit", ""), new Media("CD", "EditMediaDirt", "Artista02P006") } };
	}

	@DataProvider(name = "PV004_DeleteMedia")
	public static Object[][] PV004_DeleteMedia()
	{
		return new Object[][] { { new QueryMedia("", "MediaForDelete1", "") } };
	}

	@DataProvider(name = "PV005_EditAndSearchMedia")
	public static Object[][] PV005_EditAndSearchMedia()
	{
		return new Object[][] { { new Media("DVD", "MediaForEdit2", "Artista01P006"), new Media("DVD", "MEDIA EDITADA", "Artista02P006") } };
	}

	@DataProvider(name = "PV006_DeleteAndSearchMedia")
	public static Object[][] PV006_DeleteAndSearchMedia()
	{
		return new Object[][] { { new QueryMedia("", "MediaForDelete2", "") } };
	}

	@DataProvider(name = "PV007_LendBorrwed")
	public static Object[][] PV007_LendBorrwed()
	{
		return new Object[][] { { new QueryMedia("DVD", "MediaForBorrowed", ""), "Guilherme Alecrim", "2014 Jun 5" } };
	}

	@DataProvider(name = "PV008_LendOnBorred")
	public static Object[][] PV008_LendOnBorred()
	{
		return new Object[][] { { new QueryMedia("DVD", "MediaForBorrowed", "") } };
	}

	@DataProvider(name = "PV009_InvalidDate")
	public static Object[][] PV009_InvalidDate()
	{
		return new Object[][] { { new QueryMedia("DVD", "MediaForBorrowed", ""), "André Luiz", "2015 Jun 5" } };
	}

	@DataProvider(name = "PV010_ChangeValuesOfSearch")
	public static Object[][] P010_ChangeValuesOfSearch()
	{
		return new Object[][] { { new QueryMedia("CD", "MediaForSearch", ""), new QueryMedia("DVD", "MediaForSearch2", ""),
		    new LineTableSearchMedia(new Media("CD", "MediaForSearch", "Artista01P006"), ""),
		    new LineTableSearchMedia(new Media("DVD", "MediaForSearch2", "Artista02P006"), "") }

		};
	}
}
