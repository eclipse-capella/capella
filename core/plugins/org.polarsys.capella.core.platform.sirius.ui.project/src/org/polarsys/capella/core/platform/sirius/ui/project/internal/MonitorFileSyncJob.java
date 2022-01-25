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
package org.polarsys.capella.core.platform.sirius.ui.project.internal;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.team.core.RepositoryProvider;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.project.CapellaProjectActivator;
import org.polarsys.capella.core.platform.sirius.ui.project.internal.preferences.IMonitorFileSyncPreferences;

/**
 */
public class MonitorFileSyncJob extends RunInWorkspaceJob {
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  JobChangeAdapter adapter;

  /**
   * Constructor
   */
  public MonitorFileSyncJob() {
    super("Monitor File Sync Job"); //$NON-NLS-1$
    setSystem(true);
    adapter = new JobChangeAdapter() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void done(IJobChangeEvent event_p) {
        long delay = IMonitorFileSyncPreferences.FILE_SYNC_MONITORING_DEFAULT_DELAY;

        CapellaProjectActivator activator = CapellaProjectActivator.getDefault();
        // this can happen when Capella is shutting down
        if (null != activator) {
          IPreferenceStore prefStore = activator.getPreferenceStore();
          if (null != prefStore) {
            delay = AbstractPreferencesInitializer.getInt(IMonitorFileSyncPreferences.PREFERENCE_FILE_SYNC_MONITORING_DELAY, false);
          }
        }
        schedule(1000 /* 1s */* delay);

      }
    };
  }

  public void addChangeAdapter() {
    addJobChangeListener(adapter);
  }

  public void removeChangeAdapter() {
    removeJobChangeListener(adapter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus runInWorkspace(final IProgressMonitor monitor_p) {

    IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
    try {
      workspaceRoot.accept(new IResourceVisitor() {
        /**
         * {@inheritDoc}
         */
        public boolean visit(IResource resource_p) throws CoreException {
          boolean cr = true;
          switch (resource_p.getType()) {
            case IResource.PROJECT:
              // Visit project's members, if current project is controlled by a SCM.
              cr = (null != RepositoryProvider.getProvider((IProject) resource_p));
              if (cr) {
                resynchronize(monitor_p, resource_p);
              }
            break;
            case IResource.FOLDER:
              // Let's assume, current folder is contained by a project controlled by a SCM.
              resynchronize(monitor_p, resource_p);
            break;
            case IResource.FILE:
              // Let's assume, current file is contained by a project controlled by a SCM.
              cr = false; // no
              resynchronize(monitor_p, resource_p);
            break;
          }
          return cr;
        }
      });
    } catch (CoreException e) {
      // TODO Use logger?
      e.printStackTrace();
    }
    return Status.OK_STATUS;
  }

  /**
   * Re-synchronize if needed.
   * @param monitor_p
   * @param resource_p
   * @throws CoreException
   */
  protected void resynchronize(final IProgressMonitor monitor_p, IResource resource_p) throws CoreException {
    if (!resource_p.isSynchronized(IResource.DEPTH_ZERO)) {
      // Force a refresh to re-synchronize the resource.
      resource_p.refreshLocal(IResource.DEPTH_ZERO, monitor_p);
      String message = resource_p.getFullPath().toString() + " resynchronized from local file system"; //$NON-NLS-1$
      Platform.getLog(CapellaProjectActivator.class).log(new Status(IStatus.INFO, FrameworkUtil.getBundle(CapellaProjectActivator.class).getSymbolicName(), message, null));
      if (__logger.isInfoEnabled()) {
        __logger.info(new EmbeddedMessage(message, IReportManagerDefaultComponents.UI));
      }
    }
  }
}
