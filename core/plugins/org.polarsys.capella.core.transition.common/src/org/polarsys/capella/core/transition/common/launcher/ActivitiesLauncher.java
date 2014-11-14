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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.exception.TransitionException;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.transposer.ExtendedCadenceLauncher;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

/**
 *
 */
public class ActivitiesLauncher {

  protected ExtendedCadenceLauncher _cadenceLauncher;

  protected HashMap<String, String> _mapOverrides;

  public class StringArrayIterator implements Iterator<String> {
    private String array[];
    private int pos = 0;

    public StringArrayIterator(String array_p[]) {
      array = array_p;
    }

    public boolean hasNext() {
      return pos < array.length;
    }

    public String next() throws NoSuchElementException {
      if (hasNext()) {
        return array[pos++];
      }
      throw new NoSuchElementException();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public void initCadence() {
    _cadenceLauncher = createCadenceLauncher();
  }

  public ActivitiesLauncher() {
    init();
  }

  /**
   * 
   */
  protected void init() {
    initCadence();
    initOverrides();
  }

  /**
   * 
   */
  protected void initOverrides() {
    //Nothing here
  }

  protected void addOverrides(String idOverrided_p, String idOverriding_p) {
    if (_mapOverrides == null) {
      _mapOverrides = new HashMap<String, String>();
    }
    _mapOverrides.put(idOverrided_p, idOverriding_p);
  }

  /**
   * Return the log component
   */
  protected String getReportComponent() {
    return IReportManagerDefaultComponents.BRIDGE;
  }

  protected Collection<GenericParameter<?>> getHeadlessParameters() {
    return Collections.EMPTY_LIST;
  }

  /**
   * 
   */
  protected void processCancel() {
    LogHelper.getInstance().info("Operation has been cancelled", Messages.Activity_Transition);
  }

  protected String getName() {
    return Messages.Activity_Transition;
  }

  protected ExtendedCadenceLauncher createCadenceLauncher() {
    return new ExtendedCadenceLauncher() {

      @Override
      protected String getStatusMessage(String workflowName_p, String workflowElementName_p) {
        return getName();
      }

      @Override
      protected String getTaskName(String workflowName_p, String workflowElementName_p) {
        return getName();
      }

    };
  }

  public void launch(Collection<Object> selection_p, String purpose_p, String mappingId_p, IProgressMonitor monitor_p) {

  }

  protected String getWorkflow() {
    return ICommonConstants.EMPTY_STRING;
  }

  protected String[] getWorkflowElements(String workflowId_p) {
    return new String[0];
  }

  protected String[] getFinalWorkflowElements(String workflowId_p) {
    return new String[0];
  }

  protected WorkflowActivityParameter getParameter(String workflowId_p, String workflowElement_p) {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();
    return parameter;
  }

  protected SharedWorkflowActivityParameter getSharedParameter(String workflowId_p) {
    return new SharedWorkflowActivityParameter();
  }

  protected Iterator<String> iteratorWorkflowElements(String workflowId_p) {
    return new StringArrayIterator(getWorkflowElements(workflowId_p));
  }

  protected Iterator<String> iteratorFinalWorkflowElements(String workflowId_p) {
    return new StringArrayIterator(getFinalWorkflowElements(workflowId_p));
  }

  protected void triggerActivities(Collection<Object> selection_p, String workflowId_p, IProgressMonitor monitor_p) {
    try {
      SharedWorkflowActivityParameter sharedParameter = getSharedParameter(workflowId_p);

      for (Iterator<String> iter = iteratorWorkflowElements(workflowId_p); iter.hasNext();) {
        String workflowElement = iter.next();
        WorkflowActivityParameter parameter = getParameter(workflowId_p, workflowElement);
        WorkflowActivityParameter compoundParameter = addSharedParameter(parameter, sharedParameter);
        IStatus status = triggerActivities(compoundParameter, workflowId_p, workflowElement, monitor_p);
        checkStatus(monitor_p, status);
      }

    } catch (OperationCanceledException e) {
      throw e;

    } catch (TransitionException e) {
      throw e;

    } catch (Exception e) {
      throw new TransitionException(e);

    } finally {
      SharedWorkflowActivityParameter sharedParameter = getSharedParameter(workflowId_p);

      try {

        for (Iterator<String> iter = iteratorFinalWorkflowElements(workflowId_p); iter.hasNext();) {
          String workflowElement = iter.next();
          WorkflowActivityParameter parameter = getParameter(workflowId_p, workflowElement);
          WorkflowActivityParameter compoundParameter = addSharedParameter(parameter, sharedParameter);
          IStatus status = triggerActivities(compoundParameter, workflowId_p, workflowElement, monitor_p);
          checkStatus(monitor_p, status);
        }

      } catch (OperationCanceledException e) {
        throw e;

      } catch (TransitionException e) {
        throw e;

      } catch (Exception e) {
        throw new TransitionException(e);

      }
    }
  }

  /**
   * @param parameter_p
   * @param sharedParameter_p
   */
  protected WorkflowActivityParameter addSharedParameter(WorkflowActivityParameter parameter_p, SharedWorkflowActivityParameter sharedParameter_p) {
    if (sharedParameter_p != null) {
      for (String idActivities : parameter_p.getActivitiesID()) {
        ActivityParameters ps = sharedParameter_p.getActivityParameters(idActivities);
        for (GenericParameter<?> a : ps.getParameters()) {
          parameter_p.addParameter(idActivities, a);
        }
      }
      return parameter_p;
    }
    return parameter_p;
  }

  /**
   * @param selection_p
   * @param monitor_p
   */
  protected IStatus triggerActivities(WorkflowActivityParameter activities_p, String workflowId_p, String workflowElement_p, IProgressMonitor monitor_p) {
    try {
      return cadence(workflowId_p, workflowElement_p, activities_p, monitor_p);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters,
      final IProgressMonitor monitor_p) throws Exception {
    IStatus result = _cadenceLauncher.cadence(workflow_id, workflowElement_id, workflowActivityParameters, monitor_p);
    if (result.matches(IStatus.CANCEL) || result.matches(IStatus.ERROR)) {
      monitor_p.setCanceled(true);
    }
    return result;
  }

  /**
   * @param monitor_p
   * @param status_p 
   */
  protected void checkStatus(IProgressMonitor monitor_p, IStatus status_p) throws Exception {
    if (monitor_p.isCanceled()) {
      if ((status_p != null) && status_p.matches(IStatus.ERROR)) {
        throw new TransitionException(status_p);
      }
      throw new OperationCanceledException(status_p == null ? "" : status_p.getMessage());
    }
  }

  /**
   * @param id_p
   * @return
   */
  protected String getActivity(String id_p) {
    if (_mapOverrides != null) {
      if (_mapOverrides.containsKey(id_p)) {
        return getActivity(_mapOverrides.get(id_p));
      }
    }
    return id_p;
  }

  /**
   * 
   */
  protected void dispose() {
    //Nothing here yet
  }

}
