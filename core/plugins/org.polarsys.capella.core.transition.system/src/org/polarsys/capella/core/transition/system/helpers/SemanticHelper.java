/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 *
 */
public class SemanticHelper {

  public static Collection<Object> getSemanticObjects(Collection<Object> elements) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public static Object resolveSemanticObject(Object object) {
    Object semantic = object;
    if (semantic != null) {
      if (semantic instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object).getAdapter(EObject.class);
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
      if (semantic instanceof DSemanticDecorator) {
        semantic = ((DSemanticDecorator) semantic).getTarget();
      }
      if ((semantic != null) && !CapellaResourceHelper.isSemanticElement(semantic)) {
        semantic = null;
      }
    }
    return semantic;
  }
}
