/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.polarsys.capella.core.sirius.analysis.cache.ScenarioCache;

public class ScenarioRefreshExtension implements IRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    ScenarioCache.getInstance().clearCaches();
    ScenarioCache.getInstance().enableRefreshCache();
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {
    ScenarioCache.getInstance().clearCaches();
    ScenarioCache.getInstance().disableRefreshCache();
  }

}
