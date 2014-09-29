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
 * Class description: This class contains methods that return the inputs for testing the class CTStatistics for each method of test exist a
 * method provider of input, the name of a methods provider reference a method of test, e.g the method PV001_LoginFail in class PVLogin
 * provide input for test P001_LoginFail in class CTLogin.
 * 
 * Each method of this class returns an array object, each row of this array represents an input to a method of testing.
 * 
 * @author guilherme.alecrim
 */
// CHECKSTYLE:OFF
public class PVStatistics
{
	@DataProvider(name = "PV001")
	public static Object[][] PV001()
	{
		return new Object[][] { { new Media("CD", "Statistics1", "Artista01P006"), CDS_TOTAL, TOTAL },
		    { new Media("CD", "Statistics2", "Artista01P006"), CDS_TOTAL, TOTAL },
		    { new Media("DVD", "Statistics3", "Artista01P006"), DVDS_TOTAL, TOTAL },
		    { new Media("DVD", "Statistics4", "Artista01P006"), DVDS_TOTAL, TOTAL },
		    { new Media("CD", "Statistics5", "Artista01P006"), CDS_TOTAL, TOTAL },
		    { new Media("DVD", "Statistics6", "Artista01P006"), DVDS_TOTAL, TOTAL },

		    { new Media("CD", "Statistics1", "Artista01P006"), CDS_BORROWED, BORROWED },
		    { new Media("CD", "Statistics2", "Artista01P006"), CDS_BORROWED, BORROWED },
		    { new Media("DVD", "Statistics3", "Artista01P006"), DVDS_BORROWED, BORROWED },
		    { new Media("DVD", "Statistics4", "Artista01P006"), DVDS_BORROWED, BORROWED },
		    { new Media("CD", "Statistics5", "Artista01P006"), CDS_BORROWED, BORROWED },
		    { new Media("DVD", "Statistics6", "Artista01P006"), DVDS_BORROWED, BORROWED },

		    { new Media("CD", "Statistics1", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("CD", "Statistics2", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("DVD", "Statistics3", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("DVD", "Statistics4", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("CD", "Statistics5", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("DVD", "Statistics6", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH } };
	}

	@DataProvider(name = "PV002")
	public static Object[][] PV002()
	{
		return new Object[][] {

		{ new Media("CD", "Statistics1", "Artista01P006"), CDS_TOTAL, TOTAL },
		    { new Media("DVD", "Statistics3", "Artista01P006"), DVDS_TOTAL, TOTAL },

		    { new Media("CD", "Statistics2", "Artista01P006"), CDS_BORROWED, BORROWED },
		    { new Media("DVD", "Statistics4", "Artista01P006"), DVDS_BORROWED, BORROWED },

		    { new Media("CD", "Statistics5", "Artista01P006"), CD_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH },
		    { new Media("DVD", "Statistics6", "Artista01P006"), DVDS_MORE_THAN_ONE_MONTH, MORE_THAN_ONE_MONTH }, };
	}
}
