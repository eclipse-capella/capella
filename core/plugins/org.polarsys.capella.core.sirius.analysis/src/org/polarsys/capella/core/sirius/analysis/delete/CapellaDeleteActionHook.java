/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.delete;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * Delete hook allowing for impact analysis before delete execution.
 */
public class CapellaDeleteActionHook implements IDeleteHook {

  // Sirius triggers the hook for all selected elements but triggers the externalDeleteAction for each item of selection,
  // we need to store this selection to trigger only one time the externalDeleteAction with full initial selection.

  private static Collection<EObject> _selection;

  public static Collection<EObject> getSelection() {
    return _selection;
  }

  protected static void setSelection(Collection<EObject> semanticElements_p) {
    _selection = semanticElements_p;
  }

  public static void removeSelection() {
    setSelection(null);
  }

  /**
   * @see org.eclipse.sirius.business.api.delete.IDeleteHook#beforeDeleteCommandExecution(java.util.Collection, java.util.Map)
   */
  public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections_p, Map<String, Object> parameters_p) {
    // Precondition.
    if ((null == selections_p) || selections_p.isEmpty()) {
      return Status.CANCEL_STATUS;
    }

    // Get semantic elements from selected ones.
    Collection<EObject> semanticElements = new HashSet<EObject>(selections_p.size());
    for (DSemanticDecorator semanticDecorator : selections_p) {
      EObject target = semanticDecorator.getTarget();
      if (null != target) {
        semanticElements.add(target);
      }
    }

    setSelection(semanticElements);
    // Ask user confirmation if it is required in preferences.
    boolean deletionConfirmed = !IDeletePreferences.INSTANCE.isConfirmationRequired() || CapellaDeleteCommand.confirmDeletion(TransactionHelper.getExecutionManager(semanticElements), getSelection());
    if (!deletionConfirmed) {
      removeSelection();
    }
    return deletionConfirmed ? Status.OK_STATUS : Status.CANCEL_STATUS;
  }

}
