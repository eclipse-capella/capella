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
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 */
public class TextAreaValueGroup extends TextValueGroup {
  /**
   * @param parent
   * @param label
   * @param widgetFactory
   */
  public TextAreaValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, label, widgetFactory, false);
  }

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param skipGroup
   */
  public TextAreaValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, boolean skipGroup) {
    super(parent, label, widgetFactory, skipGroup);
  }

  /**
   * {@inheritDoc}
   * 
   * @param label
   * @param hasResetBtn
   */
  @Override
  protected void createValueTextField(String label, boolean hasResetBtn) {
    CLabel lbl = widgetFactory.createCLabel(parent, label);
    GridData gd = new GridData();
    gd.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns; //2;
    lbl.setLayoutData(gd);
    valueField = widgetFactory.createText(parent, "", SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns; //2;
    gd.heightHint = 80;
    valueField.setLayoutData(gd);
    addListeners();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addListeners() {
    valueField.addFocusListener(this);
  }
}
