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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check SequenceMessage with NO refinement tracability link. Return Warning in case of SequenceMessage is not internal exchange Component Check
 * only in intra logical layer.
 */
public class MDCHK_SequenceMessage_UnlinkJustification extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SequenceMessage) {
        SequenceMessage seqMsg = (SequenceMessage) eObj;
        Scenario currentScenario = (Scenario) seqMsg.eContainer();
        Scenario upperScenario = ScenarioExt.getUpperScenario(currentScenario);

        if (null != upperScenario && CapellaLayerCheckingExt.isInLogicalLayer(upperScenario) && CapellaLayerCheckingExt.isInLogicalLayer(currentScenario)) {
          if (seqMsg.getKind() == MessageKind.SYNCHRONOUS_CALL || seqMsg.getKind() == MessageKind.ASYNCHRONOUS_CALL) {
            SequenceMessage upperSeqMsgLinked = getUpperSequenceMessage(seqMsg, upperScenario);
            if (null == upperSeqMsgLinked) {
              // Apply this check only for SequenceMessage UNLINKED
              NamedElement sender = SequenceMessageExt.getSender(seqMsg);
              NamedElement receiver = SequenceMessageExt.getReceiver(seqMsg);

              if (ComponentExt.isActor(sender) || ComponentExt.isActor(receiver))
                ctx.createFailureStatus(new Object[] { seqMsg.getName(), upperScenario.getFullLabel() });
              else if (sender instanceof LogicalComponent && receiver instanceof LogicalComponent) {
                // Check if a SequenceMessage between internal Component
                if (!isDirectSubComponents((LogicalComponent) sender, (LogicalComponent) receiver, currentScenario))
                  return ctx.createFailureStatus(new Object[] { seqMsg.getName(), upperScenario.getFullLabel() });
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * Return true if both Components are direct children in according the LogicalComponent owned the scenario given in parameter
   */
  private boolean isDirectSubComponents(LogicalComponent sender_p, LogicalComponent receiver_p, Scenario currentScenario_p) {
    List<Component> lstDirectCommonParent = ComponentExt.getDirectCommonParent(sender_p, receiver_p);
    if (lstDirectCommonParent.size() > 0) {
      LogicalComponent lcScenarioContainer = (LogicalComponent) EcoreUtil2.getFirstContainer(currentScenario_p, LaPackage.Literals.LOGICAL_COMPONENT);
      if (lstDirectCommonParent.contains(lcScenarioContainer))
        return true;
    }
    return false;
  }

  private SequenceMessage getUpperSequenceMessage(SequenceMessage seqMsg_p, Scenario upperScenario_p) {
    List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedTargetElements(seqMsg_p, InteractionPackage.Literals.SEQUENCE_MESSAGE);

    for (CapellaElement capellaElement : lst) {
      SequenceMessage seqMsg = (SequenceMessage) capellaElement;
      if (seqMsg.eContainer() == upperScenario_p)
        return seqMsg;
    }
    return null;
  }

}
