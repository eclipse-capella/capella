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

  public static String getLabel(EObject object_p) {
    String result = null;
    // TODO: When the shared model will provide the NamedElement, it will be used
    // ***BeginTemp
    EStructuralFeature nameAttribute = object_p.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
    if (nameAttribute != null) {
      result = (String) object_p.eGet(nameAttribute);
    }
    // ***Endtemp
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = UNNAMED_ELEMENT;
    }
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = object_p.toString();
    }
    return result;
  }

  public static String getFullLabel(EObject object_p) {
    String result;
    result = getFullPath(object_p) + getLabel(object_p);
    return result;
  }

  @SuppressWarnings("nls")
  public static String getFullPath(EObject object_p) {
    String result;
    EObject eContainer = object_p.eContainer();
    if (eContainer == object_p) 
      return "";
    if (eContainer != null) {
      result = getFullPath(eContainer) + getLabel(eContainer) + SEPARATOR;
    } else {
      result = SEPARATOR;
    }
    return result;
  }

  public static String getLabel(ENamedElement namedElement_p) {
    String result = null;
    EAnnotation businessAnnotation = namedElement_p.getEAnnotation(BUSINESS_INFORMATION_SOURCE);
    if (businessAnnotation != null && businessAnnotation.getDetails().containsKey(LABEL_KEY)) {
      result = businessAnnotation.getDetails().get(LABEL_KEY);
    }
    if (result == null || result.equals("")) { //$NON-NLS-1$
      result = namedElement_p.getName();
    }
    return result;
  }
}
