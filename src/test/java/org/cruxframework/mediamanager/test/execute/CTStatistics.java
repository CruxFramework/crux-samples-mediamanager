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
 * Class description:
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
@Test(groups = { "mediaManager", "statistics" }, priority = 7)
public class CTStatistics
{

	@BeforeGroups(groups = "statistics", alwaysRun = true)
	public void restart() throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}

	/** 
	 * Verifica se dado uma modificação na base de dados, as estatisticas estão sendo devidemente incrementadas.
	 * @param ct
	 * @param media
	 * @param statistic
	 * @param typeStatistic
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001", dataProviderClass = PVStatistics.class, groups = { "branch" })
	public void P001_IncrementStatistic(final String ct, Media media, EnumStatistics statistic,
			EnumTypeStatistic typeStatistic) throws InterruptedException
	{
		int totalBefore = PTStatistics.getValueStatistics(statistic);
		PTStatistics.incrementStatistic(media, typeStatistic);
		int totalAfter = PTStatistics.getValueStatistics(statistic);
		Assert.assertEquals(totalAfter, totalBefore + 1);

	}

	/** 
	 * Verifica se dado uma modificação na base de dados, as estatisticas estão sendo devidemente decrentadas.
	 * @param ct
	 * @param media
	 * @param statistic
	 * @param typeStatistic
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV002", dataProviderClass = PVStatistics.class, groups = { "branch" })
	public void P002_DecrementStatistic(final String ct, Media media, EnumStatistics statistic,
			EnumTypeStatistic typeStatistic) throws InterruptedException
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
