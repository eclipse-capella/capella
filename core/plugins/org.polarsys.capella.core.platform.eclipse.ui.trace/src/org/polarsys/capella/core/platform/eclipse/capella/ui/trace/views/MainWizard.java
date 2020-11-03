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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.jface.wizard.Wizard;

/**
 * <code>MainWizard</code> is the main wizard of MDTrace. It's contained by the
 * {@link org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.MainWizardDialog}
 */
public class MainWizard extends Wizard {

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    return true;
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performCancel()
   */
  @Override
  public boolean performCancel() {
    return super.performCancel();
  }
}
