/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.apache.log4j.Logger;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.sirius.analysis.TitleBlockServices;

/**
 * 
 */
public class TitleBlockRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {
    // -------------------------------------
    // Show in diagram related title block elements
    // -------------------------------------
    try {
      TitleBlockServices.getService().refreshTitleBlocksInDiagram(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }
    super.postRefresh(diagram);
  }
}
