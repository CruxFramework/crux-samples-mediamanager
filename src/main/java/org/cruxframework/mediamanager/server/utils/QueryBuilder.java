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
package org.cruxframework.mediamanager.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;

/**
 * Class description: Implements a {@link Filter} based query builder.
 * 
 * @see {@link Filter}.
 * 
 * @author alexandre.costa
 */
// CHECKSTYLE:OFF
public class QueryBuilder
{
	private static final String FROM = "from";
	private static final String WHERE = "where";
	private static final String SELECT = "select";
	private static final String OPEN_PARENTHESIS = "(";
	private static final String CLOSE_PARENTHESIS = ")";
	private static final String SPACE = " ";
	private static final String COMMA = ",";
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final String PERCENT = "%";
	private static final String ALIAS = "obj";
	private static final String LABEL_PREFIX = ":value";

	private Map<String, Object> valuesMap = new HashMap<String, Object>();
	private final Collection<Filter> filters = new ArrayList<Filter>(0);
	private final Collection<OrderBy> orderings = new ArrayList<OrderBy>(0);
	private final Set<String> joinProperties = new HashSet<String>();
	private Class<?> entityClass;
	private String query;
	private int index;

	public QueryBuilder(Class<? extends AbstractEntity<?>> entityClass)
	{
		this.entityClass = entityClass;
	}

	public QueryBuilder(String query, Map<String, Object> valuesMap)
	{
		this.query = query;
		this.valuesMap = valuesMap;
	}

	public String buildJPAQuery()
	{
		String select = buildSelect();
		String from = buildFromClause();
		String where = buildWhereClause();
		String orderBy = buildOrderByClause();
		return select + from + buildJoins() + where + orderBy;
	}

	public String buildHQLCountQuery()
	{
		String select = buildSelectCountClause();
		String from = buildFromClause();
		String where = buildWhereClause();
		return select + from + buildJoins() + where;
	}

	public void addFilters(Collection<Filter> filters)
	{
		if (filters != null)
		{
			index = 0;
			this.filters.clear();
			this.filters.addAll(filters);
		}
	}

	public void addOrderings(Collection<OrderBy> orderings)
	{
		if (orderings != null)
		{
			this.orderings.clear();
			this.orderings.addAll(orderings);
		}
	}

	/*********************************************
	 * utilities
	 *********************************************/

	private String buildSelect()
	{
		return SELECT + SPACE + ALIAS + SPACE;
	}

	private String buildSelectCountClause()
	{
		return "select count(*) ";
	}

	private String buildFromClause()
	{
		return FROM + SPACE + entityClass.getSimpleName() + SPACE + ALIAS + SPACE;
	}

	private String buildWhereClause()
	{
		String where = "";
		if (filters.size() > 0)
		{
			where = WHERE + SPACE;

			for (Filter filter : filters)
			{
				String field = filter.getField();
				boolean needsAlias = isMultivaluedProperty(field) && !isRootProperty(field);

				if (needsAlias)
				{
					registerPropertyForJoin(filter);
				}

				if (filter.isOpenParenthesis())
				{
					where += OPEN_PARENTHESIS;
				}

				switch (filter.getOperator())
				{
				case NOT_EQUALS:
					where += buildNotEqualsClause(!needsAlias, filter);
					break;
				case EQUALS:
					where += buildEqualsClause(!needsAlias, filter);
					break;
				case LIKE:
					where += buildLikeClause(!needsAlias, filter);
					break;
				case LIKE_FULL:
					where += buildFullLikeClause(!needsAlias, filter);
					break;
				case LIKE_LEFT:
					where += buildLeftLikeClause(!needsAlias, filter);
					break;
				case LIKE_RIGHT:
					where += buildRightLikeClause(!needsAlias, filter);
					break;
				case GREATER_THAN:
					where += buildBinaryOperatorClause(!needsAlias, filter);
					break;
				case GREATER_THAN_OR_EQUAL:
					where += buildBinaryOperatorClause(!needsAlias, filter);
					break;
				case LESS_THAN:
					where += buildBinaryOperatorClause(!needsAlias, filter);
					break;
				case LESS_THAN_OR_EQUAL:
					where += buildBinaryOperatorClause(!needsAlias, filter);
					break;
				case MEMBER_OF:
					where += buildMemberOfClause(!needsAlias, filter);
					break;
				case NOT_MEMBER_OF:
					where += buildNotMemberOfClause(!needsAlias, filter);
					break;
				case IN:
					where += buildInClause(!needsAlias, filter);
					break;
				case NOT_IN:
					where += buildNotInClause(!needsAlias, filter);
					break;

				default:
					throw new RuntimeException("Nenhum operador informado");
				}

				if (filter.isCloseParenthesis())
				{
					where += CLOSE_PARENTHESIS;
				}

				where += filter.isDisjunctive() ? " or " : " and ";
			}
			where = StringUtils.removeEnd(where, " and ");
			where = StringUtils.removeEnd(where, " or ");
		}
		return where;
	}

	private String buildOrderByClause()
	{
		StringBuffer buffer = new StringBuffer();
		if (orderings.size() > 0)
		{
			buffer.append(" order by");
			StringBuffer item = new StringBuffer();

			for (OrderBy orderBy : orderings)
			{
				if (item.length() != 0)
				{
					item.append(COMMA);
				}

				item.append(SPACE);
				item.append(orderBy.getField());
				item.append(SPACE);

				if (orderBy.isAscending())
				{
					item.append(ASCENDING);
				}
				else
				{
					item.append(DESCENDING);
				}
			}
			buffer.append(item);
		}
		return buffer.toString();
	}

