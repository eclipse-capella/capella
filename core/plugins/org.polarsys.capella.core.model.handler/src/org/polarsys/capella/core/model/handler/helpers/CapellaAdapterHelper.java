/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 *
 */
public class CapellaAdapterHelper {

  /**
   * @param object
   */
  public static EObject resolveSemanticObject(Object object) {
    return resolveSemanticObject(object, false);
  }

  /**
   * @param object
   * @param onlySemantic
   */
  public static EObject resolveSemanticObject(Object object, boolean onlySemantic) {
    if (object instanceof EObject) {
      return resolveEObject((EObject) object, onlySemantic);
    } else if (object instanceof IAdaptable) {
      Object adapter = ((IAdaptable) object).getAdapter(EObject.class);
      if (adapter instanceof EObject) {
        return resolveEObject((EObject) adapter, onlySemantic);
      }
    }
    return null;
  }

  /**
   * @param object
   * @param onlySemantic
   */
  private static EObject resolveEObject(EObject object, boolean onlySemantic) {
    if (!onlySemantic && (object instanceof DRepresentationDescriptor || object instanceof DRepresentation)) {
      return object;
    }
    return getBusinessObject(object);
  }

  /**
   * Business level adaptation
   * @param object
   */
  private static EObject getBusinessObject(EObject object) {
    if (object != null) {
      if (CapellaResourceHelper.isSemanticElement(object)) {
        return getRelatedSemanticObject(object);
      }

      EObject obj = (EObject) Platform.getAdapterManager().getAdapter(object, ModelElement.class);
      if (obj == null) {
        obj = (EObject) Platform.getAdapterManager().loadAdapter(object, ModelElement.class.getName());
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
   * @param object object to adapt
   * @return adapted object
   */
  private static EObject getRelatedSemanticObject(EObject object) {
    if (object.eClass().equals(CsPackage.eINSTANCE.getPart())) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(object));
      if (!allowMultiplePart) {
        AbstractType type = ((Part) object).getAbstractType();
        if ((type != null) && !(type instanceof ConfigurationItem)) {
          return type;
        }
      }
    }
    return object;
  }
}
