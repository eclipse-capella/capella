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
package org.polarsys.capella.core.validation.ui.ide.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;

/**
 * Marker resolution generator for Capella markers
 *
 */
final public class CapellaMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {
  
  /**
   * 
   * @param marker
   * @return
   */
  public IMarkerResolution[] getResolutions(IMarker marker) {
    
    IMarkerResolution[] result = null;
    
    String ruleId = MarkerViewHelper.getRuleID(marker, true);

    if ( null != ruleId ) {
      result = MarkerResolutionCache.INSTANCE.getResolutionsFor(ruleId);
    } else {
      
      /* Ecore markers don't have rule id's attached. We use the diagnostic source + code as a 'virtual' rule id to find the resolution */
      if (MarkerViewHelper.isEcore(marker)){      
        Diagnostic diagnostic = MarkerViewHelper.getDiagnostic(marker);
        result = MarkerResolutionCache.INSTANCE.getResolutionsFor(diagnostic.getSource() + "." + diagnostic.getCode());
      } else {
        result = MarkerResolutionCache.NO_RESOLUTIONS;
      }
    }
    
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasResolutions(IMarker marker) {
    IMarkerResolution[] resolutions = getResolutions(marker);
    return (
        ( null != resolutions ) &&
        ( resolutions != MarkerResolutionCache.NO_RESOLUTIONS )
    );
  }
  

}
