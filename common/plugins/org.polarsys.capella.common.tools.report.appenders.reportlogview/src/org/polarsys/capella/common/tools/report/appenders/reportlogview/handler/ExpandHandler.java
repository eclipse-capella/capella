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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;

/**
 * Can expand and collapse the marker view tree.
 */
public class ExpandHandler extends AbstractHandler {

  public static final String PARAMETER_ID = "type"; //$NON-NLS-1$

  public enum Expand {
    NONE,
    ALL,
    DEFAULT
  }

  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException { 
    IWorkbenchPart part = HandlerUtil.getActivePart(event);
    if (part instanceof MarkerView){
      MarkerView view = (MarkerView) part;

      switch (Expand.valueOf(event.getParameter(PARAMETER_ID))){
        case NONE: {
          try {
            view.getViewer().getTree().setRedraw(false);
            view.getViewer().collapseAll();
          } finally {
            view.getViewer().getTree().setRedraw(true);
          }
          break;
        }
        case ALL: {
          try {
            view.getViewer().getTree().setRedraw(false);
            view.getViewer().expandAll();
          } finally {
            view.getViewer().getTree().setRedraw(true);
          }
          break;
        }
        case DEFAULT: {
          try {
            view.getViewer().getTree().setRedraw(false);
            view.expandToDefault();
          } finally {
            view.getViewer().getTree().setRedraw(true);
          }
          break;
        }
      }
    }
    return null;

  }
}
