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

import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos
@Test(priority = 1)
public class CTConfiguration
{
	/**
	 * @param url
	 * @throws Exception
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "url" })
	public void setUp(String url) throws Exception
	{
		SetUp.setUP(url);
	}
}
