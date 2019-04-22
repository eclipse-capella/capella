/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 */
public class CapellaDataListenerForPropertySections {

  /**
   * Constant identifying the job family identifier for the background refresh Properties view job.
   */
  private static final String REFRESH_VIEW_JOB_FAMILY = "RefreshPropertiesViewJob"; //$NON-NLS-1$

  private Set<TabbedPropertySheetPage> pages;

  public void refresh() {
    if (!getPages().isEmpty()) {
      scheduleRefreshPropertiesViewJob();
    }
  }

  /**
   * @return {@code TRUE} if at least one contributor to the extension point {@code delegatedCapellaListener} wants the
   *         given notification {@code notification} to be filtered, and {@code FALSE} otherwise.
   */

  /**
   * 
   */
  public void registerPropertySheetPage(TabbedPropertySheetPage page) {
    if (!getPages().contains(page)) {
      getPages().add(page);
    }
  }

  /**
   * 
   */
  public void unregisterPropertySheetPage(TabbedPropertySheetPage page) {
    getPages().remove(page);
  }

  /**
   * 
   */
  public Set<TabbedPropertySheetPage> getPages() {
    if (null == pages) {
      pages = new HashSet<>();
    }
    return pages;
  }

  private void scheduleRefreshPropertiesViewJob() {
    Job[] jobs = Job.getJobManager().find(REFRESH_VIEW_JOB_FAMILY);
    if (jobs.length == 0) {
      new RefreshPropertiesViewJob().schedule();
    } else {
      // Waiting and Sleeping jobs can't be rescheduled. Only running jobs can be rescheduled when done. We add an
      // else to handle notifications coming during the job running time.
      jobs[0].schedule();
    }
  }

  private class RefreshPropertiesViewJob extends UIJob {

    public RefreshPropertiesViewJob() {
      super(Display.getDefault(), Messages.PropertyView_JobName);
    }

    @Override
    public boolean belongsTo(Object family) {
      return REFRESH_VIEW_JOB_FAMILY.equals(family);
    }

    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
      for (TabbedPropertySheetPage page : getPages()) {
        page.refresh();
        page.labelProviderChanged(new LabelProviderChangedEvent(new BaseLabelProvider(), null));
      }
      return Status.OK_STATUS;
    }
  }
}
