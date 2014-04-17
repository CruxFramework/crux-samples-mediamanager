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
package org.cruxframework.mediamanager.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.cruxframework.crux.core.server.dispatch.RequestAware;
import org.cruxframework.mediamanager.client.service.LoginService;
import org.cruxframework.mediamanager.server.entity.User;
import org.cruxframework.mediamanager.server.entity.dao.UserDAO;
import org.cruxframework.mediamanager.server.utils.Filter;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class LoginServiceImpl implements LoginService, RequestAware
{
	private static final String LOGIN = "login";
	
	private HttpServletRequest request;
	
	@Override
	public Boolean login(String login, String password)
	{
		List<Filter> filters = new ArrayList<Filter>(2);
		filters.add(new Filter("login", login));
		filters.add(new Filter("password", password));
		List<User> result =  SpringUtils.get().getBean(
			UserDAO.class).search(filters, null);
		
		if (CollectionUtils.size(result) != 1)
		{
			return false;
		}
		
		request.getSession(true).setAttribute(LOGIN, login);
		return true;
	}

	@Override
	public Boolean isSessionActive()
	{
		String login = (String) request.getSession().getAttribute(LOGIN);
		return StringUtils.isNotEmpty(login);
	}
	
	@Override
	public void logout()
	{
		request.getSession().invalidate();
	}
	
	/*******************************************
	 * Getters and setters
	 *******************************************/

	@Override
	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

}
