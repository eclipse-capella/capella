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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

/**
 */
public class EditCapellaCustomPropertyWizard extends Wizard {
  /**
   * Model element that the wizard is open for.
   */
  private EObject object;
  /**
   * Metaclass UI label for handled model element.
   */
  private String metaclassLabel;
  /**
   * Wizard page that displays the sections for handled model element.
   */
  private EditCapellaCustomPropertyWizardPage page;

  private IWorkbenchPart part;
  
  public EditCapellaCustomPropertyWizard(IWorkbenchPart part, EObject object) {
    this.object = object;
    this.metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(object, false);
    setWindowTitle("Properties");
    this.part = part;
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    page = new EditCapellaCustomPropertyWizardPage(part, "editCapellaCustomWizardPage", object, metaclassLabel); //$NON-NLS-1$
    addPage(page);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    // Propagate the Finish action to page
    page.performFinish();
    return true;
  }

  /**
   * Get the element the wizard is open for.
   * @return a not <code>null</code> element.
   */
  public EObject getEObject() {
    return object;
  }
}
