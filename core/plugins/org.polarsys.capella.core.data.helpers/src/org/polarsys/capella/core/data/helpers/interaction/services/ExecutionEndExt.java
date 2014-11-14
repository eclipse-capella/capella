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
package org.polarsys.capella.core.data.helpers.interaction.services;

import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * ExecutionEnd helpers
 * 
 */
public class ExecutionEndExt {

  /**
   * @param msg_p
   * @return the corresponding sequence message
   */
  public static SequenceMessage getMessage(ExecutionEnd msg_p) {
    SequenceMessage msg = null;

    Execution exec = msg_p.getExecution();
    if (exec != null) {
      InteractionFragment end = exec.getStart();
      if ((end != null) && (end instanceof MessageEnd)) {
        msg = ((MessageEnd) end).getMessage();
      }
    }

    return msg;
  }

  /**
   * Returns the operation of the execution end
   * @param msg_p
   * @return the operation
   */
  public static AbstractEventOperation getOperation(ExecutionEnd msg_p) {
    AbstractEventOperation op = null;

    Execution exec = msg_p.getExecution();
    if (exec != null) {
      InteractionFragment end = exec.getStart();
      if (end instanceof MessageEnd) {
        Event evt = ((MessageEnd) end).getEvent();
        if (evt instanceof EventSentOperation) {
          EventSentOperation evtOp = (EventSentOperation)evt;
          op = evtOp.getOperation();
        }
        else if (evt instanceof EventReceiptOperation) {
          EventReceiptOperation evtOp = (EventReceiptOperation)evt;
          op = evtOp.getOperation();
        }
      }
    }

    return op;
  }
  
}
