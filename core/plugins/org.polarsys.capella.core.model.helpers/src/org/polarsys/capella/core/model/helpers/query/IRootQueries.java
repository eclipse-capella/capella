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

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Root queries for the Sys & Soft models.
 * @version 0.2.0
 */
public interface IRootQueries {
  /**
   * Get the system engineering element.
   * @param modelElement_p a business element
   * @return the system engineering (or null if it does not exist)
   */
  SystemEngineering getSystemEngineering(ModelElement modelElement_p);
  
  public Project getProject(ModelElement modelElement_p);
}
