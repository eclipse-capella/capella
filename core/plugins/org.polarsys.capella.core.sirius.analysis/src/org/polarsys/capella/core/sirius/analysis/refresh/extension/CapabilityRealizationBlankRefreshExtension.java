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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 * 
 */
public class CapabilityRealizationBlankRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram_p) {
    List<AbstractNodeMapping> returnedList = new ArrayList<AbstractNodeMapping>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.CRB_COMPONENT_MAPPING));
    return returnedList;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram_p) {
    if (((DSemanticDecorator) diagram_p).getTarget() == null) {
      // avoid refresh on dirty diagram
      return;
    }
    EObject root = ((DSemanticDecorator) diagram_p).getTarget();
    if (!(root instanceof CapabilityRealizationPkg)) {
      return;
    }

    reorderElements(diagram_p);
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
