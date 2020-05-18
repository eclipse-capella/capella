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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeMechanismExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.Messages;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;

public class CreationAlgorithms {

  public static final CreationAlgorithms INSTANCE = new CreationAlgorithms();

  protected CompatibilityDefinition compatibilityDef;

  public CreationAlgorithms() {
    compatibilityDef = CompatibilityDefinition.INSTANCE;
  }

  /**
   * Create an {@link Interface} for related to given sequence message with specified name.
   * 
   * @param sequenceMessage
   * @param interfaceName
   */
  public Interface createInterface(String interfaceName, InstanceRole sourceIR, InstanceRole targetIR,
      boolean isStructural) {
    Interface result = CsFactory.eINSTANCE.createInterface(interfaceName);
    result.setStructural(isStructural);

    EObject src = sourceIR != null ? sourceIR.getRepresentedInstance().eContainer() : null;
    EObject tgt = targetIR != null ? targetIR.getRepresentedInstance().eContainer() : null;

    EObject container = null;

    if (src instanceof Component && 
        tgt instanceof Component &&
        !((Component) src).isActor() &&
        !((Component) tgt).isActor()) {
      container = ComponentExt.getFirstCommonComponentOrPkgAncestor(src, tgt);
    }
    if(src instanceof SystemComponentPkg && tgt instanceof SystemComponentPkg) {
      container = ComponentExt.getRootBlockArchitecture((SystemComponentPkg) src);
    }
    if (sourceIR != null && ComponentExt.isActor(sourceIR.getRepresentedInstance())) {
      container = ComponentExt.getRootBlockArchitecture(sourceIR);
    } else if (targetIR != null && ComponentExt.isActor(targetIR.getRepresentedInstance())) {
      container = ComponentExt.getRootBlockArchitecture(targetIR);
    }

    // Retrieve or create an interface pkg into the container
    EReference referenceInterfacePkg = null;
    if (container instanceof BlockArchitecture) {
      referenceInterfacePkg = CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG;
    } else {
      referenceInterfacePkg = CsPackage.Literals.BLOCK__OWNED_INTERFACE_PKG;
    }

    if (container != null && container.eGet(referenceInterfacePkg) == null) {
      container.eSet(referenceInterfacePkg,
          CsFactory.eINSTANCE.createInterfacePkg(Messages.SelectOperationDialog_InterfacePkgName8));
    }

    // Set the interface into the pkg
    if(container !=  null) {
      InterfacePkg pkg = (InterfacePkg) container.eGet(referenceInterfacePkg);
      pkg.getOwnedInterfaces().add(result);      
    }

    org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(result);
    result.setName(interfaceName);

    return result;
  }

  /**
   * Create a new operation based on values entered by the end-user.
   * 
   * @return
   */
  // name = _operationText.getText()
  public ExchangeItem createExchangeItem(String name, Interface itf, ExchangeMechanism exchangeMechanism) {
    ExchangeItem result = InformationFactory.eINSTANCE.createExchangeItem();
    // the ei must be by default in the same package as the allocating
    // interface
    EObject container = itf.eContainer();
    if (container instanceof InterfacePkg) {
      InterfacePkg ipkg = (InterfacePkg) container;
      ipkg.getOwnedExchangeItems().add(result);
    } else {
      InterfaceExt.getRootOwnerInterfacePkg(itf).getOwnedExchangeItems().add(result);
    }
    result.setExchangeMechanism(exchangeMechanism);
    org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(result);
    result.setName(name);
    return result;
  }

  /**
   * Allocate the exchange item to the interface selected
   * 
   * @param getAnExchangeItemSelected
   * @return
   */
  public ExchangeItemAllocation createExchangeItemAllocationAndAllocateExchangeItem(ExchangeItem exchangeItem,
      Interface interface_, MessageKind _messageKind) {
    ExchangeItemAllocation result = InterfaceExt.addExchangeItem(interface_, exchangeItem);
    if (_messageKind == MessageKind.SYNCHRONOUS_CALL) {
      result.setSendProtocol(CommunicationLinkProtocol.SYNCHRONOUS);
    }
    if (_messageKind == MessageKind.ASYNCHRONOUS_CALL) {
      result.setSendProtocol(CommunicationLinkProtocol.ASYNCHRONOUS);
    }
    if (_messageKind == MessageKind.CREATE) {
      result.setSendProtocol(CommunicationLinkProtocol.BROADCAST);
    }
    return result;
  }

