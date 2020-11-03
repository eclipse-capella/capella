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
package org.polarsys.capella.core.platform.sirius.ui.navigator.property;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;

/**
 * Capella page contributed in Property Dialog for Capella models.
 */
public class CapellaPage extends PropertyPage implements IWorkbenchPropertyPage {

  /**
   * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createContents(Composite parent_p) {
    // Get selected file.
    IFile file = (IFile) getElement().getAdapter(IFile.class);
    // Top level composite.
    Composite composite = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout(2, false);
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    composite.setLayout(layout);
    GridData data = new GridData(GridData.FILL_BOTH);
    composite.setLayoutData(data);
    composite.setFont(parent_p.getFont());
    // Create a label & text to display current model version.
    String detectedVersion = CapellaFeatureHelper.getDetectedVersion(file);
    boolean versionErrorDetected = false;
    if (null == detectedVersion) {
      detectedVersion = Messages.CapellaPage_UnknownVersion_Title;
      versionErrorDetected = true;
    } else if (!FeatureHelper.getCapellaVersion(false).equals(detectedVersion)) {
      // Current Capella version is different from the one read in given model.
      versionErrorDetected = true;
    }
    createLabeledText(composite, Messages.CapellaPage_VersionLabel_Title, detectedVersion);
    if (versionErrorDetected) {
      // Label.
      Label label = new Label(composite, SWT.NULL);
      label.setText(Messages.CapellaPage_HintMessage_Title);
      label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
      label.setFont(composite.getFont());
      label.setForeground(label.getDisplay().getSystemColor(SWT.COLOR_RED));
    }
    return composite;
  }

  /**
   * Create a label & a text in read-only mode i.e the text is not modifiable.
   * @param parent_p
   * @param label_p
   * @param value_p
   */
  protected void createLabeledText(Composite parent_p, String label_p, String value_p) {
    Font font = parent_p.getFont();
    // Label.
    Label label = new Label(parent_p, SWT.NULL);
    label.setText(label_p);
    label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
    label.setFont(font);
    // Text.
    Text text = new Text(parent_p, SWT.WRAP | SWT.READ_ONLY);
    text.setText(value_p);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    text.setFont(font);
    text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
  }
}
