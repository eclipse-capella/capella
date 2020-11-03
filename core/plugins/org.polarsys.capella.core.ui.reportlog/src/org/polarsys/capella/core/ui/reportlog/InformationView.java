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
package org.polarsys.capella.core.ui.reportlog;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewFilter;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;

/**
 * Customized MarkerView to make selection filter work when selection 
 * is a diagram element.
 * 
 */
public class InformationView extends MarkerView {

  @Override
  public MarkerViewFilter createFilter(Viewer viewer) {
    return new MarkerViewFilter(viewer) {

      @Override
      // handle the case where we select an element in a diagram
      protected boolean select(Object selection_p, Viewer viewer_p, Object parent_p, IMarker marker_p) {

        // see if we can get a semantic element from the selection
        Object semanticObject = ModelAdaptation.adaptToCapella(selection_p);
        if (semanticObject == null) {
          semanticObject = selection_p;
        }

        // the super implementation handles the rest
        return super.select(semanticObject, viewer_p, parent_p, marker_p);
      }

      @Override
      // handle the case where we select an element in a diagram
      protected IProject getProject(Object selection_p) {

        // see if we can get a semantic element from the selection
        Object semanticObject = ModelAdaptation.adaptToCapella(selection_p);
        if (semanticObject == null) {
          semanticObject = selection_p;
        }

        // the super implementation handles the rest
        return super.getProject(semanticObject);
      }
    };
  }

}
