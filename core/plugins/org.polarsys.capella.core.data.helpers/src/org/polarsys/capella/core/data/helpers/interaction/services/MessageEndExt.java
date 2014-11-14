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
   * @param msg_p
   * @return The component type.
   */
  public static COMPONENT_TYPE getMessageEndType(MessageEnd msg_p) {
    COMPONENT_TYPE type = COMPONENT_TYPE.UNDEFINED;

    SequenceMessage seqMsg = msg_p.getMessage();
    if (seqMsg != null) {
      if (seqMsg.getReceivingEnd() == msg_p) {
        type = COMPONENT_TYPE.RECEIVER;
      } else if (seqMsg.getSendingEnd() == msg_p) {
        type = COMPONENT_TYPE.SENDER;
      }
    }

    return type;
  }

  /**
   * @param msg_p
   * @return The message end.
   */
  public static MessageEnd getOppositeMessageEnd(MessageEnd msg_p) {
    MessageEnd msgEnd = null;

    SequenceMessage seqMsg = msg_p.getMessage();
    if (seqMsg != null) {
      if (seqMsg.getReceivingEnd() == msg_p)
        msgEnd = seqMsg.getSendingEnd();
      else if (seqMsg.getSendingEnd() == msg_p)
        msgEnd = seqMsg.getReceivingEnd();
    }

    return msgEnd;
  }

  /**
   * @param msg_p
   * @return the operation
   */
  public static AbstractEventOperation getOperation(MessageEnd msg_p) {
    AbstractEventOperation op = null;
    COMPONENT_TYPE type = getMessageEndType(msg_p);
    Event evt = msg_p.getEvent();

    if (evt != null) {
      switch (type) {
        case RECEIVER: {
          Event event = msg_p.getEvent();
          if (event instanceof EventReceiptOperation) {
            EventReceiptOperation evtOp = (EventReceiptOperation)evt;
            op = evtOp.getOperation();
          }
          break;
        }
        case SENDER: {
          Event event = msg_p.getEvent();
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
