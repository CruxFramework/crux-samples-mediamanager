package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("statisticsMessage")
public interface Statistics extends Messages
{
	@DefaultMessage("CDs Total")
	String cdTotal();
	@DefaultMessage("CDs borrowed")
	String cdborrowed();
	@DefaultMessage("More than one month")
	String cdmore();
	
	@DefaultMessage("DVDs Total")
	String dvdTotal();
	@DefaultMessage("DVDs borrowed")
	String dvdborrowed();
	@DefaultMessage("More than one month")
	String dvdmore();
}
