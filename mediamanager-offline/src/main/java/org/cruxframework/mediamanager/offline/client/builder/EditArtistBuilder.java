package org.cruxframework.mediamanager.offline.client.builder;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.CountryDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.GenreDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

import com.google.gwt.core.shared.GWT;


/** Builds a editArtist object with the necessary information
 * @author bruno.rafael 
 */
public class EditArtistBuilder
{	
	private List<CountryDTO> country = new ArrayList<CountryDTO>();
	private List<GenreDTO> genre = new ArrayList<GenreDTO>();
	private ArtistDTO artist = null;
	
	//Variables synchronization control
	private boolean doneCountry = false;
	private boolean doneGenre = false;
	private boolean doneArtist = false;
	
	public DbMediamanager database = GWT.create(DbMediamanager.class);
	
	public EditArtistBuilder(Integer id)
	{
		open(id);
	}
	
	private void open(final Integer id){
		this.database.open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				if (id != null){
					setArtistDTO(id);
				}
				
				setCountryDTO();
				
				setGenreDTO();
			}
			@Override
			public void onError(String message)
			{
				super.onError(message);
			}
		});
	}
	
	
	private void setArtistDTO(Integer id){
		ArtistDao.getInstance().search(id, Artist.STORE_NAME, database, new DatabaseCursorCallback<Integer, Artist>()
		{
			@Override
			public void onSuccess(Cursor<Integer, Artist> result)
			{
				if ((result != null) && result.hasValue())
				{
					Artist entity = result.getValue();
					artist = entity.getDTORepresentation();
					result.continueCursor();
				}else
				{
					setDoneArtist(true);
				}
			}
		});
	}
	
	private void setCountryDTO(){
		CountryDao.getInstance().search(Country.STORE_NAME, database, new DatabaseCursorCallback<Integer, Country>()
				{
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
							setDoneCountry(true);
						}
					}
				});
	}
	
	private void setGenreDTO(){
		GenreDao.getInstance().search(Genre.STORE_NAME, database, new DatabaseCursorCallback<Integer, Genre>()
		{

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
					setDoneGenre(true);
				}
			}
		});
	}

	/**
	 * @return the country
	 */
	public List<CountryDTO> getCountry()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(List<CountryDTO> country)
	{
		this.country = country;
	}

	/**
	 * @return the genre
	 */
	public List<GenreDTO> getGenre()
	{
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(List<GenreDTO> genre)
	{
		this.genre = genre;
	}

	/**
	 * @return the artist
	 */
	public ArtistDTO getArtist()
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(ArtistDTO artist)
	{
		this.artist = artist;
	}

	/**
	 * @return the doneCountry
	 */
	public boolean isDoneCountry()
	{
		return doneCountry;
	}

	/**
	 * @param doneCountry the doneCountry to set
	 */
	public void setDoneCountry(boolean doneCountry)
	{
		this.doneCountry = doneCountry;
	}

	/**
	 * @return the doneGenre
	 */
	public boolean isDoneGenre()
	{
		return doneGenre;
	}

	/**
	 * @param doneGenre the doneGenre to set
	 */
	public void setDoneGenre(boolean doneGenre)
	{
		this.doneGenre = doneGenre;
	}

	/**
	 * @return the doneArtist
	 */
	public boolean isDoneArtist()
	{
		return doneArtist;
	}

	/**
	 * @param doneArtist the doneArtist to set
	 */
	public void setDoneArtist(boolean doneArtist)
	{
		this.doneArtist = doneArtist;
	}

}
