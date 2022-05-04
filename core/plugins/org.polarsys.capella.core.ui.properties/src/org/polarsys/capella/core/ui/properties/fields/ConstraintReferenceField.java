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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditor;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.ConstraintExt;
import org.polarsys.capella.core.ui.properties.controllers.ConstraintController;
import org.polarsys.capella.core.ui.properties.controllers.ISimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

/**
 * Property filed allowing to edit Constraints (or OpaqueExpressions stored in Constraints).
 */
public class ConstraintReferenceField extends AbstractSemanticField {
  public static final String DEFAULT_CONSTRAINT_NAME = "";

  /**
   * Controller associated to this semantic field.
   */
  protected ConstraintController _controller;

  private final Label _labelTextArea;
  private final Composite _textArea;

  protected Button _valueOpenBtn;
  protected Button _valueDelBtn;
  protected Button _valueEditBtn;

  private Constraint _currentConstraint;

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param displayOpenButton 
   * @param defaultName
   * @param controller
   */
  public ConstraintReferenceField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean displayOpenButton, ConstraintController controller) {
    super(widgetFactory);
    
    _controller = controller;
    
    _labelTextArea = widgetFactory.createLabel(parent, label);
    _labelTextArea.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));

    // Create a text area with a read only Text field (3 lines height).
    _textArea = widgetFactory.createComposite(parent);
    Text t = widgetFactory.createText(_textArea, "", SWT.READ_ONLY);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.heightHint = t.getLineHeight() * 3 + 5;
    gd.widthHint = 150;
    _textArea.setLayoutData(gd);
    _textArea.setLayout(new FillLayout());

    createEditButton(parent);
   if (displayOpenButton) {
      createOpenButton(parent);
   }
    createDeleteButton(parent);
  }

  /**
   * Create a button with specified image and no label.
   * 
   * @param parent
   * @param image
   * @param tooltip
   * @return a not <code>null</code> instance.
   */
  protected Button createButton(Composite parent, Image image, String tooltip) {
    Button button = super.createButton(parent, image, tooltip);
    button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    return button;
  }

  /**
   * Create the mono line Text field used to edit the name of a Constraint.
   * 
   * @param elementToEdit
   */
  public void createConstraintNameTextField(final EObject semanticElement, final EStructuralFeature semanticFeature) {
    String text = _controller.loadText(semanticElement, semanticFeature);
    final Text defaultTextField = widgetFactory.createText(_textArea, text);
    defaultTextField.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(final ModifyEvent e) {
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          public void run() {
            _controller.editText(semanticElement, semanticFeature, defaultTextField.getText());
          }
        };
        executeCommand(command);
      }
    });
  }

  /**
   * Create a multi line Text filed used to edit texts that are not of type LinkedText.
   * 
   * @param elementToEdit
   */
  public void createDefaultTextTextField(final EObject semanticElement, final EStructuralFeature semanticFeature) {
    String text = _controller.loadText(semanticElement, semanticFeature);
    final Text defaultTextField = widgetFactory.createText(_textArea, text, SWT.MULTI | SWT.V_SCROLL);
    defaultTextField.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(final ModifyEvent e) {
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          public void run() {
            _controller.editText(semanticElement, semanticFeature, defaultTextField.getText());
          }
        };
        executeCommand(command);
      }
    });
  }

  /**
   * Create Delete button.
   * 
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
   * 
   * @param parent
   */
  protected void createEditButton(Composite parent) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image editImage = imgRegistry.get(ToolkitPlugin.EDIT_IMAGE_ITEM_ID);
    String tooltip = Messages.BrowseSemanticField_EditBtn;
    _valueEditBtn = createButton(parent, editImage, tooltip);
  }

  /**
   * Create a LinkedText Editor to edit LinkedText content (or elements that are not already existing).
   * 
   * @param semanticElement
   * @param semanticFeature
   * @param elementToEdit
   */
  public void createLinkedTextEditor(final EObject semanticElement, final EStructuralFeature semanticFeature) {

    CapellaEmbeddedLinkedTextEditor editor = new CapellaEmbeddedLinkedTextEditor(_textArea,
        SWT.H_SCROLL | SWT.V_SCROLL | widgetFactory.getBorderStyle());
    final CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput(semanticElement) {
      @Override
      public String getText() {
        return _controller.loadText(semanticElement, semanticFeature);
      }

      @Override
      public void setText(final String linkedText) {
        if ((linkedText == null || linkedText.isEmpty()) && _controller.getElementToEdit(semanticElement, semanticFeature) == null) {
          // setText is called on a mouse click in the editor -> do not create all model elements in this case
          return;
        }
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          public void run() {
            EObject constraint = _controller.loadValue(semanticElement, semanticFeature);
            _controller.editText(semanticElement, semanticFeature, linkedText);
            constraint = _controller.loadValue(semanticElement, semanticFeature);
            _currentConstraint = (Constraint) constraint;
          }
        };
        executeCommand(command);
      }
    };
    editor.getSourceViewer().getTextWidget().addDisposeListener(new DisposeListener() {
      @Override
      public void widgetDisposed(DisposeEvent e) {
        input.dispose();
      }
    });
    editor.setInput(input);
  }

  /**
   * Create Open button.
   * 
   * @param parent
   */
  protected void createOpenButton(Composite parent) {
    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image openImage = imgRegistry.get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID);
    String tooltip = Messages.BrowseSemanticField_BrowseBtn;
    _valueOpenBtn = createButton(parent, openImage, tooltip);
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

    loadData(element, feature);
    if (_valueEditBtn != null)
      _valueEditBtn.setEnabled(true);

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
   * Handle Delete button click event. Reset all data value in this field.
   */
  protected void handleDeleteButtonClicked() {
    AbstractReadWriteCommand command = getDeleteCommand(semanticElement, semanticFeature);
    executeCommand(command);
  }

  /**
   * Handle Edit button click event.
   */
  protected void handleEditButtonClicked() throws EditableSemanticFieldException {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }

      public void run() {
        ((ISimpleEditableSemanticFieldController) _controller).editValue(semanticElement, semanticFeature,
            DEFAULT_CONSTRAINT_NAME);

      }
    };
    executeCommand(command);
  }

  /**
   * Handle Open button click event.
   * 
   * @param button
   */
  protected void handleOpenButtonClicked(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }

      public void run() {
        List<EObject> list = _controller.readOpenValues(semanticElement, semanticFeature);
        // calling selection wizard
        EObject firstResult = DialogHelper.openSimpleSelectionDialog(button, list);
        if (null != firstResult) {
          _controller.writeOpenValue(semanticElement, semanticFeature, DEFAULT_CONSTRAINT_NAME, firstResult);
        }
      }
    };
    executeCommand(command);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(final EObject semanticElement, final EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    if (_controller == null) {
      return;
    }

    EObject constraint = _controller.loadValue(semanticElement, semanticFeature);
    // Since we do some graphical changes while loading, we need to keep a trace to previous element to avoid massive ui
    // refresh.
    if (constraint != null && constraint == _currentConstraint) {
      return;
    }

    _currentConstraint = (Constraint) constraint;

    updateTextbox(_textArea);

  }

  private void updateTextbox(Composite textArea) {

    // Clear the text area (there should be only 1 child)
    for (Control c : _textArea.getChildren()) {
      c.dispose();
    }

    final EObject elementToEdit = _controller.getElementToEdit(semanticElement, semanticFeature);

    // Install editor/text field corresponding to the element to edit
    boolean useLinkedTextEditor = false;
    if (elementToEdit == null) {
      useLinkedTextEditor = true;

    } else if (elementToEdit instanceof OpaqueExpression) {
      OpaqueExpression expression = (OpaqueExpression) elementToEdit;
      useLinkedTextEditor = !ConstraintExt.hasBodies(expression) || ConstraintExt.hasPrimaryLinkedText(expression);
    }

    if (useLinkedTextEditor) {
      // Use a LinkedTextEditor if the first element of the OpaqueExpression is a LinkedText (if Constraint,
      // OpaqueExpression or LinkedText does not exist, create them)
      createLinkedTextEditor(semanticElement, semanticFeature);

    } else if (elementToEdit instanceof OpaqueExpression) {
      // Edit the first text of the Opaque Expression using a multi line Text field
      createDefaultTextTextField(semanticElement, semanticFeature);

    } else if (elementToEdit instanceof Constraint) {
      // Edit the Constraint name using a mono line Text field
      createConstraintNameTextField(semanticElement, semanticFeature);
    }

    _textArea.layout(true, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    LockHelper.getInstance().enable(_labelTextArea, enabled);
    LockHelper.getInstance().enable(_textArea, enabled);
    LockHelper.getInstance().enable(_valueDelBtn, enabled);
    LockHelper.getInstance().enable(_valueEditBtn, enabled);
    LockHelper.getInstance().enable(_valueOpenBtn, enabled);
  }

  /**
   * Show / Hide widget with specified value.
   * 
   * @param status
   */
  public void setVisible(boolean status) {
    _labelTextArea.setVisible(status);
    _textArea.setVisible(status);
    setVisibleDeleteButton(status);
    setVisibleEditButton(status);
    setVisibleOpenButton(status);
  }

  /**
   * Show / Hide Delete button with specified value.
   * 
   * @param status
   */
  protected void setVisibleDeleteButton(boolean status) {
    if (_valueDelBtn != null) {
      _valueDelBtn.setVisible(status);
    }
  }

  /**
   * Show / Hide edit button with specified value.
   * 
   * @param status
   */
  protected void setVisibleEditButton(boolean status) {
    if (_valueEditBtn != null) {
      _valueEditBtn.setVisible(status);
    }
  }

  /**
   * Show / Hide Open button with specified value.
   * 
   * @param status
   */
  protected void setVisibleOpenButton(boolean status) {
    if (_valueOpenBtn != null) {
      _valueOpenBtn.setVisible(status);
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
        }
        updateTextbox(_textArea);
      }
    }
  }

}
