/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
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
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.query.AbstractNodeMappingApplicabilityTester;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.model.business.internal.helper.ContentHelper;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.model.business.internal.operations.DDiagramSpecOperations;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

import com.google.common.collect.Lists;

/**
 */
@SuppressWarnings("restriction")
public class DiagramServices {
  private static DiagramServices singleton = null;
  public static final int OVERLLAPING_LABEL_MAX_LENGTH = 30;

  public static DiagramServices getDiagramServices() {
    if (singleton == null) {
      singleton = new DiagramServices();
    }
    return singleton;
  }

  /**
   * Return the EList of owned diagram elements of the given container
   * 
   * @param container
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(DragAndDropTarget container) {
    if (container instanceof DDiagram) {
      return ((DDiagram) container).getOwnedDiagramElements();
    } else if (container instanceof DNodeContainer) {
      return ((DNodeContainer) container).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Return the EList of owned diagram elements of the given container
   * 
   * @param container
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(EObject container) {
    if (container instanceof DDiagram) {
      return ((DDiagram) container).getOwnedDiagramElements();
    } else if (container instanceof DNodeContainer) {
      return ((DNodeContainer) container).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Return the EList of flat owned diagram elements of the given container
   * 
   * @param container
   * @return
   */
  public List<DDiagramElement> getFlatOwnedDiagramElements(EObject container) {
    List<DDiagramElement> result = new ArrayList<>();
    getFlatOwnedDiagramElements(container, result);

    return result;
  }

  private void getFlatOwnedDiagramElements(EObject container, List<DDiagramElement> acumulator) {
    List<DDiagramElement> ownedDiagramElements = getOwnedDiagramElements(container);
    acumulator.addAll(ownedDiagramElements);

    for (DDiagramElement dDiagramElement : ownedDiagramElements) {
      if (dDiagramElement instanceof DNodeContainer) {
        getFlatOwnedDiagramElements(dDiagramElement, acumulator);
      }
    }
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodes(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element instanceof AbstractDNode) {
      views.addAll(((AbstractDNode) element).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodeListElements(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DNodeListElement) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeList) {
      views.addAll(((DNodeList) element).getOwnedElements());
    }
    return views;
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedContainers(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    }
    return views;
  }

  public Collection<DDiagramElement> getAllAbstractNodes(EObject element) {
    return Lists.newArrayList(getAllAbstractNodes(element, true));
  }

  public Iterable<DDiagramElement> getAllAbstractNodes(EObject element, boolean borderedNode) {
    return getDiagramElements(element, false, true, true, borderedNode);
  }

  public Iterable<AbstractDNode> getAllNodeContainers(EObject element) {
    return (Iterable) getDiagramElements(element, false, false, true, false);
  }

  public Iterable<DDiagramElement> getAllBorderedNodes(DSemanticDecorator element) {
    return getDiagramElements(element, false, false, false, true);
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedAbstractNodes(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    }

    if (element instanceof AbstractDNode)

    {
      views.addAll(((AbstractDNode) element).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * @deprecated Use getMappingByName instead
   */
  @Deprecated
  public DiagramElementMapping getMapping(String eClass, DDiagram diagram) {
    return getMappingByName(diagram.getDescription(), eClass);
  }

  public List<NodeMapping> getAllBorderedNodeMapping(AbstractNodeMapping mapping) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping.getBorderedNodeMappings());
    if (mapping instanceof ContainerMapping) {
      ContainerMapping currentContainerMapping = (ContainerMapping) mapping;
      for (ContainerMapping aMapping : currentContainerMapping.getSubContainerMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
      for (NodeMapping aMapping : currentContainerMapping.getSubNodeMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
    }
    return returnedList;
  }

  public List<NodeMapping> getAllNodeMappings(ContainerMapping mapping) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping.getSubNodeMappings());
    for (ContainerMapping aMapping : mapping.getSubContainerMappings()) {
      returnedList.addAll(getAllNodeMappings(aMapping));
    }
    return returnedList;
  }

  public List<ContainerMapping> getAllContainerMappings(ContainerMapping mapping) {
    List<ContainerMapping> returnedList = new ArrayList<ContainerMapping>();
    returnedList.add(mapping);
    for (ContainerMapping aMapping : mapping.getSubContainerMappings()) {
      returnedList.addAll(getAllContainerMappings(aMapping));
    }
    return returnedList;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DiagramDescription description, String mappingName) {

    for (NodeMapping nodeMapping : ContentHelper.getAllNodeMappings(description, false)) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
      for (NodeMapping borderedMapping : MappingHelper.getAllBorderedNodeMappings(nodeMapping)) {
        if (borderedMapping.getName().equals(mappingName)) {
          return borderedMapping;
        }
      }
    }

    for (ContainerMapping nodeMapping : ContentHelper.getAllContainerMappings(description, false)) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
      for (DiagramElementMapping mapping : MappingHelper.getAllMappings(nodeMapping)) {
        if ((mapping instanceof AbstractNodeMapping)) {

          if (mapping.getName().equals(mappingName)) {
            return (AbstractNodeMapping) mapping;

          }
          for (NodeMapping borderedMapping : MappingHelper.getAllBorderedNodeMappings((AbstractNodeMapping) mapping)) {
            if (borderedMapping.getName().equals(mappingName)) {
              return borderedMapping;
            }
          }
        }

      }
    }
    return null;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    return getAbstractNodeMapping(description, mappingName);
  }

