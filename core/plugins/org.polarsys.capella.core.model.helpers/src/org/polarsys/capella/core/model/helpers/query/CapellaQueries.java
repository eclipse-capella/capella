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
package org.polarsys.capella.core.model.helpers.query;

import org.polarsys.capella.core.model.helpers.query.impl.GetElementsQueries;
import org.polarsys.capella.core.model.helpers.query.impl.RootQueries;

/**
 * Facade for the Capella Sys & Soft queries.
 * @version 0.1.0
 */
public class CapellaQueries implements ICapellaQueries {
  private static CapellaQueries __instance = null;
  private IRootQueries _rootQueries = null;
  private IGetElementsQueries _getElementsQueries = null;

  public static CapellaQueries getInstance() {
    if (null == __instance) {
      __instance = new CapellaQueries();
    }
    return __instance;
  }

  /**
   * Get the root queries.
   * @return the root queries
   * @see org.polarsys.capella.core.model.helpers.query.ICapellaQueries#getRootQueries()
   */
  public IRootQueries getRootQueries() {
    if (null == _rootQueries) {
      _rootQueries = new RootQueries();
    }
    return _rootQueries;
  }
  
  public IGetElementsQueries getGetElementsQueries() {
    if (null == _getElementsQueries) {
      _getElementsQueries = new GetElementsQueries();
    }
    return _getElementsQueries;
  }
}
