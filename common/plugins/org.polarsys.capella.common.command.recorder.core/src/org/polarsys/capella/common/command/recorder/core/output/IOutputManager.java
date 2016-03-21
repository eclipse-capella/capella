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

package org.polarsys.capella.common.command.recorder.core.output;

import java.io.Writer;

import org.polarsys.capella.common.command.recorder.core.recorder.IRecorder;

/**
 * Interface for output Manager
 *
 */
public interface IOutputManager {

  /** Return the writer for a given recorder */
  public Writer getWriter(IRecorder recorder);
  
  /** Add recorder to the manager */
  public void registerRecorder(IRecorder recorder, boolean isNewRecordFileShouldBeCreated);
  
  /** Remove recorder */
  public void removeRecorder(IRecorder recorder);

  /** init data */
  public void init();
  
  /** dispose method */
  public void dispose();
  
}
