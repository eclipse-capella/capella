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
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * The action allowing to create new representations.
 */
public class NewRepresentationAction extends BaseSelectionListenerAction {
  private EObject _selectedEObject;
  protected RepresentationDescription _description;
  protected Session _session;

  protected boolean _forceDefaultName;
  protected boolean _openRepresentation;
  private boolean isCanceled;

 
/**
   * Constructs an action allowing to create new representations.
   * @param description_p The representation description.
   * @param selectedEObject_p The selected EObject.
   * @param session_p The current session.
   */
  public NewRepresentationAction(RepresentationDescription description_p, EObject selectedEObject_p, Session session_p) {
    this(description_p, selectedEObject_p, session_p, false, true);
  }

  /**
   * Constructs an action allowing to create new representations.
   * @param description_p The representation description.
   * @param selectedEObject_p The selected EObject.
   * @param session_p The current session.
   * @param forceDefaultName_p
   * @param openRepresentation_p
   */
  public NewRepresentationAction(RepresentationDescription description_p, EObject selectedEObject_p, Session session_p, boolean forceDefaultName_p,
      boolean openRepresentation_p) {
    super(description_p.getName());
    String label = description_p.getLabel();
    if ((null != label) && (label.length() > 1)) {
      setText(label);
    }
    ImageDescriptor imageDescriptor = null;
    // Handle specific representations : Table ones.
    if (description_p instanceof CrossTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$
    } else if (description_p instanceof EditionTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/DTable.gif"); //$NON-NLS-1$
    } else {
      // Standard diagram.
      imageDescriptor = SiriusUIPlugin.getDefault().getImageDescriptor("newDiagram.png"); //$NON-NLS-1$
    }
    if (null == imageDescriptor) {
      imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setImageDescriptor(imageDescriptor);

    // Registers local fields.
    _selectedEObject = selectedEObject_p;
    _description = description_p;
    _session = session_p;
    _forceDefaultName = forceDefaultName_p;
    _openRepresentation = openRepresentation_p;
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
      isCanceled = Window.CANCEL == representationNameDlg.open() ;
      if (!isCanceled ) {
        defaultName = representationNameDlg.getValue();
      } else {
        return;
      }
    }

    // Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
    // Executes the NewRepresentationCommand.
    NewRepresentationCommand command = new NewRepresentationCommand(defaultName, _selectedEObject, _description, _session);
    MDEAdapterFactory.getExecutionManager().execute(command);

    if (null != command.getRepresentation()) {
      SessionManager.INSTANCE.notifyRepresentationCreated(_session);
      if (_openRepresentation) {
        DialectUIManager.INSTANCE.openEditor(_session, command.getRepresentation(), new NullProgressMonitor());
      }
    }
  }

  // Gets the default representation name.
  protected String computeDefaultName(EObject eObject_p, RepresentationDescription repDescription_p) {
    // Gets the interpreter.
    IInterpreter interpreter = InterpreterUtil.getInterpreter(eObject_p);

    // Computes new representation name.
    String newName = "New "; //$NON-NLS-1$
    if (!StringUtil.isEmpty(repDescription_p.getLabel())) {
      newName += repDescription_p.getLabel();
    } else {
      newName += repDescription_p.getName();
    }

    String titleExpression = repDescription_p.getTitleExpression();
    if (!StringUtil.isEmpty(titleExpression)) {
      try {
        newName = interpreter.evaluateString(eObject_p, titleExpression);
      } catch (EvaluationException e) {
        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
      }
    }

    return newName;
  }

  // The command allowing to create a new representation.
  private class NewRepresentationCommand extends AbstractReadWriteCommand {
    // The representation name.
    private String _newName;
    // The new representation.
    private DRepresentation _representation;

    // Fields.
    private EObject _eObject;
    private RepresentationDescription _repDescription;
    private Session _currentSession;

    /**
     * Constructs the command allowing to create a new representation.
     * @param newName_p The new representation name.
     * @param eObject_p The selected EObject.
     * @param repDescription_p The current representation description.
     * @param session_p The current session.
     */
    public NewRepresentationCommand(String newName_p, EObject eObject_p, RepresentationDescription repDescription_p, Session session_p) {
      _newName = newName_p;
      _eObject = eObject_p;
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
      if (_selectedEObject instanceof Scenario) {
        Scenario scenario = (Scenario) _selectedEObject;
        scenario.setName(_newName);
      }

      _representation = DialectManager.INSTANCE.createRepresentation(_newName, _eObject, _repDescription, _currentSession, monitor);
    }
  }
  
  	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled_p) {
		this.isCanceled = isCanceled_p;
	}

}
