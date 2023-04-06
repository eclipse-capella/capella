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

package org.polarsys.capella.core.model.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;

/**
 * PhysicalComponent helpers
 */
public class PhysicalComponentExt {

  /**
   * @param location target location of deployment
   * @param deployedElement element to be deployed
   */

  public static void addDeployedElement(PhysicalComponent location, PhysicalComponent deployedElement) {
    // FIXME
    TypeDeploymentLink link = DeploymentFactory.eINSTANCE.createTypeDeploymentLink();
    CapellaElement cont = (CapellaElement) location.eContainer();
    if (cont != null) {
      ((PhysicalComponentPkg) cont).getOwnedDeployments().add(link);
    } else {
      cont = (CapellaElement) location.eContainer();
      ((PhysicalArchitecture) cont).getOwnedDeployments().add(link);
    }

    link.setLocation(location);
    link.setDeployedElement(deployedElement);
  }

  /**
   * @param deployedElement element to be deployed
   * @param location target location of deployment
   */
  public static void addDeployerElement(PhysicalComponent deployedElement, PhysicalComponent location) {
    PhysicalComponentExt.addDeployedElement(location, deployedElement);

  }

  /**
   * This method adds a logical component implementation.
   * @param physicalComponent component implementing
   * @param logicalComponent component to be implemented
   */
  public static void addImplementedLogicalComponent(PhysicalComponent physicalComponent, LogicalComponent logicalComponent) {
    ComponentRealization impl = CsFactory.eINSTANCE.createComponentRealization();
    impl.setTargetElement(logicalComponent);
    impl.setSourceElement(physicalComponent);
    physicalComponent.getOwnedComponentRealizations().add(impl);
  }

  /**
   * @param sourceDeployedElements
   * @param targetDeployedElements
   * @return
   */
  public static Collection<ComponentExchange> findConnectionsBetweenPhysicalComponentes(List<Component> sourceDeployedElements,
      List<Component> targetDeployedElements) {
    List<ComponentExchange> result = new ArrayList<>(1);

    for (Component component : sourceDeployedElements) {
      Collection<ComponentExchange> allRelatedConnection = getCache(ComponentExt::getAllRelatedComponentExchange,
          component);
      for (ComponentExchange connection : allRelatedConnection) {
        Component targetComponent = ComponentExchangeExt.getTargetComponent(connection);
        if (targetDeployedElements.contains(targetComponent)) {
          result.add(connection);
        }
      }
    }
    return result;
  }

  public static List<PhysicalComponent> getDeployedElements(PhysicalComponent location) {
    List<PhysicalComponent> deployedElements = new ArrayList<PhysicalComponent>(1);
    List<AbstractDeploymentLink> deployments = location.getDeploymentLinks();

    for (AbstractDeploymentLink abstractDeployment : deployments) {
      deployedElements.add((PhysicalComponent) abstractDeployment.getDeployedElement());
    }
    return deployedElements;
  }

  public static List<PhysicalComponent> getDeploymentTargets(PhysicalComponent element) {
    List<PhysicalComponent> deploymentTargets = new ArrayList<PhysicalComponent>();
    List<AbstractDeploymentLink> deployments = element.getDeployingLinks();

    for (AbstractDeploymentLink abstractDeployment : deployments) {
      deploymentTargets.add((PhysicalComponent) abstractDeployment.getLocation());
    }
    return deploymentTargets;
  }

  /**
   * This method retrieves all epbs component which implement the specified physical component.
   * @param component The source physical component.
   * @return The configuration item list.
   */
  public static ConfigurationItem getImplementor(PhysicalComponent component, EPBSArchitecture epbsArchitecture) {
    ConfigurationItem implementorCI = null;
    for (ConfigurationItem ci : getImplementors(component)) {
      if (EcoreUtil2.isContainedBy(ci, epbsArchitecture)) {
        implementorCI = ci;
      }
    }
    return implementorCI;
  }

