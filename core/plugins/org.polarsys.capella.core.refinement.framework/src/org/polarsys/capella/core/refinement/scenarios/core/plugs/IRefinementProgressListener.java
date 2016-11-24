/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.scenarios.core.plugs;

/**
 */
public interface IRefinementProgressListener {

  /**
   * Set the name of the job that will be listened
   *
   * @param jobName
   */
  public void setJobName(String jobName);

  /**
   * Set the job duration and show the window
   *
   * @param finalValue
   */
  public void setProgressEnd(int finalValue);

  /**
   * Notify the job progression
   *
   * @param stepValue
   */
  public void notifyProgress(int stepValue);

  /**
   * Notify that the job is finished and destroy the window
   */
  public void notifyEnd();
}
