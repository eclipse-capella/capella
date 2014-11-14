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
package org.polarsys.capella.core.transition.common.activities;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.cadence.core.api.IActivity;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.DeclaredParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.ParameterError;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.activity.IActivityExtender;
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
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus preRun(IContext context_p, ActivityParameters activityParams_p) {

    IStatus status = Status.OK_STATUS;
    for (Object handler : ExtensionHelper.collectActivityExtendersFromExtensions(context_p, ISchemaConstants.EXTENSION_ID, getActivityIdentifier())) {
      if (handler instanceof IActivityExtender) {
        IActivityExtender activityExtender = (IActivityExtender) handler;
        activityExtender.init(context_p);
        status = activityExtender.preActivity(context_p, getActivityIdentifier(), activityParams_p);
        if (!checkStatus(status)) {
          return status;
        }
      }
    }

    return status;
  }

  protected IStatus postRun(IContext context_p, ActivityParameters activityParams_p) {

    IStatus status = Status.OK_STATUS;
    for (Object handler : ExtensionHelper.collectActivityExtendersFromExtensions(context_p, ISchemaConstants.EXTENSION_ID, getActivityIdentifier())) {
      if (handler instanceof IActivityExtender) {
        IActivityExtender activityExtender = (IActivityExtender) handler;
        status = activityExtender.postActivity(context_p, getActivityIdentifier(), activityParams_p);
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
  public IStatus run(ActivityParameters activityParams_p) {

    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    IStatus status = Status.OK_STATUS;

    status = preRun(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = _run(activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = postRun(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = verificationRun(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus verificationRun(IContext context_p, ActivityParameters activityParams_p) {
    return Status.OK_STATUS;
  }

  protected abstract IStatus _run(ActivityParameters activityParams_p);

  protected IContext getContext(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();
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
  public Map<String, ParameterError<?>> validateParameters(ActivityParameters valuedParameters_p) {
    return null;
  }

  /**
   * If an handler is defined in parameters for the given handlerId, use it
   * @param optionsHandler_p
   * @param activityParams_p
   * @return
   */
  protected IHandler loadHandlerFromParameters(String handlerId_p, ActivityParameters activityParams_p) {
    GenericParameter<?> parameter = activityParams_p.getParameter(handlerId_p);
    if (parameter != null) {
      if (IHandler.class.isAssignableFrom(parameter.getParameterType())) {
        return (IHandler) parameter.getValue();
      }
    }
    return null;
  }

  /**
   * If an handler is defined in parameters for the given handlerId, use it
   * @param optionsHandler_p
   * @param activityParams_p
   * @return
   */
  protected String loadStringFromParameters(String stringId_p, ActivityParameters activityParams_p) {
    GenericParameter<?> parameter = activityParams_p.getParameter(stringId_p);
    if (parameter != null) {
      if (String.class.isAssignableFrom(parameter.getParameterType())) {
        return (String) parameter.getValue();
      }
    }
    return null;
  }

  /**
   * Returns if the given status prevent next activities of transition
   * @param status_p
   * @return
   */
  protected boolean checkStatus(IStatus status_p) {
    return !(status_p.matches(IStatus.CANCEL) || status_p.matches(IStatus.ERROR));
  }

  /**
   * Ensure that all given string_p are registered in the context
   * @param context_p
   * @param strings_p
   * @return
   */
  protected IStatus checkParameters(IContext context_p, String[] strings_p) {
    if (strings_p != null) {
      for (String string : strings_p) {
        if (!context_p.exists(string) || (context_p.get(string) == null)) {
          return new Status(IStatus.ERROR, Messages.Activity_Transition, NLS.bind("Parameter ''{0}'' must be defined", string));
        }
      }
    }
    return Status.OK_STATUS;
  }
}
