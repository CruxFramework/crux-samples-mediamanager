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
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

/**
 * Class description: 
 * @author alexandre.costa
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

	public List<T> getValues()
	{
		return new ArrayList<T>(getMap().values());
	}
	
	@Override
	public void sort(String columnName, boolean ascending, boolean caseSensitive)
	{
		if (map != null && map.size() > 0)
		{
			super.sort(columnName, ascending, caseSensitive);
		}
	}
	
	@Override
	public void sort(String columnName, boolean ascending)
	{
		if (map != null && map.size() > 0)
		{
			super.sort(columnName, ascending);
		}
	}

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
	
	public void clear()
	{
		getMap().clear();
	}
	
	public void add(T dto)
	{
		if (dto != null)
		{
			getMap().put(dto.getId(), dto);
		}
	}
	
	public void remove(T dto)
	{
		if (dto != null)
		{
			getMap().remove(dto.getId());
		}
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
