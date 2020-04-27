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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.ClassifierHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionalBlockHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.CommunicationLinkExchangerHelper;

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

    if (feature.equals(CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS)) {
      ret = getRealizedComponents(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT__REALIZING_COMPONENTS)) {
      ret = getRealizingComponents(element);
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
    } else if (feature.equals(CsPackage.Literals.COMPONENT__REPRESENTING_PARTS)) {
      ret = getRepresentingParts(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractFunctionalBlockHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = ClassifierHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InterfaceAllocatorHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = CommunicationLinkExchangerHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<Component> getRealizedComponents(Component element) {
    List<Component> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentRealization) {
        Component cpnt = ((ComponentRealization) trace).getRealizedComponent();
        if (null != cpnt) {
          ret.add(cpnt);
        }
      }
    }
    return ret;
  }

  protected List<Component> getRealizingComponents(Component element) {
    List<Component> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentRealization) {
        Component cpnt = ((ComponentRealization) trace).getRealizingComponent();
        if (null != cpnt) {
          ret.add(cpnt);
        }
      }
    }
    return ret;
  }

  protected List<Interface> getProvidedInterfaces(Component element) {
    List<Interface> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ret.addAll(((ComponentPort) feature).getProvidedInterfaces());
      }
    }
    return ret;
  }

  protected List<Interface> getRequiredInterfaces(Component element) {
    List<Interface> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ret.addAll(((ComponentPort) feature).getRequiredInterfaces());
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
    List<Interface> ret = new ArrayList<>();
    for (InterfaceImplementation interfaceImplementation : element.getImplementedInterfaceLinks()) {
      Interface itf = interfaceImplementation.getImplementedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<Interface> getUsedInterfaces(Component element) {
    List<Interface> ret = new ArrayList<>();
    for (InterfaceUse interfaceUse : element.getUsedInterfaceLinks()) {
      Interface itf = interfaceUse.getUsedInterface();
      if (null != itf) {
        ret.add(itf);
      }
    }
    return ret;
  }

  protected List<ComponentPort> getContainedComponentPorts(Component element) {
    List<ComponentPort> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ret.add((ComponentPort) feature);
      }
    }
    return ret;
  }

  protected List<Part> getContainedParts(Component element) {
    List<Part> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        ret.add((Part) feature);
      }
    }
    return ret;
  }

  protected List<PhysicalPort> getContainedPhysicalPorts(Component element) {
    List<PhysicalPort> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof PhysicalPort) {
        ret.add((PhysicalPort) feature);
      }
    }
    return ret;
  }
  
  protected List<Part> getRepresentingParts(Component element) {
    List<TypedElement> typedElements = element.getTypedElements();
    List<Part> ret = new ArrayList<Part>();
    for (TypedElement typedElement : typedElements) {
      if (typedElement instanceof Part) {
        ret.add((Part) typedElement);
      }
    }
    return ret;
  }
}