  public boolean isGoodInterface(CapellaElement capellaElement, InstanceRole sourceIR, InstanceRole targetIR) {
    Interface interf = (Interface) capellaElement;
    AbstractType src = sourceIR == null ? null : sourceIR.getRepresentedInstance().getAbstractType();
    AbstractType tgt = targetIR == null ? null : targetIR.getRepresentedInstance().getAbstractType();
    Component srcComp = null;
    Component tgtComp = null;
    ExchangeItem ei = null;

    if (src instanceof Component) {
      srcComp = (Component) src;
    } else {
      ei = (ExchangeItem) src;
    }

    if (tgt instanceof Component) {
      tgtComp = (Component) tgt;
    } else {
      ei = (ExchangeItem) tgt;
    }

    if (ei == null) {
      return ComponentExt.isImplementingInterface(tgtComp, interf) && ComponentExt.isUsingInterface(srcComp, interf);
      // the interface is OK if it already allocates the EI
    }

    for (ExchangeItemAllocation eia : interf.getOwnedExchangeItemAllocations()) {
      if (eia.getAllocatedItem() == ei) {
        return true;
      }
    }
    return false;
  }

  // component-> component default name is "source to target"
  public String getDefaultInterfaceName(InstanceRole sourceIR, InstanceRole targetIR,
      List<Interface> existingInterfaces) {
    StringBuilder builder = new StringBuilder();
    if ((sourceIR != null) && (sourceIR.getRepresentedInstance() instanceof ExchangeItemInstance)) {
      builder.append(sourceIR.getName());
      builder.append("_Interface"); //$NON-NLS-1$
    } else if ((targetIR != null) && (targetIR.getRepresentedInstance() instanceof ExchangeItemInstance)) {
      builder.append(targetIR.getName());
      builder.append("_Interface"); //$NON-NLS-1$
    } else {
      if ((sourceIR != null) && (null != sourceIR.getRepresentedInstance())) {
        AbstractType abstractType = sourceIR.getRepresentedInstance().getAbstractType();
        if (null != abstractType) {
          builder.append(abstractType.getName());
        } else {
          builder.append(sourceIR.getName());
        }
      }
      if ((sourceIR != null) && (targetIR != null)) {
        builder.append("_to_"); //$NON-NLS-1$
      }
      if ((targetIR != null) && (null != targetIR.getRepresentedInstance())) {
        AbstractType abstractType = targetIR.getRepresentedInstance().getAbstractType();
        if (null != abstractType) {
          builder.append(abstractType.getName());
        } else {
          builder.append(targetIR.getName());
        }
      }
    }
    String baseName = builder.toString();
    String name = baseName;
    int index = 1;
    while (interfaceExistWithName(name, existingInterfaces)) {
      name = baseName + "_" + index; //$NON-NLS-1$
      index++;
    }
    return name;
  }

