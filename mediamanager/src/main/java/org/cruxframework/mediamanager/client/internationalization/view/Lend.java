package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("lendMessage")
public interface Lend extends Messages
{
	@DefaultMessage("Borrowed")
	String borrowed();
	
	@DefaultMessage("Name:")
	String name();

	@DefaultMessage("Date:")
	String date();

	@DefaultMessage("Save")
	String save();

	@DefaultMessage("Cancel")
	String cancel();
}
