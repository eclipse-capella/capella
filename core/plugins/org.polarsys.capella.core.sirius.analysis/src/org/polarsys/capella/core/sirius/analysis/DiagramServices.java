/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.AbstractNodeMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.AbstractDNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 */
public class DiagramServices {
  private static DiagramServices singleton = null;

  public static DiagramServices getDiagramServices() {
    if (singleton == null) {
      singleton = new DiagramServices();
    }
    return singleton;
  }

  /**
   * Return the EList of owned diagram elements of the given container_p
   * @param container_p
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(DragAndDropTarget container_p) {
    if (container_p instanceof DDiagram) {
      return ((DDiagram) container_p).getOwnedDiagramElements();
    } else if (container_p instanceof DNodeContainer) {
      return ((DNodeContainer) container_p).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Return the EList of owned diagram elements of the given container_p
   * @param container_p
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(DContainer container_p) {
    if (container_p instanceof DDiagram) {
      return ((DDiagram) container_p).getOwnedDiagramElements();
    } else if (container_p instanceof DNodeContainer) {
      return ((DNodeContainer) container_p).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodes(DSemanticDecorator element_p) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element_p instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element_p).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element_p instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element_p).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element_p instanceof AbstractDNode) {
      views.addAll(((AbstractDNode) element_p).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodeListElements(DSemanticDecorator element_p) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element_p instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element_p).getOwnedDiagramElements()) {
        if (view instanceof DNodeListElement) {
          views.add(view);
        }
      }
    } else if (element_p instanceof DNodeList) {
      views.addAll(((DNodeList) element_p).getOwnedElements());
    }
    return views;
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedContainers(DSemanticDecorator element_p) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element_p instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element_p).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    } else if (element_p instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element_p).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    }
    return views;
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getAllAbstractNodes(DSemanticDecorator element_p) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element_p instanceof DDiagram) {
      for (DDiagramElement view : getDiagramElements((DDiagram) element_p)) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    } else if (element_p instanceof DDiagramElement) {
      for (DDiagramElement view : getDiagramElements((DDiagramElement) element_p)) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    }
    return views;
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedAbstractNodes(DSemanticDecorator element_p) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element_p instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element_p).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    } else if (element_p instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element_p).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    }

    if (element_p instanceof AbstractDNode) {
      views.addAll(((AbstractDNode) element_p).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * @param eClass_p
   * @param diagram_p
   * @return
   */
  public DiagramElementMapping getMapping(String eClass_p, DDiagram diagram_p) {
    String mappingName = null;
    if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      if (eClass_p.equals(CtxPackage.Literals.ACTOR.getName())) {
        mappingName = IMappingNameConstants.MCB_COMPONENT_MAPPING_NAME;
      }
      if (eClass_p.equals(CtxPackage.Literals.CAPABILITY.getName())) {
        mappingName = IMappingNameConstants.MCB_CAPABILITY_MAPPING_NAME;
      }
      if (eClass_p.equals(CtxPackage.Literals.MISSION.getName())) {
        mappingName = IMappingNameConstants.MCB_MISSION_MAPPING_NAME;
      }
    }
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  public List<NodeMapping> getAllBorderedNodeMapping(AbstractNodeMapping mapping_p) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping_p.getBorderedNodeMappings());
    if (mapping_p instanceof ContainerMapping) {
      ContainerMapping currentContainerMapping = (ContainerMapping) mapping_p;
      for (ContainerMapping aMapping : currentContainerMapping.getSubContainerMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
      for (NodeMapping aMapping : currentContainerMapping.getSubNodeMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
    }
    return returnedList;
  }

  public List<NodeMapping> getAllNodeMappings(ContainerMapping mapping_p) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping_p.getSubNodeMappings());
    for (ContainerMapping aMapping : mapping_p.getSubContainerMappings()) {
      returnedList.addAll(getAllNodeMappings(aMapping));
    }
    return returnedList;
  }

  public List<ContainerMapping> getAllContainerMappings(ContainerMapping mapping_p) {
    List<ContainerMapping> returnedList = new ArrayList<ContainerMapping>();
    returnedList.add(mapping_p);
    for (ContainerMapping aMapping : mapping_p.getSubContainerMappings()) {
      returnedList.addAll(getAllContainerMappings(aMapping));
    }
    return returnedList;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DiagramDescription description_p, String mappingName_p) {

    for (NodeMapping nodeMapping : description_p.getAllNodeMappings()) {
      if (nodeMapping.getName().equals(mappingName_p)) {
        return nodeMapping;
      }
      for (NodeMapping borderedMapping : nodeMapping.getAllBorderedNodeMappings()) {
        if (borderedMapping.getName().equals(mappingName_p)) {
          return borderedMapping;
        }
      }
    }

    for (ContainerMapping nodeMapping : description_p.getAllContainerMappings()) {
      if (nodeMapping.getName().equals(mappingName_p)) {
        return nodeMapping;
      }
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        if ((mapping instanceof AbstractNodeMapping)) {

          if (mapping.getName().equals(mappingName_p)) {
            return (AbstractNodeMapping) mapping;

          }
          for (NodeMapping borderedMapping : ((AbstractNodeMapping) mapping).getAllBorderedNodeMappings()) {
            if (borderedMapping.getName().equals(mappingName_p)) {
              return borderedMapping;
            }
          }
        }

      }
    }
    return null;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DDiagram diagram, String mappingName_p) {
    final DiagramDescription description = diagram.getDescription();
    return getAbstractNodeMapping(description, mappingName_p);
  }

  public NodeMapping getNodeMapping(final DDiagram diagram, String mappingName_p) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : description.getAllNodeMappings()) {
      if (nodeMapping.getName().equals(mappingName_p)) {
        return nodeMapping;
      }
    }
    for (ContainerMapping aMapping : description.getAllContainerMappings()) {
      for (NodeMapping aNodeMapping : getAllNodeMappings(aMapping)) {
        if (aNodeMapping.getName().equals(mappingName_p)) {
          return aNodeMapping;
        }
      }
    }
    return null;
  }

  public List<NodeMapping> getBorderedNodeMapping(final DDiagram diagram, List<String> mappingNames_p) {
    List<NodeMapping> result = new ArrayList<NodeMapping>();

    for (String name : mappingNames_p) {
      result.add(getBorderedNodeMapping(diagram, name));
    }
    return result;
  }

  public NodeMapping getBorderedNodeMapping(final DDiagram diagram, String mappingName_p) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : description.getAllNodeMappings()) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(nodeMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName_p)) {
          return aBorderedNodeMapping;
        }
      }
    }
    for (ContainerMapping aMapping : description.getAllContainerMappings()) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(aMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName_p)) {
          return aBorderedNodeMapping;
        }
      }
    }
    return null;
  }

  public ContainerMapping getContainerMapping(final DDiagram diagram, String mappingName_p) {
    final DiagramDescription description = diagram.getDescription();
    for (ContainerMapping aContainerMapping : description.getAllContainerMappings()) {
      for (ContainerMapping aSubContainerMapping : getAllContainerMappings(aContainerMapping)) {
        if (aSubContainerMapping.getName().equals(mappingName_p)) {
          return aSubContainerMapping;
        }
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DiagramDescription description_p, String mappingName_p) {
    for (final EdgeMapping edgeMapping : description_p.getAllEdgeMappings()) {
      if (edgeMapping.getName().equals(mappingName_p)) {
        return edgeMapping;
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DDiagram diagram_p, String mappingName_p) {
    final DiagramDescription description = diagram_p.getDescription();
    return getEdgeMapping(description, mappingName_p);
  }

  public DNode createNode(NodeMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    return (DNode)createAbstractDNode(mapping_p, modelElement_p, container_p, diagram_p);
  }

  public DNode createBorderedNode(NodeMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    final DDiagram diagram = diagram_p;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement_p);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement_p);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram_p);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping_p, modelElement_p, container_p);
    return (DNode) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diagram), nodeCandidate, true);
  }

  public AbstractDNode createDNodeListElement(NodeMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    final DDiagram diagram = diagram_p;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement_p);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement_p);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram_p);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping_p, modelElement_p, container_p);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diagram), nodeCandidate, false, -1);
  }

  public DNodeContainer createContainer(ContainerMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    final DDiagram diagram = diagram_p;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement_p);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement_p);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram_p);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping_p, modelElement_p, container_p);
    return (DNodeContainer) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diagram), nodeCandidate, false);
  }

  @Deprecated
  public AbstractDNode createAbstractDNodeContainer(AbstractNodeMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    return createAbstractDNode(mapping_p, modelElement_p, container_p, diagram_p);
  }

  public boolean isBorderedNodeMapping(DiagramElementMapping mapping_p) {
    return (mapping_p != null)
           && org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS.equals(mapping_p
               .eContainingFeature());
  }

  /**
   * Evaluate precondition of the given edge mapping.
   */
  public boolean evaluateEdgePrecondition(EdgeMapping edgeMapping, DDiagram diagram, EObject semantic, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new EdgeMappingQuery(edgeMapping).evaluatePrecondition((DSemanticDiagram) diagram, (DragAndDropTarget) diagram, interpreter, semantic, sourceView, targetView);
  }
  
  /**
   * Evaluate precondition of the given node mapping.
   */
  public boolean evaluateNodePrecondition(AbstractNodeMapping nodeMapping, DDiagram diagram, DSemanticDecorator containerView, EObject semantic) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new AbstractNodeMappingQuery(nodeMapping).evaluatePrecondition((DSemanticDiagram) diagram, (DragAndDropTarget) containerView, interpreter, semantic);
  }

  public AbstractDNode createAbstractDNode(AbstractNodeMapping mapping_p, EObject modelElement_p, DragAndDropTarget container_p, DDiagram diagram_p) {
    final DDiagram diagram = diagram_p;
    if (mapping_p == null) {
      return null;
    }

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement_p);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement_p);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram_p);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping_p, modelElement_p, container_p);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diagram), nodeCandidate, isBorderedNodeMapping(mapping_p));
  }

  public DEdge createEdge(EdgeMapping mapping_p, EdgeTarget sourceView_p, EdgeTarget targetView_p, EObject semanticObject_p) {
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticObject_p);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticObject_p);

    if (mapping_p == null) {
      return null;
    }

    if ((sourceView_p == null) || (targetView_p == null)) {
      return null;
    }

    DEdgeCandidate edgeCandidate = new DEdgeCandidate(mapping_p, semanticObject_p, sourceView_p, targetView_p);

    final DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    /* maps for decorations */
    final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
    final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

    /* create the mapping to edge targets map */
    final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = new HashMap<DiagramElementMapping, Collection<EdgeTarget>>();

    DDiagramElement sourceElement = null;
    DDiagramElement targetElement = null;
    DiagramElementMapping sourceMapping = null;
    DiagramElementMapping targetMapping = null;

    if (sourceView_p instanceof DDiagramElement) {
      sourceElement = (DDiagramElement) sourceView_p;
      sourceMapping = sourceElement.getDiagramElementMapping();
    }

    if (targetView_p instanceof DDiagramElement) {
      targetElement = (DDiagramElement) targetView_p;
      targetMapping = targetElement.getDiagramElementMapping();
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.put(sourceMapping, new ArrayList<EdgeTarget>());
    }
    if ((targetMapping != null) && !targetMapping.equals(sourceMapping)) {
      mappingsToEdgeTargets.put(targetMapping, new ArrayList<EdgeTarget>());
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.get(sourceMapping).add(sourceView_p);
    }
    if ((targetMapping != null) && !sourceView_p.equals(targetView_p)) {
      mappingsToEdgeTargets.get(targetMapping).add(targetView_p);
    }

    diagramSync.computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
    return elementSync.createNewEdge(getMappingManager((DSemanticDiagram) diagram), edgeCandidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration,
        edgeToSemanticBasedDecoration);
  }

  public boolean isHiddenLabel(DDiagramElement context_p) {
    return new DDiagramElementQuery(context_p).isLabelHidden();
  }

  public EObject hideLabel(DDiagramElement context_p) {
    HideFilterHelper.INSTANCE.hideLabel(context_p);
    return context_p;
  }

  public boolean isHidden(DDiagramElement context_p) {
    return new DDiagramElementQuery(context_p).isHidden();
  }

  public EObject hide(DDiagramElement context_p) {
    HideFilterHelper.INSTANCE.hide(context_p);
    return context_p;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isVisible(DDiagramElement edge) {
    return !isHidden(edge);
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isOnDiagram(DNodeContainer diagramElement, EObject semantic) {
    for (DDiagramElement element : diagramElement.getContainers()) {
      if (element.getTarget() == semantic) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isOnDiagram(DDiagram diagram, EObject semantic) {
    for (DDiagramElement element : getDiagramElements(diagram)) {
      if (element.getTarget() == semantic) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an element is onto diagram
   */
  public EObject getDiagramElement(DDiagram diagram, EObject semantic) {
    Collection<DSemanticDecorator> views = getDiagramElements(diagram, semantic);
    if (!views.isEmpty()) {
      return views.iterator().next();
    }
    return null;
  }

  /**
   * Returns a list of all diagram elements for the given view.
   * @param diagram_p
   * @param semantic
   * @return
   */
  public Collection<DSemanticDecorator> getDiagramElements(DRepresentation diagram_p, EObject semantic) {
    Collection<DSemanticDecorator> views = new ArrayList<DSemanticDecorator>();
    Session session = SessionManager.INSTANCE.getSession(semantic);
    for (Setting setting : session.getSemanticCrossReferencer().getInverseReferences(semantic)) {
      if (ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET.equals(setting.getEStructuralFeature())) {
        DSemanticDecorator decorator = (DSemanticDecorator) setting.getEObject();
        DRepresentation diagram = DiagramHelper.getService().getRepresentation(decorator);
        if ((diagram_p == null) || diagram_p.equals(diagram)) {
          views.add(decorator);
        }
      }
    }
    return views;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isEdgeOnDiagram(EdgeTarget sourceView, EdgeTarget targetView, EObject semantic) {
    return getEdgeOnDiagram(sourceView, targetView, semantic) != null;
  }

  /**
   * Checks if an element is onto diagram
   */
  public DEdge getEdgeOnDiagram(EdgeTarget sourceView, EdgeTarget targetView, EObject semantic) {
    if (sourceView != null) {
      if (targetView != null) {
        DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

        for (DEdge edge : (sourceView).getOutgoingEdges()) {
          if ((diagram != null) && diagram.getEdges().contains(edge)) {
            if ((edge.getTarget() != null) && targetView.equals(edge.getTargetNode()) && edge.getTarget().equals(semantic)) {
              return edge;
            }
          }
        }
      }
    }
    return null;
  }

  /**
   * remove a Node view
   * @param node_p a node
   */
  public void removeNodeView(DNode node_p) {
    EObject container = node_p.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node_p);
      }
      if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node_p)) {
          nodeContainer.getOwnedDiagramElements().remove(node_p);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node_p)) {
          nodeContainer.getOwnedBorderedNodes().remove(node_p);
        }
      }
      if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node_p);
      }
    }
  }

  public void removeNodeListElementView(AbstractDNode node_p) {
    EObject container = node_p.eContainer();
    if ((container != null) && (container instanceof DNodeList)) {
      ((DNodeList) container).getOwnedElements().remove(node_p);
    }
  }

  public void removeAbstractDNodeView(AbstractDNode node_p) {
    EObject container = node_p.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node_p);
      } else if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node_p)) {
          nodeContainer.getOwnedDiagramElements().remove(node_p);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node_p)) {
          nodeContainer.getOwnedBorderedNodes().remove(node_p);
        }
      } else if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node_p);
      } else if (container instanceof DNodeList) {
        ((DNodeList) container).getOwnedElements().remove(node_p);
      }
    }
  }

  /**
   * remove a container View
   * @param container_p a container
   */
  public void removeContainerView(DContainer container_p) {
    EObject container = container_p.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(container_p);
      }
      if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(container_p)) {
          nodeContainer.getOwnedDiagramElements().remove(container_p);
        }
      }
    }
  }

  /**
   * remove an edge from a diagram
   * @param anEdge the edge to remove from diagram
   */
  public void removeEdgeView(DEdge anEdge) {

    EObject container = anEdge.eContainer();
    if ((container != null) && (container instanceof DDiagram)) {
      if (anEdge.getSourceNode() != null) {
        anEdge.getSourceNode().getIncomingEdges().remove(anEdge);
        anEdge.getSourceNode().getOutgoingEdges().remove(anEdge);
      }
      if (anEdge.getTargetNode() != null) {
        anEdge.getTargetNode().getOutgoingEdges().remove(anEdge);
        anEdge.getTargetNode().getIncomingEdges().remove(anEdge);
      }
      ((DDiagram) container).getOwnedDiagramElements().remove(anEdge);
    }
  }

  /**
   * Check if element used given mapping or use a sub mapping of the current mapping (mapping imports) We should use this method to ensure tool are working with
   * viewpoint extensions
   * @param element_p
   * @param mappind_p
   * @return
   */
  public boolean isMapping(DDiagramElement element_p, DiagramElementMapping mapping_p) {
    return (mapping_p != null) && new DiagramElementMappingQuery(mapping_p).isInstanceOf(element_p);
  }

  /**
   * This method tests if a Node is a BorderedNode
   * @param node_p : a DNode in a diagram
   * @return true if the current node is a borderedNode
   */
  public boolean isABorderedNode(AbstractDNode node_p) {
    if (null == node_p) {
      return false;
    }
    EObject container = node_p.eContainer();
    if (null == container) {
      return false;
    }
    if (container instanceof DDiagram) {
      return false;
    }
    if (container instanceof AbstractDNode) {
      AbstractDNode nodeContainer = (AbstractDNode) container;
      return nodeContainer.getOwnedBorderedNodes().contains(node_p);
    }
    return false;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node_p, DDiagram diagram_p) {
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node_p.getIncomingEdges());
    returnedSet.retainAll(diagram_p.getEdges());
    return returnedSet;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node_p) {
    DDiagram diagram_p = CapellaServices.getService().getDiagramContainer(node_p);
    return getIncomingEdges(node_p, diagram_p);
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node_p, DDiagram diagram_p) {
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node_p.getOutgoingEdges());
    returnedSet.retainAll(diagram_p.getEdges());
    return returnedSet;
  }

  /**
   * Returns candidates for the source of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeSourceCandidates(EdgeMapping edgeMapping, EObject context_p, DDiagram diagram) {
    return getEdgeMappingHelper(context_p).getEdgeSourceCandidates(edgeMapping, context_p, diagram);
  }

  /**
   * Returns candidates for the target of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeTargetCandidates(EdgeMapping edgeMapping, EObject context_p, DDiagram diagram) {
    return getEdgeMappingHelper(context_p).getEdgeTargetCandidates(edgeMapping, context_p, diagram);
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node_p);
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node_p.getOutgoingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  /**
   * An iterator to browse all diagram elements of a diagram. In fact, diagram.getDiagramElements browse one time the diagram to retrieve elements
   */
  class DiagramIterator implements Iterator<DDiagramElement> {

    LinkedList<DDiagramElement> elements;

    DiagramElementMapping mapping;

    EClass clazz;
    
    
    /**
     * @param diagram_p
     */
    public DiagramIterator(DDiagram diagram_p) {
      elements = new LinkedList<DDiagramElement>();
      if (diagram_p != null) {
        elements.addAll(diagram_p.getOwnedDiagramElements());
      }
    }

    public DiagramIterator(DDiagram diagram_p, DiagramElementMapping mapping_p) {
      this(diagram_p);
      mapping = mapping_p;
      clazz = CapellaServices.getService().getDomainClass(diagram_p, mapping_p);
    }

    public DiagramIterator(DDiagramElement diagramElement_p) {
      elements = new LinkedList<DDiagramElement>();

      if (diagramElement_p instanceof AbstractDNode) {
        elements.addAll(((AbstractDNode) diagramElement_p).getOwnedBorderedNodes());
      }
      if (diagramElement_p instanceof DNodeContainer) {
        elements.addAll(((DNodeContainer) diagramElement_p).getOwnedDiagramElements());
      }
      if (diagramElement_p instanceof DNodeList) {
        elements.addAll(((DNodeList) diagramElement_p).getOwnedElements());
      }
    }

    public DiagramIterator(DDiagramElement diagramElement_p, DiagramElementMapping mapping_p) {
      this(diagramElement_p);
      mapping = mapping_p;
      clazz = CapellaServices.getService().getDomainClass(diagramElement_p, mapping_p);
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
      if (mapping == null) {
        return elements.size() > 0;
      }
      if (elements.size() == 0) {
        return false;
      }

      DDiagramElement element = elements.getFirst();
      if (validMapping(mapping, element)) {
        return true;
      }

      LinkedList<DDiagramElement> nexts = new LinkedList<DDiagramElement>();
      nexts.addAll(elements);
      while (nexts.size() > 0) {
        DDiagramElement next = nexts.removeFirst();
        if (validMapping(mapping, next)) {
          return true;
        }
        nexts.addAll(getNexts(next));
      }
      return false;
    }

    protected Collection<DDiagramElement> getNexts(DDiagramElement diagramElement_p) {

      List<DDiagramElement> element = new ArrayList<DDiagramElement>();

      if (diagramElement_p instanceof AbstractDNode) {
        element.addAll(((AbstractDNode) diagramElement_p).getOwnedBorderedNodes());
      }
      if (diagramElement_p instanceof DNodeContainer) {
        element.addAll(((DNodeContainer) diagramElement_p).getOwnedDiagramElements());
      }
      if (diagramElement_p instanceof DNodeList) {
        element.addAll(((DNodeList) diagramElement_p).getOwnedElements());
      }

      return element;
    }

    /**
     * @see java.util.Iterator#next()
     */
    public DDiagramElement next() {
      if (hasNext()) {
        DDiagramElement element = elements.removeFirst();
        elements.addAll(getNexts(element));
        if ((mapping == null) || validMapping(mapping, element)) {
          return element;
        }
        return next();
      }
      return null;
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove() {
      if (hasNext()) {
        next();
      }
    }

    public boolean validMapping(DiagramElementMapping mapping, DDiagramElement element) {
      return isSameDomain(mapping, element) && mapping.equals(element.getDiagramElementMapping());
    }

    public boolean isSameDomain(DiagramElementMapping mapping, DDiagramElement element) {
      if (clazz == null) {
        return true;
      } else if (clazz.isInstance(element.getTarget())) {
        return true;
      }
      return false;
    }

  }

  /**
   * An optimized version of diagram.getDiagramElements()
   */
  public Iterable<DDiagramElement> getDiagramElements(DDiagram diagram_p) {
    final DiagramIterator iterator = new DiagramIterator(diagram_p);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement diagramElement_p) {
    final DiagramIterator iterator = new DiagramIterator(diagramElement_p);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  public Iterable<DDiagramElement> getDiagramElements(DDiagram diagram_p, DiagramElementMapping mapping_p) {
    final DiagramIterator iterator = new DiagramIterator(diagram_p, mapping_p);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement diagramElement_p, DiagramElementMapping mapping_p) {
    final DiagramIterator iterator = new DiagramIterator(diagramElement_p, mapping_p);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  /**
   * An optimized version of SetOf<diagram.getDiagramElements()>
   */
  public Set<DDiagramElement> getSetOfDiagramElements(DDiagram diagram_p) {
    Set<DDiagramElement> set = new HashSet<DDiagramElement>();
    for (DDiagramElement element : getDiagramElements(diagram_p)) {
      set.add(element);
    }
    return set;
  }

  public Collection<EObject> getOwnedDiagramElementsTarget(DSemanticDecorator element) {
    Set<EObject> set = new HashSet<EObject>();
    List<DDiagramElement> ownedElements = null;

    if (element instanceof DDiagram) {
      ownedElements = ((DDiagram) element).getOwnedDiagramElements();
    } else if (element instanceof DDiagramElement) {
      ownedElements = ((DNodeContainer) element).getOwnedDiagramElements();
    }

    if (ownedElements != null) {
      for (DDiagramElement owned : ownedElements) {
        set.add(owned.getTarget());
      }
    }

    return set;

  }

  /**
   * An optimized version of SetOf<diagram.getDiagramElements().target>
   */
  public Set<EObject> getSetOfDiagramElementsTarget(DDiagram diagram_p) {
    Set<EObject> set = new HashSet<EObject>();
    for (DDiagramElement element : getDiagramElements(diagram_p)) {
      set.add(element.getTarget());
    }
    set.remove(null); // avoid null target if used in a beforeRefresh method
    return set;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DSemanticDecorator> getMapOfDiagramElements(DDiagram diagram_p) {
    Map<EObject, DSemanticDecorator> map = new HashMap<EObject, DSemanticDecorator>();
    for (DDiagramElement element : getDiagramElements(diagram_p)) {
      map.put(element.getTarget(), element);
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DragAndDropTarget> getMapOfDiagramNodes(DDiagram diagram_p) {
    Map<EObject, DragAndDropTarget> map = new HashMap<EObject, DragAndDropTarget>();
    for (DDiagramElement element : getDiagramElements(diagram_p)) {
      if (element instanceof DragAndDropTarget) {
        map.put(element.getTarget(), (DragAndDropTarget) element);
      }
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * @param view_p a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers contained in view_p
   */
  public List<DNodeContainer> getAllContainers(EObject view_p) {
    List<DNodeContainer> returnedList = new ArrayList<DNodeContainer>();
    if (view_p instanceof DDiagram) {
      for (AbstractDNode aContainer : ((DDiagram) view_p).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
    }
    if (view_p instanceof DNodeContainer) {
      for (AbstractDNode aContainer : ((DNodeContainer) view_p).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
      return returnedList;
    }
    return returnedList;
  }

  /**
   * @param view_p a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers and nodeLists contained in view_p
   */
  public List<DContainer> getAllContainersAndNodeLists(EObject view_p) {
    List<DContainer> returnedList = new ArrayList<DContainer>();
    if (view_p instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view_p).getContainers());
    }
    if (view_p instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view_p).getContainers());
    }
    return returnedList;
  }

  /**
   * @param view_p a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the nodes contained in view_p
   */
  public List<DNode> getAllNodes(EObject view_p) {
    List<DNode> returnedList = new ArrayList<DNode>();

    if (view_p instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view_p).getNodes());

    } else if (view_p instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view_p).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view_p).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode)) {
          returnedList.add((DNode) dDiagramElement);
        }
      }

    } else if (view_p instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view_p).getNodes());

    } else if (view_p instanceof DNode) {
      returnedList.addAll(((DNode) view_p).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  /**
   * Select an element in the current diagram Element must exist before calling the tool. a newly created view will not be selected since GMF layer is not
   * created before the end of the tool.
   */
  public void selectElementInDiagram(DSemanticDecorator newTarget_p) {
    Object selectedElement = newTarget_p.getTarget();

    if ((null != selectedElement) && (newTarget_p instanceof DDiagramElement)) {
      IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IGraphicalEditPart graphicalEditPart = null;

      if ((null != activeEditor) && (activeEditor instanceof DiagramEditor)) {
        DiagramEditor diagramEditor = (DiagramEditor) activeEditor;
        // Get the graphical viewer.
        IDiagramGraphicalViewer diagramGraphicalViewer = diagramEditor.getDiagramGraphicalViewer();
        // Check it is a Sirius one.
        if (diagramGraphicalViewer instanceof IDiagramDialectGraphicalViewer) {
          IDiagramDialectGraphicalViewer dialectViewer = (IDiagramDialectGraphicalViewer) diagramGraphicalViewer;

          // Search all edit parts linked to selected object.
          List<IGraphicalEditPart> allEditParts = dialectViewer.findEditPartsForElement((EObject) selectedElement, IGraphicalEditPart.class);
          // Iterate over retrieved edit parts to remove the ones related to 'label' edit part.
          for (Iterator<IGraphicalEditPart> iterator = allEditParts.iterator(); iterator.hasNext();) {
            IGraphicalEditPart editPart = iterator.next();

            // Filter out label edit part.
            if (editPart instanceof IDiagramNameEditPart) {
              iterator.remove();
            }

            Object model = editPart.getModel();
            if (model instanceof View) {
              View view = (View) model;
              EObject element = view.getElement();
              if (newTarget_p.equals(element)) {
                graphicalEditPart = editPart;
                break;
              }
            }
          }
        }

        // Select the found graphical edit part.
        if (null != graphicalEditPart) {
          diagramGraphicalViewer.select(graphicalEditPart);
          diagramGraphicalViewer.reveal(graphicalEditPart);
        }
      }
    }
  }

  /**
   * Returns a new list from given list with only DNodeContainers
   * @param elements_p
   * @return
   */
  public Collection<DNodeContainer> filterNodeContainers(Collection<DDiagramElement> elements_p) {
    List<DNodeContainer> edges = new ArrayList<DNodeContainer>();
    if (elements_p == null) {
      return edges;
    }
    for (DDiagramElement element : elements_p) {
      if (element instanceof DNodeContainer) {
        edges.add((DNodeContainer) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only AbstractDNode
   * @param elements_p
   * @return
   */
  public List<AbstractDNode> filterNodes(Collection<DDiagramElement> elements_p) {
    List<AbstractDNode> edges = new ArrayList<AbstractDNode>();
    if (elements_p == null) {
      return edges;
    }
    for (DDiagramElement element : elements_p) {
      if (element instanceof AbstractDNode) {
        edges.add((AbstractDNode) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only DEdge
   * @param elements_p
   * @return
   */
  public List<DEdge> filterEdges(Collection<DDiagramElement> elements_p) {
    List<DEdge> edges = new ArrayList<DEdge>();
    if (elements_p == null) {
      return edges;
    }
    for (DDiagramElement element : elements_p) {
      if (element instanceof DEdge) {
        edges.add((DEdge) element);
      }
    }
    return edges;
  }

  /**
   * @param pDiagram
   * @param sourceNode_p
   * @param targetNode_p
   * @param semanticObject_p
   * @param mapping_p
   * @return
   */
  public DEdge findDEdgeElement(DDiagram pDiagram, EdgeTarget sourceNode_p, EdgeTarget targetNode_p, EObject semanticObject_p, EdgeMapping mapping_p) {
    for (DEdge anEdge : pDiagram.getEdgesFromMapping(mapping_p)) {
      if ((anEdge.getTarget() != null) && anEdge.getTarget().equals(semanticObject_p) && anEdge.getSourceNode().equals(sourceNode_p)
          && anEdge.getTargetNode().equals(targetNode_p)) {
        return anEdge;
      }
    }
    return null;
  }

  /**
   * Return Node and NodeListElement contained in given object
   * @param eObjecct_p
   * @return
   */
  @Deprecated
  public List<AbstractDNode> getNodesAndNodeListElements(EObject eObjecct_p) {
    return getAllNodesAndNodeListElements(eObjecct_p);
  }

  /**
   * @param view_p a {@link DDiagram}, a {@link DNodeContainer}, a {@link DNodeList}, a {@link DNode}
   * @return
   */
  public List<AbstractDNode> getAllNodesAndNodeListElements(EObject view_p) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();

    if (view_p instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view_p).getNodes());
      returnedList.addAll(((DDiagram) view_p).getNodeListElements());

    } else if (view_p instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view_p).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view_p).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode) || (dDiagramElement instanceof DNodeListElement)) {
          returnedList.add((AbstractDNode) dDiagramElement);
        }
      }

    } else if (view_p instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view_p).getNodes());
      returnedList.addAll(((DNodeList) view_p).getOwnedElements());

    } else if (view_p instanceof DNode) {
      returnedList.addAll(((DNode) view_p).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  public String getFunctionalChainDiagramPrefix(EObject eObject_p) {
    if (eObject_p instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_PREFIX;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_PREFIX;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_PREFIX;
      }
    }
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PREFIX;
  }

  public String getFunctionalChainDiagramSuffix(EObject eObject_p) {
    if (eObject_p instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_NAME;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_NAME;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject_p)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_NAME;
      }
    }
    // default
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
  }

  /**
   * @param edgeMapping_p
   * @return
   */
  public EdgeMappingHelper getEdgeMappingHelper(EObject eObject) {
    return new EdgeMappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObject));
  }

  /**
   * Returns a DiagramElementMapping from an edge (works with EdgeMappingImport)
   * @param aEdge_p
   */
  public DiagramElementMapping getEdgeMapping(DEdge aEdge_p) {
    if ((aEdge_p != null) && (aEdge_p.getActualMapping() != null)) {
      IEdgeMapping mapping = aEdge_p.getActualMapping();
      if ((mapping != null) && (mapping instanceof EdgeMappingImport)) {
        mapping = MappingHelper.getEdgeMapping((EdgeMappingImport) mapping);
      }

      if ((mapping != null) && (mapping instanceof DiagramElementMapping)) {
        return (DiagramElementMapping) mapping;
      }
    }
    return null;
  }

  /**
   * @param targetDescription_p
   * @param targetMappingName_p
   * @return
   */
  public DiagramElementMapping getMappingByName(RepresentationDescription targetDescription_p, String targetMappingName_p) {
    DiagramElementMapping mapping = null;

    if ((targetMappingName_p != null) && (targetDescription_p != null) && (targetDescription_p instanceof DiagramDescription)) {
      mapping = DiagramServices.getDiagramServices().getAbstractNodeMapping((DiagramDescription) targetDescription_p, targetMappingName_p);
      if (mapping == null) {
        mapping = DiagramServices.getDiagramServices().getEdgeMapping((DiagramDescription) targetDescription_p, targetMappingName_p);
      }
    }

    return mapping;
  }

  public HashMap<String, DiagramElementMapping> getAllMappingsByName(DiagramDescription description_p) {
    HashMap<String, DiagramElementMapping> result = new HashMap<String, DiagramElementMapping>();

    for (NodeMapping nodeMapping : description_p.getAllNodeMappings()) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        result.put(mapping.getName(), mapping);
      }
    }
    for (ContainerMapping nodeMapping : description_p.getAllContainerMappings()) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        result.put(mapping.getName(), mapping);
        if ((mapping instanceof DiagramElementMapping)) {
          for (DiagramElementMapping borderedMapping : ((DiagramElementMapping) mapping).getAllMappings()) {
            result.put(borderedMapping.getName(), borderedMapping);
          }
        }
      }
    }
    for (final EdgeMapping edgeMapping : description_p.getAllEdgeMappings()) {
      result.put(edgeMapping.getName(), edgeMapping);
    }
    return result;
  }
  
  private DiagramMappingsManager getMappingManager(final DSemanticDiagram diagram) {
    Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());
    return DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
  }

}
