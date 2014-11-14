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

  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    EvaluationContext c = (EvaluationContext) event_p.getApplicationContext();
    ISelection s = (ISelection) c.getVariable("selection");
    selectionChanged(null, s);
    run(DEFAULT_ACTION);
    return event_p;
  }

  protected abstract ICommand createCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p);

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
        public void run(IProgressMonitor progressMonitor_p) throws InvocationTargetException, InterruptedException {
          String text = "Transition";//$NON-NLS-1$
          if (action != null) {
            text = action.getText();
          }
          progressMonitor_p.beginTask(text + " processing...", 1); //$NON-NLS-1$
          ICommand command = createCommand(_selection, progressMonitor_p);
          if (command != null) {
            TransactionHelper.getExecutionManager((Collection<? extends EObject>) getSemanticObjects(_selection)).execute(command);
          }
          progressMonitor_p.worked(1);
        }
      };

      try {
        // Pb Sirius. Temporary Workaround until fix. Use "false" for the 1st parameter to run the command from the GUI thread.
        new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
      } catch (Exception exception_p) {
        throw new RuntimeException(exception_p);
      }

    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }

  }

  // FIXME refactor duplicated code
  public Collection<Object> getSemanticObjects(Collection<Object> elements_p) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements_p) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public Object resolveSemanticObject(Object object_p) {
    Object semantic = null;

    if (object_p != null) {
      if (object_p instanceof EObject) {
        semantic = object_p;

      } else if (object_p instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object_p).getAdapter(EObject.class);
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
  public void selectionChanged(IAction uiAction_p, ISelection selection_p) {
    Collection<Object> objects = new ArrayList<Object>();
    if (selection_p instanceof IStructuredSelection) {
      IStructuredSelection selection = (IStructuredSelection) selection_p;
      Iterator<Object> selections = selection.iterator();
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
