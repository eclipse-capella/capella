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

package org.polarsys.capella.core.transition.common.transposer;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

/**
 *
 */
public class SharedWorkflowActivityParameter extends WorkflowActivityParameter {

  protected ActivityParameters _sharedParameters = new ActivityParameters();

  public void addSharedParameter(GenericParameter<?> parameter) {
    if (_sharedParameters.getParameter(parameter.getName()) == null) {
      _sharedParameters.addParameter(parameter);
    }
  }

  public void merge(SharedWorkflowActivityParameter sharedParameter) {
    for (String idActivity: sharedParameter.getActivitiesID()) {
      addParameter(idActivity, sharedParameter.getSpecificActivityParameters(idActivity));
    }
    for (GenericParameter<?> parameter : sharedParameter._sharedParameters.getParameters()) {
      addSharedParameter(parameter);
    }
  }
  
  protected ActivityParameters getSpecificActivityParameters(String idActivity) {
    return super.getActivityParameters(idActivity);
  }
  
  @Override
  public ActivityParameters getActivityParameters(String idActivity) {
    ActivityParameters activityParameter = super.getActivityParameters(idActivity);
    if (activityParameter == null) {
      activityParameter = new ActivityParameters();
    }
    for (GenericParameter<?> sharedParameter : _sharedParameters.getParameters()) {
      activityParameter.addParameter(sharedParameter);
    }
    return activityParameter;
  }
}
