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

package org.polarsys.capella.core.platform.sirius.ui.project;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.MonitorFileSyncJob;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences.IMonitorFileSyncPreferences;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences.PreferencesInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class CapellaProjectActivator extends AbstractUIActivator implements IStartup {
  // The shared instance
  private static CapellaProjectActivator __plugin;

  private MonitorFileSyncJob monitorFileSyncJob = new MonitorFileSyncJob();

  /**
   * The constructor
   */
  public CapellaProjectActivator() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __plugin = this;
    // Call here to avoid weird exceptions.
    scheduleMonitoring();
    new PreferencesInitializer();
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __plugin = null;
    super.stop(context_p);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static CapellaProjectActivator getDefault() {
    return __plugin;
  }

  /**
   * Schedule file monitoring.
   */
  void scheduleMonitoring() {
    boolean enabled = CapellaProjectActivator.getDefault().getPreferenceStore().getBoolean(IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING);
    if (enabled) {
      monitorFileSyncJob.addChangeAdapter();
      monitorFileSyncJob.schedule();
    }
    addPreferenceListenerForTheMonitor();
  }

  /**
   * @param monitorFileSyncJob
   */
  private void addPreferenceListenerForTheMonitor() {
    CapellaProjectActivator.getDefault().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals(IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING)) {
          boolean enabled =
              CapellaProjectActivator.getDefault().getPreferenceStore().getBoolean(IMonitorFileSyncPreferences.PREFERENCE_ENABLE_FILE_SYNC_MONITORING);
          if (enabled) {
            monitorFileSyncJob.addChangeAdapter();
            monitorFileSyncJob.schedule();
          } else {
            monitorFileSyncJob.removeChangeAdapter();
            monitorFileSyncJob.cancel();
          }
        }
      }
    });

  }

  /**
   * {@inheritDoc}
   */
  public void earlyStartup() {
    // Do nothing here, just to make Eclipse happy.
  }
}
