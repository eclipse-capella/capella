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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;

public abstract class AbstractMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {

  private final static IMarkerResolution[] NO_RESOLUTION = new IMarkerResolution[0];
  
  @Override
  public IMarkerResolution[] getResolutions(IMarker marker) {
    if (!hasResolutions(marker)) {
      return NO_RESOLUTION;
    }
    return doGetResolutions(marker);
  }
  
  @Override
  public boolean hasResolutions(IMarker marker) {
    String ruleID = MarkerViewHelper.getRuleID(marker, true);
    return getRuleId().equals(ruleID);
  }
  
  protected abstract IMarkerResolution[] doGetResolutions(IMarker marker);
  
  protected abstract String getRuleId();
}
