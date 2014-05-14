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
package org.cruxframework.mediamanager.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.cruxframework.crux.core.server.dispatch.RequestAware;
import org.cruxframework.mediamanager.core.client.dto.UserDTO;
import org.cruxframework.mediamanager.core.client.service.LoginService;
import org.cruxframework.mediamanager.core.dao.UserDao;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.utils.Filter;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractLoginService implements LoginService, 
	RequestAware
{
	private static final String LOGIN = "login";
	
	private HttpServletRequest httpServletRequest;
	
	public Boolean login(String login, String password)
	{
		List<Filter> filters = new ArrayList<Filter>(2);
		filters.add(new Filter("login", login));
		filters.add(new Filter("password", password));
		List<AbstractEntity<UserDTO>> result =  getUserDao().search(filters, null);
		
		if (CollectionUtils.size(result) != 1)
		{
			return false;
		}
		
		httpServletRequest.getSession(true).setAttribute(LOGIN, login);
		return true;
	}

	public Boolean isSessionActive()
	{
		String login = (String) httpServletRequest.getSession().getAttribute(LOGIN);
		return StringUtils.isNotEmpty(login);
	}
	
	public void logout()
	{
		httpServletRequest.getSession().invalidate();
	}
	
	/*******************************************
	 * Getters and setters
	 *******************************************/
	
	public abstract UserDao getUserDao();

	public void setRequest(HttpServletRequest request)
	{
		this.httpServletRequest = request;
	}
}
