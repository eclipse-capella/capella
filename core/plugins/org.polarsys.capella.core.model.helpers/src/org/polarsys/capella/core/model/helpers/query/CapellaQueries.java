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

package org.polarsys.capella.core.model.helpers.query;

import org.polarsys.capella.core.model.helpers.query.impl.GetElementsQueries;
import org.polarsys.capella.core.model.helpers.query.impl.RootQueries;

/**
 * Facade for the Capella Sys & Soft queries.
 * @version 0.1.0
 */
public class CapellaQueries implements ICapellaQueries {
  private static CapellaQueries instance = null;
  private IRootQueries rootQueries = null;
  private IGetElementsQueries getElementsQueries = null;

  public static CapellaQueries getInstance() {
    if (null == instance) {
      instance = new CapellaQueries();
    }
    return instance;
  }

  /**
   * Get the root queries.
   * @return the root queries
   * @see org.polarsys.capella.core.model.helpers.query.ICapellaQueries#getRootQueries()
   */
  public IRootQueries getRootQueries() {
    if (null == rootQueries) {
      rootQueries = new RootQueries();
    }
    return rootQueries;
  }
  
  public IGetElementsQueries getGetElementsQueries() {
    if (null == getElementsQueries) {
      getElementsQueries = new GetElementsQueries();
    }
    return getElementsQueries;
  }
}
