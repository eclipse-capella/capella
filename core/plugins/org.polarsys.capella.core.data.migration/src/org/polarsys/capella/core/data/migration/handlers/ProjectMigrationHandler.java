/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.ui.tools.internal.actions.repair.RepresentationFilesNeedCloseSessionValidator;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.data.migration.Messages;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;

/**
 * 
 */
public class ProjectMigrationHandler extends AbstractMigrationHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    boolean skipConfirmation = false;
    if (Boolean.TRUE.equals(Boolean.valueOf(event.getParameter("skipConfirmation")))) {
      skipConfirmation = true;
    }
    for (Object selected : getSelection((IEvaluationContext) event.getApplicationContext(), IResource.class)) {
      if (selected instanceof IResource && selected instanceof IProject) {
			@SuppressWarnings("restriction")
			IStatus validationStatus = new RepresentationFilesNeedCloseSessionValidator(Messages.MigrationAction_Title).validate(null);
			if (validationStatus.isOK()) {
				MigrationHelpers.getInstance().trigger((IResource) selected, HandlerUtil.getActiveShell(event),
						skipConfirmation, true, MigrationConstants.DEFAULT_KIND_ORDER);
			}
      }
    }
    return event;
  }

  @Override
  protected boolean isValidSelection(List<Object> selection) {
    for (Object select : selection) {
      if (!(select instanceof IProject)) {
        return false;
      }
    }
    return true;
  }

}
