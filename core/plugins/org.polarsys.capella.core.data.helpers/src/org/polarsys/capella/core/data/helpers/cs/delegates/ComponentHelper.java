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

  public Object doSwitch(Component element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.COMPONENT__ALLOCATED_COMPONENTS)) {
      ret = getAllocatedComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__ALLOCATING_COMPONENTS)) {
      ret = getAllocatingComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS)) {
      ret = getProvisionedComponentAllocations(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS)) {
      ret = getProvisioningComponentAllocations(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES)) {
      ret = getProvidedInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES)) {
      ret = getRequiredInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES)) {
      ret = getImplementedInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__USED_INTERFACES)) {
      ret = getUsedInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS)) {
      ret = getImplementedInterfaceLinks(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS)) {
      ret = getUsedInterfaceLinks(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS)) {
      ret = getContainedComponentPorts(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__CONTAINED_PARTS)) {
      ret = getContainedParts(element_p);
    } else if (feature_p.equals(CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS)) {
      ret = getContainedPhysicalPorts(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionalBlockHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = PartitionableElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = CommunicationLinkExchangerHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<ComponentAllocation> getProvisionedComponentAllocations(Component element_p) {
    List<ComponentAllocation> ret = new ArrayList<ComponentAllocation>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof ComponentAllocation) {
        ret.add((ComponentAllocation) trace);
      }
    }
    return ret;
  }

  protected List<ComponentAllocation> getProvisioningComponentAllocations(Component element_p) {
    List<ComponentAllocation> ret = new ArrayList<ComponentAllocation>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ComponentAllocation) {
        ret.add((ComponentAllocation) trace);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatedComponents(Component element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentAllocation componentAllocation : element_p.getProvisionedComponentAllocations()) {
      Component cpnt = componentAllocation.getAllocatedComponent();
      if (null != cpnt) {
        ret.add(cpnt);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatingComponents(Component element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentAllocation componentAllocation : element_p.getProvisioningComponentAllocations()) {
      Component cpnt = componentAllocation.getAllocatingComponent();
      if (null != cpnt) {
        ret.add(cpnt);
      }
    }
    return ret;
  }

  protected List<Interface> getProvidedInterfaces(Component element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (Partition partition : element_p.getOwnedPartitions()) {
      if (partition instanceof ComponentPort) {
        ret.addAll(((ComponentPort) partition).getProvidedInterfaces());
      }
    }
    return ret;
  }

  protected List<Interface> getRequiredInterfaces(Component element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (Partition partition : element_p.getOwnedPartitions()) {
      if (partition instanceof ComponentPort) {
        ret.addAll(((ComponentPort) partition).getRequiredInterfaces());
      }
    }
    return ret;
  }

  protected List<InterfaceImplementation> getImplementedInterfaceLinks(Component element_p) {
    return element_p.getOwnedInterfaceImplementations();
  }

  protected List<InterfaceUse> getUsedInterfaceLinks(Component element_p) {
    return element_p.getOwnedInterfaceUses();
  }

  protected List<Interface> getImplementedInterfaces(Component element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (InterfaceImplementation interfaceImplementation : element_p.getImplementedInterfaceLinks()) {
      Interface itf = interfaceImplementation.getImplementedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<Interface> getUsedInterfaces(Component element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (InterfaceUse interfaceUse : element_p.getUsedInterfaceLinks()) {
      Interface itf = interfaceUse.getUsedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<ComponentPort> getContainedComponentPorts(Component element_p) {
    List<ComponentPort> ret = new ArrayList<ComponentPort>();
    for (Feature feature : element_p.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ret.add((ComponentPort) feature);
      }
    }
    return ret;
  }

  protected List<Part> getContainedParts(Component element_p) {
    List<Part> ret = new ArrayList<Part>();
    for (Feature feature : element_p.getOwnedFeatures()) {
      if (feature instanceof Part) {
        ret.add((Part) feature);
      }
    }
    return ret;
  }

  protected List<PhysicalPort> getContainedPhysicalPorts(Component element_p) {
    List<PhysicalPort> ret = new ArrayList<PhysicalPort>();
    for (Feature feature : element_p.getOwnedFeatures()) {
      if (feature instanceof PhysicalPort) {
        ret.add((PhysicalPort) feature);
      }
    }
    return ret;
  }
}
