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

package org.polarsys.capella.core.transition.system.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;


@Deprecated
public class SemanticHelper {
  @Deprecated
  public static Collection<Object> getSemanticObjects(Collection<?> elements) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }
  @Deprecated
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
