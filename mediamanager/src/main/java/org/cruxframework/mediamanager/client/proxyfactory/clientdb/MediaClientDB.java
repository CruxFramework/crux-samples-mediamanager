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
package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.mediamanager.client.controller.MediaController;
import org.cruxframework.mediamanager.client.controller.MediasController;
import org.cruxframework.mediamanager.client.proxy.MediaProxy;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.EditMediaDTO;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.MediaDao;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Media;
import org.cruxframework.mediamanager.offline.client.reuse.Utils;

import com.google.gwt.user.client.Window;


/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class MediaClientDB extends ServiceClientDB implements MediaProxy
{
	
	private static final ArtistComparator ARTIST_COMPARATOR = new ArtistComparator();
	
	/***********************************************************
	 * Insert
	 ***********************************************************/
	
	@Override
	public void insert(final MediaDTO dto, AbstractController abstractController)
	{
		final MediaController controller = (MediaController) abstractController;
		if (!getDatabase().isOpen()) {
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					getMedia(dto, controller);
				}
			});
		} 
		else
		{
			getMedia(dto, controller);
		}

	}
	
	private void getMedia(final MediaDTO dto, final MediaController controller)
	{
		final Media media;
		media = new Media();
		media.setNameMedia(dto.getName());
		media.setId(dto.getId());
		media.setBorrowed(dto.getBorrowed() == true ? 1 : 0);
		media.setDate(dto.getDate());
		media.setPerson(dto.getPerson());
		media.setType(dto.getType());

		ArtistDao.getInstance(getDatabase()).search(dto.getArtist().getId(),
			new DatabaseRetrieveCallback<Artist>()
			{
				@Override
				public void onSuccess(Artist result)
				{
					media.setArtist(result);
					saveInsert(media, controller);
				}
			});
	}
	
	

	private void saveInsert(final Media media, final MediaController controller)
	{
		MediaDao.getInstance(getDatabase()).save(media, new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				searchId(media, controller);				
			}
		});
	}
	
	/**
	 * Recover the "id" inserted into a database artist.
	 */
	private void searchId(final Media media, final MediaController controller)
	{
		MediaDao.getInstance(getDatabase()).search(media.getNameMedia(), "nameMedia",
			new DatabaseRetrieveCallback<Media>()
			{
				@Override
				public void onSuccess(Media result)
				{
					EditOperation edit = new EditOperation();
					edit.setId(result.getId());
					controller.completeInsert(edit);
				}
				@Override
				public void onError(String message)
				{
					Window.alert("blaa");
					super.onError(message);
				}
			});
	}
	
	/*****************************************
	 * Search
	 *****************************************/
	@Override
	public void search(final MediaType type, final String name, final String person,
		final MediasController controller)
	{
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					doSearch(type, name, person, controller);
				}
			});
		}
		else
		{
			doSearch(type, name, person, controller);
		}
	}
	
	private void doSearch(final MediaType type, final String name,
		final String person, final MediasController controller)
	{
		if (name.equals("") && person.equals("") && type == null)
		{
			search(controller);
		}
		else if (!name.equals("") && person.equals("") && type == null)
		{
			search(name, "nameMedia", controller);
		}
		else if (name.equals("") && !person.equals("") && type == null)
		{
			// TODO verificar a perquisa por person
			search(person, "person", controller);
		}
		else if (name.equals("") && person.equals("") && type != null)
		{
			searchOnly(type.name(), "type", controller);
		}
		else if (!name.equals("") && person.equals("") && type != null)
		{
			Object[] obj = new Object[] { new String(name), new String(type.name())};
			Object[] objBound = new Object[] {new String(Utils.StringBound(name)), new String(Utils.StringBound(type.name()))};
			search(obj, objBound, "nameMediaType", controller);
		}
	}

	private void search(final MediasController controller)
	{
		MediaDao.getInstance(getDatabase()).search(
			new DatabaseCursorCallback<Integer, Media>()
			{
				private final ArrayList<MediaDTO> medias = new ArrayList<MediaDTO>();

				@Override
				public void onSuccess(Cursor<Integer, Media> result)
				{
					if ((result != null) && result.hasValue())
					{
						Media media = result.getValue();
						medias.add(media.getDTORepresentation());
						result.continueCursor();
					}
					else
					{
						controller.showSearchResult(medias);
					}
				}
			});
	}

	private void search(final String value, String index, final MediasController controller)
	{
		MediaDao.getInstance(getDatabase()).searchLike(value, index,
			new DatabaseCursorCallback<String, Media>()
			{
				private final ArrayList<MediaDTO> medias = new ArrayList<MediaDTO>();
				@Override
				public void onSuccess(Cursor<String, Media> result)
				{
					if (result != null && result.hasValue())
					{
						Media media = result.getValue();
						medias.add(media.getDTORepresentation());
						result.continueCursor();
					}
					else
					{
						controller.showSearchResult(medias);
					}
					
				}
			});
	}

	private void searchOnly(final String value, String index, final MediasController controller)
	{
		MediaDao.getInstance(getDatabase()).search(value, index,
			new DatabaseRetrieveCallback<Media>()
			{
				private final ArrayList<MediaDTO> medias = new ArrayList<MediaDTO>();

				@Override
				public void onSuccess(Media result)
				{
					if (result != null)
					{
						medias.add(result.getDTORepresentation());
					}
					controller.showSearchResult(medias);
				}
			});
	}
	
	private void search(final Object[] value, Object[] valueBound, String index, final MediasController controller)
	{
		MediaDao.getInstance(getDatabase()).searchObjectLike(value, valueBound, index, new DatabaseCursorCallback<Object[], Media>()
		{
			
			private final ArrayList<MediaDTO> medias = new ArrayList<MediaDTO>();

			@Override
			public void onSuccess(Cursor<Object[], Media> result)
			{
				if (result != null && result.hasValue())
				{
					Media m = result.getValue();
					medias.add(m.getDTORepresentation());
					result.continueCursor();
				}
				else
				{
					controller.showSearchResult(medias);
				}
			}
		});
	}

	
