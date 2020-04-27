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

package org.polarsys.capella.core.model.helpers.query.impl;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.query.IGetElementsQueries;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class GetElementsQueries implements IGetElementsQueries {

  /**
   * @see org.polarsys.capella.core.model.helpers.query.IGetElementsQueries#getElementById(java.lang.String)
   */
  public ModelElement getElementById(Project project, String id) {
    return getElementByIdRecursively(project, id);
  }

  public ModelElement getElementByIdRecursively(ModelElement modelElement, String id) {
    ModelElement result = null;
    if (modelElement.getId().equals(id)) {
      return modelElement;
    }
    for (EObject obj : modelElement.eContents()) {
      if (obj instanceof ModelElement) {
        result = getElementByIdRecursively((ModelElement) obj, id);
        if (result != null) {
          break;
        }
      }
    }
    return result;
  }
}
