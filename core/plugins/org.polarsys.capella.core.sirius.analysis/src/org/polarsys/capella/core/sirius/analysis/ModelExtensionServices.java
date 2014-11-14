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
package org.polarsys.capella.core.sirius.analysis;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This service is used dynamically by MDK Viewpoints. So don't move/rename it unless MDK is updated accordingly.
 * 
 */

public class ModelExtensionServices {
  public boolean isExtensionModelActive(EObject obj) {
    if (obj instanceof DRepresentationElement) {
      DRepresentationElement elt = (DRepresentationElement) obj;
      for (EObject eobj : elt.getSemanticElements()) {
        if (!ModelExtensionHelper.getInstance().isExtensionModelDisabled(eobj)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
}
