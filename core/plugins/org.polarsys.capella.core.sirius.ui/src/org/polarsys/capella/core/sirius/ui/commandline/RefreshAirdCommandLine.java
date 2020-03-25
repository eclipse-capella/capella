/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.commandline;

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

  @Override
  protected IStatus executeWithinWorkbench() {
    List<IFile> airdFiles = getAirdFilesFromInput();
    for (IFile file : airdFiles) {
      Job job = new RefreshDiagramJob(file);
      job.addJobChangeListener(new JobChangeAdapter() {

        @Override
        public void done(IJobChangeEvent event) {
          URI selectedUri = EcoreUtil2.getURI(file);
          Session session = SessionManager.INSTANCE.getSession(selectedUri, new NullProgressMonitor());
          session.save(new NullProgressMonitor());
          try {
            session.close(new NullProgressMonitor());
          } catch (Exception e) {
            e.printStackTrace();
          }
          if (PlatformUI.getTestableObject() == null || PlatformUI.getTestableObject().getTestHarness() == null) {
            new CloseWorkbenchJob().schedule();
          }
        }
      });

      job.schedule();

    }
    if (!airdFiles.isEmpty()) {
      Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFiles.get(0)),
          new NullProgressMonitor());
      session.save(new NullProgressMonitor());
    }
    return Status.OK_STATUS;
  }

}
