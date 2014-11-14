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
package org.polarsys.capella.core.transition.common.handlers.selection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EClassSelectionContext implements ISelectionContext {

  EClass _class;

  ISelectionContext parent;

  public EClassSelectionContext(ISelectionContext parent_p, EClass class_p) {
    _class = class_p;
    parent = parent_p;
  }

  public EClassSelectionContext(EClass class_p) {
    _class = class_p;
  }

  public boolean match(EObject source_p, EObject target_p, IContext context_p) {
    if (parent != null) {
      if (!(parent.match(source_p, target_p, context_p))) {
        return false;
      }
    }
    return ((_class == null) || _class.isInstance(target_p));
  }

}
