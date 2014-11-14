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
package org.polarsys.capella.core.model.handler.helpers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class CapellaAdapterHelper {

  /**
   * @param object_p
   */
  public static EObject resolveSemanticObject(Object object_p) {
    return resolveSemanticObject(object_p, false);
  }

  /**
   * @param object_p
   * @param onlySemantic_p
   */
  public static EObject resolveSemanticObject(Object object_p, boolean onlySemantic_p) {
    if (object_p instanceof EObject) {
      return resolveEObject((EObject) object_p, onlySemantic_p);
    } else if (object_p instanceof IAdaptable) {
      Object adapter = ((IAdaptable) object_p).getAdapter(EObject.class);
      if (adapter instanceof EObject) {
        return resolveEObject((EObject) adapter, onlySemantic_p);
      }
    }
    return null;
  }

  /**
   * @param object_p
   * @param onlySemantic_p
   */
  private static EObject resolveEObject(EObject object_p, boolean onlySemantic_p) {
    if (!onlySemantic_p && (object_p instanceof DRepresentation)) {
      return object_p;
    }
    return getBusinessObject(object_p);
  }

  /**
   * Business level adaptation
   * @param object_p
   */
  private static EObject getBusinessObject(EObject object_p) {
    if (object_p != null) {
      if (CapellaResourceHelper.isSemanticElement(object_p)) {
        return getRelatedSemanticObject(object_p);
      }

      EObject obj = (EObject) Platform.getAdapterManager().getAdapter(object_p, ModelElement.class);
      if (obj == null) {
        obj = (EObject) Platform.getAdapterManager().loadAdapter(object_p, ModelElement.class.getName());
      }
      if (null == obj) {
        // can happen when we try to adapt a non semantic element (notes, text, ...)
        return null;
      }
      if ((null == obj.eContainer()) || (null == obj.eResource())) {
        // can happen when a diagram shows a deleted element
        return null;
      }
      return getRelatedSemanticObject(obj);
    }
    return null;
  }

  /**
   * @param object_p object to adapt
   * @return adapted object
   */
  private static EObject getRelatedSemanticObject(EObject object_p) {
    if (object_p.eClass().equals(CsPackage.eINSTANCE.getPart())) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(object_p));
      if (!allowMultiplePart) {
        AbstractType type = ((Part) object_p).getAbstractType();
        if ((type != null) && !(type instanceof ConfigurationItem)) {
          return type;
        }
      }
    }
    return object_p;
  }
}
