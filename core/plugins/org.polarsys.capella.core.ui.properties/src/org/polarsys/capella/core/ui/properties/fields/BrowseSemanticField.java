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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.model.utils.EObjectExt2;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

/**
 */
public class BrowseSemanticField extends AbstractSemanticField {
	//
	protected Button _valueAddBtn;
	protected Button _valueOpenBtn;
	protected Button _valueDelBtn;
	protected Button _valueEditBtn;
	protected Button _valueShortcutBtn;
	protected MenuItem menuitem1;
	protected MenuItem menuitem2;
	protected Menu menu;

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
		_labelTextField = widgetFactory.createCLabel(parent_p, label_p);

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
	 * @param parent
	 */
	protected void createEditButton(Composite parent) {
		ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
		Image editImage = imgRegistry.get(ToolkitPlugin.EDIT_IMAGE_ITEM_ID);
		String tooltip = Messages.BrowseSemanticField_EditBtn;
		_valueEditBtn = createButton(parent, editImage, tooltip);
	}

	/**
	 * Create Open button.
	 * @param parent
	 */
	protected void createOpenButton(Composite parent) {
		ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
		Image openImage = imgRegistry.get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID);
		String tooltip = Messages.BrowseSemanticField_BrowseBtn;
		_valueOpenBtn = createButton(parent, openImage, tooltip);
	}

	/**
	 * Create Add button.
	 * @param parent
	 */
	protected void createAddButton(Composite parent) {
		ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
		Image addImage = imgRegistry.get(ToolkitPlugin.ADD_ITEM_IMAGE_ID);
		String tooltip = Messages.BrowseSemanticField_AddBtn;
		_valueAddBtn = createButton(parent, addImage, tooltip);
	}

	/**
	 * Create Shortcut button.
	 * @param parent
	 * @param cardType 
	 */
	protected void createShortcutButton(Composite parent, int cardType) {
		Image image = null;
		switch (cardType) {
		case 0: image = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MIN_CARD_ID);			
		break;
		case 1: image = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MAX_CARD_ID);			
		break;
		case 2: image = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MIN_LENGTH_ID);			
		break;
		case 3: image = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MAX_LENGTH_ID);			
		break;
		default:
			break;
		}
		String tooltip = Messages.BrowseSemanticField_ShortcutBtn;
		if(image != null){
			_valueShortcutBtn = createButton(parent, image, tooltip);

			menu = new Menu(_valueShortcutBtn);

			if(cardType == 0){
				menuitem1 = new MenuItem(menu, SWT.NONE);
				menuitem1.setText("0");
				Image imageMenuItem1 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MIN_LENGTH_ID);
				menuitem1.setImage(imageMenuItem1);
				menuitem2 = new MenuItem(menu, SWT.NONE);
				menuitem2.setText("1");
				Image imageMenuItem2 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.ONE_ID);
				menuitem2.setImage(imageMenuItem2);
			}else if(cardType == 1){

				menuitem1 = new MenuItem(menu, SWT.NONE);
				menuitem1.setText("1");
				Image imageMenuItem1 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.ONE_ID);
				menuitem1.setImage(imageMenuItem1);
				menuitem2 = new MenuItem(menu, SWT.NONE);
				menuitem2.setText("*");
				Image imageMenuItem2 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MAX_LENGTH_ID);
				menuitem2.setImage(imageMenuItem2);

			}else if(cardType == 2){

				menuitem1 = new MenuItem(menu, SWT.NONE);
				menuitem1.setText("0");
				Image imageMenuItem1 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MIN_LENGTH_ID);
				menuitem1.setImage(imageMenuItem1);
			}else if(cardType == 3){

				menuitem1 = new MenuItem(menu, SWT.NONE);
				menuitem1.setText("*");
				Image imageMenuItem1 = CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.QUICK_EDIT_MAX_LENGTH_ID);
				menuitem1.setImage(imageMenuItem1);
			}

			_valueShortcutBtn.setMenu(menu);

			if(menuitem1 != null){
				menuitem1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event){
						setValueTextField(menuitem1.getText());
						AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
							public void run() {
								LiteralNumericValue newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue("");
								newValue.setValue(menuitem1.getText());
								semanticElement.eSet(semanticFeature, newValue);
							}
						};
						executeCommand(cmd);
					}
				});			
			}
			if(menuitem2 != null){
				menuitem2.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event){
						setValueTextField(menuitem2.getText());
						AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
							public void run() {
								LiteralNumericValue newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue("");
								newValue.setValue(menuitem2.getText());
								semanticElement.eSet(semanticFeature, newValue);
							}
						};
						executeCommand(cmd);
					}
				});

			}
		}

	}

	/**
	 * Create text field.
	 * @param parent
	 * @param textFieldSpan
	 */
	protected void createTextField(Composite parent, int textFieldSpan) {
		// this intermediate composite have been created to allow a tooltip to be shown
		// (because the text field is disabled, its own tooltip is never shown)
		_valueTextContainer = widgetFactory.createComposite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = textFieldSpan;
		_valueTextContainer.setLayoutData(gd);
		_valueTextContainer.setLayout(new GridLayout());

		_valueTextField = widgetFactory.createText(_valueTextContainer, ICommonConstants.EMPTY_STRING);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = textFieldSpan;
		gd.widthHint = _TEXTFIELD_DEFAULT_WIDTH;
		_valueTextField.setLayoutData(gd);
		_valueTextField.setEditable(false);
		_valueTextField.setForeground(_valueTextField.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
	}

	/**
	 * Enable / Disable Delete button with specified value.
	 * @param status
	 */
	public void enableDeleteButton(boolean status) {
		if ((_valueDelBtn != null) && !_valueDelBtn.isDisposed()) {
			_valueDelBtn.setEnabled(status);
		}
	}

	/**
	 * Enable / Disable edit button with specified value.
	 * @param status
	 */
	public void enableEditButton(boolean status) {
		if ((_valueEditBtn != null) && !_valueEditBtn.isDisposed()) {
			_valueEditBtn.setEnabled(status);
		}
	}

	/**
	 * Enable / Disable Open button with specified value.
	 * @param status
	 */
	public void enableOpenButton(boolean status) {
		if ((_valueOpenBtn != null) && !_valueOpenBtn.isDisposed()) {
			_valueOpenBtn.setEnabled(status);
		}
	}

	/**
	 * Enable / Disable Add button with specified value.
	 * @param status
	 */
	public void enableAddButton(boolean status) {
		if ((_valueAddBtn != null) && !_valueAddBtn.isDisposed()) {
			_valueAddBtn.setEnabled(status);
		}
	}

	/**
	 * Enable / Disable Shortcut button with specified value.
	 * @param status
	 */
	public void enableShortcutButton(boolean status) {
		if ((_valueShortcutBtn != null) && !_valueShortcutBtn.isDisposed()) {
			_valueAddBtn.setEnabled(status);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(boolean enabled) {
		LockHelper.getInstance().enable(_valueAddBtn, enabled);
		LockHelper.getInstance().enable(_valueDelBtn, enabled);
		LockHelper.getInstance().enable(_valueEditBtn, enabled);
		LockHelper.getInstance().enable(_valueOpenBtn, enabled);
		LockHelper.getInstance().enable(_valueShortcutBtn, enabled);
		LockHelper.getInstance().update(_valueTextField, enabled);
	}

	/**
	 * Show / Hide Delete button with specified value.
	 * @param status
	 */
	protected void setVisibleDeleteButton(boolean status) {
		if (_valueDelBtn != null) {
			_valueDelBtn.setVisible(status);
		}
	}

	/**
	 * Show / Hide edit button with specified value.
	 * @param status
	 */
	protected void setVisibleEditButton(boolean status) {
		if (_valueEditBtn != null) {
			_valueEditBtn.setVisible(status);
		}
	}

	/**
	 * Show / Hide Open button with specified value.
	 * @param status
	 */
	protected void setVisibleOpenButton(boolean status) {
		if (_valueOpenBtn != null) {
			_valueOpenBtn.setVisible(status);
		}
	}

	/**
	 * Show / Hide Add button with specified value.
	 * @param status
	 */
	protected void setVisibleAddButton(boolean status) {
		if (_valueAddBtn != null) {
			_valueAddBtn.setVisible(status);
		}
	}

	/**
	 * Show / Hide Shortcut button with specified value.
	 * @param status
	 */
	protected void setVisibleShortcutButton(boolean status) {
		if (_valueShortcutBtn != null) {
			_valueShortcutBtn.setVisible(status);
		}
	}

	/**
	 * Show / Hide widget with specified value.
	 * @param status
	 */
	public void setVisible(boolean status) {
		setVisibleAddButton(status);
		setVisibleDeleteButton(status);
		setVisibleEditButton(status);
		setVisibleOpenButton(status);
		setVisibleShortcutButton(status);
		if (_valueTextField != null) {
			_valueTextField.setVisible(status);
		}
		if (_labelTextField != null) {
			_labelTextField.setVisible(status);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadData(EObject semanticElement) {
		loadData(semanticElement, semanticFeature);
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
	 * @param eObject object whose path is required
	 * @return the complete path of the given model element, or null if it's not a model element
	 */
	public String getFullLabel(EObject eObject) {
		if (eObject instanceof ModelElement) {
			String path = ((ModelElement) eObject).getFullLabel();
			if (path.startsWith("/")) { //$NON-NLS-1$
				path = path.substring(1);
			}
			path = path.replaceAll("/", "::"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}

	/**
	 * @param object
	 */
	public void setValueTextField(Object object) {
		if (_valueTextField == null)
			return;

		if (object != null) {
			if (object instanceof String) {
				_valueTextField.setText((String) object);
			} else if (object instanceof EObject) {
				_valueTextField.setText(NamingHelper.getValue((EObject) object, semanticFeature));
				_valueTextContainer.setToolTipText(getFullLabel((EObject) object));
			} else if (object instanceof Collection<?>) {
				_valueTextField.setText(EObjectExt2.formatValues((Collection<?>) object, semanticFeature));
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
	 * @param label
	 */
	public void setLabel(String label) {
		if (_labelTextField != null)
			_labelTextField.setText(label);
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent event) {
		if (event != null) {
			Object source = event.getSource();
			if (source != null) {
				if (source.equals(_valueOpenBtn)) {
					handleOpenButtonClicked(_valueOpenBtn);
				} else if (source.equals(_valueDelBtn)) {
					handleDeleteButtonClicked();
				} else if (source.equals(_valueShortcutBtn)) {
					handleShortcutButtonClicked();
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
	 * @param button
	 */
	protected void handleOpenButtonClicked(Button button) {
		// do nothing (shall be overloaded)
	}

	/**
	 * Handle Delete button click event. Reset all data value in this field.
	 */
	protected void handleDeleteButtonClicked() {
		AbstractReadWriteCommand command = getDeleteCommand(semanticElement, semanticFeature);
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
	 * Handle Shortcut button click event.
	 * @throws EditableSemanticFieldException
	 */
	protected void handleShortcutButtonClicked() throws EditableSemanticFieldException {
		if(menu != null){
			menu.setVisible(true);
		}
	}


	/**
	 * @param element
	 * @param feature
	 * @return
	 */
	protected AbstractReadWriteCommand getDeleteCommand(final EObject element, final EStructuralFeature feature) {
		return new AbstractReadWriteCommand() {
			public void run() {
				doDeleteCommand(element, feature);
			}
		};
	}

	/**
	 * @param element
	 * @param feature
	 */
	protected void doDeleteCommand(EObject element, EStructuralFeature feature) {
		if (feature.isMany()) {
			removeAllDataValue(element, feature);
		} else {
			setDataValue(element, feature, null);
		}

		if (_valueEditBtn != null)
			_valueEditBtn.setEnabled(true);

		setValueTextField((EObject) null);
	}
}
