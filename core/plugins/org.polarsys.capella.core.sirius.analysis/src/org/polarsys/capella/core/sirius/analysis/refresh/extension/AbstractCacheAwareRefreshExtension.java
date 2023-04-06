/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.cache.ModelCache;

public abstract class AbstractCacheAwareRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * Subclasses should call super.beforeRefresh at the beginning of the overridden beforeRefresh.
   */
  @Override
  public void beforeRefresh(DDiagram dDiagram) {
    ModelCache.enable();
  }

  /**
   * Subclasses should call super.postRefresh at the end of the overridden postRefresh.
   */
  @Override
  public void postRefresh(DDiagram dDiagram) {
    ModelCache.disable();
  }
}
