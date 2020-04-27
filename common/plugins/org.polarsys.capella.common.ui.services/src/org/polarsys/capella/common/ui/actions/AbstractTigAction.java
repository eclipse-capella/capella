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

package org.polarsys.capella.common.ui.actions;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 */
@Deprecated
public abstract class AbstractTigAction extends AbstractHandler implements IObjectActionDelegate {
  /**
   * Current _selection.
   */
  private ISelection _selection;
  /**
   * Active shell.
   */
  private Shell _activeShell;

  /**
   * Get the active shell.
   * @return the activeShell
   */
  protected Shell getActiveShell() {
    return _activeShell;
  }

  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IEvaluationContext c = (IEvaluationContext) event.getApplicationContext();
    ISelection s = (ISelection) c.getVariable("selection");
    selectionChanged(null, s);
    setActivePart(null, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());
    run(null);
    return event;
  }

  /**
   * Get the Capella Execution Manager.
   * @return
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(getSelectedElement());
  }

  /**
   * Get model element form current selection.
   * @return could be <code>null</code>
   */
  protected ModelElement getSelectedElement() {
    return ModelAdaptation.adaptToCapella(_selection);
  }

  /**
   * Get model elements form current selection.
   * @return could be <code>null</code>
   */
  protected List<ModelElement> getSelectedElements() {
    return ModelAdaptation.adaptToCapellaElements(_selection);
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction uiAction, ISelection selection) {
    _selection = selection;
  }

  /**
   * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    _activeShell = targetPart.getSite().getShell();
  }
}
