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
package org.polarsys.capella.core.transition.system.ui.preferences;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import org.polarsys.capella.core.transition.system.ui.dialog.ProjectSelectionDialog;
import org.polarsys.capella.common.flexibility.wizards.renderer.BrowseRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class OutputModelRenderer extends BrowseRenderer {

  @Override
  protected boolean isColoredOnValidation() {
    return false;
  }

  @Override
  protected boolean isDeleteButton() {
    return false;
  }

  @Override
  protected boolean isDescription() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void proceedBrowse(Shell shell_p, IRendererContext context_p) {
    String targetCapellaProject = getTargetProject(shell_p);
    if (targetCapellaProject != null) {
      changeValue(context_p.getProperty(this), context_p, targetCapellaProject);
      updatedValue(context_p.getProperty(this), context_p, targetCapellaProject);
    }
  }

  protected String getTargetProject(Shell shell_p) {
    ProjectSelectionDialog dialog = new ProjectSelectionDialog(shell_p, new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
    dialog.open();
    Object obj = dialog.getFirstResult();
    if ((obj != null) && (obj instanceof String)) {
      return (String) obj;
    }
    return null;
  }

}
