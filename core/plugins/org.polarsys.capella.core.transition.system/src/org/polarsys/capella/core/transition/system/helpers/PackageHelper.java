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

package org.polarsys.capella.core.transition.system.helpers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PackageHelper {

  /**
   * Retrieve whether given object is a capella package
   * @param object
   * @param context
   * @return
   */
  public static boolean isPackage(EObject object, IContext context) {
    if (object != null) {
      if (object instanceof Structure) {
        EClass clazz = object.eClass();
        if (clazz != null) {
          String name = clazz.getName();
          //CapellaM2 is too complex
          if ((name != null) && name.endsWith("Pkg")) { //$NON-NLS-1$
            return true;
          }
        }
      }
    }
    return false;
  }
}
