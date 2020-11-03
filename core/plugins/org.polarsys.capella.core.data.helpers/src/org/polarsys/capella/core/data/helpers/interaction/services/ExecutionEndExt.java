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
   * @param executionEnd1
   * @return the corresponding sequence message
   */
  public static SequenceMessage getMessage(ExecutionEnd executionEnd1) {
    SequenceMessage msg = null;

    Execution exec = executionEnd1.getExecution();
    if (exec != null) {
      InteractionFragment end = exec.getStart();
      if (end instanceof MessageEnd) {
        msg = ((MessageEnd) end).getMessage();
      }
    }

    return msg;
  }

  /**
   * Returns the operation of the execution end
   * @param msg
   * @return the operation
   */
  public static AbstractEventOperation getOperation(ExecutionEnd msg) {
    AbstractEventOperation op = null;

    Execution exec = msg.getExecution();
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
