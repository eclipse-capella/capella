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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.Objects;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

/**
 *
 */
public class SharedWorkflowActivityParameter extends WorkflowActivityParameter {

  protected ActivityParameters _sharedParameters = new ActivityParameters();

  /**
   * This method allows to add a shared parameter between all activities.
   * 
   * If the shared parameter is already defined, it is not overridden.
   */
  public void addSharedParameter(GenericParameter<?> parameter) {
    GenericParameter<?> currentParameter = _sharedParameters.getParameter(parameter.getName());
    if (currentParameter == null) {
      _sharedParameters.addParameter(parameter);
    } else if (!Objects.equals(currentParameter.getValue(), parameter.getValue())) {
      if (Platform.inDevelopmentMode()) {
        System.out.println(NLS.bind("Attempt to override shared parameter {0} by another value.", parameter.getName()));
      }
    }
  }

  public void removeSharedParameter(String parameterId) {
    _sharedParameters.removeParameter(parameterId);
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
