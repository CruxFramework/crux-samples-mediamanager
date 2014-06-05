package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("mediaMessage")
public interface Media extends Messages
{
	@DefaultMessage("Media''s Type:")
	String type();
	
	@DefaultMessage("Media''s Name:")
	String name();
	
	@DefaultMessage("Media''s Artist:")
	String artist();
	
	@DefaultMessage("Add Media")
	String add();
	
	@DefaultMessage("Save Changes")
	String save();
}
