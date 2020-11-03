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

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 * 
 */
public class ContextualComponentDetailedInterfacesRefreshExtension extends AbstractCacheAwareRefreshExtension {
 
  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    
    if (((DSemanticDecorator) diagram).getTarget()==null) {
      //avoid refresh on dirty diagram
      return;
    }

    final Component component = (Component) ((DSemanticDecorator) diagram).getTarget();
    Set<EObject> targets = DiagramServices.getDiagramServices().getSetOfDiagramElementsTarget(diagram);
    
    //Add related interfaces on the diagram
    ContainerMapping interfaceMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CCDI_INTERFACE); 
    for (final Interface itf : CsServices.getService().getRelatedInterfaces(component)) {
      if (!targets.contains(itf)) {
        DiagramServices.getDiagramServices().createAbstractDNodeContainer(interfaceMapping, itf, diagram, diagram);
      }
    }
    
    //Add related exchange items on the diagram
    ContainerMapping exchangeItemMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CCDI_EXCHANGE_ITEM_MAPPING_NAME); 
    for (final CommunicationLink link : CsServices.getService().getRelatedCommunicationLinks(component)) {
      AbstractExchangeItem item = link.getExchangeItem();
      if (item!=null && !targets.contains(item)) {
        DiagramServices.getDiagramServices().createAbstractDNodeContainer(exchangeItemMapping, item, diagram, diagram);
        targets.add(item);
      }
    }
    
  }
}
