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
		return new Object[][] { 				
				{"7"}		    
		};
	}
	
	@DataProvider(name = "PV002")
	public static Object[][] PV002()
	{
		return new Object[][] { 				
				{"2"}		    
		};
	}
	
	@DataProvider(name = "PV003")
	public static Object[][] PV003()
	{
		return new Object[][] { 				
				{"1"}		    
		};
	}

	@DataProvider(name = "PV004")
	public static Object[][] PV004()
	{
		return new Object[][] { 				
				{"7"}		    
		};
	}
	
	
	@DataProvider(name = "PV005")
	public static Object[][] PV005()
	{
		return new Object[][] { 				
				{"2"}		    
		};
	}
	
	
	@DataProvider(name = "PV006")
	public static Object[][] PV006()
	{
		return new Object[][] { 				
				{"1"}		    
		};
	}
}
