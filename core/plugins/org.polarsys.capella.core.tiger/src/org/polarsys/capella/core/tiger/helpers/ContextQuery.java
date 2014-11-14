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
package org.polarsys.capella.core.tiger.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;

/**
 * This is an helper to extract context data to be added in the scheduler of the transfo engine.
 */
public class ContextQuery {

  // ////////////////////////////////////////////////////////////////////

  /**
   * Builds the merged capability realization
   * @param capabilityRealization_p The source capability realization
   * @return The new capability realization
   */
  public static CapabilityRealization buildMergedCapability(CapabilityRealization capabilityRealization_p) {
    CapabilityRealization capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization();

    List<Component> components = findInvolvedComponentOfCapabilityRealizations(capabilityRealization_p);

    for (Component component : components) {
      if (component instanceof Actor) {
        Actor actor = (Actor) component;
        ActorCapabilityRealizationInvolvement involvement = CsFactory.eINSTANCE.createActorCapabilityRealizationInvolvement();
        involvement.setInvolved(actor);
        involvement.setInvolver(capabilityRealization);
        capabilityRealization.getOwnedActorCapabilityRealizations().add(involvement);
      }

      if (component instanceof LogicalComponent) {
        LogicalComponent logicalComponent = (LogicalComponent) component;
        SystemComponentCapabilityRealizationInvolvement involvement = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
        involvement.setInvolved(logicalComponent);
        involvement.setInvolver(capabilityRealization);
        capabilityRealization.getOwnedSystemComponentCapabilityRealizations().add(involvement);
      }
    }

    return capabilityRealization;
  }

  // ////////////////////////////////////////////////////////////////////

