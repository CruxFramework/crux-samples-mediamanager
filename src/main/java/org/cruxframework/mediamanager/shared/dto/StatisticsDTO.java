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
package org.cruxframework.mediamanager.shared.dto;

import java.io.Serializable;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class StatisticsDTO implements Serializable
{
	private static final long serialVersionUID = 4744408371345757254L;

	private Integer totalCDs;
	private Integer borrowedCDs;
	private Integer forgottenCDs;
	private Integer totalDVDs;
	private Integer borrowedDVDs;
	private Integer forgottenDVDs;

	/**
	 * @return the totalCDs
	 */
	public Integer getTotalCDs()
	{
		return totalCDs;
	}

	/**
	 * @param totalCDs the totalCDs to set
	 */
	public void setTotalCDs(Integer totalCDs)
	{
		this.totalCDs = totalCDs;
	}

	/**
	 * @return the borrowedCDs
	 */
	public Integer getBorrowedCDs()
	{
		return borrowedCDs;
	}

	/**
	 * @param borrowedCDs the borrowedCDs to set
	 */
	public void setBorrowedCDs(Integer borrowedCDs)
	{
		this.borrowedCDs = borrowedCDs;
	}

	/**
	 * @return the totalDVDs
	 */
	public Integer getTotalDVDs()
	{
		return totalDVDs;
	}

	/**
	 * @param totalDVDs the totalDVDs to set
	 */
	public void setTotalDVDs(Integer totalDVDs)
	{
		this.totalDVDs = totalDVDs;
	}

	/**
	 * @return the borrowedDVDs
	 */
	public Integer getBorrowedDVDs()
	{
		return borrowedDVDs;
	}

	/**
	 * @param borrowedDVDs the borrowedDVDs to set
	 */
	public void setBorrowedDVDs(Integer borrowedDVDs)
	{
		this.borrowedDVDs = borrowedDVDs;
	}

	/**
	 * @return the forgottenCDs
	 */
	public Integer getForgottenCDs()
	{
		return forgottenCDs;
	}

	/**
	 * @param forgottenCDs the forgottenCDs to set
	 */
	public void setForgottenCDs(Integer forgottenCDs)
	{
		this.forgottenCDs = forgottenCDs;
	}

	/**
	 * @return the forgottenDVDs
	 */
	public Integer getForgottenDVDs()
	{
		return forgottenDVDs;
	}

	/**
	 * @param forgottenDVDs the forgottenDVDs to set
	 */
	public void setForgottenDVDs(Integer forgottenDVDs)
	{
		this.forgottenDVDs = forgottenDVDs;
	}
}
