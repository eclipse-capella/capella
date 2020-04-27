/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 *
 */
public class PropertyDialog extends WizardDialog {

  ProgressMonitorPart part = null;

  /**
   * @param parentShell
   * @param newWizard
   */
  public PropertyDialog(Shell parentShell, IWizard newWizard) {
    super(parentShell, newWizard);
  }

  /**
   * Returns whether the dialog must have the progressMonitor page like default implementation of WizardDialog
   * @return false by default
   */
  protected boolean hasProgressMonitor() {
    return false;
  }

  @Override
  protected ProgressMonitorPart createProgressMonitorPart(Composite composite, GridLayout pmlayout) {
    part = super.createProgressMonitorPart(composite, pmlayout);
    return part;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Control control = super.createDialogArea(parent);
    if (!hasProgressMonitor() && (part != null)) {
      GridData gd2 = new GridData();
      gd2.grabExcessHorizontalSpace = false;
      gd2.heightHint = 0;
      part.setLayoutData(gd2);
    }
    return control;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
    if (id == IDialogConstants.FINISH_ID) {
      return super.createButton(parent, id, IDialogConstants.OK_LABEL, defaultButton);
    }
    return super.createButton(parent, id, label, defaultButton);
  }

}
