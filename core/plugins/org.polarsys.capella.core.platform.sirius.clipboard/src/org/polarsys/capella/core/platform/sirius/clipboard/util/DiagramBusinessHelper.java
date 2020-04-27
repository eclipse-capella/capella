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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalArchitecture;


/**
 * This class defines business rules for interacting with Capella diagrams.
 * Those rules are dependent upon Capella.sirius.analysis.
 * They are necessary in cases where Sirius mappings are permissive regarding business
 * criteria and diagram consistency is enforced by tool usage. Since we seek to build
 * diagrammatic representations by means of the API, we cannot rely
 * on not diagram tools for consistency.
 */
public class DiagramBusinessHelper {

  // Singleton design pattern
  private static final DiagramBusinessHelper INSTANCE = new DiagramBusinessHelper();
  public static DiagramBusinessHelper getInstance() {
    return INSTANCE;
  }

  /** Lowercase names of dummy mappings which should not be used **/
  private static final Collection<String> DUMMY_MAPPING_NAMES =
    Collections.unmodifiableCollection(Arrays.asList(
        "lab sub components"
    ));
  
  /** Lowercase name of component mapping in PAB **/
  private static final String PHYSICAL_COMPONENT_MAPPING_NAME = "pab_pc";

  /** Lowercase name of deployed component mapping in PAB **/
  private static final String DEPLOYMENT_MAPPING_NAME = "pab_deployment";

  /** Lowercase names of subcomponent mappings in architecture diagrams **/
  private static final Collection<String> SUBCOMPONENT_MAPPING_NAMES =
    Collections.unmodifiableCollection(Arrays.asList(
        "lab logical component",
        PHYSICAL_COMPONENT_MAPPING_NAME
    ));
  
  
  private DiagramBusinessHelper() {
    // Forbids instantiation
  }
  
  
  /**
   * Return a replacement for the given semantic element which can be represented
   * in the given diagram
   * @param element_p a non-null semantic element
   * @param diagram_p a non-null diagram
   * @return a non-null element which is either element_p or a replacement
   */
  public EObject getRepresentableElement(EObject element_p, DDiagram diagram_p) {
    EObject result = element_p;
    // Case of a component typing a unique part in an architecture diagram:
    // take the part
    if ((isLab(diagram_p) || isPab(diagram_p)) && element_p instanceof Component) {
      Component component = (Component)element_p;
      if (component.getAbstractTypedElements().size() == 1)
        result = component.getAbstractTypedElements().get(0);
    }
    return result;
  }
  
