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
package org.cruxframework.mediamanager.model.spring.reuse;

import javax.transaction.Transactional;

import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.core.reuse.AbstractService;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractServiceSpring<D extends AbstractDTO> 
	extends AbstractService<D>
{
	
	@Transactional
	public EditOperation update(@PathParam("id") Integer id, D dto)
		throws RestException
	{
		return super.update(id, dto);
	}
	
	@Transactional
	public EditOperation insert(D dto) throws RestException
	{
		return super.insert(dto);
	}
	
	@Transactional
	public EditOperation delete(D dto) throws RestException
	{
		return super.delete(dto);
	}

}
