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

/**
 * Facade for the Capella Sys & Soft queries.
 * @version 0.2.0
 */
public interface ICapellaQueries {
  /**
   * Get the root queries.
   * @return the root queries
   */
  IRootQueries getRootQueries();
  
  IGetElementsQueries getGetElementsQueries();
}
