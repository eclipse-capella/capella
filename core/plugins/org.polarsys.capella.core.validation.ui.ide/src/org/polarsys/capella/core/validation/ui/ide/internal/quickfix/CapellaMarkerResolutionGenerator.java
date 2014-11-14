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
package org.polarsys.capella.core.validation.ui.ide.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;

import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;

/**
 * Marker resolution generator for Capella markers
 *
 */
final public class CapellaMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {
  
  /**
   * 
   * @param marker_p
   * @return
   */
  public IMarkerResolution[] getResolutions(IMarker marker_p) {
    
    IMarkerResolution[] result = null;
    
    String ruleId = null;

    try {
      ruleId = (String) marker_p.getAttribute(IValidationConstants.TAG_RULE_ID);
    } catch (CoreException e){
      PluginActivator.getDefault().getLog().log(new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), e.getMessage(), e));
    }
    
    if ( null != ruleId ) {
      result = MarkerResolutionCache.INSTANCE.getResolutionsFor(ruleId);
    } else {
      result = MarkerResolutionCache.NO_RESOLUTIONS;
    }
    
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasResolutions(IMarker marker_p) {
    IMarkerResolution[] resolutions = getResolutions(marker_p);
    return (
        ( null != resolutions ) &&
        ( resolutions != MarkerResolutionCache.NO_RESOLUTIONS )
    );
  }
  

}