  /**
   * Specifies whether a component is the parent of one of the components in the list
   * @param component_p The element to be tested
   * @param componentList_p The component list
   * @return <code>true</code> if it is the case
   */
  private static boolean containsSubComponent(Component component_p, List<Component> componentList_p) {
    for (Component component : componentList_p) {
      if (EcoreUtil2.isContainedBy(component, component_p)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Finds the external actors which are communicating with the source element of the bridge transformation.
   * @param transfoSource_p The source element
   * @return The list of external actors
   * @see #findExternalComponentTransfoSourceList(SystemComponent)
   */
  public static List<Actor> findActorTransfoSourceList(SystemComponent transfoSource_p) {
    List<Actor> actorTransfoSourceList = new ArrayList<Actor>();

    List<SystemComponentCapabilityRealizationInvolvement> involvements1 = transfoSource_p.getParticipationsInCapabilityRealizations();

    // Looks for usecases that the source element is involved in
    for (SystemComponentCapabilityRealizationInvolvement involvement1 : involvements1) {
      CapabilityRealization usecase = (CapabilityRealization) involvement1.getInvolvedCapabilityRealizationInvolvedElement();

      List<ActorCapabilityRealizationInvolvement> involvements2 = usecase.getInvolvedActors();

      // For each use case, get the actors that are involved in
      for (ActorCapabilityRealizationInvolvement involvement2 : involvements2) {
        Actor actor = (Actor) involvement2.getInvolver();
        actorTransfoSourceList.add(actor);
      }
    }

    return actorTransfoSourceList;
  }

  /**
   * Finds the CapabilityRealizations of a Component
   * @param component_p The component
   * @return The list of realization
   */
  public static List<CapabilityRealization> findCapabilityRealizationsOfComponent(Component component_p) {
    List<SystemComponentCapabilityRealizationInvolvement> involvements = ((SystemComponent) component_p).getParticipationsInCapabilityRealizations();
    List<CapabilityRealization> capabilityRealizations = new ArrayList<CapabilityRealization>();
    for (SystemComponentCapabilityRealizationInvolvement involvement : involvements) {
      capabilityRealizations.add((CapabilityRealization) involvement.getInvolved());
    }
    return capabilityRealizations;
  }

  /**
   * Finds the component instances of a component
   * @param component_p The component
   * @return The component instance list
   */
  public static List<Part> findComponentInstanceList(Component component_p) {
    List<Part> componentInstanceList = new ArrayList<Part>();
    Component pkg = component_p;
    if (pkg != null) {
      TreeIterator<?> iterator = pkg.eAllContents();
      while (iterator.hasNext()) {
        Object object = iterator.next();
        if (object instanceof Part) {
          Part componentInstance = (Part) object;
          componentInstanceList.add(componentInstance);
        }
      }
    }

    return componentInstanceList;
  }

  /**
   * Finds the component instances of a component list
   * @param componentList_p The component list
   * @return The component instances
   */
  public static List<Part> findComponentInstanceList(List<? extends Component> componentList_p) {
    List<Part> componentInstanceList = new ArrayList<Part>();
    for (Component component : componentList_p) {
      componentInstanceList.addAll(findComponentInstanceList(component));
    }

    return componentInstanceList;
  }

  public static void findContext(Component transfoSource_p, List<Actor> exportedActorList_p, List<Component> exportedExternalComponentList_p,
      List<CapabilityRealization> exportedCapabilityRealizationList_p, List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList_p,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList_p, List<InterfaceUse> exportedInterfaceUseList_p,
      List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {
    List<Component> internalComponents = new ArrayList<Component>();
    internalComponents.add(transfoSource_p);
    findContext(transfoSource_p, exportedActorList_p, exportedExternalComponentList_p, internalComponents, exportedCapabilityRealizationList_p,
        exportedActorInvolvementList_p, exportedComponentSystemInvolvementList_p, exportedInterfaceUseList_p, exportedInterfaceImplList_p,
        exportedInterfaceList_p);
  }

  /**
   * Finds the logical context
   * @param component_p
   * @param internalComponents_p
   * @param externalComponents_p
   * @param interfaceList_p
   * @param interfaceImplList_p
   * @param interfaceUsedList_p
   */
  public static void findContext(Component transfoSource_p, List<Actor> exportedActorList_p, List<Component> exportedExternalComponentList_p,
      List<Component> exportedInternalComponentList_p, List<CapabilityRealization> exportedCapabilityRealizationList_p,
      List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList_p,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList_p, List<InterfaceUse> exportedInterfaceUseList_p,
      List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {

    List<Component> internalComponentsList = new ArrayList<Component>();

    if (transfoSource_p instanceof LogicalComponent) {
      internalComponentsList.add(transfoSource_p);
    } else if (transfoSource_p instanceof ConfigurationItem) {
      internalComponentsList.addAll(findLogicalComponentsOfCI((ConfigurationItem) transfoSource_p));
    }

    exportedInternalComponentList_p.addAll(internalComponentsList);

    List<CapabilityRealization> capabilityRealizationList = findRootCapabilityRealizationsOfComponent(transfoSource_p);

    for (CapabilityRealization capabilityRealization : capabilityRealizationList) {
      List<Component> involvedComponents = findInvolvedComponentOfCapabilityRealizations(capabilityRealization);

      findExternalComponents(internalComponentsList, involvedComponents, capabilityRealization, exportedExternalComponentList_p, exportedActorList_p,
          exportedCapabilityRealizationList_p, exportedActorInvolvementList_p, exportedComponentSystemInvolvementList_p, exportedInterfaceUseList_p,
          exportedInterfaceImplList_p, exportedInterfaceList_p);
    }
  }

  /**
   * Finds the context of the Bridge transformation
   * @param transfoSource_p The element to be exported through the bridge
   * @param exportedActorList_p [Output] The actors to be exported
   * @param exportedExternalComponentList_p [Output] The external component to be exported
   * @param exportedCapabilityRealizationUseCaseList_p [Output] The Usecases to be exported
   * @param exportedInterfaceUseList_p [Output] The interface uses to be exported
   * @param exportedInterfaceImplList_p [Output] The interface impls to be exported
   * @param exportedInterfaceList_p [Output] The interfaces to be exported
   */
  public static void findDirectContext(SystemComponent transfoSource_p, List<Actor> exportedActorList_p, List<Component> exportedExternalComponentList_p,
      List<CapabilityRealization> exportedCapabilityRealizationList_p, List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList_p,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList_p, List<InterfaceUse> exportedInterfaceUseList_p,
      List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {

    List<SystemComponentCapabilityRealizationInvolvement> systemInvolvements = transfoSource_p.getParticipationsInCapabilityRealizations();

    for (SystemComponentCapabilityRealizationInvolvement systemInvolvement : systemInvolvements) {
      CapabilityRealization useCase = (CapabilityRealization) systemInvolvement.getInvolvedCapabilityRealizationInvolvedElement();

      List<ActorCapabilityRealizationInvolvement> actorInvolvements = useCase.getInvolvedActors();
      List<SystemComponentCapabilityRealizationInvolvement> systemComponentInvolvements = useCase.getInvolvedSystemComponents();

      boolean possibleExchangeFounded = false;

      List<InterfaceUse> exportedInterfaceUseList = new ArrayList<InterfaceUse>();
      List<InterfaceImplementation> exportedInterfaceImplList = new ArrayList<InterfaceImplementation>();
      List<Interface> exportedInterfaceList = new ArrayList<Interface>();

      for (ActorCapabilityRealizationInvolvement actorInvolvement : actorInvolvements) {
        Actor actor = (Actor) actorInvolvement.getInvolver();
        if (findPossibleExchangeByInterface(transfoSource_p, actor, exportedInterfaceUseList, exportedInterfaceImplList, exportedInterfaceList)) {
          exportedActorList_p.add(actor);
          exportedActorInvolvementList_p.add(actorInvolvement);
          exportedInterfaceUseList_p.addAll(exportedInterfaceUseList);
          exportedInterfaceImplList_p.addAll(exportedInterfaceImplList);
          exportedInterfaceList_p.addAll(exportedInterfaceList);
          possibleExchangeFounded = true;
        }

        exportedInterfaceUseList.clear();
        exportedInterfaceImplList.clear();
        exportedInterfaceList.clear();
      }

      for (SystemComponentCapabilityRealizationInvolvement systemComponentInvolvement : systemComponentInvolvements) {
        SystemComponent systemComponent = (SystemComponent) systemComponentInvolvement.getInvolver();
        if (findPossibleExchangeByInterface(transfoSource_p, systemComponent, exportedInterfaceUseList, exportedInterfaceImplList, exportedInterfaceList)) {
          exportedExternalComponentList_p.add(systemComponent);
          exportedComponentSystemInvolvementList_p.add(systemComponentInvolvement);
          exportedInterfaceUseList_p.addAll(exportedInterfaceUseList);
          exportedInterfaceImplList_p.addAll(exportedInterfaceImplList);
          exportedInterfaceList_p.addAll(exportedInterfaceList);
          possibleExchangeFounded = true;
        }

        exportedInterfaceUseList.clear();
        exportedInterfaceImplList.clear();
        exportedInterfaceList.clear();
      }

      if (possibleExchangeFounded) {
        exportedCapabilityRealizationList_p.add(useCase);
      }
    }
  }

  /**
   * Finds the scenarios of a list of capability realization use cases with participants specified by the component instance list in parameter
   * @param capabilityRealizationUseCaseList_p The capability realization use cases
   * @param componentInstanceList_p The list of component instance
   * @return
   */
  public static List<Scenario> findDirectScenarioList(List<CapabilityRealization> capabilityRealizationList_p, List<Part> componentInstanceList_p) {
    List<Scenario> exportedScenarioList = new ArrayList<Scenario>();

    // 1- Parse the use cases
    for (CapabilityRealization capabilityRealization : capabilityRealizationList_p) {

      // 1.1- Get the capability realization
      // CapabilityRealization capabilityRealization = (CapabilityRealization) useCase.eContainer();

      // 1.2- Get the aspect package
      // InteractionAspect interactionAspect = capabilityRealization.getInteractionAspect();

      // 1.3- If it exists
      // if(interactionAspect!=null) {

      // 1.3.1- Parse the scenarios in it
      List<Scenario> scenarioList = capabilityRealization.getOwnedScenarios();
      for (Scenario scenario : scenarioList) {
        if (!scenario.isMerged()) {
          List<AbstractInstance> componentInstanceList = new ArrayList<AbstractInstance>();
          List<InstanceRole> instanceRoleList = scenario.getOwnedInstanceRoles();
          for (InstanceRole instanceRole : instanceRoleList) {
            AbstractInstance representedInstance = instanceRole.getRepresentedInstance();
            if (componentInstanceList_p.contains(representedInstance)) {
              componentInstanceList.add(representedInstance);
            }
          }

          if (!componentInstanceList.isEmpty()) {
            exportedScenarioList.add(scenario);
            componentInstanceList.clear();
          }
        }
      }
      // }
    }
    return exportedScenarioList;
  }

  /**
   * Finds the external components of an interaction involving the internal components and a list of possible involved components. This is possible by the
   * impl/used interface relationships.
   * @param internalComponents_p The internal components
   * @param involvedComponents_p The involved components
   * @param capabilityRealization_p [Output] The capability realization list
   * @param externalComponents_p [Output]
   * @param externalActors_p [Output]
   * @param exportedCapabilityRealizationUseCaseList_p [Output] The capability realization
   * @param exportedActorInvolvementList_p [Output] The actor involvement list
   * @param exportedComponentSystemInvolvementList_p [Output] The component involvement list
   * @param exportedInterfaceUseList_p [Output] The interface use list
   * @param exportedInterfaceImplList_p [Output] The interface implementation list
   * @param exportedInterfaceList_p [Output] The interface list
   */
  public static void findExternalComponents(List<Component> internalComponents_p, List<Component> involvedComponents_p,
      CapabilityRealization capabilityRealization_p, List<Component> externalComponents_p, List<Actor> externalActors_p,
      List<CapabilityRealization> exportedCapabilityRealizationList_p, List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList_p,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList_p, List<InterfaceUse> exportedInterfaceUseList_p,
      List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {
    for (Component internalComponent : internalComponents_p) {
      for (Component involvedComponent : involvedComponents_p) {

        if (findPossibleExchangeByInterface(internalComponent, involvedComponent, exportedInterfaceUseList_p, exportedInterfaceImplList_p,
            exportedInterfaceList_p)) {
          if (!externalComponents_p.contains(involvedComponent) && !internalComponents_p.contains(involvedComponent)) {
            if (involvedComponent instanceof Actor) {
              if (!externalActors_p.contains(involvedComponent)) {
                externalActors_p.add((Actor) involvedComponent);
              }
            } else {
              if (!externalComponents_p.contains(involvedComponent)) {
                // 1- Remove a less refine component founded
                Component parent = findParentComponentInList(involvedComponent, externalComponents_p);
                if (parent != null) {
                  externalComponents_p.remove(parent);
                }

                // 2- Add unique
                if (!externalComponents_p.contains(involvedComponent) && !containsSubComponent(involvedComponent, externalComponents_p)) {
                  externalComponents_p.add(involvedComponent);
                }
              }
            }

            if (!exportedCapabilityRealizationList_p.contains(capabilityRealization_p)) {
              exportedCapabilityRealizationList_p.add(capabilityRealization_p);
            }
          }
        }
      }
    }
  }

  /**
   * Finds the external components which are communicating with the source of the bridge transformation.
   * @param transfoSource_p The source element
   * @return The list of external components
   * @see #findActorTransfoSourceList(SystemComponent)
   */
  public static List<SystemComponent> findExternalComponentTransfoSourceList(SystemComponent transfoSource_p) {
    List<SystemComponent> externalComponentTransfoSourceList = new ArrayList<SystemComponent>();

    List<SystemComponentCapabilityRealizationInvolvement> involvements1 = transfoSource_p.getParticipationsInCapabilityRealizations();

    // Looks for usecases that the source element is involved in
    for (SystemComponentCapabilityRealizationInvolvement involvement1 : involvements1) {
      CapabilityRealization usecase = (CapabilityRealization) involvement1.getInvolvedCapabilityRealizationInvolvedElement();

      // For each use case, get the other components that are involved in
      List<SystemComponentCapabilityRealizationInvolvement> involvements2 = usecase.getInvolvedSystemComponents();

      for (SystemComponentCapabilityRealizationInvolvement involvement2 : involvements2) {
        SystemComponent systemComponent = (SystemComponent) involvement2.getInvolver();

        // Don't add twice the same element nor the source element
        if ((systemComponent != transfoSource_p) && !externalComponentTransfoSourceList.contains(systemComponent)) {
          externalComponentTransfoSourceList.add(systemComponent);
        }
      }
    }

    return externalComponentTransfoSourceList;
  }

  /**
   * Finds the involved components of the CapabilityRealization tree. The algorithm is:
   * <ul>
   * <li>Find the top of the Capability tree in the Context</li>
   * <li>Find the involved SystemComponent</li>
   * </ul>
   * @param capabilityRealization_p
   * @return
   */
  public static List<Component> findInvolvedComponentOfCapabilityRealizations(CapabilityRealization capabilityRealization_p) {
    // 1- Get the Capability at the top of the CapabilityRealization tree
    CapabilityRealization currentCapability = findRootCapabilityRealization(capabilityRealization_p);
    List<Component> involvedComponents = new ArrayList<Component>();

    // 2- Find all involvements of this capability
    List<Component> newComponents = AbstractCapabilityExt.getInvolvedComponents(currentCapability);
    for (Component newComponent : newComponents) {
      if ((newComponent instanceof LogicalComponent) || (newComponent instanceof Actor)) {

        // 1- Remove a less refine component founded
        Component parent = findParentComponentInList(newComponent, involvedComponents);
        if (parent != null) {
          involvedComponents.remove(parent);
        }

        // 2- Add unique
        if (!involvedComponents.contains(newComponent)) {
          involvedComponents.add(newComponent);
        }
      }
    }

    List<CapellaElement> elements = RefinementLinkExt.getRefinementRelatedSourceElements(currentCapability, LaPackage.Literals.CAPABILITY_REALIZATION);

    List<AbstractCapability> agenda = new ArrayList<AbstractCapability>();
    agenda.add((CapabilityRealization) elements.get(0));

    AbstractCapability currentCapabilityRealization = null;
    while (agenda.size() > 0) {
      currentCapabilityRealization = agenda.get(0);
      elements = RefinementLinkExt.getRefinementRelatedSourceElements(currentCapabilityRealization, LaPackage.Literals.CAPABILITY_REALIZATION);

      List<SystemComponent> newSystemComponents = CapabilityRealizationExt.getInvolvedSystemComponents(((CapabilityRealization) currentCapabilityRealization));

      for (Component newSystemComponent : newSystemComponents) {
        if ((newSystemComponent instanceof LogicalComponent) || (newSystemComponent instanceof Actor)) {

          // 1- Remove a less refine component founded
          Component parent = findParentComponentInList(newSystemComponent, involvedComponents);
          if (parent != null) {
            involvedComponents.remove(parent);
          }

          // 2- Add unique
          if (!involvedComponents.contains(newSystemComponent)) {
            involvedComponents.add(newSystemComponent);
          }
        }
      }
      agenda.remove(currentCapabilityRealization);
      // Re-inject in agenda
      for (CapellaElement capellaElement : elements) {
        agenda.add((CapabilityRealization) capellaElement);
      }
    }

    return involvedComponents;
  }

  /**
   * Finds the logical components of a configuration item. The algorithm go through the physical architecture layer to get the logical components.
   * @param configurationItem_p The configuration item to be founded
   * @return The list of logical components.
   */
  public static List<LogicalComponent> findLogicalComponentsOfCI(ConfigurationItem configurationItem_p) {
    List<LogicalComponent> logicalComponents = new ArrayList<LogicalComponent>();
    List<PhysicalComponent> physicalComponents = new ArrayList<PhysicalComponent>();

    for (PhysicalArtifactRealization physicalComponentRealization : configurationItem_p.getOwnedPhysicalArtifactRealizations()) {
      if (physicalComponentRealization.getTargetElement() instanceof PhysicalComponent) {
        physicalComponents.add((PhysicalComponent) physicalComponentRealization.getTargetElement());
      }
    }

    for (PhysicalComponent physicalComponent : physicalComponents) {
      List<LogicalComponentRealization> logicalComponentImplementationList = physicalComponent.getLogicalComponentRealizations();
      for (LogicalComponentRealization logicalComponentImplementation : logicalComponentImplementationList) {
        logicalComponents.add((LogicalComponent) logicalComponentImplementation.getAllocatedComponent());
      }
    }

    return logicalComponents;
  }

  /**
   * Retrieves a parent of the component in the list, null of none
   * @param component_p The component to be tested
   * @param componentList_p The list of component
   * @return The parent founded in the list or <code>null</code> if none
   */
  private static Component findParentComponentInList(Component component_p, List<Component> componentList_p) {
    Iterator<Component> iterator = componentList_p.iterator();

    boolean containsComponents = false;

    while (iterator.hasNext() && !containsComponents) {
      Component currentComponent = iterator.next();
      if (EcoreUtil2.isContainedBy(component_p, currentComponent)) {
        return currentComponent;
      }
    }

    return null;
  }

  /**
   * Finds a possible exchange between the sourceComponent and the external one
   * @param sourceComponent_p
   * @param externalComponent_p
   * @param exportedInterfaceUseList_p
   * @param exportedInterfaceImplList_p
   * @param exportedInterfaceList_p
   * @return <code>true</code> if there is a possible exchange
   */
  public static boolean findPossibleExchangeByInterface(Component sourceComponent_p, Component externalComponent_p,
      List<InterfaceUse> exportedInterfaceUseList_p, List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {

    if (externalComponent_p == sourceComponent_p) {
      return false;
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e U s e
    List<InterfaceUse> interfaceUseList = sourceComponent_p.getUsedInterfaceLinks();

    // Check for interface uses of the source element
    // that are implemented by at least one external system component
    for (InterfaceUse interfaceUse : interfaceUseList) {
      Interface usedInterface = interfaceUse.getUsedInterface();
      List<InterfaceImplementation> externalInterfaceImplList = externalComponent_p.getImplementedInterfaceLinks();
      for (InterfaceImplementation interfaceImplementation : externalInterfaceImplList) {
        Interface realizedInterface = interfaceImplementation.getImplementedInterface();

        if (usedInterface == realizedInterface) {
          if (!exportedInterfaceUseList_p.contains(interfaceUse)) {
            exportedInterfaceUseList_p.add(interfaceUse);
          }

          if (!exportedInterfaceImplList_p.contains(interfaceImplementation)) {
            exportedInterfaceImplList_p.add(interfaceImplementation);
          }

          if (!exportedInterfaceList_p.contains(realizedInterface)) {
            exportedInterfaceList_p.add(realizedInterface);
          }
        }
      }
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e I m p l
    List<InterfaceImplementation> interfaceImplList = sourceComponent_p.getImplementedInterfaceLinks();

    // Check for interface implementations of the source element
    // that are used by at least one external system component
    for (InterfaceImplementation interfaceImplementation : interfaceImplList) {
      Interface realizedInterface = interfaceImplementation.getImplementedInterface();
      List<InterfaceUse> externalInterfaceUseList = externalComponent_p.getUsedInterfaceLinks();
      for (InterfaceUse interfaceUse : externalInterfaceUseList) {
        Interface usedInterface = interfaceUse.getUsedInterface();
        if (usedInterface == realizedInterface) {
          if (!exportedInterfaceUseList_p.contains(interfaceUse)) {
            exportedInterfaceUseList_p.add(interfaceUse);
          }
          if (!exportedInterfaceImplList_p.contains(interfaceImplementation)) {
            exportedInterfaceImplList_p.add(interfaceImplementation);
          }
          if (!exportedInterfaceList_p.contains(realizedInterface)) {
            exportedInterfaceList_p.add(realizedInterface);
          }
        }
      }
    }

    return exportedInterfaceList_p.size() > 0;
  }

  /**
   * Finds the root capability realization of a capability realization
   * @param capabilityRealization_p The capability realization
   * @return The 'root'
   */
  private static CapabilityRealization findRootCapabilityRealization(CapabilityRealization capabilityRealization_p) {
    CapabilityRealization currentCapability = capabilityRealization_p;
    boolean done = false;
    while ((currentCapability != null) && !done) {
      List<CapellaElement> elements = RefinementLinkExt.getRefinementRelatedTargetElements(currentCapability, LaPackage.Literals.CAPABILITY_REALIZATION);
      if (elements.size() == 0) {
        done = true;
      } else {
        currentCapability = (CapabilityRealization) elements.get(0);
      }
    }

    return currentCapability;
  }

  /**
   * Finds the CapabilityRealizations of a Component
   * @param component_p The component
   * @return The list of realization
   */
  public static List<CapabilityRealization> findRootCapabilityRealizationsOfComponent(Component component_p) {
    List<SystemComponentCapabilityRealizationInvolvement> involvements = ((SystemComponent) component_p).getParticipationsInCapabilityRealizations();
    List<CapabilityRealization> capabilityRealizations = new ArrayList<CapabilityRealization>();
    for (SystemComponentCapabilityRealizationInvolvement involvement : involvements) {
      CapabilityRealization rootCapabilityRealization = (CapabilityRealization) involvement.getInvolved();
      capabilityRealizations.add(rootCapabilityRealization);
    }
    return capabilityRealizations;
  }

  /**
   * Finds the scenarios of a list of capability realization use cases with participants specified by the component instance list in parameter
   * @param capabilityRealizationUseCaseList_p The capability realization use cases
   * @param componentInstanceList_p The list of component instance
   * @return
   */
  public static List<Scenario> findScenarioList(List<CapabilityRealization> capabilityRealizationUseCaseList_p, List<Part> componentInstanceList_p) {
    List<Scenario> exportedScenarioList = new ArrayList<Scenario>();

    // 1- Parse the use cases
    for (CapabilityRealization useCase : capabilityRealizationUseCaseList_p) {

      // 1.1- Get the capability realization
      // CapabilityRealization capabilityRealization = (CapabilityRealization) useCase.eContainer();

      // AbstractCapability currentCapability
      // = findRootCapabilityRealization(capabilityRealization);

      // 1.2- Get the aspect package
      // InteractionAspect interactionAspect = currentCapability.getInteractionAspect();

      // 1.3- If it exists
      // if(interactionAspect!=null) {

      // 1.3.1- Parse the scenarios in it
      List<Scenario> scenarioList = useCase.getOwnedScenarios();
      for (Scenario scenario : scenarioList) {

        if (scenario.isMerged()) {
          List<AbstractInstance> componentInstanceList = new ArrayList<AbstractInstance>();
          List<InstanceRole> instanceRoleList = scenario.getOwnedInstanceRoles();
          for (InstanceRole instanceRole : instanceRoleList) {
            AbstractInstance representedInstance = instanceRole.getRepresentedInstance();
            if (componentInstanceList_p.contains(representedInstance)) {
              componentInstanceList.add(representedInstance);
            }
          }

          if (!componentInstanceList.isEmpty()) {
            exportedScenarioList.add(scenario);
            componentInstanceList.clear();
          }
        }
      }
      // }
    }
    return exportedScenarioList;
  }

  /**
   * Finds the use/implement interface relationships and interfaces that are involved in communication with the source element of the transformation. <br/>
   * They will be exported in the bridge transformation. <br/>
   * <br/>
   * <b>NOTE:</b> The interfaces are also returned (but they are computable using relationships).<br/>
   * <br/>
   * @param transfoSource_p The source element of the bridge transformation
   * @param externalComponents_p The external system components (components and actors)
   * @param exportedInterfaceUseList_p [Output] The 'use interface' relationships
   * @param exportedInterfaceImplList_p [Output] The 'implement interface' relationships
   * @param exportedInterfaceList_p [Output] The interfaces
   */
  public static void findUseImplementationInterfaceForExport(SystemComponent transfoSource_p, List<Component> externalComponents_p,
      List<InterfaceUse> exportedInterfaceUseList_p, List<InterfaceImplementation> exportedInterfaceImplList_p, List<Interface> exportedInterfaceList_p) {

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e U s e
    List<InterfaceUse> interfaceUseList = transfoSource_p.getUsedInterfaceLinks();

    // Check for interface uses of the source element
    // that are implemented by at least one external system component
    for (InterfaceUse interfaceUse : interfaceUseList) {
      Interface usedInterface = interfaceUse.getUsedInterface();
      for (Component component : externalComponents_p) {
        List<InterfaceImplementation> externalInterfaceImplList = component.getImplementedInterfaceLinks();
        for (InterfaceImplementation interfaceImplementation : externalInterfaceImplList) {
          Interface realizedInterface = interfaceImplementation.getImplementedInterface();

          if (usedInterface == realizedInterface) {
            exportedInterfaceUseList_p.add(interfaceUse);
            exportedInterfaceImplList_p.add(interfaceImplementation);
            exportedInterfaceList_p.add(realizedInterface);
          }
        }
      }
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e I m p l
    List<InterfaceImplementation> interfaceImplList = transfoSource_p.getImplementedInterfaceLinks();

    // Check for interface implementations of the source element
    // that are used by at least one external system component
    for (InterfaceImplementation interfaceImplementation : interfaceImplList) {
      Interface realizedInterface = interfaceImplementation.getImplementedInterface();
      for (Component component : externalComponents_p) {
        List<InterfaceUse> externalInterfaceUseList = component.getUsedInterfaceLinks();
        for (InterfaceUse interfaceUse : externalInterfaceUseList) {
          Interface usedInterface = interfaceUse.getUsedInterface();
          if (usedInterface == realizedInterface) {
            exportedInterfaceUseList_p.add(interfaceUse);
            exportedInterfaceImplList_p.add(interfaceImplementation);
            exportedInterfaceList_p.add(usedInterface);
          }
        }
      }
    }
  }
}
