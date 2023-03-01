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

package org.polarsys.capella.core.model.helpers.query;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * Root queries for the Sys & Soft models.
 * @version 0.2.0
 */
public interface IRootQueries {
  /**
   * Get the system engineering element.
   * @param modelElement a business element
   * @return the system engineering (or null if it does not exist)
   */
  SystemEngineering getSystemEngineering(ExtensibleElement modelElement);
  
  public Project getProject(ExtensibleElement modelElement);
}
