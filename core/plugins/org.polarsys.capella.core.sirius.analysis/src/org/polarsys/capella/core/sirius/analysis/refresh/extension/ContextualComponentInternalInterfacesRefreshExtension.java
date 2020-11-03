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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 *
 */
public class ContextualComponentInternalInterfacesRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    
    if (((DSemanticDecorator) diagram).getTarget() == null) {
      //avoid refresh on dirty diagram
      return;
    }

    EObject root = ((DSemanticDecorator) diagram).getTarget();

    if (!(root instanceof Component)) {
      return;
    }

    DDiagramContents content = new DDiagramContents(diagram);

    ContainerMapping componentMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CCII_COMPONENT);

    LinkedList<Component> components = new LinkedList<>();
    List<Interface> interfaces = new LinkedList<>();
    List<CommunicationLink> links = new LinkedList<>();

    //Add root element and subcomponent on the diagram
    Component component = (Component) root;
    components.addAll(CsServices.getService().getSubUsedComponents(component));
    components.addFirst(component);

    for (Component current : components) {
      if (!content.containsView(current, componentMapping)) {
        DNodeContainer created = DiagramServices.getDiagramServices().createContainer(componentMapping, current, content.getBestContainer(current), diagram);
        content.addView(created);
      }
      interfaces.addAll(CsServices.getService().getRelatedInterfaces(current));
      links.addAll(CsServices.getService().getRelatedCommunicationLinks(current));
    }

    //add related interfaces on containing component
    NodeMapping interfaceMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CCII_INTERFACE);
    for (final Interface itf : interfaces) {
      if (!content.containsView(itf, interfaceMapping)) {
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(interfaceMapping, itf, content.getBestContainer(itf), diagram);
        content.addView(node);
      }
    }

    //Add related exchange items on the diagram
    NodeMapping exchangeItemMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME);
    for (final CommunicationLink link : links) {
      AbstractExchangeItem item = link.getExchangeItem();
      if ((item != null) && !content.containsView(item, exchangeItemMapping)) {
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(exchangeItemMapping, item, content.getBestContainer(item), diagram);
        content.addView(node);
      }
    }

    reorderElements(content);
  }

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    List<AbstractNodeMapping> returnedList = new ArrayList<>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CCII_COMPONENT));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CCII_INTERFACE));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram, IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME));
    return returnedList;
  }
}
