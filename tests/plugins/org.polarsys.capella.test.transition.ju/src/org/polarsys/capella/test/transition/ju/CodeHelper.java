/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.Component;

/**
 *
 */
public class CodeHelper {
  public static EObject getChildTracingElement(EObject parent, EObject traced_p) {
    if (traced_p instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) traced_p).getName();

      for (EObject child : parent.eContents()) {
        if (traced_p instanceof Component) {
          if (child instanceof Component && name.equals(((AbstractNamedElement) child).getName())) {
            return child;
          }
        } else if (child instanceof AbstractNamedElement) {
          if (name.equals(((AbstractNamedElement) child).getName())) {
            return child;
          }
        }
      }
    }
    return null;
  }
}
