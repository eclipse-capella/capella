/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.launcher;

import java.util.Collection;
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

  protected ExtendedCadenceLauncher cadenceLauncher;

  protected HashMap<String, String> mapOverrides;

  protected SharedWorkflowActivityParameter parameters = new SharedWorkflowActivityParameter(); 
  
  private String name;


  public class StringArrayIterator implements Iterator<String> {
    private String array[];
    private int pos = 0;

    public StringArrayIterator(String array[]) {
      this.array = array;
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

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public void initCadence() {
    this.cadenceLauncher = createCadenceLauncher();
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

  protected void addOverrides(String idOverrided, String idOverriding) {
    if (mapOverrides == null) {
      mapOverrides = new HashMap<>();
    }
    mapOverrides.put(idOverrided, idOverriding);
  }

  /**
   * Return the log component
   */
  protected String getReportComponent() {
    return IReportManagerDefaultComponents.BRIDGE;
  }

  protected String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * 
   */
  protected void processCancel() {
    LogHelper.getInstance().info("Operation has been cancelled", Messages.Activity_Transition);
  }

  protected ExtendedCadenceLauncher createCadenceLauncher() {
    return new ExtendedCadenceLauncher() {

      @Override
      protected String getStatusMessage(String workflowName, String workflowElementName) {
        return getName();
      }

      @Override
      protected String getTaskName(String workflowName, String workflowElementName) {
        return getName();
      }

    };
  }

  public void launch(Collection<?> selection, String purpose, String mappingId, IProgressMonitor monitor) {

  }

  protected String getWorkflow() {
    return ICommonConstants.EMPTY_STRING;
  }

  protected String[] getWorkflowElements(String workflowId) {
    return new String[0];
  }

  protected String[] getFinalWorkflowElements(String workflowId) {
    return new String[0];
  }

  protected WorkflowActivityParameter getParameter(String workflowId, String workflowElement) {
    return new WorkflowActivityParameter();
  }

  public void addParameters(SharedWorkflowActivityParameter parameters) {
    this.parameters.merge(parameters);
  }
  
  public void addParameter(String idActivity, GenericParameter<?> parameter) {
    this.parameters.addParameter(idActivity, parameter);
  }
  
  public void addSharedParameter(GenericParameter<?> parameter) {
    this.parameters.addSharedParameter(parameter);
  }
  
  public void removeSharedParameter(String parameterId) {
    this.parameters.removeSharedParameter(parameterId);
  }
  
  protected Iterator<String> iteratorWorkflowElements(String workflowId) {
    return new StringArrayIterator(getWorkflowElements(workflowId));
  }

  protected Iterator<String> iteratorFinalWorkflowElements(String workflowId) {
    return new StringArrayIterator(getFinalWorkflowElements(workflowId));
  }

  protected void triggerActivities(Collection<?> selection, String workflowId, IProgressMonitor monitor) {
    try {

      for (Iterator<String> iter = iteratorWorkflowElements(workflowId); iter.hasNext();) {
        String workflowElement = iter.next();
        WorkflowActivityParameter parameter = getParameter(workflowId, workflowElement);
        mergeParameters(parameter, parameters);
        IStatus status = triggerActivities(parameter, workflowId, workflowElement, monitor);
        checkStatus(monitor, status);
      }

    } catch (OperationCanceledException | TransitionException e) {
      throw e;

    } catch (Exception e) {
      throw new TransitionException(e);

    } finally {
      
      try {

        for (Iterator<String> iter = iteratorFinalWorkflowElements(workflowId); iter.hasNext();) {
          String workflowElement = iter.next();
          WorkflowActivityParameter parameter = getParameter(workflowId, workflowElement);
          mergeParameters(parameter, parameters);
          IStatus status = triggerActivities(parameter, workflowId, workflowElement, monitor);
          checkStatus(monitor, status);
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
   * @param parameter
   * @param sharedParameter
   */
  protected WorkflowActivityParameter mergeParameters(WorkflowActivityParameter parameter, SharedWorkflowActivityParameter sharedParameter) {
    if (sharedParameter != null) {
      for (String idActivities : parameter.getActivitiesID()) {
        ActivityParameters ps = sharedParameter.getActivityParameters(idActivities);
        for (GenericParameter<?> a : ps.getParameters()) {
          parameter.addParameter(idActivities, a);
        }
      }
    }
    return parameter;
  }

  /**
   * @param activities
   * @param workflowId
   * @param workflowElement
   * @param monitor
   */
  protected IStatus triggerActivities(WorkflowActivityParameter activities, String workflowId, String workflowElement, IProgressMonitor monitor) {
    try {
      return cadence(workflowId, workflowElement, activities, monitor);
    } catch (Exception e) {
      throw new TransitionException(e);
    }
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters,
      final IProgressMonitor monitor) throws Exception {
    IStatus result = cadenceLauncher.cadence(workflow_id, workflowElement_id, workflowActivityParameters, monitor);
    if (result.matches(IStatus.CANCEL) || result.matches(IStatus.ERROR)) {
      monitor.setCanceled(true);
    }
    return result;
  }

  /**
   * @param monitor
   * @param status
   */
  protected void checkStatus(IProgressMonitor monitor, IStatus status) throws Exception {
    if (monitor.isCanceled() && status != null) {
      if (status.matches(IStatus.ERROR)) {
        throw new TransitionException(status);
      } else if (status.matches(IStatus.CANCEL)) {
        throw new OperationCanceledException(status.getMessage());
      }
    }
  }

  /**
   * @param id
   * @return
   */
  protected String getActivity(String id) {
    if (mapOverrides != null) {
      if (mapOverrides.containsKey(id)) {
        return getActivity(mapOverrides.get(id));
      }
    }
    return id;
  }

  /**
   * 
   */
  protected void dispose() {
    //Nothing here yet
  }
}
