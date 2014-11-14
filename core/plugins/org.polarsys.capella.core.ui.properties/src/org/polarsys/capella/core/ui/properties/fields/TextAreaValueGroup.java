/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   */
  public TextAreaValueGroup(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, label_p, widgetFactory_p, false);
  }

  /**
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param skipGroup_p
   */
  public TextAreaValueGroup(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean skipGroup_p) {
    super(parent_p, label_p, widgetFactory_p, skipGroup_p);
  }

  /**
   * {@inheritDoc}
   * 
   * @param label_p
   * @param hasResetBtn_p
   */
  @Override
  protected void createValueTextField(String label_p, boolean hasResetBtn_p) {
    CLabel label = _widgetFactory.createCLabel(_parent, label_p);
    GridData gd = new GridData();
    gd.horizontalSpan = ((GridLayout) _parent.getLayout()).numColumns; //2;
    label.setLayoutData(gd);
    _valueField = _widgetFactory.createText(_parent, "", SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) _parent.getLayout()).numColumns; //2;
    gd.heightHint = 80;
    _valueField.setLayoutData(gd);
    addListeners();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addListeners() {
    _valueField.addFocusListener(this);
  }
}
