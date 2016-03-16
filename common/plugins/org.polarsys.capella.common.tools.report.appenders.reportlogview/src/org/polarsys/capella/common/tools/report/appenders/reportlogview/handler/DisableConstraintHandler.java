/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;

/**
 * Disable a constraint and delete all markers associated to this constraint.
 * 
 */
public class DisableConstraintHandler extends AbstractHandler {

  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {

    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
    IHandlerService service = (IHandlerService) window.getService(IHandlerService.class);
    try {
      service.executeCommand(DeleteConstraintMarkersHandler.COMMAND_ID, null);
    } catch (NotDefinedException exception_p) {
      MarkerViewPlugin.getDefault().log(IStatus.ERROR, exception_p.getMessage(), exception_p);
    } catch (NotEnabledException exception_p) {
      MarkerViewPlugin.getDefault().log(IStatus.ERROR, exception_p.getMessage(), exception_p);
    } catch (NotHandledException exception_p) {
      MarkerViewPlugin.getDefault().log(IStatus.ERROR, exception_p.getMessage(), exception_p);
    }

    IConstraintDescriptor descriptor = getConstraintDescriptor(HandlerUtil.getCurrentSelection(event));
    if (descriptor != null) {
      EMFModelValidationPreferences.setConstraintDisabled(descriptor.getId(), true);
      EMFModelValidationPreferences.save();
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
