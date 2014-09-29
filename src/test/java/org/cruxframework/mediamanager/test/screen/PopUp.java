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
package org.cruxframework.mediamanager.test.screen;

import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.openqa.selenium.NoSuchElementException;

import br.ufmg.dcc.saotome.beholder.ui.Div;
import br.ufmg.dcc.saotome.beholder.ui.form.Button;

/**
 * This class represent a pop up All class pop up should extends this class
 * 
 * @author guilherme.alecrim
 */
public class PopUp
{
	/*
	 * This parameters equivalent to the basic elements in pop up, such as button ok and one message This element are providers by Beholder
	 * Framework
	 */
	private Button btnOk;
	private Div divText;
	private String xpathBtnOk;
	private String xpathMsg;
	private String superXpath;

	/**
	 * constructor initialize all elements.
	 * @param superXpath superXpath
	 * @param xpathMsg xpathMsg
	 * @param xpathBtnOk xpathBtnOk
	 */
	public PopUp(String superXpath, String xpathMsg, String xpathBtnOk)
	{
		divText = SetUp.getBuilder().uiComponentBuilderInstance().divInterface();
		btnOk = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		this.xpathBtnOk = xpathBtnOk;
		this.xpathMsg = xpathMsg;
		this.superXpath = superXpath;
	}

	/*
	 * accessors methods, this methods performed scan in the html before return the element this scan is necessary because the element can
	 * not is displayed in screen to the access
	 */

	/**
	 * @return text div
	 */
	public Div getDivText()
	{
		divText.loadByXPath(getXpathDivText());
		return divText;
	}

	/**
	 * @return Ok button
	 */
	public Button getBtnOk()
	{
		btnOk.loadByXPath(getXpathBtnOk());
		return btnOk;
	}

	/*
	 * this accessors return the xpath of the componets of the pop up
	 */
	public String getXpathBtnOk()
	{
		return xpathBtnOk;
	}

	public void setXpathBtnOk(String xpathBtnOk)
	{
		this.xpathBtnOk = xpathBtnOk;
	}

	public String getXpathDivText()
	{
		return xpathMsg;
	}

	public void setXpathDivText(String xpathDivText)
	{
		this.xpathMsg = xpathDivText;
	}

	public String getSuperXpath()
	{
		return superXpath;
	}

	public void setSuperXpath(String xpathPopUp)
	{
		this.superXpath = xpathPopUp;
	}

	/**
	 * check if pop up is displayed
	 * @return true if it is displayed
	 */
	public boolean isDisplayedPopUp()
	{
		try
		{
			Div popUp = SetUp.getBuilder().uiComponentBuilderInstance().divInterface();
			popUp.loadByXPath(getSuperXpath());
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}

	}

	/**
	 * Confirm pop up, ie, click in button ok
	 */
	public void confirmPopUp()
	{
		if (isDisplayedPopUp())
		{
			getBtnOk().click();
		}
	}

	/**
	 * Get the message of pop up
	 * 
	 * @return the message of pop up
	 */
	public String getMenssagePopUp()
	{
		return getDivText().getText();
	}
}
