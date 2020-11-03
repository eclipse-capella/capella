/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;

/**
 * Specific Edit Part Provider for regions
 */
public class StackEditPartProvider extends AbstractEditPartProvider {

  @Override
  protected Class<?> getNodeEditPartClass(final View view) {
    String type = view.getType();
    if (type != null) {
      EObject siriusElement = ViewUtil.resolveSemanticElement(view);
      EObject capellaElement = (siriusElement instanceof DSemanticDecorator)
          ? ((DSemanticDecorator) siriusElement).getTarget()
          : null;

      if (siriusElement instanceof DNodeContainer && capellaElement instanceof Region) {
        if (String.valueOf(DNodeContainer2EditPart.VISUAL_ID).equals(type)) {
          return StackDNode2EditPart.class;
        }
        if (String.valueOf(DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID).equals(type)) {
          return StackCompartment2EditPart.class;
        }
      }
      else if (siriusElement instanceof DNodeContainer && capellaElement instanceof FunctionalChainReference) {
        if (String.valueOf(DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID).equals(type)) {
          return FcrDNodeContainerViewNodeContainerCompartmentEditPart.class;
        }
        if (String.valueOf(DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID).equals(type)) {
          return FcrDNodeContainerViewNodeContainerCompartment2EditPart.class;
        }
      }
      else if (siriusElement instanceof DNodeListElement
          && ((DNodeListElement) siriusElement).eContainer() instanceof DNodeList
          && ((DNodeList) siriusElement.eContainer()).getTarget() instanceof State) {
        if (String.valueOf(DNodeListElementEditPart.VISUAL_ID).equals(type)) {
          return StackDNodeListElementEditPart.class;
        }
      }
    }
    return super.getNodeEditPartClass(view);
  }

}
