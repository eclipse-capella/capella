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

package org.polarsys.capella.core.platform.sirius.adapter;

import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * 
 *
 */
public class SiriusToCapellaAdapterFactory implements IAdapterFactory {

  /**
   * @param object_p
   * @return
   */
  public EObject adaptToBusinessElement(Object object_p) {
    if (object_p instanceof DSemanticDecorator) {
      DSemanticDecorator vpe = (DSemanticDecorator) object_p;
      EObject element = vpe.getTarget();
      if (CapellaResourceHelper.isSemanticElement(element)) {
        return element;
      }
    }
    if (object_p instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor vpe = (DRepresentationDescriptor) object_p;
      EObject element = vpe.getTarget();
      if (CapellaResourceHelper.isSemanticElement(element)) {
        return element;
      }
    }
    if (object_p instanceof DRepresentationElement) {
      DRepresentationElement vpe = (DRepresentationElement) object_p;
      List<EObject> elements = vpe.getSemanticElements();
      for (EObject element : elements) {
        if (CapellaResourceHelper.isSemanticElement(element)) {
          return element;
        }
      }
    }
    return null;
  }

  public Object getAdapter(Object adaptableObject_p, Class adapterType) {
    EObject result = adaptToBusinessElement(adaptableObject_p);
    return result;
  }

  public Class<?>[] getAdapterList() {
    return new Class[] { ModelElement.class };
  }
}
