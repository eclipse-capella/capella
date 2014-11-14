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
package org.polarsys.capella.common.ui.actions;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tig.efprovider.TigEfProvider;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

/**
 */
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
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    EvaluationContext c = (EvaluationContext) event_p.getApplicationContext();
    ISelection s = (ISelection) c.getVariable("selection");
    selectionChanged(null, s);
    setActivePart(null, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());
    run(null);
    return event_p;
  }

  /**
   * Get the Capella Execution Manager.
   * @return
   */
  protected ExecutionManager getExecutionManager() {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TigEfProvider.getExecutionManagerName());
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
  public void selectionChanged(IAction uiAction_p, ISelection selection_p) {
    _selection = selection_p;
  }

  /**
   * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    _activeShell = targetPart.getSite().getShell();
  }
}
