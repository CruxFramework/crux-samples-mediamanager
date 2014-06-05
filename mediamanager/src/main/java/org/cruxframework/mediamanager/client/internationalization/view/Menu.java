package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("menuMessage")
public interface Menu extends Messages
{
	@DefaultMessage("Statistics")
	String statistics();
	
	@DefaultMessage("Add media")
	String addMedia();
	
	@DefaultMessage("Add artist")
	String addArtist();
	
	@DefaultMessage("Search media")
	String searchMedia();
	
	@DefaultMessage("Search artist")
	String searchArtist();
	
	@DefaultMessage("Logged as:")
	String logged(); 
}
