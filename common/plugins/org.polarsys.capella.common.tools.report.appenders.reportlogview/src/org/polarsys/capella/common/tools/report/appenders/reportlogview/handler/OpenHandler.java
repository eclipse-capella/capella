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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.actions.SelectElementAction;

public class OpenHandler extends AbstractViewHandler {

  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    int index = 0;

    for (Object selectedObject : getSelection(event_p)) {
      if (selectedObject instanceof IMarker) {
        IMarker marker = (IMarker) selectedObject;
        List<EObject> map = MarkerViewHelper.getModelElementsFromMarker(marker);

        if (index < 0 || index >= map.size()) {
          index = 0;
        }
        if (map.size() > index) {
          new SelectElementAction(map.get(index)).run();
          return null;
        }
      } else {
        Viewer v = getView(event_p).getViewer();
        if (v instanceof TreeViewer){
          boolean expanded = ((TreeViewer) v).getExpandedState(selectedObject);
          if (expanded){
            ((TreeViewer) v).collapseToLevel(selectedObject, AbstractTreeViewer.ALL_LEVELS);
          } else {
            ((TreeViewer) v).expandToLevel(selectedObject, AbstractTreeViewer.ALL_LEVELS);
          }
        }
      }
    }
    return null;
  }
}
