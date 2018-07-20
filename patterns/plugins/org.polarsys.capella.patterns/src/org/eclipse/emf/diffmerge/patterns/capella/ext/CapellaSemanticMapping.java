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
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;




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

  /** Lowercase name of the physical component mapping in PAB **/
  private static final String PHYSICAL_COMPONENT_MAPPING_NAME = "pab_pc"; //$NON-NLS-1$

  /** Lowercase name of the deployed component mapping in PAB **/
  private static final String DEPLOYMENT_MAPPING_NAME = "pab_deployment"; //$NON-NLS-1$

  /** Lowercase names of subcomponent mappings in architecture diagrams **/
  private static final Collection<String> SUBCOMPONENT_MAPPING_NAMES =
      Collections.unmodifiableCollection(Arrays.asList(
          "lab logical component", //$NON-NLS-1$
          PHYSICAL_COMPONENT_MAPPING_NAME
          ));


  /**
   * Default constructor
   */
  public CapellaSemanticMapping() {
	  // Do nothing
  }

/**
 * 
 * @see org.eclipse.emf.diffmerge.patterns.diagram.sirius.extensions.DefaultSemanticMapping#conformsToMapping(org.eclipse.emf.ecore.EObject, org.eclipse.sirius.viewpoint.description.AbstractNodeMapping, boolean, boolean, org.eclipse.sirius.viewpoint.DContainer)
 */
  @Override
  public boolean conformsToMapping(EObject semanticElt, AbstractNodeMapping mapping,
      boolean considerPrecondition, boolean considerCandidates,
      Object containerView) {
    boolean businessOk = !isDummyMapping(mapping);
    if (considerPrecondition && considerCandidates && ((containerView instanceof DDiagram)||(containerView instanceof DDiagramElementContainer))) {
      // If full verification, check for business criteria
      businessOk = businessOk && (!isSubcomponentMapping(mapping) ||
          checkSubcomponent(getSemanticElement(containerView), semanticElt));
      businessOk = businessOk && (!isPhysicalComponentMapping(mapping) ||
          !isDeployed(semanticElt));
      businessOk = businessOk && (!isDeploymentMapping(mapping) ||
          checkDeployment(getSemanticElement(containerView), semanticElt));
    }
    boolean checkPrecondition = considerPrecondition &&
        preconditionCanBeChecked(mapping);
    boolean checkSemanticCandidates = considerCandidates &&
        semanticCandidatesCanBeChecked(mapping);
    return businessOk && super.conformsToMapping(
        semanticElt, mapping, checkPrecondition, checkSemanticCandidates, containerView);
  }

  
 


  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.diagram.sirius.extensions.DefaultSemanticMapping#getSemanticCandidatesForGraphicalStorage(org.eclipse.emf.ecore.EObject, fr.obeo.dsl.viewpoint.DDiagram)
   */
  @Override
  public Collection<EObject> getSemanticCandidatesForGraphicalStorage(
      EObject element_p, Object diagram_p) {
    Collection<EObject> result =
        super.getSemanticCandidatesForGraphicalStorage(element_p, diagram_p);
    if (result.size() == 1) {
      EObject candidate1 = result.iterator().next();
      if (candidate1 instanceof Component &&
          candidate1.eContainer() instanceof ModellingArchitecture
          && diagram_p instanceof DDiagram) {
        Component component = (Component)candidate1;
        if (component.getTypedElements().size() == 1) {
          TypedElement part = component.getTypedElements().get(0);
          result.add(part);
          if (isLAB((DDiagram)diagram_p))
            result.remove(candidate1);
        }
      }
    }
    return result;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.diagram.sirius.extensions.DefaultSemanticMapping#getSemanticSelection(fr.obeo.dsl.viewpoint.DSemanticDecorator)
   */
  @Override
  public Collection<EObject> getSemanticSelection(Object decorator_p) {
    Collection<EObject> result = new FOrderedSet<EObject>();
    result.addAll(super.getSemanticSelection(decorator_p));
    EObject current = result.iterator().next(); // Non-empty by contract
    if (current instanceof Part) {
      // Component comes with part
      Part part = (Part)current;
      AbstractType type = part.getAbstractType();
      if (type instanceof Component)
        result.add(type);
    } else if (current instanceof Entity) {
      Entity entity = (Entity)current;
      List<TypedElement> typedElements = entity.getTypedElements();
      if (typedElements.size() == 1)
        result.add(typedElements.get(0));
    }
    return result;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.diagram.sirius.extensions.DefaultSemanticMapping#getSemanticStorage(fr.obeo.dsl.viewpoint.DSemanticDecorator)
   */
  @Override
  public EObject getSemanticStorage(Object decorator_p) {
    // Semantic element by default
    EObject result = super.getSemanticStorage(decorator_p);
    // If Part, take its type
    if (result instanceof Part)
      result = ((Part)result).getAbstractType();
    if (decorator_p instanceof DSemanticDiagram) {
      DSemanticDiagram diagram = (DSemanticDiagram)decorator_p;
      if (diagramTargetIsExplicitlyRepresented(diagram)) {
        if (isLAB(diagram)) {
          // In a LAB, new elements in the diagram background are stored in the
          // root Logical Component
          EObject rootLc = getLogicalArchitecture(diagram.getTarget()).getOwnedLogicalComponent();
          if (null != rootLc) result = rootLc;
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
   * Return whether the given target can be represented as a deployed component within
   * the given container
   * @param container a potentially null element
   * @param target a potentially null element
   */
  private boolean checkDeployment(EObject container, EObject target) {
    boolean allowTypeDeployment = false; // Decision here
    Part locationPart = null;
    AbstractPhysicalComponent locationType = null;
    if (container instanceof Part)
      locationPart = (Part)container;
    else if (container instanceof AbstractPhysicalComponent && allowTypeDeployment)
      locationType = (AbstractPhysicalComponent)container;
    else
      return false;
    Part targetPart = null;
    AbstractPhysicalComponent targetType = null;
    if (target instanceof Part)
      targetPart = (Part)target;
    else if (target instanceof AbstractPhysicalComponent && allowTypeDeployment)
      targetType = (AbstractPhysicalComponent)target;
    else
      return false;
    if (targetPart != null) {
      // Retrieve deployment links from target and check for a relevant one
      Collection<AbstractDeploymentLink> deployments = targetPart.getDeployingLinks();
      for (AbstractDeploymentLink deployment : deployments) {
        DeploymentTarget currentLocation = deployment.getLocation();
        if (currentLocation == locationPart ||
            currentLocation instanceof Part &&
            ((Part)currentLocation).getAbstractType() == locationType)
          return true;
      }
      // None found
      return false;
    } else if (locationPart != null) {
      // Retrieve deployment links from container and check for a relevant one
      Collection<AbstractDeploymentLink> deployments = locationPart.getDeploymentLinks();
      for (AbstractDeploymentLink deployment : deployments) {
        DeployableElement currentElement = deployment.getDeployedElement();
        if (currentElement instanceof Part &&
            ((Part)currentElement).getAbstractType() == targetType)
          return true;
      }
      // None found
      return false;
    }
    return true;
  }

  /**
   * Return whether the given target can be represented as a subcomponent of
   * the given container
   * @param container_p a potentially null element
   * @param target_p a potentially null element
   */
  private boolean checkSubcomponent(EObject container_p, EObject target_p) {
    if (!(container_p instanceof PartitionableElement &&
        (target_p instanceof AbstractType || target_p instanceof Part)))
      return true;
    PartitionableElement container = (PartitionableElement)container_p;
    AbstractType subComponentType;
    if (target_p instanceof Part)
      subComponentType = ((Part)target_p).getAbstractType();
    else
      subComponentType = (AbstractType)target_p;
    for (Partition part : container.getOwnedPartitions()) {
      if (part.getAbstractType() == subComponentType)
        return true;
    }
    return false;
  }

  /**
   * Return whether the semantic element of the given diagram is explicitly
   * represented as a node in the diagram
   * @param diagram_p a non-null diagram
   */
  private boolean diagramTargetIsExplicitlyRepresented(DSemanticDiagram diagram_p) {
    final EObject diagramTarget = diagram_p.getTarget();
    if (diagramTarget == null) return false;
    Collection<AbstractDNode> subNodes = new ArrayList<AbstractDNode>();
    subNodes.addAll(diagram_p.getContainers());
    subNodes.addAll(diagram_p.getNodeListElements());
    subNodes.addAll(diagram_p.getNodes());
    for (AbstractDNode currentNode : subNodes) {
      if (diagramTarget == currentNode.getTarget())
        return true;
    }
    return false;
  }

  /**
   * From a given Capella element contained anywhere in a System Engineering, get the
   * Logical Architecture of the System Engineering
   * @param current_p a potentially null element
   */
  private LogicalArchitecture getLogicalArchitecture(EObject current_p) {
    if (null == current_p)
      return null;
    if (current_p instanceof LogicalArchitecture)
      return (LogicalArchitecture)current_p;
    return getLogicalArchitecture(current_p.eContainer());
  }

  /**
   * Return whether the given element is deployed on another one
   * @param element_p a non-null element
   */
  private boolean isDeployed(EObject element_p) {
    boolean result = false;
    if (element_p instanceof DeployableElement) {
      DeployableElement deployable = (DeployableElement)element_p;
      result = !deployable.getDeployingLinks().isEmpty();
    }
    return result;
  }

  /**
   * Return whether the given mapping represents a deployed component in a PAB
   * @param mapping_p a non-null mapping
   */
  private boolean isDeploymentMapping(AbstractNodeMapping mapping_p) {
    return DEPLOYMENT_MAPPING_NAME.equals(getLowerCaseName(mapping_p));
  }

  /**
   * An ad-hoc method which returns true iff the given mapping is a dummy one
   * which should not be used to create graphical nodes
   * @param mapping_p a non-null mapping
   */
  private boolean isDummyMapping(AbstractNodeMapping mapping_p) {
    boolean result = false;
    if (mapping_p != null && mapping_p.getName() != null) {
      final String name = mapping_p.getName().toLowerCase();
      result = DUMMY_MAPPING_NAMES.contains(name);
      result = result || name.endsWith("_dummy"); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Return whether the given diagram is a Capella Logical Architecture Blank
   * @param diagram_p a non-null diagram
   */
  private boolean isLAB(DDiagram diagram_p) {
    boolean result = false;
    String descName = diagram_p.getDescription().getName();
    if (descName != null)
      // Can't see a better criterion
      result = descName.toLowerCase().contains("logical architecture"); //$NON-NLS-1$
    return result;
  }

  /**
   * Return whether the given mapping represents a physical component in a PAB
   * @param mapping_p a non-null mapping
   */
  private boolean isPhysicalComponentMapping(AbstractNodeMapping mapping_p) {
    return PHYSICAL_COMPONENT_MAPPING_NAME.equals(getLowerCaseName(mapping_p));
  }

  /**
   * Return whether the given mapping may represent a sub-component in an Architecture
   * diagram
   * @param mapping_p a non-null mapping
   */
  private boolean isSubcomponentMapping(AbstractNodeMapping mapping_p) {
    return mapping_p != null && mapping_p.getName() != null &&
        SUBCOMPONENT_MAPPING_NAMES.contains(getLowerCaseName(mapping_p));
  }

  /**
   * Return the name of the given mapping in lower case, or null if not applicable
   * @param mapping_p a non-null mapping
   */
  private String getLowerCaseName(AbstractNodeMapping mapping_p) {
    String result = null;
    if (mapping_p != null && mapping_p.getName() != null)
      result = mapping_p.getName().toLowerCase();
    return result;
  }

  /**
   * Return whether precondition evaluation of the given mapping is relevant
   * @param mapping_p a non-null mapping
   */
  private boolean preconditionCanBeChecked(AbstractNodeMapping mapping_p) {
    return !isDeploymentMapping(mapping_p);
  }

  /**
   * Return whether evaluation of the semantic candidates of the given mapping is relevant
   * @param mapping_p a non-null mapping
   */
  private boolean semanticCandidatesCanBeChecked(AbstractNodeMapping mapping_p) {
    return preconditionCanBeChecked(mapping_p);
  }

}
