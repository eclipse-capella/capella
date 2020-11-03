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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class ExchangeItemAllocationProtocolSequence extends AbstractValidationRule {
 
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ExchangeItemAllocation  && ((ExchangeItemAllocation) eObj).getAllocatedItem() != null) {
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) eObj;
        ExchangeMechanism mechanism = allocation.getAllocatedItem().getExchangeMechanism();
        
        for (SequenceMessage message : allocation.getInvokingSequenceMessages()) {
          if (message.getKind() != MessageKind.REPLY) {
            boolean hasReply = org.polarsys.capella.core.model.helpers.ScenarioExt.hasReply(message);
            EObject scenario = EcoreUtil2.getFirstContainer(message, InteractionPackage.Literals.SCENARIO);
            
            List<CommunicationLinkProtocol> expectedSendProtocols = LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocols(true, mechanism, message.getKind(), hasReply);
            if (!expectedSendProtocols.isEmpty() && !expectedSendProtocols.contains(allocation.getSendProtocol())) {
              String expected = ListExt.toString(expectedSendProtocols, " || ");
              IStatus status =  ctx.createFailureStatus(eObj, mechanism, expected, Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Sender, message, message.getKind(), hasReply ? "with" : "without", scenario);
              statuses.add(status);
            }
            
            List<CommunicationLinkProtocol> expectedReceiveProtocols = LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocols(false, mechanism, message.getKind(), hasReply);
            if (!expectedReceiveProtocols.isEmpty() && !expectedReceiveProtocols.contains(allocation.getReceiveProtocol())) {
              String expected = ListExt.toString(expectedReceiveProtocols, " || ");
              IStatus status =  ctx.createFailureStatus(eObj, mechanism, expected, Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Receiver, message, message.getKind(), hasReply ? "with" : "without", scenario);
              statuses.add(status);
            }
          }
        }
        
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    
    return ctx.createSuccessStatus();
  }
   
}
