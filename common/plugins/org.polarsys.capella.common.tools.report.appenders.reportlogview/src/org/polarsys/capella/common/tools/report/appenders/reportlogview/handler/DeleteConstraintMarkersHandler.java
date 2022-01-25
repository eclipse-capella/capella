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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.IMarkerSource;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;

/**
 * Delete all constraint markers for a given constraint id.
 * 
 */
public class DeleteConstraintMarkersHandler extends AbstractViewHandler {

  public static final String COMMAND_ID = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.deleteconstraintmarkers"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IConstraintDescriptor descriptor = getConstraintDescriptor(HandlerUtil.getCurrentSelection(event));
    if (descriptor != null) {
      List<IMarker> delete = new ArrayList<IMarker>();
      MarkerView view = getView(event);
      List<IMarkerSource> sources = view.getMarkerSources();
      for (IMarkerSource source : sources) {
        for (IMarker m : source.getMarkers()) {
          if (MarkerViewHelper.getConstraintDescriptor(m) == descriptor) {
            delete.add(m);
          }
        }
      }
      for (IMarker m : delete) {
        try {
          m.delete();
        } catch (CoreException e) {
          Platform.getLog(MarkerViewPlugin.class).log(new Status(IStatus.ERROR, MarkerViewPlugin.getDefault().getBundle().getSymbolicName(), e.getMessage(), e));
        }
      }
    }

    return null;

  }

  @Override
  public boolean isEnabled() {
    boolean result = false;
    ISelection selection = null;
    IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench != null) {
      IWorkbenchWindow windows = workbench.getActiveWorkbenchWindow();
      if (windows != null) {
        IWorkbenchPage page = windows.getActivePage();
        if (page != null) {
          selection = page.getSelection();
        }
      }
    }
    if (getConstraintDescriptor(selection) != null) {
      result = true;
    }
    return result;
  }

  private IConstraintDescriptor getConstraintDescriptor(ISelection selection) {
    IConstraintDescriptor descriptor = null;
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection ssel = (IStructuredSelection) selection;
      Object first = ssel.getFirstElement();
      if ((first != null) && (first instanceof IMarker)) {
        IMarker marker = (IMarker) first;
        descriptor = MarkerViewHelper.getConstraintDescriptor(marker);
      }
    }
    return descriptor;
  }
}
