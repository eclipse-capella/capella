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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Extended refresh to display the content of the focused module.
 * 
 */
public class CapabilityRealizationBlankRefreshExtension extends RefreshExtension {

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    return Arrays.asList(
        DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.CRB_COMPONENT_MAPPING));

  }

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
    EObject root = ((DSemanticDecorator) diagram).getTarget();
    if (!(root instanceof CapabilityRealizationPkg)) {
      return;
    }

    reorderElements(diagram);
  }
}
