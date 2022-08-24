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
package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectNewRepresentationDialog;
import org.polarsys.capella.core.sirius.analysis.commands.NewRepresentationCommand;

/**
 * The action allowing to create new representations.
 */
public class SelectNewRepresentationAction extends AbstractNewRepresentationAction {
  protected Set<RepresentationDescription> descriptions;

  public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject,
      Session session, String message) {
    this(descriptions, selectedEObject, session, true);
    this.message = message + "\n" + this.message;
  }

  public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject,
      Session session) {
    this(descriptions, selectedEObject, session, true);
  }

  public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject,
      Session session, boolean openRepresentation) {
    super(Messages.SelectNewRepresentation);
    message = Messages.SelectNewRepresentation;

    this.selectedEObject = selectedEObject;
    this.descriptions = new HashSet<>(descriptions);
    this.session = session;
    this.openRepresentation = openRepresentation;

    RepresentationDescription firstDescription = descriptions.iterator().next();

    ImageDescriptor imageDescriptor = getDescriptionImageDescriptor(firstDescription);
    setImageDescriptor(imageDescriptor);
  }

  @Override
  public void run() {
    Shell activeShell = Display.getDefault().getActiveShell();

    SelectNewRepresentationDialog dialog = new SelectNewRepresentationDialog(activeShell, message, selectedEObject,
        descriptions);
    isCanceled = Window.CANCEL == dialog.open();

    if (isCanceled) {
      return;
    }

    String name = dialog.getName();
    RepresentationDescription selectedDescription = dialog.getSelectedRepresentationDescription();
    // Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
    // Executes the NewRepresentationCommand.
    NewRepresentationCommand command = new NewRepresentationCommand(name, selectedEObject, selectedDescription,
        session);
    TransactionHelper.getExecutionManager(session).execute(command);

    if (null != command.getRepresentation()) {
      SessionManager.INSTANCE.notifyRepresentationCreated(session);
      if (openRepresentation) {
        DialectUIManager.INSTANCE.openEditor(session, command.getRepresentation(), new NullProgressMonitor());
      }
    }
  }
}