  /**
   * This method retrieves all epbs component which implement the specified physical component.
   * @param component The source physical component.
   * @return The configuration item list.
   */
  public static List<ConfigurationItem> getImplementors(PhysicalComponent component) {
    List<ConfigurationItem> configurationItemsList = new ArrayList<ConfigurationItem>(1);

    EList<AbstractTrace> incomingTraces = component.getIncomingTraces();
    for (AbstractTrace incomingTrace : incomingTraces) {
      if (incomingTrace instanceof PhysicalArtifactRealization) {
        TraceableElement source = ((PhysicalArtifactRealization) incomingTrace).getSourceElement();
        if (source instanceof ConfigurationItem) {
          configurationItemsList.add((ConfigurationItem) source);
        }
      }
    }
    return configurationItemsList;
  }

  /**
   * Gets all the lcs from LogicalArchitecture
   * @param logArch the {@link LogicalArchitecture}
   * @param currentPC the current {@link PhysicalComponent}
   * @param isFilterRequired flag for checking filters
   * @return list of LCs
   */
  public static List<CapellaElement> getLCsFromLogicalArchitecture(LogicalArchitecture logArch,
      PhysicalComponent currentPC, boolean isFilterRequired) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    if (null != logArch) {
      for (LogicalComponent lc : BlockArchitectureExt.getAllComponents(logArch).stream()
          .filter(LogicalComponent.class::isInstance).map(LogicalComponent.class::cast).collect(Collectors.toList())) {
        if (isFilterRequired) {
          if (hasImplementedLC(currentPC, lc)) {
            continue;
          }
        }
        list.add(lc);
      }
    }
    return list;
  }

  /**
   * @param component the current PhysicalComponent
   * @return returns the first containing PhysicalArchitecture
   */
  public static PhysicalArchitecture getOwningPhysicalArchitecture(PhysicalComponent component) {
    return (PhysicalArchitecture) EcoreUtil2.getFirstContainer(component, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    // return getRecursiveParentArchitecture(component);
  }

  /**
   * @param component the current Physical Component
   * @return returns the Component containing the given Physical Component (can be either a Physical Component, or a Physical Node)
   */
  public static Component getParentContainer(PhysicalComponent component) {
    return getRecursiveParentContainer(component);
  }

  /**
   * Retrieves the Physical component container of Scenario given in parameter. (For Scenario under the PhysicalArchitecture, return Root PhysicalComponent
   * @return
   */
  public static PhysicalComponent getPhysicalComponentContainerFromScenario(Scenario scenario) {
    PhysicalComponent containerPc = null;

    containerPc = (PhysicalComponent) EcoreUtil2.getFirstContainer(scenario, PaPackage.Literals.PHYSICAL_COMPONENT);
    if (containerPc == null) {
      // Case : Scenario contained by PhysicalArchitecture (not under a Physical Component)
      // Retrieve the Root Physical Component
      PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(scenario, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
      containerPc = (PhysicalComponent) SystemEngineeringExt.getSystem(pa);
    }

    return containerPc;
  }

  /**
   * Returns ALL provided interfaces including implemented interfaces and all the provided interfaces trough standard ports
   * @param component current component
   * @return returns all the provided interfaces of the current component
   */
  public static List<Interface> getProvidedInterfaces(PhysicalComponent component) {
    List<Interface> providedItfList = new ArrayList<Interface>(1);
    providedItfList.addAll(component.getImplementedInterfaces());
    for (ComponentPort port : component.getContainedComponentPorts()) {
      providedItfList.addAll(PortExt.getProvidedInterfaces(port));
    }
    return providedItfList;
  }

  /**
   * @param component
   * @return
   */
  private static PhysicalArchitecture getRecursiveParentArchitecture(PhysicalComponent component) {

    EObject container = component.eContainer();

    if (container instanceof PhysicalArchitecture) {
      return (PhysicalArchitecture) container;
    } else if (container instanceof PhysicalComponentPkg) {
      return getRecursiveParentArchitecture((PhysicalComponentPkg) container);
    } else if (container instanceof PhysicalComponent) {
      return getRecursiveParentArchitecture((PhysicalComponent) container);
    }
    return null;
  }

  private static PhysicalArchitecture getRecursiveParentArchitecture(PhysicalComponentPkg comppkg) {
    EObject container = comppkg.eContainer();

    if (container instanceof PhysicalArchitecture) {
      return (PhysicalArchitecture) container;
    } else if (container instanceof PhysicalComponentPkg) {
      return getRecursiveParentArchitecture((PhysicalComponentPkg) container);
    } else if (container instanceof PhysicalComponent) {
      return getRecursiveParentArchitecture((PhysicalComponent) container);
    }
    return null;
  }


  /**
   * @param component
   * @return
   */
  private static Component getRecursiveParentContainer(PhysicalComponent component) {
    Component cpnt = null;
    EObject container = component.eContainer();
    if (container instanceof PhysicalComponentPkg) {
      cpnt = getRecursiveParentContainer((PhysicalComponentPkg) container);
    } else if (container instanceof PhysicalComponent) {
      cpnt = (PhysicalComponent) container;
    }

    return cpnt;
  }

  /**
   * @param componentPkg
   * @return
   */
  private static Component getRecursiveParentContainer(PhysicalComponentPkg componentPkg) {
    Component cpnt = null;
    EObject container = componentPkg.eContainer();
    if (container instanceof PhysicalComponentPkg) {
      cpnt = getRecursiveParentContainer((PhysicalComponentPkg) container);
    } else if (container instanceof PhysicalComponent) {
      cpnt = (PhysicalComponent) container;
    }

    return cpnt;
  }

  /**
   * Returns ALL required interfaces including used interfaces and all the required interfaces trough standard ports
   * 
   * @param component
   *          current component
   * @return returns all the required interfaces of the current component
   */
  public static List<Interface> getRequiredInterfaces(PhysicalComponent component) {
    List<Interface> requiredItfList = new ArrayList<Interface>(1);
    requiredItfList.addAll(component.getUsedInterfaces());
    for (ComponentPort port : component.getContainedComponentPorts()) {
      requiredItfList.addAll(PortExt.getRequiredInterfaces(port));
    }
    return requiredItfList;
  }

  /**
   * Checks whether the PhysicalComponent has implemented the logical component
   * @param currentPC the PhysicalComponent
   * @param lc the LogicalComponent
   * @return true if the PhysicalComponent has implemented the logical component
   */
  static public boolean hasImplementedLC(PhysicalComponent currentPC, LogicalComponent lc) {
    boolean flag = false;
    for (ComponentRealization lcImpl : currentPC.getOwnedComponentRealizations()) {
      if (lcImpl.getRealizedComponent().equals(lc)) {
        flag = true;
        break;
      }
    }
    return flag;
  }

  /**
   * This method checks if the two specified physical components are implemented by the same epbs component.
   * @param PC1
   * @param PC2
   * @return
   */
  public static boolean haveSameImplementor(PhysicalComponent PC1, PhysicalComponent PC2, EPBSArchitecture epbsArchitecture) {
    ConfigurationItem CI1 = getImplementor(PC1, epbsArchitecture);
    ConfigurationItem CI2 = getImplementor(PC2, epbsArchitecture);

    if ((CI1 == null) || (CI2 == null)) {
      return false;
    }

    return CI1.equals(CI2);
  }

  public static boolean isDeployedOn(PhysicalComponent location, PhysicalComponent deployedElement) {

    List<AbstractDeploymentLink> deployments = location.getDeploymentLinks();

    for (AbstractDeploymentLink abstractDeployment : deployments) {
      if (abstractDeployment.getDeployedElement().equals(deployedElement)) {
        return true;
      }
    }

    return false;
  }

  /*
   * Return true if the the PhysicalComponent given in parameter is the PhysicalComponent Root
   */
  public static boolean isPhysicalComponentRoot(EObject element) {
    if (!(element instanceof PhysicalComponent)) {
      return false;
    }

    // Last PhysicalComponent hierarchy detection
    return BlockArchitectureExt.isRootComponent((PhysicalComponent)element);
  }

  /**
   * This method removes a logical component implementation.
   * @param physicalComponent the physical component who implements the logical component
   * @param logicalComponent the implemented logical component
   */
  public static void removeImplementedLogicalComponent(PhysicalComponent physicalComponent, LogicalComponent logicalComponent) {
    ComponentRealization implementLink = null;
    ListIterator<ComponentRealization> it = physicalComponent.getOwnedComponentRealizations().listIterator();
    while (it.hasNext()) {
      ComponentRealization lnk = it.next();
      if (lnk.getRealizedComponent().equals(logicalComponent)) {
        implementLink = lnk;
      }
    }
    if (implementLink != null) {
      physicalComponent.getOwnedComponentRealizations().remove(implementLink);
      implementLink.destroy();
    }
  }

  public static void undeployElement(PhysicalComponent location, PhysicalComponent deployedElement) {
    List<AbstractDeploymentLink> elementsToDelete = new ArrayList<AbstractDeploymentLink>();
    List<AbstractDeploymentLink> deployements = location.getDeploymentLinks();

    for (AbstractDeploymentLink abstractDeployment : deployements) {
      if (abstractDeployment.getDeployedElement().equals(deployedElement)) {
        elementsToDelete.add(abstractDeployment);
      }
    }

    for (AbstractDeploymentLink toDelete : elementsToDelete) {
      toDelete.destroy();
    }
  }

  /**
   * One can't deploy physical component of nature 'NODE'... in Physical Component with nature 'BEHAVIOUR'
   * @param sourcePart
   * @param targetPart
   * @return true, if deployment possible, false other wise
   */
  public static boolean isDeploymentPossible(Part sourcePart, Part targetPart) {
    AbstractType sourceType = sourcePart.getAbstractType();
    AbstractType targetType = targetPart.getAbstractType();
    if ((null != sourceType) && (null != targetType) && (sourceType instanceof PhysicalComponent) && (targetType instanceof PhysicalComponent)) {
      PhysicalComponent sourcePC = (PhysicalComponent) sourceType;
      PhysicalComponent targetPC = (PhysicalComponent) targetType;
      if ((sourcePC.getNature() == PhysicalComponentNature.BEHAVIOR) && (targetPC.getNature() == PhysicalComponentNature.NODE)) {
        return false;
      }
    }

    return true;
  }
  
  public static boolean canDeploy(PhysicalComponent deployComponent, PhysicalComponent deployTargetComponent) {
    if (deployComponent == deployTargetComponent) {
      return false;
    }
    
    PhysicalComponentNature deployTargetNature = deployTargetComponent.getNature();
    PhysicalComponentNature deployNature = deployComponent.getNature();
    
    // hardware to hardware
    boolean nodeToNode = PhysicalComponentNature.NODE.equals(deployNature) && PhysicalComponentNature.NODE.equals(deployTargetNature);
    // software to hardware
    boolean behaviorToNode = PhysicalComponentNature.BEHAVIOR.equals(deployNature) && PhysicalComponentNature.NODE.equals(deployTargetNature);
    // software to software
    boolean behaviorToBehavior = PhysicalComponentNature.BEHAVIOR.equals(deployNature) && PhysicalComponentNature.BEHAVIOR.equals(deployTargetNature);
    
    return nodeToNode || behaviorToNode || behaviorToBehavior ;
  }

  public static boolean isNode(PhysicalComponent physicalComponent) {
    return PhysicalComponentNature.NODE == physicalComponent.getNature();
  }

  public static boolean isBehaviour(PhysicalComponent physicalComponent) {
    return PhysicalComponentNature.BEHAVIOR == physicalComponent.getNature();
  }
}
