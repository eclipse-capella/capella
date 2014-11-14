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
package org.polarsys.capella.common.ui.progress.api;

/**
 * This interface provide some progress services. Basically, this interface provides an abstraction level over the platform progress widgets. For example, in
 * Eclipse, this interface provides an abstraction over progress monitors.
 * 
 * 
 */
public interface IProgressHandler {
  /**
   * Begins progress reporting about a task
   */
  public void beginTask(String taskName, int amountOfWork);

  /**
   * Tells the progress handler that the given amount of work has been done
   * @param amountDone
   */
  public void worked(int amountDone);

  /**
   * Tells the progress handler that the reported task has been done.
   */
  public void done();
}
