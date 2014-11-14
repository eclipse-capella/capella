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
package org.polarsys.capella.core.sequencediag.specific.tool.internal.provider;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.sequencediag.specific.tool.internal.edit.policy.ChooseServiceEditPolicy;

/**
 * 
 */
public class SpecificEditPolicyProvider implements IEditPolicyProvider {

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof IDiagramElementEditPart) {
            final IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) editPart;
            if (diagramElementEditPart.resolveTargetSemanticElement() instanceof SequenceMessage) {
                final SequenceMessage sequenceMessage = (SequenceMessage) diagramElementEditPart.resolveTargetSemanticElement();
                if (sequenceMessage.getKind() == MessageKind.ASYNCHRONOUS_CALL || sequenceMessage.getKind() == MessageKind.SYNCHRONOUS_CALL) {
                    final CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
                    compoundEditPolicy.setAllowNullCommand(false);

                    final EditPolicy existingPolicy = diagramElementEditPart.getEditPolicy(EditPolicyRoles.OPEN_ROLE);
                    if (existingPolicy != null) {
                        compoundEditPolicy.addEditPolicy(existingPolicy);
                    }
                    
                    compoundEditPolicy.addEditPolicy(new ChooseServiceEditPolicy());
                    diagramElementEditPart.installEditPolicy(EditPolicyRoles.OPEN_ROLE, compoundEditPolicy);
                }
            }
        }
    }

    public void addProviderChangeListener(IProviderChangeListener arg0) {
        // empty.
    }

    public boolean provides(IOperation operation) {
        return false;
    }

    public void removeProviderChangeListener(IProviderChangeListener arg0) {
        // empty
    }

}
