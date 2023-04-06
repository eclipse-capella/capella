/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 * Part helpers
 */
public class PartExt {
  /*
   * Associate the Part (part) to the AbstractType(abstractType) Object given in Parameter and store the Part into
   * package in layer given in parameter (componentArchitecture)
   */
  public static void addPart(AbstractType abstractType, Part part, ComponentArchitecture componentArchitecture) {
    ComponentPkg componentPkg = null;
    if (componentArchitecture instanceof SystemAnalysis) {
      componentPkg = ((SystemAnalysis) componentArchitecture).getOwnedSystemComponentPkg();
    } else if (componentArchitecture instanceof LogicalArchitecture) {
      componentPkg = ((LogicalArchitecture) componentArchitecture).getOwnedLogicalComponentPkg();
    } else if (componentArchitecture instanceof PhysicalArchitecture) {
      componentPkg = ((PhysicalArchitecture) componentArchitecture).getOwnedPhysicalComponentPkg();
    }
    if (componentPkg != null) {
      part.setAbstractType(abstractType);
      componentPkg.getOwnedParts().add(part);
    }
  }

  /**
   * Retrieve the helper part.componentExchanges returns all component exchanges directly connected to part, or by a
   * component exchange end.
   * 
   * @param part
   * @return
   */
  public static final List<ComponentExchange> getComponentExchanges(Part part) {
    List<ComponentExchange> componentExchanges = new ArrayList<>();
    for (AbstractInformationFlow flow : part.getInformationFlows()) {
      if (flow instanceof ComponentExchange) {
        componentExchanges.add((ComponentExchange) flow);
      }
    }
    for (ComponentExchangeEnd end : getCache(FunctionalExt::getRelatedComponentExchangeEnds, part)) {
      EObject owner = end.eContainer();
      if (owner instanceof ComponentExchange) {
        componentExchanges.add((ComponentExchange) owner);
      }
    }
    return componentExchanges;
  }

  /**
   * Returns components related to given parts.
   */
  public static List<Component> getComponentsOfParts(Collection<Part> parts) {
    ArrayList<Component> components = new ArrayList<Component>();
    for (Part part : parts) {
      if (part.getAbstractType() instanceof Component) {
        components.add((Component) (part.getAbstractType()));
      }
    }
    return components;
  }

  public static Component getComponentOfPart(Part part) {
    if (part.getAbstractType() instanceof Component) {
      return (Component) (part.getAbstractType());
    }
    return null;
  }

  /**
   * Returns sub components of the component which are used (have a part).
   */

  public static List<Part> getSubUsedParts(Part part) {
    return ComponentExt.getSubParts(((Component) part.getAbstractType()));
  }

  public static List<Part> getSubUsedAndDeployedParts(Part part) {
    List<Part> result = new ArrayList<Part>();
    result.addAll(PartExt.getDeployedParts(part));
    if (part.getAbstractType() != null) {
      result.addAll(ComponentExt.getSubParts(((Component) part.getAbstractType())));
    }
    return result;
  }

  public static List<Component> getSubUsedAndDeployedComponentsOfPart(Part part) {
    return getComponentsOfParts(getSubUsedAndDeployedParts(part));
  }

  public static List<DeployableElement> getDeployedElements(Part part) {
    List<DeployableElement> result = new ArrayList<DeployableElement>(1);

    EList<AbstractDeploymentLink> deployments = part.getDeploymentLinks();
    for (AbstractDeploymentLink abstractDeployment : deployments) {
      if (abstractDeployment instanceof PartDeploymentLink) {
        PartDeploymentLink deploymentLink = (PartDeploymentLink) abstractDeployment;
        DeployableElement deployedElement = deploymentLink.getDeployedElement();
        if (deployedElement != null) {
          result.add(deployedElement);
        }
      }
    }

    return result;
  }

  public static List<Part> getDeployedParts(Part part) {
    List<Part> result = new ArrayList<Part>();

    for (DeployableElement element : PartExt.getDeployedElements(part)) {
      if (element instanceof Part) {
        result.add((Part) element);
      }
    }

    return result;
  }

  /**
   * Return all the deployable elements of given part
   * 
   * @param part
   *          : a model element
   * @return list of deployable element
   */
  public static List<DeployableElement> getAllDeployableElements(Part part) {
    List<DeployableElement> result = new ArrayList<DeployableElement>(1);
    List<Part> parts = new ArrayList<Part>(1);

    // get deployedElements
    List<DeployableElement> deployedElements = getDeployedElements(part);
    // add to result
    result.addAll(deployedElements);

    // assume all the deployed elements are of type Part
    for (DeployableElement deployableElement : deployedElements) {
      if (deployableElement instanceof Part) {
        parts.add((Part) deployableElement);
      }
    }

    // get deployed elements and add to results
    for (Part partx : parts) {
      result.addAll(getAllDeployableElements(partx));
    }

    return result;
  }

