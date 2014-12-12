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
package org.polarsys.capella.core.dashboard.hyperlinkadapter;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.AbstractHyperlink;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;

/**
 * Base class to implement a diagram creation.
 */
public abstract class AbstractNewDiagramHyperlinkAdapter extends AbstractHyperlinkAdapter {

  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DIAGRAM);

  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  @Deprecated
  public AbstractNewDiagramHyperlinkAdapter(Project capellaProject_p, Session session_p) {
    super(capellaProject_p, session_p);
  }

  /**
   * Constructor.
   * @param session_p
   */
  public AbstractNewDiagramHyperlinkAdapter(Session session_p) {
    super(session_p);
  }

  /**
   * Create a diagram based on {@link #getDiagramRepresentation()}, {@link #getModelElement(Project))} and the current session.<br>
   * Must be in the UI thread.
   */
  protected boolean createDiagram(final Project capellaProject_p, final Session session_p) {
    final boolean flag[] = { true };
    AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        //
        ModelElement modelElement = getModelElement(capellaProject_p);
        // Precondition
        if (null == modelElement) {
          flag[0] = false;
        } else {
          RepresentationDescription diagramRepresentation = getDiagramRepresentation(session_p, modelElement);
          // Preconditions
          if ((null == diagramRepresentation) || !DialectManager.INSTANCE.canCreate(modelElement, diagramRepresentation)) {
            flag[0] = false;
          } else {
            NewRepresentationAction newDiagramAction = new NewRepresentationAction(diagramRepresentation, modelElement, session_p);
            newDiagramAction.run();
            if (newDiagramAction.isCanceled()) {
              throw new OperationCanceledException(); // rollback
            }

          }
        }
      }

    };

    try {
      TransactionHelper.getExecutionManager(session_p).execute(cmd);
    } catch (OperationCanceledException e) {
      return true;
    }
    return true;
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

  /**
   * Create a diagram based on {@link #getDiagramRepresentation()}, {@link #getModelElement(Project))} and the current session.<br>
   * Must be in the UI thread.
   */
  protected boolean createNEWDiagram(final Project capellaProject_p, final Session session_p) {
    final boolean isAutorized[] = { false };
    //
    final ModelElement modelElement = getModelElement(capellaProject_p);
    final RepresentationDescription diagramRepresentation = getDiagramRepresentation(session_p, modelElement);

    // 1 - Computes the default representation name.
    String defaultName = modelElement != null ? computeDefaultName(modelElement, diagramRepresentation) : null;
    AbstractReadWriteCommand cmd = null;
    if (defaultName != null) {
      String dialogTitle = "Type representation name"; //$NON-NLS-1$
      Shell activeShell = Display.getDefault().getActiveShell();
      InputDialog representationNameDlg = new InputDialog(activeShell, dialogTitle, dialogTitle, defaultName, null);

      if (Window.CANCEL == representationNameDlg.open()) {

        isAutorized[0] = false;

      } else {
        defaultName = representationNameDlg.getValue();
        // Preconditions
        if ((null == diagramRepresentation) || !DialectManager.INSTANCE.canCreate(modelElement, diagramRepresentation)) {
          isAutorized[0] = false;
        } else {
          cmd = new AbstractReadWriteCommand() {
            @Override
            public void run() {
              isAutorized[0] = true;
              NewRepresentationAction newDiagramAction = new NewRepresentationAction(diagramRepresentation, modelElement, session_p);
              newDiagramAction.run();

            }
          };

        }
      }

    }

    if (isAutorized[0] && (cmd != null)) {
      TransactionHelper.getExecutionManager(session_p).execute(cmd);
    }

    return isAutorized[0];
  }

  /**
   * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#linkPressed(org.polarsys.capella.core.data.capellamodeller.Project,
   *      org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  protected void linkPressed(HyperlinkEvent event_p, Project capellaProject_p, Session session_p) {
    if (!createDiagram(capellaProject_p, session_p)) {
      handleDiagramCreationError(event_p, capellaProject_p);
    }
  }

  /**
   * Handle creation error.
   * @param event_p
   * @param capellaProject_p
   */
  protected void handleDiagramCreationError(HyperlinkEvent event_p, Project capellaProject_p) {
    String msg =
        Messages.AbstractNewDiagramHyperlinkAdapter_DiagramCreation_Error_Msg_Part1 + getDiagramName()
            + Messages.AbstractNewDiagramHyperlinkAdapter_DiagramCreation_Error_Msg_Part2;
    AbstractHyperlink widget = (AbstractHyperlink) event_p.widget;
    MessageDialog.openError(widget.getDisplay().getActiveShell(), widget.getText(), msg);
    _logger.error(new EmbeddedMessage(msg, IReportManagerDefaultComponents.DIAGRAM, capellaProject_p));
  }

  /**
   * Get the diagram representation for {@link #getDiagramName()} and specified parameters.
   * @return <code>null</code> if not found.
   */
  protected RepresentationDescription getDiagramRepresentation(Session session_p, ModelElement modelElement) {
    // Get active viewpoints.
    Collection<Viewpoint> activeViewpoints = session_p.getSelectedViewpoints(false);
    Collection<RepresentationDescription> diagramDescriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(activeViewpoints, modelElement);
    // Get search diagram name.
    String diagramName = getDiagramName();
    // Loop over retrieved diagram descriptions to search one matching search diagram name.
    for (RepresentationDescription diagramDescription : diagramDescriptions) {
      if (diagramName.equals(diagramDescription.getName())) {
        return diagramDescription;
      }
    }
    return null;
  }

  /**
   * @return
   */
  protected abstract String getDiagramName();
}
