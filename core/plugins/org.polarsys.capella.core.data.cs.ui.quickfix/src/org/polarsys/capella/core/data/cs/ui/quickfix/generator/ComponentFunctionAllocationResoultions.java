/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command.EditComponent;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command.RemoveNonLeafFunctionAllocation;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CommandMarkerResolution;

public class ComponentFunctionAllocationResoultions extends AbstractMarkerResolutionGenerator {

  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker);
    if ((objects.size() > 0) && (objects.get(0) instanceof Component)) {
      Component element = (Component) objects.get(0);
      resolutions.add(new CommandMarkerResolution(new EditComponent(element)));
      resolutions.add(new CommandMarkerResolution(new RemoveNonLeafFunctionAllocation(element)));
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }
  
  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_DC_09";
  }
}
