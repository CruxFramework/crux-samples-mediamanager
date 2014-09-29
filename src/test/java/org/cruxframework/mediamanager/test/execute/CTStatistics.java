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
package org.cruxframework.mediamanager.test.execute;

import org.cruxframework.mediamanager.test.dataprovider.PVStatistics;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTStatistics;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.cruxframework.mediamanager.test.util.EnumStatistics;
import org.cruxframework.mediamanager.test.util.EnumTypeStatistic;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Class description: This class performs tests in Statistics screen , this tests insert or delete data in database and check if statistics
 * was updates. The providers of inputs are methods of class PVStatistics
 * 
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVStatistics}
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "statistics" }, priority = 7)
public class CTStatistics
{
	/**
	 * Access statistics view.
	 */
	@BeforeGroups(groups = "statistics", alwaysRun = true)
	public void restart()
	{
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/**
	 * Given a insertion in the database, the statistics are being updated.
	 * 
	 * @param media Media with insert in data base
	 * @param statistic Statistic witch will be check,e.g CDS_TOTAL, CDS_BORROWED, etc
	 * @param typeStatistic Type of statistic, e.g is Total, Borrowed of More than one month
	 */
	@Test(enabled = true, dataProvider = "PV001", dataProviderClass = PVStatistics.class)
	public void p001IncrementStatistic(Media media, EnumStatistics statistic, EnumTypeStatistic typeStatistic)
	{
		int totalBefore = PTStatistics.getValueStatistics(statistic);
		PTStatistics.incrementStatistic(media, typeStatistic);
		int totalAfter = PTStatistics.getValueStatistics(statistic);
		Assert.assertEquals(totalAfter, totalBefore + 1);
	}

	/**
	 * Given a decrease in the database, the statistics are being updated.
	 * 
	 * @param media Media with insert in data base
	 * @param statistic Statistic witch will be check,e.g CDS_TOTAL, CDS_BORROWED, etc
	 * @param typeStatistic Type of statistic, e.g is Total, Borrowed of More than one month
	 */
	@Test(enabled = true, dataProvider = "PV002", dataProviderClass = PVStatistics.class)
	public void p002DecrementStatistic(Media media, EnumStatistics statistic, EnumTypeStatistic typeStatistic)
	{
		int totalBefore = PTStatistics.getValueStatistics(statistic);
		PTStatistics.decrementStatistic(media, typeStatistic);
		int totalAfter = PTStatistics.getValueStatistics(statistic);
		if (totalBefore > 0)
		{
			Assert.assertEquals(totalAfter, totalBefore - 1);
		}
		else
		{
			Assert.assertEquals(totalAfter, 0);
		}
	}
}
