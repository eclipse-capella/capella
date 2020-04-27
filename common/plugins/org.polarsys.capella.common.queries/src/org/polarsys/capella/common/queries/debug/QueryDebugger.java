/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries.debug;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.queries.testGeneration.QueryTester;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 */
public class QueryDebugger {

	public static List<?> executeQueryWithInclusionDebug(String queryIdentifier, Object semanticsObject, Collection<?> oldResult) {
		return executeQueryWithInclusionDebug(queryIdentifier, semanticsObject, new QueryContext(), null, oldResult);
	}

	public static List<?> executeQueryWithInclusionDebug(String queryIdentifier, Object semanticsObject, QueryContext context, Collection<?> oldResult) {
		return executeQueryWithInclusionDebug(queryIdentifier, semanticsObject, context, null, oldResult);
	}

	public static List<?> executeQueryWithInclusionDebug(String queryIdentifier, Object semanticsObject, IQueryFilter filter, Collection<?> oldResult) {
		return executeQueryWithInclusionDebug(queryIdentifier, semanticsObject, new QueryContext(), filter, oldResult);
	}

	public static List<?> executeQueryWithEqualityDebug(String queryIdentifier, Object semanticsObject, Collection<?> oldResult) {
		return executeQueryWithEqualityDebug(queryIdentifier, semanticsObject, new QueryContext(), oldResult);
	}

	@SuppressWarnings("nls")
	public static List<?> executeQueryWithInclusionDebug(String queryIdentifier, Object semanticsObject, QueryContext context, IQueryFilter filter,
			Collection<?> oldResult) {
		List<?> result = null;
		try {
			result = QueryInterpretor.executeQuery(queryIdentifier, semanticsObject, context, filter);
			storeTestData(queryIdentifier, semanticsObject, (List) result);
			if (!assertSetIncludedInSet(oldResult, result)) {
				Logger.getLogger(IReportManagerDefaultComponents.MODEL).debug("assertSetIncludedInSet failed for query " + queryIdentifier, null);
				System.out.println("!! ORACLE COMPARISON FAILED !!");
				System.out.println("assertSetIncludedInSet failed for query " + queryIdentifier);
			}
		} catch (Exception exception) {
			Logger.getLogger(IReportManagerDefaultComponents.MODEL).debug("BUG DURING QUERY CALL !!" + queryIdentifier, exception);
			System.out.println("!! BUG DURING QUERY CALL !!");
			exception.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("nls")
	public static List<?> executeQueryWithEqualityDebug(String queryIdentifier, Object semanticsObject, QueryContext context, Collection<?> oldResult) {
		List<?> result = null;
		try {
			result = QueryInterpretor.executeQuery(queryIdentifier, semanticsObject, context);
			storeTestData(queryIdentifier, semanticsObject, (List) result);
			if (!assertSetEqualToSet(oldResult, result)) {
				Logger.getLogger(IReportManagerDefaultComponents.MODEL).debug("assertSetEqualToSet failed for query " + queryIdentifier, null);
				System.out.println("!! ORACLE COMPARISON FAILED !!");
				System.out.println("assertSetEqualToSet failed for query " + queryIdentifier);
				System.out.println("old result :");
				for (Object element : oldResult) {
					System.out.println("  " + element);
				}
				System.out.println("new result :");
				for (Object element : result) {
					System.out.println("  " + element);
				}
			}
		} catch (Exception exception) {
			Logger.getLogger(IReportManagerDefaultComponents.MODEL).debug("BUG DURING QUERY CALL !!" + queryIdentifier, exception);
			System.out.println("!! BUG DURING QUERY CALL !!");
			exception.printStackTrace();
		}
		return result;
	}

	private static boolean assertSetIncludedInSet(Collection<?> set1, Collection<?> set2) {
		for (Object object : set1) {
			if (!set2.contains(object)) {
				return false;
			}
		}
		return true;
	}

	private static boolean assertSetEqualToSet(Collection<?> set1, Collection<?> set2) {
		return (set1.size() == set2.size()) && assertSetIncludedInSet(set1, set2);
	}

	private static void storeTestData(String queryIdentifier, Object semanticsObject, List<Object> objects) {
		QueryTester testGenerator = QueryTester.generator;
		if ((testGenerator != null) && testGenerator.isEnabled()) {
			if (semanticsObject instanceof EObject) {
				EObject input = (EObject) semanticsObject;
				IProject eclipseProject = getEclipseProjectFromEObject(input);
				if (eclipseProject != null) {
					try {// for reliability purpose
						testGenerator.storeOracle(queryIdentifier, semanticsObject, objects, eclipseProject);
					} catch (Exception e) {
						e.printStackTrace();
						// do nothing
					}
				}
			}
		}
	}

	private static IProject getEclipseProjectFromEObject(EObject object) {
		try {
			String[] tab = StringUtils.split(object.eResource().getURI().toString(), '/');
			String projectName = tab[2];
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.getProject(projectName);
		} catch (Exception e) {
			return null;
		}
	}

}
