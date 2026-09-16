/*******************************************************************************
 * Copyright (c) 2017, 2026 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.data.migration.cmdline.MigrationCommandLine;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Common Test case the migration command line with some folders as input.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public abstract class CommandLineMigrationTestBase extends AbstractCommandLineTestBase {
  
  /** See extension in org.polarsys.capella.core.data.migration/plugin.xml. */
  private static final String COMMAND_ID = "org.polarsys.capella.migration";
  
  protected void launchMigration(String... arguments) throws CommandLineException {
    MigrationCommandLine migrationCmdLine = new MigrationCommandLine();
    
    var appContext = MockApplicationContext.createContext(COMMAND_ID, arguments);
    
    migrationCmdLine.parseContext(appContext);
    migrationCmdLine.checkArgs(appContext);
    migrationCmdLine.prepare(appContext);
    // Do not use "execute" directly as it will end the runtime.
    migrationCmdLine.migrateInputProjects(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
  }

  protected void openSession(String projectName) {
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(projectName);
    IFile airdFile = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
    Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor());
    
    assertTrue("Session of migrated project cannot be opened", session != null);
  }
  
}
