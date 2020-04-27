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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 * 
 */
public class ContextualComponentExternalInterfacesRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);

    if (((DSemanticDecorator) diagram).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }

    final Component component = (Component) ((DSemanticDecorator) diagram).getTarget();

    HashSet<Component> components = new HashSet<>();
    Map<EObject, DragAndDropTarget> elements = DiagramServices.getDiagramServices().getMapOfDiagramNodes(diagram);

    // Add related interfaces on the diagram
    NodeMapping interfaceMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCEI_INTERFACE);
    for (final Interface itf : CsServices.getService().getRelatedInterfaces(component)) {
      if (!elements.containsKey(itf)) {
        DiagramServices.getDiagramServices().createAbstractDNode(interfaceMapping, itf, diagram, diagram);
      }
      // add only user/require components
      // if there is no impl/provide interface for 'component'
      List<Interface> implementedAndProvidedInterfaces = ComponentExt.getImplementedAndProvidedInterfaces(component);
      if (!implementedAndProvidedInterfaces.isEmpty()) {
        for (Interface implProInterface : implementedAndProvidedInterfaces) {
          if (implProInterface.equals(itf)) {
            // add require components
            Collection<Component> relatedComponents = InterfaceExt.getRequireComponent(itf);
            // add userComponents
            relatedComponents.addAll(itf.getUserComponents());
            for (Component component2 : relatedComponents) {
              components.add(component2);
            }
          }
        }
      }
      // add only impl/provide components
      // if there is no use/require interface for 'component'
      List<Interface> usedAndRequiredInterfaces = ComponentExt.getUsedAndRequiredInterfaces(component);
      if (!usedAndRequiredInterfaces.isEmpty()) {
        for (Interface useReqInterface : usedAndRequiredInterfaces) {
          if (useReqInterface.equals(itf)) {
            // add provide components
            Collection<Component> relatedComponents = InterfaceExt.getProviderComponent(itf);
            // add implementor components
            relatedComponents.addAll(itf.getImplementorComponents());
            for (Component component2 : relatedComponents) {
              components.add(component2);
            }
          }
        }
      }
    }

    // Add related exchange items on the diagram
    NodeMapping exchangeItemMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram,
        IMappingNameConstants.CCEI_EXCHANGE_ITEM_MAPPING_NAME);
    for (final CommunicationLink link : CsServices.getService().getRelatedCommunicationLinks(component)) {
      AbstractExchangeItem item = link.getExchangeItem();
      if (item != null && !elements.containsKey(item)) {
        DiagramServices.getDiagramServices().createNode(exchangeItemMapping, item, diagram, diagram);

        for (CommunicationLinkExchanger related : CsServices.getService().getRelatedExchangers(item)) {
          if (related instanceof Component)
            components.add((Component) related);
        }
      }
    }

    // Add related components of interfaces which are in the namespace of the
    // element
    ContainerMapping componentMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.CCEI_COMPONENT);
    components.removeAll(CsServices.getService().getParentContainersByParts(component));
    Collection<EObject> namespace = CsServices.getService().getAvailableComponentsByNamespaceOfParts(component);
    components.add(component);
    namespace.add(component);

    for (final Component current : components) {
      if (namespace.contains(current) && !elements.containsKey(current)) {
        DiagramServices.getDiagramServices().createContainer(componentMapping, current, diagram, diagram);
      }
    }
  }
}
