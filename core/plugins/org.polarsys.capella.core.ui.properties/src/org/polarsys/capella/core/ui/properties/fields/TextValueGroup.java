/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class TextValueGroup extends AbstractSemanticGroup {

  protected Text _valueField;
  protected Button _valueResetBtn;

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   */
  public TextValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, label, widgetFactory, false, false);
  }

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param skipGroup
   */
  public TextValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, boolean skipGroup) {
    this(parent, label, widgetFactory, false, skipGroup);
  }

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param showResetBtn
   * @param skipGroup
   */
  public TextValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, boolean hasResetBtn, boolean skipGroup) {
    super(parent, widgetFactory, skipGroup);

    createValueTextField(label, hasResetBtn);
  }

  /**
   * @param label
   * @param hasResetBtn
   */
  protected void createValueTextField(String label, boolean hasResetBtn) {
    Composite main = _widgetFactory.createComposite(_parent);
    main.setLayout(new GridLayout(3, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) _parent.getLayout()).numColumns; //2;
    main.setLayoutData(gd);

    _widgetFactory.createCLabel(main, label);
    _valueField = _widgetFactory.createText(main, ""); //$NON-NLS-1$
    _valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    addListeners();

    if (hasResetBtn) {
      createResetButton(main);
    }
  }

  /**
   * Create Reset button.
   * @param parent
   */
  protected void createResetButton(Composite parent) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image removeImage = imgRegistry.get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID);
    String tooltip = Messages.TextValueGroup_ValueResetBtn_Label;
    _valueResetBtn = createButton(parent, removeImage, tooltip);
  }

  /**
   * 
   */
  protected void addListeners() {
    _valueField.addFocusListener(this);
    _valueField.addKeyListener(this);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    loadTextValue();
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement) {
    loadTextValue();
  }

  /**
   *
   */
  public void loadTextValue() {
    if (null != _valueField && null != _semanticElement && null != _semanticFeature) {
      setTextValue(_valueField, _semanticElement, _semanticFeature);
      updateResetBtnStatus();
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(_valueField)) {
      setDataValue(_semanticElement, _semanticFeature, _valueField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled);
    }
    if (null != _valueResetBtn && !_valueResetBtn.isDisposed()) {
      _valueResetBtn.setEnabled(enabled);
    }
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event) {
    if (event != null) {
      Object source = event.getSource();
      if (source != null) {
        if (source.equals(_valueResetBtn)) {
          handleResetButtonClicked(_valueResetBtn);
        }
      }
    }
  }

  /**
   * Handle Reset button click event.
   * @param button
   */
  protected void handleResetButtonClicked(Button button) {
    setDataValue(_semanticElement, _semanticFeature, null);
  }

  /**
   * 
   */
  protected void updateResetBtnStatus() {
    if (null != _valueResetBtn) {
      _valueResetBtn.setEnabled(_semanticElement.eGet(_semanticFeature) != null);
    }
  }
}
