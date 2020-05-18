/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class ModelMigrationHandler extends AbstractMigrationHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    try {
      IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
      for (Object selected : selection.toList()) {
        if (selected instanceof IResource) {
          MigrationHelpers.getInstance().trigger((IResource) selected, HandlerUtil.getActiveShell(event), false, true,
              new String[] { MigrationConstants.MIGRATION_KIND__SEMANTIC,
                  MigrationConstants.MIGRATION_KIND__VIEWPOINT });
        }
      }
    } finally {
      MigrationHelpers.getInstance().dispose();
    }
    return event;
  }

  @Override
  protected boolean isValidSelection(List<Object> selection) {
    for (Object select : selection) {
      if (!((select instanceof IFile) && CapellaResourceHelper.isCapellaResource(((IFile) select)))) {
        return false;
      }
    }
    return true;
  }
}
