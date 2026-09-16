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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.DefaultCommandLine;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.handlers.RefreshDiagramsCommandHandler;
import org.polarsys.capella.core.sirius.ui.helper.ResourceHelper;

/**
 * Bash Command to migrate one or many Capella models.
 * <p>
 * Example:
 * <pre>
 * &lt;Capella Installation Path&gt;/capellac.exe -nosplash
 *   -application org.polarsys.capella.core.commandline.core
 *   -appid org.polarsys.capella.migration
 *   -import D:/model/sysmodel
 *   -input /all
 *   -exportTarget D:/tmp
 * </pre>
 */
public class MigrationCommandLine extends DefaultCommandLine {

  private Display display;
  private CommandLineException failure = null;
  
    @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {
    display = PlatformUI.createDisplay();

    PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {
      @Override
      public String getInitialWindowPerspectiveId() {
        return null;
      }

      @Override
      public void postStartup() {
        try {
          migrateInputProjects(display.getActiveShell());
        } catch (CommandLineException cle) {
          failure = cle;
        } finally {
          PlatformUI.getWorkbench().close();
        }
      }
    });

    return true;
  }

  @Override
  public void postExecute(IApplicationContext context) throws CommandLineException {
    if (failure != null) {
      throw failure;
    }
    super.postExecute(context);
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
     
      if (refreshViews) {
        refreshRepresentations(project);
      }
    }
  }

  private void refreshRepresentations(IProject project) {
    var airds = ResourceHelper.getAirdFilesToOpen(project);
    if (airds.isEmpty()) {
      logError("No representation file for " + project.getName()); //$NON-NLS-1$
      return;
    }
    // Note: Capella SessionHelper only provides opened session.
    // After migration, session is closed.
    // OpenSessionAction is not used because there is no need to open editors.
    var airdUri = URI.createPlatformResourceURI(airds.iterator().next().getFullPath().toString(), true);
    Session session = SessionManager.INSTANCE.getSession(airdUri, new NullProgressMonitor());
    var representations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    
    Job job = new RefreshDiagramsCommandHandler().new RefreshDiagramsJob(representations, session, Display.getDefault());
    job.setUser(false);
    job.schedule();
    try {
      job.join();
    } catch (InterruptedException e) {
      logError("Error while refreshing " + project.getName()); //$NON-NLS-1$
    }
    session.save(new NullProgressMonitor());
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
