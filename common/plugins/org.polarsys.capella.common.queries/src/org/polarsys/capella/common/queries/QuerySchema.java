/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries;

public class QuerySchema {
  public static final String QUERY_IDENTIFIER = "queryIdentifier";
  public static final String QUERY_EXTENDED_IDENTIFIER = "extendedQueryIdentifier";
  public static final String QUERY_ALGORITHM = "queryAlgorithm";
  public static final String FILTER = "filter";
  
  public static String getQueryIdentifier(Class<?> clazz) {
    return clazz.getSimpleName();
  }
}
