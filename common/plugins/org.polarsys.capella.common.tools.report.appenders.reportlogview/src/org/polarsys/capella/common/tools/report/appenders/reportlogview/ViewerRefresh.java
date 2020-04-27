/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * A helper to perform a delayed refresh of a viewer. 
 * 
 * This is useful if a model sends a lot of notifications that would normaly 
 * be handled with a call to viewer.refresh(). By using this class, you can
 * repeatedly call its refresh() method which will schedule a
 * call to viewer.refresh() after a delay period. If you call refresh() again
 * during such a delay period, the delay is prolonged 
 * 
 * This class is thread safe. 
 * It is possible to call refresh() from different threads.
 */
class ViewerRefresh implements Runnable, IViewerRefresh {

    /* internal scheduler */
    private final ScheduledExecutorService scheduler;

    /* the future result of refresh() */
    private ScheduledFuture<?> future;
    
    /* Wait for another delay period after the current period ends? */
    protected boolean reschedule;
    
    /* The marker view */
    final protected MarkerView view;
    
    /* refresh delay */
    final protected long delay; 
    
    /* delay time units */
    final protected TimeUnit timeunit;

    ViewerRefresh(ScheduledExecutorService service, MarkerView view, long delay, TimeUnit timeunit){
      this.scheduler = service;
      this.view = view;
      this.delay = delay;
      this.timeunit = timeunit;
    }
    
    ViewerRefresh(MarkerView view, long delay, TimeUnit timeunit){
      this(Executors.newScheduledThreadPool(1), view, delay, timeunit);
    }

    
    /**
     * Call this to trigger a viewer refresh after delay.
     */
    public synchronized void refresh() {
      if (future == null || future.isDone()){
        reschedule = false;
        future = scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
      } else {
        reschedule = true;
      }
    }

    /**
     * Called by the internal executor. Not to be called by clients.
     * {@inheritDoc}
     */
    public synchronized void run() {
      if (reschedule){
        reschedule = false;
        future = scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
      } else {
        Display d = PlatformUI.getWorkbench().getDisplay();
        d.asyncExec(new Runnable(){
          public void run(){
            BusyIndicator.showWhile(null, new Runnable(){
              public void run(){
              if ((view != null) && !view.getViewer().getTree().isDisposed()) {
                try {
                  view.getViewer().getTree().setRedraw(false);
                  view.getViewer().refresh();
                } finally {
                  view.getViewer().getTree().setRedraw(true);
                }
              }
              }
            });
          }
        }); 
      }
    }
}


