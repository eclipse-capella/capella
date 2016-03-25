/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionalBlockHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkExchangerHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionableElementHelper;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class ComponentHelper {
  private static ComponentHelper instance;

  private ComponentHelper() {
    // do nothing
  }

  public static ComponentHelper getInstance() {
    if (instance == null) {
      instance = new ComponentHelper();
    }
    return instance;
  }

  public Object doSwitch(Component element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.COMPONENT__ALLOCATED_COMPONENTS)) {
      ret = getAllocatedComponents(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__ALLOCATING_COMPONENTS)) {
      ret = getAllocatingComponents(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS)) {
      ret = getProvisionedComponentAllocations(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS)) {
      ret = getProvisioningComponentAllocations(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES)) {
      ret = getProvidedInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES)) {
      ret = getRequiredInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES)) {
      ret = getImplementedInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__USED_INTERFACES)) {
      ret = getUsedInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS)) {
      ret = getImplementedInterfaceLinks(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS)) {
      ret = getUsedInterfaceLinks(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS)) {
      ret = getContainedComponentPorts(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__CONTAINED_PARTS)) {
      ret = getContainedParts(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS)) {
      ret = getContainedPhysicalPorts(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionalBlockHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = PartitionableElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = CommunicationLinkExchangerHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ComponentAllocation> getProvisionedComponentAllocations(Component element) {
    List<ComponentAllocation> ret = new ArrayList<ComponentAllocation>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentAllocation) {
        ret.add((ComponentAllocation) trace);
      }
    }
    return ret;
  }

  protected List<ComponentAllocation> getProvisioningComponentAllocations(Component element) {
    List<ComponentAllocation> ret = new ArrayList<ComponentAllocation>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentAllocation) {
        ret.add((ComponentAllocation) trace);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatedComponents(Component element) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
      Component cpnt = componentAllocation.getAllocatedComponent();
      if (null != cpnt) {
        ret.add(cpnt);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatingComponents(Component element) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentAllocation componentAllocation : element.getProvisioningComponentAllocations()) {
      Component cpnt = componentAllocation.getAllocatingComponent();
      if (null != cpnt) {
        ret.add(cpnt);
      }
    }
    return ret;
  }

  protected List<Interface> getProvidedInterfaces(Component element) {
    List<Interface> ret = new ArrayList<Interface>();
    for (Partition partition : element.getOwnedPartitions()) {
      if (partition instanceof ComponentPort) {
        ret.addAll(((ComponentPort) partition).getProvidedInterfaces());
      }
    }
    return ret;
  }

  protected List<Interface> getRequiredInterfaces(Component element) {
    List<Interface> ret = new ArrayList<Interface>();
    for (Partition partition : element.getOwnedPartitions()) {
      if (partition instanceof ComponentPort) {
        ret.addAll(((ComponentPort) partition).getRequiredInterfaces());
      }
    }
    return ret;
  }

  protected List<InterfaceImplementation> getImplementedInterfaceLinks(Component element) {
    return element.getOwnedInterfaceImplementations();
  }

  protected List<InterfaceUse> getUsedInterfaceLinks(Component element) {
    return element.getOwnedInterfaceUses();
  }

  protected List<Interface> getImplementedInterfaces(Component element) {
    List<Interface> ret = new ArrayList<Interface>();
    for (InterfaceImplementation interfaceImplementation : element.getImplementedInterfaceLinks()) {
      Interface itf = interfaceImplementation.getImplementedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<Interface> getUsedInterfaces(Component element) {
    List<Interface> ret = new ArrayList<Interface>();
    for (InterfaceUse interfaceUse : element.getUsedInterfaceLinks()) {
      Interface itf = interfaceUse.getUsedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<ComponentPort> getContainedComponentPorts(Component element) {
    List<ComponentPort> ret = new ArrayList<ComponentPort>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ret.add((ComponentPort) feature);
      }
    }
    return ret;
  }

  protected List<Part> getContainedParts(Component element) {
    List<Part> ret = new ArrayList<Part>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        ret.add((Part) feature);
      }
    }
    return ret;
  }

  protected List<PhysicalPort> getContainedPhysicalPorts(Component element) {
    List<PhysicalPort> ret = new ArrayList<PhysicalPort>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof PhysicalPort) {
        ret.add((PhysicalPort) feature);
      }
    }
    return ret;
  }
}
