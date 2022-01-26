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

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.Messages;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

public class ExportRepresentationsCommandLine extends AbstractWorkbenchCommandLine {

  public ExportRepresentationsCommandLine() {
    argHelper = new ExportRepresentationsArgumentHelper();
  }

  @Override
  protected IStatus executeWithinWorkbench() {
    ExportRepresentationsArgumentHelper args = (ExportRepresentationsArgumentHelper) argHelper;
    List<IFile> airdFiles = getAirdFilesFromInput();
    for (IFile file : airdFiles) {
      URI uri = EcoreUtil2.getURI(file);

      Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
      if (session == null) {
        return Status.error("No aird model found!");
      }
      if (!session.isOpen()) {
        session.open(new NullProgressMonitor());
      }

      if (CapellaResourceHelper.isAirdResource(uri)) {
        Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);
        IPath outputPath = getOrCreateOutputFolderForAird(uri).getLocation();
        ImageFileFormat imageFormat = ImageFileFormat.resolveImageFormat(args.getImageFormat());
        boolean exportToHtml = args.getExportToHtml();
        boolean exportDecorations = args.getExportDecorations();
        ExportAction exportRepresentationsAction = new ExportAction( //
            session, representations, outputPath, imageFormat, exportToHtml, exportDecorations);
        try {
          exportRepresentationsAction.run(new NullProgressMonitor());

        } catch (InterruptedException e1) {
          return Status.CANCEL_STATUS;

        } catch (InvocationTargetException e) {
          return Status.error(e.getMessage(), e);

        } catch (Exception e) {
          return Status.error(e.getMessage(), e);
        }
      }

    }

    return Status.OK_STATUS;

  }
  
  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    super.checkArgs(context);
    if (argHelper.getOutputFolder() == null) {
      logger.error(Messages.outputfolder_mandatory);
    }
  }
  
  @Override
  public void printHelp() {
    super.printHelp(Arrays.asList("outputfolder"));
    printArgumentsFromTable("exportRepresentationsParameters", false, Collections.emptyList());
  }
}
