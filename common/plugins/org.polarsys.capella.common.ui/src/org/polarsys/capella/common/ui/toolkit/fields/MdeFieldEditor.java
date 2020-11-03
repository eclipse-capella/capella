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

package org.polarsys.capella.common.ui.toolkit.fields;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

/**
 * The MDE field editor. This implementation of field editor respect the IMdefieldEditor API which opens the old FieldEditor API. It supports error message
 * management, validator, input behavior and the databinding (based on the already in place binding system : IPreferenceStore).
 */
public abstract class MdeFieldEditor extends FieldEditor implements IMdeFieldEditor {
  // The label control style.
  protected int _labelStyle = SWT.LEFT;
  // The value control style.
  protected int _valueStyle = SWT.NONE;
  // The current error message.
  private String _errorMessage = null;

  /**
   * Constructs the MDE field editor.
   */
  protected MdeFieldEditor() {
    // Do nothing.
  }

  /**
   * Constructs the MDE field editor with the specified field name and specified label text. Sets the default label control style to {@link SWT#LEFT} and the
   * default value control to {@link SWT#NONE}.
   * @param name The field name.
   * @param labelText The label text.
   * @param parent The parent control.
   */
  protected MdeFieldEditor(String name, String labelText, Composite parent) {
    this(name, labelText, parent, SWT.LEFT, SWT.NONE);
  }

  /**
   * Constructs the MDE field editor with the specified name, specified text label and applies the specified styles respectively to the label control and the
   * value control. For the label control style unsupported values are filtered : {@link SWT#SEPARATOR}, {@link SWT#WRAP}.
   * @param name The field name.
   * @param labelText The label text.
   * @param parent The parent control.
   * @param labelStyle The label control style {@link Label}.
   * @param valueStyle The value control style according to the value control type.
   */
  protected MdeFieldEditor(String name, String labelText, Composite parent, int labelStyle, int valueStyle) {
    init(name, labelText, labelStyle, valueStyle);
    createControl(parent);
  }

  /**
   * Initializes the field with the given property name and label.
   * @param name The field name.
   * @param text The label text of the field.
   * @param labelStyle The label style.
   * @param valueStyle The value control style.
   */
  protected void init(String name, String text, int labelStyle, int valueStyle) {
    if (null == name) {
      throw new NullPointerException("The property name should not be null value."); //$NON-NLS-1$
    }
    if (null == text) {
      throw new NullPointerException("The label text should not be null value."); //$NON-NLS-1$
    }

    // Checks styles.
    _labelStyle = checkLabelStyle(labelStyle);
    _valueStyle = checkValueStyle(valueStyle);

    // Sets the label text and field name.
    setLabelText(text);
    setPreferenceName(name);
  }

  // Checks the label style. Does not allow the SEPARATOR style and the WRAP style.
  // In case of SWT.NONE style value, it applies a correction and return the default label style value SWT.LEFT.
  private int checkLabelStyle(int labelStyle) {
    int newStyle = labelStyle;
    if (0 != (SWT.SEPARATOR & newStyle)) {
      newStyle = newStyle - SWT.SEPARATOR;
    }

    if (0 != (SWT.WRAP & newStyle)) {
      newStyle = newStyle - SWT.WRAP;
    }

    if (SWT.NONE == newStyle) {
      newStyle = SWT.LEFT;
    }
    return newStyle;
  }

  /**
   * Checks the style of the value control and updates it. <br/> <b>WARNING : This default implementation returns the specified style.</b>
   * @param valueStyle The value control style.
   * @return The updated value control style.
   */
  protected int checkValueStyle(int valueStyle) {
    return valueStyle;
  }

  /**
   * <b>Does not remove the horizontal and the vertical margins.</b>
   * @see org.eclipse.jface.preference.FieldEditor#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(Composite parent) {
    Layout layout = parent.getLayout();
    if (!(layout instanceof GridLayout)) {
      GridLayout gdLayout = new GridLayout();
      gdLayout.numColumns = getNumberOfControls();
      gdLayout.horizontalSpacing = HORIZONTAL_GAP;
      parent.setLayout(gdLayout);
      doFillIntoGrid(parent, gdLayout.numColumns);
    } else {
      GridLayout gdLayout = (GridLayout)layout;
      doFillIntoGrid(parent, gdLayout.numColumns);
    }
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#getLabelControl(org.eclipse.swt.widgets.Composite)
   * TODO propagate in the manufacturers the label style.
   */
  @Override
  public Label getLabelControl(Composite parent) {
    Label label = super.getLabelControl(parent);
    GridData gd = new GridData();
    gd.horizontalAlignment = SWT.FILL;
    label.setLayoutData(gd);
    int alignement = SWT.RIGHT & _labelStyle;
    if (0 == alignement) {
      alignement = SWT.CENTER & _labelStyle;
    }
    if (0 == alignement) {
      alignement = SWT.LEFT;
    }
    label.setAlignment(alignement);
    return label;
  }

