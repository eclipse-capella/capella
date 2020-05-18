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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

public class SelectInvokedOperationModelForSharedDataAndEvent {

  /**
   * Returns the available operation (in terms of affectation) for the given <code>SequenceMessage</code>.<br>
   * Resolves ExchangeItemAllocations using interfaces.
   * @param sequenceMessage the sequence message
   * @return a list of <code>Operation</code> instances
   */
  public static  List<CapellaElement> getAvailableExchangeItems(InstanceRole source, InstanceRole target, boolean isSynchronous) {
    ExchangeItem manipulatedItem = null;

    // A list to store the client used and required interfaces
    List<Interface> clientUsedAndRequiredInterfaces = new LinkedList<Interface>();
    // A list to store the provider implemented and provided interfaces
    List<Interface> providerImplementedAndProvidedInterfaces = new LinkedList<Interface>();

    // Gets the client and the provider of the sequence message
    AbstractInstance client = source == null ? null : source.getRepresentedInstance();
    AbstractInstance provider = target == null ? null : target.getRepresentedInstance();

    // Synchronous message involving a SharedData -> it's a READ -> switch client/provider.
    if (InterfaceHelper.isSharedDataAccess(source, target) && isSynchronous) {
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
  
  public static List<CapellaElement> getRestrictedExchangeItems(InstanceRole source, InstanceRole target, boolean isSynchronous) {
    return ScenarioExt.getRestrictedExchangeItems(source, target, isSynchronous);
  }

  
  private static List<Interface> getUsedAndRequiredInterfaces(Component component) {
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

  private static List<Interface> getImplementedAndProvidedInterfaces(Component component) {
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
}
