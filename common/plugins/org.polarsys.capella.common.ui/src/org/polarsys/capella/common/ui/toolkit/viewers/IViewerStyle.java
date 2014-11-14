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
package org.polarsys.capella.common.ui.toolkit.viewers;

/**
 */
public interface IViewerStyle {

  /**
   * Style constant for displaying the status bar in the viewer.
   */
  public static final int SHOW_STATUS_BAR = 1 << 1;
  /**
   * Style constant for displaying the TreeView mode button in the viewer.
   */
  public static final int SHOW_TREE_VIEW_MODE_BUTTON = 1 << 2;
  /**
   * Style constant for displaying the TreeView mode button in the viewer.
   */
  public static final int SHOW_LIST_VIEW_MODE = 1 << 3;

}
