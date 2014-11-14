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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class InterfaceHelper {
  private static InterfaceHelper instance;
  /**
   * Cross referencing re-entrance collection for components user.
   */
  private List<Interface> _isCrossReferencingUser;
  /**
   * Cross referencing re-entrance collection for components implementor.
   */
  private List<Interface> _isCrossReferencingImplementor;

  private InterfaceHelper() {
    _isCrossReferencingUser = new ArrayList<Interface>(0);
    _isCrossReferencingImplementor = new ArrayList<Interface>(0);
  }

  public static InterfaceHelper getInstance() {
    if (instance == null) {
      instance = new InterfaceHelper();
    }
    return instance;
  }

  public Object doSwitch(Interface element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.INTERFACE__ALLOCATING_INTERFACES)) {
      ret = getAllocatingInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__ALLOCATING_COMPONENTS)) {
      ret = getAllocatingComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__IMPLEMENTOR_COMPONENTS)) {
      ret = getImplementorComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__USER_COMPONENTS)) {
      ret = getUserComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS)) {
      ret = getProvisioningInterfaceAllocations(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__INTERFACE_IMPLEMENTATIONS)) {
      ret = getInterfaceImplementations(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__INTERFACE_USES)) {
      ret = getInterfaceUses(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__EXCHANGE_ITEMS)) {
      ret = getExchangeItems(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REQUIRING_COMPONENTS)) {
      ret = getRequiringComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__PROVIDING_COMPONENTS)) {
      ret = getProvidingComponents(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REQUIRING_COMPONENT_PORTS)) {
      ret = getRequiringComponentPorts(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__PROVIDING_COMPONENT_PORTS)) {
      ret = getProvidingComponentPorts(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REALIZING_LOGICAL_INTERFACES)) {
      ret = getRealizingLogicalInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REALIZED_CONTEXT_INTERFACES)) {
      ret = getRealizedContextInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REALIZING_PHYSICAL_INTERFACES)) {
      ret = getRealizingPhysicalInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.INTERFACE__REALIZED_LOGICAL_INTERFACES)) {
      ret = getRealizedLogicalInterfaces(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = GeneralClassHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  /**
   * Gets the exchange items.
   *
   * @param element_p the given interface
   * @return the exchange items
   */
  protected List<ExchangeItem> getExchangeItems(Interface element_p) {
    List<ExchangeItem> ret = new ArrayList<ExchangeItem>();
    for (ExchangeItemAllocation exchangeItemAllocation : element_p.getOwnedExchangeItemAllocations()) {
      ExchangeItem item = exchangeItemAllocation.getAllocatedItem();
      if (null != item) {
        ret.add(item);
      }
    }
    return ret;
  }

  protected boolean isCrossReferencing(Interface element_p, boolean user_p) {
    return user_p ? _isCrossReferencingUser.contains(element_p) : _isCrossReferencingImplementor.contains(element_p);
  }

  protected void markAsCrossReferenced(Interface element_p, boolean user_p) {
    if (user_p) {
      _isCrossReferencingUser.add(element_p);
    } else {
      _isCrossReferencingImplementor.add(element_p);
    }
  }

  protected void unmarkAsCrossReferenced(Interface element_p, boolean user_p) {
    if (user_p) {
      _isCrossReferencingUser.remove(element_p);
    } else {
      _isCrossReferencingImplementor.remove(element_p);
    }
  }

  protected List<InterfaceImplementation> getInterfaceImplementations(Interface element_p) {
    List<InterfaceImplementation> ret = new ArrayList<InterfaceImplementation>();

    if (!isCrossReferencing(element_p, false)) {
      try {
        markAsCrossReferenced(element_p, false);

        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE.equals(setting.getEStructuralFeature())) {
              ret.add((InterfaceImplementation) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, false);
      }
    }

    return ret;
  }

  protected List<InterfaceUse> getInterfaceUses(Interface element_p) {
    List<InterfaceUse> ret = new ArrayList<InterfaceUse>();

    if (!isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, true);
        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CsPackage.Literals.INTERFACE_USE__USED_INTERFACE.equals(setting.getEStructuralFeature())) {
              ret.add((InterfaceUse) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, true);
      }
    }

    return ret;
  }

  protected List<Component> getImplementorComponents(Interface element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (InterfaceImplementation interfaceImplementation : element_p.getInterfaceImplementations()) {
      Component interfaceImplementor = interfaceImplementation.getInterfaceImplementor();
      if (null != interfaceImplementor) {
        ret.add(interfaceImplementor);
      }
    }
    return ret;
  }

  protected List<Component> getUserComponents(Interface element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (InterfaceUse interfaceUse : element_p.getInterfaceUses()) {
      Component interfaceUser = interfaceUse.getInterfaceUser();
      if (null != interfaceUser) {
        ret.add(interfaceUser);
      }
    }
    return ret;
  }

  protected List<Interface> getAllocatingInterfaces(Interface element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (InterfaceAllocation interfaceAllocation : element_p.getProvisionedInterfaceAllocations()) {
      InterfaceAllocator allocator = interfaceAllocation.getAllocatingInterfaceAllocator();
      if (allocator instanceof Interface) {
        ret.add((Interface) allocator);
      }
    }
    return ret;
  }

  protected List<Component> getAllocatingComponents(Interface element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (InterfaceAllocation interfaceAllocation : element_p.getProvisionedInterfaceAllocations()) {
      InterfaceAllocator allocator = interfaceAllocation.getAllocatingInterfaceAllocator();
      if (allocator instanceof Component) {
        ret.add((Component) allocator);
      }
    }
    return ret;
  }

  protected List<InterfaceAllocation> getProvisioningInterfaceAllocations(Interface element_p) {
    List<InterfaceAllocation> ret = new ArrayList<InterfaceAllocation>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof InterfaceAllocation) {
        ret.add((InterfaceAllocation) trace);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Component> getRequiringComponents(Interface element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentPort port : element_p.getRequiringComponentPorts()) {
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
  protected List<Component> getProvidingComponents(Interface element_p) {
    List<Component> ret = new ArrayList<Component>();
    for (ComponentPort port : element_p.getProvidingComponentPorts()) {
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
  protected List<ComponentPort> getRequiringComponentPorts(Interface element_p) {
    List<ComponentPort> ret = new ArrayList<ComponentPort>();
    for (EObject obj : EObjectExt.getReferencers(element_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (obj instanceof ComponentPort) {
        ret.add((ComponentPort) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<ComponentPort> getProvidingComponentPorts(Interface element_p) {
    List<ComponentPort> ret = new ArrayList<ComponentPort>();
    for (EObject obj : EObjectExt.getReferencers(element_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (obj instanceof ComponentPort) {
        ret.add((ComponentPort) obj);
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Interface> getRealizingLogicalInterfaces(Interface element_p) {
    List<Interface> result = new ArrayList<Interface>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
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
  protected List<Interface> getRealizedContextInterfaces(Interface element_p) {
    List<Interface> result = new ArrayList<Interface>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
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
  protected List<Interface> getRealizingPhysicalInterfaces(Interface element_p) {
    List<Interface> result = new ArrayList<Interface>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
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
  protected List<Interface> getRealizedLogicalInterfaces(Interface element_p) {
    List<Interface> result = new ArrayList<Interface>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
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