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

package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;

/**
 * The action allowing to create new representations.
 */
public class NewScenarioRepresentationAction extends NewRepresentationAction {
  private AbstractCapability _selectedEObject;

  /**
   * Constructs an action allowing to create new representations.
   * @param description_p The representation description.
   * @param selectedEObject_p The selected capability.
   * @param session_p The current session.
   */
  public NewScenarioRepresentationAction(RepresentationDescription description_p, AbstractCapability selectedEObject_p, Session session_p) {
    this(description_p, selectedEObject_p, session_p, false, true);
  }

  /**
   * Constructs an action allowing to create new representations.
   * @param description_p The representation description.
   * @param selectedEObject_p The selected capability.
   * @param session_p The current session.
   * @param forceDefaultName_p
   * @param openRepresentation_p
   */
  public NewScenarioRepresentationAction(RepresentationDescription description_p, AbstractCapability selectedEObject_p, Session session_p,
      boolean forceDefaultName_p, boolean openRepresentation_p) {
    super(description_p, selectedEObject_p, session_p);
    _selectedEObject = selectedEObject_p;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // 1 - Computes the default representation name.
    String defaultName = computeDefaultName(_selectedEObject, _description);

    if (!_forceDefaultName) {
      String dialogTitle = "Type representation name"; //$NON-NLS-1$
      Shell activeShell = Display.getDefault().getActiveShell();
      InputDialog representationNameDlg = new InputDialog(activeShell, dialogTitle, dialogTitle, defaultName, null);
      if (Window.OK == representationNameDlg.open()) {
        defaultName = representationNameDlg.getValue();
      } else {
        return;
      }
    }

    // Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
    // Executes the NewRepresentationCommand.
    NewScenarioRepresentationCommand command = new NewScenarioRepresentationCommand(defaultName, _selectedEObject, _description, _session);
    TransactionHelper.getExecutionManager(_session).execute(command);

    if (null != command.getRepresentation()) {
      SessionManager.INSTANCE.notifyRepresentationCreated(_session);
      if (_openRepresentation) {
        DialectUIManager.INSTANCE.openEditor(_session, command.getRepresentation(), new NullProgressMonitor());
      }
    }
  }

  // The command allowing to create a new representation.
  private class NewScenarioRepresentationCommand extends AbstractReadWriteCommand {
    // The representation name.
    private String _newName;
    // The new representation.
    private DRepresentation _representation;

    // Fields.
    private RepresentationDescription _repDescription;
    private Session _currentSession;

    /**
     * Constructs the command allowing to create a new representation.
     * @param newName_p The new representation name.
     * @param eObject_p The selected EObject.
     * @param repDescription_p The current representation description.
     * @param session_p The current session.
     */
    public NewScenarioRepresentationCommand(String newName_p, AbstractCapability eObject_p, RepresentationDescription repDescription_p, Session session_p) {
      _newName = newName_p;
      _repDescription = repDescription_p;
      _currentSession = session_p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commandInterrupted() {
      commandRolledBack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commandRolledBack() {
      _representation = null;
    }

    /**
     * Gets the new representation.
     * @return The new representation.
     */
    public DRepresentation getRepresentation() {
      return _representation;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("synthetic-access")
    public void run() {
      NullProgressMonitor monitor = new NullProgressMonitor();
      Scenario scenario = InteractionFactory.eINSTANCE.createScenario();
      scenario.setName(_newName);
      _selectedEObject.getOwnedScenarios().add(scenario);

      _representation = DialectManager.INSTANCE.createRepresentation(_newName, scenario, _repDescription, _currentSession, monitor);
    }
  }
}
