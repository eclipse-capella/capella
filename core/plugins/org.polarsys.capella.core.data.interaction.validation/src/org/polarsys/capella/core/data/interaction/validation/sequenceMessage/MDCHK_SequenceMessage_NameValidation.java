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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * This rule checks name consistency between operations and sequence messages.
 */
public class MDCHK_SequenceMessage_NameValidation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      IStatus status = null;
      SequenceMessage seqMsg = (SequenceMessage) eObj;
      MessageEnd rcvMsgEnd = seqMsg.getReceivingEnd();
      if (null != rcvMsgEnd) {
        Event event = rcvMsgEnd.getEvent();
        if (null != event) {
          if (event instanceof EventReceiptOperation) {
            EventReceiptOperation evt = (EventReceiptOperation) event;
            AbstractEventOperation op = evt.getOperation();
            status = getAppropriateFailureMessage(ctx_p, seqMsg, op);
          }
          else if (event instanceof EventSentOperation) {
            EventSentOperation evt = (EventSentOperation) event;
            AbstractEventOperation op = evt.getOperation();
            status = getAppropriateFailureMessage(ctx_p, seqMsg, op);
          }
        }
      }
      
      if (null != status) 
        return status;
    }
    return ctx_p.createSuccessStatus();
  }

  /**
   * Return appropriate failure message if name does not correspond to sequenceMessage
   * @param ctx_p
   * @param status
   * @param seqMsg
   * @param op
   * @return : IStatus Message
   */
  private IStatus getAppropriateFailureMessage(IValidationContext ctx_p, SequenceMessage seqMsg, AbstractEventOperation op) {
    IStatus status = null;
    if (null != op) {
      String opName = op.getName();
      String seqMsgName = seqMsg.getName();
      // ExchagneItem allocation treatment 
      // Info : ? do we need to treat other sub
      if (op instanceof ExchangeItemAllocation) {
        ExchangeItemAllocation itemAllocation = (ExchangeItemAllocation) op;
        AbstractExchangeItem allocatedItem = itemAllocation.getAllocatedItem();
        if (null != allocatedItem) {
          opName =  allocatedItem.getName();
          if ((null == opName && null != seqMsgName) || (opName != null && !opName.equals(seqMsgName))) {
            status = ctx_p.createFailureStatus(new Object[] { seqMsg.getName(), opName, InformationPackage.Literals.OPERATION.getName() });
          }
        }
      } else {
        if ((null == opName && null != seqMsgName) || (opName != null && !opName.equals(seqMsgName))) {
          status =  ctx_p.createFailureStatus(new Object[] { seqMsg.getName(), opName, InformationPackage.Literals.OPERATION.getName() });
        }
      }
    }
    return status;
  }
}
