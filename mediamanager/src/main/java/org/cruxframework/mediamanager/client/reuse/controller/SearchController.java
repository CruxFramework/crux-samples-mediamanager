package org.cruxframework.mediamanager.client.reuse.controller;

import java.util.List;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid;
import org.cruxframework.crux.widgets.client.event.SelectEvent;
import org.cruxframework.crux.widgets.client.grid.DataRow;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.reuse.service.RestServiceProxy;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

import com.google.gwt.user.client.ui.Widget;

/**
 * Class description: Abstract superclass for search controllers
 * @param <T> DTO type class
 * @see AbstractDTO
 * @author alexandre.costa
 */
public abstract class SearchController<T extends AbstractDTO> 
	extends AbstractController
{
	private static final String DEFAULT_DELETE_WINDOW_TITLE = "Delete?";
	
	private static final String DEFAULT_DELETE_WINDOW_MESSAGE = 
		"Are you sure you want to delete this record?";
	
	private static final String DEFAULT_DELETED_SUCCESS_MESSAGE = 
		"Record successfully deleted!";
	
	@Expose
	public void prepareForInsert()
	{
		SimpleViewContainer views = (SimpleViewContainer) Screen.get("views");
		views.showView(getEditViewOutcome());
	}
	
	@Expose
	@SuppressWarnings("unchecked")
	public void prepareForUpdate(SelectEvent selectEvent)
	{
		DataRow row = getResultGrid().getRow((Widget) selectEvent.getSource());
		final T dto = (T) row.getBoundObject();
		SimpleViewContainer views = (SimpleViewContainer) Screen.get("views");
		views.showView(getEditViewOutcome(), getEditViewOutcome(), dto);
	}
	
	@SuppressWarnings("unchecked")
	public void showSearchResult(List<T> result)
	{
		if (result != null && result.size() > 0)
		{
			AbstractDTODatasource<T> datasource = 
				(AbstractDTODatasource<T>) getResultGrid().getDataSource();
			
			getResultGrid().clear();
			datasource.clear();
			datasource.addValues(result);
			getResultGrid().loadData();
			getResultGrid().refresh();
			WaitBox.hideAllDialogs();
		} else
		{
			getResultGrid().clear();
			getResultGrid().refresh();
			WaitBox.hideAllDialogs();
			MessageBox.show("No results found.", MessageType.INFO);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void showDeleteResult(T dto)
	{
		try
		{
			AbstractDTODatasource<T> datasource = 
				(AbstractDTODatasource<T>) getResultGrid().getDataSource();
			datasource.remove(dto);
			getResultGrid().clear();
			getResultGrid().loadData();
			getResultGrid().refresh();
			WaitBox.hideAllDialogs();
			MessageBox.show(DEFAULT_DELETED_SUCCESS_MESSAGE, 
				MessageType.SUCCESS);
		} 
		catch (Exception e)
		{
			Crux.getErrorHandler().handleError(e);
			e.printStackTrace();
		}
	}
	
	/*****************************************
	 * Callback classes
	 *****************************************/
	
	/**
	 * Class description: Callback class for handling delete operation result.
	 * @author alexandre.costa
	 */
//	private class DeleteCallback implements Callback<EditOperation>
//	{
//		private final T dto;
//		
//		public DeleteCallback(T dto)
//		{
//			this.dto = dto;
//		}
//		
//		@Override
//		@SuppressWarnings("unchecked")
//		public void onSuccess(EditOperation result)
//		{
//			if (result.getType() == null)
//			{
//				AbstractDTODatasource<T> datasource = 
//					(AbstractDTODatasource<T>) getResultGrid().getDataSource();
//				
//				datasource.remove(dto);
//				getResultGrid().clear();
//				getResultGrid().loadData();
//				getResultGrid().refresh();
//				WaitBox.hideAllDialogs();
//				MessageBox.show(DEFAULT_DELETED_SUCCESS_MESSAGE, 
//					MessageType.SUCCESS);
//			} else
//			{
//				WaitBox.hideAllDialogs();
//				MessageBox.show(getMessageForDeleteValidationError(), 
//					MessageType.ERROR);
//			}
//		}
//
//		@Override
//		public void onError(Exception e)
//		{
//			WaitBox.hideAllDialogs();
//			Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
//		}
//	}
	
	/**
	 * Class description: Callback class for handling search results.
	 * @author alexandre.costa
	 */
//	@SuppressWarnings("unchecked")
//	public class SearchCallback implements Callback<List<T>>
//	{
//		@Override
//		public void onSuccess(List<T> result)
//		{
//			if (result != null && result.size() > 0)
//			{
//				AbstractDTODatasource<T> datasource = 
//					(AbstractDTODatasource<T>) getResultGrid().getDataSource();
//				
//				getResultGrid().clear();
//				datasource.clear();
//				datasource.addValues(result);
//				getResultGrid().loadData();
//				getResultGrid().refresh();
//				WaitBox.hideAllDialogs();
//			} else
//			{
//				getResultGrid().clear();
//				getResultGrid().refresh();
//				WaitBox.hideAllDialogs();
//				MessageBox.show("No results found.", MessageType.INFO);
//			}
//		};
//		
//		@Override
//		public void onError(Exception e)
//		{
//			WaitBox.hideAllDialogs();
//			Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
//		}
//	}
	
	/******************************************
	 * Abstract methods
	 ******************************************/
	
	protected abstract String getEditViewOutcome();
	
	protected abstract DeviceAdaptiveGrid getResultGrid();
	
	protected abstract RestServiceProxy<T> getRestServiceProxy();
	
	
	/*******************************************
	 * Messages
	 *******************************************/
	
	public String getMessageForDeleteValidationError()
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
