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

  public static void displayTree(EObject root_p) {
    displayTree(root_p, 0);
  }

  @SuppressWarnings("nls")
  public static void displayTree(EObject root_p, int level) {
    if (root_p instanceof GenericTrace) {
      return;
    }

    List<EObject> elements = root_p.eContents();

    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }

    System.out.println(" + " + elementToString(root_p));

    for (EObject element : elements) {
      displayTree(element, level + 1);
    }
  }

  @SuppressWarnings("nls")
  public static String elementToString(EObject object_p) {
    if (object_p == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (object_p instanceof NamedElement) {
      if (object_p == object_p.eContainer()) {
        System.out.println("Object same container name " + object_p.toString());
      }
      return "'" + ((NamedElement) object_p).getName() + "' " + ((NamedElement) object_p).getFullLabel() + " [" + object_p.eClass().getName() + "] ";
    }

    return "Unnamed of [" + object_p.eClass().getName() + "] ";
  }

  @SuppressWarnings("unchecked")
  public static String elementToString(Object obj_p) {
    if (obj_p == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (obj_p instanceof EObject) {
      return elementToString((EObject) obj_p);

    } else if (obj_p instanceof List<?>) {
      List<EObject> lst = (List<EObject>) obj_p;
      StringBuilder builder = new StringBuilder();
      for (EObject eObject : lst) {
        builder.append(elementToString(eObject));
      }
      return builder.toString();

    }
    return obj_p.toString();
  }
}
