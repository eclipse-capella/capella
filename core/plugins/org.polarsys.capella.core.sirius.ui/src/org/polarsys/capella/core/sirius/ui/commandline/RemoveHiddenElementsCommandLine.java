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

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.core.sirius.ui.handlers.DeleteHiddenElementsJob;

public class RemoveHiddenElementsCommandLine extends AbstractWorkbenchCommandLine {

  public RemoveHiddenElementsCommandLine() {
    super(false);
    argHelper = new RemoveHiddenElementsArgumentHelper();
  }

  @Override
  protected IStatus executeWithinWorkbench() {

    RemoveHiddenElementsArgumentHelper args = (RemoveHiddenElementsArgumentHelper) argHelper;
    boolean unsyncDiags = args.getUnsyncDiags();

    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(argHelper.getFilePath()));
    URI uri = EcoreUtil2.getURI(file);
    
    Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
    if (session == null) {
      return new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(), "No aird model found!");
    }
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }
    if (CapellaResourceHelper.isAirdResource(uri)) {
      Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
          .getAllRepresentationDescriptors(session);

      DeleteHiddenElementsJob job = new DeleteHiddenElementsJob(representations, session, unsyncDiags);
      job.schedule();
      try {
        job.join();
      } catch (InterruptedException e) {
        return Status.CANCEL_STATUS;
      }
      session.save(new NullProgressMonitor());
      try {
        session.close(new NullProgressMonitor());
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return Status.OK_STATUS;
  }

}
