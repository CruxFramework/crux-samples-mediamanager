package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

import com.google.gwt.core.client.GWT;




/**Class Description: This class has two main methods insert and update. Basically 
 * insert and update one Artist in the database on the client side.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class ArtistClientDB<T extends ArtistDTO> implements ArtistProxy<ArtistDTO>
{
	private Artist artist;
	
	public DbMediamanager database = GWT.create(DbMediamanager.class);
	
	private EditControllerInterface controller;
	
	/********************
	 * Insert
	 *******************/
	@Override
	public void insert(final EditControllerInterface controller, ArtistDTO dto)
	{
		//final ArtistBuilder artist = new ArtistBuilder(dto);
		this.controller = controller;
		this.artist = getArtist(dto, null);
		
		//Open database
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				//Save Artist
				ArtistDao.getInstance(database).save(artist, new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						//This method is required to recover the "id" inserted into a database artist.
						searchId();
					}
				});
			}
			@Override
			public void onError(String message)
			{
				super.onError(message);
			}
		});
	}
	
	/********************
	 * Update
	 *******************/
	
	@Override
	public void update(final EditControllerInterface controller, Integer id,
			ArtistDTO dto)
	{
		//final ArtistBuilder artist = new ArtistBuilder(id, dto);
		
		this.artist = getArtist(dto, id);
				
		//Open database
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				//Save Artist
				ArtistDao.getInstance(database).save(artist, new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						controller.completeUpdate();
					}
				});
			}
			@Override
			public void onError(String message)
			{
				super.onError(message);
			}
		});
	}
	
	/******************
	 * utils
	 *******************/
	
	/**
	 * Recover the "id" inserted into a database artist.
	 */
	private void searchId(){
		ArtistDao.getInstance(database).search(artist.getName(), Artist.STORE_NAME, new DatabaseRetrieveCallback<Artist>()
		{
			
			@Override
			public void onSuccess(Artist result)
			{
				EditOperation edit = new EditOperation();
				edit.setId(result.getId());
				controller.completeInsert(edit);
			}
		});
	}
	
	

	private Artist getArtist(ArtistDTO dto, Integer id){
		Artist artist = new Artist();
		artist = new Artist();
		//Name
		artist.setName(dto.getName());
		//Country
		Country country = new Country();
		country.setId(dto.getCountry().getId());
		artist.setCountry(country);
		//Genre
		Genre genre = new Genre();
		genre.setId(dto.getGenre().getId());
		artist.setGenre(genre);
		//id
		artist.setId(id);
		return artist;
	}
}
