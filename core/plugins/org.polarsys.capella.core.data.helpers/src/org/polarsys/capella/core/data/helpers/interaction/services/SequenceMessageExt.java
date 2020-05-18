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

package org.polarsys.capella.core.data.helpers.interaction.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * Adcded to correct 40036, to avoid a link to model helpers.
 * It contains code duplication.
 *
 */
public class SequenceMessageExt {

  public static void resetMessage(SequenceMessage sequenceMessage) {
    MessageEnd rcvMsgEnd = sequenceMessage.getReceivingEnd();
    if (rcvMsgEnd != null) {
      Event evt = rcvMsgEnd.getEvent();
      if (evt instanceof EventReceiptOperation) {
        ((EventReceiptOperation) evt).setOperation(null);
      }
    }
    MessageEnd sndMsgEnd = sequenceMessage.getSendingEnd();
    if (sndMsgEnd != null) {
      Event evt = sndMsgEnd.getEvent();
      if (evt instanceof EventSentOperation) {
        ((EventSentOperation) evt).setOperation(null);
      }
    }
    sequenceMessage.setName(""); //$NON-NLS-1$
  }

  public static AbstractEventOperation getOperation(SequenceMessage sequenceMessage) {
    MessageEnd rcvMsgEnd = sequenceMessage.getReceivingEnd();
    if (rcvMsgEnd != null) {
      Event evt = rcvMsgEnd.getEvent();
      if (evt instanceof EventReceiptOperation) {
        EventReceiptOperation evtOp = (EventReceiptOperation)evt;
        return evtOp.getOperation();
      }
    }
    MessageEnd sndMsgEnd = sequenceMessage.getSendingEnd();
    if (sndMsgEnd != null) {
      Event evt = sndMsgEnd.getEvent();
      if (evt instanceof EventSentOperation) {
        EventSentOperation evtOp = (EventSentOperation)evt;
        return evtOp.getOperation();
      }
    }
    return null;
  }

  public static AbstractEventOperation getFunctionalExchange(SequenceMessage sequenceMessage) {
    MessageEnd rcvMsgEnd = sequenceMessage.getReceivingEnd();
    if (rcvMsgEnd != null) {
      Event evt = rcvMsgEnd.getEvent();
      if (evt instanceof EventReceiptOperation) {
        EventReceiptOperation evtOp = (EventReceiptOperation) evt;
        if (evtOp.getOperation() != null) {
          return evtOp.getOperation();
        }
      }
    }
    MessageEnd sndMsgEnd = sequenceMessage.getSendingEnd();
    if (sndMsgEnd != null) {
      Event evt = sndMsgEnd.getEvent();
      if (evt instanceof EventSentOperation) {
        EventSentOperation evtOp = (EventSentOperation) evt;
        if (evtOp.getOperation() != null) {
          return evtOp.getOperation();
        }
      }
    }
    return null;
  }

  /**
   * 
   * @param list
   * @return
   */
  public static List<SequenceMessage> reverse(List<SequenceMessage> list) {
    List<SequenceMessage> reversedList = new ArrayList<>();

    ListIterator<SequenceMessage> iterator = list.listIterator(list.size());
    while (iterator.hasPrevious()) {
      reversedList.add(iterator.previous());
    }

    return reversedList;
  }

  /**
   * 
   * @param sequenceMessage
   * @return
   */
  public static SequenceMessage findReplySequenceMessage(SequenceMessage sequenceMessage) {
    ECrossReferenceAdapter crossReferenceAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(sequenceMessage.getReceivingEnd());
    for (Setting setting : crossReferenceAdapter.getInverseReferences(sequenceMessage.getReceivingEnd())) {
      if (setting.getEObject() instanceof Execution && setting.getEStructuralFeature() == InteractionPackage.eINSTANCE.getTimeLapse_Start()) {
        return ((MessageEnd) ((Execution) setting.getEObject()).getFinish()).getMessage();
      }
    }
    return null;
  }

  /**
   * Returns the 'calling' or 'reply' branch related to the given sequence message.
   * 
   * @param sequenceMessage1
   * @return
   */
  public static SequenceMessage getOppositeSequenceMessage(SequenceMessage sequenceMessage1) {

    boolean flag = false;
    List<SequenceMessage> setPortionMessage = new ArrayList<>();
    Stack<SequenceMessage> stack = new Stack<>();

    if (sequenceMessage1 != null) {
      /** On messages of type 'destroy' there is no processing */
      if (!sequenceMessage1.getKind().equals(MessageKind.CREATE) &&
          !sequenceMessage1.getKind().equals(MessageKind.DELETE) &&
          !sequenceMessage1.getKind().equals(MessageKind.ASYNCHRONOUS_CALL))
      {
        Scenario sc = (Scenario) sequenceMessage1.eContainer();
        if (sc != null) {
          if (sequenceMessage1.getKind().equals(MessageKind.REPLY)) {
            /** If this is a REPLY message => the CALLING branch is present in the upper portion of the messages */
            flag = false;
            for (Iterator<MessageEnd> it = ScenarioExt.getOwnedMessagesEnds(sc).iterator(); it.hasNext() && !flag;) {
              MessageEnd msgEnd = it.next();
              if (msgEnd != null) {
                SequenceMessage msg = msgEnd.getMessage();
                if (msg != null) {
                  if (!msg.equals(sequenceMessage1)) {
                    setPortionMessage.add(msg);
                  }
                  else flag = true;
                }
              }
            }
            /** Invert sequence messages  order */
            setPortionMessage = reverse(setPortionMessage);
          }
          else {
            /** If this is a CALLING message =>  The REPLY branch is present in the upper portion of the messages */
            flag = false;
            for (Iterator<MessageEnd> it = ScenarioExt.getOwnedMessagesEnds(sc).iterator(); it.hasNext();) {
              MessageEnd msgEnd = it.next();
              if (msgEnd != null) {
                SequenceMessage msg = msgEnd.getMessage();
                if (msg != null) {
                  if (flag) {
                    setPortionMessage.add(msg);
                  }
                  else if (msg.equals(sequenceMessage1)) {
                    flag = true;
                  }
                }
              }
            }
          }

          for (SequenceMessage msg : setPortionMessage) {
            if (!msg.getKind().equals(MessageKind.CREATE) &&
                !msg.getKind().equals(MessageKind.DELETE) &&
                !msg.getKind().equals(MessageKind.ASYNCHRONOUS_CALL))
            {
              if (sequenceMessage1.getKind().equals(MessageKind.REPLY)) {
                /**
                 * Treatment: research branch 'toGo'
                 * If current message type return: Pushes current message
                 * else if stack empty: branch the found 'toGo'
                 * else pops the last message and return.
                 */
                if (msg.getKind().equals(MessageKind.REPLY)) {
                  stack.push(msg);
                }
                else {
                  if (stack.isEmpty()) {
                    return msg;
                  }
                  stack.pop();
                }
              }
              else {
                /**
                 * Treatment: research branch 'toGo'
                 * If current message type is return and empty stack: Branch return found. 
                 * If current message type is return and non-empty stack: Pops last message 'toGo' 
                 * else the current message is stacked
                 */
                if (msg.getKind().equals(MessageKind.REPLY)) {
                  if (stack.isEmpty()) {
                    return msg;
                  }
                  stack.pop();
                }
                else {
                  stack.push(msg);
                }
              }
            }
          }
        }
      }
    }

    return null;
  }

}
