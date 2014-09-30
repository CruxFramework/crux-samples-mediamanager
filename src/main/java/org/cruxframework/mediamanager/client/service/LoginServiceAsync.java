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
package org.cruxframework.mediamanager.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Class description: Asynchronous RPC interface for user session control operations.
 * 
 * @author alexandre.costa
 */
public interface LoginServiceAsync
{
	/**
	 * Performs user login.
	 * @param login user login
	 * @param password user password
	 * @param callback callback object.
	 */
	void login(String login, String password, AsyncCallback<Boolean> callback);

	/**
	 * checks whether the user's session is active.
	 * @param callback callback object
	 */
	void isSessionActive(AsyncCallback<Boolean> callback);

	/**
	 * Performs user logout.
	 * @param callback callback object.
	 */
	void logout(AsyncCallback<Void> callback);
}