  /**
   * From a graphical element in a diagram (GEF, GMF or Sirius diagram or
   * diagram element), return the storage element it represents.
   * The storage element may be different from the semantic element being represented,
   * e.g., creating a node in the diagram background means adding a sibling (not
   * a child) of the semantic element represented by the diagram.
   */
  public EObject getRepresentedStorage(Object representation_p) {
    // Semantic element by default
    EObject result = SiriusUtil.getSemanticElement(representation_p);
    // If Part, take its type
    if (result instanceof Part)
      result = ((Part)result).getAbstractType();
    DSemanticDecorator graphicalSource = toSiriusElement(representation_p);
    if (graphicalSource instanceof DSemanticDiagram) {
      DSemanticDiagram diagram = (DSemanticDiagram)graphicalSource;
      if (diagramTargetIsExplicitlyRepresented(diagram)) {
        if (isLab(diagram)) {
          // In a LAB, new elements in the diagram background are stored in the
          // root Logical Component
          EObject rootLc = getLogicalArchitecture(diagram.getTarget()).getSystem();
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
   * From a given Capella element contained anywhere in a System Engineering, get the
   * Logical Architecture of the System Engineering
   */
  private LogicalArchitecture getLogicalArchitecture(EObject current_p) {
    if (null == current_p)
      return null;
    if (current_p instanceof LogicalArchitecture)
      return (LogicalArchitecture)current_p;
    return getLogicalArchitecture(current_p.eContainer());
  }
  
  /**
   * Return whether the given diagram is a Capella Logical Architecture Blank
   */
  private boolean isLab(DDiagram diagram_p) {
    boolean result = false;
    String descName = diagram_p.getDescription().getName();
    if (descName != null)
      // Can't see a better criterion
      result = descName.toLowerCase().contains("logical architecture"); //$NON-NLS-1$
    return result;
  }
  
  /**
   * Return whether the given diagram is a Capella Physical Architecture Blank
   */
  private boolean isPab(DDiagram diagram_p) {
    boolean result = false;
    String descName = diagram_p.getDescription().getName();
    if (descName != null)
      // Can't see a better criterion
      result = descName.toLowerCase().contains("physical architecture"); //$NON-NLS-1$
    return result;
  }
  
  /**
   * From any upper layer element to its embedded Sirius element, if any 
   */
  private DSemanticDecorator toSiriusElement(Object source_p) {
    DSemanticDecorator result = null;
    Object current = source_p;
    if (current instanceof EditPart)
      current = ((EditPart)source_p).getModel();
    if (current instanceof View)
      current = ((View)current).getElement();
    if (current instanceof DSemanticDecorator)
      result = (DSemanticDecorator)current;
    return result;
  }

  /**
   * From any upper layer element to its semantic element, if any 
   */
//  private EObject toSemanticElement(Object source_p) {
//    EObject result = null;
//    DSemanticDecorator decorator = toSiriusElement(source_p);
//    if (decorator != null)
//      result = decorator.getTarget();
//    return result;
//  }

  /**
   * Return whether the semantic element of the given diagram is explicitly
   * represented as a node in the diagram.
   */
  private boolean diagramTargetIsExplicitlyRepresented(
      DSemanticDiagram diagram_p) {
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
   * Return whether the given element conforms to the given mapping's requirements.
   * In addition to standard Sirius mechanisms, this method is based on business criteria.
   * @see SiriusUtil#conformsToMapping(EObject, AbstractNodeMapping, boolean, boolean, EObject)
   * @param semanticElt_p the semantic element to test against the given mapping
   * @param mapping_p the mapping to test again the given semantic element
   * @param considerPrecondition_p whether the precondition of the mapping must be tested
   * @param considerCandidates_p whether the semantic candidates expression of the mapping
   *        must be taken into account
   * @param containerView_p the (optional) expected graphical container of the node that
   *        would be created by instantiation of the given mapping on the given semantic
   *        element
   */
//  public boolean conformsToMapping(EObject semanticElt_p, AbstractNodeMapping mapping_p,
//      boolean considerPrecondition_p, boolean considerCandidates_p, EObject containerView_p) {
//    boolean businessOk = !isDummyMapping(mapping_p);
//    if (businessOk && considerPrecondition_p && considerCandidates_p) {
//      // If full verification, check for business criteria
//      businessOk = businessOk && (!isSubcomponentMapping(mapping_p) ||
//          checkSubcomponent(toSemanticElement(containerView_p), semanticElt_p));
//      businessOk = businessOk && (!isPhysicalComponentMapping(mapping_p) ||
//          !isDeployed(semanticElt_p));
//      businessOk = businessOk && (!isDeploymentMapping(mapping_p) ||
//          checkDeployment(toSemanticElement(containerView_p), semanticElt_p));
//    }
//    boolean checkPrecondition = considerPrecondition_p &&
//      preconditionCanBeChecked(mapping_p);
//    boolean checkSemanticCandidates = considerCandidates_p &&
//      semanticCandidatesCanBeChecked(mapping_p);
//    return businessOk && SiriusUtil.conformsToMapping(semanticElt_p, mapping_p,
//        checkPrecondition, checkSemanticCandidates, containerView_p);
//  }
  
  /**
   * An ad-hoc method which returns true if the given mapping is a dummy one
   * which should not be used to create graphical nodes
   */
//  private boolean isDummyMapping(AbstractNodeMapping mapping_p) {
//    boolean result = false;
//    if (mapping_p != null && mapping_p.getName() != null) {
//      final String name = mapping_p.getName().toLowerCase();
//      result = DUMMY_MAPPING_NAMES.contains(name);
//      result = result || name.endsWith("_dummy");
//    }
//    return result;
//  }

  /**
   * Return whether the given mapping may represent a sub-component in an Architecture
   * diagram
   */
//  private boolean isSubcomponentMapping(AbstractNodeMapping mapping_p) {
//    return mapping_p != null && mapping_p.getName() != null &&
//      SUBCOMPONENT_MAPPING_NAMES.contains(getLowerCaseName(mapping_p));
//  }
  
  /**
   * Return whether the given target can be represented as a subcomponent of
   * the given container
   */
//  private boolean checkSubcomponent(EObject container_p, EObject target_p) {
//    if (!(container_p instanceof PartitionableElement &&
//        (target_p instanceof AbstractType || target_p instanceof Part)))
//      return true;
//    PartitionableElement container = (PartitionableElement)container_p;
//    AbstractType subComponentType;
//    if (target_p instanceof Part)
//      subComponentType = ((Part)target_p).getAbstractType();
//    else
//      subComponentType = (AbstractType)target_p;
//    for (Partition part : container.getOwnedPartitions()) {
//      if (part.getAbstractType() == subComponentType)
//        return true;
//    }
//    return false;
//  }
  
  /**
   * Return whether the given element is deployed on another one
   * @param element_p a non-null element
   */
//  private boolean isDeployed(EObject element_p) {
//    boolean result = false;
//    if (element_p instanceof DeployableElement) {
//      DeployableElement deployable = (DeployableElement)element_p;
//      result = !deployable.getDeployingLinks().isEmpty();
//    }
//    return result;
//  }
  
  /**
   * Return whether the given mapping represents a deployed component in a PAB
   */
//  private boolean isDeploymentMapping(AbstractNodeMapping mapping_p) {
//    return DEPLOYMENT_MAPPING_NAME.equals(getLowerCaseName(mapping_p));
//  }
  
  /**
   * Return whether the given mapping represents a physical component in a PAB
   */
//  private boolean isPhysicalComponentMapping(AbstractNodeMapping mapping_p) {
//    return PHYSICAL_COMPONENT_MAPPING_NAME.equals(getLowerCaseName(mapping_p));
//  }
  
  /**
   * Return whether the given target can be represented as a deployed component within
   * the given container
   */
//  private boolean checkDeployment(EObject container_p, EObject target_p) {
//    Part locationPart = null;
//    AbstractPhysicalComponent locationType = null;
//    if (container_p instanceof Part)
//      locationPart = (Part)container_p;
//    else if (container_p instanceof AbstractPhysicalComponent)
//      locationType = (AbstractPhysicalComponent)container_p;
//    else
//      return true;
//    Part targetPart = null;
//    AbstractPhysicalComponent targetType = null;
//    if (target_p instanceof Part)
//      targetPart = (Part)target_p;
//    else if (target_p instanceof AbstractPhysicalComponent)
//      targetType = (AbstractPhysicalComponent)target_p;
//    else
//      return true;
//    if (targetPart != null) {
//      // Retrieve deployment links from target and check for a relevant one
//      Collection<AbstractDeploymentLink> deployments = targetPart.getDeployingLinks();
//      for (AbstractDeploymentLink deployment : deployments) {
//        DeploymentTarget currentLocation = deployment.getLocation();
//        if (currentLocation == locationPart ||
//            currentLocation instanceof Part &&
//            ((Part)currentLocation).getAbstractType() == locationType)
//          return true;
//      }
//      // None found
//      return false;
//    } else if (locationPart != null) {
//      // Retrieve deployment links from container and check for a relevant one
//      Collection<AbstractDeploymentLink> deployments = locationPart.getDeploymentLinks();
//      for (AbstractDeploymentLink deployment : deployments) {
//        DeployableElement currentElement = deployment.getDeployedElement();
//        if (currentElement instanceof Part &&
//            ((Part)currentElement).getAbstractType() == targetType)
//          return true;
//      }
//      // None found
//      return false;
//    }
//    return true;
//  }
  
  /**
   * Return whether precondition evaluation of the given mapping is relevant
   */
//  private boolean preconditionCanBeChecked(AbstractNodeMapping mapping_p) {
//    return !isDeploymentMapping(mapping_p);
//  }

  /**
   * Return whether evaluation of the semantic candidates of the given mapping is relevant
   */
//  private boolean semanticCandidatesCanBeChecked(AbstractNodeMapping mapping_p) {
//    return preconditionCanBeChecked(mapping_p);
//  }
  
  /**
   * Return the name of the given mapping in lower case, or null if not applicable
   */
//  private String getLowerCaseName(AbstractNodeMapping mapping_p) {
//    String result = null;
//    if (mapping_p != null && mapping_p.getName() != null)
//      result = mapping_p.getName().toLowerCase();
//    return result;
//  }

}
