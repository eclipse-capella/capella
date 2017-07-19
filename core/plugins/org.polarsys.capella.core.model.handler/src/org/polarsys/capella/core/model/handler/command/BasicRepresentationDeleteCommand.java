/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * @author Joao Barata
 */
public class BasicRepresentationDeleteCommand extends RecordingCommand {
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  // The list of representation to delete.
  private Collection<DRepresentation> representations;

  private IProgressMonitor monitor;

  /**
   * Constructs the command allowing to delete representations.
   * @param domain
   * @param representations
   */
  public BasicRepresentationDeleteCommand(TransactionalEditingDomain domain, Collection<DRepresentation> representations, IProgressMonitor monitor) {
    super(domain, Messages.DeleteRepresentationCommand_DeleteRepresentationCommandLabel);
    this.representations = representations;
    this.monitor = monitor;
  }

  /**
   * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
   */
  @Override
  protected void doExecute() {
    if ((representations == null) || (representations.size() == 0)) {
      return;
    }

    try {
      monitor.beginTask(description, representations.size());
      for (DRepresentation representation : representations) {
        // Gets the session from the current representation.
        DSemanticDecorator semanticDecorator = (DSemanticDecorator) representation;

        if (representation.getName() != null) {
          monitor.setTaskName(NLS.bind(Messages.DeleteRepresentationCommand_DeleteRepresentationCommandText, representation.getName()));
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

          DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(session, representation);
          if (descriptor != null) {
            // Delete the current representation.
            if (DialectManager.INSTANCE.deleteRepresentation(descriptor, session)) {
              // Notify changes.
              SessionManager.INSTANCE.notifyRepresentationDeleted(session);
            }
          }
        } else {
          StringBuilder loggerMessage = new StringBuilder("DeleteRepresentationAction.DeleteRepresentationCommand.doExecute(..) _ "); //$NON-NLS-1$
          loggerMessage.append("unable to find a session for ").append(semanticDecorator.toString()); //$NON-NLS-1$
          logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
        }
        monitor.worked(1);
      }
    } finally {
      monitor.done();
    }
  }

  protected void closeActiveRepresentationEditor(DRepresentation representation, Session session) {
    // by default, do nothing
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
