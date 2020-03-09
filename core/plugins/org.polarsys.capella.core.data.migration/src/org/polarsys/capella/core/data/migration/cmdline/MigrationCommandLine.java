/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  private boolean initialValue_RefreshOnOpening;
  private boolean initialValue_AutoRefresh;

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
        resetRefreshPrefs();

        PlatformUI.getWorkbench().close();
      }
    });

    return true;
  }

  public void migrateAllImportedProjects(Shell shell) {
    for (IProject project : getProjectsFromInput()) {
      try {
        if (argHelper.isBackupNeeded()) {
          // Migrate Project
          MigrationHelpers.getInstance().trigger(project, shell, true, false, MigrationConstants.DEFAULT_KIND_ORDER);
        } else {
          MigrationHelpers.getInstance().trigger(project, shell, true, true, false, false, MigrationConstants.DEFAULT_KIND_ORDER);
        }
      } catch (Exception e) {
        logError("Error during migration of " + project.getName());
      }
    }
  }

  /**
   * Set the refresh preference to true for diagrams
   *
   */
  public void setRefreshPrefs() {
    IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
    initialValue_RefreshOnOpening = preferenceStore
        .getBoolean(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name());
    preferenceStore = SiriusEditPlugin.getPlugin().getCorePreferenceStore();
    initialValue_AutoRefresh = preferenceStore.getBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name());

    doSetSiriusPrefs(true, true);

    return;
  }

  private void resetRefreshPrefs() {
    doSetSiriusPrefs(initialValue_RefreshOnOpening, initialValue_AutoRefresh);
  }

  private void doSetSiriusPrefs(boolean refreshOnOpening, boolean autoRefresh) {
    IEclipsePreferences siriusUIPluginPreferences = InstanceScope.INSTANCE.getNode(SiriusEditPlugin.ID);
    siriusUIPluginPreferences.putBoolean(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(),
        refreshOnOpening);

    IEclipsePreferences siriusPluginPreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
    siriusPluginPreferences.putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefresh);
  }
}
