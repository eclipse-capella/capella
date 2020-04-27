/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.projection.exchanges.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.ui.IObjectActionDelegate;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.Messages;
import org.polarsys.capella.core.projection.exchanges.commands.MDGenerateLinksCommand;

/**
 * This action launches the automatic physical links generation.
 */
public class MDGenerateLinksAction extends AbstractTigAction implements IObjectActionDelegate {
  final String PROGRESS_BAR_NAME = Messages.MDGenerateConnectionsAction_CreatingPhysicalLinks;
  /**
   * This variable stores the diagram where the "generate component exchanges" has been launched
   */
  protected DDiagram _diagram;

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings({ "synthetic-access", "unchecked", "rawtypes" })
      public void run(IProgressMonitor progressMonitor_p) throws InvocationTargetException, InterruptedException {
        progressMonitor_p.beginTask(PROGRESS_BAR_NAME, IProgressMonitor.UNKNOWN);
        final IProgressMonitor pm = progressMonitor_p;
        getExecutionManager().execute(new MDGenerateLinksCommand((Collection) getSelectedElements()));
        getExecutionManager().execute(new AbstractReadWriteCommand() {
          public void run() {

            if (null != _diagram) {
              // Refreshes the diagram:
              DialectManager.INSTANCE.refresh(_diagram, pm);
            }
          }
        });
      }
    };
    try {
      new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
    } catch (Exception exception_p) {
      throw new RuntimeException(exception_p);
    }
  }

  /**
   * Overridden in order to get a reference on the diagram on which the action has been launched
   * @see org.polarsys.capella.core.platform.sirius.ui.actions.AbstractTigAction#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction arg0_p, ISelection selection_p) {
    if (selection_p instanceof StructuredSelection) {
      StructuredSelection selection = (StructuredSelection) selection_p;
      Object firstElement = selection.getFirstElement();
      if (firstElement instanceof IGraphicalEditPart) {
        IGraphicalEditPart gep = (IGraphicalEditPart) firstElement;
        Object model = gep.getModel();
        if (model instanceof View) {
          View node = (View) model;
          EObject element = node.getElement();
          if (element instanceof DDiagramElement) {
            DDiagramElement dnode = (DDiagramElement) element;
            _diagram = dnode.getParentDiagram();
          }
        }
      }
    }
    // calls the super implementation
    super.selectionChanged(arg0_p, selection_p);
  }
}
