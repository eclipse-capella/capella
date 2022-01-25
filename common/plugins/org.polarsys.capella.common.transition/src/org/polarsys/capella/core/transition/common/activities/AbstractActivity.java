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

package org.polarsys.capella.core.transition.common.activities;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.activity.IActivityExtender;
import org.polarsys.kitalpha.cadence.core.api.IActivity;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.DeclaredParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.ParameterError;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public abstract class AbstractActivity implements IActivity {

  public AbstractActivity() {

  }

  public String getActivityIdentifier() {
    return ITransitionSteps.UNDEFINED_ATIVITY + getClass().getSimpleName();
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus preRun(IContext context, ActivityParameters activityParams) {

    IStatus status = Status.OK_STATUS;
    for (Object handler : ExtensionHelper.collectActivityExtendersFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        getActivityIdentifier(), (String) context.get(ITransitionConstants.TRANSPOSER_PURPOSE),
        (String) context.get(ITransitionConstants.TRANSPOSER_MAPPING))) {
      if (handler instanceof IActivityExtender) {
        IActivityExtender activityExtender = (IActivityExtender) handler;
        activityExtender.init(context);
        status = activityExtender.preActivity(context, getActivityIdentifier(), activityParams);
        if (!checkStatus(status)) {
          return status;
        }
      }
    }

    return status;
  }

  protected IStatus postRun(IContext context, ActivityParameters activityParams) {

    IStatus status = Status.OK_STATUS;
    for (Object handler : ExtensionHelper.collectActivityExtendersFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        getActivityIdentifier(), (String) context.get(ITransitionConstants.TRANSPOSER_PURPOSE),
        (String) context.get(ITransitionConstants.TRANSPOSER_MAPPING))) {
      if (handler instanceof IActivityExtender) {
        IActivityExtender activityExtender = (IActivityExtender) handler;
        status = activityExtender.postActivity(context, getActivityIdentifier(), activityParams);
        if (!checkStatus(status)) {
          return status;
        }
      }
    }

    return status;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus run(ActivityParameters activityParams) {

    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    IStatus status = Status.OK_STATUS;

    status = preRun(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = _run(activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = postRun(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = verificationRun(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus verificationRun(IContext context, ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  protected abstract IStatus _run(ActivityParameters activityParams);

  protected IContext getContext(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();
    return context;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<DeclaredParameter> getParameters() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Map<String, ParameterError<?>> validateParameters(ActivityParameters valuedParameters) {
    return null;
  }

  /**
   * If an handler is defined in parameters for the given handlerId, use it
   * 
   * @param optionsHandler_p
   * @param activityParams
   * @return
   */
  protected IHandler loadHandlerFromParameters(String handlerId, ActivityParameters activityParams) {
    GenericParameter<?> parameter = activityParams.getParameter(handlerId);
    if (parameter != null) {
      if (IHandler.class.isAssignableFrom(parameter.getParameterType())) {
        return (IHandler) parameter.getValue();
      }
    }
    return null;
  }

  /**
   * If an handler is defined in parameters for the given handlerId, use it
   * 
   * @param optionsHandler_p
   * @param activityParams
   * @return
   */
  protected String loadStringFromParameters(String stringId, ActivityParameters activityParams) {
    GenericParameter<?> parameter = activityParams.getParameter(stringId);
    if (parameter != null) {
      if (String.class.isAssignableFrom(parameter.getParameterType())) {
        return (String) parameter.getValue();
      }
    }
    return null;
  }

  /**
   * Returns if the given status prevent next activities of transition
   * 
   * @param status
   * @return
   */
  protected boolean checkStatus(IStatus status) {
    return !(status.matches(IStatus.CANCEL) || status.matches(IStatus.ERROR));
  }

  /**
   * Ensure that all given string_p are registered in the context
   * 
   * @param context
   * @param strings
   * @return
   */
  protected IStatus checkParameters(IContext context, String[] strings) {
    if (strings != null) {
      for (String string : strings) {
        if (!context.exists(string) || (context.get(string) == null)) {
          return new Status(IStatus.ERROR, Messages.Activity_Transition, NLS.bind("Parameter ''{0}'' must be defined", string));
        }
      }
    }
    return Status.OK_STATUS;
  }
}
