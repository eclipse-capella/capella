/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.transition.common.constants.IOptionsConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class FinalizeTransitionActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.FinalizeTransitionActivity"; //$NON-NLS-1$

  @Override
  public String getActivityIdentifier() {
    return ITransitionSteps.FINALIZE_TRANSITION;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.
   * ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    boolean shouldSave = Boolean.TRUE.equals(context.get(ITransitionConstants.SAVE_REQUIRED));

    // Maybe with an option of configuration ?
    // Save if it another resource than the source resource
    Resource sourceResource = (Resource) context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
    Resource targetResource = (Resource) context.get(ITransitionConstants.TRANSITION_TARGET_RESOURCE);

    if (targetResource == null) {
      // don't have anything to cleanup/save
      return Status.OK_STATUS;
    }

    if (targetResource != sourceResource || shouldSave) {
      Session session = SessionManager.INSTANCE.getSession(targetResource);
      if (session != null) {
        if (session.isOpen()) {
          session.save(new NullProgressMonitor());
          LogHelper.getInstance().info(
              NLS.bind("Session for ''{0}'' has been saved automatically.", targetResource.getURI()),
              Messages.Activity_Transition);
        }
      } else {
        try {
          LogHelper.getInstance().info(
              NLS.bind("Resource ''{0}'' has been saved automatically.", targetResource.getURI()),
              Messages.Activity_Transition);
          targetResource.save(Collections.EMPTY_MAP);
        } catch (IOException exception) {
          exception.printStackTrace();
          LogHelper.getInstance().warn(exception.getMessage(), Messages.Activity_Transition);
        }
      }
    }

    GenericParameter<?> parameter = activityParams.getParameter(IOptionsConstants.IS_DRY_RUN);
    if (parameter == null || !Boolean.valueOf(parameter.getValue().toString())) {
      LogHelper.getInstance().info("Operation has been successful.", Messages.Activity_Transition);
    }

    return Status.OK_STATUS;
  }

}
