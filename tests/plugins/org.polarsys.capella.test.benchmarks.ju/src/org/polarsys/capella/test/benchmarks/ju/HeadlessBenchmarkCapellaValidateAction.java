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
package org.polarsys.capella.test.benchmarks.ju;

import java.util.List;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.polarsys.capella.core.validation.ui.actions.CapellaValidateAction;

public class HeadlessBenchmarkCapellaValidateAction extends CapellaValidateAction {
  @SuppressWarnings("unused")
  @Override
  /**
   * Tune the superclass method to avoid opening a dialog. {@inheritDoc}
   */
  protected void handleDiagnostic(Diagnostic diagnostic) {
    int severity = diagnostic.getSeverity();
    String title = null;
    String message = null;

    if (severity == Diagnostic.ERROR || severity == Diagnostic.WARNING) {
      title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_title"); //$NON-NLS-1$
      message = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_message"); //$NON-NLS-1$
    } else {
      title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationResults_title"); //$NON-NLS-1$
      message = EMFEditUIPlugin.INSTANCE
          .getString(severity == Diagnostic.OK ? "_UI_ValidationOK_message" : "_UI_ValidationResults_message"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    int result = 0;
    if (diagnostic.getSeverity() == Diagnostic.OK) {
      // MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message);
      result = Window.CANCEL;
    } else {
      // result = DiagnosticDialog.open
      // (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message, diagnostic);
      result = Window.OK;
    }

    Resource resource = eclipseResourcesUtil != null ? domain.getResourceSet().getResources().get(0) : null;
    if (resource != null) {
      eclipseResourcesUtil.deleteMarkers(resource);
    }

    if (result == Window.OK) {
      if (!diagnostic.getChildren().isEmpty()) {
        List<?> data = (diagnostic.getChildren().get(0)).getData();
        if (!data.isEmpty() && data.get(0) instanceof EObject) {
          Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
          if (part instanceof ISetSelectionTarget) {
            ((ISetSelectionTarget) part).selectReveal(new StructuredSelection(data.get(0)));
          } else if (part instanceof IViewerProvider) {
            Viewer viewer = ((IViewerProvider) part).getViewer();
            if (viewer != null) {
              viewer.setSelection(new StructuredSelection(data.get(0)), true);
            }
          }
        }
      }

      if (resource != null) {
        for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
          eclipseResourcesUtil.createMarkers(resource, childDiagnostic);
        }
      }
    }
  }
}
