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
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.SingleViewContainer;
import org.cruxframework.mediamanager.client.controller.LoginController;
import org.cruxframework.mediamanager.client.controller.MenuController;
import org.cruxframework.mediamanager.client.controller.RootController;
import org.cruxframework.mediamanager.client.controller.callback.LoginCallback;
import org.cruxframework.mediamanager.client.proxy.LoginProxy;
import org.cruxframework.mediamanager.core.client.service.LoginService;
import org.cruxframework.mediamanager.core.client.service.LoginServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class LoginRPC implements LoginProxy
{

	public final LoginServiceAsync loginServiceAsync = GWT.create(LoginService.class);
	
	@Override
	public void login(String login, String password, final LoginController controller)
	{
		loginServiceAsync.login(login, password, 
			new LoginCallback(controller));
	}
	
	
	@Override
	public void isSessionActive(final RootController controller)
	{
		loginServiceAsync.isSessionActive(new AsyncCallbackAdapter<Boolean>()
			{
				@Override
				public void onComplete(Boolean result)
				{
					controller.isSesionActiviteState(result);
				}
			});
	}


	@Override
	public void logout(final MenuController controller)
	{
		loginServiceAsync.logout(new AsyncCallback<Void>()
		{
			
			@SuppressWarnings("static-access")
			@Override
			public void onSuccess(Void result)
			{
				((SingleViewContainer) Screen.get("views")).showView("login");
				controller.hideMenu();
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				controller.animateMenuIn();
			}
		});
	}

	
	

}
