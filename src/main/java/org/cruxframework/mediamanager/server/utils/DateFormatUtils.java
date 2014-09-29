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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Class description: Utilities for date formatting.
 * 
 * @author alexandre.costa
 */
public class DateFormatUtils
{

	private DateFormatUtils()
	{
		// Constructor
	}

	/**
	 * Indicates if a String is valid date.
	 * @param date string date.
	 * @return true if the string is a valid date. false otherwise.
	 */
	public static boolean isValid(String date)
	{
		SimpleDateFormat format = DateFormatContainer.getFormat(DateFormatContainer.FORMAT_DATE);

		try
		{
			format.parse(date);
		}
		catch (ParseException e)
		{
			return false;
		}

		return true;
	}
}

class DateFormatContainer
{
	public static final String FORMAT_DATE = "dd/MM/yyyy";

	private static final ThreadLocal<Map<String, SimpleDateFormat>> FORMATS = new ThreadLocal<Map<String, SimpleDateFormat>>();

	public static SimpleDateFormat getFormat(String formatPattern)
	{
		Map<String, SimpleDateFormat> threadMap = FORMATS.get();
		if (threadMap == null)
		{
			threadMap = new HashMap<String, SimpleDateFormat>();
			FORMATS.set(threadMap);
		}

		SimpleDateFormat format = threadMap.get(formatPattern);

		if (format == null)
		{
			format = new SimpleDateFormat(formatPattern);
			format.setLenient(false);
			threadMap.put(formatPattern, format);
		}
		return format;
	}
}