  public static boolean isDeploying(Part partDeployer, Part deployed) {

    EList<AbstractDeploymentLink> deployments = partDeployer.getDeploymentLinks();
    for (AbstractDeploymentLink abstractDeployment : deployments) {
      if (abstractDeployment instanceof PartDeploymentLink) {
        PartDeploymentLink deploymentLink = (PartDeploymentLink) abstractDeployment;
        DeployableElement deployedElement = deploymentLink.getDeployedElement();
        if ((deployedElement != null) && deployedElement.equals(deployed)) {
          return true;
        }
      }
    }

    return false;
  }

  public static List<DeploymentTarget> getDeployingElements(Part part) {
    List<DeploymentTarget> result = new ArrayList<DeploymentTarget>(1);

    EList<AbstractDeploymentLink> deployingLinks = part.getDeployingLinks();
    for (AbstractDeploymentLink abstractDeployment : deployingLinks) {
      if (abstractDeployment instanceof PartDeploymentLink) {
        PartDeploymentLink deploymentLink = (PartDeploymentLink) abstractDeployment;
        DeploymentTarget location = deploymentLink.getLocation();
        if (location != null) {
          result.add(location);
        }
      }
    }

    return result;
  }

  /**
   * Return all the deployable components from given component
   * 
   * @param component
   *          : a model element
   * @return : list of deployable Component
   */
  public static List<Component> getAllDeployableComponents(Component component) {
    List<Component> result = new ArrayList<Component>(1);

    // get deployed Component of source
    EList<AbstractTypedElement> abstractTypedElements = component.getAbstractTypedElements();
    for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
      if (abstractTypedElement instanceof Part) {
        Part part = (Part) abstractTypedElement;
        List<DeployableElement> deployedElements = PartExt.getAllDeployableElements(part);
        for (DeployableElement deployableElement : deployedElements) {
          if (deployableElement instanceof Part) {
            AbstractType abstractType = ((Part) deployableElement).getAbstractType();
            if ((null != abstractType) && (abstractType instanceof Component)) {
              result.add((Component) abstractType);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<Part> getAllPartsFromBlockArch(BlockArchitecture architecture) {
    List<Part> result = new ArrayList<Part>();
    Collection<Component> allComponents = BlockArchitectureExt.getAllComponents(architecture);
    for (CapellaElement aComponent : allComponents) {
      if (aComponent instanceof Component) {
        Component currentComponent = (Component) aComponent;
        for (Part part : currentComponent.getRepresentingParts()) {
          result.add(part);
        }
      }
    }
    return result;
  }

  public static List<Part> getAllPartsFromPhysicalArchitecture(PhysicalArchitecture blockArch) {
    List<Part> result = new ArrayList<>();
    Collection<Component> allComponents = BlockArchitectureExt.getAllComponents(blockArch);
    for (Component aComponent : allComponents) {
      for (Part part : aComponent.getRepresentingParts()) {
        result.add(part);
      }
    }
    return result;
  }

  /**
   * Retrieve part ancestors.
   * 
   * @param currentPart
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static Collection<Part> getFirstPartAncestors(Part currentPart) {
    LinkedList<Part> parents = new LinkedList<>();
    parents.addAll((Collection) getCache(PartExt::getDeployingElements, currentPart));
    Component directParent = ComponentExt.getDirectParent(currentPart);
    if (null != directParent) {
      parents.addAll(directParent.getRepresentingParts());
    }
    return parents;
  }

  public static boolean isDeployable(Part part) {
    if (CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed()) {
      return true;
    }
    return part.getDeployingParts().isEmpty();
  }

  public static boolean isDeployed(Part part) {
    return !part.getDeployingParts().isEmpty();
  }

  /**
   * Returns whether part source can be moved into target component
   * 
   * @param source
   * @param target
   * @return whether part source can be moved into target component
   */
  public static boolean canMoveInto(Part source, Component target) {
    Collection<Part> representingParts = getCache(ComponentExt::getRepresentingParts, target);

    for (Part part : representingParts) {
      Collection<Part> parts = ComponentExt.getPartAncestors(part);
      if (parts.contains(source)) {
        return false;
      }
    }

    return ComponentExt.canMoveInto((Component) source.getAbstractType(), target);
  }

  /**
   * Returns whether part source can be moved into target component package
   * 
   * @param source
   * @param target
   * @return whether part source can be moved into target component package
   */
  public static boolean canMoveInto(Part source, ComponentPkg target) {
    Component parentComponent = ComponentPkgExt.getParentComponent(target);

    if (parentComponent != null) {
      return canMoveInto(source, parentComponent);
    }

    return (ComponentExt.isActor(source) && ComponentExt.canCreateABActor(target))
        || (!ComponentExt.isActor(source) && ComponentExt.canCreateABComponent(target));
  }

  /**
   * Returns whether part source can be moved into target model element
   * 
   * @param source
   * @param target
   * @return whether part source can be moved into target model element
   */
  public static boolean canMoveInto(Part source, ModelElement target) {
    if (target instanceof Component) {
      return canMoveInto(source, (Component) target);
    }

    if (target instanceof ComponentPkg) {
      return canMoveInto(source, (ComponentPkg) target);
    }

    return false;
  }

}
