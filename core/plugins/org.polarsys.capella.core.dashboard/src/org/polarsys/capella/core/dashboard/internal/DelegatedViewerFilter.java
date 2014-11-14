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
package org.polarsys.capella.core.dashboard.internal;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Viewer filter driven by provided algorithm.
 */
public class DelegatedViewerFilter extends ViewerFilter {
  /**
   * Delegated filter
   */
  private ViewerFilter _delegatedFilter;

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    boolean selected = true;
    if (null != _delegatedFilter) {
      selected = _delegatedFilter.select(viewer_p, parentElement_p, element_p);
    }
    return selected;
  }

  /**
   * @param delegatedFilter_p
   */
  public void setDelegatedFilter(ViewerFilter delegatedFilter_p) {
    _delegatedFilter = delegatedFilter_p;
  }
}
