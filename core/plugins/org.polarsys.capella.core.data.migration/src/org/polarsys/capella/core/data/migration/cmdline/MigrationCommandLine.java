/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.cmdline;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.CommandLineMode;
import org.polarsys.capella.core.commandline.core.Messages;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;

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
public class MigrationCommandLine extends AbstractCommandLine {

  private Display display;

  @Override
  public boolean projectVersionIsCompliant() throws CommandLineException {
    // FIXME: 'filepath'and'outputfolder' are not used. Maybe they mustn'tbe mandatory in CommandLine
    return true;
  }

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

        setRefreshPrefs();
        migrateAllImportedProjects(display.getActiveShell());

        PlatformUI.getWorkbench().close();
      }

    });

    return true;
  }

  public void migrateAllImportedProjects(Shell shell) {
    for (String projectName : getImportedProjects()) {
      try {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        // Migrate Project
        MigrationHelpers.getInstance().trigger(project, shell, true, false,
            MigrationConstants.DEFAULT_KIND_ORDER);
      } catch (Exception e) {
        logError("Error during migration of " + projectName);
      }

    }
  }

  /**
   * Set the refresh preference to true for diagrams
   *
   */
  public void setRefreshPrefs() {

    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();

    preferenceStore.setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    preferenceStore.setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

    return;
  }
  
  @Override
  public void checkArgs(IApplicationContext context) throws CommandLineException {
    // refreshing the workspace needed in case of folders removed from outside the workbench
    try {
      ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    } catch (CoreException exception) {
      logErrorAndThrowException(Messages.refresh_problem);
    }

    if (isEmtyOrNull(argHelper.getImportProjects())) {
      logErrorAndThrowException(Messages.representation_mandatory);
    } else 
      setMode(CommandLineMode.IMPORT);

  }
}
