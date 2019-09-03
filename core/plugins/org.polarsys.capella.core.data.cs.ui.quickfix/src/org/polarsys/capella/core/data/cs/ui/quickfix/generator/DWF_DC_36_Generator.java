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
package org.polarsys.capella.core.data.cs.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.SwitchIsActorIsHumanFlag_Resolver;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.SwitchIsActorIsHumanFlag_ResolverAll;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;

public class DWF_DC_36_Generator extends AbstractMarkerResolutionGenerator {
  private String switch_OE = "Switch to Operational Entity";
  private String switch_non_human = "Switch to non HUMAN";

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    if ((modelElements.size() < 1) || !(modelElements.get(0) instanceof Component)) {
      return new IMarkerResolution[0];
    }

    Component component = (Component) modelElements.get(0);
    if (component != null) {
      if (component instanceof Entity) {
        resolutions.add(new SwitchIsActorIsHumanFlag_Resolver(switch_OE, false, false));
        resolutions.add(new SwitchIsActorIsHumanFlag_ResolverAll(switch_OE, false, false));
      } else {
        // switch to non human
        resolutions.add(new SwitchIsActorIsHumanFlag_Resolver(switch_non_human, false, false));
        resolutions.add(new SwitchIsActorIsHumanFlag_ResolverAll(switch_non_human, false, false));
      }
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_DC_36";
  }
}
