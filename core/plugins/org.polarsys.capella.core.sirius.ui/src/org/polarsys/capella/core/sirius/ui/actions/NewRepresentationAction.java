/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.Messages;
import org.polarsys.capella.core.sirius.analysis.commands.NewRepresentationCommand;

/**
 * The action allowing to create new representations.
 */
public class NewRepresentationAction extends AbstractNewRepresentationAction {

  protected RepresentationDescription description;

  protected boolean forceDefaultName;	
  private String descriptionLabel;

  public NewRepresentationAction(RepresentationDescription description, EObject selectedEObject, Session session, String message) {
    this(description, selectedEObject, session, false, true);
    this.message = message;
  }

  public NewRepresentationAction(RepresentationDescription description, EObject selectedEObject, Session session) {
    this(description, selectedEObject, session, false, true);
  }

  public NewRepresentationAction(RepresentationDescription description, EObject selectedEObject, Session session,
      boolean forceDefaultName, boolean openRepresentation) {
    super(description.getName());

    this.selectedEObject = selectedEObject;
    this.description = description;
    this.session = session;
    this.forceDefaultName = forceDefaultName;
    this.openRepresentation = openRepresentation;

    this.descriptionLabel = getDescriptionLabel(description);
    if (!StringUtil.isEmpty(descriptionLabel)) {
      setText(descriptionLabel);
    }

    ImageDescriptor imageDescriptor = getDescriptionImageDescriptor(description);
    setImageDescriptor(imageDescriptor);
  }

  protected String computeDefaultName(EObject eObject, RepresentationDescription repDescription) {

    IInterpreter interpreter = InterpreterUtil.getInterpreter(eObject);

    String newName = "New "; //$NON-NLS-1$

    if (!StringUtil.isEmpty(descriptionLabel)) {
      newName += descriptionLabel;
    } else {
      newName += repDescription.getName();
    }

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

  @Override
  public void run() {
    // 1 - Computes the default representation name.
    String defaultName = computeDefaultName(selectedEObject, description);

    if (!forceDefaultName) {

      String dialogTitle = "New " + descriptionLabel; //$NON-NLS-1$
      String dialogMessage;
      if(message != null) {
        dialogMessage = message + "\n" + "Name:"; //$NON-NLS-1$
      } else {
        dialogMessage = "Name:"; //$NON-NLS-1$
      }

      Shell activeShell = Display.getDefault().getActiveShell();

      IInputValidator validator = new IInputValidator() {

        @Override
        public String isValid(String newText) {
          if(newText.isBlank()) {
            return Messages.blankName;
          }
          return null;					
        }
      };

      InputDialog representationNameDialog = new InputDialog(activeShell, dialogTitle, dialogMessage, defaultName, validator);
      isCanceled = Window.CANCEL == representationNameDialog.open();

      if (!isCanceled) {
        defaultName = representationNameDialog.getValue();
      } else {
        return;
      }
    }

    // Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
    // Executes the NewRepresentationCommand.
    NewRepresentationCommand command = new NewRepresentationCommand(defaultName, selectedEObject, description, session);
    TransactionHelper.getExecutionManager(session).execute(command);

    if (null != command.getRepresentation()) {
      SessionManager.INSTANCE.notifyRepresentationCreated(session);
      if (openRepresentation) {
        DialectUIManager.INSTANCE.openEditor(session, command.getRepresentation(), new NullProgressMonitor());
      }
    }
  }
}
