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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.sirius.analysis.task.CreateViewTask;

/**
 */
public class CreateViewAction extends AbstractExternalJavaAction {
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    EObject context = (EObject) parameters_p.get("context"); //$NON-NLS-1$
    EObject create = (EObject) parameters_p.get(SCOPE);
    DSemanticDecorator containerView = (DSemanticDecorator) InterpreterUtil.getInterpreter(context).getVariable("containerView"); //$NON-NLS-1$
    EObject current = (EObject) parameters_p.get("containerViewExpression"); //$NON-NLS-1$
    if (current != null && current instanceof DSemanticDecorator) {
      containerView = (DSemanticDecorator) current;
    }
    String toolId = (String) InterpreterUtil.getInterpreter(context).getVariable("tool"); //$NON-NLS-1$
    CreateViewTask task = new CreateViewTask(context, containerView, create, toolId, "view");
    task.execute();
  }
}
