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
package org.polarsys.capella.core.transition.common.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.core.transition.common.ui.commands.CommandUIHandler;

/**
 * Deprecated, should use CommandUIHandler instead
 */
@Deprecated
public abstract class TransitionAction extends CommandUIHandler implements IActionDelegate {

  private Collection<Object> _selection;

  public void run(final IAction action) {
    try {
      execute(_selection);
    } catch (ExecutionException e) {
      // Nothing here
    }
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction uiAction, ISelection selection) {
    Collection<Object> objects = new ArrayList<Object>();
    if (selection instanceof IStructuredSelection) {
      Iterator<Object> selections = ((IStructuredSelection) selection).iterator();
      while (selections.hasNext()) {
        objects.add(selections.next());
      }
    }
    _selection = objects;
  }

}
