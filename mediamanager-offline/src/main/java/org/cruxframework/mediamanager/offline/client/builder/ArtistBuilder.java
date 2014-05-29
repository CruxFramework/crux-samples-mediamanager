package org.cruxframework.mediamanager.offline.client.builder;

import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

import com.google.gwt.core.shared.GWT;


public class ArtistBuilder
{
	private Artist artist;
	private boolean doneSave = false;


	public DbMediamanager database = GWT.create(DbMediamanager.class);
	
	
	public ArtistBuilder(ArtistDTO dto)
	{
		artist = new Artist();
		artist.setName(dto.getName());
		Country country = new Country();
		country.setId(dto.getCountry().getId());
		artist.setCountry(country);
		Genre genre = new Genre();
		genre.setId(dto.getGenre().getId());
		artist.setGenre(genre);
		open();
	}
	
	private void open(){
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
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
}
