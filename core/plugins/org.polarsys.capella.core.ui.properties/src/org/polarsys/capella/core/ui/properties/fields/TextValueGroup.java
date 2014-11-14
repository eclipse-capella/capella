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
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   */
  public TextValueGroup(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, label_p, widgetFactory_p, false, false);
  }

  /**
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param skipGroup_p
   */
  public TextValueGroup(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean skipGroup_p) {
    this(parent_p, label_p, widgetFactory_p, false, skipGroup_p);
  }

  /**
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param showResetBtn_p
   * @param skipGroup_p
   */
  public TextValueGroup(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean hasResetBtn_p, boolean skipGroup_p) {
    super(parent_p, widgetFactory_p, skipGroup_p);

    createValueTextField(label_p, hasResetBtn_p);
  }

  /**
   * @param label_p
   * @param hasResetBtn_p
   */
  protected void createValueTextField(String label_p, boolean hasResetBtn_p) {
    Composite main = _widgetFactory.createComposite(_parent);
    main.setLayout(new GridLayout(3, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) _parent.getLayout()).numColumns; //2;
    main.setLayoutData(gd);

    _widgetFactory.createCLabel(main, label_p);
    _valueField = _widgetFactory.createText(main, ""); //$NON-NLS-1$
    _valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    addListeners();

    if (hasResetBtn_p) {
      createResetButton(main);
    }
  }

  /**
   * Create Reset button.
   * @param parent_p
   */
  protected void createResetButton(Composite parent_p) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image removeImage = imgRegistry.get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID);
    String tooltip = Messages.TextValueGroup_ValueResetBtn_Label;
    _valueResetBtn = createButton(parent_p, removeImage, tooltip);
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
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    super.loadData(semanticElement_p, semanticFeature_p);

    loadTextValue();
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
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
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_valueField)) {
      setDataValue(_semanticElement, _semanticFeature, _valueField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled_p);
    }
    if (null != _valueResetBtn && !_valueResetBtn.isDisposed()) {
      _valueResetBtn.setEnabled(enabled_p);
    }
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event_p) {
    if (event_p != null) {
      Object source = event_p.getSource();
      if (source != null) {
        if (source.equals(_valueResetBtn)) {
          handleResetButtonClicked(_valueResetBtn);
        }
      }
    }
  }

  /**
   * Handle Reset button click event.
   * @param button_p
   */
  protected void handleResetButtonClicked(Button button_p) {
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
