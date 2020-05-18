/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;

/**
 * Specific Edit Part Provider for rotative image
 */
public class RotativeImageEditPartProvider extends AbstractEditPartProvider {

  public static Set<String> IMAGES_IDS = Stream
      .of("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_providedrequired.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_required.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_provided.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_2.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/InFlowPort.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/OutFlowPort.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPortSmall.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionInputPort.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionOutputPort.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/CategoryInput.png", //$NON-NLS-1$
          "/org.polarsys.capella.core.sirius.analysis/description/images/CategoryOutput.png" //$NON-NLS-1$
      ).collect(Collectors.toSet());;

  @Override
  protected Class<?> getNodeEditPartClass(final View view) {
    String type = view.getType();

    if (type != null) {
      EObject resolvedSemanticElement = ViewUtil.resolveSemanticElement(view);
      if (resolvedSemanticElement != null) {

        if (String.valueOf(WorkspaceImageEditPart.VISUAL_ID).equals(type)) {
          if (resolvedSemanticElement instanceof WorkspaceImage) {
            final WorkspaceImage customStyle = (WorkspaceImage) resolvedSemanticElement;
            if (isRotative(customStyle)) {
              return RotativeImageEditPart.class;
            }
          }

        } else if (String.valueOf(DNode4EditPart.VISUAL_ID).equals(type)) {
          if (resolvedSemanticElement instanceof DNode) {
            DNode spec = (DNode) resolvedSemanticElement;
            if (spec.getOwnedStyle() != null && spec.getOwnedStyle() instanceof WorkspaceImage) {
              if (isRotative((WorkspaceImage) spec.getOwnedStyle())) {
                return RotativeDNode4EditPart.class;
              }
            }
          }
        }
      }
    }

    return super.getNodeEditPartClass(view);
  }

  private boolean isRotative(WorkspaceImage ownedStyle_p) {
    return IMAGES_IDS.contains(ownedStyle_p.getWorkspacePath());
  }

}
