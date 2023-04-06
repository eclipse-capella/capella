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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideIDRelationships;

/**
 * Shared helpers for Architecture Blank diagrams
 */
public class IBServices {

  private static IBServices service = null;

  public static IBServices getService() {
    if (service == null) {
      service = new IBServices();
    }
    return service;
  }

  /**
   * Show/Hide of AbstractEventOperation in classes and interfaces used in common.odesign
   */
  public EObject showHideIDExchangeItems(EObject context, List<CapellaElement> selectedOperations, DDiagram diagram) {

    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      if ((aNode instanceof AbstractDNode) && (aNode.getTarget() != null)
          && (aNode.getTarget() instanceof ExchangeItem)) {
        visibleElements.put((CapellaElement) aNode.getTarget(), (AbstractDNode) aNode);
      }
    }

    // remove elements
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedOperations.contains(me.getKey())) {
        if (me.getValue() instanceof DNodeList) {
          DiagramServices.getDiagramServices().removeContainerView(me.getValue());
        } else if (me.getValue() instanceof DNode) {
          DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
        }
      }
    }
    // create elements
    for (CapellaElement aOperation : selectedOperations) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(aOperation, diagram,
          CsPackage.Literals.COMPONENT);
      if (!visibleElements.containsKey(aOperation)) {
        createIDExchangeItemView((DragAndDropTarget) container, aOperation, diagram);
      }
    }
    return context;
  }

  /**
   * @param context
   * @param aOperation
   * @param diagram
   */
  protected AbstractDNode createIDExchangeItemView(DragAndDropTarget containerView, CapellaElement aOperation,
      DDiagram diagram) {
    AbstractNodeMapping mapping = IBServices.getService().getMappingIDExchangeItem(aOperation, diagram);
    return DiagramServices.getDiagramServices().createAbstractDNode(mapping, aOperation, containerView, diagram);
  }

  public AbstractNodeMapping getMappingIDInterface(EObject context, DDiagram diagram) {
    boolean isContainerMapping = true;

    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_INTERFACE;
      isContainerMapping = false;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_INTERFACE;
      isContainerMapping = false;
    }

    if (isContainerMapping) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
    }

    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  /**
   * In Interface Diagrams, we want to create a NODE when parent component is NODE, BEHAVIOR otherwise
   */
  public void setIBCreationNature(EObject current, EObject container) {
    if (current instanceof PhysicalComponent) {
      if (container instanceof PhysicalComponent
          && ((PhysicalComponent) container).getNature() == PhysicalComponentNature.NODE) {
        ((PhysicalComponent) current).setNature(PhysicalComponentNature.NODE);
      } else {
        ((PhysicalComponent) current).setNature(PhysicalComponentNature.BEHAVIOR);
      }
    } else if (current instanceof ConfigurationItem) {
      ((ConfigurationItem) current).setKind(ConfigurationItemKind.SYSTEM_CI);
    }
  }
  
  public EdgeMapping getMappingIDCommunicationLink(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_COMMUNICATION_LINK_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_COMMUNICATION_LINK_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_COMMUNICATION_LINK_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_COMMUNICATION_LINK_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * Crate interface view with proper mapping
   * 
   * @param context
   * @param aOperation
   * @param diagram
   */
  public EObject createIDInterfaceView(DragAndDropTarget context, CapellaElement aOperation, DDiagram diagram) {
    AbstractNodeMapping mapping = getMappingIDInterface(aOperation, diagram);
    return DiagramServices.getDiagramServices().createAbstractDNode(mapping, aOperation, context, diagram);
  }

  public EdgeMapping getMappingIDGeneralization(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_GENERALIZATION_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_GENERALIZATION_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_GENERALIZATION_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_GENERALIZATION_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public EdgeMapping getMappingIDInterfaceUse(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_USE_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_USE_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_USE_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_USE_INTERFACE_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public EdgeMapping getMappingIDInterfaceImplementation(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_IMPLEMENTATION_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_IMPLEMENTATION_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_IMPLEMENTATION_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_IMPLEMENTATION_INTERFACE_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public EdgeMapping getMappingIDInterfaceProvide(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_PROVIDED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_PROVIDED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_PROVIDED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_PROVIDED_INTERFACE_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public EdgeMapping getMappingIDInterfaceRequire(EObject context, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_REQUIRED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_REQUIRED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_REQUIRED_INTERFACE_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_REQUIRED_INTERFACE_MAPPING_NAME;
    }

    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  public AbstractNodeMapping getMappingIDComponent(EObject element, DDiagram diagram) {

    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_COMPONENT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_COMPONENT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_COMPONENT;

    }
    
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);

  }

  public AbstractNodeMapping getMappingIDComponentPort(EObject element, DDiagram diagram) {
    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_COMPONENT_PORT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_COMPONENT_PORT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_COMPONENT_PORT_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_COMPONENT_PORT_MAPPING_NAME;

    }

    return DiagramServices.getDiagramServices().getBorderedNodeMapping(diagram, mappingName);
  }

  public AbstractNodeMapping getMappingIDExchangeItem(EObject context, DDiagram diagram) {
    boolean isContainerMapping = true;

    String mappingName = ""; //$NON-NLS-1$
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.IDB_EXCHANGE_ITEM_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCDI_EXCHANGE_ITEM_MAPPING_NAME;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCEI_EXCHANGE_ITEM_MAPPING_NAME;
      isContainerMapping = false;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME;
      isContainerMapping = false;
    }

    if (isContainerMapping) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
    }

    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  public Interface createCCDIInterface(DSemanticDecorator containerView) {
    EObject container = containerView.getTarget();
    if (container != null) {
      EObject target = CsServices.getService().getParentContainer(container);
      InterfacePkg pkg = null;
      if (target instanceof BlockArchitecture) {
        pkg = BlockArchitectureExt.getInterfacePkg((BlockArchitecture) target);

      } else if (target instanceof Component) {
        pkg = ComponentExt.getInterfacePkg((Component) target);
      }

      if (pkg != null) {
        Interface itf = CsFactory.eINSTANCE.createInterface();
        pkg.getOwnedInterfaces().add(itf);
        CapellaElementExt.creationService(itf);
        createIDInterfaceView((DragAndDropTarget) containerView, itf,
            CapellaServices.getService().getDiagramContainer(containerView));
        return itf;
      }
    }
    return null;
  }

  @Deprecated
  public Collection<EObject> getAvailableInterfacesAndLinksFromComponentToInsert(EObject containerView) {
    return getIDInsertRemoveRelationshipsScope(containerView);
  }

  @Deprecated
  public Collection<EObject> getExistingInterfacesAndLinksFromDiagram(EObject containerView) {
    return getIDInsertRemoveRelationshipsInitialSelection(containerView,
        getAvailableInterfacesAndLinksFromComponentToInsert(containerView));
  }

  public Collection<EObject> getIDInsertRemoveRelationshipsScope(EObject context) {
    List<EObject> result = new ArrayList<>();

    EObject target = context;
    if (target instanceof DSemanticDecorator) {
      return getIDInsertRemoveRelationshipsScope(((DSemanticDecorator) context).getTarget());
    }
    if (target instanceof Component) {
      Component component = (Component) target;
      EList<InterfaceImplementation> implementedInterfaceLinks = component.getImplementedInterfaceLinks();
      if (null != implementedInterfaceLinks) {
        result.addAll(implementedInterfaceLinks);
      }
      EList<InterfaceUse> usedInterfaceLinks = component.getUsedInterfaceLinks();
      if (null != usedInterfaceLinks) {
        result.addAll(usedInterfaceLinks);
      }

      result.addAll(CsServices.getService().getRelatedCommunicationLinks(component));
      result.addAll(component.getSuperGeneralizations());
      result.addAll(component.getSubGeneralizations());

    } else if (target instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) target;
      EList<Interface> providedInterfaces = port.getProvidedInterfaces();
      if (null != providedInterfaces) {
        result.addAll(providedInterfaces);
      }
      EList<Interface> requitedInterfaces = port.getRequiredInterfaces();
      if (null != requitedInterfaces) {
        result.addAll(requitedInterfaces);
      }
    } else if (target instanceof Interface) {
      Interface itf = (Interface) target;
      result.addAll(itf.getSuperGeneralizations());
      result.addAll(itf.getSubGeneralizations());
    }
    return result;
  }

  public List<EObject> getIDInsertRemoveRelationshipsInitialSelection(EObject sourceView, Collection scope) {
    List<EObject> result = new ArrayList<>();
    DDiagram diagramContainer = CapellaServices.getService().getDiagramContainer(sourceView);
    EObject sourceTarget = ((DSemanticDecorator) sourceView).getTarget();

    if ((null != sourceTarget) && (null != diagramContainer)) {
      if ((sourceTarget instanceof Component) || (sourceTarget instanceof Interface)) {
        // add only implementation and use edges from diagram
        for (Object capellaElement : scope) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, (EObject) capellaElement)) {
            result.add((EObject) capellaElement);
          }
        }

      } else if (sourceTarget instanceof ComponentPort) {
        // add only provided and required edges from diagram
        for (Object capellaElement : scope) {
          if (DiagramServices.getDiagramServices().isOnDiagram(diagramContainer, (EObject) capellaElement)) {
            // check if there is provided or required link between sourceView and capellaElement
            EList<DEdge> outgoingEdges = ((DNode) sourceView).getOutgoingEdges();
            for (DEdge dEdge : outgoingEdges) {
              EdgeTarget edgeSNode = dEdge.getSourceNode();
              EdgeTarget edgeTNode = dEdge.getTargetNode();
              if ((null != edgeSNode) && (null != edgeTNode)) {
                EObject edgeSTarget = ((DSemanticDecorator) edgeSNode).getTarget();
                EObject edgeTTarget = ((DSemanticDecorator) edgeTNode).getTarget();
                if ((null != edgeSTarget) && (null != edgeTTarget)) {
                  if (edgeSTarget.equals(sourceTarget) && edgeTTarget.equals(capellaElement)) {
                    result.add((EObject) capellaElement);
                  }
                }
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * @common.odesign show/hide use, impl, required and provided links
   */
  public EObject insertRemoveIDRelationships(EObject sourceView, Collection scope, Collection initialSelection,
      Collection selectedElements) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    DDiagramContents content = new DDiagramContents(diagram);
    EObject semantic = ((DSemanticDecorator) sourceView).getTarget();
    AbstractShowHide shService = new ShowHideIDRelationships(content);

    for (Object object : selectedElements) {
      DiagramContext context = shService.new DiagramContext();
      context.setVariable(ShowHideIDRelationships.INITIAL_SOURCE_VIEW, sourceView);

      if (semantic instanceof ComponentPort) {
        if ((object instanceof Interface) && ((ComponentPort) semantic).getProvidedInterfaces().contains(object)) {
          context.setVariable(ShowHideIDRelationships.PROVIDED_INTERFACE, object);
          shService.show(semantic, context);

        } else if ((object instanceof Interface)
            && ((ComponentPort) semantic).getRequiredInterfaces().contains(object)) {
          context.setVariable(ShowHideIDRelationships.REQUIRED_INTERFACE, object);
          shService.show(semantic, context);
        }

      } else {
        shService.show((EObject) object, context);

      }
    }

    for (Object object : initialSelection) {
      DiagramContext context = shService.new DiagramContext();
      context.setVariable(ShowHideIDRelationships.INITIAL_SOURCE_VIEW, sourceView);

      if (!selectedElements.contains(object)) {

        if (semantic instanceof ComponentPort) {
          if ((object instanceof Interface) && ((ComponentPort) semantic).getProvidedInterfaces().contains(object)) {
            context.setVariable(ShowHideIDRelationships.PROVIDED_INTERFACE, object);
            shService.hide(semantic, context);

          } else if ((object instanceof Interface)
              && ((ComponentPort) semantic).getRequiredInterfaces().contains(object)) {
            context.setVariable(ShowHideIDRelationships.REQUIRED_INTERFACE, object);
            shService.hide(semantic, context);
          }

        } else {
          shService.hide((EObject) object, context);

        }

      }
    }

    return sourceView;
  }

  /**
   * @used in common.odesign Remove views and Create views depending on the selected element list
   * @param context
   *          : diagram context
   * @param selectedInterfaces
   *          : list of element to be show in diagram
   * @param diagram
   *          current diagram
   * @return
   */
  public EObject showHideInterfaces(EObject context, List<CapellaElement> selectedInterfaces,
      List<CapellaElement> scope, DDiagram diagram) {
    Map<CapellaElement, AbstractDNode> visibleElements = new HashMap<>();
    for (DDiagramElement aNode : DiagramServices.getDiagramServices().getDiagramElements(diagram)) {
      EObject target = aNode.getTarget();
      if ((aNode instanceof AbstractDNode) && (target != null) && (target instanceof Interface)) {
        visibleElements.put((CapellaElement) target, (AbstractDNode) aNode);
      }
    }

    // remove elements : if view is of type DContainer or DNode
    // any new type should be taken into consideration
    for (Entry<CapellaElement, AbstractDNode> me : visibleElements.entrySet()) {
      if (!selectedInterfaces.contains(me.getKey()) && scope.contains(me.getKey())) {
        AbstractDNode node = me.getValue();
        if (node.isVisible()) {
          if ((node instanceof DDiagramElementContainer) || (node instanceof DDiagram)) {
            DiagramServices.getDiagramServices().removeContainerView(me.getValue());
          } else if (node instanceof DNode) {
            DiagramServices.getDiagramServices().removeNodeView((DNode) me.getValue());
          }
        }
      }
    }
    // create elements
    for (CapellaElement aOperation : selectedInterfaces) {
      EObject container = CapellaServices.getService().getBestGraphicalContainer(aOperation, diagram,
          CsPackage.Literals.INTERFACE);
      if (!visibleElements.containsKey(aOperation)) {
        // create node or container mapping for interface
        createIDInterfaceView((DragAndDropTarget) container, aOperation, diagram);
      }
    }
    return context;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationCCDIInterface(DSemanticDecorator containerView) {
    if (!((!(containerView instanceof DDiagram)) && ComponentExt.isActor(containerView.getTarget()))
        && !(((DSemanticDecorator) CapellaServices.getService().getDiagramContainer(containerView))
            .getTarget() instanceof ConfigurationItem)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationIBDelegationExchange(EObject root, DSemanticDecorator preSourceView,
      DSemanticDecorator preTargetView) {
    return CsServices.getService().isValidCreationABDelegationExchange(root, preSourceView, preTargetView);
  }

  public Object getIBInterfaceUseSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Component) {
        result.addAll(((Component) target).getUsedInterfaceLinks());
      }
    }

    return result;
  }

  public Object getIBInterfaceImplementationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Component) {
        result.addAll(((Component) target).getImplementedInterfaceLinks());
      }
    }

    return result;
  }

  public Object getIBComponentExchangeSemanticCandidates(DDiagram diagram) {
    Collection<ComponentExchange> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Part) {
        result.addAll(ComponentExt.getAllRelatedComponentExchange((Part) target, true));
      } else if (target instanceof Component) {
        result.addAll(getCache(ComponentExt::getAllRelatedComponentExchange, (Component) target));
      }
    }

    return result;
  }

  public Object getIBGeneralizationSemanticCandidates(DDiagram diagram) {
    return InformationServices.getService().getCDBGeneralizationSemanticCandidates(diagram);
  }

  public Object getIBCommunicationLinkSemanticCandidates(DDiagram diagram) {
    Collection<CommunicationLink> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Component) {
        result.addAll(((Component) target).getOwnedCommunicationLinks());
      }
    }

    return result;
  }

  public Object getIBExchangeItemAllocationSemanticCandidates(DDiagram diagram) {
    Collection<ExchangeItemAllocation> result = new ArrayList<>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Interface) {
        result.addAll(((Interface) target).getOwnedExchangeItemAllocations());
      }
    }

    return result;
  }
}
