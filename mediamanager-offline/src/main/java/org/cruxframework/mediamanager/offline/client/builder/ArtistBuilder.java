package org.cruxframework.mediamanager.offline.client.builder;

import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

import com.google.gwt.core.shared.GWT;

/**This class is responsible for building an artist with the 
 * information provided by the DTO and execute database operations. 
 * Also, monitors, by doneX variables, whether the transactions database finished.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class ArtistBuilder
{
	private Artist artist;
	private boolean doneSave = false;
	private Integer id;

	public DbMediamanager database = GWT.create(DbMediamanager.class);
	
	public ArtistBuilder(ArtistDTO dto)
	{
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
		
		//Open database
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				//Save Artist
				ArtistDao.getInstance().save(artist, database, new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						//Search the id of the artist
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
	
	public ArtistBuilder(Integer id, ArtistDTO dto)
	{
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
		//Id
		artist.setId(id);
		
		//Open database
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				//Save Artist
				ArtistDao.getInstance().save(artist, database, new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						setDoneSave(true);
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
	
	private void searchId(){
		ArtistDao.getInstance().search(artist.getName(), artist.STORE_NAME, database, new DatabaseRetrieveCallback<Artist>()
		{
			
			@Override
			public void onSuccess(Artist result)
			{
				setId(result.getId());
				setDoneSave(true);
			}
		});
	}

	
	
	
	/**************************
	 * GETTER's and SETTER's
	 * 
	 **************************/
	/**
	 * @return the doneSave
	 */
	public boolean isDoneSave()
	{
		return doneSave;
	}

	/**
	 * @param doneSave the doneSave to set
	 */
	public void setDoneSave(boolean doneSave)
	{
		this.doneSave = doneSave;
	}

	/**
	 * @return the id
	 */
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
}
