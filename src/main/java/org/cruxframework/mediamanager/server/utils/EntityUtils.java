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
package org.cruxframework.mediamanager.server.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class EntityUtils
{
	public static <D extends AbstractDTO, E extends AbstractEntity<D>>  List<D> 
		convert(List<E> entities)
	{
		List<D> dtoResultList = new ArrayList<D>(CollectionUtils.size(entities));
		for (E entity : entities)
		{
			dtoResultList.add(entity.getDTORepresentation());
		}
		
		return dtoResultList;
	}
}
