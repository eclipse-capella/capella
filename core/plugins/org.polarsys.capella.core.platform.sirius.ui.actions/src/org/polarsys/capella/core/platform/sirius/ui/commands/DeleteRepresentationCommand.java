/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 */
public class DeleteRepresentationCommand extends RecordingCommand {
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  // The list of representation to delete.
  private Collection<DRepresentation> _representations;

  private IProgressMonitor _monitor;

  /**
   * Constructs the command allowing to delete representations.
   * @param domain
   * @param representations
   */
  public DeleteRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentation> representations) {
    this(domain, representations, new NullProgressMonitor());
  }

  public DeleteRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentation> representations, IProgressMonitor monitor) {
    super(domain, Messages.DeleteRepresentationCommand_DeleteRepresentationCommandLabel);
    _representations = representations;
    _monitor = monitor;
  }

  /**
   * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
   */
  @Override
  protected void doExecute() {
    if ((_representations == null) || (_representations.size() == 0)) {
      return;
    }

    try {
      _monitor.beginTask(description, _representations.size());
      for (DRepresentation representation : _representations) {
        // Gets the session from the current representation.
        DSemanticDecorator semanticDecorator = (DSemanticDecorator) representation;

        if (representation.getName() != null) {
          _monitor.setTaskName(NLS.bind(Messages.DeleteRepresentationCommand_DeleteRepresentationCommandText, representation.getName()));
        }
        Session session = null;
        EObject targetElement = semanticDecorator.getTarget();
        if (null != targetElement) {
          session = SessionManager.INSTANCE.getSession(targetElement);
        }
        // Be robust !!
        if (null == session) {
          session = getSession(semanticDecorator.eResource());
        }
        if (null != session) {
          // Closes the related opened editors.
          closeActiveRepresentationEditor(representation, session);
          // Delete the current representation.
          if (DialectManager.INSTANCE.deleteRepresentation(representation, session)) {
            // Notify changes.
            SessionManager.INSTANCE.notifyRepresentationDeleted(session);
          }
        } else {
          StringBuilder loggerMessage = new StringBuilder("DeleteRepresentationAction.DeleteRepresentationCommand.doExecute(..) _ "); //$NON-NLS-1$
          loggerMessage.append("unable to find a session for ").append(semanticDecorator.toString()); //$NON-NLS-1$
          __logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
        }
        _monitor.worked(1);
      }
    } finally {
      _monitor.done();
    }
  }

  // Closes the opened editor about the current representation.
  private void closeActiveRepresentationEditor(DRepresentation representation, Session session) {
    IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
    if (null == editingSession) {
      return;
    }
    DialectEditor editor = editingSession.getEditor(representation);
    if (editor != null) {
      DialectUIManager.INSTANCE.closeEditor(editor, false);
      // We detach the editor here because sometimes cause of the asyncExec of the closing, the detach could not be done during the dispose of the editor
      editingSession.detachEditor(editor);
    }
  }

  /**
   * Get a session from a representation resource.
   * @param airdResource
   * @return
   */
  private Session getSession(Resource airdResource) {
    Session result = null;
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      Collection<Resource> allAnalysisResources = new HashSet<Resource>(session.getReferencedSessionResources());
      allAnalysisResources.add(session.getSessionResource());
      if (allAnalysisResources.contains(airdResource)) {
        result = session;
        break;
      }
    }
    return result;
  }
}
