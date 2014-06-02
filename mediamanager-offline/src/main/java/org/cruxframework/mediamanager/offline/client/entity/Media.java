package org.cruxframework.mediamanager.offline.client.entity;


import java.util.Date;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;


/**
 * Class description: Defines an entity Media with the properties that must be stored in the database.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
@Store(Media.STORE_NAME)
public class Media extends OfflineEntity<MediaDTO> 
{
	public static final String STORE_NAME = "media";
	private Boolean borrowed = false;
	private String person;
	private Date date;
	private Artist artist;

	/**********************************
	 * Methods Abstract
	 **********************************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	
	@Override
	public MediaDTO getDTORepresentation()
	{		
		return null;
	}
	
	/************************
	 * GETTERS and SETTERS
	 ***********************/	
	/**
	 * @return the borrowed
	 */
	public Boolean getBorrowed()
	{
		return borrowed;
	}

	/**
	 * @param borrowed the borrowed to set
	 */
	public void setBorrowed(Boolean borrowed)
	{
		this.borrowed = borrowed;
	}

	/**
	 * @return the person
	 */
	public String getPerson()
	{
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person)
	{
		this.person = person;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist()
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist)
	{
		this.artist = artist;
	}

}
