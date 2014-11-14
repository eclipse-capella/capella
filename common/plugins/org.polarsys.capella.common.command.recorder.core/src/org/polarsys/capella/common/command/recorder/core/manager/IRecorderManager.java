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
package org.polarsys.capella.common.command.recorder.core.manager;

import org.polarsys.capella.common.command.recorder.core.exception.RecorderException;

/**
 * Interface for Recorder Manager.
 */
public interface IRecorderManager {
  
  /**
   * Startup method
   * @throws RecorderException 
   */
  public void startup() throws RecorderException;
  
  /**
   * Shutdown method
   * @throws RecorderException 
   */
  public void shutDown() throws RecorderException;
  
  /**
   * Switch current state of the Recorder manager.
   */
  public void switchState();
  
  /**
   * Write Recorders data.
   */
  public void write();
  
  /**
   * Clear all recorders.
   */
  public void clearRecorders();
  
}
