package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("artistMessage")
public interface Artist extends Messages
{
	
	@DefaultMessage("Artist''s Name:")
	String name();

	@DefaultMessage("Artist''s Country:")
	String country();
	
	@DefaultMessage("Artist''s Genre:")
	String genre();
	
	@DefaultMessage("Add Artist")
	String add();
	
	@DefaultMessage("Save Changes")
	String save();
}
