/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemComponent;
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
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;

/**
 * This is an helper to extract context data to be added in the scheduler of the transfo engine.
 */
public class ContextQuery {

  private ContextQuery() {
    // To hide the implicit public one.
  }

  /**
   * Builds the merged capability realization
   * 
   * @param capabilityRealization
   *          The source capability realization
   * @return The new capability realization
   */
  public static CapabilityRealization buildMergedCapability(CapabilityRealization capabilityRealization) {
    CapabilityRealization cr = LaFactory.eINSTANCE.createCapabilityRealization();

    List<Component> components = findInvolvedComponentOfCapabilityRealizations(capabilityRealization);

    for (Component component : components) {
      CapabilityRealizationInvolvement involvement = CapellacommonFactory.eINSTANCE
          .createCapabilityRealizationInvolvement();
      involvement.setInvolved((InvolvedElement) component);
      cr.getOwnedCapabilityRealizationInvolvements().add(involvement);
    }

    return cr;
  }

  /**
   * Specifies whether a component is the parent of one of the components in the list
   * 
   * @param component
   *          The element to be tested
   * @param componentList
   *          The component list
   * @return <code>true</code> if it is the case
   */
  private static boolean containsSubComponent(Component component, List<Component> componentList) {
    for (Component cpnt : componentList) {
      if (EcoreUtil2.isContainedBy(cpnt, component)) {
        return true;
      }
    }

    return false;
  }


  /**
   * Finds the CapabilityRealizations of a Component
   * 
   * @param component
   *          The component
   * @return The list of realization
   */
  public static List<CapabilityRealization> findCapabilityRealizationsOfComponent(Component component) {
    List<SystemComponentCapabilityRealizationInvolvement> involvements = ((SystemComponent) component)
        .getParticipationsInCapabilityRealizations();
    List<CapabilityRealization> capabilityRealizations = new ArrayList<>();
    for (SystemComponentCapabilityRealizationInvolvement involvement : involvements) {
      capabilityRealizations.add((CapabilityRealization) involvement.getInvolved());
    }
    return capabilityRealizations;
  }

