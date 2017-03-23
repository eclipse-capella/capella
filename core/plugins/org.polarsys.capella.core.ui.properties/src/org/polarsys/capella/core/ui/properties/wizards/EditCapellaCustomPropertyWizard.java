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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;
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

  public EditCapellaCustomPropertyWizard(EObject object) {
    this.object = object;
    this.metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(object, false);
    setWindowTitle(metaclassLabel);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    page = new EditCapellaCustomPropertyWizardPage("editCapellaCustomWizardPage", object, metaclassLabel); //$NON-NLS-1$
    addPage(page);
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    // Do nothing special.
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
