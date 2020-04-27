/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.fields;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A field editor to display labels not associated with other widgets.
 */
public class LabelFieldEditor extends FieldEditor {
  private Label _label;

  public LabelFieldEditor(String labelText, Composite parent) {
    super("label", labelText, parent); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#adjustForNumColumns(int)
   */
  @Override
  protected void adjustForNumColumns(int numColumns) {
    ((GridData) _label.getLayoutData()).horizontalSpan = numColumns;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#doFillIntoGrid(org.eclipse.swt.widgets.Composite, int)
   */
  @Override
  protected void doFillIntoGrid(Composite parent, int numColumns) {
    _label = getLabelControl(parent);
    GridData gridData = new GridData();
    gridData.horizontalSpan = numColumns;
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = false;
    gridData.verticalAlignment = GridData.CENTER;
    gridData.grabExcessVerticalSpace = false;

    _label.setLayoutData(gridData);
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#doLoad()
   */
  @Override
  protected void doLoad() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#doLoadDefault()
   */
  @Override
  protected void doLoadDefault() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#doStore()
   */
  @Override
  protected void doStore() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#getNumberOfControls()
   */
  @Override
  public int getNumberOfControls() {
    return 1;
  }
}
