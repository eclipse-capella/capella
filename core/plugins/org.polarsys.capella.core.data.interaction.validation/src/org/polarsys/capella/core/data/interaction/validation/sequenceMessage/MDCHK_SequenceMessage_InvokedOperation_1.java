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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that in Interface Scenarios the 2 ends of all SequenceMessages are associated to an existing Operation.
 */
public class MDCHK_SequenceMessage_InvokedOperation_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Preconditions.
    EMFEventType eType = ctx.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx.createSuccessStatus();
    }
    EObject eObj = ctx.getTarget();
    if (!(eObj instanceof SequenceMessage)) {
      return ctx.createSuccessStatus();
    }

    String lookedObject = "operation"; //$NON-NLS-1$
    SequenceMessage seqMsg = (SequenceMessage) eObj;
    Scenario scenario = (Scenario) seqMsg.eContainer();
    if (scenario.getKind() == ScenarioKind.INTERFACE) {
      MessageEnd rcvMsgEnd = seqMsg.getReceivingEnd();
      if (rcvMsgEnd != null) {
        if (rcvMsgEnd.getEvent() instanceof EventReceiptOperation) { // Avoid CreationEvent/DestructionEvent kind message
          EventReceiptOperation evt = (EventReceiptOperation) rcvMsgEnd.getEvent();
          if (evt != null) {
            AbstractEventOperation op = evt.getOperation();
            if (op == null) {
              return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
            }
          } else {
            return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
          }
        }
      } else {
        return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
      }

      MessageEnd sntMsgEnd = seqMsg.getSendingEnd();
      if (sntMsgEnd != null) {
        if (sntMsgEnd.getEvent() instanceof EventSentOperation) { // Avoid CreationEvent/DestructionEvent kind message
          EventSentOperation evt = (EventSentOperation) sntMsgEnd.getEvent();
          if (evt != null) {
            AbstractEventOperation op = evt.getOperation();
            if (op == null) {
              return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
            }
          } else {
            return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
          }
        }
      } else {
        return ctx.createFailureStatus(seqMsg.getName(), lookedObject);
      }

    }

    return ctx.createSuccessStatus();
  }
}
