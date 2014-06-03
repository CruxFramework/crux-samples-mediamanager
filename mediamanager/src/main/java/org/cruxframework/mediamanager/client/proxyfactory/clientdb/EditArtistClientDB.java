package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.mediamanager.client.proxy.EditArtistProxy;
import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.CountryDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.GenreDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

import com.google.gwt.core.client.GWT;

/**Class description: This class search for information necessary to build a EditArtist. 
 * The search in the database are made synchronously (One after another).
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class EditArtistClientDB implements EditArtistProxy
{
	
	public DbMediamanager database = GWT.create(DbMediamanager.class);
	
	private final EditArtistDTO editArtistDTO = new EditArtistDTO();
	
	ArtistControllerInterface controller;
	
	@Override
	public void editArtist(ArtistControllerInterface controller,
			final Integer id)
	{
		this.controller = controller;
		
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				if (id != null){
					setArtistDTO(id);
				}else 
				{
					setCountryDTO();
				}
			}
			@Override
			public void onError(String message)
			{
				super.onError(message);
			}
		});
	}
	
	private void setArtistDTO(Integer id)
	{
		ArtistDao.getInstance(database).search(id, Artist.STORE_NAME, new DatabaseCursorCallback<Integer, Artist>()
		{
			
			@Override
			public void onSuccess(Cursor<Integer, Artist> result)
			{
				if ((result != null) && result.hasValue())
				{
					Artist entity = result.getValue();
					editArtistDTO.setArtist(entity.getDTORepresentation());
					result.continueCursor();
				}else
				{
					setCountryDTO();
				}
			}
		});
	}
	
	private void setCountryDTO(){
		CountryDao.getInstance(database).search(Country.STORE_NAME, new DatabaseCursorCallback<Integer, Country>()
				{
					private final List<CountryDTO> country = new ArrayList<CountryDTO>();
					@Override
					public void onSuccess(Cursor<Integer, Country> result)
					{
						if ((result != null) && result.hasValue())
						{
							Country entity = result.getValue();
							country.add(entity.getDTORepresentation());
							result.continueCursor();
						}else
						{
							editArtistDTO.setCountries(country);
							setGenreDTO();
						}
					}
				});
	}
	
	private void setGenreDTO()
	{
		GenreDao.getInstance(database).search(Genre.STORE_NAME, new DatabaseCursorCallback<Integer, Genre>()
		{
			private final List<GenreDTO> genre = new ArrayList<GenreDTO>();
			@Override
			public void onSuccess(Cursor<Integer, Genre> result)
			{
				if ((result != null) && result.hasValue())
				{
					Genre entity = result.getValue();
					genre.add(entity.getDTORepresentation());
					result.continueCursor();
				}else
				{
					editArtistDTO.setGenres(genre);
					//Send the return to Controller
					controller.editableState(editArtistDTO);
				}
			}
		});
	}
}
