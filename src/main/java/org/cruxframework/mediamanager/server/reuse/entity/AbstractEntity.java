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
package org.cruxframework.mediamanager.server.reuse.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Class description: Base class for entities.
 * 
 * @author alexandre.costa
 * 
 * @param <T> DTO class
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractDTO>
{
	private Integer id;
	private Integer version;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_STORE")
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the version
	 */
	@Version
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version)
	{
		this.version = version;
	}

	/**
	 * @return DTO represation of entity
	 */
	@Transient
	public abstract T getDTORepresentation();

}
