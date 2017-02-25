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

package org.polarsys.capella.core.transition.common.launcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.capella.core.transition.common.handlers.log.DefaultLogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.ILogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.transposer.ExtendedTransposer;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransposerLauncher extends ActivitiesLauncher {

  private final String PURPOSE = "org.polarsys.capella.core.transition"; //$NON-NLS-1$
  private final String MAPPING = "org.polarsys.capella.core.transition.defaultMapping"; //$NON-NLS-1$

  protected ExtendedTransposer _transposer;

  protected ILoopActivityDispatcher _dispatcher;

  protected ExtendedTransposer getTransposer() {
    return _transposer;
  }

  protected String getPurpose() {
    return PURPOSE;
  }

  protected String getMapping() {
    return MAPPING;
  }

  protected ExtendedTransposer createTransposer(String purpose, String mappingId) {
    return new ExtendedTransposer(purpose, mappingId) {
      @Override
      public void initCadence() {
        cadenceLauncher = _cadenceLauncher;
      }
    };
  }

  public ILoopActivityDispatcher createDispatcher() {
    return null;
  }

  /**
   * @return
   */
  public ILoopActivityDispatcher getDispatcher() {
    if (_dispatcher == null) {
      _dispatcher = createDispatcher();
    }
    return _dispatcher;
  }

  /**
   * Should not raise any exception since they will be logged in the default log component
   */
  protected void initializeLogHandler() {
    ILogHandler handler = new DefaultLogHandler(getReportComponent());
    handler.init(TransitionContext.EMPTY_CONTEXT);
    LogHelper.setInstance(handler);
  }

  /**
   * 
   */
  @Override
  protected void dispose() {
    if (_transposer != null) {
      _transposer.dispose();
    }

    LogHelper.getInstance().flush();
    LogHelper.dispose();

    super.dispose();
    _transposer = null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.action.Action#run()
   */
  public void run(Collection<?> selection1, boolean save, IProgressMonitor monitor) {
    List<Object> selection = new ArrayList<Object>();
    selection.addAll(selection1);
    launch(selection, getPurpose(), getMapping(), monitor);
  }

  
  @Override
  public void launch(Collection<?> selection, String purpose, String mappingId, IProgressMonitor monitor) {

    try {
      initializeLogHandler();

      _transposer = createTransposer(purpose, mappingId);

      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_INSTANCE, _transposer);
      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_SELECTION, selection);
      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_PURPOSE, purpose);
      _transposer.getContext().put(ITransitionConstants.COMMAND_NAME, getName());
      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_MAPPING, mappingId);
      _transposer.getContext().put(ITransposerWorkflow.TRANSPOSER_ANALYSIS_SOURCES, new ArrayList<Object>());

      initializeParameters();
      
      triggerActivities(selection, getWorkflow(), monitor);

    } catch (OperationCanceledException e) {
      processCancel();
      throw e;

    } catch (TransitionException e) {
      LogHelper.getInstance().error(e.getMessage(), Messages.Activity_Transition);
      e.printStackTrace();
      throw e;

    } catch (Exception e) {
      LogHelper.getInstance().error(e.getMessage(), Messages.Activity_Transition);
      e.printStackTrace();
      throw new TransitionException(e);

    } finally {
      dispose();
    }
  }

  protected void initializeParameters() {
    addSharedParameter(new GenericParameter<IContext>(ITransposerWorkflow.TRANSPOSER_CONTEXT,
        _transposer.getContext(), "Context used during rules execution"));
  }

  public class DispatcherArrayIterator implements Iterator<String> {
    private String array[];
    private int pos = 0;

    public DispatcherArrayIterator(String array[]) {
      this.array = array;
    }

    public boolean hasNext() {
      ILoopActivityDispatcher dispatcher = getDispatcher();
      if ((pos < (array.length - 1))) {
        if (dispatcher.loop(getTransposer().getContext(), array[pos])) {
          return true;
        }
        return true;
      } else if ((pos < array.length)) {
        if (dispatcher.loop(getTransposer().getContext(), array[pos])) {
          return true;
        }
      }
      return false;
    }

    public String next() throws NoSuchElementException {
      ILoopActivityDispatcher dispatcher = getDispatcher();
      if (dispatcher.loop(getTransposer().getContext(), array[pos])) {
        return array[pos];
      }
      return array[pos++];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
