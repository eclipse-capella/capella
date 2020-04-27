/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.viewers.data;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 *
 */
public class DataViewerLabelProvider extends ValidLabelProvider {

  private TreeViewer _viewer;

  /**
   * @param labelProvider
   */
  public DataViewerLabelProvider(ILabelProvider labelProvider) {
    super(labelProvider);
  }

  /**
   * @param element
   * @return
   */
  @Override
  protected boolean isValid(Object element) {
    if ((_viewer != null) && (_viewer.getInput() instanceof AbstractData)) {
      AbstractData input = (AbstractData) _viewer.getInput();
      if (!input.isValid(element)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Set the viewer that uses this label provider to render text and images.
   * @param viewer
   */
  public void setViewer(TreeViewer viewer) {
    _viewer = viewer;
  }
}
