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

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.IRootQueries;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class RootQueries implements IRootQueries {
  /**
   * Get the system engineering element.
   * @see org.polarsys.capella.core.model.helpers.query.IRootQueries#getSystemEngineering()
   * @param modelElement_p a business element
   */
  public SystemEngineering getSystemEngineering(ModelElement modelElement_p) {
    return (SystemEngineering) getSystemEngineeringRecursively(modelElement_p);
  }

  private ModelElement getSystemEngineeringRecursively(ModelElement modelElement_p) {
    ModelElement result;
    if (null == modelElement_p) {
      result = null;
    } else if (modelElement_p instanceof SystemEngineering) {
      result = modelElement_p;
    } else {
      ModelElement container = (ModelElement) modelElement_p.eContainer();
      result = getSystemEngineeringRecursively(container);
    }
    return result;
  }

  public Project getProject(ModelElement modelElement_p) {
    return (Project) getProjectRecursively(modelElement_p);
  }

  private ModelElement getProjectRecursively(ModelElement modelElement_p) {
    ModelElement result;
    if (null == modelElement_p) {
      result = null;
    } else if (modelElement_p instanceof Project) {
      result = modelElement_p;
    } else {
      ModelElement container = (ModelElement) modelElement_p.eContainer();
      result = getProjectRecursively(container);
    }
    return result;
  }
}
