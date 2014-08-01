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
package org.cruxframework.mediamanager.client.proxyfactory.rest;

import org.cruxframework.crux.core.client.rpc.AsyncCallbackAdapter;
import org.cruxframework.mediamanager.client.controller.StatisticsController;
import org.cruxframework.mediamanager.client.proxy.StatisticsProxy;
import org.cruxframework.mediamanager.core.client.dto.StatisticsDTO;
import org.cruxframework.mediamanager.core.client.service.StatisticsService;
import org.cruxframework.mediamanager.core.client.service.StatisticsServiceAsync;

import com.google.gwt.core.shared.GWT;

/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class StatisticsRest implements StatisticsProxy
{

	public final StatisticsServiceAsync statisticsServiceProxy = GWT.create(StatisticsService.class);
	
	/* (non-Javadoc)
	 * @see org.cruxframework.mediamanager.client.proxy.StatisticsProxy#getStatistics(org.cruxframework.mediamanager.client.controller.StatisticsController)
	 */
	@Override
	public void getStatistics(final StatisticsController controller)
	{
		statisticsServiceProxy.getStatistics(new AsyncCallbackAdapter<StatisticsDTO>()
		{
			@Override
			public void onComplete(StatisticsDTO result)
			{	
				controller.StatisticsState(result);				
			}
		});
	}
}
