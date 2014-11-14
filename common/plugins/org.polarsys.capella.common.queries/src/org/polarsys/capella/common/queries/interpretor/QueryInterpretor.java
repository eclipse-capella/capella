/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.queries.interpretor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.queries.IQuery;
import org.polarsys.capella.common.queries.debug.FormatedLogger;
import org.polarsys.capella.common.queries.debug.Log;
import org.polarsys.capella.common.queries.exceptions.ContextShallNotBeNullException;
import org.polarsys.capella.common.queries.exceptions.NonExistingQuery;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.internal.IPrivateQueryContext;
import org.polarsys.capella.common.queries.internal.NoneValue;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class QueryInterpretor {

	private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
	private static final NoneValue NONE_VALUE = new NoneValue();

	private static int queryIdCounter = 0;
	private static Hashtable<IQuery, Integer> queryToId = new Hashtable<IQuery, Integer>();
	private static Hashtable<String, IConfigurationElement> identifier2QueryDef = null;
	private static Hashtable<String, IQuery> identifier2Query = null;
	private static Hashtable<String, String> queryIdentifier2ExtendedQueryIdentifier = null;

	/**
	 * Execute a query whose semantics is defined by one or more extension
	 * points. The result is a set of non-redundant elements. If you call this
	 * method in a query, make sure to pass the query context to the sub call.
	 * 
	 * @throws ContextShallNotBeNullException
	 * @throws NonExistingQuery
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject_p, IQueryContext context, IQueryFilter filter) {
		if (context == null) {
			throw new IllegalArgumentException("the context shall not be null"); //$NON-NLS-1$
		}

		Object semanticsObject = semanticsObject_p;
		if (semanticsObject == null) {
			semanticsObject = NONE_VALUE;
		}

		IPrivateQueryContext theContext = (IPrivateQueryContext) context;
		if (theContext.getExecutionLevel() == 0) {
			FormatedLogger.addTextLn("QUERY CALL : " + queryIdentifier, Log.QUERY_INTERPRETOR); //$NON-NLS-1$
		}
		FormatedLogger.addTextLn("QueryInterpretor.executeQuery(" + queryIdentifier + ", " + semanticsObject + ")", Log.QUERY_INTERPRETOR); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		FormatedLogger.addTextLn("{", Log.QUERY_INTERPRETOR); //$NON-NLS-1$
		FormatedLogger.incIndent(Log.QUERY_INTERPRETOR);

		Collection<Object> subResult = new LinkedHashSet<Object>();

		List<IQuery> queries = getQuerySetForQueryIdentifier(queryIdentifier);
		for (IQuery query : queries) {
			Integer queryId = queryToId.get(query);
			List<Object> res = theContext.getResultFromCache(queryId, semanticsObject);
			if (res == null) {
				FormatedLogger.addTextLn("execute " + query.getIdentifier() + " {", Log.QUERY_INTERPRETOR); //$NON-NLS-1$ //$NON-NLS-2$
				FormatedLogger.incIndent(Log.QUERY_INTERPRETOR);
				theContext.incCallLevel();
				res = query.execute(semanticsObject, theContext);
				theContext.decCallLevel();
				theContext.addInCache(queryId, semanticsObject, res);
				FormatedLogger.decIndent(Log.QUERY_INTERPRETOR);
				FormatedLogger.addTextLn("}", Log.QUERY_INTERPRETOR); //$NON-NLS-1$

			} else {
				FormatedLogger.addTextLn("retrieve from cache for " + query.getIdentifier() + "[" + queryId + "]" + " " + semanticsObject, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						Log.QUERY_INTERPRETOR);
			}

			if ((res != null) && !res.isEmpty()) {
				subResult.addAll(res);
			}
		}

		FormatedLogger.decIndent(Log.QUERY_INTERPRETOR);
		FormatedLogger.addTextLn("}", Log.QUERY_INTERPRETOR); //$NON-NLS-1$
		List<Object> result = new ArrayList<Object>(subResult);

		if (theContext.getExecutionLevel() == 0) {
			if (filter != null) {
				result = executeFilter(result, filter, context);
			}
			FormatedLogger.addTextLn("Filtering ...", Log.QUERY_INTERPRETOR); //$NON-NLS-1$
		}
		return (List<T>) result;
	}

	public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject, IQueryContext context) {
		return executeQuery(queryIdentifier, semanticsObject, context, null);
	}

	public static <T> List<T> executeQuery(String queryIdentifier, IQueryContext context) {
		return executeQuery(queryIdentifier, NONE_VALUE, context, null);
	}

	public static <T> List<T> executeQuery(String queryIdentifier, IQueryContext context, IQueryFilter filter) {
		return executeQuery(queryIdentifier, NONE_VALUE, context, filter);
	}

	public static <T> List<T> executeFilter(List<T> elements, IQueryFilter filter, IQueryContext context) {
		List<T> filteredResult = new ArrayList<T>();
		for (T obj : elements) {
			if (filter.keepElement(obj, context)) {
				filteredResult.add(obj);
			}
		}
		return filteredResult;
	}

	public static <T> List<T> executeFilter(List<T> elements, IQueryFilter filter) {
		return executeFilter(elements, filter, null);
	}

	private static List<IQuery> getQuerySetForQueryIdentifier(String queryIdentifier) {
		List<IQuery> res = new ArrayList<IQuery>();
		if (identifier2QueryDef == null) {
			loadQueries();
		}
		if (!identifier2QueryDef.containsKey(queryIdentifier)) {
			throw new NonExistingQuery(queryIdentifier);
		}
		String currentQueryIdentifier = queryIdentifier;
		while (currentQueryIdentifier != null) {
			IQuery query = getQuery(currentQueryIdentifier);
			res.add(0, query);
			currentQueryIdentifier = query.getExtendedQueryIdentifier();
		}
		return res;
	}

	private static IQuery getQuery(String queryIdentifier) {
		IQuery query = identifier2Query.get(queryIdentifier);
		if (query == null) {
			try {
				IConfigurationElement queryDef = identifier2QueryDef.get(queryIdentifier);
				query = (IQuery) queryDef.createExecutableExtension("algorithm"); //$NON-NLS-1$
				query.setIdentifier(queryIdentifier);
				query.setExtendedQueryIdentifier(queryIdentifier2ExtendedQueryIdentifier.get(queryIdentifier));
				identifier2Query.put(queryIdentifier, query);
				queryToId.put(query, new Integer(queryIdCounter++));
			} catch (CoreException exception_p) {
				exception_p.printStackTrace();
			}
		}
		return query;
	}

	private static void loadQueries() {
		// initialize tables
		identifier2QueryDef = new Hashtable<String, IConfigurationElement>();
		identifier2Query = new Hashtable<String, IQuery>();
		queryIdentifier2ExtendedQueryIdentifier = new Hashtable<String, String>();
		// load all the extensions
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] querySpecifications = registry.getConfigurationElementsFor("org.polarsys.capella.common.queries.query"); //$NON-NLS-1$
		for (IConfigurationElement querySpecification : querySpecifications) {
			String queryIdentifier = querySpecification.getAttribute("queryIdentifier"); //$NON-NLS-1$
			String extendedQueryIdentifier = querySpecification.getAttribute("extendedQueryIdentifier"); //$NON-NLS-1$
			IConfigurationElement[] queryConfs = querySpecification.getChildren("queryAlgorithm"); //$NON-NLS-1$
			IConfigurationElement queryDef = identifier2QueryDef.get(queryIdentifier);
			if (queryDef != null) {
				logger.error(new EmbeddedMessage(
						"Query Engine : At least two query has been defined with identifier " + queryIdentifier + ". Identifier shall be unique !", //$NON-NLS-1$ //$NON-NLS-2$
						IReportManagerDefaultComponents.MODEL));
			} else {
				identifier2QueryDef.put(queryIdentifier, queryConfs[0]);
				if (extendedQueryIdentifier != null) {
					queryIdentifier2ExtendedQueryIdentifier.put(queryIdentifier, extendedQueryIdentifier);
				}
			}
		}
	}

	/** Returns true if a query exists for the given identifier */
	public static boolean doesQueryExist(String queryIdentifier) {
		try {
			getQuerySetForQueryIdentifier(queryIdentifier);
		} catch (NonExistingQuery e) {
			return false;
		}
		return true;
	}
}
