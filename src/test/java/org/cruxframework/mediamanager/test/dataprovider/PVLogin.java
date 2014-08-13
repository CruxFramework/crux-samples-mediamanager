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

import org.testng.annotations.DataProvider;

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos
public class PVLogin
{
	@DataProvider(name = "PV001_LoginFail")
	public static Object[][] PV001_LoginFail()
	{
		return new Object[][] { { "CT001", "abc", "123" }, { "CT002", "", "admin" }, { "CT003", "admin", "" },
				{ "CT004", "", "" }, };
	}

	@DataProvider(name = "PV001_LoginSucess")
	public static Object[][] PV001_LoginSucess()
	{
		return new Object[][] { { "CT001", "admin", "admin" }, };
	}
}
