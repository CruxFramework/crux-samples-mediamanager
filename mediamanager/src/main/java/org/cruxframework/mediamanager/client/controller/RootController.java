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
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.proxy.LoginProxy;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("rootController")
public class RootController extends AbstractController
{
	
	@Inject
	public LoginProxy loginProxy;
	
	@Expose
	public void onActivate()
	{
		
		loginProxy.isSessionActive(this);
	}
	
	
	public void isSesionActiviteState(Boolean result)
	{
		if (result != null && result)
		{
			Screen.get("menuView").setVisible(true);
			((SimpleViewContainer) Screen.get("views")).showView("statistics");
		} else
		{
			((SimpleViewContainer) Screen.get("views")).showView("login");
		}
	}
}
