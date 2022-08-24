/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * 
 * @author etraisnel
 *
 */
public class SelectNewRepresentationDialog extends AbstractSelectionDialog<RepresentationDescription> {

  /**
   * List of available representation descriptions
   */
  HashSet<RepresentationDescription> descriptions;

  /**
   * selected representation description from combo
   */
  RepresentationDescription selectedRepresentationDescription;

  /**
   * Input text widget.
   */
  private Text text;

  /**
   * Text typed in text widget
   */
  private String newRepresentationName;

  /**
   * The input validator, or <code>null</code> if none.
   */
  private IInputValidator validator;

  /**
   * Error message label widget.
   */
  private Text errorMessageText;

  /**
   * Error message string.
   */
  private String errorMessage;

  /**
   * Message Displayed about the Combo
   */
  private String message;

  /**
   * Eobject on which the double click was performed
   */
  private EObject selectedEObject;

  public SelectNewRepresentationDialog(Shell parentShell, String message, EObject selectedEObject,
      Collection<RepresentationDescription> descriptions) {
    super(parentShell);
    this.selectedEObject = selectedEObject;
    this.descriptions = new HashSet<>(descriptions);
    this.message = message;
    setTitle(Messages.newRepresentationFor + ((NamedElement) selectedEObject).getName());
    this.validator = (newText -> {
      if (newText.isBlank()) {
        return Messages.blankName;
      }
      return null;
    });
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);

    // Create Label above the combo
    setMessage(message);
    createMessageArea(composite);

    // Create list viewer
    ComboViewer comboViewer = new ComboViewer(composite,
        SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
    comboViewer.getCombo().setFont(parent.getFont());

    // Create Label next to name input
    Label label = new Label(composite, SWT.NONE);
    label.setText("Name :");
    label.setFont(parent.getFont());

    GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
        | GridData.VERTICAL_ALIGN_CENTER);
    data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
    // Create Name input
    text = new Text(composite, SWT.SINGLE | SWT.BORDER);
    text.setFont(parent.getFont());
    text.setLayoutData(data);

    // Set the label provider for the combo
    comboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        RepresentationDescription description = (RepresentationDescription) element;
        IInterpreter interpreter = InterpreterUtil.getInterpreter(description);
        String newName = "";

        String titleExpression = description.getTitleExpression();
        if (!StringUtil.isEmpty(titleExpression)) {
          try {
            newName = interpreter.evaluateString(description, titleExpression);
          } catch (EvaluationException e) {
            SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
          }
        }
        return newName; // $NON-NLS-1$
      }
    });
    comboViewer.getCombo().setLayoutData(data);

    // Set the content provider
    comboViewer.setContentProvider(ArrayContentProvider.getInstance());

    // Set input to all available descriptions
    comboViewer.setInput(descriptions);

    // Set initial selection
    setInitialSelection(descriptions.iterator().next());

    // Add a selection change listener on the combo to update the default name in text field
    comboViewer.addSelectionChangedListener(selectionChangedEvent -> {
      selectedRepresentationDescription = (RepresentationDescription) ((StructuredSelection) selectionChangedEvent
          .getSelection()).getFirstElement();
      text.setText(computeDefaultName(selectedEObject, selectedRepresentationDescription));
    });

    comboViewer.setSelection(new StructuredSelection(getInitialSelection()));

    // Initialize text field with default name from initial selection
    newRepresentationName = computeDefaultName(selectedEObject, getInitialSelection().get(0));
    text.setText(newRepresentationName);

    // Add modify listener to validate text input
    text.addModifyListener(e -> validateInput());

    // Add Error message text
    errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
    errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
    errorMessageText.setBackground(errorMessageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    setErrorMessage(errorMessage);

    // Add a selection change listener on the combo to update the default name in text field
    comboViewer.addSelectionChangedListener(event -> text.setText(computeDefaultName(selectedEObject,
        (RepresentationDescription) ((StructuredSelection) event.getSelection()).getFirstElement())));

    return composite;
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  protected boolean isResizable() {
    return false;
  }

  @Override
  public DialogTray getTray() {
    return null;
  }

  public String getName() {
    return newRepresentationName;
  }

  protected String computeDefaultName(EObject eObject, RepresentationDescription repDescription) {

    IInterpreter interpreter = InterpreterUtil.getInterpreter(eObject);

    String newName = "New "; //$NON-NLS-1$

    newName += repDescription.getName();

    String titleExpression = repDescription.getTitleExpression();
    if (!StringUtil.isEmpty(titleExpression)) {
      try {
        newName = interpreter.evaluateString(eObject, titleExpression);
      } catch (EvaluationException e) {
        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
      }
    }

    return newName;
  }

  /**
   * Validates the input.
   * <p>
   * The default implementation of this framework method delegates the request to the supplied input validator object;
   * if it finds the input invalid, the error message is displayed in the dialog's message line. This hook method is
   * called whenever the text changes in the input field.
   * </p>
   */
  protected void validateInput() {
    newRepresentationName = text.getText();
    if (validator != null) {
      errorMessage = validator.isValid(text.getText());
    }
    setErrorMessage(errorMessage);
  }

  /**
   * Sets or clears the error message. If not <code>null</code>, the OK button is disabled.
   *
   * @param errorMessage
   *          the error message, or <code>null</code> to clear
   * @since 3.0
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    if (errorMessageText != null && !errorMessageText.isDisposed()) {
      errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$
      // Disable the error message text control if there is no error, or
      // no error text (empty or whitespace only). Hide it also to avoid
      // color change.
      // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
      boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
      errorMessageText.setEnabled(hasError);
      errorMessageText.setVisible(hasError);
      errorMessageText.getParent().update();
      // Access the ok button by id, in case clients have overridden button creation.
      // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
      Control button = getButton(IDialogConstants.OK_ID);
      if (button != null) {
        button.setEnabled(errorMessage == null);
      }
    }
  }

  public RepresentationDescription getSelectedRepresentationDescription() {
    return selectedRepresentationDescription;
  }

}