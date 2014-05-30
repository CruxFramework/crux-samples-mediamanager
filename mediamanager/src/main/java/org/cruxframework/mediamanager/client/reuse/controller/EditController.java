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
package org.cruxframework.mediamanager.client.reuse.controller;

import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.screen.views.BindableView;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.screen.views.ViewActivateEvent;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.animation.DialogAnimation;
import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.client.reuse.service.RestServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

/**
 * Class description:
 * @author alexandre.costa
 */
public abstract class EditController<T extends AbstractDTO> extends AbstractController 
implements EditControllerInterface
{
	private static final String DEFAULT_TITLE = "Cancel?";
	private static final String DEFAULT_MESSAGE = "Deseja cencelar a edição desse registro?";
	private static final String DEFAULT_SUCCESS_MESSAGE = "Successfully saved!";
	private static final String DEFAULT_FILL_FIELDS_MESSAGE = "Fill all fields.";

	/**
	 * Insert a new register on persistence mechanism using REST service proxy.
	 */
	@Expose
	public void insert()
	{
		final BindableView<T> view = View.of(this);
		T dto = view.getData();
		fillDTO(view, dto);
		
		if (!validate(dto))
		{
			MessageBox.show(null, DEFAULT_FILL_FIELDS_MESSAGE, MessageType.ERROR, true,
					false, true, true,"faces-MessageBox", DialogAnimation.fadeDownUp);
		} else
		{
			//getRestServiceProxy().insert(dto, new InsertCallback(view));
			getArtistProxy().insert(this, dto);
		}
	}
	
	/**
	 * Update a register on persistence mechanism using REST service proxy.
	 */
	@Expose
	public void update()
	{
		final BindableView<T> view = View.of(this);
		T dto = view.getData();
		fillDTO(view, dto);
		
		if (!validate(dto))
		{
			MessageBox.show(null, DEFAULT_FILL_FIELDS_MESSAGE, MessageType.ERROR, true,
					false, true, true,"faces-MessageBox", DialogAnimation.fadeDownUp);
		} else
		{
			//getRestServiceProxy().update(dto.getId(), dto, new UpdateCallback());
			getArtistProxy().update(this, dto.getId(), dto);
		}
	}
	
	/******************************************
	 * Utilities
	 ******************************************/
	
	/**
	 * Load most recent entity from persistence mechanism for updating. 
	 * @param id entity id
	 * @param view view
	 */
	protected void loadEntity(final Integer id, final BindableView<T> view)
	{
		getRestServiceProxy().get(id, new LoadEntityCallback(view));
	}
	
	/**
	 * Adjust view state for updating or inserting.
	 * @param event view activate event
	 */
	@SuppressWarnings("unchecked")
	protected void adjustViewState(ViewActivateEvent event)
	{
		T dto = (T) event.getParameterObject();
		Integer id = dto == null ? null : dto.getId();
		final BindableView<T> view = View.of(this);

		if (id != null)
		{
			loadEntity(id, view);
			editState(view);
		} else
		{
			T newDTO = getNewInstance();
			view.setData(newDTO);
			insertState(view);
		}
	}
	
	@Override
	public void completeInsert(EditOperation result)
	{
		BindableView<ArtistDTO> view = View.of(this);
		Integer id = result.getId();
		ArtistDTO dto = view.getData();
		dto.setId(id);
		editState(view);
		MessageBox.show(null, "Successfully saved!", MessageType.SUCCESS, true,
				false, true, true,"faces-MessageBox", DialogAnimation.fadeDownUp);
	}
	
	
	
	/******************************************
	 * Abstract or empty methods
	 ******************************************/
	
	/**
	 * Create a new DTO instance for inserting. 
	 * @return new instance
	 */
	protected abstract T getNewInstance();
	
	/**
	 * Get the service proxy.
	 * @return current proxy
	 */
	protected abstract RestServiceProxy<T> getRestServiceProxy();
	
	protected abstract ArtistProxy<T> getArtistProxy();


	/**
	 * Perform DTO client validation
	 * @param dto data transfer object
	 * @return true if DTO is valid, false otherwise.
	 */
	protected boolean validate(T dto)
	{
		return true;
	}
	
	/**
	 * Fill DTO with view informations.
	 * @param view editing view
	 * @param dto DTO to be filled.
	 */
	protected void fillDTO(BindableView<T> view, T dto)
	{
		
	}
	
	/*********************************************
	 * Callback classes
	 *********************************************/
	
	private class LoadEntityCallback extends CallbackAdapter<T>
	{
		private final BindableView<T> view;
		
		public LoadEntityCallback(BindableView<T> view)
		{
			this.view = view;
		}
		
		@Override
		public void onComplete(T result)
		{
			view.setData(result);
		}
	}
	
//	private class InsertCallback extends CallbackAdapter<EditOperation>
//	{
//		private final BindableView<T> view;
//		
//		public InsertCallback(BindableView<T> view)
//		{
//			this.view = view;
//		}
//		
//		@Override
//		public void onComplete(EditOperation result)
//		{
//			Integer id = result.getId();
//			T dto = view.getData();
//			dto.setId(id);
//			editState(view);
//			MessageBox.show(null, DEFAULT_SUCCESS_MESSAGE, MessageType.SUCCESS, true,
//					false, true, true,"faces-MessageBox", DialogAnimation.fadeDownUp);
//		}
//	}
	
//	private class UpdateCallback extends CallbackAdapter<EditOperation>
//	{
//		@Override
//		public void onComplete(EditOperation result)
//		{
//			MessageBox.show(null, DEFAULT_SUCCESS_MESSAGE, MessageType.SUCCESS, true,
//					false, true, true,"faces-MessageBox", DialogAnimation.fadeDownUp);
//		}
//	}
	
	/*********************************************
	 * Confirm dialog messages
	 ********************************************/

	protected String getConfirmDialogTitle()
	{
		return DEFAULT_TITLE;
	}

	protected String getConfirmDialogMessage()
	{
		return DEFAULT_MESSAGE;
	}

	/*********************************************
	 * View behavior 
	 ********************************************/

	protected void editState(View view)
	{

	}

	protected void insertState(View view)
	{

	}
}
