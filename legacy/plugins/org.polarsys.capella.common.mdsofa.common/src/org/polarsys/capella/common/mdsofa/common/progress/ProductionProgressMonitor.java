/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Production progress monitor eases the usage of {@link SubMonitor} in patterns production or execution.<br>
 * Have a look at {@link SubMonitor}, {@link IProgressMonitor} documentation.
 */
public class ProductionProgressMonitor {
  /**
   * Number of ticks to allocate to this progress monitor.
   */
  private int _totalWork;
  /**
   * Internal Sub monitor.
   */
  private SubMonitor _monitor;

  /**
   * Return the underlying monitor.
   * @return the monitor
   */
  public SubMonitor getMonitor() {
    return _monitor;
  }

  /**
   * Create a progress monitor for given parameters.
   * @param parentMonitor_p the parent monitor for this progress monitor.
   * @param taskName_p the end-user task name displayed in progress reporter.
   * @param totalWork_p number of ticks to allocate
   */
  public ProductionProgressMonitor(SubMonitor parentMonitor_p, String taskName_p, int totalWork_p) {
    _totalWork = totalWork_p;
    _monitor = parentMonitor_p.newChild(_totalWork);
    _monitor.beginTask(ICommonConstants.EMPTY_STRING, _totalWork);
    _monitor.setTaskName(taskName_p);
  }

  /**
   * End this progress monitor.
   */
  public void end() {
    _monitor.worked(_totalWork);
    _monitor.setWorkRemaining(0);
  }
}
