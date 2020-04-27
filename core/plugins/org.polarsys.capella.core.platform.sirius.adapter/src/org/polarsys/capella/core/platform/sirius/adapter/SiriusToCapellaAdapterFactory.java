/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.adapter;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * 
 *
 */
public class SiriusToCapellaAdapterFactory implements IAdapterFactory {

  /**
   * @param object
   * @return
   */
  public EObject adaptToBusinessElement(Object object) {
    try {
      if (object instanceof ItemWrapper) {
        object = ((ItemWrapper) object).getWrappedObject();
      }
      if (object instanceof EObjectWrapper) {
        object = ((EObjectWrapper) object).getElement();
      }
      if (object instanceof EditPart) {
        EditPart editPart = (EditPart) object;
        Object editPartModel = editPart.getModel();
        if (editPartModel instanceof View) {
          EObject siriusElement = ((View) editPartModel).getElement();
          if (siriusElement instanceof DSemanticDecorator) {
            return ((DSemanticDecorator) siriusElement).getTarget();
          }
          if (siriusElement instanceof DRepresentation) {
            return RepresentationHelper.getRepresentationDescriptor((DRepresentation) siriusElement);
          }
        }
      }
      
      // It is for DRepresentationElement and DTableElement
      if (object instanceof DSemanticDecorator) {
        DSemanticDecorator vpe = (DSemanticDecorator) object;
        EObject element = vpe.getTarget();
        if (CapellaResourceHelper.isSemanticElement(element)) {
          return element;
        }
      }

      if (object instanceof IMarker) {
        List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker((IMarker) object);
        if (!objects.isEmpty()) {
          return objects.get(0);
        }
      }
    } catch (IllegalStateException e) {
          // Silent catch: this can happen when trying to get the session
          // on a disposed Eobject
   }
    return null;
  }

  public Object getAdapter(Object adaptableObject_p, Class adapterType) {
    EObject result = adaptToBusinessElement(adaptableObject_p);
    if (adapterType != null && adapterType.isInstance(result)) {
      return result;
    }
    return null;
  }

  public Class<?>[] getAdapterList() {
    return new Class[] { Element.class };
  }
}
