/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
