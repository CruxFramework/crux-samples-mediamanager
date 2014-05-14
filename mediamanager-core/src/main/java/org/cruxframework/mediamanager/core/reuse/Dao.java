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
package org.cruxframework.mediamanager.core.reuse;

import java.util.List;

import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.utils.Filter;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: 
 * @author alexandre.costa
 */
public interface Dao<DTO extends AbstractDTO>
{
	public AbstractEntity<DTO> find(Integer id);
	
	public void save(AbstractEntity<DTO> entity);
	
	public void delete(AbstractEntity<DTO> entity);
	
	public List<AbstractEntity<DTO>> search(List<Filter> filters, List<OrderBy> orderings) ;
	
	public List<AbstractEntity<DTO>> search(List<Filter> filters, List<OrderBy> orderings, 
		Integer firstResult, Integer pageSize);
	
	public Class<?> getEntityClass();
}
