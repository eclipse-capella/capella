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
package org.polarsys.capella.core.ui.toolkit.viewers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.fields.CStringFieldEditor;
import org.polarsys.capella.common.ui.toolkit.viewers.GeneralViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.Messages;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 */
public class CapellaElementGeneralViewer extends GeneralViewer {
  // The summary field.
  private CStringFieldEditor _summaryField;
  // The description field.
  private CStringFieldEditor _descriptionField;

  /**
   * The default identifier for the field representing the summary.
   */
  public static final String DEFAULT_SUMMARY_ID = "general.summary";//$NON-NLS-1$
  /**
   * The default identifier for the field representing the description.
   */
  public static final String DEFAULT_DESCRIPTION_ID = "general.description";//$NON-NLS-1$

  /**
   * @param parent_p
   */
  public CapellaElementGeneralViewer(Composite parent_p) {
    super(parent_p);
    setDescriptionId(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
    setSummaryId(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.GeneralViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(Composite parent_p) {
    
    // Summary.
    _summaryField = new CStringFieldEditor(CapellaElementGeneralViewer.DEFAULT_SUMMARY_ID, Messages.getString("GeneralViewer.label.summary"), parent_p); //$NON-NLS-1$ 
    // Description.
    _descriptionField = new CStringFieldEditor(CapellaElementGeneralViewer.DEFAULT_DESCRIPTION_ID, Messages.getString("GeneralViewer.label.description"), parent_p, SWT.BORDER | SWT.MULTI | SWT.WRAP); //$NON-NLS-1$ 

    registerField(_summaryField);
    registerField(_descriptionField);

    super.createControl(parent_p);
  }

  /**
   * Sets the summary field identifier.
   * @param identifier_p The identifier.
   */
  public void setSummaryId(String identifier_p) {
    _summaryField.setPreferenceName(identifier_p);
  }

  /**
   * Sets the description field identifier.
   * @param identifier_p The identifier.
   */
  public void setDescriptionId(String identifier_p) {
    _descriptionField.setPreferenceName(identifier_p);
  }

  /**
   * Gets the summary field.
   * @return The summary field.
   */
  public CStringFieldEditor getSummaryField() {
    return _summaryField;
  }
  
  /**
   * Gets the description field.
   * @return the description field.
   */
  public CStringFieldEditor getDescriptionField() {
    return _descriptionField;
  }
}
