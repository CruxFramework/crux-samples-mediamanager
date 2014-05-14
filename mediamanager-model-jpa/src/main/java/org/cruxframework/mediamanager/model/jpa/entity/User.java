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

import org.cruxframework.mediamanager.core.client.dto.UserDTO;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.model.jpa.reuse.JpaEntity;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_USER")
public class User extends JpaEntity<UserDTO> implements AbstractEntity<UserDTO>
{
	private String name;
	private String login;
	private String password;
	
	public User()
	{
		
	}
	
	public User(String name, String login, String password)
	{
		this.name = name;
		this.login = login;
		this.password = password;
	}

	@Override
	@Transient
	public UserDTO getDTORepresentation()
	{
		UserDTO dto = new UserDTO();
		dto.setName(getName());
		dto.setLogin(getLogin());
		dto.setPassword(getPassword());
		return dto;
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

	/**
	 * @return the login
	 */
	@Column(nullable = false, unique = true)
	public String getLogin()
	{
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login)
	{
		this.login = login;
	}

	/**
	 * @return the password
	 */
	@Column(nullable = false)
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
