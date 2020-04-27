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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

/**
 * This is a class above classes allowing to know available operations regarding sequence messages <br>
 * NOTE: for now, this class only factorize the <code>getAvailableOperations</code> method for <code>ServiceHelper</code> and <code>SignalHelper</code>, but may factorize some other tasks later.
 */
public class InterfaceHelper {

  /**
   * The single instance of the class
   */
  private static InterfaceHelper _instance = null;

  /**
   * @return the single instance of the helper
   */
  public static InterfaceHelper getInstance() {
    if (null == _instance) {
      _instance = new InterfaceHelper();
    }
    return _instance;
  }

  /**
   * Private constructor
   */
  private InterfaceHelper() {
    // Does nothing
  }

  public static boolean isSharedDataAccess(InstanceRole src, InstanceRole tgt) {

    if ((src != null) && (src.getRepresentedInstance() instanceof ExchangeItemInstance)) {
      return true;
    }
    if ((tgt != null) && (tgt.getRepresentedInstance() instanceof ExchangeItemInstance)) {
      return true;
    }
    return false;
  }

  public static void affectExchangeItem(SequenceMessage sequenceMessage, AbstractEventOperation operation) {
    if (operation != null) {

      String operationName =
          (operation instanceof ExchangeItemAllocation) ? ((ExchangeItemAllocation) operation).getAllocatedItem().getName() : operation.getName();

      MessageEnd receive = sequenceMessage.getReceivingEnd();
      if (receive != null) {
        EventReceiptOperation eventReceiptOperation = getOrCreateReceiptEvent(receive);
        eventReceiptOperation.setOperation(operation);
        eventReceiptOperation.setName("rcvOp_" + operationName + "_" + sequenceMessage.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        sequenceMessage.getReceivingEnd().setName("rcvMsgEnd_" + operationName + "_" + sequenceMessage.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
      }
      MessageEnd send = sequenceMessage.getSendingEnd();
      if (send != null) {
        EventSentOperation eventSentOperation = getOrCreateSentEvent(send);
        eventSentOperation.setOperation(operation);
        eventSentOperation.setName("sndOp_" + operation.getName() + "_" + sequenceMessage.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        sequenceMessage.getSendingEnd().setName("sndMsgEnd_" + operationName + "_" + sequenceMessage.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        sequenceMessage.setName(operationName);
      }
      SequenceMessage reply = SequenceLocalHelper.getOppositeSequenceMessage(sequenceMessage);
      if (reply != null) {
        MessageEnd receiveReply = reply.getReceivingEnd();
        EventReceiptOperation eventReceiptOperationReply = getOrCreateReceiptEvent(receiveReply);
        eventReceiptOperationReply.setOperation(operation);
        eventReceiptOperationReply.setName("rcvOp_" + operationName + "_" + reply.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        reply.getReceivingEnd().setName("rcvMsgEnd_" + operationName + "_" + reply.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$

        MessageEnd sendReply = reply.getSendingEnd();
        EventSentOperation eventSentOperationReply = getOrCreateSentEvent(sendReply);
        eventSentOperationReply.setOperation(operation);
        eventSentOperationReply.setName("sndOp_" + operationName + "_" + reply.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        reply.getSendingEnd().setName("sndMsgEnd_" + operationName + "_" + reply.getKind().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        reply.setName(operationName);
      }
    }

    if (operation instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) operation;
      ExchangeItem item = allocation.getAllocatedItem();
      if (ExchangeMechanism.OPERATION.equals(item.getExchangeMechanism())) {
        MessageKind kind = sequenceMessage.getKind();
        if (!MessageKind.CREATE.equals(kind) && !MessageKind.DELETE.equals(kind)) {
          CommunicationLinkProtocol protocol = allocation.getSendProtocol();
          if (CommunicationLinkProtocol.UNSET.equals(protocol) && !MessageKind.UNSET.equals(kind)) {
            sequenceMessage.setKind(MessageKind.UNSET);
          } else if (CommunicationLinkProtocol.SYNCHRONOUS.equals(protocol) && !MessageKind.SYNCHRONOUS_CALL.equals(kind)) {
            sequenceMessage.setKind(MessageKind.SYNCHRONOUS_CALL);
          } else if (CommunicationLinkProtocol.ASYNCHRONOUS.equals(protocol) && !MessageKind.ASYNCHRONOUS_CALL.equals(kind)) {
            sequenceMessage.setKind(MessageKind.ASYNCHRONOUS_CALL);
          }
        }
      }
    }

  }

  private static EventSentOperation getOrCreateSentEvent(MessageEnd messageEnd) {
    EventSentOperation result;
    if (messageEnd.getEvent() instanceof EventSentOperation) {
      result = (EventSentOperation) messageEnd.getEvent();
    } else {
      result = InteractionFactory.eINSTANCE.createEventSentOperation();
      Scenario scenario = (Scenario) messageEnd.eContainer();
      scenario.getOwnedEvents().add(result);
      messageEnd.setEvent(result);
    }
    return result;
  }

  private static EventReceiptOperation getOrCreateReceiptEvent(MessageEnd messageEnd) {
    EventReceiptOperation result;
    if (messageEnd.getEvent() instanceof EventReceiptOperation) {
      result = (EventReceiptOperation) messageEnd.getEvent();
    } else {
      result = InteractionFactory.eINSTANCE.createEventReceiptOperation();
      Scenario scenario = (Scenario) messageEnd.eContainer();
      scenario.getOwnedEvents().add(result);
      messageEnd.setEvent(result);
    }
    return result;
  }

  private List<Interface> getUsedAndRequiredInterfaces(Component component) {
    List<Interface> result = new ArrayList<Interface>();
    result.addAll(component.getUsedInterfaces());
    result.addAll(component.getRequiredInterfaces());
    for (Feature f : component.getOwnedFeatures()) {
      if (f instanceof ComponentPort) {
        ComponentPort p = (ComponentPort) f;
        result.addAll(p.getRequiredInterfaces());
      }
    }

    return result;
  }

  private List<Interface> getImplementedAndProvidedInterfaces(Component component) {
    List<Interface> result = new ArrayList<Interface>();
    result.addAll(component.getImplementedInterfaces());
    result.addAll(component.getProvidedInterfaces());
    for (Feature f : component.getOwnedFeatures()) {
      if (f instanceof ComponentPort) {
        ComponentPort p = (ComponentPort) f;
        result.addAll(p.getProvidedInterfaces());
      }
    }

    return result;
  }

  /**
   * Filter given ExchangeItemAllocations regarding CommunicationLink(s) or information on the message to create.
   * @param elements
   * @param sourceIR
   * @param targetIR
   * @param messageKind
   * @return
   */
  public List<CapellaElement> filterExchangeItemAllocations(List<CapellaElement> elements, InstanceRole sourceIR, InstanceRole targetIR,
      MessageKind messageKind) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // Get involved Component and ExchangeItem.
    AbstractType sourceType = sourceIR.getRepresentedInstance().getAbstractType();
    AbstractType targetType = targetIR.getRepresentedInstance().getAbstractType();
    Component component = null;
    ExchangeItem exchangeItem = null;
    if ((sourceType instanceof Component) && (targetType instanceof ExchangeItem)) {
      component = (Component) sourceType;
      exchangeItem = (ExchangeItem) targetType;
    } else if ((sourceType instanceof ExchangeItem) && (targetType instanceof Component)) {
      exchangeItem = (ExchangeItem) sourceType;
      component = (Component) targetType;
    } else {
      return Collections.emptyList();
    }
    // Extract communication links between ExchangeItem and Component.
    List<CommunicationLink> communicationLinks = new ArrayList<CommunicationLink>();
    for (CommunicationLink cl : component.getOwnedCommunicationLinks()) {
      if (exchangeItem == cl.getExchangeItem()) {
        communicationLinks.add(cl);
      }
    }
    // Filter ExchangeItemAllocations.
    if (!communicationLinks.isEmpty()) {
      // CommunicationLink(s) is/are found, select only EchangeItemAllocations corresponding to it/them.
      for (CapellaElement element : elements) {
        if (!(element instanceof ExchangeItemAllocation)) {
          result.add(element);
          continue;
        }
        ExchangeItemAllocation exchangeItemAllocation = (ExchangeItemAllocation) element;
        for (CommunicationLink cl : communicationLinks) {
          if (((cl.getKind() == CommunicationLinkKind.RECEIVE) || (cl.getKind() == CommunicationLinkKind.CONSUME)
               || (cl.getKind() == CommunicationLinkKind.EXECUTE) || (cl.getKind() == CommunicationLinkKind.ACCESS))
              && (exchangeItemAllocation.getReceiveProtocol() == cl.getProtocol())) {
            result.add(exchangeItemAllocation);
          } else if (((cl.getKind() == CommunicationLinkKind.CALL) || (cl.getKind() == CommunicationLinkKind.PRODUCE)
                      || (cl.getKind() == CommunicationLinkKind.SEND) || (cl.getKind() == CommunicationLinkKind.WRITE))
                     && (exchangeItemAllocation.getSendProtocol() == cl.getProtocol())) {
            result.add(exchangeItemAllocation);
          } else if (!result.contains(exchangeItemAllocation.getAllocatedItem())) {
            result.add(exchangeItemAllocation.getAllocatedItem());
          }
        }
      }
    } else {
      // No CommunicationLink(s) found, we have to "guess" what the user wants to do and select valid ExchangeItemAllocations.
      boolean isSynchronous = MessageKind.SYNCHRONOUS_CALL == messageKind;
      for (CapellaElement element : elements) {
        if (!(element instanceof ExchangeItemAllocation)) {
          result.add(element);
          continue;
        }
        ExchangeItemAllocation exchangeItemAllocation = (ExchangeItemAllocation) element;
        // SharedData ExchangeItem.
        if (ExchangeMechanism.SHARED_DATA == exchangeItem.getExchangeMechanism()) {
          if ((sourceType instanceof ExchangeItem) && !isSynchronous && (CommunicationLinkProtocol.ACCEPT == exchangeItemAllocation.getReceiveProtocol())) {
            result.add(exchangeItemAllocation);
          } else if ((targetType instanceof ExchangeItem) && isSynchronous && (CommunicationLinkProtocol.READ == exchangeItemAllocation.getReceiveProtocol())) {
            result.add(exchangeItemAllocation);
          } else if ((targetType instanceof ExchangeItem) && !isSynchronous && (CommunicationLinkProtocol.UNSET == exchangeItemAllocation.getSendProtocol())) {
            result.add(exchangeItemAllocation);
          } else if (!result.contains(exchangeItemAllocation.getAllocatedItem())) {
            result.add(exchangeItemAllocation.getAllocatedItem());
          }
        }
        // Event ExchangeItem.
        else if (ExchangeMechanism.EVENT == exchangeItem.getExchangeMechanism()) {
          if ((sourceType instanceof Component) && (MessageKind.CREATE == messageKind)
              && (CommunicationLinkProtocol.UNSET == exchangeItemAllocation.getSendProtocol())) {
            result.add(exchangeItemAllocation);
          } else if ((sourceType instanceof ExchangeItem) && (CommunicationLinkProtocol.UNSET == exchangeItemAllocation.getReceiveProtocol())) {
            result.add(exchangeItemAllocation);
          } else if (!result.contains(exchangeItemAllocation.getAllocatedItem())) {
            result.add(exchangeItemAllocation.getAllocatedItem());
          }
        }
        // For other ExchangeItem types, add the ExchangeItem itself -> a new ExchangeItemAllocation will be created on it.
        else {
          if (!result.contains(exchangeItemAllocation.getAllocatedItem())) {
            result.add(exchangeItemAllocation.getAllocatedItem());
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns the available operation (in terms of affectation) for the given <code>SequenceMessage</code>.<br>
   * Resolves ExchangeItemAllocations using interfaces.
   * @param sequenceMessage the sequence message
   * @return a list of <code>Operation</code> instances
   */
  public List<CapellaElement> getAvailableExchangeItemsFromInterfaces(InstanceRole source, InstanceRole target, boolean isSynchronous) {
    ExchangeItem manipulatedItem = null;

    // A list to store the client used and required interfaces
    List<Interface> clientUsedAndRequiredInterfaces = new LinkedList<Interface>();
    // A list to store the provider implemented and provided interfaces
    List<Interface> providerImplementedAndProvidedInterfaces = new LinkedList<Interface>();

    // Gets the client and the provider of the sequence message
    AbstractInstance client = source == null ? null : source.getRepresentedInstance();
    AbstractInstance provider = target == null ? null : target.getRepresentedInstance();

    // Synchronous message involving a SharedData -> it's a READ -> switch client/provider.
    if (isSharedDataAccess(source, target) && isSynchronous) {
      AbstractInstance temp = client;
      client = provider;
      provider = temp;
    }

    // Builds the client and provider interfaces lists regarding their types
    if (client != null) {
      if (client.getAbstractType() instanceof Component) {
        for (Component component : ComponentExt.getAllAncestors((Component) client.getAbstractType())) {
          clientUsedAndRequiredInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(getUsedAndRequiredInterfaces(component)));
        }
      } else if (client.getAbstractType() instanceof ExchangeItem) {
        // The interfaces are all those containing a ExchangeItemAllocation to this EI
        List<EObject> lst = EObjectExt.getReferencers(client.getAbstractType(), CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
        for (EObject obj : lst) {
          Interface interf = (Interface) obj.eContainer();
          clientUsedAndRequiredInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(interf));
        }
        manipulatedItem = (ExchangeItem) client.getAbstractType();
      }
    }
    if (provider != null) {
      if (provider.getAbstractType() instanceof Component) {
        for (Component component : ComponentExt.getAllAncestors((Component) provider.getAbstractType())) {
          providerImplementedAndProvidedInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(getImplementedAndProvidedInterfaces(component)));
        }
      } else if (provider.getAbstractType() instanceof ExchangeItem) {
        // The interfaces are all those containing a ExchangeItemAllocation to this EI
        List<EObject> lst = EObjectExt.getReferencers(provider.getAbstractType(), CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
        for (EObject obj : lst) {
          Interface interf = (Interface) obj.eContainer();
          providerImplementedAndProvidedInterfaces.addAll(InterfaceExt.getAllSuperGeneralizableElements(interf));
        }
        manipulatedItem = (ExchangeItem) provider.getAbstractType();
      }
    }
    // Gets the architecture layer containing the sequence message

    // This is the list of the available operations the method will return
    List<CapellaElement> operations = new ArrayList<CapellaElement>();

    // The strategy is to show only the interfaces implemented and used by the client and the provider
    for (Interface currentInterface : clientUsedAndRequiredInterfaces) {
      if (providerImplementedAndProvidedInterfaces.contains(currentInterface)) {
        // if the current interface is one of the client used interfaces and one of the provider implemented interfaces
        for (ExchangeItemAllocation operation : currentInterface.getOwnedExchangeItemAllocations()) {
          // Look for every operation of the interface and if it is a service, then adds it to the returned list
          ExchangeItem ei = operation.getAllocatedItem();
          if ((manipulatedItem == null) || (ei == manipulatedItem)) {
            operations.add(operation);
          }
        }
      }
    }
    return operations;
  }
  
	public Collection<CapellaElement> getExchangeItemsFromCommunicationLinks(InstanceRole source, InstanceRole target, boolean isSynchronous) {
		// Gets the client and the provider of the sequence message
		Component client = null;
		Component provider = null;

		if (source != null && source.getRepresentedInstance().getAbstractType() instanceof Component) {
			client = (Component) source.getRepresentedInstance().getAbstractType();
		}
		if (target != null && target.getRepresentedInstance().getAbstractType() instanceof Component) {
			provider = (Component) target.getRepresentedInstance().getAbstractType();
		}

		// A synchronous message to SD: in inverse client / provider because it is a
		// READ.
		if (((client == null) || (provider == null)) && isSynchronous) {
			Component temp = client;
			client = provider;
			provider = temp;
		}

		if (client != null && provider != null) {
			HashSet<CapellaElement> clientSendItems = new HashSet<CapellaElement>((Collection<? extends CapellaElement>) CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getSenderCommunicationLink(client)));
			Collection<AbstractExchangeItem> providerReceiveItems = CommunicationLinkExt.getExchangeItems(CommunicationLinkExt.getReceiverCommunicationLink(provider));
			clientSendItems.retainAll(providerReceiveItems);
			return clientSendItems;
		} else {
			return new ArrayList<CapellaElement>();
		}
	}

  public List<CapellaElement> getAllExchangeItems(InstanceRole source, InstanceRole target, MessageKind messageKind) {
    if ((source != null) && (target != null)) {
      return ScenarioExt.getAvailableExchangeItemsBetweenInstances(source.getRepresentedInstance(), target.getRepresentedInstance(), messageKind);
    }
    return new ArrayList<CapellaElement>();
  }

  /**
   * Resolves ExchangeItemAllocatons using CommunicationLinks.
   * @param message
   * @return
   */
  public List<CapellaElement> getRestrictedExchangeItemsFromCommunicationLinks(InstanceRole source, InstanceRole target, boolean isSynchronous) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    // Gets the client and the provider of the sequence message
    Component client = null;
    Component provider = null;
    AbstractExchangeItem eiClient = null;

    if (source != null) {
      if (source.getRepresentedInstance().getAbstractType() instanceof Component) {
        client = (Component) source.getRepresentedInstance().getAbstractType();
      } else {
        eiClient = (AbstractExchangeItem) source.getRepresentedInstance().getAbstractType();
      }
    } else {
      client = null;
    }

    if (target != null) {
      if (target.getRepresentedInstance().getAbstractType() instanceof Component) {
        provider = (Component) target.getRepresentedInstance().getAbstractType();
      } else {
        eiClient = (AbstractExchangeItem) target.getRepresentedInstance().getAbstractType();
      }
    } else {
      provider = null;
    }

    // A synchronous message to SD: in inverse client / provider because it is a READ.
    if (((client == null) || (provider == null)) && isSynchronous) {
      Component temp = client;
      client = provider;
      provider = temp;
    }
    // Find potential ExchangeItems using communication links.
    List<AbstractExchangeItem> potentialEI = new ArrayList<AbstractExchangeItem>();
    if (provider != null) {
      for (CommunicationLink cl : provider.getOwnedCommunicationLinks()) {
        if ((cl.getKind() == CommunicationLinkKind.RECEIVE) || (cl.getKind() == CommunicationLinkKind.CONSUME)
            || (cl.getKind() == CommunicationLinkKind.EXECUTE) || (cl.getKind() == CommunicationLinkKind.ACCESS)) {
          if (CommunicationLinkKind.ACCESS == cl.getKind()) {
            CommunicationLinkProtocol protocol = cl.getProtocol();
            if (isSynchronous && (CommunicationLinkProtocol.READ == protocol)) {
              potentialEI.add(cl.getExchangeItem());
            }
            if (!isSynchronous && (CommunicationLinkProtocol.ACCEPT == protocol)) {
              potentialEI.add(cl.getExchangeItem());
            }
            if (CommunicationLinkProtocol.UNSET == protocol) {
              potentialEI.add(cl.getExchangeItem());
            }
          } else {
            potentialEI.add(cl.getExchangeItem());
          }
        }

      }
    } else if (eiClient != null) {
      // the provider is a ExchangeItem, so the potential is himself
      potentialEI.add((AbstractExchangeItem) target.getRepresentedInstance().getAbstractType());
    }

    if (client != null) {
      for (CommunicationLink cl : client.getOwnedCommunicationLinks()) {
        if ((cl.getKind() == CommunicationLinkKind.CALL) || (cl.getKind() == CommunicationLinkKind.PRODUCE) || (cl.getKind() == CommunicationLinkKind.SEND)
            || (cl.getKind() == CommunicationLinkKind.WRITE)) {
          if (potentialEI.contains(cl.getExchangeItem())) {
            CommunicationLinkProtocol protocol = cl.getProtocol();
            if (cl.getKind() == CommunicationLinkKind.CALL) {
              // complementary filtering synchronous/asynchronous at the communication link
              if (isSynchronous && (protocol == CommunicationLinkProtocol.SYNCHRONOUS)) {
                result.add(cl.getExchangeItem());
              }
              if (!isSynchronous && (protocol == CommunicationLinkProtocol.ASYNCHRONOUS)) {
                result.add(cl.getExchangeItem());
              }
              if (protocol == CommunicationLinkProtocol.UNSET) {
                result.add(cl.getExchangeItem());
              }
            } else {
              result.add(cl.getExchangeItem());
            }
          }
        }
      }
    } else {
      // the client is an exchaneItem, it must be the same than potentialEI
      for (AbstractExchangeItem abstractExchangeItem : potentialEI) {
        if (abstractExchangeItem == eiClient) {
          result.add((CapellaElement) eiClient);
          // communication pattern : we add EIA that point to this EI
          List<EObject> eiaPotentials = EObjectExt.getReferencers(abstractExchangeItem, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
          for (EObject eObject : eiaPotentials) {
            if (eObject instanceof ExchangeItemAllocation) {
              ExchangeItemAllocation eia = (ExchangeItemAllocation) eObject;
              result.add(eia);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * @param message
   * @return
   */
  public static boolean isSharedDataAccess(SequenceMessage message) {
    if (message.getSendingEnd() == null) {
      return false;
    }
    if (message.getReceivingEnd() == null) {
      return false;
    }
    return isSharedDataAccess(message.getSendingEnd().getCovered(), message.getReceivingEnd().getCovered());
  }


}
