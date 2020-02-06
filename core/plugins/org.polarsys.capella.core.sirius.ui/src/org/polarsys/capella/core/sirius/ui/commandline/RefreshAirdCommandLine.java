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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramsCommandHandler;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramsCommandHandler.RefreshDiagramsJob;

public class RefreshAirdCommandLine extends AbstractWorkbenchCommandLine {

  public RefreshAirdCommandLine() {
    argHelper = new CommandLineArgumentHelper();
  }

  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {
    startWorkbench();
    
    String outputFolder = argHelper.getOutputFolder();

    String fileURI = argHelper.getFilePath();
    URI uri = URI.createPlatformResourceURI(fileURI, false);

    Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());

    if (session == null) {
      throw new CommandLineException("No aird model found!"); //$NON-NLS-1$
    }

    if (CapellaResourceHelper.isAirdResource(uri)) {
      Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
          .getAllRepresentationDescriptors(session);

      RefreshDiagramsCommandHandler handler = new RefreshDiagramsCommandHandler();
      RefreshDiagramsJob job = handler.new RefreshDiagramsJob( //
          Messages.RefreshRepresentation_0, representations, session, Display.getDefault());
      IStatus status = job.run(new NullProgressMonitor());
      
      try {
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder));
        if (!folder.exists()) {
          folder.create(false, true, new NullProgressMonitor());
        }
        String fileName = Messages.refreshResultsFileName;
        IFile file = folder.getFile(new Path(fileName));
        String result = toHTML(status, representations);
        InputStream outputContent = new ByteArrayInputStream(result.getBytes());
        if (file.exists()) {
          file.setContents(outputContent, true, false, null);
        } else {
          file.create(outputContent, false, null);
        }
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    return false;
  }
  
  private String toHTML(IStatus status, Collection<DRepresentationDescriptor> representations) {
    StringBuilder res = new StringBuilder();
    res.append("<html> \n"); //$NON-NLS-1$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<title>Refresh all representations on " + new java.util.Date() + "</title> \n"); //$NON-NLS-1$ //$NON-NLS-2$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<body> \n"); //$NON-NLS-1$
    if (status.getSeverity() == IStatus.OK) {
      res.append("All "+representations.size()+" representations refreshed."); //$NON-NLS-1$
    } else {
      res.append("The refresh of all representations failed."); //$NON-NLS-1$
    }
    res.append("</body> \n"); //$NON-NLS-1$
    res.append("</html> \n"); //$NON-NLS-1$
    return res.toString();
  }
}
