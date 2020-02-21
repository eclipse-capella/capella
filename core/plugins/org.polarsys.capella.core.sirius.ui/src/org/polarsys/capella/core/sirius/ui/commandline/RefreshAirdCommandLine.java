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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.commandline.core.ui.CloseWorkbenchJob;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramJob;

public class RefreshAirdCommandLine extends AbstractWorkbenchCommandLine {

  public RefreshAirdCommandLine() {
    super(true);
  }
  
  protected IStatus executeWithinWorkbench() {
    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(argHelper.getFilePath()));
    Job job = new RefreshDiagramJob(file);
    job.addJobChangeListener(new JobChangeAdapter() {

      @Override
      public void done(IJobChangeEvent event) {
        URI selectedUri = EcoreUtil2.getURI(file);
        Session session = SessionManager.INSTANCE.getSession(selectedUri, new NullProgressMonitor());
        session.save(new NullProgressMonitor());
        session.close(new NullProgressMonitor());
        new CloseWorkbenchJob().schedule();
      }

    });
    job.schedule();
    return Status.OK_STATUS;
  }

}
