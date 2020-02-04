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
package org.polarsys.capella.core.platform.sirius.ui.navigator.commandline;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.Messages;

public class ExportRepresentationsCommandLine extends AbstractCommandLine {

  public ExportRepresentationsCommandLine() {
    argHelper = new ExportRepresentationsArgumentHelper();
  }

  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {

    ExportRepresentationsArgumentHelper args = (ExportRepresentationsArgumentHelper) argHelper;

    String fileURI = Messages.resource_prefix + args.getFilePath();
    URI uri = URI.createURI(fileURI);

    Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());

    if (session == null) {
      throw new CommandLineException("No aird model found!"); //$NON-NLS-1$
    }

    session.open(new NullProgressMonitor());

    if (CapellaResourceHelper.isAirdResource(uri)) {
      Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);

      IPath outputPath = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(args.getOutputFolder()))
          .getLocation();
      ImageFileFormat imageFormat = ImageFileFormat.resolveImageFormat(args.getImageFormat());
      boolean exportToHtml = args.getExportToHtml();
      boolean exportDecorations = args.getExportDecorations();
      ExportAction exportRepresentationsAction = new ExportAction( //
          session, representations, outputPath, imageFormat, exportToHtml, exportDecorations);
      try {
        exportRepresentationsAction.run(new NullProgressMonitor());
      } catch (InvocationTargetException | InterruptedException e1) {
        e1.printStackTrace();
      }
    }

    return false;
  }
}
