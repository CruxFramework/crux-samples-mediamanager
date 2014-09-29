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
package org.cruxframework.mediamanager.test.procedure;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;

import br.ufmg.dcc.saotome.beholder.Browser;
import br.ufmg.dcc.saotome.beholder.builder.Builder;
import br.ufmg.dcc.saotome.beholder.selenium.SeleniumController;

/**
 * This class implements the method setup used for initiate the browser and instantiate the web elements.
 * 
 * @author guilherme.alecrim
 */
public class SetUp
{
	private static Browser browser;
	private static Builder builder;
	private static String url;
	private static WebDriver driver;

	/**
	 * init setup.
	 * @param url project URL
	 * @throws MalformedURLException exception
	 */
	public static void setUP(final String url) throws MalformedURLException
	{
		SetUp.browser = SeleniumController.getBrowser();
		SetUp.builder = SeleniumController.getBuilder();
		SetUp.url = url;
		SetUp.browser.open(new URL(url));
		SetUp.driver = SeleniumController.getDriver();
	}

	/**
	 * @return the browser
	 */
	public static Browser getBrowser()
	{
		return browser;
	}

	/**
	 * @return the builder
	 */
	public static Builder getBuilder()
	{
		return builder;
	}

	/**
	 * @return the url
	 */
	public static String getUrl()
	{
		return url;
	}

	/**
	 * @return the driver
	 */
	public static WebDriver getDriver()
	{
		return driver;
	}
}