/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * The action allowing to create new representations.
 */
public class NewRepresentationAction extends BaseSelectionListenerAction {
  private EObject selectedEObject;
  protected RepresentationDescription description;
  protected Session session;

  protected boolean forceDefaultName;
  protected boolean openRepresentation;
  private boolean isCanceled;

  /**
   * Constructs an action allowing to create new representations.
   * 
   * @param description
   *          The representation description.
   * @param selectedEObject
   *          The selected EObject.
   * @param session
   *          The current session.
   */
  public NewRepresentationAction(RepresentationDescription description, EObject selectedEObject, Session session) {
    this(description, selectedEObject, session, false, true);
  }

  /**
   * Constructs an action allowing to create new representations.
   * 
   * @param description
   *          The representation description.
   * @param selectedEObject
   *          The selected EObject.
   * @param session
   *          The current session.
   * @param forceDefaultName
   * @param openRepresentation
   */
  public NewRepresentationAction(RepresentationDescription description, EObject selectedEObject, Session session,
      boolean forceDefaultName, boolean openRepresentation) {
    super(description.getName());
    String label = description.getLabel();
    if ((null != label) && (label.length() > 1)) {
      setText(label);
    }
    ImageDescriptor imageDescriptor = null;
    
    // Handle specific representations : Table ones.
    if (description instanceof CrossTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID,
          "/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$
    
    } else if (description instanceof EditionTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/DTable.gif"); //$NON-NLS-1$
    
    } else if (description instanceof SequenceDiagramDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.sirius.diagram.sequence.edit", //$NON-NLS-1$
          "/icons/full/obj16/TSequenceDiagram.gif"); //$NON-NLS-1$
    
    } else {
      // Standard diagram.
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramUIPlugin.ID,
          "/icons/full/obj16/DDiagram.gif"); //$NON-NLS-1$
    }
    
    if (null == imageDescriptor) {
      imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setImageDescriptor(imageDescriptor);

    // Registers local fields.
    this.selectedEObject = selectedEObject;
    this.description = description;
    this.session = session;
    this.forceDefaultName = forceDefaultName;
    this.openRepresentation = openRepresentation;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // 1 - Computes the default representation name.
    String defaultName = computeDefaultName(selectedEObject, description);

    if (!forceDefaultName) {

      String label = description.getLabel();
      if (label == null || label.isEmpty()) {
        label = description.getName();
      }

      String dialogTitle = "New " + label; //$NON-NLS-1$
      String dialogMessage = "Name:"; //$NON-NLS-1$
      Shell activeShell = Display.getDefault().getActiveShell();
      InputDialog representationNameDlg = new InputDialog(activeShell, dialogTitle, dialogMessage, defaultName, null);
      isCanceled = Window.CANCEL == representationNameDlg.open();
      if (!isCanceled) {
        defaultName = representationNameDlg.getValue();
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

  // Gets the default representation name.
  protected String computeDefaultName(EObject eObject, RepresentationDescription repDescription) {
    // Gets the interpreter.
    IInterpreter interpreter = InterpreterUtil.getInterpreter(eObject);

    // Computes new representation name.
    String newName = "New "; //$NON-NLS-1$
    if (!StringUtil.isEmpty(repDescription.getLabel())) {
      newName += repDescription.getLabel();
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

  // The command allowing to create a new representation.
  private class NewRepresentationCommand extends AbstractReadWriteCommand {
    // The representation name.
    private String newName;
    // The new representation.
    private DRepresentation representation;

    // Fields.
    private EObject eObject;
    private RepresentationDescription repDescription;
    private Session currentSession;

    /**
     * Constructs the command allowing to create a new representation.
     * 
     * @param newName
     *          The new representation name.
     * @param eObject
     *          The selected EObject.
     * @param repDescription
     *          The current representation description.
     * @param session
     *          The current session.
     */
    public NewRepresentationCommand(String newName, EObject eObject, RepresentationDescription repDescription,
        Session session) {
      this.newName = newName;
      this.eObject = eObject;
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
     * 
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
      if (selectedEObject instanceof Scenario) {
        Scenario scenario = (Scenario) selectedEObject;
        scenario.setName(newName);
      }

      String eventName = "Create Representation";
      String eventContext = repDescription.getName();
      String addendum = IdManager.getInstance().getId(eObject);
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE, addendum);
      representation = DialectManager.INSTANCE.createRepresentation(newName, eObject, repDescription, currentSession,
          monitor);
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK, addendum);
    }
  }

  public boolean isCanceled() {
    return isCanceled;
  }

  public void setCanceled(boolean isCanceled) {
    this.isCanceled = isCanceled;
  }

}
