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
package org.cruxframework.mediamanager.client.controller;

import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.rpc.AsyncCallbackAdapter;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.smartfaces.client.dialog.animation.DialogAnimation;
import org.cruxframework.crux.smartfaces.client.label.Label;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;
import org.cruxframework.mediamanager.core.client.dto.StatisticsDTO;
import org.cruxframework.mediamanager.core.client.service.StatisticsServiceAsync;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("statisticsController")
public class StatisticsController extends AbstractController
{
	@Inject
	public StatisticsServiceAsync statisticsServiceProxy;
	
	@Expose
	public void onActivate()
	{
		animateContent();
		WaitBox.show("Wait", DialogAnimation.fadeDownUp);
		statisticsServiceProxy.getStatistics(new AsyncCallbackAdapter<StatisticsDTO>()
		{
			@Override
			public void onComplete(StatisticsDTO result)
			{	
				WaitBox.hideAllDialogs();
				View view = View.of(StatisticsController.this);
				((Label)view.getWidget("totalCDsLabel")).setText(
					result.getTotalCDs().toString());
				
				((Label)view.getWidget("borrowedCDsLabel")).setText(
					result.getBorrowedCDs().toString());
				
				((Label)view.getWidget("forgottenCDsLabel")).setText(
					result.getForgottenCDs().toString());
				
				((Label)view.getWidget("totalDVDsLabel")).setText(
					result.getTotalDVDs().toString());
				
				((Label)view.getWidget("borrowedDVDsLabel")).setText(
					result.getBorrowedDVDs().toString());
				
				((Label)view.getWidget("forgottenDVDsLabel")).setText(
					result.getForgottenDVDs().toString());
				
			}
		});
	}
}