  public boolean interfaceExistWithName(String name, List<Interface> allInterfacesInBetweenSourceAndTarget) {
    for (Interface interfaze : allInterfacesInBetweenSourceAndTarget) {
      if (interfaze.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /* Get or create the allocation for the given commmunication */
  public ExchangeItemAllocation getOrCreateAllocation(AbstractCommunication communication, MessageKind messageKind,
      boolean withReturn) {
    // if the allocation is not already defined, we try to find one
    ExchangeMechanism mechanism = communication.exchangeItem.getExchangeMechanism();
    CommunicationLinkProtocol defaultSenderProtocol = compatibilityDef.getCompatibleProtocol(true, mechanism,
        messageKind, withReturn);
    CommunicationLinkProtocol defaultReceiverProtocol = compatibilityDef.getCompatibleProtocol(false, mechanism,
        messageKind, withReturn);
    ExchangeItemAllocation allocation = null;
    for (ExchangeItemAllocation existingAllocation : communication.interfaze.getOwnedExchangeItemAllocations()) {
      ExchangeItem item = existingAllocation.getAllocatedItem();
      if (item == communication.exchangeItem) {
        boolean isCandidate = true;
        if (communication.senderLink != null) {
          isCandidate = existingAllocation.getSendProtocol() == communication.senderLink.getProtocol();
        } else {
          isCandidate = existingAllocation.getSendProtocol() == defaultSenderProtocol;
        }
        if (isCandidate) {
          if (communication.receiverLink != null) {
            isCandidate = existingAllocation.getReceiveProtocol() == communication.receiverLink.getProtocol();
          } else {
            isCandidate = existingAllocation.getReceiveProtocol() == defaultReceiverProtocol;
          }
          if (isCandidate) {
            allocation = existingAllocation;
            break;
          }
        }
      }
    }
    // if no compatible allocation has been found, we create it
    if (allocation == null) {
      allocation = CsFactory.eINSTANCE.createExchangeItemAllocation();
      allocation.setAllocatedItem(communication.exchangeItem);
      communication.interfaze.getOwnedExchangeItemAllocations().add(allocation);
      if (communication.senderLink != null) {
        allocation.setSendProtocol(communication.senderLink.getProtocol());
      } else {
        allocation.setSendProtocol(defaultSenderProtocol);
      }
      if (communication.receiverLink != null) {
        allocation.setReceiveProtocol(communication.receiverLink.getProtocol());
      } else {
        allocation.setReceiveProtocol(defaultReceiverProtocol);
      }
      CapellaElementExt.creationService(allocation);
    }
    return allocation;
  }

  /*
   * Create (if not exists) the "uses" and "implements" links between components and interface of the communication
   */
  public void updateInterfaceCommunication(AbstractCommunication communication) {
    Component source = communication.source;
    Component target = communication.target;
    Interface interfaze = communication.interfaze;
    if (!ComponentExt.getAllUsedAndRequiredInterfaces(source).contains(interfaze)) {
      InterfaceUse use = CsFactory.eINSTANCE.createInterfaceUse();
      use.setUsedInterface(interfaze);
      source.getOwnedInterfaceUses().add(use);
      CapellaElementExt.creationService(use);
    }
    if (!ComponentExt.getAllImplementedAndProvidedInterfaces(target).contains(interfaze)) {
      InterfaceImplementation implementation = CsFactory.eINSTANCE.createInterfaceImplementation();
      implementation.setImplementedInterface(interfaze);
      target.getOwnedInterfaceImplementations().add(implementation);
      CapellaElementExt.creationService(implementation);
    }
  }

  /*
   * Update/Create communication links according to the protocols defined by the exchange item allocation of the
   * communication.
   */
  public void updateLinkCommunication(AbstractCommunication communication, MessageKind messageKind,
      boolean withReturn) {
    // create or update the sender
    CommunicationLink sender = communication.senderLink;
    if (sender == null) {
      sender = CommunicationFactory.eINSTANCE.createCommunicationLink();
      sender.setExchangeItem(communication.exchangeItem);
      sender.setKind(ExchangeMechanismExt.getKind(communication.exchangeItem.getExchangeMechanism(), true));
      communication.source.getOwnedCommunicationLinks().add(sender);
      org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(sender);
      communication.senderLink = sender;
    }
    sender.setProtocol(communication.exchangeItemAllocation.getSendProtocol());
    // create or update the receiver
    CommunicationLink receiver = communication.receiverLink;
    if (receiver == null) {
      receiver = CommunicationFactory.eINSTANCE.createCommunicationLink();
      receiver.setExchangeItem(communication.exchangeItem);
      receiver.setKind(ExchangeMechanismExt.getKind(communication.exchangeItem.getExchangeMechanism(), false));
      communication.target.getOwnedCommunicationLinks().add(receiver);
      org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(receiver);
      communication.receiverLink = receiver;
    }
    receiver.setProtocol(communication.exchangeItemAllocation.getReceiveProtocol());
  }
}
