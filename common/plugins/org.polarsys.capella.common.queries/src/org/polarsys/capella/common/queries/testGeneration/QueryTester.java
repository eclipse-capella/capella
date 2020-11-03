/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.queries.testGeneration;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

public abstract class QueryTester {

  public static final String TEST_GENERATOR_QUERY_IDENTIFIER = "org.polarsys.capella.common.queries.testGeneration.GetTestGenerator"; //$NON-NLS-1$
  public static final QueryTester generator = getGenerator();

  private static QueryTester getGenerator() {
    try {
      return (QueryTester) QueryInterpretor.executeQuery(QueryTester.TEST_GENERATOR_QUERY_IDENTIFIER, new QueryContext()).get(0);
    } catch (Exception e) {
      return null;
    }
  }

  public abstract void storeOracle(String queryIdentifier, Object input, List<Object> elements, IProject eclipseProject);

  public abstract void disable();

  public abstract void enable();

  public abstract boolean isEnabled();

  public abstract void normalizeDataFile(IProject eclipseProject) throws IOException;

}