/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementGoToResolver;

/*
 * DWF_DF_21 - Control Nodes inconsistent operations
 */
public class DWF_DF_21_Resolutions extends AbstractMarkerResolutionGenerator {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_21";

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);

    if (modelElements.size() > 0 && modelElements.get(0) instanceof ControlNode) {
      ControlNode cNode = (ControlNode) modelElements.get(0);
      // go to element in Capella Explorer
      resolutions
          .add(new CapellaElementGoToResolver("", cNode));
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return RULE_ID;
  }

}
