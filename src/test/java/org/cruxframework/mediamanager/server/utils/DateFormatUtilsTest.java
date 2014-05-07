// package test;
package org.cruxframework.mediamanager.server.utils;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;


public class DateFormatUtilsTest {

	@Test
	public void testIsValid_ct001() {
		System.out.println("testIsValid - ct01");
		String date = "01/01/2012";
		assertTrue( DateFormatUtils.isValid(date) );
	}

	@Test
	public void testIsValid_ct002() 
	{
		System.out.println("testIsValid - ct02");
		String date = "01/55/2012";
		assertFalse( DateFormatUtils.isValid(date) );
	}

	@Test
	public void testIsValid_ct003() 
	{
		System.out.println("testIsValid - ct03");
		String date = "01/05/1812";
		assertTrue( DateFormatUtils.isValid(date) );
	}
	
	@Ignore
	@Test
	public void testIsValid_false() 
	{
		int a = 2;
		assertEquals( a , 7 );
	}
	
}
