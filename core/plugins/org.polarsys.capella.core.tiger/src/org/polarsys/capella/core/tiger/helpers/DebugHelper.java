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
package org.polarsys.capella.core.tiger.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * Helper.
 */
public class DebugHelper {

  /**
   * 
   */
  private DebugHelper() {
    // 
  }

  public static void displayTree(EObject root) {
    displayTree(root, 0);
  }

  public static void displayTree(EObject root, int level) {
    if (root instanceof GenericTrace) {
      return;
    }

    List<EObject> elements = root.eContents();

    for (int i = 0; i < level; i++) {
      System.out.print("  "); //$NON-NLS-1$
    }

    System.out.println(" + " + elementToString(root)); //$NON-NLS-1$

    for (EObject element : elements) {
      displayTree(element, level + 1);
    }
  }

  public static String elementToString(EObject object) {
    if (object == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (object instanceof NamedElement) {
      if (object == object.eContainer()) {
        System.out.println("Object same container name " + object.toString()); //$NON-NLS-1$
      }
      return "'" + ((NamedElement) object).getName() + "' " + ((NamedElement) object).getFullLabel() + " [" + object.eClass().getName() + "] "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    return "Unnamed of [" + object.eClass().getName() + "] "; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @SuppressWarnings("unchecked")
  public static String elementToString(Object obj) {
    if (obj == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (obj instanceof EObject) {
      return elementToString((EObject) obj);

    } else if (obj instanceof List<?>) {
      List<Object> lst = (List<Object>) obj;
      StringBuilder builder = new StringBuilder();
      for (Object object : lst) {
        builder.append(elementToString(object));
      }
      return builder.toString();

    }
    return obj.toString();
  }
}
