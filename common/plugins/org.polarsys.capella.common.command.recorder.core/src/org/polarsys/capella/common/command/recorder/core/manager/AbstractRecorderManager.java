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

package org.polarsys.capella.common.command.recorder.core.manager;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.command.recorder.core.exception.RecorderException;
import org.polarsys.capella.common.command.recorder.core.manager.utils.IRecorderManagerConstants;
import org.polarsys.capella.common.command.recorder.core.manager.utils.RecorderManagerUtils;
import org.polarsys.capella.common.command.recorder.core.messages.RecorderMessages;
import org.polarsys.capella.common.command.recorder.core.output.DummyOutputManager;
import org.polarsys.capella.common.command.recorder.core.output.IOutputManager;
import org.polarsys.capella.common.command.recorder.core.output.OutputHelper;
import org.polarsys.capella.common.command.recorder.core.recorder.AbstractRecorder;
import org.polarsys.capella.common.command.recorder.core.recorder.IRecorder;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * Basic internal mechanisms implementation for RecorderManager.
 * Default "core" behavior is based on history operation listening.
 * Used recorders are based on {@link AbstractRecorder}.
 */
public abstract class AbstractRecorderManager implements IRecorderManager, IRecorderObserver, IOperationHistoryListener {
  
  
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
  
  /**
   * Internal class for history "data" storage.
   *
   */
  protected class HistoryListenerControl {
    
    /** Constructor */
    public HistoryListenerControl() {
      init();
    }    
    
    public void init() {
      
      _doublet = new int[]{
          IRecorderManagerConstants.NONE,
          IRecorderManagerConstants.NONE
      };
      
      _opInProgress = false;
      
      _operationLabel = RecorderMessages.abstractRecorderManager_unknownOperation_Lbl;

      return;
    }
        
    /** check whether start and stop operation are valid */
    public boolean isValid() {
      return _isValid;
    }
    
    /** Accessor on starting operation event */
    public int getStartingOperation() {
      return _doublet[START_IDX];
    }

    /** Accessor on closing operation event */
    public int getClosingOperation() {
      return _doublet[END_IDX];
    }
    
    /** For not ok ending operation.*/
    public void setToKo() {
      _doublet[END_IDX] = OperationHistoryEvent.OPERATION_NOT_OK;
    }
    
    /** Accessor on current operation label */
    public String getLabel() {
      return _operationLabel;
    }
    
    /** Accessor on operation date */
    public Date getDate() {
      return _date;
    }
    
    /** Write accessor on operation date */
    public void setDate(Date date) {
      _date = date;
      return;
    }
    
    /** Write accessor on current operation label */
    public void setLabel(final String label) {
      _operationLabel = label;
      return;
    }

    /** Check whether an operation is currently in progress */
    public boolean isOperationInProgress() {
      return _opInProgress;
    }
    
    /**
     * Start operation initialization
     * @param startEvent
     * @return <code>true</code> whether all is ok, <code>false</code> in case of wrong start event
     */
    public void operationStarted(final int startEvent) {
      
      if ( IRecorderManagerConstants.NONE == startEvent) {
        init();
      } else {
        int expectedFinishOperation = IRecorderManagerConstants.NONE;
        try {
          expectedFinishOperation = RecorderManagerUtils.getExpectedClosingEvent(startEvent);
        } catch (RecorderException exception) {
          _isValid = false;
          return;
        } 
        _doublet[START_IDX] = startEvent;
        _doublet[END_IDX] =  expectedFinishOperation;
        
      }
      
      _isValid = true;
      _opInProgress = true;
     
      return;
    }
    
    /** for internal use */
    protected int _doublet[];
    protected boolean _opInProgress;
    protected boolean _isValid;
    protected String _operationLabel;
    protected Date _date;
    
    protected static final int START_IDX = 0;
    protected static final int END_IDX = 1;
    
  }
  
  /** State of the Recorder */
  private boolean _isStarted;
  
  /** Operation history */
  protected IOperationHistory _history;
  
  /** internal control on history listener */
  protected HistoryListenerControl _hlc;
  
  /** The recorders */
  protected Set<AbstractRecorder> _recorders = null;

  /** file Manager */
  private IOutputManager _outputManager = null;
  
  /** Initialize recorder(s) plugged to this manager. 
   * Default implementation does nothing*/
  protected void initRecorders() {

    if (null == _recorders) {
      _recorders = new HashSet<AbstractRecorder>();
    } else {
      removeRecorders();
    }
        
    return;
  }

/**
 * Plug an {@link AbstractRecorder} to this recorder manager.
 * Does nothing whether the recorder has been already added. 
 * @param recorder the {@link AbstractRecorder} to add.
 * @param createNewRecord Should we force the creation of a new Record file
 */
  public void addRecorder(AbstractRecorder recorder, boolean createNewRecord) {
    
    _recorders.add(recorder);
    _outputManager.registerRecorder(recorder, createNewRecord);
    for (TransactionalEditingDomain editingDomain : ExecutionManagerRegistry.getInstance().getAllEditingDomains()) {
      editingDomain.addResourceSetListener(recorder);
    }
    SessionManager.INSTANCE.addSessionsListener(recorder);
  }

  /**
   * Remove an {@link AbstractRecorder} from this recorder manager.
   * @param recorder
   */
  public void removeRecorder(AbstractRecorder recorder) {
    _recorders.remove(recorder);
    _outputManager.removeRecorder(recorder);
    for (TransactionalEditingDomain editingDomain : ExecutionManagerRegistry.getInstance().getAllEditingDomains()) {
	  editingDomain.removeResourceSetListener(recorder);
	}
    SessionManager.INSTANCE.removeSessionsListener(recorder);
  }
  
