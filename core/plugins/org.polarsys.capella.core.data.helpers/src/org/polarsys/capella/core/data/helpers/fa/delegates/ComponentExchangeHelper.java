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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractEventOperationHelper;
import org.polarsys.capella.core.data.information.Port;

public class ComponentExchangeHelper {
  private static ComponentExchangeHelper instance;

  private ComponentExchangeHelper() {
    // do nothing
  }

  public static ComponentExchangeHelper getInstance() {
    if (instance == null)
      instance = new ComponentExchangeHelper();
    return instance;
  }

  public Object doSwitch(ComponentExchange element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__ALLOCATED_FUNCTIONAL_EXCHANGES)) {
      ret = getAllocatedFunctionalExchanges(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS)) {
      ret = getIncomingComponentExchangeRealizations(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS)) {
      ret = getOutgoingComponentExchangeRealizations(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS)) {
      ret = getOutgoingComponentExchangeFunctionalExchangeAllocations(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PART)) {
      ret = getSourcePart(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PORT)) {
      ret = getSourcePort(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PART)) {
      ret = getTargetPart(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PORT)) {
      ret = getTargetPort(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__ALLOCATOR_PHYSICAL_LINKS)) {
      ret = getAllocatorPhysicalLinks(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__REALIZED_COMPONENT_EXCHANGES)) {
      ret = getRealizedComponentExchanges(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__REALIZING_COMPONENT_EXCHANGES)) {
      ret = getRealizingComponentExchanges(element);
    } else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE__CATEGORIES)) {
      ret = getCategories(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = ExchangeSpecificationHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<EObject> getCategories(ComponentExchange element) {
    return EObjectExt.getReferencers(element, FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY__EXCHANGES);
  }
  
  protected List<ComponentExchangeRealization> getIncomingComponentExchangeRealizations(ComponentExchange element) {
    List<ComponentExchangeRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentExchangeRealization) {
        ret.add((ComponentExchangeRealization) trace);
      }
    }
    return ret;
  }

  protected List<ComponentExchangeRealization> getOutgoingComponentExchangeRealizations(ComponentExchange element) {
    List<ComponentExchangeRealization> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentExchangeRealization) {
        ret.add((ComponentExchangeRealization) trace);
      }
    }
    return ret;
  }

  protected List<FunctionalExchange> getAllocatedFunctionalExchanges(ComponentExchange element) {
    List<FunctionalExchange> ret = new ArrayList<>();
    for (ComponentExchangeFunctionalExchangeAllocation item : element.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
      FunctionalExchange allocatedFunctionalExchange = item.getAllocatedFunctionalExchange();
      if (null != allocatedFunctionalExchange) {
        ret.add(allocatedFunctionalExchange);
      }
    }
    return ret;
  }

  protected List<ComponentExchangeFunctionalExchangeAllocation> getOutgoingComponentExchangeFunctionalExchangeAllocations(ComponentExchange element) {
    List<ComponentExchangeFunctionalExchangeAllocation> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentExchangeFunctionalExchangeAllocation) {
        ret.add((ComponentExchangeFunctionalExchangeAllocation) trace);
      }
    }
    return ret;
  }

  protected Part getSourcePart(ComponentExchange element) {
    InformationsExchanger source = element.getSource();
    if (source instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) source).getPart();
    } else if (source instanceof Part) {
      return (Part) source;
    }
    return null;
  }

  protected Port getSourcePort(ComponentExchange element) {
    InformationsExchanger source = element.getSource();
    if (source instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }
    return null;
  }

  protected Part getTargetPart(ComponentExchange element) {
    InformationsExchanger target = element.getTarget();
    if (target instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) target).getPart();
    } else if (target instanceof Part) {
      return (Part) target;
    }
    return null;
  }

  protected Port getTargetPort(ComponentExchange element) {
    InformationsExchanger target = element.getTarget();
    if (target instanceof ComponentExchangeEnd) {
      return ((ComponentExchangeEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }
    return null;
  }

  protected List<PhysicalLink> getAllocatorPhysicalLinks(ComponentExchange element) {
    List<PhysicalLink> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentExchangeAllocation) {
        ComponentExchangeAllocator componentExchangeAllocator = ((ComponentExchangeAllocation)trace).getComponentExchangeAllocator();
        if (componentExchangeAllocator instanceof PhysicalLink) {
          ret.add((PhysicalLink) componentExchangeAllocator);
        }
      }
    }
    return ret;
  }

  protected List<ComponentExchange> getRealizedComponentExchanges(ComponentExchange element) {
    List<ComponentExchange> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentExchangeRealization) {
        ComponentExchange allocatedComponentExchange = ((ComponentExchangeRealization) trace)
            .getAllocatedComponentExchange();
        if (allocatedComponentExchange != null) {
          ret.add(allocatedComponentExchange);
        }
      }
    }
    return ret;
  }

  protected List<ComponentExchange> getRealizingComponentExchanges(ComponentExchange element) {
    List<ComponentExchange> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentExchangeRealization) {
        ComponentExchange allocatingComponentExchange = ((ComponentExchangeRealization) trace)
            .getAllocatingComponentExchange();
        if (allocatingComponentExchange != null) {
          ret.add(allocatingComponentExchange);
        }
      }
    }
    return ret;
  }
}
