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

import org.cruxframework.mediamanager.test.model.LineTableSearchMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.screen.ScreenSearchMedia;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.openqa.selenium.NoSuchElementException;

import br.ufmg.dcc.saotome.beholder.ui.table.Cell;
import br.ufmg.dcc.saotome.beholder.ui.table.Cell.Coordinate;

/**
 * Class description: This class implements the procedures in 'Search Media' screen.
 * 
 * @author guilherme.alecrim
 */
public class PTSearchMedia
{
	private static ScreenSearchMedia screenSearchMedia;

	/**
	 * Returns the first media in a search result, or null if the search does not return any results.
	 * 
	 * @param queryMedia parameter for search media
	 * @return LineTableSearchMedia: result of search
	 */
	public static LineTableSearchMedia searchMedia(QueryMedia queryMedia)
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		PTSearchMedia.populateFieldsQuery(queryMedia);
		getScreenSearchMedia().getBtnSearch().click();

		try
		{
			lsm = getFirstLineSearch();
		}
		catch (NoSuchElementException e)
		{
			System.out.println("No results find in your search");
		}
		return lsm;
	}

	/**
	 * Search for a media and change yours values.
	 * 
	 * @param queryMedia parameter for search media
	 * @param newValues new values for search media
	 * @return edit media success message
	 */
	public static String editMedia(QueryMedia queryMedia, Media newValues)
	{
		String sucessEditMedia = null;
		searchMedia(queryMedia);
		getScreenSearchMedia().getBtnEdit().click();
		PTAddMedia.populateFields(newValues);
		PTAddMedia.getScreenAddMedia().getBtnSaveChanges().click();
		sucessEditMedia = PTAddMedia.getScreenAddMedia().getPopUp().getMenssagePopUp();
		PTAddMedia.getScreenAddMedia().getPopUp().confirmPopUp();
		return sucessEditMedia;
	}

	/**
	 * Delete a media.
	 * 
	 * @param queryMedia parameter for search media
	 * @return delete media success message
	 */
	public static String deleteMedia(QueryMedia queryMedia)
	{
		String sucessOnDelete = null;
		PTSearchMedia.searchMedia(queryMedia);
		getScreenSearchMedia().getBtnDelete().click();
		getScreenSearchMedia().getPopUpDelete().confirmPopUp();		
		sucessOnDelete = getScreenSearchMedia().getPopUpSucessDelete().getMenssagePopUp();
		getScreenSearchMedia().getPopUpSucessDelete().confirmPopUp();
		return sucessOnDelete;
	}

	/**
	 * Check if media deleted was exclude of the data base.
	 * 
	 * @param queryMedia media for delete
	 * @return message 'No Results Found'
	 */
	public static String deleteAndSearhcMedia(QueryMedia queryMedia)
	{
		String noResultsFound = null;
		PTSearchMedia.deleteMedia(queryMedia);
		PTSearchMedia.searchMedia(queryMedia);
		noResultsFound = getScreenSearchMedia().getPopUpNoResultsFound().getMenssagePopUp();
		getScreenSearchMedia().getPopUpNoResultsFound().confirmPopUp();
		return noResultsFound;
	}

	/**
	 * Check if changes in media occurs in database.
	 * 
	 * @param media current parameters of the media
	 * @param newValues new values for media
	 * @return media searched in 'Search Media' screens
	 */
	public static Media editAndSearchMedia(Media media, Media newValues)
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		QueryMedia queryMedia = new QueryMedia(media.getType(), media.getName(), "");
		PTSearchMedia.editMedia(queryMedia, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		queryMedia = new QueryMedia(newValues.getType(), newValues.getName(), "");
		PTSearchMedia.searchMedia(queryMedia);
		lsm = PTSearchMedia.getFirstLineSearch();
		return lsm.getMedia();
	}

	/**
	 * Fill fields of 'Search Media' screen.
	 * 
	 * @param values values to fill
	 */
	public static void populateFieldsQuery(QueryMedia values)
	{
		getScreenSearchMedia().getType().select(values.getType());
		getScreenSearchMedia().getName().fill(values.getName());
		getScreenSearchMedia().getBorrowed().fill(values.getBorrowed());
	}

	/**
	 * Realize one search.
	 * 
	 * @return the first result of a search
	 */
	public static LineTableSearchMedia getFirstLineSearch()
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		Media m = new Media();
		m.setType(getScreenSearchMedia().getTableResult().getCell(2, 1).getText());
		m.setName(getScreenSearchMedia().getTableResult().getCell(2, 2).getText());
		m.setArtist(getScreenSearchMedia().getTableResult().getCell(2, 3).getText());
		lsm.setMedia(m);
		lsm.setPerson(getScreenSearchMedia().getTableResult().getCell(2, 4).getText());
		return lsm;
	}

	/**
	 * Lend a media.
	 * 
	 * @param queryMedia parameters of search
	 * @param nameLend name of the client which borrowed media
	 * @param dateLend date of the lend media
	 */
	public static void lendMedia(QueryMedia queryMedia, String nameLend, String dateLend)
	{
		PTSearchMedia.searchMedia(queryMedia);
		getScreenSearchMedia().getBtnLend().click();
		getScreenSearchMedia().getLendMedia().getCbBorrowed().check();
		getScreenSearchMedia().getLendMedia().getTxtName().fill(nameLend);
		getScreenSearchMedia().getLendMedia().getTxtDate().click();
		PTSearchMedia.fillDate(dateLend);
		getScreenSearchMedia().getLendMedia().getBtnSave().click();
	}

	/**
	 * Unborrowed media.
	 * 
	 * @param queryMedia parameter for search media.
	 */
	public static void lendMediaUnborrowed(QueryMedia queryMedia)
	{
		PTSearchMedia.searchMedia(queryMedia);
		getScreenSearchMedia().getBtnLend().click();
		getScreenSearchMedia().getLendMedia().getCbBorrowed().uncheck();
	}

	/**
	 * Instance screenSearchMedia.
	 * 
	 * @return screenSearchMedia
	 */
	public static ScreenSearchMedia getScreenSearchMedia()
	{
		if (screenSearchMedia == null)
		{
			screenSearchMedia = new ScreenSearchMedia();
		}
		return screenSearchMedia;
	}

	/**
	 * empty search result.
	 * @param query query
	 * @return message
	 */
	public static String searchMediaWithNotExist(QueryMedia query)
	{
		PTSearchMedia.searchMedia(query);
		String noResultsFound = PTSearchMedia.getScreenSearchMedia().getPopUpNoResultsFound().getMenssagePopUp();
		return noResultsFound;
	}

	/**********************************************************
	 * This auxiliary methods for fill date to lend media screen 
	 * ********************************************************/

	/**
	 * Browse in the calendar to find month or year
	 * 
	 * @param yearOrMonth: year or month for select
	 * @param optionMonth: month for select
	 */
	private static void selectYearOrMonth(int yearOrMonth, boolean optionMonth)
	{
		Cell yearMonth = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 3);
		Cell btnLeft;  
		Cell btnRight;

		int atualValue;
		if (optionMonth)
		{
			btnLeft = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 2);
			btnRight = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 4);
			atualValue = getMonth(yearMonth.getText());
		}
		else
		{
			btnLeft = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 1);
			btnRight = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 5);
			atualValue = getYear(yearMonth.getText());
		}

		while (yearOrMonth != atualValue)
		{
			if (yearOrMonth > atualValue)
			{
				btnRight.click();
				atualValue++;
			}
			else
			{
				btnLeft.click();
				atualValue--;
			}
		}
	}

	/**
	 * Get year string composed by year and month in the header of calendar
	 * 
	 * @param yearMonth: string year month in the header of calendar
	 * @return: year
	 */
	private static Integer getYear(String yearMonth)
	{
		Integer year = Integer.parseInt(yearMonth.substring(0, 4));
		return year;
	}

	/**
	 * Fill fiedl date
	 * 
	 * @param date: date in format dd/mm/yyyy
	 */
	private static void fillDate(String date)
	{
		int year = getYear(date);
		int month = getMonth(date);
		int day = Integer.parseInt(date.substring(9));
		selectYearOrMonth(year, false);
		selectYearOrMonth(month, true);
		selectDay(day);
	}

	/**
	 * Select one day in calendar
	 * 
	 * @param strDay: day for select
	 */
	private static void selectDay(int strDay)
	{
		Coordinate c = null;
		boolean stop = false;
		for (int i = 2; i < 8; i++)
		{
			for (int j = 1; j < 8; j++)
			{
				Cell dayCell = screenSearchMedia.getLendMedia().getTbDayCalendar().getCell(i, j);
				int day = Integer.parseInt(dayCell.getText());
				if (strDay == day)
				{
					c = new Coordinate(i, j);
					/*
					 * cases one day is displayed more than once in matrix of moth e.g day 1, can is displayed in begin and the end of the
					 * matrix month
					 */
					if (strDay < 10)
					{
						stop = true;
						break;
					}
					/*
					 * case interval of day 20 - 31 is displayed more that once in the matrix month e.g day 31 of the current month and day
					 * 31 month before
					 */
					else if ((strDay > 20 && strDay < 31))
					{
						continue;
					}
				}
			}
			if (stop)
			{
				break;
			}
		}
		screenSearchMedia.getLendMedia().getTbDayCalendar().getCell(c).click();
	}

	/**
	 * Map the month in calendar for a option integer
	 * 
	 * @param calendarMonth: String which represent month in the calendar
	 * @return: option integer equivalent to month.
	 */
	private static int getMonth(String calendarMonth)
	{
		String month = calendarMonth.substring(5, 8);
		if (month.equals("Jan"))
		{
			return 1;
		}
		else if (month.equals("Feb"))
		{
			return 2;
		}
		else if (month.equals("Mar"))
		{
			return 3;
		}
		else if (month.equals("Apr"))
		{
			return 4;
		}
		else if (month.equals("May"))
		{
			return 5;
		}
		else if (month.equals("Jun"))
		{
			return 6;
		}
		else if (month.equals("Jul"))
		{
			return 7;
		}
		else if (month.equals("Aug"))
		{
			return 8;
		}
		else if (month.equals("Sep"))
		{
			return 9;
		}
		else if (month.equals("Oct"))
		{
			return 10;
		}
		else if (month.equals("Nov"))
		{
			return 11;
		}
		else
		{
			return 12;
		}
	}
}
