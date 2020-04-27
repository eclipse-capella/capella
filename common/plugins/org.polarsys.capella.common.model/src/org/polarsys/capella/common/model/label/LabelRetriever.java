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

package org.polarsys.capella.common.model.label;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LabelRetriever {
  private static final String BUSINESS_INFORMATION_SOURCE = "http://www.polarsys.org/capella/2007/BusinessInformation"; //$NON-NLS-1$
  private static final String LABEL_KEY = "Label"; //$NON-NLS-1$
  public static final String UNNAMED_ELEMENT = "[Unnamed element]"; //$NON-NLS-1$
  public static final String SEPARATOR = "/"; //$NON-NLS-1$

  public static String getLabel(EObject object) {
    String result = null;
    // TODO: When the shared model will provide the NamedElement, it will be used
    // ***BeginTemp
    EStructuralFeature nameAttribute = object.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
    if (nameAttribute != null) {
      result = (String) object.eGet(nameAttribute);
    }
    // ***Endtemp
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = UNNAMED_ELEMENT;
    }
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = object.toString();
    }
    return result;
  }

  public static String getFullLabel(EObject object) {
    String result;
    result = getFullPath(object) + getLabel(object);
    return result;
  }

  @SuppressWarnings("nls")
  public static String getFullPath(EObject object) {
    String result;
    EObject eContainer = object.eContainer();
    if (eContainer == object) 
      return "";
    if (eContainer != null) {
      result = getFullPath(eContainer) + getLabel(eContainer) + SEPARATOR;
    } else {
      result = SEPARATOR;
    }
    return result;
  }

  public static String getLabel(ENamedElement namedElement) {
    String result = null;
    EAnnotation businessAnnotation = namedElement.getEAnnotation(BUSINESS_INFORMATION_SOURCE);
    if (businessAnnotation != null && businessAnnotation.getDetails().containsKey(LABEL_KEY)) {
      result = businessAnnotation.getDetails().get(LABEL_KEY);
    }
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = namedElement.getName();
    }
    return result;
  }
}
