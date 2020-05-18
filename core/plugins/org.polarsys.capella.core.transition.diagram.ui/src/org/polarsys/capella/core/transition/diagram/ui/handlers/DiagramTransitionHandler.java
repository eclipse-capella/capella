/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.ui.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.transition.diagram.commands.DiagramTransitionRunnable;

/**
 *
 */
public class DiagramTransitionHandler extends AbstractRunnableHandler {

  @Override
  protected IRunnableWithProgress createRunnable(ExecutionEvent event_p) {
    List<DDiagram> diagrams = getSelection(event_p, DDiagram.class);
    final IEvaluationContext evaluationContext = (IEvaluationContext) event_p.getApplicationContext();

    return new DiagramTransitionRunnable(diagrams) {

      @Override
      protected void initialize(IProgressMonitor monitor_p) {
        Object variable = evaluationContext.getVariable(DiagramTransitionRunnable.TARGET_DIAGRAM);
        if (variable != null) {
          getContext().put(DiagramTransitionRunnable.TARGET_DIAGRAM, variable);
        }
      }

      @Override
      protected void checkStatus(DRepresentation source_p, DRepresentation target_p, IStatus status_p) {
        if (status_p.getException() != null) {
          status_p.getException().printStackTrace();
        }
        if (status_p.getSeverity() == IStatus.ERROR) {
          MessageDialog.openError(getShell(), getName(), status_p.getMessage());

        } else if (status_p.getSeverity() == IStatus.WARNING) {
          MessageDialog.openWarning(getShell(), getName(), status_p.getMessage());
        }

        super.checkStatus(source_p, target_p, status_p);
      }
    };
  }
}
