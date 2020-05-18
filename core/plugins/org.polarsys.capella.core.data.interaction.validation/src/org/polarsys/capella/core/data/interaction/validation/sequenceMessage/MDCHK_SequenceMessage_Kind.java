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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class MDCHK_SequenceMessage_Kind extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    SequenceMessage msg = (SequenceMessage) ctx.getTarget();
    if (msg.getInvokedOperation() instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) msg.getInvokedOperation();
      ExchangeItem item = allocation.getAllocatedItem();
      if ((null != item) && ExchangeMechanism.OPERATION.equals(item.getExchangeMechanism())) {
        MessageKind kind = msg.getKind();
        CommunicationLinkProtocol protocol = allocation.getSendProtocol();
        if ((MessageKind.UNSET.equals(kind) && !CommunicationLinkProtocol.UNSET.equals(protocol))
            || (MessageKind.SYNCHRONOUS_CALL.equals(kind) && !CommunicationLinkProtocol.SYNCHRONOUS.equals(protocol))
            || (MessageKind.ASYNCHRONOUS_CALL.equals(kind) && !CommunicationLinkProtocol.ASYNCHRONOUS.equals(protocol))) {
          return ctx.createFailureStatus(new Object[] { msg.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
