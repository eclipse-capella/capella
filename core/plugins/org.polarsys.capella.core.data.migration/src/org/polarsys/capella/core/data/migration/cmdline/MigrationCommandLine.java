/*******************************************************************************
 * Copyright (c) 2006, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.cmdline;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.commandline.RefreshAirdCommandLine;

/**
 * Bash Command to migrate one or many Capella models.
 * <p>
 * Example:
 * <pre>
 * &lt;Capella Installation Path&gt;/capellac.exe -nosplash
 *   -application org.polarsys.capella.core.commandline.core
 *   -appid org.polarsys.capella.migration
 *   -importAll D:/model/sysmodel
 *   -input /all
 * </pre>
 */
public class MigrationCommandLine extends RefreshAirdCommandLine {

  public MigrationCommandLine() {
    super();
  }

  @Override
  protected IStatus executeWithinWorkbench() {
    try {
      migrateInputProjects(Display.getCurrent().getActiveShell());
    } catch (CommandLineException e) {
      logger.error(e.getMessage(), e);
    }
    if (argHelper.isRefreshRepresentations()) {
      super.executeWithinWorkbench();
    }
    return Status.OK_STATUS;
  }
  
  /**
   * Migrates the projects listed in Command line arguments.
   * 
   * @param shell the current display
   * @throws CommandLineException if projects cannot be migrated
   */
  public void migrateInputProjects(Shell shell) throws CommandLineException {
    Set<IProject> capellaProjects = getProjectsFromInput().stream()
        .filter(CapellaResourceHelper::isCapellaProject)
        .collect(Collectors.toSet());
    
    migrateProjects(shell, capellaProjects, argHelper.isBackupNeeded(), argHelper.isRefreshRepresentations());
  }

  /**
   * Migrates provided projects.
   * <p>
   * Also migrates projects in dependencies.
   * </p>
   * 
   * @param shell current Shell
   * @param capellaProjects projects to migrate.
   * @param backup flag to backup content
   * @param refreshViews flag to refresh diagrams.
   * @throws CommandLineException if the migration is not possible due to Project dependencies
   */
  public void migrateProjects(Shell shell, Collection<? extends IProject> capellaProjects, boolean backup, boolean refreshViews) throws CommandLineException {
    for (IProject project : new MigrationOrchestration(capellaProjects).getSequence()) {
       MigrationHelpers.getInstance().trigger(project, shell, true/*in job*/, true/*no confirmation*/, 
           backup, false, MigrationConstants.DEFAULT_KIND_ORDER);
    }
  }
  
  @Override
  public void printHelp() {
    super.printHelp();
    printArgumentsFromTable("migrationParameters", false, Collections.emptyList()); //$NON-NLS-1$
  }
  
  @Override
  public void compliancyCheck(IFile modeller) throws CommandLineException {
    // No check for compliancy for Migration command line since it's done already in Migration command
  }
}
