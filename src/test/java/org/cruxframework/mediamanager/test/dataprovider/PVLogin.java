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
 * Class description: This class contains methods that return  inputs for test the class CTLogin
 * for each method of test exist a method provider of input, the name of a methods provider reference 
 * a method of test, e.g the method PV001_LoginFail in class PVLogin provide input for test P001_LoginFail in class CTLogin
 * @author guilherme.alecrim
 */
public class PVLogin
{
	
	/*
	 * Each method of this class returns an array object, 
	 * each row of this array represents an input to 
	 * a method of test
	 */
	
	@DataProvider(name = "PV001_LoginFail")
	public static Object[][] PV001_LoginFail()
	{
		return new Object[][] { 
				{"abc", "123" }, 
				{"", "admin" }, 
				{"admin", "" },
				{ "", "" },
				};
	}

	@DataProvider(name = "PV001_LoginSucess")
	public static Object[][] PV001_LoginSucess()
	{
		return new Object[][] { 
				{"admin", "admin" },
				};
	}
}
