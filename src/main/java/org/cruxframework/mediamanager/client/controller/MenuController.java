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
import org.cruxframework.crux.core.client.rpc.AsyncCallbackAdapter;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.SingleViewContainer;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.service.LoginServiceAsync;

import com.google.gwt.user.client.ui.Widget;

/**
 * View controller for menu commands.
 * 
 * @author alexandre.costa
 */
@Controller("menuController")
public class MenuController
{
	private static final String ON = "on";

	private static final String MM_HEADER = "mmHeader";

	private static final String VIEWS = "views";
	
	@Inject
	private LoginServiceAsync loginServiceAsync;

	/**
	 * Handles activate event.
	 */
	@Expose
	public void onActivate()
	{
		animateMenuIn();
	}

	/**
	 * Shows add artist view.
	 */
	@Expose
	public void addArtist()
	{
		((SimpleViewContainer) Screen.get(VIEWS)).showView("artist");
		closeMenu();
	}

	/**
	 * Shows add media view.
	 */
	@Expose
	public void addMedia()
	{
		((SingleViewContainer) Screen.get(VIEWS)).showView("media");
		closeMenu();
	}

	/**
	 * Shows statistics view.
	 */
	@Expose
	public void stats()
	{
		((SingleViewContainer) Screen.get(VIEWS)).showView("statistics");
		closeMenu();
	}

	/**
	 * Shows search artist view.
	 */
	@Expose
	public void searchArtist()
	{
		((SingleViewContainer) Screen.get(VIEWS)).showView("artists");
		closeMenu();
	}

	/**
	 * Shows search media view.
	 */
	@Expose
	public void searchMedia()
	{
		((SingleViewContainer) Screen.get(VIEWS)).showView("medias");
		closeMenu();
	}

	/**
	 * Performs user logout.
	 */
	@Expose
	public void signOut()
	{
		loginServiceAsync.logout(new LogoutCallback());
	}

	/**
	 * Toggle menu.
	 */
	@Expose
	public void toggleMenu()
	{
		Widget header = View.of(this).getWidget(MM_HEADER);
		if ((header.getStyleName()).indexOf(ON) != -1)
		{
			header.removeStyleName(ON);
		}
		else
		{
			header.addStyleName(ON);
		}
	}
	
	/********************************************
	 * Getters and setters
	 ********************************************/
	
	/**
	 * @param loginServiceAsync the loginServiceAsync to set
	 */
	public void setLoginServiceAsync(LoginServiceAsync loginServiceAsync)
	{
		this.loginServiceAsync = loginServiceAsync;
	}


	/*********************************************
	 * Animations
	 *********************************************/
	
	private static void hideMenu()
	{
		Screen.get("menuView").setVisible(false);
	}

	private void animateMenuIn()
	{
		Widget header = View.of(this).getWidget(MM_HEADER);
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.slideInDown);
		animation.animate(header);
	}

	private void closeMenu()
	{
		Widget header = View.of(this).getWidget(MM_HEADER);
		header.removeStyleName(ON);
	}
	
	/*********************************************
	 * Callback classes
	 *********************************************/

	private class LogoutCallback extends AsyncCallbackAdapter<Void>
	{
		@Override
		public void onComplete(Void result)
		{
			((SingleViewContainer) Screen.get(VIEWS)).showView("login");
			hideMenu();
		}

		@Override
		public void onError(Throwable e)
		{
			animateMenuIn();
		}
	}
}
