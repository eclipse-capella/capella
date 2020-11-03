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
import org.polarsys.capella.core.projection.exchanges.commands.MDGenerateConnectionsCommand;

/**
 * This action launches the automatic component exchanges generation.
 */
public class MDGenerateConnectionsAction extends AbstractTigAction implements IObjectActionDelegate {
	

final String PROGRESS_BAR_NAME = Messages.MDGenerateConnectionsAction_CreatingExchange;
  /**
   * This variable stores the diagram where the "generate component exchanges" has been launched
   */
  protected DDiagram diagram;
  
  /**
   * Constructor
   */
  public MDGenerateConnectionsAction() {
		super();
	}
  
  /**
   * Constructor
   * @param diagram
   */
  public MDGenerateConnectionsAction(DDiagram diagram) {
	super();
	this.diagram = diagram;
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @SuppressWarnings({ "synthetic-access", "unchecked", "rawtypes" })
      public void run(IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
        progressMonitor.beginTask(PROGRESS_BAR_NAME, IProgressMonitor.UNKNOWN);
        final IProgressMonitor pm = progressMonitor;
        getExecutionManager().execute(new MDGenerateConnectionsCommand((Collection) getSelectedElements()));
        getExecutionManager().execute(new AbstractReadWriteCommand() {
          public void run() {

            if (null != diagram) {
              // Refreshes the diagram:
              DialectManager.INSTANCE.refresh(diagram, pm);
            }
          }
        });
      }
    };
    try {
      new ProgressMonitorDialog(getActiveShell()).run(false, false, runnable);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  /**
   * Overridden in order to get a reference on the diagram on which the action has been launched
   * @see org.polarsys.capella.core.platform.sirius.ui.actions.AbstractTigAction#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction arg0, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) selection).getFirstElement();
      if (firstElement instanceof IGraphicalEditPart) {
        IGraphicalEditPart gep = (IGraphicalEditPart) firstElement;
        Object model = gep.getModel();
        if (model instanceof View) {
          View node = (View) model;
          EObject element = node.getElement();
          if (element instanceof DDiagramElement) {
            DDiagramElement dnode = (DDiagramElement) element;
            diagram = dnode.getParentDiagram();
          }
        }
      }
    }
    // calls the super implementation
    super.selectionChanged(arg0, selection);
  }
}
