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

import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.crux.core.shared.rest.annotation.DELETE;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.POST;
import org.cruxframework.crux.core.shared.rest.annotation.PUT;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;
import org.cruxframework.crux.core.shared.rest.annotation.StateValidationModel;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

/**
 * Class description: Abstract superclass for REST based services.
 * @param <D> DTO type class
 * @param <E> AbstractEntity type class
 * @see AbstractDTO
 * @see AbstractEntity
 * 
 * @author alexandre.costa
 */
public interface IRestService<D extends AbstractDTO, 
	E extends AbstractEntity<D>>
{
	/**
	 * Retrieves a record form persistence mechanism by id.
	 * @param id register id
	 * @return DTO representation from an entity record
	 * @throws RestException
	 */
	@GET/*(cacheTime = GET.ONE_DAY)*/
	@Path("{id}")
	public D get(@PathParam("id") Integer id) throws RestException;


	/**
	 * Update a record on persistence mechanism.
	 * @param id entity id
	 * @param dto DTO representation for updating an entity record. 
	 */
	@PUT(validatePreviousState = StateValidationModel.ENSURE_STATE_MATCHES)
	@Path("{id}")
	public EditOperation update(@PathParam("id") Integer id, D dto)
		throws RestException;
	
	/**
	 * Insert a new register on database.
	 * @param dto new instance
	 * @return instance ID
	 * @throws RestException
	 */
	@POST
	@Path("new")
	public EditOperation insert(D dto) throws RestException;

	/**
	 * delete a register on database.
	 * @param abstractBO new instance
	 * @return instance ID
	 * @throws RestException
	 */
	@DELETE
	@Path("delete")
	public EditOperation delete(D dto) throws RestException;
	
}
