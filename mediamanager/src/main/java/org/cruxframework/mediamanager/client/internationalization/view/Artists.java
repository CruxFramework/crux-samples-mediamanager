package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("artistsMessage")
public interface Artists extends Messages
{
	@DefaultMessage("Name:")
	String name();
	
	@DefaultMessage("Search")
	String search();
	
	@DefaultMessage("Name")
	String nameGrid();

	@DefaultMessage("Country")
	String countryGrid();

	@DefaultMessage("Genre")
	String genreGrid();

	@DefaultMessage("Action")
	String actionGrid();

	@DefaultMessage("Edit")
	String editGrid();

	@DefaultMessage("Delete")
	String deleteGrid();
}
