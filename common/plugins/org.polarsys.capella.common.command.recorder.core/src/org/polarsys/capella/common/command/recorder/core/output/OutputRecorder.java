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
package org.polarsys.capella.common.command.recorder.core.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polarsys.capella.common.command.recorder.core.recorder.IRecorder;

/**
 *
 */
public class OutputRecorder implements IOutputManager {
  
  /** for internal use */
  private Map<IRecorder, OutputData> _map;
  
  /** for internal use */
  protected class OutputData {
    
    private File _file = null;
    private Writer _writer = null;
    
    /** Accessor */
    public File getFile() {
      return _file;
    }
    
    /** Accessor */
    public Writer getWriter() {
      return _writer;
    }
    
    /** 
     * Ensure that the Writer is well closed, if exists
     */
    public void clear() {
      if ( null != _writer) {
        try {
          _writer.close();
        } catch (IOException exception_p) {
          // Do nothing
        }
        _writer = null;
      }
      return;
    }

    /** Write accessor */
    public void setFile(File file_p) {
      _file = file_p;
      return;
    }
    
    /** set writer */
    public void setWriter() {
      
      if (null != _file) {
        try {
          
          if (null != _writer) {
            _writer.close();
          }
          
          OutputStream stream = new FileOutputStream(_file, true);
          _writer = OutputHelper.logForStream(stream);
        } catch (FileNotFoundException exception_p) {
          //Do nothing
        } catch (IOException exIoException_p) {
          //Do nothing
        }
      }
      
      return;
    }
    
  }
  
  /**
   * Constructor
   */
  public OutputRecorder() {
    _map = new HashMap<IRecorder, OutputRecorder.OutputData>();
  }
  
  /**
   * {@inheritDoc}
   */
  public Writer getWriter(IRecorder recorder_p) {
   
    boolean shouldChangeOfFile = false;
    
    OutputData data = null;
    if ( _map.containsKey(recorder_p) ) {
      data = _map.get(recorder_p);
    } else {
      data = new OutputData();
      _map.put(recorder_p, data);
    }
    
    if ( null == data.getWriter() ) {
      shouldChangeOfFile = true;
    } else if ( null != data.getFile() && OutputHelper.isOverSized(data.getFile()) ) {
      shouldChangeOfFile = true;
    }
    
    if (shouldChangeOfFile) {
        data.setFile(OutputHelper.createNewLogFile(recorder_p));
        data.setWriter();
    }
    
    return data.getWriter();
  }

  /**
   * {@inheritDoc}
   */
  public void registerRecorder(IRecorder recorder_p, boolean isNewRecordFileShouldBeCreated_p) {
    
    // Check directory
    File dir = OutputHelper.getDir(recorder_p);
    if ( !dir.exists() ) {
      dir.mkdir();
    }

    OutputData data = null;
    
    if (_map.containsKey(recorder_p)) {
      removeRecorder(recorder_p);
    }
    
    data = new OutputData();
    _map.put(recorder_p, data);
    
    
    // Initialize the file that is going to be used
    if ( true == isNewRecordFileShouldBeCreated_p ) {
      data.setFile(OutputHelper.createNewLogFile(recorder_p));
    } else {
      List<File> recordFiles = OutputHelper.getRecordFiles(recorder_p);
      File file = null;
      if (!recordFiles.isEmpty() ) {
        File lastAvailable = recordFiles.get(0);
        // check whether this file is ok
        if (
            OutputHelper.isOverSized(lastAvailable) ||
            OutputHelper.isOutOfTime(lastAvailable, null)
        ) {
          file = OutputHelper.createNewLogFile(recorder_p);
        } else {
          file = recordFiles.get(0);
        }
      } else {
        file = OutputHelper.createNewLogFile(recorder_p);
      }
      data.setFile(file);
      data.setWriter();
    }
    
    return;
  }
  
  /**
   * {@inheritDoc}
   */
  public void removeRecorder(IRecorder recorder_p) {
    
    OutputData data = _map.get(recorder_p);
    
    if ( null != data ) {
      data.clear();
      _map.remove(recorder_p);
    }
    
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void init() {
    // Do nothing
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void dispose() {
    
    for (OutputData data: _map.values()) {
      data.clear();
    }
    _map.clear();
    
    return;
  }
  
}
