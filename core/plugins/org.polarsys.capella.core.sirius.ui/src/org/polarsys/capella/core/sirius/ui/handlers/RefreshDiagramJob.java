/*******************************************************************************
 * Copyright (c) 2020, 2026 THALES GLOBAL SERVICES.
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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class RefreshDiagramJob extends Job {

  IFile file;

  public RefreshDiagramJob(IFile file) {
    super(NLS.bind(Messages.RefreshDiagramJob_Name, file.getName()));
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
    try {
      if (!openSession(session, monitor)) {
        return Status.CANCEL_STATUS;
      }
      Project capellaProject = SessionHelper.getCapellaProject(session);
      RefreshDiagramsCommandHandler handler = new RefreshDiagramsCommandHandler();
      Job job = handler.new RefreshDiagramsJob(handler.getSubRepresentations(capellaProject, session), session,
          Display.getDefault());
      job.setUser(true);
      job.schedule();
      job.join();
      return job.getResult();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return Status.CANCEL_STATUS;
    }
  }

  private boolean openSession(Session session, IProgressMonitor monitor) throws InterruptedException {
    CountDownLatch sessionOpeningFinished = new CountDownLatch(1);
    SessionListener listener = changeKind -> {
      if (changeKind == SessionListener.OPENED || changeKind == SessionListener.CLOSED) {
        sessionOpeningFinished.countDown();
      }
    };
    // Register before opening so synchronous OPENED notifications are also received.
    session.addListener(listener);
    try {
      if (session.isOpen()) {
        return !monitor.isCanceled();
      }
      session.open(monitor);
      while (!monitor.isCanceled()) {
        if (sessionOpeningFinished.await(100, TimeUnit.MILLISECONDS)) {
          return session.isOpen() && !monitor.isCanceled();
        }
      }
      return false;
    } finally {
      session.removeListener(listener);
    }
  }

  public IFile getFile() {
    return file;
  }

}
