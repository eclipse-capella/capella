/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.polarsys.capella.core.data.helpers.cache.ModelCache;

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
