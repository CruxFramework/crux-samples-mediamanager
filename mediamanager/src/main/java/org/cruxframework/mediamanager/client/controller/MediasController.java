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

import java.util.Date;

import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.screen.views.BindView;
import org.cruxframework.crux.core.client.screen.views.BindableView;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.screen.views.ViewActivateEvent;
import org.cruxframework.crux.core.client.screen.views.WidgetAccessor;
import org.cruxframework.crux.smartfaces.client.dialog.Confirm;
import org.cruxframework.crux.smartfaces.client.dialog.DialogViewContainer;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.crux.smartfaces.client.dialog.animation.DialogAnimation;
import org.cruxframework.crux.smartfaces.client.event.OkEvent;
import org.cruxframework.crux.smartfaces.client.event.OkHandler;
import org.cruxframework.crux.widgets.client.datebox.DateBox;
import org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid;
import org.cruxframework.crux.widgets.client.event.SelectEvent;
import org.cruxframework.crux.widgets.client.grid.DataRow;
import org.cruxframework.mediamanager.client.controller.datasource.MediaDTODatasource;
import org.cruxframework.mediamanager.client.proxy.MediaProxy;
import org.cruxframework.mediamanager.client.reuse.controller.SearchController;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("mediasController")
public class MediasController extends SearchController<MediaDTO>
{
	@Inject
	public MediasViewWidgetAccessor mediaViewWidgetAccessor;
	
	@Inject
	public MediaProxy mediaProxy;
	
	@Expose
	public void onActivate()
	{
		/* Clean grid */
		getResultGrid().clear();
		getResultGrid().refresh();
		
		/* Get all media types */
		ListBox types = mediaViewWidgetAccessor.typeListBox();
		if (types.getItemCount() == 0)
		{
			types.addItem("", "");
			types.setSelectedIndex(0);
			for (MediaType type : MediaType.values())
			{
				types.addItem(type.name(), type.name());
			}
		}
		
		/* animations */
		animateContent();
		animateResults();
	}
	
	@Expose
	public void search()
	{
		WaitBox.show("Wait", DialogAnimation.fadeDownUp);
		MediaType type = null; 
		String typeValue = mediaViewWidgetAccessor.typeListBox().getValue(
			mediaViewWidgetAccessor.typeListBox().getSelectedIndex());
		
		if (typeValue != null && typeValue.trim().length() > 0)
		{
			type = MediaType.valueOf(typeValue);
		}
		
		String name = mediaViewWidgetAccessor.nameTextBox().getValue();
		String person = mediaViewWidgetAccessor.personTextBox().getValue();
		mediaProxy.search(type, name, person, this);
	}
	
	@Expose
	public void delete(SelectEvent selectEvent)
	{
		DataRow row = getResultGrid().getRow((Widget) selectEvent.getSource());
		final MediaDTO dto = (MediaDTO) row.getBoundObject();
		final MediasController controller = this;
		Confirm.show(getConfirmDialogTitle(), getConfirmDialogMessage(), new OkHandler()
		{
			@Override
			public void onOk(OkEvent event)
			{
				WaitBox.show("Wait");
				mediaProxy.delete(dto, controller);
			}
		}, null);
	}
	
	
	@Expose
	public void onActivateLendView(ViewActivateEvent event)
	{
		View view = View.of(this);
		DateBox dateBox = (DateBox) view.getWidget("date");
		dateBox.getTextBox().setReadOnly(true);
		dateBox.setFormat(
			new org.cruxframework.crux.widgets.client.datebox.DateBox.CruxDefaultFormat(
				DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
		
		MediaDTO media = event.getParameterObject();
		mediaProxy.getLend(media.getId(), this);
	}
	
	@Expose
	public void lend(SelectEvent selectEvent)
	{
		DataRow row = getResultGrid().getRow((Widget) selectEvent.getSource());
		MediaDTO dto = (MediaDTO) row.getBoundObject();
		DialogViewContainer container = DialogViewContainer.createDialog("lend", "lend", false, false, false, true, null, null, null, 0, 0, dto);
		
		container.center();
		container.show();
	}
	
	@Expose
	public void changeBorrowed(ClickEvent event)
	{
		boolean checked = ((CheckBox) event.getSource()).getValue();
		updateBorrowedCheckBoxState(checked);
	}
	
	private void updateBorrowedCheckBoxState(boolean checked)
	{
		View view = View.of(this);
		TextBox text = (TextBox)view.getWidget("nameTxt");
		DateBox date = (DateBox) view.getWidget("date");

		if (!checked)
		{
			text.setValue("");
			text.setEnabled(false);
			date.setValue(null);
			date.setEnabled(false);
		} else
		{
			text.setEnabled(true);
			date.setEnabled(true);
		}
	}
	
	@Expose
	public void saveLend()
	{
		BindableView<MediaDTO> view = View.of(this);
		final MediaDTO dto = view.getData();
		
		String validation = validate(dto);
		
		if (validation == null)
		{
			mediaProxy.lend(dto.getId(), dto, this);
		} else
		{
			MessageBox.show(validation, MessageType.ERROR);
		}
	}
	
	private static boolean isEmpty(String string)
	{
		return string == null || string.trim().length() == 0;
	}
	
	@SuppressWarnings("deprecation")
	private String validate(MediaDTO dto)
	{
		/* all fields */
		if (dto.getBorrowed() && (isEmpty(dto.getPerson()) || dto.getDate() == null))
		{
			return "Fill all fields";
		}

		/* date validation */
		Date now = new Date();
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(1);
		
		if (dto.getDate() != null && dto.getDate().after(now))
		{
			return "The date you are trying to use is greater than the actual date.";
		}
		
		return null;
	}
	
	@Expose
	public void cancelLend()
	{
		closeDialogViewContainer(View.of(this));
	}
	
	/**********************************************
	 * Callback classes
	 **********************************************/
	
	public void saveLendState(MediaDTO dto, EditOperation result){
		MediaDTODatasource datasource = 
			(MediaDTODatasource) getResultGrid().getDataSource();
		
		datasource.add(dto);
		getResultGrid().clear();
		getResultGrid().loadData();
		getResultGrid().refresh();
		closeDialogViewContainer(View.of(MediasController.this));
	}
	
	
	public void getLendState(MediaDTO result)
	{
		BindableView<MediaDTO> view = View.of(MediasController.this);
		view.setData(result);
		if (result != null)
		{
			updateBorrowedCheckBoxState(result.getBorrowed());
		}
	}
	
	
	/***********************************************
	 * WidgetAccessor interfaces
	 ***********************************************/
	
	@BindView("medias")
	public static interface MediasViewWidgetAccessor extends WidgetAccessor
	{
		TextBox nameTextBox();
		
		ListBox typeListBox();
		
		TextBox personTextBox();
		
		DeviceAdaptiveGrid tableGrid();
	}
	
	/**************************************
	 * Utilities
	 *************************************/
	
	private static void closeDialogViewContainer(View view)
	{
		DialogViewContainer container = (DialogViewContainer) view.getContainer();
		container.setUnloadViewOnClose(true);
		container.hide();
	}
	
	/*************************************
	 * Overwritten methods
	 *************************************/
	
	@Override
	protected String getEditViewOutcome()
	{
		return "media";
	}

	@Override
	protected DeviceAdaptiveGrid getResultGrid()
	{
		return mediaViewWidgetAccessor.tableGrid();
	}

}
