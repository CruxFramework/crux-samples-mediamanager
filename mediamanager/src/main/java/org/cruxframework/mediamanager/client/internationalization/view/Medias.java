package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("mediasMessage")
public interface Medias extends Messages
{
	@DefaultMessage("Type:")
	String type();
	
	@DefaultMessage("Name:")
	String name();
	
	@DefaultMessage("Borrowed:")
	String borrewed();
	
	@DefaultMessage("Search")
	String search();
	
	@DefaultMessage("Type")
	String typeGrid();
	
	@DefaultMessage("Name")
	String nameGrid();
	
	@DefaultMessage("Artist")
	String artistGrid();

	@DefaultMessage("Person")
	String personGrid();

	@DefaultMessage("Action")
	String actionGrid();
	
	@DefaultMessage("Edit")
	String editGrid();
	
	@DefaultMessage("Delete")
	String deleteGrid();
	
	@DefaultMessage("Lend")
	String lendGrid();
	
}
