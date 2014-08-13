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
 * Class description:
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class PTSearchMedia
{
	private static ScreenSearchMedia screenSearchMedia;

	/**
	 * Retorna a primeira media no resultado de uma busca, ou null caso a busca não retorne nenhum resultado.
	 * @param queryMedia
	 * @return
	 */
	public static LineTableSearchMedia searchMedia(QueryMedia queryMedia)
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		screenSearchMedia = new ScreenSearchMedia();
		PTSearchMedia.populateFieldsQuery(queryMedia);
		screenSearchMedia.getBtnSearch().click();

		try
		{
			lsm = getFirstLineSearch();
		}
		catch (NoSuchElementException e)
		{
			// TODO guilherme.alecrim: exceção calada
		}
		return lsm;
	}

	public static String editMedia(QueryMedia queryMedia, Media newValues) throws InterruptedException
	{
		String sucessEditMedia = null;
		searchMedia(queryMedia);
		screenSearchMedia.getBtnEdit().click();
		PTAddMedia.populateFields(newValues);
		PTAddMedia.getScreenAddMedia().getBtnSaveChanges().click();
		sucessEditMedia = PTAddMedia.getScreenAddMedia().getPopUpSuccessfullySavedSaveChanges().getDivText().getText();
		PTAddMedia.getScreenAddMedia().getPopUpSuccessfullySavedSaveChanges().getBtnOk().click();
		return sucessEditMedia;
	}

	public static String deleteMedia(QueryMedia queryMedia)
	{
		screenSearchMedia = new ScreenSearchMedia();
		String sucessOnDelete = null;
		PTSearchMedia.searchMedia(queryMedia);
		screenSearchMedia.getBtnDelete().click();
		screenSearchMedia.getPopUpDelete().getBtnOk().click();
		sucessOnDelete = screenSearchMedia.getPopUpSucessDelete().getDivText().getText();
		screenSearchMedia.getPopUpSucessDelete().getBtnOk().click();
		return sucessOnDelete;
	}

	public static String deleteAndSearhcMedia(QueryMedia queryMedia)
	{
		String noResultsFound = null;
		screenSearchMedia = new ScreenSearchMedia();
		PTSearchMedia.deleteMedia(queryMedia);
		PTSearchMedia.searchMedia(queryMedia);
		noResultsFound = screenSearchMedia.getPopUpNoResultsFound(1).getDivText().getText();
		screenSearchMedia.getPopUpNoResultsFound(1).getBtnOk().click();
		return noResultsFound;
	}

	public static Media editAndSearchMedia(Media media, Media newValues) throws InterruptedException
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		screenSearchMedia = new ScreenSearchMedia();
		QueryMedia queryMedia = new QueryMedia(media.getType(), media.getName(), "");
		PTSearchMedia.editMedia(queryMedia, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		queryMedia = new QueryMedia(newValues.getType(), newValues.getName(), "");
		PTSearchMedia.searchMedia(queryMedia);
		lsm = PTSearchMedia.getFirstLineSearch();
		return lsm.getMedia();
	}

	public static void populateFieldsQuery(QueryMedia queryMedia)
	{
		screenSearchMedia.getType().select(queryMedia.getType());
		screenSearchMedia.getName().fill(queryMedia.getName());
		screenSearchMedia.getBorrowed().fill(queryMedia.getBorrowed());
	}

	public static LineTableSearchMedia getFirstLineSearch()
	{
		LineTableSearchMedia lsm = new LineTableSearchMedia();
		Media m = new Media();
		m.setType(screenSearchMedia.getTableResult().getCell(2, 1).getText());
		m.setName(screenSearchMedia.getTableResult().getCell(2, 2).getText());
		m.setArtist(screenSearchMedia.getTableResult().getCell(2, 3).getText());
		lsm.setMedia(m);
		lsm.setPerson(screenSearchMedia.getTableResult().getCell(2, 4).getText());
		return lsm;
	}

	public static void lendMedia(QueryMedia queryMedia, String nameLend, String dateLend) throws InterruptedException
	{
		PTSearchMedia.searchMedia(queryMedia);
		screenSearchMedia = new ScreenSearchMedia();
		screenSearchMedia.getBtnLend().click();
		screenSearchMedia.getLendMedia().getCbBorrowed().check();
		screenSearchMedia.getLendMedia().getTxtName().fill(nameLend);
		screenSearchMedia.getLendMedia().getTxtDate().click();
		PTSearchMedia.fillDate(dateLend);
		screenSearchMedia.getLendMedia().getBtnSave().click();
	}

	public static void lendMediaUnborrowed(QueryMedia queryMedia)
	{
		screenSearchMedia = new ScreenSearchMedia();
		PTSearchMedia.searchMedia(queryMedia);
		PTSearchMedia.getScreenSearchMedia().getBtnLend().click();
		PTSearchMedia.getScreenSearchMedia().getLendMedia().getCbBorrowed().uncheck();
	}

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
	 * seleciona um dia no calendario trandando os casos em que um dia pode aparecer mais dia vez em um mês - dias (1-10)
	 * e (20-31) podem aparecer no inicio e no fim do mês
	 * @param strDay
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
					if (strDay < 10)
					{
						stop = true;
						break;
					}
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
	 * seleciona no calendario um mes ou um ano, por default seleciona um ano
	 * @param yearOrMonth
	 * @param optionMonth
	 */
	private static void selectYearOrMonth(int yearOrMonth, boolean optionMonth)
	{
		Cell yearMonth = screenSearchMedia.getLendMedia().getTbHeaderCalendar().getCell(1, 3);
		Cell btnLeft, btnRight;

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
	 * pega o ano que esta no calendario
	 * @param yearMonth
	 * @return
	 */
	private static Integer getYear(String yearMonth)
	{
		Integer year = Integer.parseInt(yearMonth.substring(0, 4));
		return year;
	}

	/** 
	 * pega o mes que esta no calendario
	 * @param yearMonth
	 * @return
	 */
	private static int getMonth(String yearMonth)
	{
		// TODO switch para string
		String month = yearMonth.substring(5, 8);
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

	public static ScreenSearchMedia getScreenSearchMedia()
	{
		return screenSearchMedia;
	}
}
