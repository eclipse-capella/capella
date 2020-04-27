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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractEndExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * SequenceMessage helpers
 */
public class SequenceMessageExt {

  /**
   * Returns the 'calling' or 'reply' branch related to the given sequence message.
   * @param sequenceMessage1
   * @return
   */
  public static SequenceMessage getOppositeSequenceMessage(SequenceMessage sequenceMessage1) {

    boolean flag = false;
    List<SequenceMessage> setPortionMessage = new ArrayList<SequenceMessage>();
    Stack<SequenceMessage> stack = new Stack<SequenceMessage>();

    if (sequenceMessage1 != null) {
      /** On messages of type 'destroy' there is no processing */
      if (!sequenceMessage1.getKind().equals(MessageKind.CREATE) && !sequenceMessage1.getKind().equals(MessageKind.DELETE) && !sequenceMessage1.getKind().equals(MessageKind.ASYNCHRONOUS_CALL)) {
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
                  } else {
                    flag = true;
                  }
                }
              }
            }
            /** Invert sequence messages order */
            setPortionMessage = ListExt.reverse(setPortionMessage);
          } else {
            /** If this is a CALLING message => The REPLY branch is present in the lower portion of the messages */
            flag = false;
            for (MessageEnd msgEnd : ScenarioExt.getOwnedMessagesEnds(sc)) {
              if (msgEnd != null) {
                SequenceMessage msg = msgEnd.getMessage();
                if (msg != null) {
                  if (flag) {
                    setPortionMessage.add(msg);
                  } else if (msg.equals(sequenceMessage1)) {
                    flag = true;
                  }
                }
              }
            }
          }

          for (SequenceMessage msg : setPortionMessage) {
            if (!msg.getKind().equals(MessageKind.CREATE) && !msg.getKind().equals(MessageKind.DELETE) && !msg.getKind().equals(MessageKind.ASYNCHRONOUS_CALL)) {
              if (sequenceMessage1.getKind().equals(MessageKind.REPLY)) {
                /**
                 * Treatment: research branch "aller" 
                 * - if the message type is "retour" : Stacks current message 
                 * - otherwise if the stack is empty : the "aller" branch is found.
                 * 			- else unstacks last message "retour".
                 */
            	  
                if (msg.getKind().equals(MessageKind.REPLY)) {
                  stack.push(msg);
                } else {
                  if (stack.isEmpty()) {
                    return msg;
                  }
                  stack.pop();
                }
              } else {
                /**
                 * Treatment: research branch 'retour' 
                 * - if the current message type is "retour" and the stack is empty : the "retour" branch is found. 
                 * - otherwise f the current message type is "retour" and the stack is not empty : unstacks last message "retour"
                 * 			- else Stacks current message .
                 */
                if (msg.getKind().equals(MessageKind.REPLY)) {
                  if (stack.isEmpty()) {
                    return msg;
                  }
                  stack.pop();
                } else {
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

  /**
   * Gets all the interfaces implemented by the receiver of the sequence message
   * @param currentSequenceMessage the current sequence message
   * @return list of Interfaces.
   */
  public static List<Interface> getOwnedInterfacesImplementedByReceiverOfSequenceMessage(SequenceMessage currentSequenceMessage) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != currentSequenceMessage) {
      NamedElement component = getReceiver(currentSequenceMessage);
      if ((null != component) && (component instanceof Component)) {
        list.addAll(ComponentExt.getImplementedInterfaces((Component) component));
      }
    }
    return list;
  }

  /**
   * Gets all the interfaces used by the sender of the sequence message
   * @param currentSequenceMessage the current sequence message
   * @return list of Interfaces
   */
  public static List<Interface> getOwnedInterfacesUsedBySenderOfSequenceMessage(SequenceMessage currentSequenceMessage) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != currentSequenceMessage) {
      NamedElement component = getSender(currentSequenceMessage);
      if ((null != component) && (component instanceof Component)) {
        list.addAll(ComponentExt.getUsedInterfaces((Component) component));
      }
    }
    return list;
  }

  /**
   * Gets the sender of the sequence message
   * @param currentSequenceMessage the current sequence message
   * @return the sender component
   */
  public static NamedElement getSender(SequenceMessage currentSequenceMessage) {
    if (null != currentSequenceMessage) {
      MessageEnd messageEnd = currentSequenceMessage.getSendingEnd();
      return AbstractEndExt.getComponent(messageEnd);
    }
    return null;
  }

  /**
   * Gets the receiver of the sequence message
   * @param currentSequenceMessage the current sequence message
   * @return the receiver component
   */
  public static NamedElement getReceiver(SequenceMessage currentSequenceMessage) {
    if (null != currentSequenceMessage) {
      MessageEnd messageEnd = currentSequenceMessage.getReceivingEnd();
      return AbstractEndExt.getComponent(messageEnd);
    }
    return null;
  }

  /**
   * For the display purposes, testing if the message between the two elements is a message of writing (SD) or sends (Event). For this we test if the component is a user of an interface allocating the exchange item.
   * @param componentSide
   * @param eiSide
   * @return
   */
  private static boolean isSDWriteAccessMessage(Component componentSide, ExchangeItem eiSide) {
    for (Interface interf : componentSide.getUsedInterfaces()) {
      for (ExchangeItemAllocation alloc : interf.getOwnedExchangeItemAllocations()) {
        if (alloc.getAllocatedItem() == eiSide) {
          return true;
        }
      }
    }
    return false;
  }

  public static String getMessageNameForSharedDataAccess(SequenceMessage message) {

    if (message.getKind() == MessageKind.DELETE) {
      return ICommonConstants.EMPTY_STRING;
    }

    if (message.getKind() == MessageKind.REPLY) {
      return ICommonConstants.EMPTY_STRING;
    }

    // it can be a WRITE or a READ/ACCEPT in case of SharedData
    // BROADCAST/UNICAST/MULTICAST or RECEIVE in case of an event
    // defaultly, we use the kind on the ExchangeItemAllocation on the
    // message
    InstanceRole src = message.getSendingEnd().getCovered();
    InstanceRole tgt = message.getReceivingEnd().getCovered();

    if(message.getSendingEnd().getEvent() instanceof EventSentOperation){
    EventSentOperation eso = (EventSentOperation) message.getSendingEnd().getEvent();
    ExchangeItemAllocation eia = (ExchangeItemAllocation) eso.getOperation();

    if (eia == null) {
      return ICommonConstants.EMPTY_STRING;
    }

    CommunicationLinkProtocol protocol = eia.getReceiveProtocol();
    CommunicationLinkProtocol sendProtocol = eia.getSendProtocol();

    // this can be overrided by the protocol on the communicationLink
    // between the Component and the sharedData
    Component component;
    ExchangeItem ei;

    if (src.getRepresentedInstance() instanceof ExchangeItemInstance) {
      ei = (ExchangeItem) src.getRepresentedInstance().getAbstractType();
      component = (Component) tgt.getRepresentedInstance().getAbstractType();
    } else {
      component = (Component) src.getRepresentedInstance().getAbstractType();
      ei = (ExchangeItem) tgt.getRepresentedInstance().getAbstractType();
    }

    if (ei == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    
    // The create message must display "CREATE" in the case of sharedData,
    // and the send protocol in the case of an event.
    if (message.getKind() == MessageKind.CREATE) {
      if (ei.getExchangeMechanism() == ExchangeMechanism.SHARED_DATA) {
        return "CREATE"; //$NON-NLS-1$
      } else if (ei.getExchangeMechanism() == ExchangeMechanism.EVENT) {
        if (sendProtocol != CommunicationLinkProtocol.UNSET) {
          return sendProtocol.toString();
        }
        // looking in CommunicationLink model
        for (CommunicationLink cl : component.getOwnedCommunicationLinks()) {
          if (cl.getExchangeItem() == ei) {
            return cl.getProtocol().toString();
          }
        }
      }
    }

    if (isSDWriteAccessMessage(component, ei)) {
      if (ei.getExchangeMechanism() == ExchangeMechanism.EVENT) {
        return "SEND"; //$NON-NLS-1$
      }
      return "WRITE"; //$NON-NLS-1$
    }

    //default overload with the EIA if there are other value than UNSET 
    if ((ei.getExchangeMechanism() == ExchangeMechanism.SHARED_DATA) && (eia.getReceiveProtocol() != CommunicationLinkProtocol.UNSET)) {
      return eia.getReceiveProtocol().toString();
    }

    for (CommunicationLink cl : component.getOwnedCommunicationLinks()) {
      if (cl.getExchangeItem() == ei) {
        // found the correct Communication link, overriding it
        if (cl.getProtocol() != CommunicationLinkProtocol.UNSET) {
          return cl.getProtocol().toString();
        }
        return cl.getKind().toString();
      }
    }

    if (ei.getExchangeMechanism() == ExchangeMechanism.EVENT) {
      return CommunicationLinkKind.RECEIVE.toString();
    }
    return protocol.toString();
    }
	return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Return executions which are just under an other execution.
   * @param objP the execution
   * @return the list of result executions
   */
  public static List<Execution> getExecutionFromExecution(Execution execution) {
    List<Execution> result = new ArrayList<Execution>();

    Scenario scenario = (Scenario) execution.eContainer();

    // null check. Before refresh of a diagram it is possible that the container is null.
    if (scenario != null) {
      InstanceRole currentIR = execution.getCovered();
      List<Execution> executionStack = new ArrayList<Execution>();
      for (InteractionFragment ifgt : scenario.getOwnedInteractionFragments()) {
        if (ifgt instanceof AbstractEnd) {
          AbstractEnd ae = (AbstractEnd) ifgt;
          if (ae.getCovered() == currentIR) {
            // if the ae start an execution, in this case stacks this execution
            // if the ae finish an execution, in this case unstacks this execution
            for (TimeLapse laptime : scenario.getOwnedTimeLapses()) {
              if (laptime instanceof Execution) {
                Execution exec2 = (Execution) laptime;
                if (exec2.getCovered() == currentIR) {
                  if (exec2.getStart() == ae) {
                    if (top(executionStack) == execution) {
                      result.add(exec2);
                    }
                    executionStack.add(exec2);

                  }
                  if (exec2.getFinish() == ae) {
                    executionStack.remove(exec2);
                  }
                }
              }
            }
          }
        }
      }
    }

    return result;
  }

  private static Execution top(List<Execution> stack) {
    if (stack.size() == 0) {
      return null;
    }
    return (stack.get(stack.size() - 1));
  }

  public static Execution getStartedExecution(SequenceMessage seqMsg) {
    MessageEnd re = seqMsg.getReceivingEnd();
    Scenario scenario = (Scenario) seqMsg.eContainer();
    for (TimeLapse tl : scenario.getOwnedTimeLapses()) {
      if (tl instanceof Execution) {
        Execution exec = (Execution) tl;
        if (exec.getStart().equals(re)) {
          return exec;
        }

      }
    }
    return null;
  }

  /**
   * Returns all exchange items allovated to the sequence message which are not allocated to the related operation
   * @param message
   * @return
   */
  public static Collection<AbstractExchangeItem> getInvalidExchangeItems(SequenceMessage message) {
    // Get ExchangeItems from SequenceMessage.
    List<ExchangeItem> exchangedItemsFromSequenceMessage = message.getExchangedItems();

    // Get ExchangeItems from invoked operation of given SequenceMessage.
    Collection<AbstractExchangeItem> exchangeItemsFromInvokedOperation = SequenceMessageExt.getExchangeItemsFromOperation(message);

    // Collect invalid ExchangeItems (ExchangeItems referenced by the SequenceMessage but not associated with the invoked operation).
    List<AbstractExchangeItem> invalidExchangeItems = new ArrayList<AbstractExchangeItem>(exchangedItemsFromSequenceMessage);
    invalidExchangeItems.removeAll(exchangeItemsFromInvokedOperation);
    return invalidExchangeItems;
  }

  /**
   * Returns exchange items linked to the associated operation
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static Collection<AbstractExchangeItem> getExchangeItemsFromOperation(SequenceMessage message) {
    AbstractEventOperation invokedOperation = message.getInvokedOperation();

    Collection<AbstractExchangeItem> result = Collections.emptyList();

    if (invokedOperation instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) invokedOperation;
      result = (Collection) exchange.getExchangedItems();

    } else if (invokedOperation instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) invokedOperation;
      result = exchange.getConvoyedInformations();

    } else if (invokedOperation instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation exchange = (ExchangeItemAllocation) invokedOperation;
      result = Collections.singletonList((AbstractExchangeItem) exchange.getAllocatedItem());
    }

    return result;
  }

}
