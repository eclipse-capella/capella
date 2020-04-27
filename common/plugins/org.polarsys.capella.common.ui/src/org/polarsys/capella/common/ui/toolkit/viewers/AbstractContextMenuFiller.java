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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;

/**
 * Filler called to fill in an {@link IMenuManager}.<br>
 * Used by {@link AbstractRegExpViewer} to fill in contextual menu of its client viewer.
 */
public abstract class AbstractContextMenuFiller {
  /**
   * Constructor.
   */
  protected AbstractContextMenuFiller() {
    super();
  }

  /**
   * Fill specified menu manager
   * @param contextMenuManager
   * @param selection
   */
  public abstract void fillMenuManager(IMenuManager contextMenuManager, ISelection selection);
}
