/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class NamingHelper {

  /**
   * get the default title for the selection wizard
   * @param currentObject
   * @return the default title
   */
  public static String getDefaultTitle(EObject currentObject) {
    String title = EObjectLabelProviderHelper.getMetaclassLabel(currentObject, true);
    if (currentObject instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) currentObject).getName();
      title = title + (name == null ? ICommonConstants.EMPTY_STRING : name);
    }
    return title;
  }

  /**
   * get the default message for the selection wizard
   * @param currentObject
   * @return
   */
  public static String getDefaultMessage(EObject currentObject, String editedPropertyName) {
    String message = "Select " + editedPropertyName; //$NON-NLS-1$
    if (currentObject instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) currentObject).getName();
      message = message + " of " + currentObject.eClass().getName() + " \"" + (name == null ? ICommonConstants.EMPTY_STRING : name) + "\"."; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    return message;
  }
}
