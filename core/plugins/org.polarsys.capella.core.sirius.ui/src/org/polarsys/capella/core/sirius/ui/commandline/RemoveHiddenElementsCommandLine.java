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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
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

    List<IFile> airdFiles = getAirdFilesFromInput();
    for (IFile file : airdFiles) {
      URI uri = EcoreUtil2.getURI(file);

      Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
      if (session == null) {
        return new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getBundle().getSymbolicName(), "No aird model found!");
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

    }

    return Status.OK_STATUS;
  }
  
  @Override
  public void printHelp() {
    super.printHelp();
    printArgumentsFromTable("removeHiddenElementsParameters", false, Collections.emptyList());
  }
}
