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

import org.eclipse.sirius.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.viewpoint.DDiagram;

/**
 */
public class BreakdownRefreshExtensionProvider implements IRefreshExtensionProvider {

  private static final IRefreshExtension REFRESH_EXTENSION = new BreakdownRefreshExtension();

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#getRefreshExtension(org.eclipse.sirius.DDiagram)
   */
  public IRefreshExtension getRefreshExtension(DDiagram viewPoint_p) {
    return REFRESH_EXTENSION;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#provides(org.eclipse.sirius.DDiagram)
   */
  public boolean provides(DDiagram viewPoint_p) {
    return viewPoint_p != null && viewPoint_p.getDescription() != null && viewPoint_p.getDescription().getName().endsWith("Breakdown"); //$NON-NLS-1$
  }
}
