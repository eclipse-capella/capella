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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.queries.debug.QueryDebugger;

/**
 * Interface helpers
 */
public class InterfaceExt {

  public static ExchangeItemAllocation addExchangeItem(Interface itf_p, ExchangeItem item_p) {
    return addExchangeItem(itf_p, item_p, CommunicationLinkProtocol.UNSET, CommunicationLinkProtocol.UNSET);
  }

  public static ExchangeItemAllocation addExchangeItem(Interface itf_p, ExchangeItem item_p, CommunicationLinkProtocol sendProtocol,
      CommunicationLinkProtocol receiveProtocol) {
    if (null != item_p) {
      ExchangeItemAllocation allocation = CsFactory.eINSTANCE.createExchangeItemAllocation();
      allocation.setSendProtocol(sendProtocol);
      allocation.setReceiveProtocol(receiveProtocol);
      allocation.setAllocatedItem(item_p);
      itf_p.getOwnedExchangeItemAllocations().add(allocation);
      CapellaElementExt.creationService(allocation);
      return allocation;
    }
    return null;
  }

  /**
   * @param context a Capella Element
   * @return all the interface contained in the current and previous architectures
   */
  public static Collection<Interface> getAllInterfaces(EObject context_p) {
    // OLD CODE
    Collection<Interface> returnedInterfaces = new ArrayList<Interface>();
    for (BlockArchitecture aBlockArchitecture : BlockArchitectureExt.getRootAndPreviousBlockArchitectures(context_p)) {
      for (EObject aInterface : EObjectExt.getAll(aBlockArchitecture, CsPackage.Literals.INTERFACE)) {
        returnedInterfaces.add((Interface) aInterface);
      }
    }
    // NEW CODE
    returnedInterfaces = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_INTERFACES, context_p, returnedInterfaces);
    // END CODE REFACTOR
    return returnedInterfaces;
  }

  /**
   * return all super interfaces for the parameter abstract interface
   * @param interf_p
   * @return
   */
  public static Collection<? extends Interface> getAllSuperGeneralizableElements(Interface interf_p) {
    Collection<Interface> result = new HashSet<Interface>();
    result.add(interf_p);
    for (GeneralizableElement ge : GeneralizableElementExt.getAllSuperGeneralizableElements(interf_p)) {
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
   * @param interf_p the interface whose implementer actors will be retrieved
   * @return List<Actor> the implementer actors
   */
  public static List<Actor> getImplementerActors(Interface interf_p) {
    List<Actor> implementerActors = new ArrayList<Actor>();
    List<Component> implementerComponents = interf_p.getImplementorComponents();
    for (Component cpnt : implementerComponents) {
      if (cpnt instanceof Actor) {
        implementerActors.add((Actor) cpnt);
      }
    }
    return implementerActors;
  }

  /**
   * This method retrieves the implementer epbs components.
   * @param interf_p the interface whose implementer epbs components will be retrieved
   * @return List<PhysicalComponent> the implementer epbs components
   */
  public static List<ConfigurationItem> getImplementerEPBSComponents(Interface interf_p) {
    List<ConfigurationItem> implementerEPBSComponents = new ArrayList<ConfigurationItem>();
    List<Component> implementerComponents = interf_p.getImplementorComponents();
    for (Component cpnt : implementerComponents) {
      if (cpnt instanceof ConfigurationItem) {
        implementerEPBSComponents.add((ConfigurationItem) cpnt);
      }
    }
    return implementerEPBSComponents;
  }

  /**
   * This method retrieves the implementer logical components.
   * @param interf_p the interface whose implementer logical components will be retrieved
   * @return List<LogicalComponent> the implementer logical components
   */
  public static List<LogicalComponent> getImplementerLogicalComponents(Interface interf_p) {
    List<LogicalComponent> implementerLogicalComponents = new ArrayList<LogicalComponent>();
    List<Component> implementerComponents = interf_p.getImplementorComponents();
    for (Component cpnt : implementerComponents) {
      if (cpnt instanceof LogicalComponent) {
        implementerLogicalComponents.add((LogicalComponent) cpnt);
      }
    }
    return implementerLogicalComponents;
  }

  /**
   * This method retrieves the implementer physical components.
   * @param interf_p the interface whose implementer physical components will be retrieved
   * @return List<PhysicalComponent> the implementer physical components
   */
  public static List<PhysicalComponent> getImplementerPhysicalComponents(Interface interf_p) {
    List<PhysicalComponent> implementerPhysicalComponents = new ArrayList<PhysicalComponent>();
    List<Component> implementerComponents = interf_p.getImplementorComponents();
    for (Component cpnt : implementerComponents) {
      if (cpnt instanceof PhysicalComponent) {
        implementerPhysicalComponents.add((PhysicalComponent) cpnt);
      }
    }
    return implementerPhysicalComponents;
  }

  /**
   * @see #getInterfaceDependencies(Interface)
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getInterfaceDependencies2(Interface interface_p) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result =
        new HashMap<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>>();

    for (AbstractExchangeItem anOperation : interface_p.getExchangeItems()) {
      if (anOperation instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) anOperation;
        addToResultMap(item, ExchangeItemExt.getExchangeItemDependencies2(item), result);
      }
    }

    // superInterfaces
    Map<AbstractDependenciesPkg, Collection<EObject>> map = null;
    for (Generalization aGeneralization : interface_p.getSuperGeneralizations()) {
      map = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(map, aGeneralization.getSuper());
      addToResultMap(aGeneralization, map, result);
    }
    return result;
  }

  /** for internal use */
  private static void addToResultMap(EObject tgt_p, Map<AbstractDependenciesPkg, Collection<EObject>> map_p,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result_p) {

    Couple<EObject, Collection<EObject>> couple = null;

    Set<Couple<EObject, Collection<EObject>>> col = null;

    if (null != map_p) {
      for (AbstractDependenciesPkg pkg : map_p.keySet()) {

        if (!result_p.containsKey(pkg)) {
          col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result_p.put(pkg, col);
        }

        couple = new Couple<EObject, Collection<EObject>>(tgt_p, map_p.get(pkg));
        result_p.get(pkg).add(couple);
      }

    }

    return;
  }

  /**
   * @param interface_p
   * @return all dependent packages of the interface
   */
  public static Collection<AbstractDependenciesPkg> getInterfaceDependencies(Interface interface_p) {
    return getInterfaceDependencies2(interface_p).keySet();
  }

  /**
   * This method returns the interface package of the current interface
   */
  public static InterfacePkg getOwnerInterfacePkg(Interface interf_p) {
    if (interf_p.eContainer() instanceof Interface) {
      return getOwnerInterfacePkg((Interface) interf_p.eContainer());
    } else if (interf_p.eContainer() instanceof InterfacePkg) {
      return (InterfacePkg) interf_p.eContainer();
    }

    return null;
  }

  /**
   * This method retrieves standardPorts that provide the given interface.
   * @param interf_p
   * @return
   */
  public static List<ComponentPort> getProvidedByComponentPorts(Interface interf_p) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
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
  public static List<ComponentPort> getProvidedByPorts(Interface interf_p) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
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
  public static List<Component> getProviderComponent(Interface itf_p) {
    List<Component> result = new ArrayList<Component>();
    for (Object objectRef : EObjectExt.getReferencers(itf_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
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
  public static Collection<Component> getRelatedComponents(Interface itf_p) {
    Set<Component> result = new HashSet<Component>();

    result.addAll(itf_p.getImplementorComponents());
    result.addAll(itf_p.getUserComponents());

    List<EReference> refs = new ArrayList<EReference>();
    refs.add(InformationPackage.Literals.PORT__REQUIRED_INTERFACES);
    refs.add(InformationPackage.Literals.PORT__PROVIDED_INTERFACES);

    for (Object objectRef : EObjectExt.getReferencers(itf_p, refs)) {
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
  public static List<Component> getRequireComponent(Interface itf_p) {
    List<Component> result = new ArrayList<Component>();
    for (Object objectRef : EObjectExt.getReferencers(itf_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
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
   * @param interf_p
   * @return
   */
  public static List<ComponentPort> getRequiredByComponentPorts(Interface interf_p) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
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
  public static List<ComponentPort> getRequiredByPorts(Interface interf_p) {
    List<ComponentPort> result = new ArrayList<ComponentPort>();
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        result.add((ComponentPort) objectRef);
      }
    }
    return result;
  }

  /**
   * This method returns the root interface package of the current interface
   */
  public static InterfacePkg getRootOwnerInterfacePkg(Interface interf_p) {
    if (interf_p.eContainer() instanceof Interface) {
      return getRootOwnerInterfacePkg((Interface) interf_p.eContainer());
    } else if (interf_p.eContainer() instanceof InterfacePkg) {
      return InterfacePkgExt.getRootInterfacePkg((InterfacePkg) interf_p.eContainer());
    }

    return null;
  }

  /**
   * This method retrieves the user actors.
   * @param interf_p the interface whose user actors will be retrieved
   * @return List<Actor> the user actors
   */
  public static List<Actor> getUserActors(Interface interf_p) {
    List<Actor> userActors = new ArrayList<Actor>();
    List<Component> userComponents = interf_p.getUserComponents();
    for (Component cpnt : userComponents) {
      if (cpnt instanceof Actor) {
        userActors.add((Actor) cpnt);
      }
    }
    return userActors;
  }

  /**
   * This method retrieves the user epbs components.
   * @param interf_p the interface whose user epbs components will be retrieved
   * @return List<PhysicalComponent> the user epbs components
   */
  public static List<ConfigurationItem> getUserEPBSComponents(Interface interf_p) {
    List<ConfigurationItem> userEPBSComponents = new ArrayList<ConfigurationItem>();
    List<Component> userComponents = interf_p.getUserComponents();
    for (Component cpnt : userComponents) {
      if (cpnt instanceof ConfigurationItem) {
        userEPBSComponents.add((ConfigurationItem) cpnt);
      }
    }
    return userEPBSComponents;
  }

  /**
   * This method retrieves the user logical components.
   * @param interf_p the interface whose user logical components will be retrieved
   * @return List<LogicalComponent> the user logical components
   */
  public static List<LogicalComponent> getUserLogicalComponents(Interface interf_p) {
    List<LogicalComponent> userLogicalComponents = new ArrayList<LogicalComponent>();
    List<Component> userComponents = interf_p.getUserComponents();
    for (Component cpnt : userComponents) {
      if (cpnt instanceof LogicalComponent) {
        userLogicalComponents.add((LogicalComponent) cpnt);
      }
    }
    return userLogicalComponents;
  }

  /**
   * This method retrieves the user physical components.
   * @param interf_p the interface whose user physical components will be retrieved
   * @return List<PhysicalComponent> the user physical components
   */
  public static List<PhysicalComponent> getUserPhysicalComponents(Interface interf_p) {
    List<PhysicalComponent> userPhysicalComponents = new ArrayList<PhysicalComponent>();
    List<Component> userComponents = interf_p.getUserComponents();
    for (Component cpnt : userComponents) {
      if (cpnt instanceof PhysicalComponent) {
        userPhysicalComponents.add((PhysicalComponent) cpnt);
      }
    }
    return userPhysicalComponents;
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

  public static Interface getInterfaceFromLink(Relationship element_p) {
    if (null != element_p) {
      if (element_p instanceof InterfaceImplementation) {
        return ((InterfaceImplementation) element_p).getImplementedInterface();
      } else if (element_p instanceof InterfaceUse) {
        return ((InterfaceUse) element_p).getUsedInterface();
      }
    }
    return null;
  }

  /**
   * Return true if given interface is provided by any element
   * @param interf_p
   * @return
   */
  public static boolean isProvidedByComponentPorts(Interface interf_p) {
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return true if given interface is required by any element
   * @param interf_p
   * @return
   */
  public static boolean isRequiredByComponentPorts(Interface interf_p) {
    for (Object objectRef : EObjectExt.getReferencers(interf_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (objectRef instanceof ComponentPort) {
        return true;
      }
    }
    return false;
  }

  public static EObject getTargetElementFromLink(EObject element_p) {
    if (null != element_p) {
      if (element_p instanceof InterfaceImplementation) {
        return ((InterfaceImplementation) element_p).getImplementedInterface();
      } else if (element_p instanceof InterfaceUse) {
        return ((InterfaceUse) element_p).getUsedInterface();
      } else if (element_p instanceof SystemComponentCapabilityRealizationInvolvement) {
        return ((SystemComponentCapabilityRealizationInvolvement) element_p).getInvolved();
      } else if (element_p instanceof ActorCapabilityRealizationInvolvement) {
        return ((ActorCapabilityRealizationInvolvement) element_p).getInvolved();
      } else if (element_p instanceof AbstractCapabilityExtend) {
        return ((AbstractCapabilityExtend) element_p).getExtended();
      } else if (element_p instanceof AbstractCapabilityInclude) {
        return ((AbstractCapabilityInclude) element_p).getIncluded();
      } else if (element_p instanceof AbstractCapabilityGeneralization) {
        return ((AbstractCapabilityGeneralization) element_p).getSuper();
      } else if (element_p instanceof Generalization) {
        return ((Generalization) element_p).getSuper();
      }
    }
    return null;
  }

  /**
   * @param interfasse_p
   * @return
   */
  public static Collection<AbstractExchangeItem> getAllExchangeItems(Interface interfasse_p) {
    Set<AbstractExchangeItem> interfaceExchangeItems = new HashSet<AbstractExchangeItem>();
    for (ExchangeItem exchangeItem2 : interfasse_p.getExchangeItems()) {
      AbstractExchangeItem exchangeItem = exchangeItem2;
      interfaceExchangeItems.add(exchangeItem);
    }

    return interfaceExchangeItems;
  }
}
