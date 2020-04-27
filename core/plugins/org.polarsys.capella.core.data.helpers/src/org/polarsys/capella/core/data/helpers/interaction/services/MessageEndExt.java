/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * MessageEnd helpers
 */
public class MessageEndExt {

  /**
   * 
   */
  public enum COMPONENT_TYPE {
    /**
     */
    UNDEFINED,
    /**
     */
    SENDER,
    /**
     */
    RECEIVER
  }

  /**
   * @param msg
   * @return The component type.
   */
  public static COMPONENT_TYPE getMessageEndType(MessageEnd msg) {
    COMPONENT_TYPE type = COMPONENT_TYPE.UNDEFINED;

    SequenceMessage seqMsg = msg.getMessage();
    if (seqMsg != null) {
      if (seqMsg.getReceivingEnd() == msg) {
        type = COMPONENT_TYPE.RECEIVER;
      } else if (seqMsg.getSendingEnd() == msg) {
        type = COMPONENT_TYPE.SENDER;
      }
    }

    return type;
  }

  /**
   * @param msg
   * @return The message end.
   */
  public static MessageEnd getOppositeMessageEnd(MessageEnd msg) {
    MessageEnd msgEnd = null;

    SequenceMessage seqMsg = msg.getMessage();
    if (seqMsg != null) {
      if (seqMsg.getReceivingEnd() == msg)
        msgEnd = seqMsg.getSendingEnd();
      else if (seqMsg.getSendingEnd() == msg)
        msgEnd = seqMsg.getReceivingEnd();
    }

    return msgEnd;
  }

  /**
   * @param msg
   * @return the operation
   */
  public static AbstractEventOperation getOperation(MessageEnd msg) {
    AbstractEventOperation op = null;
    COMPONENT_TYPE type = getMessageEndType(msg);
    Event evt = msg.getEvent();

    if (evt != null) {
      switch (type) {
        case RECEIVER: {
          Event event = msg.getEvent();
          if (event instanceof EventReceiptOperation) {
            EventReceiptOperation evtOp = (EventReceiptOperation)evt;
            op = evtOp.getOperation();
          }
          break;
        }
        case SENDER: {
          Event event = msg.getEvent();
          if (event instanceof EventSentOperation) {
            EventSentOperation evtOp = (EventSentOperation)evt;
            op = evtOp.getOperation();
          }
          break;
        }
        case UNDEFINED: {
          /** no op */
        }
      }
    }

    return op;
  }

}
