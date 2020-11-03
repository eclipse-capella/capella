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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Components filiation between current SequenceMessage and the upper sequenceMessage linked by refinement tracability. Return Warning in
 * case of Components for current sequenceMessage are not same or child Components for upper SequenceMessage. Check only in intra logical layer.
 */
public class MDCHK_SequenceMessage_ComponentsFiliationJustification extends AbstractValidationRule {
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
              NamedElement sender = SequenceMessageExt.getSender(seqMsg);
              NamedElement receiver = SequenceMessageExt.getReceiver(seqMsg);

              if (unjustifyComponentFilliation(upperSeqMsgLinked, sender, receiver))
                return ctx.createFailureStatus(new Object[] { seqMsg.getName(), upperSeqMsgLinked.getFullLabel() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
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

  private boolean unjustifyComponentFilliation(SequenceMessage upperSeqMsgLinked_p, NamedElement childSender_p, NamedElement childReceiver_p) {
    boolean unjustify = true;
    if (upperSeqMsgLinked_p.getKind() == MessageKind.SYNCHRONOUS_CALL || upperSeqMsgLinked_p.getKind() == MessageKind.ASYNCHRONOUS_CALL) {
      NamedElement sender = SequenceMessageExt.getSender(upperSeqMsgLinked_p);
      NamedElement receiver = SequenceMessageExt.getReceiver(upperSeqMsgLinked_p);

      if (sameComponentDirectFiliation((Component) childSender_p, (Component) sender)
          && sameComponentDirectFiliation((Component) childReceiver_p, (Component) receiver)) {
        unjustify = false;
      }
    }
    return unjustify;
  }

  private boolean sameComponentDirectFiliation(Component childCpnt_p, Component cpnt_p) {
    if (childCpnt_p == cpnt_p)
      return true;
    else if (childCpnt_p instanceof LogicalComponent && cpnt_p instanceof LogicalComponent) {
      if (ComponentExt.getDirectParents(childCpnt_p).contains(cpnt_p))
        return true;
    }
    return false;
  }

}
