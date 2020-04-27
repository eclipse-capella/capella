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
package org.polarsys.capella.core.sirius.ui.commandline;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.commandline.core.ui.CloseWorkbenchJob;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramJob;

public class RefreshAirdCommandLine extends AbstractWorkbenchCommandLine {

  public RefreshAirdCommandLine() {
    super(true);
  }

  protected IStatus executeWithinWorkbench() {
    List<IFile> airdFiles = getAirdFilesFromInput();
    List<Job> jobs = new ArrayList<>();
    for (IFile file : airdFiles) {
      jobs.add(new RefreshDiagramJob(file));
    }
    for (int i = 0; i < jobs.size(); i++) {
      final int index = i;
      Job currentJob = jobs.get(i);
      currentJob.addJobChangeListener(new JobChangeAdapter() {

        @Override
        public void done(IJobChangeEvent event) {
          Job eventJob = event.getJob();
          if (eventJob instanceof RefreshDiagramJob) {
            RefreshDiagramJob job = (RefreshDiagramJob) eventJob;
            IFile file = job.getFile();
            URI selectedUri = EcoreUtil2.getURI(file);
            Session session = SessionManager.INSTANCE.getSession(selectedUri, new NullProgressMonitor());
            session.save(new NullProgressMonitor());
            try {
              session.close(new NullProgressMonitor());
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (index < jobs.size() - 1) {
              Job nextJob = jobs.get(index + 1);
              nextJob.schedule();
            } else {
              if (PlatformUI.getTestableObject() == null || PlatformUI.getTestableObject().getTestHarness() == null) {
                new CloseWorkbenchJob().schedule();
              }
            }
          }
        }
      });
    }
    if (!jobs.isEmpty()) {
      Job entryJob = jobs.get(0);
      entryJob.schedule();
    }
    return Status.OK_STATUS;
  }
}
