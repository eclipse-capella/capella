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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.handlers.DeleteHiddenElementsJob;

public class RemoveHiddenElementsCommandLine extends AbstractWorkbenchCommandLine {
  
  public RemoveHiddenElementsCommandLine() {
    super();
    argHelper = new RemoveHiddenElementsArgumentHelper();
  }
  
  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {
    startWorkbench();
    
    RemoveHiddenElementsArgumentHelper args = (RemoveHiddenElementsArgumentHelper) argHelper;
    
    String outputFolder = args.getOutputFolder();
    boolean unsyncDiags = args.getUnsyncDiags();
    
    String fileURI = Messages.resource_prefix + argHelper.getFilePath();
    URI uri = URI.createURI(fileURI);

    try {
      // SessionManager.INSTANCE.getSession() do not load the IsModifiedSavingPolicy class
      // that set the isModified flag to true, thus the session is never saved.
      // To avoid this, use the SessionFactory.INSTANCE.createSession() method instead.
      Session session = SessionFactory.INSTANCE.createSession(uri, new NullProgressMonitor());

      if (session == null) {
        throw new CommandLineException("No aird model found!"); //$NON-NLS-1$
      }
      
      session.open(new NullProgressMonitor());
      
      if (CapellaResourceHelper.isAirdResource(uri)) {
        Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
            .getAllRepresentationDescriptors(session);

        DeleteHiddenElementsJob job = new DeleteHiddenElementsJob(representations, session, unsyncDiags);
        IStatus status = job.run(new NullProgressMonitor());
        
        session.save(new NullProgressMonitor());

        try {
          IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder));
          if (!folder.exists()) {
            folder.create(false, true, new NullProgressMonitor());
          }
          String fileName = Messages.removeHiddenElementsResultsFileName;
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
          e.printStackTrace();
        }
      }
    } catch (CoreException e1) {
      // TODO: handle exception
      e1.printStackTrace();
    }
    return false;
  }
  
  private String toHTML(IStatus status, Collection<DRepresentationDescriptor> representations) {
    StringBuilder res = new StringBuilder();
    res.append("<html> \n"); //$NON-NLS-1$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<title>Remove Hidden Elements on " + new java.util.Date() + "</title> \n"); //$NON-NLS-1$ //$NON-NLS-2$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<body> \n"); //$NON-NLS-1$
    if (status.getSeverity() == IStatus.OK) {
      res.append("All hidden elements was removed from "+representations.size()+" representation(s)."); //$NON-NLS-1$
    } else {
      res.append("The removal of all hidden elements failed."); //$NON-NLS-1$
    }
    res.append("</body> \n"); //$NON-NLS-1$
    res.append("</html> \n"); //$NON-NLS-1$
    return res.toString();
  }
}
