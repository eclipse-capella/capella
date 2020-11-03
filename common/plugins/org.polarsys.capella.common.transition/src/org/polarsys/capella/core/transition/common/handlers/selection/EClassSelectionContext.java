/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.handlers.selection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EClassSelectionContext implements ISelectionContext {

  EClass eClass;

  ISelectionContext parent;

  public EClassSelectionContext(ISelectionContext parent, EClass eClass) {
    this.eClass = eClass;
    this.parent = parent;
  }

  public EClassSelectionContext(EClass eClass) {
    this.eClass = eClass;
  }

  public boolean match(EObject source, EObject target, IContext context) {
    if (parent != null) {
      if (!(parent.match(source, target, context))) {
        return false;
      }
    }
    return ((eClass == null) || eClass.isInstance(target));
  }

}
