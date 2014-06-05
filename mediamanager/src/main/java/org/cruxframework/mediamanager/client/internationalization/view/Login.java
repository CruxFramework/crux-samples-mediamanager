package org.cruxframework.mediamanager.client.internationalization.view;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("loginMessage")
public interface Login extends Messages
{
	@DefaultMessage("Login: ")
	String login();
	
	@DefaultMessage("Password: ")
	String password();
	
	@DefaultMessage("Access my media")
	String loginButtun();
	
	@DefaultMessage("Create an account")
	String signUpButton();
}
