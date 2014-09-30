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

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Class description: Synchronous RPC interface for user session control operations.
 * 
 * @author alexandre.costa
 */
public interface LoginService extends RemoteService
{
	/**
	 * Performs user login.
	 * @param login user login
	 * @param password user password
	 * @return true if the user was authenticated.
	 */
	Boolean login(String login, String password);

	/**
	 * checks whether the user's session is active.
	 * @return true if active.
	 */
	Boolean isSessionActive();

	/**
	 * Performs user logout.
	 */
	void logout();
}
