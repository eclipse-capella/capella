/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DDiagramItemSemanticEditPolicy;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * It is possible to duplicate the content of a title block cell, by selecting the cell content, pressing CTRL and
 * moving the cell content out of the cell. The nodes are duplicated in the aird but are then removed at the next
 * refresh.
 * 
 * This wrong behavior comes from the {@link DDiagramItemSemanticEditPolicy} which is installed in
 * {@link DDiagramEditPart#installEditPolicy(Object, org.eclipse.gef.EditPolicy)}.
 * 
 * We override this EditPolicy here, so that elements having Title Block mappings are not duplicated. The behavior is
 * unchanged for all the other elements which are not Title Blocks.
 * 
 *
 */

public class DDiagramEditPartProvider extends AbstractEditPartProvider {

  @Override
  protected Class getDiagramEditPartClass(View view) {

    if (view instanceof Diagram && DDiagramEditPart.MODEL_ID.equals(view.getType())) {
      return DDiagramEditPartCustom.class;
    }

    return super.getDiagramEditPartClass(view);
  }

  protected static class DDiagramEditPartCustom extends DDiagramEditPart {

    public DDiagramEditPartCustom(View view) {
      super(view);
    }

    @Override
    protected void createDefaultEditPolicies() {
      super.createDefaultEditPolicies();

      installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DDiagramItemSemanticEditPolicyCustom());
    }

    protected static class DDiagramItemSemanticEditPolicyCustom extends DDiagramItemSemanticEditPolicy {
      @Override
      protected Command getDuplicateCommand(DuplicateElementsRequest request) {

        List<Object> elementsToDuplicate = request.getElementsToBeDuplicated();

        boolean titleBlockElementsPresent = elementsToDuplicate.stream() //
            .filter(DDiagramElement.class::isInstance) //
            .map(DDiagramElement.class::cast) //
            .map(DDiagramElement::getMapping) //
            .anyMatch(TitleBlockHelper::isTitleBlockMapping);

        return titleBlockElementsPresent ? null : super.getDuplicateCommand(request);
      }
    }
  }

}
