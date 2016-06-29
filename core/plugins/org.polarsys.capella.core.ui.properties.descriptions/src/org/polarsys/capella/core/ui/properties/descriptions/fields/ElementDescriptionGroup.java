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
package org.polarsys.capella.core.ui.properties.descriptions.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * @author Joao Barata
 */
public abstract class ElementDescriptionGroup implements SelectionListener, FocusListener, KeyListener {

  /**
   * Current edited semantic element.
   */
  protected EObject _semanticElement;

  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature _semanticFeature;

  /**
   * Text widget.
   */
  protected Text _descriptionTextField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public ElementDescriptionGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super();

    Group textGroup = new Group(parent_p, SWT.NONE);
    if (widgetFactory_p != null) {
      widgetFactory_p.adapt(textGroup);
    }
    textGroup.setLayout(new GridLayout());
    textGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    createDescriptionField(textGroup, widgetFactory_p);

  }

  /**
   * 
   */
  public void dispose() {
    if (null != _descriptionTextField && !_descriptionTextField.isDisposed()) {
      _descriptionTextField.dispose();
    }
  }

  /**
   * @param textGroup_p
   * @param widgetFactory_p
   */
  private void createDescriptionField(Group textGroup_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    _descriptionTextField = createDescriptionField(textGroup_p);
    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    _descriptionTextField.setLayoutData(gd);
    _descriptionTextField.setEditable(false);
    _descriptionTextField.addFocusListener(this);
  }

  /**
   * This method can be override to provide a customized text widget
   * @param parent_p
   * @return
   */
  protected Text createDescriptionField(Composite parent_p) {
    return new Text(parent_p, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
  }

  /**
   * @param enabled_p
   */
  public void setEnabled(boolean enabled_p) {
    if ((_descriptionTextField != null) && !_descriptionTextField.isDisposed()) {
      _descriptionTextField.setEditable(enabled_p);
    }
  }

  /**
   * @param element_p
   * @param feature_p
   */
  public void loadData(EObject element_p, EStructuralFeature feature_p) {
    _semanticElement = element_p;
    _semanticFeature = feature_p;
    setTextValue(_descriptionTextField, _semanticElement, _semanticFeature);
  }

  /**
   * Set text value.
   * @param text_p
   * @param object_p
   * @param feature_p
   */
  protected void setTextValue(Text text_p, EObject object_p, EStructuralFeature feature_p) {
    // Precondition:
    if ((null == text_p) || (null == object_p) || (null == feature_p)) {
      return;
    }
    Object value = object_p.eGet(feature_p);
    String currentTextValue = text_p.getText();
    String newTextValue = (String) ((value instanceof String) ? value : ICommonConstants.EMPTY_STRING);
    // Update the text value if needed.
    if (!currentTextValue.equals(newTextValue) || ICommonConstants.EMPTY_STRING.equals(currentTextValue)) {
      text_p.setText(newTextValue);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  protected abstract void setDataValue(final EObject object_p, final EStructuralFeature feature_p, final Object value_p);

  /**
   * Save a description into related element.
   * @param newDescription_p
   */
  public void saveDescription(String newDescription_p) {
    setDataValue(_semanticElement, _semanticFeature, newDescription_p);
  }

  /**
   * @see org.eclipse.emf.IRichTextSaveable.runtime.capella.richtext.IEEFRichTextSaveable#save(java.lang.String)
   */
  public void save(String value_p) {
    saveDescription(value_p);
  }

  /**
   * @see org.eclipse.emf.IRichTextSaveable.runtime.capella.richtext.IEEFRichTextSaveable#getValueToSave()
   */
  public String getValueToSave() {
    return _descriptionTextField.getText();
  }

  /**
   * @param textField_p text field to be filled
   */
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_descriptionTextField)) {
      setDataValue(_semanticElement, _semanticFeature, _descriptionTextField.getText());
    }
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
   */
  public void focusGained(final FocusEvent event_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
   */
  public void focusLost(FocusEvent event_p) {
    if (event_p != null) {
      Object source = event_p.getSource();
      if ((source != null) && (source instanceof Text)) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
   */
  public void keyPressed(KeyEvent event_p) {
    if ((event_p != null) && (event_p.character == SWT.CR)) {
      Object source = event_p.getSource();
      if ((source != null) && (source instanceof Text)) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
   */
  public void keyReleased(KeyEvent event_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent event_p) {
	// Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent event_p) {
    // Do nothing.
  }
}
