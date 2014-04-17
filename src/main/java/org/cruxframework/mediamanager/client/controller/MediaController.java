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
package org.cruxframework.mediamanager.client.controller;

import java.util.List;

import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.screen.views.BindableView;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.screen.views.ViewActivateEvent;
import org.cruxframework.crux.smartfaces.client.button.Button;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.smartfaces.client.dialog.animation.DialogAnimation;
import org.cruxframework.mediamanager.client.reuse.controller.EditController;
import org.cruxframework.mediamanager.client.reuse.controller.WaitCallbackAdapter;
import org.cruxframework.mediamanager.client.service.EditMediaServiceProxy;
import org.cruxframework.mediamanager.client.service.MediaServiceProxy;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;
import org.cruxframework.mediamanager.shared.dto.EditMediaDTO;
import org.cruxframework.mediamanager.shared.dto.MediaDTO;
import org.cruxframework.mediamanager.shared.enums.MediaType;

import com.google.gwt.user.client.ui.ListBox;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("mediaController")
public class MediaController extends EditController<MediaDTO>
{	
	@Inject
	public MediaServiceProxy restServiceProxy;
	
	@Inject
	public EditMediaServiceProxy editMediaServiceProxy;
	
	@Expose
	public void onActivate(ViewActivateEvent event)
	{
		animateContent();
		WaitBox.show("Wait", DialogAnimation.fadeDownUp);
		BindableView<MediaDTO> view = View.of(this);
		MediaDTO media = event.getParameterObject();
		Integer identificator = media == null ? null : media.getId();
		fillMediaTypeListBox((ListBox) view.getWidget("typeListBox"), media);
		editMediaServiceProxy.get(identificator, new EditMediaCallback());
	}
	
	/********************************************
	 * UI control methods
	 ********************************************/
	
	private static void fillMediaTypeListBox(ListBox typesListBox, 
		MediaDTO mediaDTO)
	{
		if (typesListBox.getItemCount() == 0)
		{
			typesListBox.addItem("", "");
			typesListBox.setSelectedIndex(0);

			for (MediaType type : MediaType.values())
			{
				typesListBox.addItem(type.name(), type.name());
			}
		}
		
		if (mediaDTO == null)
		{
			typesListBox.setSelectedIndex(0);
		} else
		{
			for (int i = 0; i < typesListBox.getItemCount(); i++)
			{
				if (typesListBox.getValue(i).equals(mediaDTO.getType().name()))
				{
					typesListBox.setSelectedIndex(i);
					return;
				}
			}
		}
	}
	
	private static void fillArtistListBox(ListBox artistListBox, 
		List<ArtistDTO> artistList, MediaDTO mediaDTO)
	{
		artistListBox.clear();
		artistListBox.addItem("", "");
		artistListBox.setSelectedIndex(0);
		for (int i = 0; i < artistList.size(); i++)
		{
			ArtistDTO artist = artistList.get(i);
			artistListBox.addItem(artist.getName(), artist.getId().toString());
			
			if (mediaDTO != null && mediaDTO.getArtist().getId() == artist.getId())
			{
				artistListBox.setSelectedIndex(i + 1);
			}
		}
	}
	
	/********************************************
	 * View state settings
	 ********************************************/
	
	@Override
	protected void editState(View view)
	{
		Button insertButton = (Button)view.getWidget("insertButton");
		Button updateButton = (Button) view.getWidget("updateButton");
		insertButton.setEnabled(false);
		updateButton.setEnabled(true);
	}
	
	@Override
	protected void insertState(View view)
	{
		Button insertButton = (Button)view.getWidget("insertButton");
		Button updateButton = (Button) view.getWidget("updateButton");
		insertButton.setEnabled(true);
		updateButton.setEnabled(false);
	}
	
	@Override
	protected boolean validate(MediaDTO mediaDTO)
	{
		return mediaDTO.getName() != null 
			&& mediaDTO.getName().trim().length() > 0
			&& mediaDTO.getArtist().getId() != null
			&& mediaDTO.getType() != null;
	}
	
	/****************************************
	 * Callback classes
	 *****************************************/
	
	private class EditMediaCallback extends WaitCallbackAdapter<EditMediaDTO>
	{
		@Override
		public void success(EditMediaDTO result)
		{
			BindableView<MediaDTO> view = View.of(MediaController.this);
			MediaDTO media = result.getMedia();
			Integer identificator = media == null ? null : media.getId();
			
			/* Adjust artistsViewviewAcessor state */
			if (identificator != null)
			{
				editState(view);
				view.setData(result.getMedia());
			} else
			{
				insertState(view);
				view.setData(getNewInstance());
			}
			
			fillArtistListBox((ListBox) view.getWidget("artistListBox"), 
				result.getArtists(), media);
		}
	}
	
	/********************************************
	 * Overwritten methods
	 ********************************************/
	
	@Override
	protected void fillDTO(BindableView<MediaDTO> view, MediaDTO dto)
	{
		/* Get media type */
		ListBox typeListBox = (ListBox) view.getWidget("typeListBox");
		
		try
		{
			MediaType type = MediaType.valueOf(
				typeListBox.getValue(typeListBox.getSelectedIndex()));
			
			dto.setType(type);
		} catch (Exception e)
		{
			dto.setType(null);
		}
		
		/* Get artist id */
		ListBox artistListBox = (ListBox) view.getWidget("artistListBox");
		ArtistDTO artistDTO = new ArtistDTO();
		
		try
		{
			artistDTO.setId(Integer.parseInt(
				artistListBox.getValue(artistListBox.getSelectedIndex())));
			
		} catch (NumberFormatException e)
		{
			artistDTO.setId(null);
		}
		
		dto.setArtist(artistDTO);
	}
	
	@Override
	protected MediaServiceProxy getRestServiceProxy()
	{
		return restServiceProxy;
	}

	@Override
	protected MediaDTO getNewInstance()
	{
		return new MediaDTO();
	}
}
