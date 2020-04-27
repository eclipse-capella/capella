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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 * Interface helpers
 */
public class InterfaceExt {

  public static ExchangeItemAllocation addExchangeItem(Interface itf, ExchangeItem item) {
    return addExchangeItem(itf, item, CommunicationLinkProtocol.UNSET, CommunicationLinkProtocol.UNSET);
  }

  public static ExchangeItemAllocation addExchangeItem(Interface itf, ExchangeItem item, CommunicationLinkProtocol sendProtocol,
      CommunicationLinkProtocol receiveProtocol) {
    if (null != item) {
      ExchangeItemAllocation allocation = CsFactory.eINSTANCE.createExchangeItemAllocation();
      allocation.setSendProtocol(sendProtocol);
      allocation.setReceiveProtocol(receiveProtocol);
      allocation.setAllocatedItem(item);
      itf.getOwnedExchangeItemAllocations().add(allocation);
      CapellaElementExt.creationService(allocation);
      return allocation;
    }
    return null;
  }

  /**
   * @param context a Capella Element
   * @return all the interface contained in the current and previous architectures
   */
  public static Collection<Interface> getAllInterfaces(EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACES, context);
  }

  /**
   * return all super interfaces for the parameter abstract interface
   * @param interf
   * @return
   */
  public static Collection<? extends Interface> getAllSuperGeneralizableElements(Interface interf) {
    Collection<Interface> result = new HashSet<Interface>();
    result.add(interf);
    for (GeneralizableElement ge : GeneralizableElementExt.getAllSuperGeneralizableElements(interf)) {
      if (ge instanceof Interface) {
        Interface ai = (Interface) ge;
        result.add(ai);
      }
    }
    return result;
  }

  /**
   * return all super interfaces for each interface in the list
   * @param usedAndRequiredInterfaces_p
   * @return
   */
  public static Collection<? extends Interface> getAllSuperGeneralizableElements(Collection<? extends Interface> interfaces) {
    Collection<Interface> result = new HashSet<Interface>();
    for (Interface itf : interfaces) {
      result.addAll(getAllSuperGeneralizableElements(itf));
    }
    return result;
  }

  /**
   * This method retrieves the implementer actors.
   * @param interf the interface whose implementer actors will be retrieved
   * @return List<Actor> the implementer actors
   */
  public static List<SystemComponent> getImplementerSystemComponents(Interface interf) {
    List<SystemComponent> result = new ArrayList<SystemComponent>();
    for (Component cpnt : interf.getImplementorComponents()) {
      if (cpnt instanceof SystemComponent) {
        result.add((SystemComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the implementer epbs components.
   * @param interf the interface whose implementer epbs components will be retrieved
   * @return List<PhysicalComponent> the implementer epbs components
   */
  public static List<ConfigurationItem> getImplementerEPBSComponents(Interface interf) {
    List<ConfigurationItem> result = new ArrayList<ConfigurationItem>();
    for (Component cpnt : interf.getImplementorComponents()) {
      if (cpnt instanceof ConfigurationItem) {
        result.add((ConfigurationItem) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the implementer logical components.
   * @param interf the interface whose implementer logical components will be retrieved
   * @return List<LogicalComponent> the implementer logical components
   */
  public static List<LogicalComponent> getImplementerLogicalComponents(Interface interf) {
    List<LogicalComponent> result = new ArrayList<LogicalComponent>();
    for (Component cpnt : interf.getImplementorComponents()) {
      if (cpnt instanceof LogicalComponent) {
        result.add((LogicalComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the implementer physical components.
   * @param interf the interface whose implementer physical components will be retrieved
   * @return List<PhysicalComponent> the implementer physical components
   */
  public static List<PhysicalComponent> getImplementerPhysicalComponents(Interface interf) {
    List<PhysicalComponent> result = new ArrayList<PhysicalComponent>();
    for (Component cpnt : interf.getImplementorComponents()) {
      if (cpnt instanceof PhysicalComponent) {
        result.add((PhysicalComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * @see #getInterfaceDependencies(Interface)
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getInterfaceDependencies2(Interface interface1) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result =
        new HashMap<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>>();

    for (AbstractExchangeItem anOperation : interface1.getExchangeItems()) {
      if (anOperation instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) anOperation;
        addToResultMap(item, ExchangeItemExt.getExchangeItemDependencies2(item), result);
      }
    }

    // superInterfaces
    Map<AbstractDependenciesPkg, Collection<EObject>> map = null;
    for (Generalization aGeneralization : interface1.getSuperGeneralizations()) {
      map = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(map, aGeneralization.getSuper());
      addToResultMap(aGeneralization, map, result);
    }
    return result;
  }

  /** for internal use */
  private static void addToResultMap(EObject tgt, Map<AbstractDependenciesPkg, Collection<EObject>> map,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result) {

    Couple<EObject, Collection<EObject>> couple = null;

    Set<Couple<EObject, Collection<EObject>>> col = null;

    if (null != map) {
      for (AbstractDependenciesPkg pkg : map.keySet()) {

        if (!result.containsKey(pkg)) {
          col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result.put(pkg, col);
        }

        couple = new Couple<EObject, Collection<EObject>>(tgt, map.get(pkg));
        result.get(pkg).add(couple);
      }

    }

    return;
  }

  /**
   * @param interface1
   * @return all dependent packages of the interface
   */
  public static Collection<AbstractDependenciesPkg> getInterfaceDependencies(Interface interface1) {
    return getInterfaceDependencies2(interface1).keySet();
  }

  /**
   * This method returns the interface package of the current interface
   */
  public static InterfacePkg getOwnerInterfacePkg(Interface interf) {
    if (interf.eContainer() instanceof Interface) {
      return getOwnerInterfacePkg((Interface) interf.eContainer());
    } else if (interf.eContainer() instanceof InterfacePkg) {
      return (InterfacePkg) interf.eContainer();
    }

    return null;
  }

  /**
   * This method retrieves standardPorts that provide the given interface.
   * @param interf
   * @return
   */
  public static List<ComponentPort> getProvidedByComponentPorts(Interface interf) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        result.add((ComponentPort) objectRef);
      }
    }
    return result;
  }

  /**
   * Returns list of ports providing the given interface
   * @param itf_p
   * @return
   */
  public static List<ComponentPort> getProvidedByPorts(Interface interf) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        result.add((ComponentPort) objectRef);
      }
    }
    return result;
  }

  /**
   * Return Components that implements the Interface given in parameter through Provider Port . return all super interfaces for the parameter abstract
   * interface. note that interf_p will be included into the list result.add(interf_p);
   */
  public static List<Component> getProviderComponent(Interface itf) {
    List<Component> result = new ArrayList<Component>();
    for (Object objectRef : EObjectExt.getReferencers(itf, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (objectRef instanceof Port) {
        EObject objContainer = ((Port) objectRef).eContainer();
        if (objContainer instanceof Component) {
          result.add((Component) objContainer);
        }
      }
    }
    return result;
  }

  /**
   * Return Components that Uses/Implements/Provides/Requires the given Interface
   */
  public static Collection<Component> getRelatedComponents(Interface itf) {
    Set<Component> result = new HashSet<Component>();

    result.addAll(itf.getImplementorComponents());
    result.addAll(itf.getUserComponents());

    List<EReference> refs = new ArrayList<EReference>();
    refs.add(InformationPackage.Literals.PORT__REQUIRED_INTERFACES);
    refs.add(InformationPackage.Literals.PORT__PROVIDED_INTERFACES);

    for (Object objectRef : EObjectExt.getReferencers(itf, refs)) {
      if (objectRef instanceof Port) {
        EObject objContainer = ((Port) objectRef).eContainer();
        if (objContainer instanceof Component) {
          result.add((Component) objContainer);
        }
      }
    }

    return result;
  }

  /**
   * Return Components that Uses the Interface given in parameter through Require Port.
   */
  public static List<Component> getRequireComponent(Interface itf) {
    List<Component> result = new ArrayList<Component>();
    for (Object objectRef : EObjectExt.getReferencers(itf, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof Port) {
        EObject objContainer = ((Port) objectRef).eContainer();
        if (objContainer instanceof Component) {
          result.add((Component) objContainer);
        }
      }
    }
    return result;
  }

  /**
   * This method retrieves standardPorts that require the given interface.
   * @param interf
   * @return
   */
  public static List<ComponentPort> getRequiredByComponentPorts(Interface interf) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        result.add((ComponentPort) objectRef);
      }
    }
    return result;
  }

  /**
   * Returns list of ports requiring the given interface
   * @param itf_p
   * @return
   */
  public static List<ComponentPort> getRequiredByPorts(Interface interf) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        result.add((ComponentPort) objectRef);
      }
    }
    return result;
  }

  /**
   * This method returns the root interface package of the current interface
   */
  public static InterfacePkg getRootOwnerInterfacePkg(Interface interf) {
    if (interf.eContainer() instanceof Interface) {
      return getRootOwnerInterfacePkg((Interface) interf.eContainer());
    } else if (interf.eContainer() instanceof InterfacePkg) {
      return InterfacePkgExt.getRootInterfacePkg((InterfacePkg) interf.eContainer());
    }

    return null;
  }

  /**
   * This method retrieves the user actors.
   * @param interf the interface whose user actors will be retrieved
   * @return List<Actor> the user actors
   */
  public static List<SystemComponent> getUserSystemComponents(Interface interf) {
    List<SystemComponent> result = new ArrayList<SystemComponent>();
    for (Component cpnt : interf.getUserComponents()) {
      if (cpnt instanceof SystemComponent) {
        result.add((SystemComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the user epbs components.
   * @param interf the interface whose user epbs components will be retrieved
   * @return List<PhysicalComponent> the user epbs components
   */
  public static List<ConfigurationItem> getUserEPBSComponents(Interface interf) {
    List<ConfigurationItem> result = new ArrayList<ConfigurationItem>();
    for (Component cpnt : interf.getUserComponents()) {
      if (cpnt instanceof ConfigurationItem) {
        result.add((ConfigurationItem) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the user logical components.
   * @param interf the interface whose user logical components will be retrieved
   * @return List<LogicalComponent> the user logical components
   */
  public static List<LogicalComponent> getUserLogicalComponents(Interface interf) {
    List<LogicalComponent> result = new ArrayList<LogicalComponent>();
    for (Component cpnt : interf.getUserComponents()) {
      if (cpnt instanceof LogicalComponent) {
        result.add((LogicalComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * This method retrieves the user physical components.
   * @param interf the interface whose user physical components will be retrieved
   * @return List<PhysicalComponent> the user physical components
   */
  public static List<PhysicalComponent> getUserPhysicalComponents(Interface interf) {
    List<PhysicalComponent> result = new ArrayList<PhysicalComponent>();
    for (Component cpnt : interf.getUserComponents()) {
      if (cpnt instanceof PhysicalComponent) {
        result.add((PhysicalComponent) cpnt);
      }
    }
    return result;
  }

  /**
   * Filter in interfaces if realized by physical interface
   * @param availableElements
   * @param interfaces
   */
  public static List<CapellaElement> filterLCRealizedInterfaces(List<?> interfaces) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    for (Object element : interfaces) {
      // if interface is not transitioned
      if (element instanceof Interface) {
        Interface lcInterface = (Interface) element;
        EList<AbstractTrace> incomingTraces = lcInterface.getIncomingTraces();
        boolean isRealized = false;
        for (AbstractTrace abstractTrace : incomingTraces) {
          if (abstractTrace instanceof LogicalInterfaceRealization) {
            isRealized = true;
            break;
          }
        }
        if (!isRealized) {
          availableElements.add((CapellaElement) element);
        }
      }
    }
    return availableElements;
  }

  public static Interface getInterfaceFromLink(Relationship element) {
    if (null != element) {
      if (element instanceof InterfaceImplementation) {
        return ((InterfaceImplementation) element).getImplementedInterface();
      } else if (element instanceof InterfaceUse) {
        return ((InterfaceUse) element).getUsedInterface();
      }
    }
    return null;
  }

  /**
   * Return true if given interface is provided by any element
   * @param interf
   * @return
   */
  public static boolean isProvidedByComponentPorts(Interface interf) {
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return true if given interface is required by any element
   * @param interf
   * @return
   */
  public static boolean isRequiredByComponentPorts(Interface interf) {
    for (Object objectRef : EObjectExt.getReferencers(interf, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        return true;
      }
    }
    return false;
  }

  public static EObject getTargetElementFromLink(EObject element) {
    if (null != element) {
      if (element instanceof InterfaceImplementation) {
        return ((InterfaceImplementation) element).getImplementedInterface();
      } else if (element instanceof InterfaceUse) {
        return ((InterfaceUse) element).getUsedInterface();
      } else if (element instanceof CapabilityRealizationInvolvement) {
        return ((CapabilityRealizationInvolvement) element).getInvolved();
      } else if (element instanceof AbstractCapabilityExtend) {
        return ((AbstractCapabilityExtend) element).getExtended();
      } else if (element instanceof AbstractCapabilityInclude) {
        return ((AbstractCapabilityInclude) element).getIncluded();
      } else if (element instanceof AbstractCapabilityGeneralization) {
        return ((AbstractCapabilityGeneralization) element).getSuper();
      } else if (element instanceof Generalization) {
        return ((Generalization) element).getSuper();
      }
    }
    return null;
  }

  /**
   * @param interfasse
   * @return
   */
  public static Collection<AbstractExchangeItem> getAllExchangeItems(Interface interfasse) {
    Set<AbstractExchangeItem> interfaceExchangeItems = new HashSet<AbstractExchangeItem>();
    for (ExchangeItem exchangeItem2 : interfasse.getExchangeItems()) {
      AbstractExchangeItem exchangeItem = exchangeItem2;
      interfaceExchangeItems.add(exchangeItem);
    }

    return interfaceExchangeItems;
  }
}
