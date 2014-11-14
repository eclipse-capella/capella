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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.EObjectExt2;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */
public class BrowseSemanticField extends AbstractSemanticField {
  //
  protected Button _valueAddBtn;
  protected Button _valueOpenBtn;
  protected Button _valueDelBtn;
  protected Button _valueEditBtn;

  // this text field shall be accessed only through its getter/setter
  private Text _valueTextField;
  private CLabel _labelTextField;
  private Composite _valueTextContainer;

  /**
   * the default width of the text field
   */
  private static final int _TEXTFIELD_DEFAULT_WIDTH = 200;

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param textFieldSpan_p
   */
  public BrowseSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, int textFieldSpan_p) {
    super(widgetFactory_p);
    _labelTextField = _widgetFactory.createCLabel(parent_p, label_p);

    createTextField(parent_p, textFieldSpan_p);
  }

  /**
   * Create Delete button.
   * @param parent_p
   */
  protected void createDeleteButton(Composite parent_p) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image removeImage = imgRegistry.get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID);
    String tooltip = Messages.BrowseSemanticField_DelBtn;
    _valueDelBtn = createButton(parent_p, removeImage, tooltip);
  }

  /**
   * Create Edit button.
   * @param parent_p
   */
  protected void createEditButton(Composite parent_p) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image editImage = imgRegistry.get(ToolkitPlugin.EDIT_IMAGE_ITEM_ID);
    String tooltip = Messages.BrowseSemanticField_EditBtn;
    _valueEditBtn = createButton(parent_p, editImage, tooltip);
  }

  /**
   * Create Open button.
   * @param parent_p
   */
  protected void createOpenButton(Composite parent_p) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image openImage = imgRegistry.get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID);
    String tooltip = Messages.BrowseSemanticField_BrowseBtn;
    _valueOpenBtn = createButton(parent_p, openImage, tooltip);
  }

  /**
   * Create Add button.
   * @param parent_p
   */
  protected void createAddButton(Composite parent_p) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image addImage = imgRegistry.get(ToolkitPlugin.ADD_ITEM_IMAGE_ID);
    String tooltip = Messages.BrowseSemanticField_AddBtn;
    _valueAddBtn = createButton(parent_p, addImage, tooltip);
  }

  /**
   * Create text field.
   * @param parent_p
   * @param textFieldSpan_p
   */
  protected void createTextField(Composite parent_p, int textFieldSpan_p) {
    // this intermediate composite have been created to allow a tooltip to be shown
    // (because the text field is disabled, its own tooltip is never shown)
    _valueTextContainer = _widgetFactory.createComposite(parent_p);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = textFieldSpan_p;
    _valueTextContainer.setLayoutData(gd);
    _valueTextContainer.setLayout(new GridLayout());

    _valueTextField = _widgetFactory.createText(_valueTextContainer, ICommonConstants.EMPTY_STRING);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = textFieldSpan_p;
    gd.widthHint = _TEXTFIELD_DEFAULT_WIDTH;
    _valueTextField.setLayoutData(gd);
    _valueTextField.setEditable(false);
    _valueTextField.setForeground(_valueTextField.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
  }

  /**
   * Enable / Disable Delete button with specified value.
   * @param status_p
   */
  public void enableDeleteButton(boolean status_p) {
    if ((_valueDelBtn != null) && !_valueDelBtn.isDisposed()) {
      _valueDelBtn.setEnabled(status_p);
    }
  }

  /**
   * Enable / Disable edit button with specified value.
   * @param status_p
   */
  public void enableEditButton(boolean status_p) {
    if ((_valueEditBtn != null) && !_valueEditBtn.isDisposed()) {
      _valueEditBtn.setEnabled(status_p);
    }
  }

  /**
   * Enable / Disable Open button with specified value.
   * @param status_p
   */
  public void enableOpenButton(boolean status_p) {
    if ((_valueOpenBtn != null) && !_valueOpenBtn.isDisposed()) {
      _valueOpenBtn.setEnabled(status_p);
    }
  }

  /**
   * Enable / Disable Add button with specified value.
   * @param status_p
   */
  public void enableAddButton(boolean status_p) {
    if ((_valueAddBtn != null) && !_valueAddBtn.isDisposed()) {
      _valueAddBtn.setEnabled(status_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    LockHelper.getInstance().enable(_valueAddBtn, enabled_p);
    LockHelper.getInstance().enable(_valueDelBtn, enabled_p);
    LockHelper.getInstance().enable(_valueEditBtn, enabled_p);
    LockHelper.getInstance().enable(_valueOpenBtn, enabled_p);
    LockHelper.getInstance().update(_valueTextField, enabled_p);
  }

  /**
   * Show / Hide Delete button with specified value.
   * @param status_p
   */
  protected void setVisibleDeleteButton(boolean status_p) {
    if (_valueDelBtn != null) {
      _valueDelBtn.setVisible(status_p);
    }
  }

  /**
   * Show / Hide edit button with specified value.
   * @param status_p
   */
  protected void setVisibleEditButton(boolean status_p) {
    if (_valueEditBtn != null) {
      _valueEditBtn.setVisible(status_p);
    }
  }

  /**
   * Show / Hide Open button with specified value.
   * @param status_p
   */
  protected void setVisibleOpenButton(boolean status_p) {
    if (_valueOpenBtn != null) {
      _valueOpenBtn.setVisible(status_p);
    }
  }

  /**
   * Show / Hide Add button with specified value.
   * @param status_p
   */
  protected void setVisibleAddButton(boolean status_p) {
    if (_valueAddBtn != null) {
      _valueAddBtn.setVisible(status_p);
    }
  }

  /**
   * Show / Hide widget with specified value.
   * @param status_p
   */
  public void setVisible(boolean status_p) {
    setVisibleAddButton(status_p);
    setVisibleDeleteButton(status_p);
    setVisibleEditButton(status_p);
    setVisibleOpenButton(status_p);
    if (_valueTextField != null) {
      _valueTextField.setVisible(status_p);
    }
    if (_labelTextField != null) {
      _labelTextField.setVisible(status_p);
    }
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, _semanticFeature);
  }

  /**
   *
   */
  public String getValueTextField() {
    if (_valueTextField != null)
      return _valueTextField.getText();
    return null;
  }

  /**
   * @param eObject_p object whose path is required
   * @return the complete path of the given model element, or null if it's not a model element
   */
  public String getFullLabel(EObject eObject_p) {
    if (eObject_p instanceof ModelElement) {
      String path = ((ModelElement) eObject_p).getFullLabel();
      if (path.startsWith("/")) { //$NON-NLS-1$
        path = path.substring(1);
      }
      path = path.replaceAll("/", "::"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return null;
  }

  /**
   * @param object_p
   */
  public void setValueTextField(Object object_p) {
    if (_valueTextField == null)
      return;

    if (object_p != null) {
      if (object_p instanceof String) {
        _valueTextField.setText((String) object_p);
      } else if (object_p instanceof EObject) {
        _valueTextField.setText(NamingHelper.getValue((EObject) object_p, _semanticFeature));
        _valueTextContainer.setToolTipText(getFullLabel((EObject) object_p));
      } else if (object_p instanceof Collection<?>) {
        _valueTextField.setText(EObjectExt2.formatValues((Collection<?>) object_p, _semanticFeature));
      }
    } else {
      _valueTextField.setText(Messages.UndefinedValue);
    }
  }

  /**
   * 
   */
  protected boolean isLoaded() {
    return !_valueTextField.getText().equals(Messages.UndefinedValue);
  }

  /**
   * @param label_p
   */
  public void setLabel(String label_p) {
    if (_labelTextField != null)
      _labelTextField.setText(label_p);
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event_p) {
    if (event_p != null) {
      Object source = event_p.getSource();
      if (source != null) {
        if (source.equals(_valueOpenBtn)) {
          handleOpenButtonClicked(_valueOpenBtn);
        } else if (source.equals(_valueDelBtn)) {
          handleDeleteButtonClicked();
        } else if (source.equals(_valueEditBtn)) {
          try {
            handleEditButtonClicked();
          } catch (EditableSemanticFieldException ex) {
            // this exception has been thrown in order to roll back the command
            // it shall not be visible by the end user
          }
        } else if (_valueAddBtn.equals(source)) {
          try {
            handleAddButtonClicked();
          } catch (EditableSemanticFieldException ex) {
            // this exception has been thrown in order to roll back the command
            // it shall not be visible by the end user
          }
        }
      }
    }
  }

  /**
   * Handle Open button click event.
   * @param button_p
   */
  protected void handleOpenButtonClicked(Button button_p) {
    // do nothing (shall be overloaded)
  }

  /**
   * Handle Delete button click event. Reset all data value in this field.
   */
  protected void handleDeleteButtonClicked() {
    AbstractReadWriteCommand command = getDeleteCommand(_semanticElement, _semanticFeature);
    executeCommand(command);
  }

  /**
   * Handle Edit button click event.
   * @throws EditableSemanticFieldException
   */
  protected void handleEditButtonClicked() throws EditableSemanticFieldException {
    // do nothing (shall be overloaded)
  }

  /**
   * Handle Add button click event.
   * @throws EditableSemanticFieldException
   */
  protected void handleAddButtonClicked() throws EditableSemanticFieldException {
    // do nothing (shall be overloaded)
  }

  /**
   * @param element_p
   * @param feature_p
   * @return
   */
  protected AbstractReadWriteCommand getDeleteCommand(final EObject element_p, final EStructuralFeature feature_p) {
    return new AbstractReadWriteCommand() {
      public void run() {
        doDeleteCommand(element_p, feature_p);
      }
    };
  }
  
  /**
   * @param element_p
   * @param feature_p
   */
  protected void doDeleteCommand(EObject element_p, EStructuralFeature feature_p) {
    if (feature_p.isMany()) {
      removeAllDataValue(element_p, feature_p);
    } else {
      setDataValue(element_p, feature_p, null);
    }

    if (_valueEditBtn != null)
      _valueEditBtn.setEnabled(true);

    setValueTextField((EObject) null);
  }
}
