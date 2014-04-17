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
import org.cruxframework.crux.core.client.screen.views.BindView;
import org.cruxframework.crux.core.client.screen.views.WidgetAccessor;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.smartfaces.client.dialog.animation.DialogAnimation;
import org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid;
import org.cruxframework.mediamanager.client.reuse.controller.SearchController;
import org.cruxframework.mediamanager.client.service.ArtistServiceProxy;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;

import com.google.gwt.user.client.ui.TextBox;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("artistsController")
public class ArtistsController extends SearchController<ArtistDTO>
{
	@Inject
	public ArtistsView artistsViewviewAcessor;
	
	@Inject
	public ArtistServiceProxy artistServiceProxy;
	
	@Expose
	public void onActivate()
	{
		animateContent();
		animateResults();
		
		/* Clean grid */
		getResultGrid().clear();
		getResultGrid().refresh();
	}
	
	@Expose
	public void search()
	{
		WaitBox.show("Wait", DialogAnimation.fadeDownUp);
		String name = artistsViewviewAcessor.nameTextBox().getText();
		artistServiceProxy.search(name, new SearchCallback()); 
	}
	
	/*************************************
	 * WidgetAccessor interfaces
	 *************************************/
	
	@BindView("artists")
	public static interface ArtistsView extends WidgetAccessor
	{
		TextBox nameTextBox();
		
		DeviceAdaptiveGrid tableGrid();
	}

	/*************************************
	 * Overwritten methods
	 *************************************/
	
	@Override
	protected String getMessageForDeleteValidationError()
	{
		return "Delete all media of this artist before deleting it.";
	}

	@Override
	protected String getEditViewOutcome()
	{
		return "artist";
	}
	
	@Override
	protected DeviceAdaptiveGrid getResultGrid()
	{
		return artistsViewviewAcessor.tableGrid();
	}

	@Override
	protected ArtistServiceProxy getRestServiceProxy()
	{
		return artistServiceProxy;
	}
}
