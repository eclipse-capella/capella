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
package org.polarsys.capella.core.ui.properties.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class TabbedPropertiesEditPolicyProvider implements IEditPolicyProvider {

  /**
   * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
   */
  public void createEditPolicies(final EditPart editPart) {
    if (editPart instanceof IDiagramElementEditPart) {
      final IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) editPart;
      if (CapellaResourceHelper.isSemanticElement(diagramElementEditPart.resolveTargetSemanticElement())) {
        final CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.setAllowNullCommand(false);

        final EditPolicy existingPolicy = diagramElementEditPart.getEditPolicy(EditPolicyRoles.OPEN_ROLE);
        if (existingPolicy != null) {
          compoundEditPolicy.addEditPolicy(existingPolicy);
        }

        compoundEditPolicy.addEditPolicy(new TabbedPropertiesWizardEditPolicy());
        diagramElementEditPart.installEditPolicy(EditPolicyRoles.OPEN_ROLE, compoundEditPolicy);
      }
    }
  }

  public void addProviderChangeListener(IProviderChangeListener arg0) {
    // empty.
  }

  public boolean provides(IOperation operation) {
    if (operation instanceof CreateEditPoliciesOperation) {
      final CreateEditPoliciesOperation createEditPoliciesOperation = (CreateEditPoliciesOperation) operation;
      if (createEditPoliciesOperation.getEditPart() instanceof IDiagramElementEditPart) {
        final IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) createEditPoliciesOperation.getEditPart();
        if (CapellaResourceHelper.isSemanticElement(diagramElementEditPart.resolveTargetSemanticElement())) {
          return true;
        }
      }
    }
    return false;
  }

  public void removeProviderChangeListener(IProviderChangeListener arg0) {
    // empty
  }
}
