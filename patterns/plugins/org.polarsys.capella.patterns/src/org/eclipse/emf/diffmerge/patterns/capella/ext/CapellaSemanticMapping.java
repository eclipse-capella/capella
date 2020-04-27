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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import static org.eclipse.emf.diffmerge.patterns.diagrams.sirius.util.SiriusLayersUtil.getSemanticElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.DefaultSemanticMapping;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;


/**
 * An implementation of ISemanticMapping for Capella.
 * This implementation relies on the diagram specifications of Capella.
 */
public class CapellaSemanticMapping extends DefaultSemanticMapping {

  /** Lowercase names of mappings which should not be used **/
  private static final Collection<String> DUMMY_MAPPING_NAMES =
      Collections.unmodifiableCollection(Arrays.asList(
          "lab sub components", "dt_datavalue", "ca dummy" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          ));

  /** Lowercase names of allocated function mappings in architecture diagrams **/
  @SuppressWarnings("nls")
  private static final Collection<String> ALLOCATED_FUNCTION_MAPPING_NAMES =
      Arrays.asList(
          "oab_activity",
          "ca system function",
          "lab_function",
          "pab_physicalfunction");

  /** Lowercase name of the physical component mapping in PAB **/
  private static final String PHYSICAL_COMPONENT_MAPPING_NAME = "pab_pc"; //$NON-NLS-1$

  /** Lowercase name of the deployed component mapping in PAB **/
  private static final String DEPLOYMENT_MAPPING_NAME = "pab_deployment"; //$NON-NLS-1$

  /** Lowercase names of subcomponent mappings in architecture diagrams **/
  private static final Collection<String> SUBCOMPONENT_MAPPING_NAMES =
      Arrays.asList(
          "lab logical component", //$NON-NLS-1$
          PHYSICAL_COMPONENT_MAPPING_NAME);


