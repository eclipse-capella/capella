/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.util.IJobConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;
import org.polarsys.capella.test.platform.ju.Activator;

/**
 * This test ensures that jobs are properly logged into both Error Log and Information views
 */
public class JobLogTest extends BasicTestCase {

  private class MyJob extends Job {
    IStatus status;

    MyJob(int severity, boolean log) {
      super(MyJob.class.getSimpleName());
      this.status = new Status(severity, Activator.getDefault().getBundle().getSymbolicName(), "severity:" + severity);
      if (log) {
        setProperty(IJobConstants.ALWAYS_LOG_STATUS, true);
      }
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
      return status;
    }
  }

  protected void launch(int severity, boolean log) {
    MyJob job = new MyJob(severity, log);
    job.schedule();
    try {
      job.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void test() throws Exception {
    loggingJobs();
    normalJobs();
  }

  public void loggingJobs() {
    cleanInformationView();
    StatusValidator validator = new StatusValidator(s -> s.getMessage().contains("severity:"));
    Platform.addLogListener(validator);

    launch(IStatus.OK, true);
    launch(IStatus.INFO, true);
    launch(IStatus.WARNING, true);
    launch(IStatus.ERROR, true);
    launch(IStatus.CANCEL, true);

    GuiActions.flushASyncGuiThread();

    // Ensure that results are logged once into Information view
    assertTrue(LightMarkerRegistry.getInstance().getMarkers().size() == 5);
    Platform.removeLogListener(validator);

    // Error and warning are logged once into Error Log
    assertTrue(validator.getMatchedStatuses().size() == 5);
  }

  public void normalJobs() {
    cleanInformationView();
    StatusValidator validator = new StatusValidator(s -> s.getMessage().contains("severity:"));
    Platform.addLogListener(validator);

    launch(IStatus.OK, false);
    launch(IStatus.INFO, false);
    launch(IStatus.WARNING, false);
    launch(IStatus.ERROR, false);
    launch(IStatus.CANCEL, false);

    GuiActions.flushASyncGuiThread();

    // Default jobs errors are not logged into Information View. This is a known issue
    assertTrue(LightMarkerRegistry.getInstance().getMarkers().size() == 0);
    Platform.removeLogListener(validator);

    // Error and warning are logged once into Error Log
    assertTrue(validator.getMatchedStatuses().size() == 2);
  }

  private void cleanInformationView() {
    new ArrayList<IMarker>(LightMarkerRegistry.getInstance().getMarkers()).stream().forEach(m -> {
      try {
        m.delete();
      } catch (CoreException e) {
        // Nothing here
      }
    });
  }

}
