/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;

public class ConstraintLocationRule_Generator extends AbstractMarkerResolutionGenerator {

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<>();
    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    
    if ((!modelElements.isEmpty()) && (modelElements.get(0) instanceof Constraint)) {
      Constraint constraint = (Constraint) modelElements.get(0);
      String label;
      if (CsServices.getService().isMultipartMode((ModelElement) constraint)) {
        label = "Move constraint under the first value of ConstrainedElements";
      } else {
        label = "Move constraint under Component";
      }
      resolutions.add(new ConstraintLocationRule_Resolver(label));
    }
    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.core.validation.DWF_D_59";
  }
}
