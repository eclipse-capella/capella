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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.StructuredViewer;

import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;

/**
 */
public class CapellaCopyAction extends CopyAction {

  private StructuredViewer _viewer;

  /**
   * @param editingDomain_p
   */
  public CapellaCopyAction(TransactionalEditingDomain editingDomain_p, StructuredViewer viewer_p) {
    super(editingDomain_p);
    _viewer = viewer_p;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CopyAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<?> selection_p) {
    filterSelectedElements(selection_p);
    return new CapellaCopyToClipboardCommand(domain, selection_p, _viewer);
  }

  /**
   * Filter out elements that are already included in selection because their container is also included.
   * @param selection_p
   */
  static void filterSelectedElements(Collection<?> selection_p) {
    // Filter out on specified selection all elements that are de facto included because their container is also included.
    ArrayList<Object> temporarySelection = new ArrayList<Object>(selection_p);
    Iterator<?> iterator = selection_p.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      if (object instanceof EObject) {
        if (temporarySelection.contains(((EObject) object).eContainer())) {
          iterator.remove();
        }
      }
    }
  }
}
