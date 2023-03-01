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

package org.polarsys.capella.core.model.helpers.query.impl;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.IRootQueries;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

public class RootQueries implements IRootQueries {
  /**
   * Get the system engineering element.
   * @see org.polarsys.capella.core.model.helpers.query.IRootQueries#getSystemEngineering()
   * @param modelElement a business element
   */
  public SystemEngineering getSystemEngineering(ExtensibleElement modelElement) {
    return (SystemEngineering) getSystemEngineeringRecursively(modelElement);
  }

  private ModelElement getSystemEngineeringRecursively(ExtensibleElement modelElement) {
    ModelElement result;
    if (null == modelElement) {
      result = null;
    } else if (modelElement instanceof SystemEngineering) {
      result = (ModelElement) modelElement;
    } else {
      ExtensibleElement container = (ExtensibleElement) modelElement.eContainer();
      result = getSystemEngineeringRecursively(container);
    }
    return result;
  }

  public Project getProject(ExtensibleElement modelElement) {
    return (Project) getProjectRecursively(modelElement);
  }

  private ModelElement getProjectRecursively(ExtensibleElement modelElement) {
    ModelElement result;
    if (null == modelElement) {
      result = null;
    } else if (modelElement instanceof Project) {
      result = (ModelElement) modelElement;
    } else {
      ExtensibleElement container = (ExtensibleElement) modelElement.eContainer();
      result = getProjectRecursively(container);
    }
    return result;
  }
}
