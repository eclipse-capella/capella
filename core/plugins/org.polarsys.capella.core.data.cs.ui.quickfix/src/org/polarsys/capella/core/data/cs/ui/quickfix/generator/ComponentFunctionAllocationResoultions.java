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
package org.polarsys.capella.core.data.cs.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command.EditComponent;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command.RemoveNonLeafFunctionAllocation;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CommandMarkerResolution;

public class ComponentFunctionAllocationResoultions implements IMarkerResolutionGenerator {

  public IMarkerResolution[] getResolutions(IMarker marker_p) {

    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker_p);
    if ((objects.size() > 0) && (objects.get(0) instanceof Component)) {
      Component element = (Component) objects.get(0);
      resolutions.add(new CommandMarkerResolution(new EditComponent(element)));
      resolutions.add(new CommandMarkerResolution(new RemoveNonLeafFunctionAllocation(element)));
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

}
