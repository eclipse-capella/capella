/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.actions.headless;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class HeadlessWizardDialog extends WizardDialog {

  /**
   * @param parentShell_p
   * @param newWizard_p
   */
  public HeadlessWizardDialog(Shell parentShell_p, IWizard newWizard_p) {
    super(parentShell_p, newWizard_p);

  }

  public void clickOnOk() {
    finishPressed();
  }

}
