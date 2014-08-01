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
import org.cruxframework.crux.core.client.css.animation.StandardAnimation;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.SingleViewContainer;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.proxy.LoginProxy;
import org.cruxframework.mediamanager.core.client.service.LoginServiceAsync;

import com.google.gwt.user.client.ui.Widget;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("menuController")
public class MenuController
{	
	@Inject
	public LoginServiceAsync loginServiceAsync;
	
	@Inject
	public LoginProxy loginProxy;

	@Expose
	public void onActivate()
	{
		animateMenuIn();
	}
	
	@Expose
	public void addArtist()
	{
		((SimpleViewContainer) Screen.get("views")).showView("artist");
		closeMenu();
	}
	
	@Expose
	public void addMedia()
	{
		((SingleViewContainer) Screen.get("views")).showView("media");
		closeMenu();
	}
	
	@Expose
	public void stats()
	{
		((SingleViewContainer) Screen.get("views")).showView("statistics");
		closeMenu();
	}
	
	@Expose
	public void searchArtist()
	{
		((SingleViewContainer) Screen.get("views")).showView("artists");
		closeMenu();
	}
	
	@Expose
	public void searchMedia()
	{
		((SingleViewContainer) Screen.get("views")).showView("medias");
		closeMenu();
	}
	
	@Expose
	public void signOut()
	{
		loginProxy.logout(this);
	}
	
	public static void hideMenu()
	{
		Screen.get("menuView").setVisible(false);
	}
	
	/*********************************************
	 * Animations
	 *********************************************/
	
	public void animateMenuIn()
	{
		Widget header = View.of(this).getWidget("mmHeader");
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.slideInDown);
		animation.animate(header);
	}
	
	public void animateMenuOut()
	{
		Widget header = View.of(this).getWidget("mmHeader");
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.slideOutUp);
		animation.animate(header);
	}
	
	private void closeMenu()
	{
		Widget header = View.of(this).getWidget("mmHeader");
		header.removeStyleName("on");
	}
	
	@Expose
	public void toggleMenu()
	{
		Widget header = View.of(this).getWidget("mmHeader");
		if((header.getStyleName()).indexOf("on") != -1)
		{
			header.removeStyleName("on");
		}else
		{
			header.addStyleName("on");
		}
	}
}
