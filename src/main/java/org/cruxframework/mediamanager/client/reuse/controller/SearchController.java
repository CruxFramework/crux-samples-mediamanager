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

import java.util.List;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.event.OkEvent;
import org.cruxframework.crux.core.client.event.OkHandler;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.smartfaces.client.dialog.Confirm;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid;
import org.cruxframework.crux.widgets.client.event.SelectEvent;
import org.cruxframework.crux.widgets.client.grid.DataRow;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.reuse.service.RestServiceProxy;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

import com.google.gwt.user.client.ui.Widget;

/**
 * Class description: Abstract superclass for search controllers.
 * 
 * @param <T> DTO type class
 * @see AbstractDTO
 * @author alexandre.costa
 */
public abstract class SearchController<T extends AbstractDTO> extends AbstractController
{
	private static final String VIEWS = "views";

	private static final String DEFAULT_DELETE_WINDOW_TITLE = "Delete?";

	private static final String DEFAULT_DELETE_WINDOW_MESSAGE = "Are you sure you want to delete this record?";

	private static final String DEFAULT_DELETED_SUCCESS_MESSAGE = "Record successfully deleted!";

	/**
	 * Prepares view for insert operation.
	 */
	@Expose
	public void prepareForInsert()
	{
		SimpleViewContainer views = (SimpleViewContainer) Screen.get(VIEWS);
		views.showView(getEditViewOutcome());
	}

	/**
	 * Prepares view for update operation.
	 * 
	 * @param selectEvent event
	 */
	@Expose
	@SuppressWarnings("unchecked")
	public void prepareForUpdate(SelectEvent selectEvent)
	{
		DataRow row = getResultGrid().getRow((Widget) selectEvent.getSource());
		final T DTO = (T) row.getBoundObject();
		SimpleViewContainer views = (SimpleViewContainer) Screen.get(VIEWS);
		views.showView(getEditViewOutcome(), getEditViewOutcome(), DTO);
	}

	/**
	 * Removes a selected resource.
	 * 
	 * @param selectEvent event
	 */
	@Expose
	@SuppressWarnings("unchecked")
	public void delete(SelectEvent selectEvent)
	{
		DataRow row = getResultGrid().getRow((Widget) selectEvent.getSource());
		final T DTO = (T) row.getBoundObject();
		Confirm.show(getConfirmDialogTitle(), getConfirmDialogMessage(), new OkHandler()
		{
			@Override
			public void onOk(OkEvent event)
			{
				WaitBox.show("Wait");
				getRestServiceProxy().delete(DTO.getId(), DTO, new DeleteCallback(DTO));
			}
		}, null);
	}

	/*****************************************
	 * Callback classes
	 *****************************************/

	/**
	 * Class description: Callback class for handling delete operation result.
	 * 
	 * @author alexandre.costa
	 */
	private class DeleteCallback implements Callback<EditOperation>
	{
		private final T dto;

		public DeleteCallback(T dto)
		{
			this.dto = dto;
		}

		@Override
		@SuppressWarnings("unchecked")
		public void onSuccess(EditOperation result)
		{
			if (result.getType() == null)
			{
				AbstractDTODatasource<T> datasource = (AbstractDTODatasource<T>) getResultGrid().getDataSource();

				datasource.remove(dto);
				getResultGrid().clear();
				getResultGrid().loadData();
				getResultGrid().refresh();
				WaitBox.hideAllDialogs();
				MessageBox.show(DEFAULT_DELETED_SUCCESS_MESSAGE, MessageType.SUCCESS);
			}
			else
			{
				WaitBox.hideAllDialogs();
				MessageBox.show(getMessageForDeleteValidationError(), MessageType.ERROR);
			}
		}

		@Override
		public void onError(Exception e)
		{
			WaitBox.hideAllDialogs();
			Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Class description: Callback class for handling search results.
	 * 
	 * @author alexandre.costa
	 */
	@SuppressWarnings("unchecked")
	public class SearchCallback implements Callback<List<T>>
	{
		@Override
		public void onSuccess(List<T> result)
		{
			if (result != null && result.size() > 0)
			{
				AbstractDTODatasource<T> datasource = (AbstractDTODatasource<T>) getResultGrid().getDataSource();

				getResultGrid().clear();
				datasource.clear();
				datasource.addValues(result);
				getResultGrid().loadData();
				getResultGrid().refresh();
				WaitBox.hideAllDialogs();
			}
			else
			{
				getResultGrid().clear();
				getResultGrid().refresh();
				WaitBox.hideAllDialogs();
				MessageBox.show("No results found.", MessageType.INFO);
			}
		};

		@Override
		public void onError(Exception e)
		{
			WaitBox.hideAllDialogs();
			Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
		}
	}

	/******************************************
	 * Abstract methods
	 ******************************************/

	protected abstract String getEditViewOutcome();

	protected abstract DeviceAdaptiveGrid getResultGrid();

	protected abstract RestServiceProxy<T> getRestServiceProxy();

	/*******************************************
	 * Messages
	 *******************************************/

	protected String getMessageForDeleteValidationError()
	{
		return "Error";
	}

	protected String getConfirmDialogTitle()
	{
		return DEFAULT_DELETE_WINDOW_TITLE;
	}

	protected String getConfirmDialogMessage()
	{
		return DEFAULT_DELETE_WINDOW_MESSAGE;
	}
}
