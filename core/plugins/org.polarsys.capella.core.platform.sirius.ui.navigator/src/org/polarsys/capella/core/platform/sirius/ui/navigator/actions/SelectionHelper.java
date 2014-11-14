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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 */
public class SelectionHelper {
  /**
   * Register given action to the selection changes emitted by given selection provider.
   * @param action_p
   * @param selectionProvider_p
   */
  public static void registerToSelectionChanges(ISelectionChangedListener action_p, ISelectionProvider selectionProvider_p) {
    // Listen selection changes.
    selectionProvider_p.addSelectionChangedListener(action_p);
    // Set the current selection otherwise at first run, selection is lost.
    ISelection selection = selectionProvider_p.getSelection();
    if (!selection.isEmpty()) {
      action_p.selectionChanged(new SelectionChangedEvent(selectionProvider_p, selection));
    }
  }
}
