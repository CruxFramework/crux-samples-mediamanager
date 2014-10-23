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
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTStatistics;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Class description: This class performs tests in Statistics screen.
 * The providers of inputs are methods of class PVStatistics.
 * 
 * @see { org.cruxframework.mediamanager.test.dataprovider.PVStatistics}
 * @author guilherme.alecrim
 */
@Test(groups = { "mediaManager", "statistics" }, priority = 3)
public class CTStatistics 
{
	
	/**
	 * Before tests access menu statistics. 
	 */
	@BeforeGroups(groups = "statistics", alwaysRun = true)
	public void restart()
	{
		Navegation.acessMenu(EnumMenu.STATISTICS);
	}
	
	
	/**
	 * This test check if number of CDs total in screen statistics is equal total Cds in data base.
	 * @param expected value expect of CDs total. 
	 */
	@Test(enabled = true, dataProvider = "PV001", dataProviderClass = PVStatistics.class)
	public  void p001CdsTotal(String expected) 
	{
		String actual = PTStatistics.getCdsTotal();		
		Assert.assertEquals(actual,  expected);
	}
	
	/**
	 * This test check if number of  CDs borrowed in screen statistics is equal total Cds borrowed in data base.
	 * @param expected value expect of CDs borrowed.
	 */
	@Test(enabled = true, dataProvider = "PV002", dataProviderClass = PVStatistics.class)
	public void p002CdsBorrowed(String expected)
	{
		String actual = PTStatistics.getCdsBorrowed();
		Assert.assertEquals(actual,  expected);
	}
	
	/**
	 * This test check if number of  CDs more than on  month in screen statistics is equal total Cds more than on  month in data base. 	 
	 * @param expected value expect of CDs more than on  month. 
	 */
	@Test(enabled = true, dataProvider = "PV003", dataProviderClass = PVStatistics.class)
	public void p003CdsMoreThanonMonth(String expected)
	{
		String actual = PTStatistics.getCdsMoreThanonMonth();
		Assert.assertEquals(actual,  expected);
	}
	
	/**
	 * This test check if DVDs total in screen statistics is equal total DVDs  in data base. 	 
	 * @param expected value expect of DVDs total. 
	 */
	@Test(enabled = true, dataProvider = "PV004", dataProviderClass = PVStatistics.class)
	public  void p004DvdsTotal(String expected) 
	{
		String actual = PTStatistics.getDvdsTotal();
		Assert.assertEquals(actual, expected);
	}
	
	
	/**
	 * This test check if number of DVDs borrowed in screen statistics is equal total  DVDs borrowed in data base.
	 * @param expected value expect of  DVDs borrowed. 
	 */
	@Test(enabled = true, dataProvider = "PV005", dataProviderClass = PVStatistics.class)
	public void p005DvdsBorrowed(String expected)
	{
		
		String actual = PTStatistics.getDvdsBorrowed();
		Assert.assertEquals(actual, expected);
	}

	/**
	 * This test check if number of DVDs More Than on Month in screen statistics is equal total  DVDs More Than on Month in data base.
	 * @param expected value expect of DVDs More Than on Month. 
	 */
	@Test(enabled = true, dataProvider = "PV006", dataProviderClass = PVStatistics.class)
	public void p006DvdsMoreThanonMonth(String expected)
	{
		String actual = PTStatistics.getDvdsMoreThanonMonth();
		Assert.assertEquals(actual, expected);
	}
}
