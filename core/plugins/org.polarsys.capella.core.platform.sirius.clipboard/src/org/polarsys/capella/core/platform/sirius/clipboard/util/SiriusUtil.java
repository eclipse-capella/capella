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

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * Utility class providing services related to Sirius concepts
 */
public final class SiriusUtil {

  private SiriusUtil() {
    // Forbids instantiation
  }

  /**
   * This restricted conformity checking is applicable to elements which are not bound to their model yet. It only
   * considers the domain class of the mapping, ignoring the precondition and candidate expressions.
   */
  // public static boolean conformsToMapping(EObject semanticElt_p, AbstractNodeMapping mapping_p) {
  // return conformsToMapping(semanticElt_p, mapping_p, false, false, null);
  // }

  /**
   * Return whether the given element conforms to the given mapping's requirements. Criteria: domain class, precondition
   * expression, semantic candidates expression. For precondition and semantic candidates to be considered, the target
   * semantic element must belong to the model of the semantic target of the container view.
   * 
   * @param semanticElt_p
   *          the semantic element to test against the given mapping
   * @param mapping_p
   *          the mapping to test again the given semantic element
   * @param considerPrecondition_p
   *          whether the precondition of the mapping must be tested
   * @param considerCandidates_p
   *          whether the semantic candidates expression of the mapping must be taken into account
   * @param containerView_p
   *          the (optional) expected graphical container of the node that would be created by instantiation of the
   *          given mapping on the given semantic element
   */
  // public static boolean conformsToMapping(EObject semanticElt_p, AbstractNodeMapping mapping_p,
  // boolean considerPrecondition_p, boolean considerCandidates_p, EObject containerView_p) {
  // boolean result = false;
  // ModelAccessorsRegistry reg = SiriusPlugin.getDefault().getModelAccessorRegistry();
  // ModelAccessor accessor = reg.getModelAccessor(semanticElt_p);
  // // Check domain class
  // if (null != accessor) {
  // String domainClass = mapping_p.getDomainClass();
  // result = accessor.eInstanceOf(semanticElt_p, domainClass);
  // EObject semanticOfGraphicalContainer =
  // (null == containerView_p)? null: getSemanticElement(containerView_p);
  // // Check precondition
  // if (result && considerPrecondition_p && null != containerView_p) {
  // result = mapping_p.checkPrecondition(semanticElt_p, semanticOfGraphicalContainer, containerView_p);
  // }
  // // Check semantic candidates
  // if (result && considerCandidates_p) {
  // List<EObject> candidates = null;
  // if (null != containerView_p) {
  // if (mapping_p instanceof NodeMapping) {
  // NodeMapping nm = (NodeMapping)mapping_p;
  // candidates = nm.getNodesCandidates(semanticOfGraphicalContainer,
  // semanticOfGraphicalContainer, containerView_p);
  // } else if (mapping_p instanceof ContainerMapping) {
  // ContainerMapping cm = (ContainerMapping)mapping_p;
  // candidates = cm.getNodesCandidates(semanticOfGraphicalContainer, semanticOfGraphicalContainer, containerView_p);
  // }
  // }
  // result = null != candidates && candidates.contains(semanticElt_p);
  // }
  // }
  // return result;
  // }

  /**
   * Reduce a set of mappings to the set of its leaves regarding the import hierarchy.
   */
  public static UniqueEList<AbstractNodeMapping> fiterOutImported(Collection<AbstractNodeMapping> mappings_p) {
    UniqueEList<AbstractNodeMapping> result = new UniqueEList<AbstractNodeMapping>(mappings_p);
    for (AbstractNodeMapping mapping : mappings_p) {
      if (mapping instanceof AbstractMappingImport) {
        AbstractMappingImport importMapping = (AbstractMappingImport) mapping;
        AbstractNodeMapping imported = null;
        if (importMapping instanceof NodeMappingImport)
          imported = ((NodeMappingImport) importMapping).getImportedMapping();
        else if (importMapping instanceof ContainerMappingImport)
          imported = ((ContainerMappingImport) importMapping).getImportedMapping();
        if (null != imported)
          result.remove(imported);
      }
    }
    return result;
  }

  /**
   * Get all mappings which are directly applicable within the given graphical context.
   */
  public static UniqueEList<AbstractNodeMapping> getApplicableMappingsIn(DRefreshable graphicalContext_p) {
    UniqueEList<AbstractNodeMapping> result = null;
    if (graphicalContext_p instanceof DDiagram)
      result = getApplicableMappingsInDiagram((DDiagram) graphicalContext_p);
    else if (graphicalContext_p instanceof DNodeContainer)
      result = getApplicableMappingsInContainer((DNodeContainer) graphicalContext_p);
    return result;
  }

