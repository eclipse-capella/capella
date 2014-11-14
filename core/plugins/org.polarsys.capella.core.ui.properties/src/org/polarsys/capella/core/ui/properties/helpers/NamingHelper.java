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
package org.polarsys.capella.core.ui.properties.helpers;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

public class NamingHelper {

  /**
   * get the default title for the selection wizard
   * @param currentObject_p
   * @return the default title
   */
  public static String getDefaultTitle(EObject currentObject_p) {
    String title = EObjectLabelProviderHelper.getMetaclassLabel(currentObject_p, true);
    if (currentObject_p instanceof AbstractNamedElement) {
      title = title + ((AbstractNamedElement) currentObject_p).getName();
    }
    return title;
  }

  /**
   * get the default message for the selection wizard
   * @param currentObject_p
   * @return
   */
  public static String getDefaultMessage(EObject currentObject_p, String editedPropertyName_p) {
    String message = "Select " + editedPropertyName_p; //$NON-NLS-1$
    if (currentObject_p instanceof AbstractNamedElement) {
      message = message + " of " + currentObject_p.eClass().getName() + " \"" + ((AbstractNamedElement) currentObject_p).getName() + "\"."; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    return message;
  }
}