/***************************************************************
 * GET
 ******************************************************************/
	@Override
	public void get(final Integer identificator, AbstractController abstractController)
	{
		final MediaController controller = (MediaController) abstractController;
		final EditMediaDTO editMediaDTO = new EditMediaDTO();
		
		if (!getDatabase().isOpen())
		{
			
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					doGet(identificator, editMediaDTO ,controller);
				}
			});
			
		}
		else
		{
			doGet(identificator, editMediaDTO , controller);
		}
	}

	private void doGet(Integer identificator, EditMediaDTO editMediaDTO, final MediaController controller)
	{
		if (identificator != null)
		{
			setMedia(identificator, editMediaDTO , controller);
		}
		else
		{
			setArtist(editMediaDTO, controller);
		}
	}
	
	
	
	private void setMedia(Integer identificador, final EditMediaDTO editMediaDTO, final MediaController controller)
	{
		MediaDao.getInstance(getDatabase()).search(identificador, new DatabaseRetrieveCallback<Media>()
		{
			
			@Override
			public void onSuccess(Media result)
			{
				editMediaDTO.setMedia(result.getDTORepresentation());
				setArtist(editMediaDTO, controller);
			}
		});
	}

	private void setArtist(final EditMediaDTO editMediaDTO, final MediaController controller)
	{
		ArtistDao.getInstance(getDatabase()).search(new DatabaseCursorCallback<Integer, Artist>()
		{
			List<ArtistDTO> artists = new ArrayList<ArtistDTO>();
			@Override
			public void onSuccess(Cursor<Integer, Artist> result)
			{
				if (result != null && result.hasValue())
				{
					Artist artist = result.getValue();
					artists.add(artist.getDTORepresentation());
					result.continueCursor();
				}
				else
				{
					// Sort List by Name
					Collections.sort(artists, ARTIST_COMPARATOR);
					editMediaDTO.setArtists(artists);
					controller.editableState(editMediaDTO);
				}
			}
		});
		
	}
	
	/*****************************************
	 * Delete
	 *****************************************/
	@Override
	public void delete(final MediaDTO dto, final MediasController controller)
	{
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{

				@Override
				public void onSuccess()
				{
					doDelete(dto, controller);
				}
			});
		}
		else
		{
			doDelete(dto, controller);
		}
	}
	
	public void doDelete(final MediaDTO dto, final MediasController controller) 
	{
		MediaDao.getInstance(getDatabase()).search(dto.getName(), "nameMedia", new DatabaseRetrieveCallback<Media>()
			{
				@Override
				public void onSuccess(Media result)
				{
					if (result != null)
					{
						MediaDao.getInstance(getDatabase()).delete(result.getId(), new DatabaseCallback()
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
	
	/***********************************************************
	 * Update
	 ***********************************************************/

	@Override
	public void update(final Integer id, final MediaDTO dto, AbstractController abstractController)
	{
		final MediaController controller = (MediaController) abstractController;		
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					doneUpdate(id, dto, controller);
				}
			});
		}
		else
		{
			doneUpdate(id, dto, controller);
		}
	}
	
	private void doneUpdate(final Integer id, MediaDTO dto, final MediaController controller)
	{
		final Media media;
		media = new Media();
		media.setNameMedia(dto.getName());
		media.setId(dto.getId());
		media.setBorrowed(dto.getBorrowed() == true ? 1 : 0 );
		media.setDate(dto.getDate());
		media.setPerson(dto.getPerson());
		media.setType(dto.getType());

		ArtistDao.getInstance(getDatabase()).search(dto.getArtist().getId(),
			new DatabaseRetrieveCallback<Artist>()
			{
				@Override
				public void onSuccess(Artist result)
				{
						media.setArtist(result);
						save(media, controller);
				}
			});
	}
	
	private void save(Media media, final MediaController controller)
	{
		MediaDao.getInstance(getDatabase()).save(media, new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				controller.completeUpdate();
			}
		});
	}

	
	
	/***********************************************************
	 * Lend
	 ***********************************************************/

	@Override
	public void lend(final Integer id, final MediaDTO dto, final MediasController controller)
	{
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					doneLend(id, dto, controller);
				}
			});
		}
		else
		{
			doneLend(id, dto, controller);
		}
	}
	
	private void doneLend(final Integer id, final MediaDTO dto, final MediasController controller)
	{
		final Media media;
		media = new Media();
		media.setNameMedia(dto.getName());
		media.setId(dto.getId());
		media.setBorrowed(dto.getBorrowed() == true ? 1 : 0 );
		media.setDate(dto.getDate());
		media.setPerson(dto.getPerson());
		media.setType(dto.getType());

		ArtistDao.getInstance(getDatabase()).search(dto.getArtist().getId(),
			new DatabaseRetrieveCallback<Artist>()
			{
				@Override
				public void onSuccess(Artist result)
				{
						media.setArtist(result);
						saveLend(media, dto, controller);
				}
			});
	}
	
	private void saveLend(Media media, final MediaDTO dto, final MediasController controller)
	{
		MediaDao.getInstance(getDatabase()).save(media, new DatabaseCallback()
		{
			@Override
			public void onSuccess()
			{
				EditOperation editoperation = null;
				controller.saveLendState(dto, editoperation);;
			}
		});
	}
	
	
	/***************************************************************
	 * GetLend
	 ******************************************************************/
		@Override
		public void getLend(final Integer identificator, final MediasController controller)
		{
			if (!getDatabase().isOpen())
			{
				
				getDatabase().open(new DatabaseCallback()
				{
					@Override
					public void onSuccess()
					{
						setMedia(identificator, controller);
					}
				});
				
			}
			else
			{
				setMedia(identificator, controller);
			}
		}
		
		private void setMedia(Integer identificador, final MediasController controller)
		{
			MediaDao.getInstance(getDatabase()).search(identificador, new DatabaseRetrieveCallback<Media>()
			{
				
				@Override
				public void onSuccess(Media result)
				{
					MediaDTO mediaDTO = new MediaDTO();
					mediaDTO = result.getDTORepresentation();
					controller.getLendState(mediaDTO);
				}
			});
		}

	
	/***********************************************************
	 * Util
	 ***********************************************************/
	private static class ArtistComparator implements Comparator<ArtistDTO>
	{
		@Override
		public int compare(ArtistDTO o1, ArtistDTO o2)
		{
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	}
}
