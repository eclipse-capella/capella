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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.PhysicalServices;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;
import org.polarsys.capella.core.sirius.analysis.cache.PhysicalPathCache;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.helpers.FilterHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

public class ComponentArchitectureBlankRefreshExtension extends AbstractCacheAwareRefreshExtension {

  private final List<String> monitoredFilters = Arrays.asList(IMappingNameConstants.HIDE_CE_BY_DELEGATION,
      IMappingNameConstants.HIDE_CE_BY_GROUP, IMappingNameConstants.HIDE_CE_BY_GROUP_ORIENTED,
      IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_CE, IFilterNameConstants.FILTER_XAB_HIDE_COMPUTED_PL);

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);

    FunctionalChainCache.getInstance().reset();
    PhysicalPathCache.getInstance().reset();
    DDiagram openingDiagram = DiagramServices.getDiagramServices().getOpeningDiagram();
    // Avoid resetting the icon cache if it's not on the diagram currently being opened
    if (openingDiagram == null || openingDiagram == diagram) {
      DEdgeIconCache.getInstance().reset();
    }

    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);

    // -------------------------------------
    // Monitor some filters desactivation
    // -------------------------------------

    FilterHelper.monitorDesactivation(monitoredFilters, descriptor);    

    // -------------------------------------
    // Change target of diagram to the related part
    // -------------------------------------

    updateTargetDiagram(diagram, descriptor);

    // -------------------------------------
    // Remove all functions added directly on diagram.
    // Fix for bug 560092, can be deleted in Capella 6.0
    // -------------------------------------

    String mappingName = MappingConstantsHelper.getMappingFunction(diagram);
    if (mappingName != null) {
      NodeMapping mapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
      if (mapping != null) {
        Collection<DDiagramElement> nodes = diagram.getOwnedDiagramElements().stream() //
            .filter(AbstractDNode.class::isInstance) //
            .filter(d -> d.getTarget() instanceof AbstractFunction) //
            .filter(d -> mapping.equals(d.getDiagramElementMapping())) //
            .collect(Collectors.toList());
        nodes.stream().forEach(d -> DiagramServices.getDiagramServices().removeContainerView(d));
      }
    }

    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------

    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram);
    Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(descriptor);

    try {
      CsServices.getService().showABContextualElements(context, contextualElements);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }

    // -------------------------------------
    // Reorder elements in best containers
    // -------------------------------------

    try {
      reorderElements(diagram);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }

    // -------------------------------------
    // Update categories
    // -------------------------------------

    try {
      updateComponentCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnUpdateComponentCategories, e);
    }

    try {
      updatePhysicalCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnUpdatePhysicalCategories, e);
    }

    try {
      updateFunctionalExchangeCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnUpdateFECategories, e);
    }
    // -------------------------------------
    // Commit elements
    // -------------------------------------

    try {
      context.commitDeferredActions();
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnCommitDeferredActions, e);
    }
  }

  /**
   * Updates the diagram target according to business rules.
   * <ol>
   * <li>If the target is a Component, the associated part is created if absent.</li>
   * <li>If the target is a Part, in monopart mode the diagram is moved to the type component, in multipart the target is untouched.</li>
   * </ol>
   * 
   * @param diagram
   * @param descriptor
   */
  protected void updateTargetDiagram(DDiagram diagram, DRepresentationDescriptor descriptor) {
    if (diagram instanceof DSemanticDiagram) {
      DSemanticDiagram semanticDiagram = (DSemanticDiagram) diagram;
      EObject target = semanticDiagram.getTarget();

      if (target instanceof Component) {
        CsServices.getService().createRepresentingPartIfNone((Component) target);

      } else if (target instanceof Part) {
        Part part = (Part) target;

        if (!CsServices.getService().isMultipartMode(part)) {
          EObject type = CsServices.getService().getComponentType(part);
          
          if (type instanceof Component) {
            RepresentationHelper.setTarget(descriptor, semanticDiagram, type);            
          }
        }
      }
    }
  }

  /**
   * 
   */
  protected void updateComponentCategories(DDiagramContents context) {

    DDiagram diagram = context.getDDiagram();
    Collection<EObject> categories = getCache(ABServices::getComponentExchangeCategories, diagram);
    if (!categories.isEmpty()) {
      if (diagram.isSynchronized()) {

        ABServices.getService().switchABComponentCategories(context, (DSemanticDecorator) context.getDDiagram(),
            categories, false);
      } else {
        ABServices.getService().updateABComponentCategories(context);
      }
    }
  }

  protected void updateFunctionalExchangeCategories(DDiagramContents diagramContents) {
    DDiagram diagram = diagramContents.getDDiagram();
    Collection<ExchangeCategory> categories = getCache(ABServices::getExchangeCategories, diagram);
    if (!categories.isEmpty()) {
      if (diagram.isSynchronized()) {
        FaServices.getFaServices().switchFECategories(diagramContents, (DSemanticDecorator) diagram, categories, false);
      }
    }
    ABServices.getService().updateABFunctionalCategories(diagramContents);
  }

  /**
   * 
   */
  protected void updatePhysicalCategories(DDiagramContents context) {

    DDiagram diagram = context.getDDiagram();
    Collection<EObject> categories = getCache(ABServices::getPhysicalLinkCategory, diagram);
    if (!categories.isEmpty()) {
      if (diagram.isSynchronized()) {
        ABServices.getService().switchABPhysicalCategories(context, (DSemanticDecorator) context.getDDiagram(),
            categories, false);
      } else {
        ABServices.getService().updateABPhysicalCategories(context);
      }
    }
  }

  @Override
  public void reorderElements(DDiagram diagram) {

    DDiagramContents content = new DDiagramContents(diagram) {

      @Override
      public Collection<EObject> getParents(EObject object, EObject context) {

        LinkedList<EObject> parents = new LinkedList<>();

        if (object instanceof Part && context instanceof DNodeContainer) {
          EObject contextPart = ((DNodeContainer) context).getTarget();
          if (CsServices.getService().isDeployed((DNodeContainer) context)) {
            parents.addAll(getCache(PartExt::getDeployingElements, (Part) object));
          } else {
            parents.add(CsServices.getService().getParentContainer(object));
          }
          parents.remove(contextPart);
        }
        return parents;
      }
    };

    // All displayed elements in the diagram
    HashMapSet<AbstractType, DNodeContainer> typeViews = new HashMapSet<>();

    // All displayed elements in the diagram
    HashMapSet<Part, DNodeContainer> partViews = new HashMapSet<>();

    // Diagram elements to be moved
    Set<DNodeContainer> toBeMoved = new HashSet<>();

    // Retrieve all mappings to be moved
    List<AbstractNodeMapping> mappingsToMove = getListOfMappingsToMove(diagram);

    // get all displayed parts in the diagram
    for (AbstractDNode aContainer : diagram.getContainers()) {

      if (aContainer instanceof DNodeContainer && aContainer.getTarget() instanceof Part && isReorderable(aContainer)
          && mappingsToMove.contains(aContainer.getDiagramElementMapping())) {
        Part currentPart = (Part) aContainer.getTarget();
        AbstractType currentType = CsServices.getService().getComponentType(currentPart);
        typeViews.put(currentType, (DNodeContainer) aContainer);
        partViews.put(currentPart, (DNodeContainer) aContainer);
      }
    }

    // first iteration (to avoid null container)
    // the elements to be moved are temporarily placed in the diagram
    // retrieve the displayed part to be moved since container is no more the same
    // than model

    for (Entry<AbstractType, Collection<DNodeContainer>> typeView : typeViews.entrySet()) {

      Collection<DNodeContainer> views = typeView.getValue();

      for (DNodeContainer anElement : views) {

        Part currentPart = (Part) anElement.getTarget();
        boolean willBeMoved = false;

        if (anElement.eContainer() instanceof DSemanticDecorator) {
          DSemanticDecorator containerView = (DSemanticDecorator) anElement.eContainer();
          EObject actualContainer = containerView.getTarget();
          EObject actualComponentContainer = CsServices.getService().getComponentType(containerView);

          // If container has no target, move the element
          if (actualContainer == null) {
            willBeMoved = true;

            // If element is owned by diagram and there is a view of the container in the
            // diagram
          } else if ((containerView instanceof DDiagram) && (actualComponentContainer != null)
              && (actualComponentContainer instanceof Component)
              && (!typeViews.get(actualComponentContainer).isEmpty())) {
            willBeMoved = true;

          } else {

            // It will be moved only if it is not already owned by a parent.
            willBeMoved = true;
            for (EObject currentParent : content.getParents(currentPart, anElement)) {
              // case if the actual container is not the same that the actual container of the
              // part
              if (currentParent != null
                  && (currentParent.equals(actualContainer) || currentParent.equals(actualComponentContainer))) {
                willBeMoved = false;
                break;
              }
            }

          }

          if (willBeMoved) {
            toBeMoved.add(anElement);
            break;
          }
        }

        if (willBeMoved) {
          break;
        }
      }
    }

    // Move all toBeMoved parts to a better container
    // (which is here the first part of a parents where the part has not yet been
    // added)
    // If a same part has been added in all firstLevel parent parts, add to thefirst
    // firstLevel parent part.
    // If no first level parent part, browse upper.
    for (DNodeContainer aContainer : toBeMoved) {
      Part currentPart = (Part) aContainer.getTarget();

      LinkedList<EObject> parents = new LinkedList<>();
      HashSet<EObject> visitedParents = new HashSet<>();
      boolean toBeDeleted = false;
      boolean isAdded = false;

      parents.addAll(content.getParents(currentPart, aContainer));

      // If not yet added, browse parts of a parent
      while (!isAdded && !toBeDeleted && !parents.isEmpty()) {
        EObject parent = parents.removeFirst();
        if (visitedParents.contains(parent)) {
          continue;
        }
        visitedParents.add(parent);

        if (parent instanceof Component) {
          Component parentElement = (Component) parent;
          if (typeViews.get(parentElement).size() == 1) {
            // Add the part in the first partView which haven't the part
            for (DNodeContainer container : typeViews.get(parentElement)) {
              if (!container.getOwnedDiagramElements().contains(aContainer) && (aContainer != container.eContainer())) {
                container.getOwnedDiagramElements().add(aContainer);
              }
              isAdded = true;
            }
          } else if (typeViews.get(parentElement).size() > 1) {
            toBeDeleted = true;
          }

          if (!isAdded && !toBeDeleted) {
            for (Part part : parentElement.getRepresentingParts()) {
              parents.addAll(content.getParents(part, aContainer));
            }
          }

        } else if (parent instanceof Part) {
          Part parentElement = (Part) parent;

          if (partViews.get(parentElement).size() == 1) {
            // Add the part in the first partView which haven't the part
            for (DNodeContainer container : partViews.get(parentElement)) {
              if (!container.getOwnedDiagramElements().contains(aContainer)) {
                container.getOwnedDiagramElements().add(aContainer);
              }
              isAdded = true;
            }

          } else if (partViews.get(parentElement).size() > 1) {
            toBeDeleted = true;
          }

          if (!isAdded && !toBeDeleted) {
            parents.addAll(content.getParents(parent, aContainer));
          }

        }

      }

      // If not yet added and there is a partView, add to it. Otherwise, go to
      // parents.
      if (!isAdded && !toBeDeleted) {
        if (!diagram.getOwnedDiagramElements().contains(aContainer)) {
          diagram.getOwnedDiagramElements().add(aContainer);
        }
      }

      // If not yet added and there is a partView, add to it. Otherwise, go to
      // parents.
      if (toBeDeleted) {
        // If there is no free parent, add to the first parent
        DiagramServices.getDiagramServices().removeContainerView(aContainer);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    List<AbstractNodeMapping> returnedList = new ArrayList<>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.SAB_ACTOR_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.EAB_CI));
    return returnedList;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {

    try {
      FunctionalChainServices.getFunctionalChainServices().updateFunctionalChainStyles(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnUpdateFunctionalChainStyle, e);
    }

    try {
      List<String> physicalPathSupportingDiagrams = Arrays.asList(
          IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME,
          IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME,
          IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME);
      if (physicalPathSupportingDiagrams.contains(diagram.getDescription().getName())) {
        PhysicalServices.getService().updateInternalPhysicalPaths(diagram);
        PhysicalServices.getService().updatePhysicalPathStyles(diagram);
      }
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
          .error(Messages.RefreshExtension_ErrorOnUpdatePhysicalPathStyle, e);
    }

    FunctionalChainCache.getInstance().reset();
    PhysicalPathCache.getInstance().reset();
    super.postRefresh(diagram);
  }

  @Deprecated
  /**
   * unused
   */
  public ContainerMapping getComponentMapping(DDiagram diagram) {
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.SAB_ACTOR_MAPPING_NAME);
    }
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME);
    }
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME);
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
  }
}
