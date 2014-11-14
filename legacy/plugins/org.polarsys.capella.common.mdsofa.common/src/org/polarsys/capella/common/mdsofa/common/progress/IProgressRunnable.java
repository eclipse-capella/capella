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

/**
 * Runnable that is reporting its progress through a {@link IProgressMonitor}.
 */
public interface IProgressRunnable {
  /**
   * A run method, that can take any action.<br>
   * 
   * @param progressMonitor_p
   * @return true if runnable ended successfully, false otherwise.
   */
  public boolean run(IProgressMonitor progressMonitor_p);

  /**
   * Get reporting title.
   * @return
   */
  public String getReportingTitle();
}
