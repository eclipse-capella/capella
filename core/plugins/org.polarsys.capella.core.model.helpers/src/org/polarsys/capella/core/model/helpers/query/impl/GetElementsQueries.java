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
package org.polarsys.capella.core.model.helpers.query.impl;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.query.IGetElementsQueries;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class GetElementsQueries implements IGetElementsQueries {

  /**
   * @see org.polarsys.capella.core.model.helpers.query.IGetElementsQueries#getElementById(java.lang.String)
   */
  public ModelElement getElementById(Project project_p, String id_p) {
    return getElementByIdRecursively(project_p, id_p);
  }

  public ModelElement getElementByIdRecursively(ModelElement modelElement_p, String id_p) {
    ModelElement result = null;
    if (modelElement_p.getId().equals(id_p)) {
      return modelElement_p;
    }
    for (EObject obj : modelElement_p.eContents()) {
      if (obj instanceof ModelElement) {
        result = getElementByIdRecursively((ModelElement) obj, id_p);
        if (result != null) {
          break;
        }
      }
    }
    return result;
  }
}
