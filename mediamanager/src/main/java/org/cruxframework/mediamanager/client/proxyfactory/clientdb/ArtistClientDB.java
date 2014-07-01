package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.mediamanager.client.controller.ArtistController;
import org.cruxframework.mediamanager.client.controller.ArtistsController;
import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.CountryDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.GenreDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.MediaDao;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;
import org.cruxframework.mediamanager.offline.client.entity.Media;

import com.google.gwt.user.client.Window;

/**
 * Class Description: This class has two main methods insert and update.
 * Basically
 * insert and update one Artist in the database on the client side.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class ArtistClientDB extends ServiceClientDB implements ArtistProxy
{

	/*****************************************
	 * Insert
	 *****************************************/
	@Override
	public void insert(final ArtistDTO dto, AbstractController abstractController)
	{
		final ArtistController controller = (ArtistController) abstractController;
		
		if (!getDatabase().isOpen())
		{
			// Open database
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					// Save Artist
					saveInsert(dto, controller);
				}
			});
		}
		else
		{
			saveInsert(dto, controller);
		}
	}

	private void saveInsert(ArtistDTO dto, final ArtistController controller)
	{
		final Artist artist = getArtist(dto, null);
		
		// Search Country
		CountryDao.getInstance(getDatabase()).search(artist.getCountry().getId(),
		new DatabaseRetrieveCallback<Country>()
		{
						
			@Override
			public void onSuccess(Country result)
			{
			artist.getCountry().setNameCountry(result.getNameCountry());
			// Search Genre
			GenreDao.getInstance(getDatabase()).search(artist.getGenre().getId(),
				new DatabaseRetrieveCallback<Genre>()
				{
					
					@Override
					public void onSuccess(Genre result)
					{
						artist.getGenre().setNameGenre(result.getNameGenre());
						// Save Artist
						ArtistDao.getInstance(getDatabase()).save(artist,
							new DatabaseCallback()
							{
								@Override
								public void onSuccess()
								{
									// This method is required to recover the "id" inserted into a database
									// artist.
									searchId(artist, controller);
								}
							});
					}
					@Override
					public void onError(String message)
					{
						Window.alert("Genre not found in the database !");
						super.onError(message);
					}
				});
			}
			@Override
			public void onError(String message)
			{
				Window.alert("Country not found in the database !");
				super.onError(message);
			}
		});
	}
	
	/**
	 * Recover the "id" inserted into a database artist.
	 */
	private void searchId(final Artist artist, final ArtistController controller)
	{
		ArtistDao.getInstance(getDatabase()).search(artist.getNameArtist(), "nameArtist",
			new DatabaseRetrieveCallback<Artist>()
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

	
	
	/*****************************************
	 * Get
	 *****************************************/
	@Override
	public void get(final Integer id, AbstractController abstractController)
	{
		final ArtistController controller = (ArtistController) abstractController;
		final EditArtistDTO dto = new EditArtistDTO();
		
		getDatabase().open(new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				if (id != null)
				{
					setArtistDTO(id, dto, controller);
				} 
				else 
				{
					setCountryDTO(dto, controller);
				}
			}
		});
	}
	
	private void setArtistDTO(final Integer id, final EditArtistDTO dto, final ArtistController controller)
	{
		ArtistDao.getInstance(getDatabase()).search(id, new DatabaseRetrieveCallback<Artist>()
		{
			
			@Override
			public void onSuccess(Artist result)
			{
				dto.setArtist(result.getDTORepresentation());
				setCountryDTO(dto, controller);
			}
		}); 
	}
	
	private void setCountryDTO(final EditArtistDTO dto, final ArtistController controller)
	{
		CountryDao.getInstance(getDatabase()).search(new DatabaseCursorCallback<Integer, Country>()
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
				}
				else
				{
					// Sort List by Name
					CountryComparator comparator = new CountryComparator();
					Collections.sort(country, comparator);
					dto.setCountries(country);
					setGenreDTO(dto, controller);
				}
			}
		});
	}
	
	private void setGenreDTO(final EditArtistDTO dto, final ArtistController controller)
	{
		GenreDao.getInstance(getDatabase()).search(new DatabaseCursorCallback<Integer, Genre>()
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
				} else
				{
					// Sort List by Name
					GenreComparator comparator = new GenreComparator();
					Collections.sort(genre, comparator);
					dto.setGenres(genre);
					//Send the return to Controller
					controller.editableState(dto);
				}
			}
		});
	}

	/*****************************************
	 * Update
	 *****************************************/
	@Override
	public void update(final Integer id, final ArtistDTO dto, AbstractController abstractController)
	{
		final ArtistController controller = (ArtistController) abstractController;
 		if (!getDatabase().isOpen())
		{
			// Open database
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					// Save Artist
					saveUpdate(id, dto, controller);
				}
			});
		}
		else
		{
			// Save Artist
			saveUpdate(id, dto, controller);
		}
	}

	private void saveUpdate(final Integer id, final ArtistDTO dto, final ArtistController controller)
	{
		final Artist artist = getArtist(dto, id);
		
		// Search Country
		CountryDao.getInstance(getDatabase()).search(artist.getCountry().getId(),
			new DatabaseRetrieveCallback<Country>()
			{
				
				@Override
				public void onSuccess(Country result)
				{
					artist.getCountry().setNameCountry(result.getNameCountry());
					// Search Genre
					GenreDao.getInstance(getDatabase()).search(artist.getGenre().getId(),
						new DatabaseRetrieveCallback<Genre>()
						{
							
							@Override
							public void onSuccess(Genre result)
							{
								artist.getGenre().setNameGenre(result.getNameGenre());
								// Save Artist
								ArtistDao.getInstance(getDatabase()).save(artist,
									new DatabaseCallback()
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
								Window.alert("Genre not found in the database !");
								super.onError(message);
							}
						});
					
				}
				@Override
				public void onError(String message)
				{
					Window.alert("Country not found in the database !");
					super.onError(message);
				}
			});
	}
	
	
	/*****************************************
	 * Delete 
	 *****************************************/
	
	@Override
	public void delete(final ArtistDTO dto, final ArtistsController controller)
	{
		MediaDao.getInstance(getDatabase()).searchLike(dto.getId(),
			new DatabaseCursorCallback<Integer, Media>()
			{

				@Override
				public void onSuccess(Cursor<Integer, Media> result)
				{
					if (result != null)
					{
						WaitBox.hideAllDialogs();
						MessageBox.show(controller.getMessageForDeleteValidationError(),
							MessageType.ERROR);
					}
					else
					{
						ArtistDao.getInstance(getDatabase()).delete(dto.getId(),
							new DatabaseCallback()
							{

								@Override
								public void onSuccess()
								{
									controller.showDeleteResult(dto);
								}
							});
					}
				}
			});
	}

	
	/*****************************************
	 * Search
	 *****************************************/
	@Override
	public void search(final String name, final ArtistsController controller)
	{
		
		if (!getDatabase().isOpen())
		{
				this.getDatabase().open(new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						if (name.equals(""))
						{
							doSearch(name, controller);
						}
						else
						{
							doSearch(name, "nameArtist", controller);
						}
					}
				});
		}
		else
		{
			if (name.equals(""))
			{
				doSearch(name, controller);
			}
			else
			{
				doSearch(name, "nameArtist", controller);
			}
		}
	}

	private void doSearch(final String name, final ArtistsController controller)
	{
		ArtistDao.getInstance(getDatabase()).search(
			new DatabaseCursorCallback<Integer, Artist>()
			{
				private final List<ArtistDTO> artists = new ArrayList<ArtistDTO>();

				@Override
				public void onSuccess(Cursor<Integer, Artist> result)
				{
					if ((result != null) && result.hasValue())
					{
						Artist artist = result.getValue();
						artists.add(artist.getDTORepresentation());
						result.continueCursor();
					}
					else
					{
						controller.showSearchResult(artists);
					}
				}
			});
	}
	
	
	private void doSearch(final String name, String index, final ArtistsController controller)
	{
		ArtistDao.getInstance(getDatabase()).searchLike(name, index, new DatabaseCursorCallback<String, Artist>()
		{
			private final List<ArtistDTO> artists = new ArrayList<ArtistDTO>();
			@Override
			public void onSuccess(Cursor<String, Artist> result)
			{
				if ((result != null) && result.hasValue())
				{
					Artist artist = result.getValue();
					artists.add(artist.getDTORepresentation());
					result.continueCursor();
				}
				else
				{
					controller.showSearchResult(artists);
				}
			}
		});
	}

	/******************
	 * utils
	 *******************/
	private Artist getArtist(ArtistDTO dto, Integer id)
	{
		Artist artist = new Artist();
		artist = new Artist();
		// Name
		artist.setNameArtist(dto.getName());
		// Country
		Country country = new Country();
		country.setId(dto.getCountry().getId());
		artist.setCountry(country);
		// Genre
		Genre genre = new Genre();
		genre.setId(dto.getGenre().getId());
		artist.setGenre(genre);
		// id
		artist.setId(id);
		return artist;
	}
	
	private class GenreComparator implements Comparator<GenreDTO>
	{
		@Override
		public int compare(GenreDTO o1, GenreDTO o2)
		{
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	}
	
	private class CountryComparator implements Comparator<CountryDTO>
	{
		@Override
		public int compare(CountryDTO o1, CountryDTO o2)
		{
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	}

}
