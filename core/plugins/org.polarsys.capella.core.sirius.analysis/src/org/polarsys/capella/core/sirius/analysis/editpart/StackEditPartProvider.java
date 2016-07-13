/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.polarsys.capella.core.data.capellacommon.Region;

/**
 * Specific Edit Part Provider for regions
 */
public class StackEditPartProvider extends AbstractEditPartProvider {

  @Override
  protected Class<?> getNodeEditPartClass(final View view) {
    String type = view.getType();

    if (type != null) {

      EObject resolvedSemanticElement = ViewUtil.resolveSemanticElement(view);
      if (resolvedSemanticElement != null && resolvedSemanticElement instanceof DNodeContainer
          && ((DNodeContainer) resolvedSemanticElement).getTarget() instanceof Region) {

        if (String.valueOf(DNodeContainer2EditPart.VISUAL_ID).equals(type)) {
          return StackDNode2EditPart.class;
        }

        if (String.valueOf(DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID).equals(type)) {
          return StackCompartment2EditPart.class;

        }

      }
    }

    return super.getNodeEditPartClass(view);
  }

}
