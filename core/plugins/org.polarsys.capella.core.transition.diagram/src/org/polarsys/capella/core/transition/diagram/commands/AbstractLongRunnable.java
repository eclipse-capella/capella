/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.commands;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;

/**
 *
 */
public class AbstractLongRunnable implements IRunnableWithProgress {

  /**
   * {@inheritDoc}
   */
  @Override
  public final void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());

    try {
      init(monitor_p);
      performCommand(monitor_p);

    } finally {

      try {
        dispose(monitor_p);

      } finally {
        // Send long running operation events.
        // Operation has finished.
        LongRunningListenersRegistry.getInstance().operationEnded(getClass());
      }
    }
  }

  /**
   * @param monitor_p
   */
  protected void init(IProgressMonitor monitor_p) {

  }

  /**
   * 
   */
  protected void dispose(IProgressMonitor monitor_p) {
    //Do nothing
  }

  /**
   * @param monitor_p 
   * 
   */
  protected void performCommand(IProgressMonitor monitor_p) {
    //Do nothing
  }

}
