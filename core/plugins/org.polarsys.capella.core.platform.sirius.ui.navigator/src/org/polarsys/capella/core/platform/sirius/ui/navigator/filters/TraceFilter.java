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
package org.polarsys.capella.core.platform.sirius.ui.navigator.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;

/**
 * Filter out {@link AbstractTrace} elements.
 */
public class TraceFilter extends ViewerFilter {
  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    if (element_p instanceof AbstractTrace && !(element_p instanceof ComponentFunctionalAllocation)
        && !(element_p instanceof ComponentExchangeAllocation)
        && !(element_p instanceof ComponentExchangeFunctionalExchangeAllocation)) {
      return false;
    }
    return true;
  }
}
