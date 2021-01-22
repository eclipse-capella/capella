/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.DefaultCommandLine;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * <p>
 * Implementation of Command in order to migrate one or many Capella models.<br/>
 * </p>
 * <p>
 * Example: <br/>
 * <em>CapellaInstallationPath</em>/eclipse.exe -nosplash <br/>
 * -application org.polarsys.capella.core.commandline.core <br/>
 * -appid org.polarsys.capella.migration <br/>
 * -import D:/model/sysmodel <br/>
 * -filepath model <br/>
 * -outputfolder d:/tmp <br/>
 * </p>
 */
public class MigrationCommandLine extends DefaultCommandLine {

  private Display display;

  /**
   * {@inheritDoc}
   */
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
        super.postStartup();

        migrateAllImportedProjects(display.getActiveShell());

        PlatformUI.getWorkbench().close();
      }
    });

    return true;
  }

  public void migrateAllImportedProjects(Shell shell) {
    Set<IProject> capellaProjects = getProjectsFromInput().stream().filter(CapellaResourceHelper::isCapellaProject)
        .collect(Collectors.toSet());
    for (IProject project : capellaProjects) {
      try {
        if (argHelper.isBackupNeeded()) {
          // Migrate Project
          MigrationHelpers.getInstance().trigger(project, shell, true, false, MigrationConstants.DEFAULT_KIND_ORDER);
        } else {
          MigrationHelpers.getInstance().trigger(project, shell, true, true, false, false,
              MigrationConstants.DEFAULT_KIND_ORDER);
        }
      } catch (Exception e) {
        logError("Error during migration of " + project.getName());
      }
    }
  }

  @Override
  public void printHelp() {
    super.printHelp();
    printArgumentsFromTable("migrationParameters", false, Collections.emptyList());
  }
  
  @Override
  public void compliancyCheck(IFile modeller) throws CommandLineException {
    // No check for compliancy for Migration command line since it's done already in Migration command
  }
}
