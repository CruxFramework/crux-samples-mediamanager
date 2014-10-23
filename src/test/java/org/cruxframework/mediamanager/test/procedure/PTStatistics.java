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

import org.cruxframework.mediamanager.test.screen.ScreenStatistics;

/**
 * This class implements all procedures for tests of the screen statistics. 
 * @author guilherme.alecrim
 *
 */

public class PTStatistics 
{
	
	private static ScreenStatistics screenStatistics;


	/**
	 * Return the total Cds in the screen.
	 * @return Cds total.
	 */

	public  static String getCdsTotal() 
	{
		return getScreenStatistics().getDivCdsTotal().getText();
	}
	
	

	/**
	 * Return the total Cds borrowed in the screen.
	 * @return Cds borrowed.
	 */
	public static String getCdsBorrowed()
	{
		return getScreenStatistics().getDivCdsBorrowed().getText();
	}
	
	
	/**
	 * Return the total Cds more than on month in the screen.
	 * @return Cds more than on month .
	 */
	public static String getCdsMoreThanonMonth()
	{
//		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
//		QueryMedia qm = new QueryMedia("CD","CD3NotBorrowed","");	
//		PTSearchMedia.lendMedia(qm, "Guilherme Natan", "2014 Jun 5");
//		Navegation.acessMenu(EnumMenu.STATISTICS);
		return getScreenStatistics().getDivMoreThanOneMonthCD().getText();
	}
	
	/**
	 * Return the total Dvds total in the screen.
	 * @return  Dvds total.
	 */
	public  static String getDvdsTotal() 
	{
		
		return  getScreenStatistics().getDivDVDsTotal().getText();
	}
	
	
	/**
	 * Return the total Dvds borrowed in the screen.
	 * @return   Dvds borrowed.
	 */
	public static String getDvdsBorrowed()
	{
		return  getScreenStatistics().getDivDVDsBorrowed().getText();
	}
	
	
	/**
	 * Return the total Dvds more than on month in the screen.
	 * @return   Dvds more than on month.
	 */
	public static String getDvdsMoreThanonMonth()
	{
//		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
//		QueryMedia qm = new QueryMedia("DVD","DVD3NotBorrowed","");	
//		PTSearchMedia.lendMedia(qm, "Guilherme Natan", "2014 Jun 5");
//		Navegation.acessMenu(EnumMenu.STATISTICS);
		return  getScreenStatistics().getDivMoreThanOneMonthDVD().getText();
	}
	
	/**
	 * Get screen name.
	 * @return name
	 */
	public static String getNameScreenStatistics()
	{
		return getScreenStatistics().getNameScreen();
	}
	
	private static ScreenStatistics getScreenStatistics()
	{
		if (screenStatistics == null)
		{
			screenStatistics = new ScreenStatistics();
		}
		return screenStatistics;
	}
	
}
