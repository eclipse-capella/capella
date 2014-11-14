/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.NodeMapping;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * Extended refresh to display the content of the focused module.
 *
 */
public class ContextualComponentInternalInterfacesRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram_p) {
    if (((DSemanticDecorator) diagram_p).getTarget() == null) {
      //avoid refresh on dirty diagram
      return;
    }

    EObject root = ((DSemanticDecorator) diagram_p).getTarget();

    if (!(root instanceof Component)) {
      return;
    }

    DDiagramContents content = new DDiagramContents(diagram_p);

    ContainerMapping componentMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CCII_COMPONENT);

    LinkedList<Component> components = new LinkedList<Component>();
    List<Interface> interfaces = new LinkedList<Interface>();
    List<CommunicationLink> links = new LinkedList<CommunicationLink>();

    //Add root element and subcomponent on the diagram
    Component component = (Component) root;
    components.addAll(CsServices.getService().getSubUsedComponents(component));
    components.addFirst(component);

    for (Component current : components) {
      if (!content.containsView(current, componentMapping)) {
        DNodeContainer created = DiagramServices.getDiagramServices().createContainer(componentMapping, current, content.getBestContainer(current), diagram_p);
        content.addView(created);
      }
      interfaces.addAll(CsServices.getService().getRelatedInterfaces(current));
      links.addAll(CsServices.getService().getRelatedCommunicationLinks(current));
    }

    //add related interfaces on containing component
    NodeMapping interfaceMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, IMappingNameConstants.CCII_INTERFACE);
    for (final Interface itf : interfaces) {
      if (!content.containsView(itf, interfaceMapping)) {
        DNode node = DiagramServices.getDiagramServices().createNode(interfaceMapping, itf, content.getBestContainer(itf), diagram_p);
        content.addView(node);
      }
    }

    //Add related exchange items on the diagram
    NodeMapping exchangeItemMapping = DiagramServices.getDiagramServices().getNodeMapping(diagram_p, IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME);
    for (final CommunicationLink link : links) {
      AbstractExchangeItem item = link.getExchangeItem();
      if ((item != null) && !content.containsView(item, exchangeItemMapping)) {
        DNode node = DiagramServices.getDiagramServices().createNode(exchangeItemMapping, item, content.getBestContainer(item), diagram_p);
        content.addView(node);
      }
    }

    reorderElements(content);
  }

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram_p) {
    List<AbstractNodeMapping> returnedList = new ArrayList<AbstractNodeMapping>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CCII_COMPONENT));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram_p, IMappingNameConstants.CCII_INTERFACE));
    returnedList.add(DiagramServices.getDiagramServices().getNodeMapping(diagram_p, IMappingNameConstants.CCII_EXCHANGE_ITEM_MAPPING_NAME));
    return returnedList;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  public void postRefresh(DDiagram diagram_p) {
    // Nothing to do
  }

}
