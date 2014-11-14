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
package org.polarsys.capella.core.transition.common.launcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;

import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.capella.core.transition.common.handlers.log.DefaultLogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.ILogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.transposer.ExtendedTransposer;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransposerLauncher extends ActivitiesLauncher {

  private final String PURPOSE = "org.polarsys.capella.core.transition"; //$NON-NLS-1$
  private final String MAPPING = "org.polarsys.capella.core.transition.defaultMapping"; //$NON-NLS-1$

  protected ExtendedTransposer _transposer;

  protected ExtendedTransposer getTransposer() {
    return _transposer;
  }

  protected String getPurpose() {
    return PURPOSE;
  }

  protected String getMapping() {
    return MAPPING;
  }

  protected ExtendedTransposer createTransposer(String purpose_p, String mappingId_p) {
    return new ExtendedTransposer(purpose_p, mappingId_p) {
      @Override
      public void initCadence() {
        cadenceLauncher = _cadenceLauncher;
      }
    };
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

  /**
   * @param parameter_p
   */
  @Override
  protected SharedWorkflowActivityParameter getSharedParameter(String workflowId_p) {
    SharedWorkflowActivityParameter parameter = super.getSharedParameter(workflowId_p);

    parameter.addSharedParameter(new GenericParameter<IContext>(ITransposerWorkflow.TRANSPOSER_CONTEXT, _transposer.getContext(),
        "Context used during rules execution")); //$NON-NLS-1$

    return parameter;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.action.Action#run()
   */
  public void run(Collection<Object> selection_p, boolean save, IProgressMonitor monitor_p) {
    List<Object> selection = new ArrayList<Object>();
    selection.addAll(selection_p);
    launch(selection, getPurpose(), getMapping(), monitor_p);
  }

  @Override
  public void launch(Collection<Object> selection_p, String purpose_p, String mappingId_p, IProgressMonitor monitor_p) {

    try {
      initializeLogHandler();

      _transposer = createTransposer(purpose_p, mappingId_p);
      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_INSTANCE, _transposer);
      _transposer.getContext().put(ITransitionConstants.TRANSPOSER_SELECTION, selection_p);
      _transposer.getContext().put(ITransposerWorkflow.TRANSPOSER_ANALYSIS_SOURCES, new ArrayList<Object>());

      triggerActivities(selection_p, getWorkflow(), monitor_p);

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

}
