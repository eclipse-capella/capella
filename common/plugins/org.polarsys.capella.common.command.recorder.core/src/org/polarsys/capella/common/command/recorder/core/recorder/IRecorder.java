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

package org.polarsys.capella.common.command.recorder.core.recorder;

import java.io.IOException;
import java.io.Writer;

/**
 * Interface for Recorder.
 *
 */
public interface IRecorder {
  
  /** clear all the data stored inside this recorder */
  public void clearRecords();
  
  /** check whether recorder has stored any data */
  public boolean isEmpty();
  
  /** return the relative path from root recorder path used to persist this Recorder data*/
  public String getRelativePath();
  
  /** write recorders data to an output stream */
  public void write(Writer writer) throws IOException;
  
  /** Extra data linked to this writer */
  public String getExtra();
  
}
