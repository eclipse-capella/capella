/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.queries.interpretor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.IQuery;
import org.polarsys.capella.common.queries.QuerySchema;
import org.polarsys.capella.common.queries.debug.FormatedLogger;
import org.polarsys.capella.common.queries.debug.Log;
import org.polarsys.capella.common.queries.exceptions.ContextShallNotBeNullException;
import org.polarsys.capella.common.queries.exceptions.NonExistingQuery;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.internal.IPrivateQueryContext;
import org.polarsys.capella.common.queries.internal.NoneValue;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContextConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

public class QueryInterpretor {

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.MODEL);
  private static final NoneValue NONE_VALUE = new NoneValue();

  private static int queryIdCounter = 0;
  private static Hashtable<IQuery, Integer> queryToId = new Hashtable<IQuery, Integer>();
  private static Hashtable<String, IConfigurationElement> identifier2QueryDef = null;
  private static Hashtable<String, Set<IQueryFilter>> identifier2QueryFilters = null;
  private static Hashtable<String, IQuery> identifier2Query = null;
  private static Hashtable<String, String> queryIdentifier2ExtendedQueryIdentifier = null;

  /**
   * Execute a query whose semantics is defined by one or more extension points. The result is a set of non-redundant
   * elements. If you call this method in a query, make sure to pass the query context to the sub call.
   * 
   * @throws ContextShallNotBeNullException
   * @throws NonExistingQuery
   */
  @SuppressWarnings("unchecked")
  public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject, IQueryContext context,
      Set<IQueryFilter> filters) {
    if (context == null) {
      throw new IllegalArgumentException("the context shall not be null"); //$NON-NLS-1$
    }

    Object semanticsObj = semanticsObject;
    if (semanticsObj == null) {
      semanticsObj = NONE_VALUE;
    }

    IPrivateQueryContext theContext = (IPrivateQueryContext) context;
    if (theContext.getExecutionLevel() == 0) {
      FormatedLogger.addTextLn("QUERY CALL : " + queryIdentifier, Log.QUERY_INTERPRETOR); //$NON-NLS-1$
    }
    FormatedLogger.addTextLn("QueryInterpretor.executeQuery(" + queryIdentifier + ", " + semanticsObj + ")", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Log.QUERY_INTERPRETOR);
    FormatedLogger.addTextLn("{", Log.QUERY_INTERPRETOR); //$NON-NLS-1$
    FormatedLogger.incIndent(Log.QUERY_INTERPRETOR);

    Collection<Object> subResult = new LinkedHashSet<Object>();

    Set<IQuery> queries = getQuerySetForQueryIdentifier(queryIdentifier);
    for (IQuery query : queries) {
      Integer queryId = queryToId.get(query);
      List<Object> res = theContext.getResultFromCache(queryId, semanticsObj);
      if (res == null) {
        FormatedLogger.addTextLn("execute " + query.getIdentifier() + " {", Log.QUERY_INTERPRETOR); //$NON-NLS-1$ //$NON-NLS-2$
        FormatedLogger.incIndent(Log.QUERY_INTERPRETOR);
        theContext.incCallLevel();
        res = query.execute(semanticsObj, theContext);
        theContext.decCallLevel();
        theContext.addInCache(queryId, semanticsObj, res);
        FormatedLogger.decIndent(Log.QUERY_INTERPRETOR);
        FormatedLogger.addTextLn("}", Log.QUERY_INTERPRETOR); //$NON-NLS-1$

      } else {
        FormatedLogger.addTextLn(
            "retrieve from cache for " + query.getIdentifier() + "[" + queryId + "]" + " " + semanticsObj, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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
      // Add (or overwrite) query input element in the context to be used by filters
      theContext.overwriteValue(QueryContextConstants.QUERY_INPUT_ELEMENT_PARAMETER, semanticsObject);
      for (IQueryFilter filter : filters) {
        result = executeFilter(result, filter, context);
      }
      FormatedLogger.addTextLn("Filtering ...", Log.QUERY_INTERPRETOR); //$NON-NLS-1$
    }
    return (List<T>) result;
  }

  public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject, IQueryContext context,
      IQueryFilter filter) {
    Set<IQueryFilter> filters = getFilters(queryIdentifier);
    if (filter != null)
      filters.add(filter);
    return executeQuery(queryIdentifier, semanticsObject, context, filters);
  }

  public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject, IQueryContext context) {
    return executeQuery(queryIdentifier, semanticsObject, context, getFilters(queryIdentifier));
  }

  /**
   * Execute the given query on the given semanticObject with an empty QueryContext.
   */
  public static <T> List<T> executeQuery(String queryIdentifier, Object semanticsObject) {
    return executeQuery(queryIdentifier, semanticsObject, new QueryContext());
  }

  public static <T> List<T> executeQuery(String queryIdentifier, IQueryContext context) {
    return executeQuery(queryIdentifier, NONE_VALUE, context, getFilters(queryIdentifier));
  }

  public static <T> List<T> executeQuery(String queryIdentifier, IQueryContext context, IQueryFilter filter) {
    Set<IQueryFilter> filters = getFilters(queryIdentifier);
    if (filter != null)
      filters.add(filter);
    return executeQuery(queryIdentifier, NONE_VALUE, context, filters);
  }

  public static Set<IQueryFilter> getFilters(String queryIdentifier) {
    if (identifier2QueryFilters == null) {
      loadQueries();
    }
    Set<IQueryFilter> result = new HashSet<IQueryFilter>();
    for (IQuery query : getQuerySetForQueryIdentifier(queryIdentifier)) {
      Set<IQueryFilter> filters = identifier2QueryFilters.get(query.getIdentifier());
      if (filters != null) {
        result.addAll(filters);
      }
    }
    return result;
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

  private static Set<IQuery> getQuerySetForQueryIdentifier(String queryIdentifier) {
    Set<IQuery> res = new HashSet<IQuery>();
    if (identifier2QueryDef == null) {
      loadQueries();
    }
    if (!identifier2QueryDef.containsKey(queryIdentifier)) {
      throw new NonExistingQuery(queryIdentifier);
    }
    String currentQueryIdentifier = queryIdentifier;
    while (currentQueryIdentifier != null) {
      IQuery query = getQuery(currentQueryIdentifier);
      if (query != null && !res.contains(query)) {
        res.add(query);
        // FIXME the path here doesn't take into account multiple levels of extenders, shall be fixed
        for (String extendingQueryId : query.getExtendingQueryIdentifiers()) {
          IQuery extendingQuery = getQuery(extendingQueryId);
          if (extendingQuery != null && !res.contains(extendingQuery)) {
            res.add(extendingQuery);
          }
        }
        currentQueryIdentifier = query.getExtendedQueryIdentifier();
      }
    }
    return res;
  }

  private static IQuery getQuery(String queryIdentifier) {
    IQuery query = identifier2Query.get(queryIdentifier);
    if (query == null) {
      try {
        IConfigurationElement queryDef = identifier2QueryDef.get(queryIdentifier);
        if (queryDef != null) {
          query = (IQuery) queryDef.createExecutableExtension("algorithm"); //$NON-NLS-1$
          query.setIdentifier(queryIdentifier);
          query.setExtendedQueryIdentifier(queryIdentifier2ExtendedQueryIdentifier.get(queryIdentifier));
          identifier2Query.put(queryIdentifier, query);
          queryToId.put(query, Integer.valueOf(queryIdCounter++));
        }
      } catch (CoreException exception) {
        exception.printStackTrace();
      }
    }
    return query;
  }

  private static void loadQueries() {
    // initialize tables
    identifier2QueryDef = new Hashtable<String, IConfigurationElement>();
    identifier2QueryFilters = new Hashtable<String, Set<IQueryFilter>>();
    identifier2Query = new Hashtable<String, IQuery>();
    queryIdentifier2ExtendedQueryIdentifier = new Hashtable<String, String>();
    
    // load all the extensions
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IConfigurationElement[] querySpecifications = registry
        .getConfigurationElementsFor("org.polarsys.capella.common.queries.query"); //$NON-NLS-1$

    for (IConfigurationElement querySpecification : querySpecifications) {
      if ("querySpecification".equals(querySpecification.getName())) {
        String queryIdentifier = querySpecification.getAttribute(QuerySchema.QUERY_IDENTIFIER); //$NON-NLS-1$
        String extendedQueryIdentifier = querySpecification.getAttribute(QuerySchema.QUERY_EXTENDED_IDENTIFIER); //$NON-NLS-1$

        IConfigurationElement[] queryConfs = querySpecification.getChildren(QuerySchema.QUERY_ALGORITHM); //$NON-NLS-1$
        IConfigurationElement queryDef = identifier2QueryDef.get(queryIdentifier);
        if (queryDef != null) {
          logger.error(new EmbeddedMessage(
              "Query Engine : At least two query has been defined with identifier " + queryIdentifier //$NON-NLS-1$
                  + ". Identifier shall be unique !", //$NON-NLS-1$
              IReportManagerDefaultComponents.MODEL));
        } else {
          identifier2QueryDef.put(queryIdentifier, queryConfs[0]);
          if (extendedQueryIdentifier != null) {
            queryIdentifier2ExtendedQueryIdentifier.put(queryIdentifier, extendedQueryIdentifier);
          }
        }
        
      } else if ("queryFilter".equals(querySpecification.getName())) {
        String queryIdentifier = querySpecification.getAttribute(QuerySchema.QUERY_IDENTIFIER); //$NON-NLS-1$
        try {
          IQueryFilter filter = (IQueryFilter) querySpecification.createExecutableExtension(QuerySchema.FILTER); //$NON-NLS-1$
          Set<IQueryFilter> filters = identifier2QueryFilters.get(queryIdentifier);
          if (filters == null) {
            filters = new HashSet<IQueryFilter>();
            identifier2QueryFilters.put(queryIdentifier, filters);
          }
          filters.add(filter);
        } catch (CoreException ex) {
          ex.printStackTrace();
        }
      }
    }
    // resolve cross-references
    for (Map.Entry<String, String> entry : queryIdentifier2ExtendedQueryIdentifier.entrySet()) {
      String extendedQueryId = entry.getValue();
      IQuery extendedQuery = (extendedQueryId != null && !ICommonConstants.EMPTY_STRING.equals(extendedQueryId))
          ? getQuery(extendedQueryId)
          : null;
      if (extendedQuery instanceof AbstractQuery) {
        ((AbstractQuery) extendedQuery).addExtendingQueryIdentifier(entry.getKey());
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
