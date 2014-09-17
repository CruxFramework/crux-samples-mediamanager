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

import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.screen.ScreenStatistics;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.cruxframework.mediamanager.test.util.EnumStatistics;
import org.cruxframework.mediamanager.test.util.EnumTypeStatistic;

/**
 * Class description: This class implements the procedures for 'Statistics' screen
 * @author guilherme.alecrim
 */
public class PTStatistics
{
	private static ScreenStatistics screenStatistics;

	
	public static ScreenStatistics getScreenStatistics()
	{
		if (screenStatistics == null)
		{
			screenStatistics = new ScreenStatistics();
		}
		return screenStatistics;
	}

	/**
	 * Increment the value of the one statistic 
	 * @param media : media for add in the data base
	 * @param statistic: statistic for increment e.g total, borrowed or more than one month
	 */
	public static void incrementStatistic(Media media, EnumTypeStatistic statistic) 
	{
		switch (statistic)
		{
			case TOTAL:
				incrementTotal(media);
				break;
			case BORROWED:
				incrementTotalBorrowed(media);
				break;
			case MORE_THAN_ONE_MONTH:
				incrementMoreThanOneMonth(media);
				break;
			default:
				break;
		}
	}

	/**
	 * Decrement the value of the one statistic 
	 * @param media:  media for remove in the data base
	 * @param statistic: statistic for decrement e.g total, borrowed or more than one month
	 */
	public static void decrementStatistic(Media media, EnumTypeStatistic statistic)  
	{
		switch (statistic)
		{
			case TOTAL:
				decrementTotal(media);
				break;
			case BORROWED:
				decrementTotalBorrowed(media);
				break;
			case MORE_THAN_ONE_MONTH:
				decrementMoreThanOneMonth(media);
				break;
			default:
				break;
		}
	}

	/**
	 * Get value of statistic  in the screen Statistics
	 * @param statistic: statistic desired
	 * @return: value correspondent of statistic desired
	 */
	public static int getValueStatistics(EnumStatistics statistic)
	{
		switch (statistic)
		{
			case CDS_TOTAL:
				return Integer.parseInt(getScreenStatistics().getDivCdsTotal().getText());
			case CDS_BORROWED:
				return Integer.parseInt(getScreenStatistics().getDivCdsBorrowed().getText());
			case CD_MORE_THAN_ONE_MONTH:
				return Integer.parseInt(getScreenStatistics().getDivMoreThanOneMonthCD().getText());
			case DVDS_TOTAL:
				return Integer.parseInt(getScreenStatistics().getDivDVDsTotal().getText());
			case DVDS_BORROWED:
				return Integer.parseInt(getScreenStatistics().getDivDVDsBorrowed().getText());
			case DVDS_MORE_THAN_ONE_MONTH:
				return Integer.parseInt(getScreenStatistics().getDivMoreThanOneMonthDVD().getText());
			default:
				return -1;
		}
	}

	public static String getNameScreenStatistics()
	{
		return getScreenStatistics().getNameScreen();
	}
	
	
	
	/**
	 * Increment value of statistic Total
	 * @param media: media for add in the data base
	 */
	private static void incrementTotal(Media media) 
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(media);
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Increment value of statistic Total Borrowed
	 * @param media: media for borrowed  
	 */
	
	private static void incrementTotalBorrowed(Media media) 
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(new QueryMedia(media.getType(), media.getName(), ""), "André Luiz", "2014 Jun 20");
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Increment value of statistic More Than One Month
	 * @param media:  media for borrowed  more than one month
	 */
	private static void incrementMoreThanOneMonth(Media media)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(new QueryMedia(media.getType(), media.getName(), ""), "André Luiz", "2014 May 3");
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Decrement value of statistic Total
	 * @param media:  media for remove in the data base
	 */
	private static void decrementTotal(Media media)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.deleteMedia(new QueryMedia(media.getType(), media.getName(), ""));
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Decrement value of statistic Total Borrowed
	 * @param media: media for remove in the data base
	 */
	private static void decrementTotalBorrowed(Media media) 
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMediaUnborrowed(new QueryMedia(media.getType(), media.getName(), "André Luiz"));
		PTSearchMedia.getScreenSearchMedia().getLendMedia().getBtnSave().click();
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Decrement value of statistic More Than On eMonth
	 * @param media: media for remove in the data base
	 */
	private static void decrementMoreThanOneMonth(Media media)  
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(new QueryMedia(media.getType(), media.getName(), ""), "André Luiz", "2014 Jul 3");
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}
	
	
}
