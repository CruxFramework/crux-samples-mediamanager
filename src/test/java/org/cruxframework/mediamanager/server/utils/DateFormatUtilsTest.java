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
package org.cruxframework.mediamanager.server.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class for {@link DateFormatUtils}.
 * 
 * @author alexandre.costa
 */
// CHECKSTYLE:OFF
public class DateFormatUtilsTest
{
	@Test
	public void testIsValidCT001()
	{
		System.out.println("testIsValid - ct01");
		String date = "01/01/2012";
		assertTrue(DateFormatUtils.isValid(date));
	}

	@Test
	public void testIsValidCT002()
	{
		System.out.println("testIsValid - ct02");
		String date = "01/55/2012";
		assertFalse(DateFormatUtils.isValid(date));
	}

	@Test
	public void testIsValidCT003()
	{
		System.out.println("testIsValid - ct03");
		String date = "01/05/1812";
		assertTrue(DateFormatUtils.isValid(date));
	}

	@Ignore
	@Test
	public void testIsValidFalse()
	{
		int a = 2;
		assertEquals(a, 7);
	}
}
