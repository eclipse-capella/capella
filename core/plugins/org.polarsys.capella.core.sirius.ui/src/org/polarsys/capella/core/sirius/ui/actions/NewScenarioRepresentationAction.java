/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * The action allowing to create new representations.
 */
public class NewScenarioRepresentationAction extends NewRepresentationAction {
  private AbstractCapability selectedEObject;

  /**
   * Constructs an action allowing to create new representations.
   * @param description The representation description.
   * @param selectedEObject The selected capability.
   * @param session The current session.
   */
  public NewScenarioRepresentationAction(RepresentationDescription description, AbstractCapability selectedEObject, Session session) {
    this(description, selectedEObject, session, false, true);
  }

  /**
   * Constructs an action allowing to create new representations.
   * @param description The representation description.
   * @param selectedEObject The selected capability.
   * @param session The current session.
   * @param forceDefaultName
   * @param openRepresentation
   */
  public NewScenarioRepresentationAction(RepresentationDescription description, AbstractCapability selectedEObject, Session session,
      boolean forceDefaultName, boolean openRepresentation) {
    super(description, selectedEObject, session, forceDefaultName, openRepresentation);
    this.selectedEObject = selectedEObject;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // 1 - Computes the default representation name.
    String defaultName = computeDefaultName(selectedEObject, description);

    if (!forceDefaultName) {
      
      String label = MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel());
      if (label == null || label.isEmpty()) {
        label = description.getName();
      }
      
      String dialogTitle = "New "+label; //$NON-NLS-1$
      String dialogMessage = "Name:"; //$NON-NLS-1$
      Shell activeShell = Display.getDefault().getActiveShell();
      InputDialog representationNameDlg = new InputDialog(activeShell, dialogTitle, dialogMessage, defaultName, null);
      if (Window.OK == representationNameDlg.open()) {
        defaultName = representationNameDlg.getValue();
      } else {
        return;
      }
    }

    // Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
    // Executes the NewRepresentationCommand.
    NewScenarioRepresentationCommand command = new NewScenarioRepresentationCommand(defaultName, selectedEObject, description, session);
    TransactionHelper.getExecutionManager(session).execute(command);

    if (null != command.getRepresentation()) {
      SessionManager.INSTANCE.notifyRepresentationCreated(session);
      if (openRepresentation) {
        DialectUIManager.INSTANCE.openEditor(session, command.getRepresentation(), new NullProgressMonitor());
      }
    }
  }

  // The command allowing to create a new representation.
  private class NewScenarioRepresentationCommand extends AbstractReadWriteCommand {
    // The representation name.
    private String newName;
    // The new representation.
    private DRepresentation representation;

    // Fields.
    private RepresentationDescription repDescription;
    private Session currentSession;

    /**
     * Constructs the command allowing to create a new representation.
     * @param newName The new representation name.
     * @param eObject The selected EObject.
     * @param repDescription The current representation description.
     * @param session The current session.
     */
    public NewScenarioRepresentationCommand(String newName, AbstractCapability eObject, RepresentationDescription repDescription, Session session) {
      this.newName = newName;
      this.repDescription = repDescription;
      this.currentSession = session;
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
      representation = null;
    }

    /**
     * Gets the new representation.
     * @return The new representation.
     */
    public DRepresentation getRepresentation() {
      return representation;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("synthetic-access")
    public void run() {
      NullProgressMonitor monitor = new NullProgressMonitor();
      Scenario scenario = InteractionFactory.eINSTANCE.createScenario();
      scenario.setName(newName);
      selectedEObject.getOwnedScenarios().add(scenario);

      String eventName = "Create Representation";
      String eventContext = repDescription.getName();
      String addendum = IdManager.getInstance().getId(scenario);
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE, addendum);
      representation = DialectManager.INSTANCE.createRepresentation(newName, scenario, repDescription, currentSession, monitor);
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK, addendum);
    }
  }
}
