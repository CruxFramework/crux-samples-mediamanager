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
package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import java.util.Date;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCountCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.mediamanager.client.controller.StatisticsController;
import org.cruxframework.mediamanager.client.proxy.StatisticsProxy;
import org.cruxframework.mediamanager.core.client.dto.StatisticsDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.offline.client.dao.impl.MediaDao;
import org.cruxframework.mediamanager.offline.client.entity.Media;

/**
 * Class description:
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class StatisticsClientDB extends ServiceClientDB implements
	StatisticsProxy
{
	// private static final Logger LOGGER =
	// Logger.getLogger(StatisticsClientDB.class.getName());
	// LOGGER.log(Level.SEVERE, "Unexpected error.", e);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cruxframework.mediamanager.client.proxy.StatisticsProxy#getStatistics
	 * (org.cruxframework.mediamanager.client.controller.StatisticsController)
	 */
	@Override
	public void getStatistics(final StatisticsController controller)
	{
		final StatisticsDTO dto = new StatisticsDTO();
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					countCDs(dto, controller);
				}
			});
		}
		else
		{
			countCDs(dto, controller);
		}
	}

	/***********************************************
	 * utilities
	 ***********************************************/

	private void countCDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
		MediaDao.getInstance(getDatabase()).count(MediaType.CD.name(), "type",
			new DatabaseCountCallback()
			{

				@Override
				public void onSuccess(int result)
				{
					dto.setTotalCDs(result);
					countDVDs(dto, controller);
				}
			});
	}

	private void countDVDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
		MediaDao.getInstance(getDatabase()).count(MediaType.DVD.name(), "type",
			new DatabaseCountCallback()
			{

				@Override
				public void onSuccess(int result)
				{
					dto.setTotalDVDs(result);
					countBorrowedCDs(dto, controller);
				}
			});
	}

	private void countBorrowedCDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
		Object[] obj = new Object[]
		{ 1, new String(MediaType.CD.name()) };
		MediaDao.getInstance(getDatabase()).count(obj, "borrowedType",
			new DatabaseCountCallback()
			{
				@Override
				public void onSuccess(int result)
				{
					dto.setBorrowedCDs(result);
					countBorrowedDVDs(dto, controller);
				}
			});
	}

	private void countBorrowedDVDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
		Object[] obj = new Object[]
		{ 1, new String(MediaType.DVD.name()) };
		MediaDao.getInstance(getDatabase()).count(obj, "borrowedType",
			new DatabaseCountCallback()
			{
				@Override
				public void onSuccess(int result)
				{
					dto.setBorrowedDVDs(result);
					countForgottenCDs(dto, controller);
				}
			});
	}

	@SuppressWarnings("deprecation")
	private void countForgottenCDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DAY_OF_YEAR, -30);
		Date date = new Date();
		date.setMonth(date.getMonth() - 1);
		MediaDao.getInstance(getDatabase()).searchUpperBound(date, "date", new DatabaseCursorCallback<Date, Media>()
		{
			private int count = 0;
			@Override
			public void onSuccess(Cursor<Date, Media> result)
			{
				if (result != null && result.hasValue())
				{
					Media media = result.getValue();
					if (media.getType().equals("CD")){
						count++;
					}
					result.continueCursor();
				}
				else 
				{
					dto.setForgottenCDs(count);
					countForgottenDVDs(dto, controller);
				}
				
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void countForgottenDVDs(final StatisticsDTO dto,
		final StatisticsController controller)
	{
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DAY_OF_YEAR, -30);
		Date date = new Date();
		date.setMonth(date.getMonth() - 1);
		MediaDao.getInstance(getDatabase()).searchUpperBound(date, "date", new DatabaseCursorCallback<Date, Media>()
		{
			private int count = 0;
			@Override
			public void onSuccess(Cursor<Date, Media> result)
			{
				if (result != null && result.hasValue())
				{
					Media media = result.getValue();
					if (media.getType().equals("DVD")){
						count++;
					}
					result.continueCursor();
				}
				else 
				{
					dto.setForgottenDVDs(count);
					controller.StatisticsState(dto);
				}
				
			}
		});
	}
}