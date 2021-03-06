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
package org.cruxframework.mediamanager.model.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.model.jpa.reuse.JpaEntity;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_GENERE")
public class Genre extends JpaEntity<GenreDTO> 
	implements AbstractEntity<GenreDTO>
{
	private String name;
	
	public Genre()
	{
		
	}
	
	public Genre(String name)
	{
		this.name = name;
	}

	/**
	 * @return the name
	 */
	@Column(nullable = false)
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	@Transient
	public GenreDTO getDTORepresentation()
	{
		GenreDTO dto = new GenreDTO();
		dto.setId(getId());
		dto.setName(getName());
		return dto;
	}
}
