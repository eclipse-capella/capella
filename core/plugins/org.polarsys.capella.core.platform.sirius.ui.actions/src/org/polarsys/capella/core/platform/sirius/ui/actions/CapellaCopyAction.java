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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.viewers.StructuredViewer;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;

/**
 */
public class CapellaCopyAction extends AbstractCommandActionHandler {

  private StructuredViewer _viewer;

  /**
   * @param editingDomain
   */
  public CapellaCopyAction(StructuredViewer viewer) {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Copy_menu_item"));
    _viewer = viewer;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CopyAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection) {
    filterSelectedElements(selection);
    if (TransactionHelper.getEditingDomain(filterSelection(selection)) != null)
      return new CapellaCopyToClipboardCommand(TransactionHelper.getEditingDomain(filterSelection(selection)), selection, _viewer);
    return null;
  }

  /**
   * Filter out elements that are already included in selection because their container is also included.
   * 
   * @param selection
   */
  static void filterSelectedElements(Collection<?> selection) {
    // Filter out on specified selection all elements that are de facto included because their container is also
    // included.
    ArrayList<Object> temporarySelection = new ArrayList<Object>(selection);
    Iterator<?> iterator = selection.iterator();
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
