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
package org.polarsys.capella.core.business.abstractqueries;

import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.queries.debug.FormatedLogger;
import org.polarsys.capella.common.queries.debug.Log;
import org.polarsys.capella.common.queries.debug.QueryDebugger;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

public class RefactorDebugger {

	  public static List<CapellaElement> callAndTestQuery(String queryIdentifier, Object element_p, List<CapellaElement> oldResult, EClass theClass,
	      Class<?> callerClass) {
	    List<CapellaElement> result = oldResult;
	    if (QueryInterpretor.doesQueryExist(queryIdentifier)) {
	      QueryContext context = new QueryContext();
	      context.putValue("theClass", theClass); //$NON-NLS-1$
	      if (queryIdentifier.endsWith("Lib")) {//$NON-NLS-1$
	        result = (List) QueryDebugger.executeQueryWithInclusionDebug(queryIdentifier, element_p, context, oldResult);
	      } else {
	        result = (List) QueryDebugger.executeQueryWithEqualityDebug(queryIdentifier, element_p, context, oldResult);
	      }
	      if (result == null) {
	        result = oldResult;
	      }
	      FormatedLogger.addTextLn("--------------- REFACTORED QUERY HAS BEEN CALLED ---------------", Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.addTextLn("identifier : " + queryIdentifier, Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.addTextLn("old result :", Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.incIndent(Log.QUERY_REFACTOR_DEBUG);
	      for (CapellaElement element : oldResult) {
	        FormatedLogger.addTextLn(element, Log.QUERY_REFACTOR_DEBUG);
	      }
	      FormatedLogger.decIndent(Log.QUERY_REFACTOR_DEBUG);
	      FormatedLogger.addTextLn("new result :", Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.incIndent(Log.QUERY_REFACTOR_DEBUG);
	      for (CapellaElement element : result) {
	        FormatedLogger.addTextLn(element, Log.QUERY_REFACTOR_DEBUG);
	      }
	      FormatedLogger.decIndent(Log.QUERY_REFACTOR_DEBUG);
	    } else {
	      FormatedLogger.addTextLn("--------------- REFACTORED QUERY HAS BEEN CALLED ---------------", Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.addTextLn("identifier : " + queryIdentifier, Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.addTextLn("!! QUERY NOT FOUND !!", Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	      FormatedLogger.addTextLn("!! caller : " + callerClass.getSimpleName(), Log.QUERY_REFACTOR_DEBUG);//$NON-NLS-1$
	    }
	    return result;
	  }

}