	private String buildBinaryOperatorClause(boolean useClassAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, filter.getValue());
		String where = (useClassAlias ? ALIAS + "." : "") + filter.getField() + " " + filter.getOperator() + " " + label;

		return where;
	}

	private String buildInClause(boolean useClassAlias, Filter filter)
	{
		String where = (useClassAlias ? ALIAS + "." : "") + filter.getField() + " in (";

		for (Object value : filter.getValues())
		{
			String label = generateLabel();
			registerLabelAndValue(label, value);
			where += label + ", ";
		}
		where = StringUtils.removeEnd(where, ", ");
		where += ")";
		return where;
	}

	private String buildNotInClause(boolean useClassAlias, Filter filter)
	{
		String where = (useClassAlias ? ALIAS + "." : "") + filter.getField() + " not in (";

		for (Object value : filter.getValues())
		{
			String label = generateLabel();
			registerLabelAndValue(label, value);
			where += label + ", ";
		}
		where = StringUtils.removeEnd(where, ", ");
		where += ")";
		return where;
	}

	private String buildMemberOfClause(boolean useClassAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, filter.getValue());
		String where = label + " member of " + (useClassAlias ? ALIAS + "." : "") + filter.getField();

		return where;
	}

	private String buildNotMemberOfClause(boolean useClassAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, filter.getValue());
		String where = label + " not member of " + (useClassAlias ? ALIAS + "." : "") + filter.getField();

		return where;
	}

	private String buildNotEqualsClause(boolean useClassAlias, Filter filter)
	{
		if (filter.getValues().size() > 1)
		{
			return buildNotInClause(useClassAlias, filter);
		}
		else
		{
			if (filter.getValue() == null)
			{
				return buildIsNotNullClause(useClassAlias, filter);
			}
			else
			{
				String label = generateLabel();
				String where = (useClassAlias ? ALIAS + "." : "") + filter.getField() + " <> " + label;
				registerLabelAndValue(label, filter.getValue());
				return where;
			}
		}
	}

	private String buildEqualsClause(boolean useClassAlias, Filter filter)
	{
		if (filter.getValues().size() > 1)
		{
			return buildInClause(useClassAlias, filter);
		}
		else
		{
			if (filter.getValue() == null)
			{
				return buildIsNullClause(useClassAlias, filter);
			}
			else
			{
				String label = generateLabel();
				Object value = filter.getValue();
				registerLabelAndValue(label, value);
				String where = StringUtils.EMPTY;

				boolean needsLower = value instanceof String && !filter.isCaseSensitive();

				where += needsLower ? "lower(" : "";
				where += (useClassAlias ? ALIAS + "." : "") + filter.getField();
				where += needsLower ? ") = lower(" : " = ";
				where += label;
				where += needsLower ? ")" : "";
				return where;
			}
		}
	}

	private String buildIsNotNullClause(boolean useClassAlias, Filter filter)
	{
		return (useClassAlias ? ALIAS + "." : "") + filter.getField() + " is not null";
	}

	private String buildIsNullClause(boolean useClassAlias, Filter filter)
	{
		return (useClassAlias ? ALIAS + "." : "") + filter.getField() + " is null";
	}

	private String buildLikeClause(boolean useClassAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, filter.getValue());
		String normalizedField = (useClassAlias ? ALIAS + "." : "") + filter.getField();

		String where = "lower(" + normalizedField + ")" + " like lower(" + label + ")";
		return where;
	}

	private String buildFullLikeClause(boolean useClassAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, PERCENT + filter.getValue() + PERCENT);

		String normalizedField = (useClassAlias ? ALIAS + "." : "") + filter.getField();

		String where = "lower(" + normalizedField + ")" + " like lower(" + label + ")";
		return where;
	}

	private String buildLeftLikeClause(boolean useClasseAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, PERCENT + filter.getValue());
		String normalizedField = (useClasseAlias ? ALIAS + "." : "") + filter.getField();

		String where = "lower(" + normalizedField + ")" + " like lower(" + label + ")";
		return where;
	}

	private String buildRightLikeClause(boolean useClasseAlias, Filter filter)
	{
		String label = generateLabel();
		registerLabelAndValue(label, filter.getValue() + PERCENT);
		String normalizedField = (useClasseAlias ? ALIAS + "." : "") + filter.getField();

		String where = "lower(" + normalizedField + ")" + " like lower(" + label + ")";
		return where;
	}

	private String buildJoins()
	{
		String join = "";
		for (String property : joinProperties)
		{
			join += "join " + ALIAS + "." + property + " as " + property + " ";
		}
		return join;
	}

	private String generateLabel()
	{
		return LABEL_PREFIX + index++;
	}

	private static String normalizeLabel(String label)
	{
		return label.substring(1);
	}

	private void registerLabelAndValue(String label, Object value)
	{
		valuesMap.put(normalizeLabel(label), value);
	}

	private void registerPropertyForJoin(Filter filter)
	{
		String property = filter.getField();
		String rootProperty = StringUtils.substringBefore(property, ".");
		joinProperties.add(rootProperty);
	}

	private boolean isMultivaluedProperty(String property)
	{
		String rootProperty = StringUtils.substringBefore(property, ".");

		try
		{
			Field field = entityClass.getDeclaredField(rootProperty);
			return Iterable.class.isAssignableFrom(field.getType());
		}
		catch (Exception e)
		{
			return false;
		}
	}

	private boolean isRootProperty(String property)
	{
		return !StringUtils.contains(property, ".");
	}

	/*********************************************
	 * getters and setters
	 ********************************************/

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public Map<String, Object> getValuesMap()
	{
		return valuesMap;
	}

	public void setValuesMap(Map<String, Object> valuesMap)
	{
		this.valuesMap = valuesMap;
	}
}
