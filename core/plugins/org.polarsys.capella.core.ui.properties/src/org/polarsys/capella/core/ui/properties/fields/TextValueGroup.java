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
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.emf.ecore.EObject;
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

/**
 */
public class TextValueGroup extends AbstractSemanticGroup {

  protected Text valueField;
  protected Button valueResetBtn;

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
    Composite main = widgetFactory.createComposite(parent);
    main.setLayout(new GridLayout(3, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns; //2;
    main.setLayoutData(gd);

    widgetFactory.createCLabel(main, label);
    valueField = widgetFactory.createText(main, ""); //$NON-NLS-1$
    valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
    valueResetBtn = createButton(parent, removeImage, tooltip);
  }

  /**
   * 
   */
  protected void addListeners() {
    valueField.addFocusListener(this);
    valueField.addKeyListener(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    loadTextValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadTextValue();
  }

  /**
   *
   */
  public void loadTextValue() {
    if (null != valueField && null != semanticElement && null != semanticFeature) {
      setTextValue(valueField, semanticElement, semanticFeature);
      updateResetBtnStatus();
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(valueField)) {
      setDataValue(semanticElement, semanticFeature, valueField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != valueField && !valueField.isDisposed()) {
      valueField.setEnabled(enabled);
    }
    if (null != valueResetBtn && !valueResetBtn.isDisposed()) {
      valueResetBtn.setEnabled(enabled);
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
        if (source.equals(valueResetBtn)) {
          handleResetButtonClicked(valueResetBtn);
        }
      }
    }
  }

  /**
   * Handle Reset button click event.
   * @param button
   */
  protected void handleResetButtonClicked(Button button) {
    setDataValue(semanticElement, semanticFeature, null);
    setTextValue(valueField, semanticElement, semanticFeature);
  }

  /**
   * 
   */
  protected void updateResetBtnStatus() {
    if (null != valueResetBtn) {
      valueResetBtn.setEnabled(semanticElement.eGet(semanticFeature) != null);
    }
  }
  
  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object
   * @param feature
   * @param value
   */
  protected void setDataValue(EObject object, EStructuralFeature feature, Object value) {
    super.setDataValue(object, feature, value);
    
    updateResetBtnStatus();
  }
}
