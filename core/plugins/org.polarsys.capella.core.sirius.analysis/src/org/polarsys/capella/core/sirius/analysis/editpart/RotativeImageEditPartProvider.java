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
package org.polarsys.capella.core.sirius.analysis.editpart;

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

  /**
   * @param ownedStyle_p
   * @return
   */
  private boolean isRotative(WorkspaceImage ownedStyle_p) {
    for (String imageId : RotativeImageEditPart.IMAGES_ID) {
      if (ownedStyle_p.getWorkspacePath() != null && ownedStyle_p.getWorkspacePath().startsWith(imageId)) {
        return true;
      }
    }
    return false;
  }

}
