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
package org.polarsys.capella.common.helpers.query;

/**
 * Facade for the MDE queries.
 * @version 0.2.0
 */
public interface IMDEQueries {
  /**
   * Get the "GetAll" queries.
   * @return the "GetAll" queries
   */
  IGetAllQueries getAllQueries();
}
