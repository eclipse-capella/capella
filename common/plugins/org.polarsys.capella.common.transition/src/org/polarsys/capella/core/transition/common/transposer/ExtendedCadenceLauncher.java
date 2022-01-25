/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.cadence.core.api.CadenceValidator;
import org.polarsys.kitalpha.cadence.core.api.IActivity;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.ParameterError;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.transition.common.exception.TransitionException;

/**
 * This class allows to launch workflow.
 * 
 * 
 */
public class ExtendedCadenceLauncher {

  private Map<String, ParameterError<?>> wrongParams = null;

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters)
      throws Exception {
    return cadence(workflow_id, workflowElement_id, workflowActivityParameters, null);

  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final WorkflowActivityParameter workflowActivityParameters,
      final IProgressMonitor monitor) throws Exception {

    MultiStatus result = new MultiStatus("org.polarsys.kitalpha.cadence.core", 0, getStatusMessage(workflow_id, workflowElement_id), null) {

      /**
       * Retrieve a compound message with all sub child statuses
       * {@inheritDoc}
       */
      @Override
      public String getMessage() {
        String message = super.getMessage();

        if (getChildren() != null) {
          message += ICommonConstants.WHITE_SPACE_CHARACTER;
          message += ICommonConstants.PARENTHESIS_OPEN_CHARACTER;
          int i = 0;
          for (IStatus child : getChildren()) {
            message += child.getMessage();
            i++;
            if (getChildren().length != i) {
              message += ICommonConstants.SEMICOLON_CHARACTER;
            }
          }
          message += ICommonConstants.PARENTHESIS_CLOSE_CHARACTER;
        }
        return message;
      }

    };
    final Set<String> activitiesID;
    final int candidateSize;
    final boolean isMultiple = CadenceExtensions.isMultiple(workflow_id, workflowElement_id);

    if (workflowActivityParameters != null) {
      activitiesID = workflowActivityParameters.getActivitiesID();
      candidateSize = workflowActivityParameters.getActivitiesID().size();
    } else {
      activitiesID = Collections.<String> emptySet();
      candidateSize = 0;
    }

    if (monitor != null) {
      IConfigurationElement workflow = CadenceExtensions.getWorkflow(workflow_id); // get worlkflow element
      IConfigurationElement workflowElement = CadenceExtensions.getWorkflowElement(workflow_id, workflowElement_id); // get worlkflow element

      String workflowName = workflow == null ? workflow_id : workflow.getAttribute(CadenceExtensions.ATT_NAME);
      String workflowElementName = workflowElement == null ? workflowElement_id : workflowElement.getAttribute(CadenceExtensions.ATT_NAME);

      monitor.beginTask(getTaskName(workflowName, workflowElementName), candidateSize);
    }

    if ((candidateSize <= 1) || ((candidateSize > 1) && isMultiple)) {

      for (String activityID : activitiesID) {
        ActivityParameters activityParameters = workflowActivityParameters != null ? workflowActivityParameters.getActivityParameters(activityID) : null;
        IStatus status = cadence(workflow_id, workflowElement_id, activityID, activityParameters, monitor);
        if (status == null) {
          //  Platform.getLog(Activator.class).log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, "Activity : " + activityID + " has returned a null status."));
        } else if (status.matches(IStatus.CANCEL) || status.matches(IStatus.ERROR)) {
          result.add(status);
          break;

        } else {
          result.add(status);
        }
        if (monitor != null) {
          monitor.worked(1);
        }
      }
    } else {
      throw new Exception("the workflowElement " + workflowElement_id + " is not multiple."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (monitor != null) {
      monitor.done();
    }

    return result;
  }

  /**
   * @param workflowName
   * @param workflowElementName
   * @return
   */
  protected String getTaskName(String workflowName, String workflowElementName) {
    return "Cadence " + workflowName + " : " + workflowElementName;
  }

  protected String getStatusMessage(String workflowName, String workflowElementName) {
    return "Cadence activities";
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final String activityElement_id, final ActivityParameters activityParameters) {

    return cadence(workflow_id, workflowElement_id, activityElement_id, activityParameters, null);
  }

  public IStatus cadence(final String workflow_id, final String workflowElement_id, final String activityElement_id,
      final ActivityParameters activityParameters, IProgressMonitor monitor) {

    IConfigurationElement workflowElement = CadenceExtensions.getWorkflowElement(workflow_id, workflowElement_id); // get worlkflow element
    IConfigurationElement activityElement = CadenceExtensions.getActivityConfigElement(activityElement_id);// get activity candidate

    if (activityElement == null) {
      throw new RuntimeException(NLS.bind("Activity ''{0}'' is not registered.", activityElement_id));
    }

    if (monitor != null) {
      String activityName = activityElement.getAttribute(CadenceExtensions.ATT_NAME);
      monitor.subTask(activityName);
    }

    return cadence(workflowElement, activityElement, activityParameters); // if is good candidate run it
  }

  private IStatus cadence(final IConfigurationElement workflowElement, final IConfigurationElement activityElement, final ActivityParameters activityParameters) {

    IActivity activity;
    IStatus status = null;
    try {

      activity = (IActivity) activityElement.createExecutableExtension(CadenceExtensions.ATT_ACTIVITY_CLASS);

      if ((workflowElement == null) || ((activityParameters != null) && matchParameters(workflowElement, activityParameters))) { // 
        wrongParams = activity.validateParameters(activityParameters);
        if ((wrongParams == null) || wrongParams.isEmpty()) {
          status = activity.run(activityParameters);

        } else {
          throw new Exception("activity's parameters are not valid. " + CadenceValidator.getParameterErrorsTrace(wrongParams)); //$NON-NLS-1$
        }
      }

    } catch (Exception e) {
      throw new TransitionException(e);
    }

    return status;
  }

  private boolean matchParameters(IConfigurationElement workflowElement, ActivityParameters activityParameters) throws Exception {

    if (activityParameters != null) {
      IConfigurationElement[] params = CadenceExtensions.getWorkflowElementParameters(workflowElement);
      if (activityParameters.getParameters().size() < params.length) {
        throw new Exception("activity doesn't define parameters enough"); //$NON-NLS-1$
      }
      for (IConfigurationElement p : params) {
        String name = p.getAttribute(CadenceExtensions.ATT_NAME);
        if (!activityParameters.getParametersID().contains(name)) {
          throw new Exception("The org.polarsys.kitalpha.cadence.core.api.parameter " + name + " doesn't exist."); //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
    }

    return true;
  }

  public final String PARAMETER_DEFINITION = "ParameterDefinition"; //$NON-NLS-1$

  public IConfigurationElement[] getWorkflowElementParameters(IConfigurationElement workflowElement) {
    return workflowElement.getChildren(PARAMETER_DEFINITION);
  }
}
