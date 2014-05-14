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
package org.cruxframework.mediamanager.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class Filter implements Serializable
{
	private static final long serialVersionUID = -8102908510693313633L;

	private final List<Object> values = new ArrayList<Object>(0);
	private String field;
	private Operator operator = Operator.EQUALS;
	
	/* all default values is false */
	private boolean closeParenthesis;
	private boolean disjunctive;
	private boolean openParenthesis;
	private boolean caseSensitive;

	public Filter(String field, Object... values)
	{
		this.field = field;
		if (values != null)
		{
			this.values.addAll(Arrays.asList(values));
		}
	}

	public Filter(Operator operator, String field, Object...  values)
	{
		this.field = field;
		if (values != null)
		{
			this.values.addAll(Arrays.asList(values));
		}
		this.operator = operator;
	}
	
	public Filter(Operator operator, String field, Collection<?> values)
	{
		this.field = field;
		this.values.addAll(values);
		this.operator = operator;
	}

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field)
	{
		this.field = field;
	}

	/**
	 * @return the value
	 */
	public Object getValue()
	{
		if (values.isEmpty())
		{
			return null;
		}
		return values.get(0);
	}
	
	/**
	 * @return the operator
	 */
	public Operator getOperator()
	{
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator)
	{
		this.operator = operator;
	}

	/**
	 * @return the closeParenthesis
	 */
	public boolean isCloseParenthesis()
	{
		return closeParenthesis;
	}

	/**
	 * @param closeParenthesis the closeParenthesis to set
	 */
	public void setCloseParenthesis(boolean closeParenthesis)
	{
		this.closeParenthesis = closeParenthesis;
	}

	/**
	 * @return the disjunctive
	 */
	public boolean isDisjunctive()
	{
		return disjunctive;
	}

	/**
	 * @param disjunctive the disjunctive to set
	 */
	public void setDisjunctive(boolean disjunctive)
	{
		this.disjunctive = disjunctive;
	}

	/**
	 * @return the openParenthesis
	 */
	public boolean isOpenParenthesis()
	{
		return openParenthesis;
	}

	/**
	 * @param openParenthesis the openParenthesis to set
	 */
	public void setOpenParenthesis(boolean openParenthesis)
	{
		this.openParenthesis = openParenthesis;
	}

	/**
	 * @return the caseSensitive
	 */
	public boolean isCaseSensitive()
	{
		return caseSensitive;
	}

	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(boolean caseSensitive)
	{
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return the values
	 */
	public List<Object> getValues()
	{
		return values;
	}
}
