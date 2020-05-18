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

import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Operation between current SequenceMessage and the upper sequenceMessage linked by refinement tracability. Return Warning in case of
 * Operation is not same between SequenceMessage. Check only in intra logical layer.
 */
public class MDCHK_SequenceMessage_OperationJustification extends AbstractValidationRule {
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
            if (null != upperSeqMsgLinked) {
              // Apply this check only for SequenceMessage LINKED
              if (unjustifyOperation(seqMsg, upperSeqMsgLinked))
                return ctx.createFailureStatus(new Object[] { seqMsg.getName(), upperSeqMsgLinked.getFullLabel() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  private boolean unjustifyOperation(SequenceMessage seqMsg_p, SequenceMessage upperSeqMsgLinked_p) {
    AbstractEventOperation opReceiv = MessageEndExt.getOperation(seqMsg_p.getReceivingEnd());
    AbstractEventOperation opSend = MessageEndExt.getOperation(seqMsg_p.getSendingEnd());
    AbstractEventOperation opReceivUpper = MessageEndExt.getOperation(upperSeqMsgLinked_p.getReceivingEnd());
    AbstractEventOperation opSendUpper = MessageEndExt.getOperation(upperSeqMsgLinked_p.getSendingEnd());

    // The check is apply only if Operation is affected (Null value is tested by the other rule validation 'MDCHK_SequenceMessage_InvokedOperation_1')
    if (opReceiv != null && opSend != null && opReceivUpper != null && opSendUpper != null) {
      if (opReceiv != opReceivUpper || opSend != opSendUpper)
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
