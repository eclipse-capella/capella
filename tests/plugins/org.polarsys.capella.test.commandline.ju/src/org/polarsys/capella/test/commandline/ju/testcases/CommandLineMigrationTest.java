/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.commandline.ju.testcases;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.data.migration.cmdline.MigrationCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public abstract class CommandLineMigrationTest extends BasicTestCase {
  protected void launchApplication(IApplicationContext mockApplicationContext, MigrationCommandLine migrationCmdLine)
      throws CommandLineException {
    migrationCmdLine.parseContext(mockApplicationContext);
    migrationCmdLine.checkArgs(mockApplicationContext);
    migrationCmdLine.prepare(mockApplicationContext);
    migrationCmdLine.setRefreshPrefs();
    migrationCmdLine.migrateAllImportedProjects(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
  }
  
  protected void openSession(String projectName) {
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(projectName);
    IFile airdFile = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
    Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor());
    
    assertTrue("Session of migrated project cannot be opened", session != null);
  }
}
