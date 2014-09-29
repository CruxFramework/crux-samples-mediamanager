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
package org.cruxframework.mediamanager.client.reuse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cruxframework.crux.core.client.datasource.LocalPagedDataSource;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Base class for datasources.
 * @author alexandre.costa
 * @param <T> DTO type
 */
public abstract class AbstractDTODatasource<T extends AbstractDTO> 
	extends LocalPagedDataSource<T>
{
	private Map<Integer, T> map;
	
	@Override
	public void load()
	{
		updateData(getValues());
	}

	/**
	 * add a DTO list to datasource.
	 * @param values DTO list to be added.
	 */
	public void addValues(List<T> values)
	{
		if (values != null)
		{
			for (T dto : values)
			{
				if (dto != null && dto.getId() != null)
				{
					getMap().put(dto.getId(), dto);
				}
			}
		}
	}
	
	/**
	 * reset datasource.
	 */
	public void clear()
	{
		getMap().clear();
	}
	
	/**
	 * add a new DTO to datasource.
	 * @param dto DTO to be added.
	 */
	public void add(T dto)
	{
		if (dto != null)
		{
			getMap().put(dto.getId(), dto);
		}
	}
	
	/**
	 * remove a DTO from datasource.
	 * @param dto DTO to be removed
	 */
	public void remove(T dto)
	{
		if (dto != null)
		{
			getMap().remove(dto.getId());
		}
	}
	
	/****************************************
	 * utilities
	 ****************************************/
	
	private List<T> getValues()
	{
		return new ArrayList<T>(getMap().values());
	}

	
	private Map<Integer, T> getMap()
	{
		if (map == null)
		{
			map = new HashMap<Integer, T>();
		}
		
		return map;
	}
}
