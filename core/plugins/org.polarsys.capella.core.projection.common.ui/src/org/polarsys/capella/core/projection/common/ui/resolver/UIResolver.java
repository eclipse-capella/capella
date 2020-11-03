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
package org.polarsys.capella.core.projection.common.ui.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;

public class UIResolver implements IResolver, Runnable {

  SelectElementsDialog selectionDialog;
  int res = Window.OK;
  boolean remindSelection = false;

  /**
   * @throws TransfoException 
   * @see org.polarsys.capella.core.projection.scenario.resolver.IResolver#resolve(org.eclipse.emf.ecore.EObject, java.util.List, boolean)
   */
  @SuppressWarnings("unchecked")
  public List<EObject> resolve(final EObject source_p, final List<EObject> scope_p, final String title_p, final String message_p,
      final boolean multipleSelection_p, ITransfo transfo_p, EObject[] context_p) {
    //Avoid non ambiguous case
    if (scope_p.size() == 1) {
      return scope_p;
    }

    try {
      //Create a dialog
      Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
      selectionDialog = new SelectElementsDialog(shell,
        title_p, message_p,
        new ArrayList<EObject>(scope_p))
      {
        @Override
        protected void okPressed() {
          List<? extends EObject> result = handleResult();
          //Avoid initial OK press with no selection
          if (!(result == null || result.size() == 0)) {
            super.okPressed();
          }
        }
      };

      //Ask to the user
      PlatformUI.getWorkbench().getDisplay().syncExec(this);

      //Retrieve result
      if (Window.OK == res) {
        return (List<EObject>) selectionDialog.getResult();
      }

      //User has canceled the selection
      throw new OperationCanceledException(ProjectionMessages.Transition_UI_Cancel);

    } finally {
      selectionDialog = null;
      res = 0;
      remindSelection = false;
    }
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    if (selectionDialog != null) {
      res = selectionDialog.open();
    }

  }

}
