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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralClassHelper;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;

public class InterfaceHelper {
  private static InterfaceHelper instance;

  private InterfaceHelper() {
  }

  public static InterfaceHelper getInstance() {
    if (instance == null) {
      instance = new InterfaceHelper();
    }
    return instance;
  }

  public Object doSwitch(Interface element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.INTERFACE__ALLOCATING_INTERFACES)) {
      ret = getAllocatingInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__ALLOCATING_COMPONENTS)) {
      ret = getAllocatingComponents(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__IMPLEMENTOR_COMPONENTS)) {
      ret = getImplementorComponents(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__USER_COMPONENTS)) {
      ret = getUserComponents(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS)) {
      ret = getProvisioningInterfaceAllocations(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__INTERFACE_IMPLEMENTATIONS)) {
      ret = getInterfaceImplementations(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__INTERFACE_USES)) {
      ret = getInterfaceUses(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__EXCHANGE_ITEMS)) {
      ret = getExchangeItems(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REQUIRING_COMPONENTS)) {
      ret = getRequiringComponents(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__PROVIDING_COMPONENTS)) {
      ret = getProvidingComponents(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REQUIRING_COMPONENT_PORTS)) {
      ret = getRequiringComponentPorts(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__PROVIDING_COMPONENT_PORTS)) {
      ret = getProvidingComponentPorts(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REALIZING_LOGICAL_INTERFACES)) {
      ret = getRealizingLogicalInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REALIZED_CONTEXT_INTERFACES)) {
      ret = getRealizedContextInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REALIZING_PHYSICAL_INTERFACES)) {
      ret = getRealizingPhysicalInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.INTERFACE__REALIZED_LOGICAL_INTERFACES)) {
      ret = getRealizedLogicalInterfaces(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = GeneralClassHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  /**
   * Gets the exchange items.
   * @param element the given interface
   * @return the exchange items
   */
  protected List<ExchangeItem> getExchangeItems(Interface element) {
    List<ExchangeItem> ret = new ArrayList<>();
    for (ExchangeItemAllocation exchangeItemAllocation : element.getOwnedExchangeItemAllocations()) {
      ExchangeItem item = exchangeItemAllocation.getAllocatedItem();
      if (null != item) {
        ret.add(item);
      }
    }
    return ret;
  }

  protected List<InterfaceImplementation> getInterfaceImplementations(Interface element) {
    return EObjectExt.getReferencers(element, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);
  }

  protected List<InterfaceUse> getInterfaceUses(Interface element) {
    return EObjectExt.getReferencers(element, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE);
  }

  protected List<Component> getImplementorComponents(Interface element) {
    List<Component> ret = new ArrayList<>();
    for (InterfaceImplementation interfaceImplementation : element.getInterfaceImplementations()) {
      Component interfaceImplementor = interfaceImplementation.getInterfaceImplementor();
      if (null != interfaceImplementor) {
        ret.add(interfaceImplementor);
      }
    }
    return ret;
  }

  protected List<Component> getUserComponents(Interface element) {
    List<Component> ret = new ArrayList<>();
    for (InterfaceUse interfaceUse : element.getInterfaceUses()) {
      Component interfaceUser = interfaceUse.getInterfaceUser();
      if (null != interfaceUser) {
        ret.add(interfaceUser);
      }
    }
    return ret;
  }

  protected List<Interface> getAllocatingInterfaces(Interface element) {
    List<Interface> ret = new ArrayList<>();
    for (InterfaceAllocation interfaceAllocation : element.getProvisioningInterfaceAllocations()) {
      InterfaceAllocator allocator = interfaceAllocation.getAllocatingInterfaceAllocator();
      if (allocator instanceof Interface) {
        ret.add((Interface) allocator);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatingComponents(Interface element) {
    List<Component> ret = new ArrayList<>();
    for (InterfaceAllocation interfaceAllocation : element.getProvisioningInterfaceAllocations()) {
      InterfaceAllocator allocator = interfaceAllocation.getAllocatingInterfaceAllocator();
      if (allocator instanceof Component) {
        ret.add((Component) allocator);
      }
    }
    return ret;
  }

  protected List<InterfaceAllocation> getProvisioningInterfaceAllocations(Interface element) {
    List<InterfaceAllocation> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof InterfaceAllocation) {
        ret.add((InterfaceAllocation) trace);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Component> getRequiringComponents(Interface element) {
    List<Component> ret = new ArrayList<>();
    for (ComponentPort port : element.getRequiringComponentPorts()) {
      EObject obj = port.eContainer();
      if (obj instanceof Component) {
        ret.add((Component) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Component> getProvidingComponents(Interface element) {
    List<Component> ret = new ArrayList<>();
    for (ComponentPort port : element.getProvidingComponentPorts()) {
      EObject obj = port.eContainer();
      if (obj instanceof Component) {
        ret.add((Component) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<ComponentPort> getRequiringComponentPorts(Interface element) {
    List<ComponentPort> ret = new ArrayList<>();
    for (EObject obj : EObjectExt.getReferencers(element, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (obj instanceof ComponentPort) {
        ret.add((ComponentPort) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<ComponentPort> getProvidingComponentPorts(Interface element) {
    List<ComponentPort> ret = new ArrayList<>();
    for (EObject obj : EObjectExt.getReferencers(element, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (obj instanceof ComponentPort) {
        ret.add((ComponentPort) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Interface> getRealizingLogicalInterfaces(Interface element) {
    List<Interface> result = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ContextInterfaceRealization) {
        TraceableElement src = ((ContextInterfaceRealization) trace).getSourceElement();
        if (src instanceof Interface) {
          result.add((Interface) src);
        }
      }
    }
    return result;
  }

  /**
   *
   */
  protected List<Interface> getRealizedContextInterfaces(Interface element) {
    List<Interface> result = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ContextInterfaceRealization) {
        TraceableElement src = ((ContextInterfaceRealization) trace).getTargetElement();
        if (src instanceof Interface) {
          result.add((Interface) src);
        }
      }
    }
    return result;
  }

  /**
   *
   */
  protected List<Interface> getRealizingPhysicalInterfaces(Interface element) {
    List<Interface> result = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof LogicalInterfaceRealization) {
        TraceableElement src = ((LogicalInterfaceRealization) trace).getSourceElement();
        if (src instanceof Interface) {
          result.add((Interface) src);
        }
      }
    }
    return result;
  }

  /**
   *
   */
  protected List<Interface> getRealizedLogicalInterfaces(Interface element) {
    List<Interface> result = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof LogicalInterfaceRealization) {
        TraceableElement src = ((LogicalInterfaceRealization) trace).getTargetElement();
        if (src instanceof Interface) {
          result.add((Interface) src);
        }
      }
    }
    return result;
  }
}