  /**
   * Sets the error message that will be displayed when and if an error occurs.
   * @param message The error message.
   */
  public void setErrorMessage(String message) {
    _errorMessage = message;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#doFillIntoGrid(org.eclipse.swt.widgets.Composite, int)
   */
  @Override
  protected abstract void doFillIntoGrid(Composite parent, int numColumns);

  /**
   * Gets the value from the specified control.
   * @param control The value control.
   * @return The value.
   */
  protected abstract Object getValue(Control control);

  // /////////////////////////////////// PUBLIC API ////////////////////////////////////

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#getLabel()
   */
  public Label getLabel() {
    return getLabelControl();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#getValueControl()
   */
  public abstract Control getValueControl();

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#getHelperControl()
   */
  public abstract Control getHelperControl();

  /**
   * @see IMdeFieldEditor#getErrorMessage()
   */
  public String getErrorMessage() {
    return _errorMessage;
  }

  @Override
  protected void adjustForNumColumns(int numColumns) {
    // Do nothing.
  }

  @Override
  protected void doLoad() {
    // Do nothing.
  }

  @Override
  protected void doLoadDefault() {
    // Do nothing.
  }

  @Override
  protected void doStore() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#getNumberOfControls()
   */
  @Override
  public int getNumberOfControls() {
    return 0;
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#isValid()
   */
  @Override
  public boolean isValid() {
    return true;
  }

  /**
   * Hook for subclasses to do specific state checks.
   * <p>
   * The default implementation of this framework method uses the validator to validate the field value.<br>
   * If no validator is available, the methods returns <code>true</code>.
   * </p>
   * @return <code>true</code> if the field value is valid, and <code>false</code> if invalid
   */
  protected boolean doCheckState() {
    return isValid();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#getFieldName()
   */
  public String getFieldName() {
    return getPreferenceName();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#getFieldPage()
   */
  public DialogPage getFieldPage() {
    return getPage();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#setFieldPage(org.eclipse.jface.dialogs.DialogPage)
   */
  public void setFieldPage(DialogPage page) {
    setPage(page);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#isHelperEnabled()
   */
  public boolean isHelperEnabled() {
    boolean enabled = false;
    Control helperControl = getHelperControl();
    if (null != helperControl) {
      enabled = helperControl.isEnabled();
    }
    return enabled;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#setHelperEnabled(boolean)
   */
  public void setHelperEnabled(boolean enabled) {
    Control helperControl = getHelperControl();
    if (null != helperControl) {
      helperControl.setEnabled(enabled);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#isValueEnabled()
   */
  public boolean isValueEnabled() {
    boolean enabled = false;
    Control valueControl = getValueControl();
    if (null != valueControl) {
      enabled = valueControl.isEnabled();
    }
    return enabled;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#setValueEnabled(boolean)
   */
  public void setValueEnabled(boolean enabled) {
    Control valueControl = getValueControl();
    if (null != valueControl) {
      valueControl.setEnabled(enabled);
    }
  }

  /**
   * @see org.eclipse.jface.preference.FieldEditor#setFocus()
   */
  @Override
  public void setFocus() {
    Control valueControl = getValueControl();
    if (null != valueControl) {
      valueControl.setFocus();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#isFocused()
   */
  public boolean isFocused() {
    Control valueControl = getValueControl();
    Control helperControl = getHelperControl();
    boolean focused = false;
    if (null != valueControl) {
      focused = valueControl.isFocusControl();
    }
    if (null != helperControl) {
      focused |= helperControl.isFocusControl();
    }
    return focused;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#defaultLayout()
   */
  public void defaultLayout() {
    Composite parent = getLabelControl().getParent();
    GridLayout layout = (GridLayout) parent.getLayout();
    fillIntoGrid(parent, layout.numColumns);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.fields.IMdeFieldEditor#layout(int)
   */
  public void layout(int numColumns) {
    adjustForNumColumns(numColumns);
  }


  /**
   * Sets the MDE field editor enable or not.
   * @param enabled <code>True</code> to enable the MDE field editor else <code>false</code>.
   */
  public void setEnabled(boolean enabled) {
    Control control = getLabelControl();
    if (null != control) {
    	control.setEnabled(enabled);
	} 
   
    control = getValueControl();
    if (null != control) {
      control.setEnabled(enabled);
    }

    control = getHelperControl();
    if (null != control) {
      control.setEnabled(enabled);
    }
  }
}

