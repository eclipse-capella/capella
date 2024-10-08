/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementGoToResolver;

public class DWF_CA_08_Resolutions extends AbstractMarkerResolutionGenerator {

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);

    AbstractCapability capability = null;
    Component component = null;
    for (EObject element : modelElements) {
      if (element instanceof AbstractCapability) {
        capability = (AbstractCapability) element;
        resolutions
            .add(new CapellaElementGoToResolver(EObjectLabelProviderHelper.getMetaclassLabel(element, false), element));
      } else if (element instanceof Component) {
        component = (Component) element;
        resolutions
            .add(new CapellaElementGoToResolver(NamingHelper.getTitleLabel(component), element));
      }
    }

    if (capability != null && component != null) {
      Helper_Resolutions.addResolution(capability, component, resolutions, getRuleId());
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_CA_08";
  }

}
