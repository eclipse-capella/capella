/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolution;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.ui.quickfix.resolver.PhysicalComponentNatureToBehaviorResolver;
import org.polarsys.capella.core.data.pa.ui.quickfix.resolver.PhysicalComponentNatureToBehaviorResolverAll;
import org.polarsys.capella.core.data.pa.ui.quickfix.resolver.PhysicalComponentNatureToNodeResolver;
import org.polarsys.capella.core.data.pa.ui.quickfix.resolver.PhysicalComponentNatureToNodeResolverAll;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;

public class DWF_DC_22_Resolutions extends AbstractMarkerResolutionGenerator {

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    if ((modelElements.size() < 1) || !(modelElements.get(0) instanceof PhysicalComponent)) {
      return new IMarkerResolution[0];
    }

    PhysicalComponent physicalComponent = (PhysicalComponent) modelElements.get(0);
    if (physicalComponent != null) {
      if (physicalComponent.getNature() == PhysicalComponentNature.BEHAVIOR) {
        resolutions.add(new PhysicalComponentNatureToNodeResolver());
        resolutions.add(new PhysicalComponentNatureToNodeResolverAll());
      } else if (physicalComponent.getNature() == PhysicalComponentNature.NODE) {
        resolutions.add(new PhysicalComponentNatureToBehaviorResolver());
        resolutions.add(new PhysicalComponentNatureToBehaviorResolverAll());
      }
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.pa.validation.DWF_DC_22";
  }
}
