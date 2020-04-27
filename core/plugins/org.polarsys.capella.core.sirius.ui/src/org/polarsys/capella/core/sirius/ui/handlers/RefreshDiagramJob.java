/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class RefreshDiagramJob extends Job {

  IFile file;

  public RefreshDiagramJob(IFile file) {
    super(NLS.bind("Refresh diagrams on {0}", file.getName()));
    this.file = file;
    this.setUser(false);
  }

  @Override
  public boolean belongsTo(Object family) {
    return family.getClass().equals(RefreshDiagramJob.class);
  }

  @Override
  public IStatus run(IProgressMonitor monitor) {
    URI selectedUri = EcoreUtil2.getURI(file);
    Session session = SessionManager.INSTANCE.getSession(selectedUri, monitor);
    if (!session.isOpen()) {
      session.open(monitor);
    }
    Project capellaProject = SessionHelper.getCapellaProject(session);
    RefreshDiagramsCommandHandler handler = new RefreshDiagramsCommandHandler();
    Job job = handler.new RefreshDiagramsJob(handler.getSubRepresentations(capellaProject, session), session,
        Display.getDefault());
    job.setUser(true);
    job.schedule();
    try {
      job.join();
    } catch (InterruptedException e) {
      return Status.CANCEL_STATUS;
    }
    return job.getResult();
  }

  public IFile getFile() {
    return file;
  }

}
