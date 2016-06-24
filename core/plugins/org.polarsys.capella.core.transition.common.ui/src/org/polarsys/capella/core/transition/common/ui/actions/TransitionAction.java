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
package org.polarsys.capella.core.transition.common.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;

public abstract class TransitionAction extends AbstractHandler implements IActionDelegate {

  public static IAction DEFAULT_ACTION = null;

  /**
   * Current _selection.
   */
  private Collection<Object> _selection;

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

  public Object execute(ExecutionEvent event) throws ExecutionException {
    EvaluationContext c = (EvaluationContext) event.getApplicationContext();
    ISelection s = (ISelection) c.getVariable("selection");
    selectionChanged(null, s);
    run(DEFAULT_ACTION);
    return event;
  }

  protected abstract ICommand createCommand(Collection<Object> selection, IProgressMonitor progressMonitor);

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(final IAction action) {

    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());

    try {
      IRunnableWithProgress runnable = new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @SuppressWarnings("synthetic-access")
        public void run(IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
          String text = "Transition";//$NON-NLS-1$
          if (action != null) {
            text = action.getText();
          }
          progressMonitor.beginTask(text + " processing...", 1); //$NON-NLS-1$
          ICommand command = createCommand(_selection, progressMonitor);
          if (command != null) {
            ExecutionManager executionManager = TransactionHelper.getExecutionManager((Collection<? extends EObject>) getSemanticObjects(_selection));
            if (executionManager != null) {
              executionManager.execute(command);
            }
          }
          progressMonitor.worked(1);
        }
      };

      try {
        // Pb Sirius. Temporary Workaround until fix. Use "false" for the 1st parameter to run the command from the GUI thread.
        new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }

    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }

  }

  // FIXME refactor duplicated code
  public Collection<Object> getSemanticObjects(Collection<Object> elements) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public Object resolveSemanticObject(Object object) {
    Object semantic = null;

    if (object != null) {
      if (object instanceof EObject) {
        semantic = object;

      } else if (object instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object).getAdapter(EObject.class);
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
    }
    return semantic;
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
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

  /**
   * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    _activeShell = targetPart.getSite().getShell();
  }
}
