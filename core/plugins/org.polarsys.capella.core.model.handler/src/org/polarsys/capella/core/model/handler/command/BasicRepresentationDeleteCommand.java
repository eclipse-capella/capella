/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * @author Joao Barata
 */
public class BasicRepresentationDeleteCommand extends RecordingCommand {
  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  // The list of representation to delete.
  private Collection<DRepresentationDescriptor> descriptors;

  private IProgressMonitor monitor;

  /**
   * Constructs the command allowing to delete representations.
   * 
   * @param domain
   * @param descriptors
   */
  public BasicRepresentationDeleteCommand(TransactionalEditingDomain domain,
      Collection<DRepresentationDescriptor> descriptors, IProgressMonitor monitor) {
    super(domain, Messages.DeleteRepresentationCommand_DeleteRepresentationCommandLabel);
    this.descriptors = descriptors;
    this.monitor = monitor;
  }

  /**
   * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
   */
  @Override
  protected void doExecute() {
    if ((descriptors == null) || (descriptors.size() == 0)) {
      return;
    }

    try {
      monitor.beginTask(description, descriptors.size());
      for (DRepresentationDescriptor descriptor : descriptors) {
        monitor.setTaskName(
            NLS.bind(Messages.DeleteRepresentationCommand_DeleteRepresentationCommandText, descriptor.getName()));
        Session session = null;
        EObject targetElement = descriptor.getTarget();
        if (null != targetElement) {
          session = SessionManager.INSTANCE.getSession(targetElement);
        }
        // Be robust !! (it is useful when diagram has no target)
        if (null == session) {
          session = getSession(descriptor.eResource());
        }
        if (null != session) {
          // Closes the related opened editors.
          closeActiveRepresentationEditor(descriptor, session);

          // Delete the current representation.
          if (DialectManager.INSTANCE.deleteRepresentation(descriptor, session)) {
            // Notify changes.
            SessionManager.INSTANCE.notifyRepresentationDeleted(session);
          }

        } else {
          StringBuilder loggerMessage = new StringBuilder(
              "DeleteRepresentationAction.DeleteRepresentationCommand.doExecute(..) _ "); //$NON-NLS-1$
          loggerMessage.append("unable to find a session for ").append(descriptor.toString()); //$NON-NLS-1$
          logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
        }

        monitor.worked(1);
      }
    } finally {
      monitor.done();
    }
  }

  protected void closeActiveRepresentationEditor(DRepresentationDescriptor representation, Session session) {
    // by default, do nothing
  }

  /**
   * Get a session from a representation resource.
   * 
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
