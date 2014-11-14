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
