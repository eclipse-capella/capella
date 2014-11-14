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
package org.polarsys.capella.common.ui.toolkit.viewers.data;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 *
 */
public class DataViewerLabelProvider extends ValidLabelProvider {

  private TreeViewer _viewer;

  /**
   * @param adapterFactory_p
   */
  public DataViewerLabelProvider(ILabelProvider labelProvider_p) {
    super(labelProvider_p);
  }

  /**
   * @param element_p
   * @return
   */
  @Override
  protected boolean isValid(Object element_p) {
    if ((_viewer != null) && (_viewer.getInput() instanceof AbstractData)) {
      AbstractData input = (AbstractData) _viewer.getInput();
      if (!input.isValid(element_p)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Set the viewer that uses this label provider to render text and images.
   * @param viewer_p
   */
  public void setViewer(TreeViewer viewer_p) {
    _viewer = viewer_p;
  }
}