  /**
   * Finds the component instances of a component
   * 
   * @param component
   *          The component
   * @return The component instance list
   */
  public static List<Part> findComponentInstanceList(Component component) {
    List<Part> componentInstanceList = new ArrayList<>();
    Component pkg = component;
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
   * 
   * @param componentList
   *          The component list
   * @return The component instances
   */
  public static List<Part> findComponentInstanceList(List<? extends Component> componentList) {
    List<Part> componentInstanceList = new ArrayList<>();
    for (Component component : componentList) {
      componentInstanceList.addAll(findComponentInstanceList(component));
    }

    return componentInstanceList;
  }

  public static void findContext(Component transfoSource, List<Actor> exportedActorList,
      List<Component> exportedExternalComponentList, List<CapabilityRealization> exportedCapabilityRealizationList,
      List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList,
      List<InterfaceUse> exportedInterfaceUseList, List<InterfaceImplementation> exportedInterfaceImplList,
      List<Interface> exportedInterfaceList) {
    List<Component> internalComponents = new ArrayList<>();
    internalComponents.add(transfoSource);
    findContext(transfoSource, exportedActorList, exportedExternalComponentList, internalComponents,
        exportedCapabilityRealizationList, exportedActorInvolvementList, exportedComponentSystemInvolvementList,
        exportedInterfaceUseList, exportedInterfaceImplList, exportedInterfaceList);
  }

  /**
   * Finds the logical context
   * 
   * @param component
   * @param internalComponents
   * @param externalComponents
   * @param interfaceList
   * @param interfaceImplList
   * @param interfaceUsedList
   */
  public static void findContext(Component transfoSource, List<Actor> exportedActorList,
      List<Component> exportedExternalComponentList, List<Component> exportedInternalComponentList,
      List<CapabilityRealization> exportedCapabilityRealizationList,
      List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList,
      List<InterfaceUse> exportedInterfaceUseList, List<InterfaceImplementation> exportedInterfaceImplList,
      List<Interface> exportedInterfaceList) {

    List<Component> internalComponentsList = new ArrayList<>();

    if (transfoSource instanceof LogicalComponent) {
      internalComponentsList.add(transfoSource);
    } else if (transfoSource instanceof ConfigurationItem) {
      internalComponentsList.addAll(findLogicalComponentsOfCI((ConfigurationItem) transfoSource));
    }

    exportedInternalComponentList.addAll(internalComponentsList);

    List<CapabilityRealization> capabilityRealizationList = findRootCapabilityRealizationsOfComponent(transfoSource);

    for (CapabilityRealization capabilityRealization : capabilityRealizationList) {
      List<Component> involvedComponents = findInvolvedComponentOfCapabilityRealizations(capabilityRealization);

      findExternalComponents(internalComponentsList, involvedComponents, capabilityRealization,
          exportedExternalComponentList, exportedActorList, exportedCapabilityRealizationList,
          exportedActorInvolvementList, exportedComponentSystemInvolvementList, exportedInterfaceUseList,
          exportedInterfaceImplList, exportedInterfaceList);
    }
  }

  /**
   * Finds the context of the Bridge transformation
   * 
   * @param transfoSource
   *          The element to be exported through the bridge
   * @param exportedActorList
   *          [Output] The actors to be exported
   * @param exportedExternalComponentList
   *          [Output] The external component to be exported
   * @param exportedCapabilityRealizationUseCaseList
   *          [Output] The Usecases to be exported
   * @param exportedInterfaceUseList
   *          [Output] The interface uses to be exported
   * @param exportedInterfaceImplList
   *          [Output] The interface impls to be exported
   * @param exportedInterfaceList
   *          [Output] The interfaces to be exported
   */
  public static void findDirectContext(SystemComponent transfoSource, List<Actor> exportedActorList,
      List<Component> exportedExternalComponentList, List<CapabilityRealization> exportedCapabilityRealizationList,
      List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList,
      List<InterfaceUse> exportedInterfaceUseList, List<InterfaceImplementation> exportedInterfaceImplList,
      List<Interface> exportedInterfaceList) {

    List<SystemComponentCapabilityRealizationInvolvement> systemInvolvements = transfoSource
        .getParticipationsInCapabilityRealizations();

    for (SystemComponentCapabilityRealizationInvolvement systemInvolvement : systemInvolvements) {
      CapabilityRealization useCase = (CapabilityRealization) systemInvolvement
          .getInvolvedCapabilityRealizationInvolvedElement();

      List<ActorCapabilityRealizationInvolvement> actorInvolvements = useCase.getInvolvedActors();
      List<SystemComponentCapabilityRealizationInvolvement> systemComponentInvolvements = useCase
          .getInvolvedSystemComponents();

      boolean possibleExchangeFounded = false;

      List<InterfaceUse> exportedInterfaceUses = new ArrayList<>();
      List<InterfaceImplementation> exportedInterfaceImpls = new ArrayList<>();
      List<Interface> exportedInterfaces = new ArrayList<>();

      for (ActorCapabilityRealizationInvolvement actorInvolvement : actorInvolvements) {
        Actor actor = (Actor) actorInvolvement.getInvolver();
        if (findPossibleExchangeByInterface(transfoSource, actor, exportedInterfaceUses, exportedInterfaceImpls,
            exportedInterfaces)) {
          exportedActorList.add(actor);
          exportedActorInvolvementList.add(actorInvolvement);
          exportedInterfaceUseList.addAll(exportedInterfaceUses);
          exportedInterfaceImplList.addAll(exportedInterfaceImpls);
          exportedInterfaceList.addAll(exportedInterfaces);
          possibleExchangeFounded = true;
        }

        exportedInterfaceUses.clear();
        exportedInterfaceImpls.clear();
        exportedInterfaces.clear();
      }

      for (SystemComponentCapabilityRealizationInvolvement systemComponentInvolvement : systemComponentInvolvements) {
        SystemComponent systemComponent = (SystemComponent) systemComponentInvolvement.getInvolver();
        if (findPossibleExchangeByInterface(transfoSource, systemComponent, exportedInterfaceUses,
            exportedInterfaceImpls, exportedInterfaces)) {
          exportedExternalComponentList.add(systemComponent);
          exportedComponentSystemInvolvementList.add(systemComponentInvolvement);
          exportedInterfaceUseList.addAll(exportedInterfaceUses);
          exportedInterfaceImplList.addAll(exportedInterfaceImpls);
          exportedInterfaceList.addAll(exportedInterfaces);
          possibleExchangeFounded = true;
        }

        exportedInterfaceUses.clear();
        exportedInterfaceImpls.clear();
        exportedInterfaces.clear();
      }

      if (possibleExchangeFounded) {
        exportedCapabilityRealizationList.add(useCase);
      }
    }
  }

  /**
   * Finds the scenarios of a list of capability realization use cases with participants specified by the component
   * instance list in parameter
   * 
   * @param capabilityRealizationUseCaseList
   *          The capability realization use cases
   * @param componentInstanceList
   *          The list of component instance
   * @return
   */
  public static List<Scenario> findDirectScenarioList(List<CapabilityRealization> capabilityRealizationList,
      List<Part> componentInstanceList) {
    List<Scenario> exportedScenarioList = new ArrayList<>();

    // 1- Parse the use cases
    for (CapabilityRealization capabilityRealization : capabilityRealizationList) {

      // 1.1- Get the capability realization
      // CapabilityRealization capabilityRealization = (CapabilityRealization) useCase.eContainer();

      // 1.2- Get the aspect package
      // InteractionAspect interactionAspect = capabilityRealization.getInteractionAspect();

      // 1.3- Parse the scenarios in it
      List<Scenario> scenarioList = capabilityRealization.getOwnedScenarios();
      for (Scenario scenario : scenarioList) {
        if (!scenario.isMerged()) {
          List<AbstractInstance> componentInstances = new ArrayList<>();
          List<InstanceRole> instanceRoleList = scenario.getOwnedInstanceRoles();
          for (InstanceRole instanceRole : instanceRoleList) {
            AbstractInstance representedInstance = instanceRole.getRepresentedInstance();
            if (componentInstanceList.contains(representedInstance)) {
              componentInstances.add(representedInstance);
            }
          }

          if (!componentInstances.isEmpty()) {
            exportedScenarioList.add(scenario);
            componentInstances.clear();
          }
        }
      }
    }
    return exportedScenarioList;
  }

  /**
   * Finds the external components of an interaction involving the internal components and a list of possible involved
   * components. This is possible by the impl/used interface relationships.
   * 
   * @param internalComponents
   *          The internal components
   * @param involvedComponents
   *          The involved components
   * @param capabilityRealization
   *          [Output] The capability realization list
   * @param externalComponents
   *          [Output]
   * @param externalActors
   *          [Output]
   * @param exportedCapabilityRealizationUseCaseList
   *          [Output] The capability realization
   * @param exportedActorInvolvementList
   *          [Output] The actor involvement list
   * @param exportedComponentSystemInvolvementList
   *          [Output] The component involvement list
   * @param exportedInterfaceUseList
   *          [Output] The interface use list
   * @param exportedInterfaceImplList
   *          [Output] The interface implementation list
   * @param exportedInterfaceList
   *          [Output] The interface list
   */
  public static void findExternalComponents(List<Component> internalComponents, List<Component> involvedComponents,
      CapabilityRealization capabilityRealization, List<Component> externalComponents, List<Actor> externalActors,
      List<CapabilityRealization> exportedCapabilityRealizationList,
      List<ActorCapabilityRealizationInvolvement> exportedActorInvolvementList,
      List<SystemComponentCapabilityRealizationInvolvement> exportedComponentSystemInvolvementList,
      List<InterfaceUse> exportedInterfaceUseList, List<InterfaceImplementation> exportedInterfaceImplList,
      List<Interface> exportedInterfaceList) {
    for (Component internalComponent : internalComponents) {
      for (Component involvedComponent : involvedComponents) {

        if (findPossibleExchangeByInterface(internalComponent, involvedComponent, exportedInterfaceUseList,
            exportedInterfaceImplList, exportedInterfaceList)) {
          if (!externalComponents.contains(involvedComponent) && !internalComponents.contains(involvedComponent)) {
            if (involvedComponent instanceof Actor) {
              if (!externalActors.contains(involvedComponent)) {
                externalActors.add((Actor) involvedComponent);
              }
            } else {
              if (!externalComponents.contains(involvedComponent)) {
                // 1- Remove a less refine component founded
                Component parent = findParentComponentInList(involvedComponent, externalComponents);
                if (parent != null) {
                  externalComponents.remove(parent);
                }

                // 2- Add unique
                if (!externalComponents.contains(involvedComponent)
                    && !containsSubComponent(involvedComponent, externalComponents)) {
                  externalComponents.add(involvedComponent);
                }
              }
            }

            if (!exportedCapabilityRealizationList.contains(capabilityRealization)) {
              exportedCapabilityRealizationList.add(capabilityRealization);
            }
          }
        }
      }
    }
  }

  /**
   * Finds the external components which are communicating with the source of the bridge transformation.
   * 
   * @param transfoSource
   *          The source element
   * @return The list of external components
   * @see #findActorTransfoSourceList(SystemComponent)
   */
  public static List<SystemComponent> findExternalComponentTransfoSourceList(SystemComponent transfoSource) {
    List<SystemComponent> externalComponentTransfoSourceList = new ArrayList<>();

    List<SystemComponentCapabilityRealizationInvolvement> involvements1 = transfoSource
        .getParticipationsInCapabilityRealizations();

    // Looks for usecases that the source element is involved in
    for (SystemComponentCapabilityRealizationInvolvement involvement1 : involvements1) {
      CapabilityRealization usecase = (CapabilityRealization) involvement1
          .getInvolvedCapabilityRealizationInvolvedElement();

      // For each use case, get the other components that are involved in
      List<SystemComponentCapabilityRealizationInvolvement> involvements2 = usecase.getInvolvedSystemComponents();

      for (SystemComponentCapabilityRealizationInvolvement involvement2 : involvements2) {
        SystemComponent systemComponent = (SystemComponent) involvement2.getInvolver();

        // Don't add twice the same element nor the source element
        if ((systemComponent != transfoSource) && !externalComponentTransfoSourceList.contains(systemComponent)) {
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
   * 
   * @param capabilityRealization
   * @return
   */
  public static List<Component> findInvolvedComponentOfCapabilityRealizations(
      CapabilityRealization capabilityRealization) {
    // 1- Get the Capability at the top of the CapabilityRealization tree
    CapabilityRealization currentCapability = findRootCapabilityRealization(capabilityRealization);
    List<Component> involvedComponents = new ArrayList<>();

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

    List<CapellaElement> elements = RefinementLinkExt.getRefinementRelatedSourceElements(currentCapability,
        LaPackage.Literals.CAPABILITY_REALIZATION);

    List<AbstractCapability> agenda = new ArrayList<>();
    agenda.add((CapabilityRealization) elements.get(0));

    AbstractCapability currentCapabilityRealization = null;
    while (!agenda.isEmpty()) {
      currentCapabilityRealization = agenda.get(0);
      elements = RefinementLinkExt.getRefinementRelatedSourceElements(currentCapabilityRealization,
          LaPackage.Literals.CAPABILITY_REALIZATION);

      List<SystemComponent> newSystemComponents = CapabilityRealizationExt
          .getInvolvedSystemComponents(((CapabilityRealization) currentCapabilityRealization));

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
   * Finds the logical components of a configuration item. The algorithm go through the physical architecture layer to
   * get the logical components.
   * 
   * @param configurationItem
   *          The configuration item to be founded
   * @return The list of logical components.
   */
  public static List<LogicalComponent> findLogicalComponentsOfCI(ConfigurationItem configurationItem) {
    List<LogicalComponent> logicalComponents = new ArrayList<>();
    List<PhysicalComponent> physicalComponents = new ArrayList<>();

    for (PhysicalArtifactRealization physicalComponentRealization : configurationItem
        .getOwnedPhysicalArtifactRealizations()) {
      if (physicalComponentRealization.getTargetElement() instanceof PhysicalComponent) {
        physicalComponents.add((PhysicalComponent) physicalComponentRealization.getTargetElement());
      }
    }

    for (PhysicalComponent physicalComponent : physicalComponents) {
      List<LogicalComponentRealization> logicalComponentImplementationList = physicalComponent
          .getLogicalComponentRealizations();
      for (LogicalComponentRealization logicalComponentImplementation : logicalComponentImplementationList) {
        logicalComponents.add((LogicalComponent) logicalComponentImplementation.getAllocatedComponent());
      }
    }

    return logicalComponents;
  }

  /**
   * Retrieves a parent of the component in the list, null of none
   * 
   * @param component
   *          The component to be tested
   * @param componentList
   *          The list of component
   * @return The parent founded in the list or <code>null</code> if none
   */
  private static Component findParentComponentInList(Component component, List<Component> componentList) {
    Iterator<Component> iterator = componentList.iterator();

    while (iterator.hasNext()) {
      Component currentComponent = iterator.next();
      if (EcoreUtil2.isContainedBy(component, currentComponent)) {
        return currentComponent;
      }
    }

    return null;
  }

  /**
   * Finds a possible exchange between the sourceComponent and the external one
   * 
   * @param sourceComponent
   * @param externalComponent
   * @param exportedInterfaceUseList
   * @param exportedInterfaceImplList
   * @param exportedInterfaceList
   * @return <code>true</code> if there is a possible exchange
   */
  public static boolean findPossibleExchangeByInterface(Component sourceComponent, Component externalComponent,
      List<InterfaceUse> exportedInterfaceUseList, List<InterfaceImplementation> exportedInterfaceImplList,
      List<Interface> exportedInterfaceList) {

    if (externalComponent == sourceComponent) {
      return false;
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e U s e
    List<InterfaceUse> interfaceUseList = sourceComponent.getUsedInterfaceLinks();

    // Check for interface uses of the source element
    // that are implemented by at least one external system component
    for (InterfaceUse interfaceUse : interfaceUseList) {
      Interface usedInterface = interfaceUse.getUsedInterface();
      List<InterfaceImplementation> externalInterfaceImplList = externalComponent.getImplementedInterfaceLinks();
      for (InterfaceImplementation interfaceImplementation : externalInterfaceImplList) {
        Interface realizedInterface = interfaceImplementation.getImplementedInterface();

        if (usedInterface == realizedInterface) {
          if (!exportedInterfaceUseList.contains(interfaceUse)) {
            exportedInterfaceUseList.add(interfaceUse);
          }

          if (!exportedInterfaceImplList.contains(interfaceImplementation)) {
            exportedInterfaceImplList.add(interfaceImplementation);
          }

          if (!exportedInterfaceList.contains(realizedInterface)) {
            exportedInterfaceList.add(realizedInterface);
          }
        }
      }
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e I m p l
    List<InterfaceImplementation> interfaceImplList = sourceComponent.getImplementedInterfaceLinks();

    // Check for interface implementations of the source element
    // that are used by at least one external system component
    for (InterfaceImplementation interfaceImplementation : interfaceImplList) {
      Interface realizedInterface = interfaceImplementation.getImplementedInterface();
      List<InterfaceUse> externalInterfaceUseList = externalComponent.getUsedInterfaceLinks();
      for (InterfaceUse interfaceUse : externalInterfaceUseList) {
        Interface usedInterface = interfaceUse.getUsedInterface();
        if (usedInterface == realizedInterface) {
          if (!exportedInterfaceUseList.contains(interfaceUse)) {
            exportedInterfaceUseList.add(interfaceUse);
          }
          if (!exportedInterfaceImplList.contains(interfaceImplementation)) {
            exportedInterfaceImplList.add(interfaceImplementation);
          }
          if (!exportedInterfaceList.contains(realizedInterface)) {
            exportedInterfaceList.add(realizedInterface);
          }
        }
      }
    }

    return !exportedInterfaceList.isEmpty();
  }

  /**
   * Finds the root capability realization of a capability realization
   * 
   * @param capabilityRealization
   *          The capability realization
   * @return The 'root'
   */
  private static CapabilityRealization findRootCapabilityRealization(CapabilityRealization capabilityRealization) {
    CapabilityRealization currentCapability = capabilityRealization;
    boolean done = false;
    while ((currentCapability != null) && !done) {
      List<CapellaElement> elements = RefinementLinkExt.getRefinementRelatedTargetElements(currentCapability,
          LaPackage.Literals.CAPABILITY_REALIZATION);
      if (elements.isEmpty()) {
        done = true;
      } else {
        currentCapability = (CapabilityRealization) elements.get(0);
      }
    }

    return currentCapability;
  }

  /**
   * Finds the CapabilityRealizations of a Component
   * 
   * @param component
   *          The component
   * @return The list of realization
   */
  public static List<CapabilityRealization> findRootCapabilityRealizationsOfComponent(Component component) {
    List<SystemComponentCapabilityRealizationInvolvement> involvements = ((SystemComponent) component)
        .getParticipationsInCapabilityRealizations();
    List<CapabilityRealization> capabilityRealizations = new ArrayList<>();
    for (SystemComponentCapabilityRealizationInvolvement involvement : involvements) {
      CapabilityRealization rootCapabilityRealization = (CapabilityRealization) involvement.getInvolved();
      capabilityRealizations.add(rootCapabilityRealization);
    }
    return capabilityRealizations;
  }

  /**
   * Finds the scenarios of a list of capability realization use cases with participants specified by the component
   * instance list in parameter
   * 
   * @param capabilityRealizationUseCaseList
   *          The capability realization use cases
   * @param componentInstanceList
   *          The list of component instance
   * @return
   */
  public static List<Scenario> findScenarioList(List<CapabilityRealization> capabilityRealizationUseCaseList,
      List<Part> componentInstanceList) {
    List<Scenario> exportedScenarioList = new ArrayList<>();

    // 1- Parse the use cases
    for (CapabilityRealization useCase : capabilityRealizationUseCaseList) {

      // 1.1- Get the capability realization
      // CapabilityRealization capabilityRealization = (CapabilityRealization) useCase.eContainer();

      // AbstractCapability currentCapability
      // = findRootCapabilityRealization(capabilityRealization);

      // 1.2- Get the aspect package
      // InteractionAspect interactionAspect = currentCapability.getInteractionAspect();

      // 1.3- Parse the scenarios in it
      List<Scenario> scenarioList = useCase.getOwnedScenarios();
      for (Scenario scenario : scenarioList) {

        if (scenario.isMerged()) {
          List<AbstractInstance> componentInstances = new ArrayList<>();
          List<InstanceRole> instanceRoleList = scenario.getOwnedInstanceRoles();
          for (InstanceRole instanceRole : instanceRoleList) {
            AbstractInstance representedInstance = instanceRole.getRepresentedInstance();
            if (componentInstanceList.contains(representedInstance)) {
              componentInstances.add(representedInstance);
            }
          }

          if (!componentInstances.isEmpty()) {
            exportedScenarioList.add(scenario);
            componentInstances.clear();
          }
        }
      }
    }
    return exportedScenarioList;
  }

  /**
   * Finds the use/implement interface relationships and interfaces that are involved in communication with the source
   * element of the transformation. <br/>
   * They will be exported in the bridge transformation. <br/>
   * <br/>
   * <b>NOTE:</b> The interfaces are also returned (but they are computable using relationships).<br/>
   * <br/>
   * 
   * @param transfoSource
   *          The source element of the bridge transformation
   * @param externalComponents
   *          The external system components (components and actors)
   * @param exportedInterfaceUseList
   *          [Output] The 'use interface' relationships
   * @param exportedInterfaceImplList
   *          [Output] The 'implement interface' relationships
   * @param exportedInterfaceList
   *          [Output] The interfaces
   */
  public static void findUseImplementationInterfaceForExport(SystemComponent transfoSource,
      List<Component> externalComponents, List<InterfaceUse> exportedInterfaceUseList,
      List<InterfaceImplementation> exportedInterfaceImplList, List<Interface> exportedInterfaceList) {

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e U s e
    List<InterfaceUse> interfaceUseList = transfoSource.getUsedInterfaceLinks();

    // Check for interface uses of the source element
    // that are implemented by at least one external system component
    for (InterfaceUse interfaceUse : interfaceUseList) {
      Interface usedInterface = interfaceUse.getUsedInterface();
      for (Component component : externalComponents) {
        List<InterfaceImplementation> externalInterfaceImplList = component.getImplementedInterfaceLinks();
        for (InterfaceImplementation interfaceImplementation : externalInterfaceImplList) {
          Interface realizedInterface = interfaceImplementation.getImplementedInterface();

          if (usedInterface == realizedInterface) {
            exportedInterfaceUseList.add(interfaceUse);
            exportedInterfaceImplList.add(interfaceImplementation);
            exportedInterfaceList.add(realizedInterface);
          }
        }
      }
    }

    // /////////////////////////////////////////////////////////////////
    // I n t e r f a c e I m p l
    List<InterfaceImplementation> interfaceImplList = transfoSource.getImplementedInterfaceLinks();

    // Check for interface implementations of the source element
    // that are used by at least one external system component
    for (InterfaceImplementation interfaceImplementation : interfaceImplList) {
      Interface realizedInterface = interfaceImplementation.getImplementedInterface();
      for (Component component : externalComponents) {
        List<InterfaceUse> externalInterfaceUseList = component.getUsedInterfaceLinks();
        for (InterfaceUse interfaceUse : externalInterfaceUseList) {
          Interface usedInterface = interfaceUse.getUsedInterface();
          if (usedInterface == realizedInterface) {
            exportedInterfaceUseList.add(interfaceUse);
            exportedInterfaceImplList.add(interfaceImplementation);
            exportedInterfaceList.add(usedInterface);
          }
        }
      }
    }
  }
}
