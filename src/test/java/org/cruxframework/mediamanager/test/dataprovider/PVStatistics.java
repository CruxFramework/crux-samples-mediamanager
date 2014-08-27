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

import static org.cruxframework.mediamanager.test.util.EnumStatistics.CDS_BORROWED;
import static org.cruxframework.mediamanager.test.util.EnumStatistics.CDS_TOTAL;
import static org.cruxframework.mediamanager.test.util.EnumStatistics.CD_MORE_THAN_ONE_MONTH;
import static org.cruxframework.mediamanager.test.util.EnumStatistics.DVDS_BORROWED;
import static org.cruxframework.mediamanager.test.util.EnumStatistics.DVDS_MORE_THAN_ONE_MONTH;
import static org.cruxframework.mediamanager.test.util.EnumStatistics.DVDS_TOTAL;
import static org.cruxframework.mediamanager.test.util.EnumTypeStatistic.BORROWED;
import static org.cruxframework.mediamanager.test.util.EnumTypeStatistic.MORE_THAN_ONE_MONTH;
import static org.cruxframework.mediamanager.test.util.EnumTypeStatistic.TOTAL;

import org.cruxframework.mediamanager.test.model.Media;
import org.testng.annotations.DataProvider;

/**
 * Class description:
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos
public class PVStatistics
{
	@DataProvider(name = "PV001")
	public static Object[][] PV001()
	{
		return new Object[][] { { "CT001", new Media("CD", "Statistics1", "Artista01P006"), CDS_TOTAL, TOTAL },
				{ "CT002", new Media("CD", "Statistics2", "Artista01P006"), CDS_TOTAL, TOTAL },
				{ "CT003", new Media("DVD", "Statistics3", "Artista01P006"), DVDS_TOTAL, TOTAL },
				{ "CT004", new Media("DVD", "Statistics4", "Artista01P006"), DVDS_TOTAL, TOTAL },
				{ "CT005", new Media("CD", "Statistics5", "Artista01P006"), CDS_TOTAL, TOTAL },
				{ "CT006", new Media("DVD", "Statistics6", "Artista01P006"), DVDS_TOTAL, TOTAL },

				{ "CT007", new Media("CD", "Statistics1", "Artista01P006"), CDS_BORROWED, BORROWED },
				{ "CT008", new Media("CD", "Statistics2", "Artista01P006"), CDS_BORROWED, BORROWED },
				{ "CT009", new Media("DVD", "Statistics3", "Artista01P006"), DVDS_BORROWED, BORROWED },
				{ "CT010", new Media("DVD", "Statistics4", "Artista01P006"), DVDS_BORROWED, BORROWED },
				{ "CT011", new Media("CD", "Statistics5", "Artista01P006"), CDS_BORROWED, BORROWED },
				{ "CT012", new Media("DVD", "Statistics6", "Artista01P006"), DVDS_BORROWED, BORROWED },

				{ "CT013", new Media("CD", "Statistics1", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT014", new Media("CD", "Statistics2", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT015", new Media("DVD", "Statistics3", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT016", new Media("DVD", "Statistics4", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT017", new Media("CD", "Statistics5", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT018", new Media("DVD", "Statistics6", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH } };
	}

	@DataProvider(name = "PV002")
	public static Object[][] PV002()
	{
		return new Object[][] {

		{ "CT001", new Media("CD", "Statistics1", "Artista01P006"), CDS_TOTAL, TOTAL },
				{ "CT002", new Media("DVD", "Statistics3", "Artista01P006"), DVDS_TOTAL, TOTAL },

				{ "CT003", new Media("CD", "Statistics2", "Artista01P006"), CDS_BORROWED, BORROWED },
				{ "CT004", new Media("DVD", "Statistics4", "Artista01P006"), DVDS_BORROWED, BORROWED },

				{ "CT005", new Media("CD", "Statistics5", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
				{ "CT006", new Media("DVD", "Statistics6", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH }, };
	}
}
