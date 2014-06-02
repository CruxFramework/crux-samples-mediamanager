/**
 * 
 */
package org.cruxframework.mediamanager.offline.client.reuse;

import org.cruxframework.crux.core.client.db.annotation.Store.Indexed;
import org.cruxframework.crux.core.client.db.annotation.Store.Key;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

/**
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public abstract class OfflineEntity<T extends AbstractDTO> 
{
	
	private Integer id;
	private String name;
	
	public abstract String getStoreName();
	
	public abstract T getDTORepresentation();
		
	
	/************************
	 * GETTERS and SETTERS 
	 *********************/
	/**
	 * @key autoIncrement = true
	 * @return the id
	 */
	@Key(autoIncrement = true)
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
	 * @return the name
	 */
	@Indexed
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
	
}