  /** Remove all recorder(s) plugged to this manager */
  protected void removeRecorders() {
    // Remove listener from resourceSet
    while (!_recorders.isEmpty()) {
      removeRecorder(_recorders.iterator().next());
    }
    
    // Let's clear everything
    if (null != _recorders) {
      _recorders.clear();
    }
  }
  
  /** Constructor */
  public AbstractRecorderManager() {
    
    _isStarted = false;
    
    /** reference to the operation history */
    _history = OperationHistoryFactory.getOperationHistory();
    
    checkDirectory();
  }

  public boolean isStarted() {
    return _isStarted;
  }

  private void checkDirectory() {
   
    File file = OutputHelper.getDir(null);
    
    if (!file.exists()) {
      file.mkdir();
    }
  }
  
  /** Initialize the recorder state and data */
  protected void initOperation() {
    
    _hlc.init();
        
    clearRecorders();
  }
  
  /**
   * 
   * {@inheritDoc}
   */
  public void switchState() {
    try {
      if ( false == _isStarted ) {
        startup();
      } else {
        shutDown();
      }
    } catch (RecorderException exception) {
      // Do nothing
    }
  }
  
  /**
   * 
   * {@inheritDoc}
   */
  public void startup() throws RecorderException {
    
    if ( false == _isStarted) {
      
      _isStarted = true;
      // Registering as Operation history listener
      _history.addOperationHistoryListener(this);
      
      _hlc = new HistoryListenerControl();
      
      // output manager
      getOutputManager().init(); 
      
      // Initialize internal control
      initOperation();
      
      // Add recorders
      initRecorders();
      
      // clean the storage area on startup
      OutputHelper.cleanUpStorageAreaForRecords(_recorders);
      
    } else {
      throw new RecorderException(RecorderMessages.abstractRecorderManager_alreadyStarted);
    }
  }
  
  /** 
   * @see org.polarsys.capella.common.command.recorder.core.manager.IRecorderManager#shutDown()
   * @throws RecorderException
   */
  public void shutDown() throws RecorderException {
    
    if ( true == _isStarted) {
      
      _isStarted = false;
      
      // Remove listener(s)
      _history.removeOperationHistoryListener(this);
      
      // remove recorders
      removeRecorders();
      
      // clear output manager
      _outputManager.dispose();

    } else {
      throw new RecorderException(RecorderMessages.abstractRecorderManager_alreadyStopped);
    }
  }
  
  /**	
   * {@inheritDoc}
   */
  public void recorderChanged(IRecorder recorder, Object object) {
    if (! _hlc.isOperationInProgress() ) {
      _hlc.operationStarted(IRecorderManagerConstants.NONE);
      initOperation();
    }
  }
 
  /**
   * {@inheritDoc}
   */
  public void clearRecorders() {
    if ( null != _recorders ) {
      for (IRecorder recorder: _recorders) {
        recorder.clearRecords();
      }
    }
  }
  
  final public void setOutPutManager(IOutputManager outputManager) {
    if (null != outputManager) {
      _outputManager = outputManager;
    } else {
      _outputManager = getOutputManager();
    }
  }
  
  final public IOutputManager getOutputManager() {
    
    if ( null == _outputManager ) {
      _outputManager = new DummyOutputManager();
    }

    return _outputManager;
  }
  
  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  public void historyNotification(OperationHistoryEvent event) {

    IUndoableOperation op = event.getOperation();
    
    int eventType = event.getEventType();
    
    switch (eventType) {
      case OperationHistoryEvent.ABOUT_TO_EXECUTE:
      case OperationHistoryEvent.ABOUT_TO_UNDO:
      case OperationHistoryEvent.ABOUT_TO_REDO:
        
        // Case for previous unbounded changes caught...
        if ( _hlc.isOperationInProgress() ) {
          _hlc.setDate(new Date());
          write();
          initOperation();
        }
        
        // Common case
        
        _hlc.init();
        _hlc.operationStarted(eventType);
        _hlc.setDate(new Date());
        _hlc.setLabel(op.getLabel());
        
        
        if (! _hlc.isValid() ) {
          // event does not match, let's initialize op and clean current records
          initOperation();
          return;
        }
        
        break;
      case OperationHistoryEvent.DONE:
      case OperationHistoryEvent.UNDONE:
      case OperationHistoryEvent.REDONE:
      
        int expectedStartOps = -1;
        
        try {
          expectedStartOps = RecorderManagerUtils.getExpectedOpeningEvent(eventType);
        } catch (RecorderException exception) {
          // Wrong event caught, let's initialize op and clean current records
          initOperation();
          return;
        } 
        
        if (expectedStartOps != _hlc.getStartingOperation() ) {
          // Wrong event caught, let's initialize op and clean current records
          initOperation();
          return;
        }
        
        write();
        initOperation();
        break;
      case OperationHistoryEvent.OPERATION_NOT_OK:
        _hlc.setToKo();
        write();
        initOperation();
        
        break;
      default: 
        // Do nothing
    }
  }
  
  /** Write new operation to a given writer */
  abstract public void writeOperation(Writer writer) throws IOException;
  
  /**
   * {@inheritDoc}
   */
  public void write() {
    try {
      Writer writer = null;
      for (IRecorder recorder: _recorders) {
        if (!recorder.isEmpty()) {
          writer = _outputManager.getWriter(recorder);
          if (null == writer) { // disk access pb or other            
             __logger.info(RecorderMessages.abstractRecorderManager_cannotwrite);
             return;
          }
          writeOperation(writer);  
          recorder.write(writer);
          writer.flush();
        }
      }
    } catch (IOException exception) {
      __logger.info(RecorderMessages.abstractRecorderManager_cannotwrite);
    }
  }
}
 
