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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_SequenceMessage_TimerReflexive extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    SequenceMessage message = (SequenceMessage) ctx.getTarget();
    if (message.getKind().equals(MessageKind.TIMER)) {
      MessageEnd send = message.getSendingEnd();
      MessageEnd receive = message.getReceivingEnd();

      if ((send != null) && (receive != null)) {
        if (send.getCovered().equals(receive.getCovered())) {
          return ctx.createSuccessStatus();
        } else {
          return ctx.createFailureStatus(new Object[] { message.getName() });
        }
      } else {
        // lost and found the type timer. Error.
        return ctx.createFailureStatus(new Object[] { message.getName() });
      }
    }
    return ctx.createSuccessStatus();
  }

}
