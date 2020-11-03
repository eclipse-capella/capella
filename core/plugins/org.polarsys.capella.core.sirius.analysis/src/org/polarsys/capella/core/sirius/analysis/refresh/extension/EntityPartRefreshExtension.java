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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class EntityPartRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * <b>Before Refresh</b>
   * <p>
   * This method is executed before the refreshing action of the diagram
   * 
   * @param diagram
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
    CsServices.getService().refreshEntitiesArchitecture(getComponentMapping(diagram), diagram);
  }

  public ContainerMapping getComponentMapping(DDiagram diagram) {
    if (diagram.getDescription().getName()
        .equals(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram,
          IMappingNameConstants.COC_OPERATIONAL_ENTITY_MAPPING_NAME);
    }
    return null;
  }
}