  /**
   * Get all mappings which are directly applicable within a diagram.
   */
  public static UniqueEList<AbstractNodeMapping> getApplicableMappingsInDiagram(DDiagram diagram_p) {
    UniqueEList<AbstractNodeMapping> result = new UniqueEList<AbstractNodeMapping>();
    DiagramDescription desc = diagram_p.getDescription();
    result.addAll(desc.getContainerMappings());
    result.addAll(desc.getNodeMappings());
    for (DiagramElementMapping reusedMapping : desc.getReusedMappings()) {
      if (reusedMapping instanceof AbstractNodeMapping) {
        result.add((AbstractNodeMapping) reusedMapping);
      }
    }
    // For all layers of the DiagramDescription, get all
    // AbstractNodeMappings which deal with the root of diagrams
    EList<Layer> layers = LayerHelper.getAllLayers(desc);
    for (Layer layer : layers) {
      result.addAll(layer.getContainerMappings());
      result.addAll(layer.getNodeMappings());
      for (DiagramElementMapping reusedMapping : layer.getReusedMappings()) {
        if (reusedMapping instanceof AbstractNodeMapping) {
          result.add((AbstractNodeMapping) reusedMapping);
        }
      }
    }
    return result;
  }

  /**
   * Get all mappings which are directly applicable within a DNodeContainer.
   */
  public static UniqueEList<AbstractNodeMapping> getApplicableMappingsInContainer(DNodeContainer container_p) {
    UniqueEList<AbstractNodeMapping> result = new UniqueEList<AbstractNodeMapping>();
    ContainerMapping mapping = container_p.getActualMapping();
    result.addAll(mapping.getReusedNodeMappings());
    result.addAll(mapping.getSubNodeMappings());
    result.addAll(mapping.getReusedContainerMappings());
    result.addAll(mapping.getSubContainerMappings());
    return result;
  }

  /**
   * Return the DDiagram which is the parent of the given Sirius element, or the Sirius element itself if it is a
   * DDiagram
   */
  public static DDiagram getDiagram(EObject context_p) {
    DDiagram result = null;
    if (context_p instanceof DDiagram) {
      result = (DDiagram) context_p;
    } else if (context_p instanceof DDiagramElement) {
      result = ((DDiagramElement) context_p).getParentDiagram();
    }
    return result;
  }

  /**
   * Get the owning diagram of the given element, if any.
   */
  public static DDiagram getOwningDiagram(EObject element_p) {
    if (null == element_p)
      return null;
    if (element_p instanceof DDiagram)
      return (DDiagram) element_p;
    return getOwningDiagram(element_p.eContainer());
  }

  /**
   * From a selectable element in a diagram to its semantic element
   */
  public static EObject getSemanticElement(Object source_p) {
    if (null == source_p)
      return null;
    Object elt = source_p;
    if (elt instanceof EditPart)
      elt = ((EditPart) elt).getModel();
    if (elt instanceof View)
      elt = ((View) elt).getElement();
    if (elt instanceof DSemanticDecorator)
      elt = ((DSemanticDecorator) elt).getTarget();
    if (elt instanceof EObject)
      return (EObject) elt;
    return null;
  }

  /**
   * Return whether the given Sirius node is a bordered node
   */
  public static boolean isBorderedNode(DNode siriusNode_p) {
    boolean result = false;
    NodeMapping mapping = siriusNode_p.getActualMapping();
    if (mapping != null)
      result = mapping.eContainmentFeature() == DescriptionPackage.eINSTANCE
          .getAbstractNodeMapping_BorderedNodeMappings();
    return result;
  }

  /**
   * Return whether the given Sirius element is or is in a Sequence Diagram
   */
  public static boolean isInSequenceDiagram(EObject context_p) {
    return getDiagram(context_p) instanceof SequenceDDiagram;
  }

  /**
   * Return whether the given Sirius element is or is in a diagram whose layout should not be altered other than by the
   * diagram GUI
   */
  public static boolean layoutIsConstrained(EObject context_p) {
    return isInSequenceDiagram(context_p);
  }

  /**
   * Make Sirius refresh the diagram of the given diagram element
   */
  public static boolean refreshDiagram(DDiagramElement diagramElement_p) {
    return refreshDiagram(getDiagram(diagramElement_p));
  }

  /**
   * Make Sirius refresh the given diagram
   */
  public static boolean refreshDiagram(DDiagram diagram_p) {
    boolean result = false;
    if (diagram_p != null) {
      DialectManager.INSTANCE.refresh(diagram_p, new NullProgressMonitor());
      result = true;
    }
    return result;
  }

  /**
   * 
   * @param element
   * @return the name of the diagram owning the given element.
   */
  public static String getContextDiagram(EObject element) {
    DDiagram diagram = getOwningDiagram(element);
    if (diagram != null) {
      return diagram.getDescription().getName();
    }
    return null;
  }
}
