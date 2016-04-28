/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;

/**
 * Part helpers
 */
public class PartExt {
  /*
   * Associate the Part (part_p) to the AbstractType(abstractType_p) Object given in Parameter and store the Part into
   * Context package in layer given in parameter (componentArchitecture_p)
   */
  public static void addPart(AbstractType abstractType, Part part, ComponentArchitecture componentArchitecture) {
    ComponentContext componentCtx = null;

    if (componentArchitecture instanceof SystemAnalysis) {
      componentCtx = ((SystemAnalysis) componentArchitecture).getOwnedSystemContext();
    } else if (componentArchitecture instanceof LogicalArchitecture) {
      componentCtx = ((LogicalArchitecture) componentArchitecture).getOwnedLogicalContext();
    } else if (componentArchitecture instanceof PhysicalArchitecture) {
      componentCtx = ((PhysicalArchitecture) componentArchitecture).getOwnedPhysicalContext();
    }

    if (componentCtx != null) {
      part.setAbstractType(abstractType);
      componentCtx.getOwnedFeatures().add(part);
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
    List<ComponentExchange> componentExchanges = new ArrayList<ComponentExchange>();
    for (AbstractInformationFlow flow : part.getInformationFlows()) {
      if (flow instanceof ComponentExchange) {
        componentExchanges.add((ComponentExchange) flow);
      }
    }
    for (ComponentExchangeEnd end : FunctionalExt.getRelatedComponentExchangeEnds(part)) {
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
  public static List<Component> getComponentsOfParts(Collection<? extends Partition> parts) {
    ArrayList<Component> components = new ArrayList<Component>();
    for (Partition part : parts) {
      if (part.getAbstractType() instanceof Component) {
        components.add((Component) (part.getAbstractType()));
      }
    }
    return components;
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
    result.addAll(ComponentExt.getSubParts(((Component) part.getAbstractType())));
    return result;
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

    List<CapellaElement> components = new ArrayList<CapellaElement>();
    List<Part> result = new ArrayList<Part>();
    BlockArchitectureExt.getAllComponentsFromBlockArchitecture(architecture, components);
    for (CapellaElement aComponent : components) {
      if (aComponent instanceof Component) {
        Component currentComponent = (Component) aComponent;
        for (Partition aPartition : currentComponent.getRepresentingPartitions()) {
          if (aPartition instanceof Part) {
            result.add((Part) aPartition);
          }
        }
      }
    }
    return result;
  }

  public static List<Part> getAllPartsFromPhysicalArchitecture(PhysicalArchitecture blockArch) {
    List<CapellaElement> components = new ArrayList<CapellaElement>();
    List<Part> result = new ArrayList<Part>();
    BlockArchitectureExt.getAllComponentsFromPA(blockArch, components);
    for (CapellaElement aComponent : components) {
      if (aComponent instanceof AbstractPhysicalComponent) {
        AbstractPhysicalComponent currentComponent = (AbstractPhysicalComponent) aComponent;
        for (Partition aPartition : currentComponent.getRepresentingPartitions()) {
          if (aPartition instanceof Part) {
            result.add((Part) aPartition);
          }
        }
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
    LinkedList<Part> parents = new LinkedList<Part>();
    parents.addAll((Collection) PartExt.getDeployingElements(currentPart));
    Component directParent = ComponentExt.getDirectParent(currentPart);
    if (null != directParent) {
      parents.addAll((Collection) directParent.getRepresentingPartitions());
    }
    return parents;
  }

}
