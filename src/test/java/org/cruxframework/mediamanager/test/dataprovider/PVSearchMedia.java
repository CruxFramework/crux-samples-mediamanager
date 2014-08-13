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
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos
public class PVSearchMedia
{
	@DataProvider(name = "PV001_SearchMediaWhitExist")
	public static Object[][] PV001_SearchMediaWhitExist()
	{
		return new Object[][] { { "CT001", new Media("CD", "Dirt", "Artista01P006"), new QueryMedia("", "Dirt", "") },
				{ "CT002", new Media("CD", "Dirt", "Artista01P006"), new QueryMedia("", "Di", "") } };
	}

	@DataProvider(name = "PV002_SearchMediaWhitNotExist")
	public static Object[][] PV002_SearchMediaWhitNotExist()
	{
		return new Object[][] { { "CT001", new QueryMedia("DVD", "NOT EXIST", "") } };
	}

	@DataProvider(name = "PV003_EditMedia")
	public static Object[][] PV003_EditMedia()
	{
		return new Object[][] { { "CT001", new QueryMedia("CD", "Dirt", ""),
				new Media("CD", "EditMediaDirt", "Artista02P006") } };
	}

	@DataProvider(name = "PV004_DeleteMedia")
	public static Object[][] PV004_DeleteMedia()
	{
		return new Object[][] { { "CT001", new QueryMedia("", "Dirt", "") } };
	}

	@DataProvider(name = "PV005_EditAndSearchMedia")
	public static Object[][] PV005_EditAndSearchMedia()
	{
		return new Object[][] { { "CT001", new Media("DVD", "Unplugged MTV", "Mega death"),
				new Media("DVD", "MEDIA EDITADA", "Artista01P006") } };
	}

	@DataProvider(name = "PV006_DeleteAndSearchMedia")
	public static Object[][] PV006_DeleteAndSearchMedia()
	{
		return new Object[][] { { "CT001", new QueryMedia("", "EditNameMediaTest4", "") } };
	}

	@DataProvider(name = "PV007_LendBorrwed")
	public static Object[][] PV007_LendBorrwed()
	{
		return new Object[][] { { "CT001", new QueryMedia("DVD", "Black Rain", ""), "Guilherme Alecrim", "2014 Jun 5" } };
	}

	@DataProvider(name = "PV008_LendOnBorred")
	public static Object[][] PV008_LendOnBorred()
	{
		return new Object[][] { { "CT001", new QueryMedia("DVD", "Black Rain", "") } };
	}

	@DataProvider(name = "PV009_InvalidDate")
	public static Object[][] PV009_InvalidDate()
	{
		return new Object[][] { { "CT001", new QueryMedia("DVD", "Black Rain", ""), "André Luiz", "2015 Jun 5" } };
	}

	@DataProvider(name = "PV010_ChangeValuesOfSearch")
	public static Object[][] P010_ChangeValuesOfSearch()
	{
		return new Object[][] { { "CT001", new QueryMedia("DVD", "Black Rain", ""),
				new QueryMedia("DVD", "MEDIA EDITADA", ""),
				new LineTableSearchMedia(new Media("DVD", "Black Rain", "Teste3"), ""),
				new LineTableSearchMedia(new Media("DVD", "MEDIA EDITADA", "Artista01P006"), "") } };
	}
}