  /**
   * Default constructor
   */
  public CapellaSemanticMapping() {
	  // Do nothing
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.DefaultSemanticMapping#conformsToMapping(EObject, AbstractNodeMapping, boolean, boolean, Object)
   */
  @Override
  public boolean conformsToMapping(EObject semanticElt, AbstractNodeMapping mapping,
      boolean considerPrecondition, boolean considerCandidates,
      Object containerView) {
    boolean businessOk = !isDummyMapping(mapping);
    if (considerPrecondition && considerCandidates &&
        (containerView instanceof DDiagram || containerView instanceof DDiagramElementContainer)) {
      // If full verification, check for business criteria
      businessOk = businessOk && (!isSubcomponentMapping(mapping) ||
          checkSubcomponent(getSemanticElement(containerView), semanticElt));
      businessOk = businessOk && (!isPhysicalComponentMapping(mapping) ||
          !isDeployed(semanticElt));
      businessOk = businessOk && (!isDeploymentMapping(mapping) ||
          checkDeployment(getSemanticElement(containerView), semanticElt));
      businessOk = businessOk && (!isAllocatedFunctionMapping(mapping) ||
          checkAllocation(getSemanticElement(containerView), semanticElt));
    }
    boolean checkPrecondition = considerPrecondition &&
        preconditionCanBeChecked(mapping);
    boolean checkSemanticCandidates = considerCandidates &&
        semanticCandidatesCanBeChecked(mapping);
    return businessOk && super.conformsToMapping(
        semanticElt, mapping, checkPrecondition, checkSemanticCandidates, containerView);
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.DefaultSemanticMapping#getSemanticCandidatesForGraphicalStorage(EObject, Object)
   */
  @Override
  public Collection<EObject> getSemanticCandidatesForGraphicalStorage(
      EObject element, Object diagram) {
    Collection<EObject> result =
        super.getSemanticCandidatesForGraphicalStorage(element, diagram);
    if (result.size() == 1) {
      EObject candidate1 = result.iterator().next();
      if (candidate1 instanceof Component &&
          candidate1.eContainer() instanceof ModellingArchitecture
          && diagram instanceof DDiagram) {
        Component component = (Component)candidate1;
        if (component.getTypedElements().size() == 1) {
          TypedElement part = component.getTypedElements().get(0);
          result.add(part);
          if (isLAB((DDiagram)diagram)) {
            result.remove(candidate1);
          }
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.DefaultSemanticMapping#getSemanticSelection(Object)
   */
  @Override
  public Collection<EObject> getSemanticSelection(Object decorator) {
    Collection<EObject> result = new FOrderedSet<>();
    result.addAll(super.getSemanticSelection(decorator));
    EObject current = result.iterator().next(); // Non-empty by contract
    if (current instanceof Part) {
      // Component comes with part
      Part part = (Part)current;
      AbstractType type = part.getAbstractType();
      if (type instanceof Component) {
        result.add(type);
      }
    } else if (current instanceof Entity) {
      Entity entity = (Entity)current;
      List<TypedElement> typedElements = entity.getTypedElements();
      if (typedElements.size() == 1) {
        result.add(typedElements.get(0));
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.DefaultSemanticMapping#getSemanticStorage(Object)
   */
  @Override
  public EObject getSemanticStorage(Object decorator) {
    // Semantic element by default
    EObject result = super.getSemanticStorage(decorator);
    // If Part, take its type
    if (result instanceof Part) {
      result = ((Part)result).getAbstractType();
    }
    if (decorator instanceof DSemanticDiagram) {
      DSemanticDiagram diagram = (DSemanticDiagram)decorator;
      if (diagramTargetIsExplicitlyRepresented(diagram)) {
        if (isLAB(diagram)) {
          // In a LAB, new elements in the diagram background are stored in the
          // root Logical Component
          EObject rootLc = getLogicalArchitecture(diagram.getTarget()).getSystem();
          if (null != rootLc) {
            result = rootLc;
          }
        } else {
          // The diagram's target is represented within the diagram:
          // take its container for storage
          result = result.eContainer();
        }
      }
    }
    return result;
  }

  /**
   * Return whether the given target can be represented as an allocated function within
   * the given container
   * @param container a potentially null element
   * @param target a potentially null element
   */
  private boolean checkAllocation(EObject container, EObject target) {
    boolean result = false;
    if (target instanceof AbstractFunction) {
      AbstractFunctionalBlock refinedContainer = null;
      if (container instanceof Part) { // SA, LA, PA
        Type type = ((Part)container).getType();
        if (type instanceof AbstractFunctionalBlock) {
          refinedContainer = (AbstractFunctionalBlock) type;
        }
      } else if (container instanceof AbstractFunctionalBlock) { // OA
        refinedContainer = (AbstractFunctionalBlock) container;
      }
      if (refinedContainer != null) {
        result = refinedContainer.getAllocatedFunctions().contains(target);
      }
    }
    return result;
  }

  /**
   * Return whether the given target can be represented as a deployed component within
   * the given container
   * @param container a potentially null element
   * @param target a potentially null element
   */
  private boolean checkDeployment(EObject container, EObject target) {
    Part locationPart = null;
    PhysicalComponent locationType = null;
    if (container instanceof Part) {
      locationPart = (Part) container;
    } else {
      return false;
    }
    Part targetPart = null;
    if (target instanceof Part) {
      targetPart = (Part) target;
    } else {
      return false;
    }
    // Retrieve deployment links from target and check for a relevant one
    Collection<AbstractDeploymentLink> deployments = targetPart.getDeployingLinks();
    for (AbstractDeploymentLink deployment : deployments) {
      DeploymentTarget currentLocation = deployment.getLocation();
      if (currentLocation == locationPart
          || currentLocation instanceof Part &&
            ((Part) currentLocation).getAbstractType() == locationType) {
        return true;
      }
    }
    // None found
    return false;
  }

  /**
   * Return whether the given target can be represented as a subcomponent of
   * the given container
   * @param container a potentially null element
   * @param target a potentially null element
   */
  private boolean checkSubcomponent(EObject container, EObject target) {
    if (!(container instanceof Component &&
        (target instanceof AbstractType || target instanceof Part))) {
      return true;
    }
    Component partitionableElement = (Component)container;
    AbstractType subComponentType;
    if (target instanceof Part) {
      subComponentType = ((Part)target).getAbstractType();
    } else {
      subComponentType = (AbstractType)target;
    }
    for (Part part : partitionableElement.getContainedParts()) {
      if (part.getAbstractType() == subComponentType) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return whether the semantic element of the given diagram is explicitly
   * represented as a node in the diagram
   * @param diagram a non-null diagram
   */
  private boolean diagramTargetIsExplicitlyRepresented(DSemanticDiagram diagram) {
    final EObject diagramTarget = diagram.getTarget();
    if (diagramTarget == null) {
      return false;
    }
    Collection<AbstractDNode> subNodes = new ArrayList<>();
    subNodes.addAll(diagram.getContainers());
    subNodes.addAll(diagram.getNodeListElements());
    subNodes.addAll(diagram.getNodes());
    for (AbstractDNode currentNode : subNodes) {
      if (diagramTarget == currentNode.getTarget()) {
        return true;
      }
    }
    return false;
  }

  /**
   * From a given Capella element contained anywhere in a System Engineering, get the
   * Logical Architecture of the System Engineering
   * @param current a potentially null element
   */
  private LogicalArchitecture getLogicalArchitecture(EObject current) {
    if (null == current) {
      return null;
    }
    if (current instanceof LogicalArchitecture) {
      return (LogicalArchitecture)current;
    }
    return getLogicalArchitecture(current.eContainer());
  }

  /**
   * Return whether the given mapping represents an allocated function in a PAB
   * @param mapping a non-null mapping
   */
  private boolean isAllocatedFunctionMapping(AbstractNodeMapping mapping) {
    return ALLOCATED_FUNCTION_MAPPING_NAMES.contains(getLowerCaseName(mapping));
  }

  /**
   * Return whether the given element is deployed on another one
   * @param element a non-null element
   */
  private boolean isDeployed(EObject element) {
    boolean result = false;
    if (element instanceof DeployableElement) {
      DeployableElement deployable = (DeployableElement)element;
      result = !deployable.getDeployingLinks().isEmpty();
    }
    return result;
  }

  /**
   * Return whether the given mapping represents a deployed component in a PAB
   * @param mapping a non-null mapping
   */
  private boolean isDeploymentMapping(AbstractNodeMapping mapping) {
    return DEPLOYMENT_MAPPING_NAME.equals(getLowerCaseName(mapping));
  }

  /**
   * An ad-hoc method which returns true iff the given mapping is a dummy one
   * which should not be used to create graphical nodes
   * @param mapping a non-null mapping
   */
  private boolean isDummyMapping(AbstractNodeMapping mapping) {
    boolean result = false;
    if (mapping != null && mapping.getName() != null) {
      final String name = mapping.getName().toLowerCase();
      result = DUMMY_MAPPING_NAMES.contains(name);
      result = result || name.endsWith("_dummy"); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Return whether the given diagram is a Capella Logical Architecture Blank
   * @param diagram a non-null diagram
   */
  private boolean isLAB(DDiagram diagram) {
    boolean result = false;
    String descName = diagram.getDescription().getName();
    if (descName != null) {
      // Can't see a better criterion
      result = descName.toLowerCase().contains("logical architecture"); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Return whether the given mapping represents a physical component in a PAB
   * @param mapping_p a non-null mapping
   */
  private boolean isPhysicalComponentMapping(AbstractNodeMapping mapping) {
    return PHYSICAL_COMPONENT_MAPPING_NAME.equals(getLowerCaseName(mapping));
  }

  /**
   * Return whether the given mapping may represent a sub-component in an Architecture
   * diagram
   * @param mapping a non-null mapping
   */
  private boolean isSubcomponentMapping(AbstractNodeMapping mapping) {
    return mapping != null && mapping.getName() != null &&
        SUBCOMPONENT_MAPPING_NAMES.contains(getLowerCaseName(mapping));
  }

  /**
   * Return the name of the given mapping in lower case, or null if not applicable
   * @param mapping a non-null mapping
   */
  private String getLowerCaseName(AbstractNodeMapping mapping) {
    String result = null;
    if (mapping != null && mapping.getName() != null) {
      result = mapping.getName().toLowerCase();
    }
    return result;
  }

  /**
   * Return whether precondition evaluation of the given mapping is relevant
   * @param mapping a non-null mapping
   */
  private boolean preconditionCanBeChecked(AbstractNodeMapping mapping) {
    return !isDeploymentMapping(mapping);
  }

  /**
   * Return whether evaluation of the semantic candidates of the given mapping is relevant
   * @param mapping a non-null mapping
   */
  private boolean semanticCandidatesCanBeChecked(AbstractNodeMapping mapping) {
    return preconditionCanBeChecked(mapping);
  }

}