  public NodeMapping getNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : ContentHelper.getAllNodeMappings(description, false)) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
    }
    for (ContainerMapping aMapping : ContentHelper.getAllContainerMappings(description, false)) {
      for (NodeMapping aNodeMapping : getAllNodeMappings(aMapping)) {
        if (aNodeMapping.getName().equals(mappingName)) {
          return aNodeMapping;
        }
      }
    }
    return null;
  }

  public List<NodeMapping> getBorderedNodeMapping(final DDiagram diagram, List<String> mappingNames) {
    List<NodeMapping> result = new ArrayList<NodeMapping>();

    for (String name : mappingNames) {
      result.add(getBorderedNodeMapping(diagram, name));
    }
    return result;
  }

  public NodeMapping getBorderedNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : ContentHelper.getAllNodeMappings(description, false)) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(nodeMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName)) {
          return aBorderedNodeMapping;
        }
      }
    }
    for (ContainerMapping aMapping : ContentHelper.getAllContainerMappings(description, false)) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(aMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName)) {
          return aBorderedNodeMapping;
        }
      }
    }
    return null;
  }

  public ContainerMapping getContainerMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (ContainerMapping aContainerMapping : ContentHelper.getAllContainerMappings(description, false)) {
      for (ContainerMapping aSubContainerMapping : getAllContainerMappings(aContainerMapping)) {
        if (aSubContainerMapping.getName().equals(mappingName)) {
          return aSubContainerMapping;
        }
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DiagramDescription description, String mappingName) {
    for (final EdgeMapping edgeMapping : ContentHelper.getAllEdgeMappings(description, false)) {
      if (edgeMapping.getName().equals(mappingName)) {
        return edgeMapping;
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    return getEdgeMapping(description, mappingName);
  }

  public DNode createNode(NodeMapping mapping, EObject modelElement, DragAndDropTarget container, DDiagram diagram) {
    return (DNode) createAbstractDNode(mapping, modelElement, container, diagram);
  }

  public DNode createBorderedNode(NodeMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    DNodeCandidate nodeCandidate = new DNodeCandidate(mapping, modelElement, container, rId);
    return (DNode) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, true);
  }

  public AbstractDNode createDNodeListElement(AbstractNodeMapping mapping, EObject modelElement,
      DragAndDropTarget container, DDiagram diagram) {
    return createDNodeListElement(mapping, modelElement, container, diagram, -1);
  }

  public AbstractDNode createDNodeListElement(AbstractNodeMapping mapping, EObject modelElement,
      DragAndDropTarget container, DDiagram diagram, int index) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    DNodeCandidate nodeCandidate = new DNodeCandidate(mapping, modelElement, container, rId);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, false, index);
  }

  public DNodeContainer createContainer(ContainerMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram, int index) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    DNodeCandidate nodeCandidate = new DNodeCandidate(mapping, modelElement, container, rId);
    return (DNodeContainer) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, false,
        index);
  }

  public DNodeContainer createContainer(ContainerMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram) {
    return createContainer(mapping, modelElement, container, diagram, -1);
  }

  @Deprecated
  public AbstractDNode createAbstractDNodeContainer(AbstractNodeMapping mapping, EObject modelElement,
      DragAndDropTarget container, DDiagram diagram) {
    return createAbstractDNode(mapping, modelElement, container, diagram);
  }

  public boolean isBorderedNodeMapping(DiagramElementMapping mapping) {
    return (mapping != null)
        && org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS
            .equals(mapping.eContainingFeature());
  }

  /**
   * Evaluate precondition of the given edge mapping.
   */
  public boolean evaluateEdgePrecondition(EdgeMapping edgeMapping, DDiagram diagram, EObject semantic,
      DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new EdgeMappingQuery(edgeMapping).evaluatePrecondition((DSemanticDiagram) diagram,
        (DragAndDropTarget) diagram, interpreter, semantic, sourceView, targetView);
  }

  /**
   * Evaluate precondition of the given node mapping.
   */
  public boolean evaluateNodePrecondition(AbstractNodeMapping nodeMapping, DDiagram diagram,
      DSemanticDecorator containerView, EObject semantic) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new AbstractNodeMappingQuery(nodeMapping).evaluatePrecondition((DSemanticDiagram) diagram,
        (DragAndDropTarget) containerView, interpreter, semantic);
  }

  /**
   * Evaluate candidate expression of the given node mapping.
   */
  public Collection<EObject> evaluateCandidateExpression(AbstractNodeMapping nodeMapping, DDiagram diagram,
      DSemanticDecorator containerView, EObject semantic) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);

    return new AbstractNodeMappingQuery(nodeMapping).evaluateCandidateExpression((DSemanticDiagram) diagram,
        interpreter, (DragAndDropTarget) containerView);
  }

  public AbstractDNode createAbstractDNode(AbstractNodeMapping mapping, EObject modelElement,
      DragAndDropTarget container, DDiagram diagram) {
    final DDiagram diag = diagram;
    if (mapping == null) {
      return null;
    }

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    DNodeCandidate nodeCandidate = new DNodeCandidate(mapping, modelElement, container, rId);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate,
        isBorderedNodeMapping(mapping));
  }

  public DEdge createEdge(EdgeMapping mapping, EdgeTarget sourceView, EdgeTarget targetView, EObject semanticObject) {
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticObject);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticObject);

    if (mapping == null) {
      return null;
    }

    if ((sourceView == null) || (targetView == null)) {
      return null;
    }

    final DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);
    DEdgeCandidate edgeCandidate = new DEdgeCandidate(mapping, semanticObject, sourceView, targetView, rId);

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

    if (sourceView instanceof DDiagramElement) {
      sourceElement = (DDiagramElement) sourceView;
      sourceMapping = sourceElement.getDiagramElementMapping();
    }

    if (targetView instanceof DDiagramElement) {
      targetElement = (DDiagramElement) targetView;
      targetMapping = targetElement.getDiagramElementMapping();
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.put(sourceMapping, new ArrayList<EdgeTarget>());
    }
    if ((targetMapping != null) && !targetMapping.equals(sourceMapping)) {
      mappingsToEdgeTargets.put(targetMapping, new ArrayList<EdgeTarget>());
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.get(sourceMapping).add(sourceView);
    }
    if ((targetMapping != null) && !sourceView.equals(targetView)) {
      mappingsToEdgeTargets.get(targetMapping).add(targetView);
    }

    new DecorationHelperInternal(diagram, interpreter, accessor).computeDecorations(mappingsToEdgeTargets,
        edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
    return elementSync.createNewEdge(getMappingManager((DSemanticDiagram) diagram), edgeCandidate,
        mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
  }

  public boolean isHiddenLabel(DDiagramElement context) {
    return new DDiagramElementQuery(context).isLabelHidden();
  }

  public EObject hideLabel(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hideLabel(context);
    return context;
  }

  public boolean isHidden(DDiagramElement context) {
    DDiagramElementQuery query = new DDiagramElementQuery(context);
    return query.isHidden();
  }

  public boolean isFiltered(DDiagramElement context) {
    DDiagramElementQuery query = new DDiagramElementQuery(context);
    return query.isFiltered() || query.isIndirectlyFiltered();
  }

  public EObject hide(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hide(context);
    return context;
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
  public boolean isOnDiagram(DNodeContainer diagramElement, EObject semanticTarget) {
    for (DDiagramElement element : diagramElement.getContainers()) {
      if (element.getTarget() == semanticTarget) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an element is directly displayed on a diagram, or indirectly through the type of a Part.
   * 
   * @param diagramElement
   *          the diagram element
   * @param semanticTarget
   *          the semantic target
   * @return true if an element is directly displayed on a diagram, or indirectly through the type of a Part, false
   *         otherwise.
   */
  public boolean isIndirectlyOnDiagram(DNodeContainer diagramElement, EObject semanticTarget) {
    for (DDiagramElement element : diagramElement.getContainers()) {
      EObject elementTarget = element.getTarget();
      if (elementTarget != null && elementTarget.equals(semanticTarget)) {
        return true;
      }

      if (elementTarget instanceof Part) {
        AbstractType partType = ((Part) elementTarget).getAbstractType();
        if (partType != null && partType.equals(semanticTarget)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Checks if an element is on the current diagram.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param semanticTarget
   *          the semantic target
   * @return true if an element is on the current diagram, false otherwise.
   */
  public boolean isOnDiagram(DDiagram diagramTarget, EObject semanticTarget) {
    return getDiagramElement(diagramTarget, semanticTarget) != null;
  }

  /**
   * @return true if the current settings contains a 'target' feature.
   */
  protected Predicate<Setting> isValidTargetFeature() {
    return setting -> ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET.equals(setting.getEStructuralFeature())
        || ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS.equals(setting.getEStructuralFeature());
  }

  /**
   * Return whether the given node mapping can be created into the given container
   */
  public boolean isValidMapping(AbstractNodeMapping mapping_p, DSemanticDecorator container) {

    AbstractNodeMappingApplicabilityTester tester = new AbstractNodeMappingApplicabilityTester(
        Arrays.asList(mapping_p));
    if (container instanceof DDiagram) {
      return tester.canCreateIn((DDiagram) container);

    } else if (container instanceof DDiagramElementContainer) {
      return tester.canCreateIn((DDiagramElementContainer) container);

    } else if (container instanceof DNode) {
      return tester.canCreateIn((DNode) container);
    }

    return true;
  }

  /**
   * Returns a stream containing elements for the diagram target that have the specified semantic target.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param semanticTarget
   *          the semantic target
   * @return a stream containing elements for the diagram target that have the specified semantic target.
   */
  protected Stream<DDiagramElement> getDiagramElementsStream(DRepresentation diagramTarget, EObject semanticTarget) {
    Session session = SessionManager.INSTANCE.getSession(semanticTarget);
    return session.getSemanticCrossReferencer().getInverseReferences(semanticTarget).stream()
        .filter(isValidTargetFeature()).filter(setting -> setting.getEObject() instanceof DDiagramElement)
        .map(setting -> (DDiagramElement) setting.getEObject())
        .filter(decorator -> diagramTarget.equals(DiagramHelper.getService().getRepresentation(decorator)));
  }

  /**
   * Returns the element on the diagram target that has the semantic target.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param semanticTarget
   *          the semantic target
   * @return the element on the diagram target that has the semantic target.
   */
  public DDiagramElement getDiagramElement(DDiagram diagramTarget, EObject semanticTarget) {
    return getDiagramElementsStream(diagramTarget, semanticTarget).findFirst().orElse(null);
  }

  /**
   * Returns the elements on the diagram target that have the specified semantic target.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param semanticTarget
   *          the semantic target
   * @return the elements on the diagram target that have the specified semantic target.
   */
  public Collection<DSemanticDecorator> getDiagramElements(DRepresentation diagramTarget, EObject semanticTarget) {
    return getDiagramElementsStream(diagramTarget, semanticTarget).collect(Collectors.toSet());
  }

  public Iterable<DDiagramElement> getDiagramElements(DDiagram diagram, DiagramElementMapping mapping) {
    boolean isEdgeMapping = mapping instanceof EdgeMapping || mapping instanceof EdgeMappingImport;

    final DiagramIterator iterator = new DiagramIterator(diagram, mapping, isEdgeMapping, !isEdgeMapping,
        !isEdgeMapping, !isEdgeMapping);

    return new Iterable<DDiagramElement>() {

      @Override
      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  // TODO Optimize
  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement container, DiagramElementMapping mapping) {
    boolean isEdgeMapping = mapping instanceof EdgeMapping || mapping instanceof EdgeMappingImport;

    final DiagramIterator iterator = new DiagramIterator(container, mapping, isEdgeMapping, !isEdgeMapping,
        !isEdgeMapping, !isEdgeMapping);

    return new Iterable<DDiagramElement>() {

      @Override
      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  /**
   * Returns the first element on the diagram target that has the specified semantic and mapping target.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param mappingTarget
   *          the mapping target
   * @param semanticTarget
   *          the semantic target
   * @return the first element on the diagram target that has the specified semantic and mapping target.
   */
  public DDiagramElement getDiagramElement(DDiagram diagramTarget, DiagramElementMapping mappingTarget,
      EObject semanticTarget) {

    return getDiagramElementsStream(diagramTarget, semanticTarget).filter(DDiagramElement.class::isInstance)
        .map(DDiagramElement.class::cast).filter(element -> element.getMapping().equals(mappingTarget)).findFirst()
        .orElse(null);
  }
  
  /**
   * Returns the elements on the diagram target that have the specified semantic and mapping target.
   * 
   * @param diagramTarget
   *          the diagram target
   * @param mappingTarget
   *          the mapping target
   * @param semanticTarget
   *          the semantic target
   * @return the elements on the diagram target that have the specified semantic and mapping target.
   */
  public Collection<DDiagramElement> getDiagramElements(DDiagram diagramTarget, DiagramElementMapping mappingTarget,
      EObject semanticTarget) {

    return getDiagramElementsStream(diagramTarget, semanticTarget).filter(DDiagramElement.class::isInstance)
        .map(DDiagramElement.class::cast).filter(element -> element.getMapping().equals(mappingTarget))
        .collect(Collectors.toSet());
  }

  /**
   * An optimized version of diagram.getDiagramElements()
   */
  @Deprecated
  public Iterable<DDiagramElement> getDiagramElements(EObject context) {
    final DiagramIterator iterator = new DiagramIterator(context);

    return new Iterable<DDiagramElement>() {

      @Override
      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  @Deprecated
  public Iterable<DDiagramElement> getDiagramElements(EObject context, boolean edges, boolean nodes, boolean containers,
      boolean borderedNodes) {
    final DiagramIterator iterator = new DiagramIterator(context, edges, nodes, containers, borderedNodes);

    return new Iterable<DDiagramElement>() {

      @Override
      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
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
            if ((edge.getTarget() != null) && targetView.equals(edge.getTargetNode())
                && edge.getTarget().equals(semantic)) {
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
   * 
   * @param node
   *          a node
   */
  public void removeNodeView(DNode node) {
    EObject container = node.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node);
      }
      if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node)) {
          nodeContainer.getOwnedDiagramElements().remove(node);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node)) {
          nodeContainer.getOwnedBorderedNodes().remove(node);
        }
      }
      if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node);
      }
    }
  }

  public void removeNodeListElementView(AbstractDNode node) {
    EObject container = node.eContainer();
    if ((container != null) && (container instanceof DNodeList)) {
      ((DNodeList) container).getOwnedElements().remove(node);
    }
  }

  public void removeAbstractDNodeView(AbstractDNode node) {
    EObject container = node.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node);
      } else if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node)) {
          nodeContainer.getOwnedDiagramElements().remove(node);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node)) {
          nodeContainer.getOwnedBorderedNodes().remove(node);
        }
      } else if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node);
      } else if (container instanceof DNodeList) {
        ((DNodeList) container).getOwnedElements().remove(node);
      }
    }
  }

  /**
   * remove a container View
   * 
   * @param container
   *          a container
   */
  public void removeContainerView(EObject container) {
    EObject owner = container.eContainer();
    if (owner != null) {
      if (owner instanceof DDiagram) {
        ((DDiagram) owner).getOwnedDiagramElements().remove(container);
      }
      if (owner instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) owner;
        if (nodeContainer.getOwnedDiagramElements().contains(container)) {
          nodeContainer.getOwnedDiagramElements().remove(container);
        }
      }
    }
  }

  /**
   * remove an edge from a diagram
   * 
   * @param anEdge
   *          the edge to remove from diagram
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
   * Check if element used given mapping or use a sub mapping of the current mapping (mapping imports) We should use
   * this method to ensure tool are working with viewpoint extensions
   * 
   * @param element
   * @param mappind
   * @return
   */
  public boolean isMapping(DDiagramElement element, DiagramElementMapping mapping) {
    return (mapping != null) && new DiagramElementMappingQuery(mapping).isInstanceOf(element);
  }

  /**
   * This method tests if a Node is a BorderedNode
   * 
   * @param node
   *          : a DNode in a diagram
   * @return true if the current node is a borderedNode
   */
  public boolean isABorderedNode(AbstractDNode node) {
    if (null == node) {
      return false;
    }
    EObject container = node.eContainer();
    if (null == container) {
      return false;
    }
    if (container instanceof DDiagram) {
      return false;
    }
    if (container instanceof AbstractDNode) {
      AbstractDNode nodeContainer = (AbstractDNode) container;
      return nodeContainer.getOwnedBorderedNodes().contains(node);
    }
    return false;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node, DDiagram diagram) {
    Set<DEdge> returnedSet = new HashSet<>();
    returnedSet.addAll(node.getIncomingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    return getIncomingEdges(node, diagram);
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node, DDiagram diagram) {
    Set<DEdge> returnedSet = new HashSet<>();
    returnedSet.addAll(node.getOutgoingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    return getOutgoingEdges(node, diagram);
  }

  public Set<DEdge> getAllEdges(EdgeTarget node, DDiagram diagram) {
    Set<DEdge> returnedSet = new HashSet<>();
    returnedSet.addAll(node.getOutgoingEdges());
    returnedSet.addAll(node.getIncomingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  public Set<DEdge> getAllEdges(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    return getAllEdges(node, diagram);
  }

  /**
   * Returns candidates for the source of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeSourceCandidates(EdgeMapping edgeMapping, EObject context, DDiagram diagram) {
    return getEdgeMappingHelper(context).getEdgeSourceCandidates(edgeMapping, context, diagram);
  }

  /**
   * Returns candidates for the target of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeTargetCandidates(EdgeMapping edgeMapping, EObject context, DDiagram diagram) {
    return getEdgeMappingHelper(context).getEdgeTargetCandidates(edgeMapping, context, diagram);
  }

  /**
   * An iterator to browse all diagram elements of a diagram. In fact, diagram.getDiagramElements browse one time the
   * diagram to retrieve elements
   */
  class DiagramIterator implements Iterator<DDiagramElement> {

    LinkedList<DDiagramElement> elements;

    DiagramElementMapping mapping;

    EClass clazz;

    boolean edges = true;

    boolean nodes = true;

    boolean containers = true;

    boolean borderedNodes = true;

    /**
     * @param context
     * @param edges
     *          whether edges will be returned
     * @param nodes
     *          whether nodes will be returned
     * @param containers
     *          whether containers will be returned
     * @param borderedNodes
     *          whether bordered nodes will be included in addition to other nodes/containers
     */
    public DiagramIterator(EObject context, boolean edges, boolean nodes, boolean containers, boolean borderedNodes) {
      elements = new LinkedList<DDiagramElement>();

      this.edges = edges;
      this.nodes = nodes;
      this.containers = containers;
      this.borderedNodes = borderedNodes;

      if (context instanceof DDiagram) {
        addElements(elements, ((DDiagram) context).getOwnedDiagramElements());

      } else if (context instanceof DDiagramElement) {
        elements.addAll(getNexts((DDiagramElement) context));
      }
    }

    private void addElements(Collection<DDiagramElement> elements, Collection<DDiagramElement> toAdd) {
      for (DDiagramElement element : toAdd) {
        if (this.containers && element instanceof DNodeContainer) {
          elements.add(element);
        } else if (this.nodes && element instanceof AbstractDNode && !(element instanceof DNodeContainer)) {
          elements.add(element);
        } else if (this.edges && element instanceof DEdge) {
          elements.add(element);
        }
      }
    }

    /**
     * @param context
     *          a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context) {
      this(context, true, true, true, true);
    }

    /**
     * @param context
     *          a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context, DiagramElementMapping mapping) {
      this(context, mapping, true, true, true, true);
    }

    /**
     * @param context
     *          a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context, DiagramElementMapping mapping, boolean edges, boolean nodes,
        boolean containers, boolean borderedNodes) {
      this(context, edges, nodes, containers, borderedNodes);
      this.mapping = mapping;
      if (mapping != null) {
        clazz = CapellaServices.getService().getDomainClass(context, mapping);
      }
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
      if (elements.size() == 0) {
        return false;
      }

      DDiagramElement element = elements.getFirst();
      if (mapping == null || validMapping(mapping, element)) {
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

    protected Collection<DDiagramElement> getNexts(DDiagramElement context) {

      List<DDiagramElement> element = new ArrayList<DDiagramElement>();

      if (this.borderedNodes && context instanceof AbstractDNode) {
        element.addAll(((AbstractDNode) context).getOwnedBorderedNodes());
      }
      if (context instanceof DNodeContainer) {
        addElements(element, ((DNodeContainer) context).getOwnedDiagramElements());
      }
      if (context instanceof DNodeList) {
        addElements(element, (Collection) ((DNodeList) context).getOwnedElements());
      }

      return element;
    }

    /**
     * @see java.util.Iterator#next()
     */
    @Override
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
    @Override
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
   * An optimized version of SetOf<diagram.getDiagramElements()>
   */
  public Set<DDiagramElement> getSetOfDiagramElements(DDiagram diagram) {
    Set<DDiagramElement> set = new HashSet<DDiagramElement>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
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
  public Set<EObject> getSetOfDiagramElementsTarget(DDiagram diagram) {
    Set<EObject> set = new HashSet<EObject>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      set.add(element.getTarget());
    }
    set.remove(null); // avoid null target if used in a beforeRefresh method
    return set;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DSemanticDecorator> getMapOfDiagramElements(DDiagram diagram) {
    Map<EObject, DSemanticDecorator> map = new HashMap<EObject, DSemanticDecorator>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      map.put(element.getTarget(), element);
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DragAndDropTarget> getMapOfDiagramNodes(DDiagram diagram) {
    Map<EObject, DragAndDropTarget> map = new HashMap<EObject, DragAndDropTarget>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      if (element instanceof DragAndDropTarget) {
        map.put(element.getTarget(), (DragAndDropTarget) element);
      }
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers contained in view
   */
  @Deprecated
  public List<DNodeContainer> getAllContainers(EObject view) {
    List<DNodeContainer> returnedList = new ArrayList<DNodeContainer>();
    if (view instanceof DDiagram) {
      for (AbstractDNode aContainer : ((DDiagram) view).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      for (AbstractDNode aContainer : ((DNodeContainer) view).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
      return returnedList;
    }
    return returnedList;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers and nodeLists contained in view
   */
  public List<EObject> getAllContainersAndNodeLists(EObject view) {
    List<EObject> returnedList = new ArrayList<EObject>();
    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getContainers());
    }
    if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getContainers());
    }
    return returnedList;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the nodes contained in view
   */
  public List<DNode> getAllNodes(EObject view) {
    List<DNode> returnedList = new ArrayList<DNode>();

    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getNodes());

    } else if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode)) {
          returnedList.add((DNode) dDiagramElement);
        }
      }

    } else if (view instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view).getNodes());

    } else if (view instanceof DNode) {
      returnedList.addAll(((DNode) view).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  /**
   * Select an element in the current diagram Element must exist before calling the tool. a newly created view will not
   * be selected since GMF layer is not created before the end of the tool.
   */
  public void selectElementInDiagram(DSemanticDecorator newTarget) {
    Object selectedElement = newTarget.getTarget();

    if ((null != selectedElement) && (newTarget instanceof DDiagramElement)) {
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
          List<IGraphicalEditPart> allEditParts = dialectViewer.findEditPartsForElement((EObject) selectedElement,
              IGraphicalEditPart.class);
          // Iterate over retrieved edit parts to remove the ones related to 'label' edit
          // part.
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
              if (newTarget.equals(element)) {
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
   * 
   * @param elements
   * @return
   */
  public Collection<DNodeContainer> filterNodeContainers(Collection<DDiagramElement> elements) {
    List<DNodeContainer> edges = new ArrayList<DNodeContainer>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof DNodeContainer) {
        edges.add((DNodeContainer) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only AbstractDNode
   * 
   * @param elements
   * @return
   */
  public List<AbstractDNode> filterNodes(Collection<DDiagramElement> elements) {
    List<AbstractDNode> edges = new ArrayList<AbstractDNode>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof AbstractDNode) {
        edges.add((AbstractDNode) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only DEdge
   * 
   * @param elements
   * @return
   */
  public List<DEdge> filterEdges(Collection<DDiagramElement> elements) {
    List<DEdge> edges = new ArrayList<DEdge>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof DEdge) {
        edges.add((DEdge) element);
      }
    }
    return edges;
  }

  /**
   * Return the internal link of the given mapping between given two ports.
   * 
   * (It returns the same internal link between 2 ports regardless of orientation of both ports)
   */
  public DEdge findInternalLinkEdge(DDiagram pDiagram, EdgeTarget firstPort, EdgeTarget secondPort,
      EdgeMapping mapping) {
    // As there might have several internal links between two ports (prior 5.1.x),
    // we need to ensure that we look always in the same order the ports looking for an internal link.
    // This 'if' might be removed when we are sure there is at most one internal link between two ports.

    // We can also use diagram.getAllEdges(mapping) looking for the correct one, but with poorer 
    // performances than a small comparison then outgoing/incoming edges
    if (firstPort.getUid().compareTo(secondPort.getUid()) < 0) {
      EdgeTarget tmp = secondPort;
      secondPort = firstPort;
      firstPort = tmp;
    }

    // Looking for an link directly oriented
    for (DEdge anEdge : firstPort.getOutgoingEdges()) {
      if (anEdge.getTarget() != null && anEdge.getTargetNode().equals(secondPort)) {
        if (mapping.equals(anEdge.getActualMapping())) {
          return anEdge;
        }
      }
    }

    // Looking for an link in the other orientation
    for (DEdge anEdge : firstPort.getIncomingEdges()) {
      if (anEdge.getTarget() != null && anEdge.getSourceNode().equals(secondPort)) {
        if (mapping.equals(anEdge.getActualMapping())) {
          return anEdge;
        }
      }
    }
    return null;
  }

  /**
   * @param pDiagram
   * @param sourceNode
   * @param targetNode
   * @param semanticObject
   * @param mapping
   * @return
   */
  @Deprecated
  public DEdge findDEdgeElement(DDiagram pDiagram, EdgeTarget sourceNode, EdgeTarget targetNode, EObject semanticObject,
      EdgeMapping mapping) {
    for (DEdge anEdge : DDiagramSpecOperations.getEdgesFromMapping(pDiagram, mapping)) {
      if ((anEdge.getTarget() != null) && anEdge.getTarget().equals(semanticObject)
          && anEdge.getSourceNode().equals(sourceNode) && anEdge.getTargetNode().equals(targetNode)) {
        return anEdge;
      }
    }
    return null;
  }

  /**
   * Return Node and NodeListElement contained in given object
   * 
   * @param eObjecct
   * @return
   */
  @Deprecated
  public List<AbstractDNode> getNodesAndNodeListElements(EObject eObjecct) {
    return getAllNodesAndNodeListElements(eObjecct);
  }

  /**
   * @param view
   *          a {@link DDiagram}, a {@link DNodeContainer}, a {@link DNodeList}, a {@link DNode}
   * @return
   */
  public List<AbstractDNode> getAllNodesAndNodeListElements(EObject view) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();

    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getNodes());
      returnedList.addAll(((DDiagram) view).getNodeListElements());

    } else if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode) || (dDiagramElement instanceof DNodeListElement)) {
          returnedList.add((AbstractDNode) dDiagramElement);
        }
      }

    } else if (view instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view).getNodes());
      returnedList.addAll(((DNodeList) view).getOwnedElements());

    } else if (view instanceof DNode) {
      returnedList.addAll(((DNode) view).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  public String getFunctionalChainDiagramPrefix(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_PREFIX;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_PREFIX;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_PREFIX;
      }
    }
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PREFIX;
  }

  public String getFunctionalChainDiagramSuffix(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_NAME;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_NAME;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_NAME;
      }
    }
    // default
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
  }

  /**
   * @param edgeMapping
   * @return
   */
  public EdgeMappingHelper getEdgeMappingHelper(EObject eObject) {
    return new EdgeMappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObject));
  }

  /**
   * Returns a DiagramElementMapping from an edge (works with EdgeMappingImport)
   * 
   * @param aEdge
   */
  public DiagramElementMapping getEdgeMapping(DEdge aEdge) {
    if ((aEdge != null) && (aEdge.getActualMapping() != null)) {
      IEdgeMapping mapping = aEdge.getActualMapping();
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
   * @param targetDescription
   * @param targetMappingName
   * @return
   */
  public DiagramElementMapping getMappingByName(RepresentationDescription targetDescription, String targetMappingName) {
    DiagramElementMapping mapping = null;

    if ((targetMappingName != null) && (targetDescription != null)
        && (targetDescription instanceof DiagramDescription)) {
      mapping = DiagramServices.getDiagramServices().getAbstractNodeMapping((DiagramDescription) targetDescription,
          targetMappingName);
      if (mapping == null) {
        mapping = DiagramServices.getDiagramServices().getEdgeMapping((DiagramDescription) targetDescription,
            targetMappingName);
      }
    }

    return mapping;
  }

  public HashMap<String, DiagramElementMapping> getAllMappingsByName(DiagramDescription description) {
    HashMap<String, DiagramElementMapping> result = new HashMap<String, DiagramElementMapping>();

    for (NodeMapping nodeMapping : ContentHelper.getAllNodeMappings(description, false)) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : MappingHelper.getAllMappings(nodeMapping)) {
        result.put(mapping.getName(), mapping);
      }
    }
    for (ContainerMapping nodeMapping : ContentHelper.getAllContainerMappings(description, false)) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : MappingHelper.getAllMappings(nodeMapping)) {
        result.put(mapping.getName(), mapping);
        for (DiagramElementMapping borderedMapping : MappingHelper.getAllMappings(mapping)) {
          result.put(borderedMapping.getName(), borderedMapping);
        }
      }
    }
    for (final EdgeMapping edgeMapping : ContentHelper.getAllEdgeMappings(description, false)) {
      result.put(edgeMapping.getName(), edgeMapping);
    }
    return result;
  }

  private DiagramMappingsManager getMappingManager(final DSemanticDiagram diagram) {
    Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());
    return DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
  }

  /**
   * Returns a collection of all DDiagramElement (representing a semantic element) having the same EType as
   * <code>selectedViews</code> in the <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected views. For each view we consider the EType of its semantic elements.
   * @return a collection of all DDiagramElement having the same EType as the selectedViews.
   */
  public Collection<DDiagramElement> getViewsRepresentingSameEType(DSemanticDiagram currentDiagram,
      List<DSemanticDecorator> selectedViews) {

    Set<EClass> eclassesToSelect = selectedViews.stream().filter(view -> view instanceof DDiagramElement)
        .map(view -> ((DDiagramElement) view).getTarget().eClass()).collect(Collectors.toSet());

    Collection<DDiagramElement> diagramElements = new DDiagramQuery(currentDiagram).getAllDiagramElements();

    return diagramElements.stream().filter(element -> eclassesToSelect.contains(element.getTarget().eClass()))
        .collect(Collectors.toList());
  }

  /**
   * Returns a collection of all Functional Exchanges, Exchange Categories and Ports involved in the FunctionalChains as
   * <code>selectedViews</code> in the <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected FunctionalChain decorators.
   * @return a collection of all DDiagramElement involved in the FunctionalChain as the selectedViews.
   */
  public Collection<DDiagramElement> getRelatedFunctionalChainElements(DSemanticDiagram currentDiagram,
      List<DSemanticDecorator> selectedViews) {

    Collection<DDiagramElement> diagramElements = new DDiagramQuery(currentDiagram).getAllDiagramElements();
    Collection<DDiagramElement> diagramElementsToBeSelected = new HashSet<DDiagramElement>();

    Set<EObject> toBeSelected = new HashSet<EObject>();
    Set<ExchangeCategory> exchangeCategories = new HashSet<ExchangeCategory>();
    for (DSemanticDecorator selectedView : selectedViews) {
      FunctionalChain selectedFC = (FunctionalChain) selectedView.getTarget();
      toBeSelected.add(selectedFC);

      Set<FunctionalExchange> involvedFEs = FunctionalChainExt.getFlatFunctionalExchanges(selectedFC);

      toBeSelected.addAll(involvedFEs);
      for (FunctionalExchange involvedFE : involvedFEs) {
        toBeSelected.add(involvedFE.getSource());
        toBeSelected.add(involvedFE.getTarget());
        exchangeCategories.addAll(involvedFE.getCategories());
      }
    }

    diagramElementsToBeSelected.addAll(diagramElements.stream()
        .filter(element -> toBeSelected.contains(element.getTarget())).collect(Collectors.toList()));

    for (DDiagramElement diagramElement : diagramElements) {
      if (diagramElement instanceof DEdge) {
        DEdge edge = (DEdge) diagramElement;
        EObject edgeTarget = edge.getTarget();
        if (exchangeCategories.contains(edgeTarget)) {
          boolean shallBeSelected = false;
          for (EObject semanticElement : edge.getSemanticElements()) {
            if (toBeSelected.contains(semanticElement)) {
              // Only add categories edge that represents a CE in "toBeSelected"
              shallBeSelected = true;
              break;
            }
          }
          if (shallBeSelected) {
            EdgeTarget target = edge.getTargetNode();
            EdgeTarget source = edge.getSourceNode();

            if (source instanceof DDiagramElement) {
              diagramElementsToBeSelected.add((DDiagramElement) source);
            }
            if (target instanceof DDiagramElement) {
              diagramElementsToBeSelected.add((DDiagramElement) target);
            }

            diagramElementsToBeSelected.add(edge);
          }

        }
      }

    }

    return diagramElementsToBeSelected;
  }

  /**
   * Returns a collection of all Physical Links, Physical Links Categories and Ports involved in a Physical Path as
   * <code>selectedViews</code> in the <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedPPDecorator
   *          The selected PhysicalPath decorator.
   * @return a collection of all DDiagramElement involved in the PhysicalPath as the selectedViews.
   */
  public Collection<DDiagramElement> getRelatedPhysicalPathElements(DSemanticDiagram currentDiagram,
      List<DSemanticDecorator> selectedViews) {

    Collection<DDiagramElement> diagramElements = new DDiagramQuery(currentDiagram).getAllDiagramElements();
    Collection<DDiagramElement> diagramElementsToBeSelected = new HashSet<DDiagramElement>();

    Set<EObject> toBeSelected = new HashSet<EObject>();
    Set<PhysicalLinkCategory> exchangeCategories = new HashSet<PhysicalLinkCategory>();
    for (DSemanticDecorator selectedView : selectedViews) {
      PhysicalPath selectedPP = (PhysicalPath) selectedView.getTarget();
      Collection<PhysicalLink> involvedPLs = PhysicalPathExt.getFlatPhysicalLinks(selectedPP);

      toBeSelected.add(selectedPP);
      toBeSelected.addAll(involvedPLs);
      for (PhysicalLink involvedPL : involvedPLs) {
        toBeSelected.add(involvedPL.getSourcePhysicalPort());
        toBeSelected.add(involvedPL.getTargetPhysicalPort());
        exchangeCategories.addAll(involvedPL.getCategories());
      }
    }
    diagramElementsToBeSelected.addAll(diagramElements.stream()
        .filter(element -> toBeSelected.contains(element.getTarget())).collect(Collectors.toList()));

    for (DDiagramElement diagramElement : diagramElements) {
      if (diagramElement instanceof DEdge) {
        DEdge edge = (DEdge) diagramElement;
        EObject edgeTarget = edge.getTarget();
        if (exchangeCategories.contains(edgeTarget)) {
          boolean shallBeSelected = false;
          for (EObject semanticElement : edge.getSemanticElements()) {
            if (toBeSelected.contains(semanticElement)) {
              // Only add Category edge that concerns a PL in "toBeSelected"
              shallBeSelected = true;
              break;
            }
          }
          if (shallBeSelected) {
            EdgeTarget target = edge.getTargetNode();
            EdgeTarget source = edge.getSourceNode();

            if (source instanceof DDiagramElement) {
              diagramElementsToBeSelected.add((DDiagramElement) source);
            }
            if (target instanceof DDiagramElement) {
              diagramElementsToBeSelected.add((DDiagramElement) target);
            }

            diagramElementsToBeSelected.add(edge);
          }
        }
      }
    }

    return diagramElementsToBeSelected;
  }

  /**
   * Return a collection of all DDiagramElement having the same mappings as <code>selectedViews</code> in the
   * <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected views. For each view we consider the EType of its semantic elements.
   * @return a collection of all DDiagramElement having the same mappings as the selected views.
   */
  public Collection<DDiagramElement> getViewsWithSameMapping(DSemanticDiagram currentDiagram,
      List<DSemanticDecorator> selectedViews) {

    Set<DiagramElementMapping> mappingsToSelect = selectedViews.stream().filter(view -> view instanceof DDiagramElement)
        .map(view -> ((DDiagramElement) view).getDiagramElementMapping()).collect(Collectors.toSet());

    Collection<DDiagramElement> diagramElements = new DDiagramQuery(currentDiagram).getAllDiagramElements();

    return diagramElements.stream().filter(element -> mappingsToSelect.contains(element.getDiagramElementMapping()))
        .collect(Collectors.toList());
  }

  /**
   * Returns a collection of all DNode ports for the <code>selectedViews</code> in the <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected views.
   * @return the list of all DNode ports for the selected views.
   */
  public Collection<DNode> getAllOwnedPorts(DSemanticDiagram currentDiagram, List<DSemanticDecorator> selectedViews) {

    return selectedViews.stream().filter(view -> view instanceof AbstractDNode)
        .flatMap(view -> ((AbstractDNode) view).getOwnedBorderedNodes().stream()).collect(Collectors.toList());
  }

  /**
   * Returns a collection of all owned DDiagramElement for the <code>selectedViews</code> in the
   * <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected views.
   * @return a collection of all owned DDiagramElement for the selected views.
   */
  public Collection<DDiagramElement> getAllOwnedElements(DSemanticDiagram currentDiagram,
      List<DSemanticDecorator> selectedViews) {

    return selectedViews.stream().flatMap(view -> getOwnedDiagramElements(view).stream()).collect(Collectors.toList());
  }

  /**
   * Returns a collection of all related DEdge for the for the <code>selectedViews</code> in the
   * <code>currentDiagram</code>.
   * 
   * @param currentDiagram
   *          The current {@link DSemanticDiagram}
   * @param selectedViews
   *          The selected views.
   * @return a collection of all related DEdge for the for the selected views.
   */
  public Collection<DEdge> getAllEdges(DSemanticDiagram currentDiagram, List<DSemanticDecorator> selectedViews) {

    Set<DEdge> edgesToSelect = new HashSet<>();

    for (DSemanticDecorator view : selectedViews) {

      if (view instanceof EdgeTarget) {
        EdgeTarget viewEdgeTarget = (EdgeTarget) view;
        Collection<DEdge> directEdges = getEdges(viewEdgeTarget);

        edgesToSelect.addAll(directEdges);
      }

      if (view instanceof AbstractDNode) {
        AbstractDNode viewNode = (AbstractDNode) view;
        Collection<DEdge> portEdges = viewNode.getOwnedBorderedNodes().stream().flatMap(port -> getEdges(port).stream())
            .collect(Collectors.toSet());

        edgesToSelect.addAll(portEdges);
      }
    }

    return edgesToSelect;
  }

  private Collection<DEdge> getEdges(EdgeTarget edgeTarget) {
    HashSet<DEdge> allEdges = new HashSet<>();
    allEdges.addAll(edgeTarget.getIncomingEdges());
    allEdges.addAll(edgeTarget.getOutgoingEdges());

    return allEdges;
  }

  public EObject getDiagramElementDiagramIfNull(DDiagram diagram, EObject semanticElement) {
    DDiagramElement viewElement = getDiagramElement(diagram, semanticElement);
    return viewElement == null ? diagram : viewElement;
  }

  /**
   * Set the position of the given newView relatively to the given node
   * 
   * @param container
   * @param node
   * @param deltaX
   * @param deltaY
   * @return
   */
  public EObject setRelativePositionToNode(AbstractDNode newView, AbstractDNode node, int deltaX, int deltaY) {
    Point location = DiagramHelper.getRelativePositionToNode(node, node.eContainer(), deltaX, deltaY);
    DiagramHelper.setPosition(newView, location);
    return newView;
  }

  /**
   * Set the position of the given newView relatively to the middle of the given edge
   * 
   * @param container
   * @param edge
   * @return
   */
  public EObject setPositionAtMiddleOfEdge(AbstractDNode newView, DEdge edge, int deltaX, int deltaY) {
    Point location = DiagramHelper.getPositionAtMiddleOfEdge(edge, edge.eContainer(), deltaX, deltaY);
    DiagramHelper.setPosition(newView, location);
    return newView;
  }

  /**
   * Set the position of the given newView at the middle of the given edge
   * 
   * @param container
   * @param edge
   * @return
   */
  public EObject setPositionAtMiddleOfEdge(AbstractDNode newView, DEdge edge) {
    return setPositionAtMiddleOfEdge(newView, edge, 0, 0);
  }

  /**
   * Remove all nodes of the given mapping without any incoming/outgoing edge
   * 
   * @param context
   * @param nodeMapping
   */
  public void removeNodeWithoutEdges(DDiagramContents context, AbstractNodeMapping nodeMapping) {
    Collection<DNode> toRemoveNodes = new HashSet<>();
    for (DDiagramElement element : getCache(context::getDiagramElements, nodeMapping)) {
      if (element instanceof DNode && !isNodeEdged((DNode) element)) {
        toRemoveNodes.add((DNode) element);
      }
    }

    for (DNode dNode : toRemoveNodes) {
      removeAbstractDNodeView(dNode);
    }

  }

  /**
   * this function check if a node is targeted by an edge or not
   * 
   * @param node
   *          represent an eventual edge target
   * @return this function return true if the node is targeted by an edge otherwise it return false
   */
  public boolean isNodeEdged(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    Set<DEdge> incomingEdges = new HashSet<>(node.getIncomingEdges());

    Set<DEdge> diagramEdges = new HashSet<>(diagram.getEdges());

    incomingEdges.retainAll(diagramEdges);
    if (!incomingEdges.isEmpty()) {
      return true;
    }

    Set<DEdge> outgoingEdges = new HashSet<>(node.getOutgoingEdges());

    outgoingEdges.retainAll(diagramEdges);
    if (!outgoingEdges.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * Force refresh the representation embedded in an editor. This method must be called in a transaction.
   * 
   * @param editor
   */
  public void refreshRepresentationOfEditor(IEditorPart editor) {
    if (editor instanceof DialectEditor) {
      DialectEditor siriusEditor = (DialectEditor) editor;
      DRepresentation representation = siriusEditor.getRepresentation();
      CapellaServices.getService().forceRefresh((DDiagram) representation);
    }
  }

  /**
   * Returns the GraphicalEditPart of the given DDiagramElement
   * 
   * @param diagramElement
   * @return
   */
  public EditPart getEditPart(DDiagramElement diagramElement) {
    IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if (editor instanceof DiagramEditor) {
      Session session = new EObjectQuery(diagramElement).getSession();
      View gmfView = SiriusGMFHelper.getGmfView(diagramElement, session);

      if (gmfView != null && editor instanceof DiagramEditor) {
        final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
        final Object editPart = editPartRegistry.get(gmfView);
        if (editPart instanceof EditPart) {
          return (EditPart) editPart;
        }
      }
    }
    return null;
  }

  /**
   * Refresh the begin EditPart and end EditPart of a DEdge
   */
  @SuppressWarnings("unchecked")
  public void refreshBeginEndLabels(DEdge edge) {
    EditPart edgeEditPart = getEditPart(edge);
    // Refresh BeginNameEditPart and EndNameEditPart
    if (edgeEditPart != null) {
      edgeEditPart.getChildren().stream()
          .filter(child -> child instanceof DEdgeBeginNameEditPart || child instanceof DEdgeEndNameEditPart)
          .forEach(editPart -> ((EditPart) editPart).refresh());
    }
  }

  /**
   * 
   * @return the currently opening diagram
   */
  public DDiagram getOpeningDiagram() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      IWorkbenchPage activePage = window.getActivePage();
      if (activePage != null) {
        IEditorPart activeEditor = activePage.getActiveEditor();
        if (activeEditor instanceof DDiagramEditor) {
          DDiagramEditor ddiagramEditor = (DDiagramEditor) activeEditor;
          if (ddiagramEditor.getRepresentation() instanceof DDiagram) {
            return (DDiagram) ddiagramEditor.getRepresentation();
          }
        }
      }
    }
    return null;
  }

  public String getOverlappedLabels(List<String> names) {
    return names.stream().sorted().map(name -> StringUtils.abbreviate(name, OVERLLAPING_LABEL_MAX_LENGTH))
        .map(name -> StringUtils.rightPad(name, OVERLLAPING_LABEL_MAX_LENGTH)).collect(Collectors.joining("\n"));
  }
}
