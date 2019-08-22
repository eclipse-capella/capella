/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * ModelElement helpers
 */
public class ModelElementExt {
  /**
   * Set object's id while updating the resource's IDToEObjectMap
   * 
   * @param eObj
   * @param id
   */
  public static void setObjectId(ModelElement modelElement, String id) {
    Resource eResource = modelElement.eResource();
    if (eResource instanceof XMLResource) {
      modelElement.setId(id);
      ((XMLResource) eResource).setID(modelElement, id);
    }
  }
}
