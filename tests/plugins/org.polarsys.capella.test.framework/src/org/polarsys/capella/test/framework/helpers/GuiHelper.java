/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * @author Erwan Brottier
 */
public class GuiHelper {

  /** Get all selected resources in the package explorer view */
  public static List<IResource> getCurrentSelectionInNavigator(String navigatorId) {
    List<IResource> resources = new ArrayList<IResource>();
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
    if (selection != null) {
      for (Object elt : selection.toList()) {
        IResource resource = (IResource) Platform.getAdapterManager().getAdapter(elt, IResource.class);
        if (resource != null) {
          resources.add(resource);
        }
      }
    }
    return resources;
  }

  public static List<IResource> getCurrentSelection() {
    List<IResource> resources = new ArrayList<IResource>();
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    IWorkbenchPart part = window.getActivePage().getActivePart();

    IStructuredSelection selection = (IStructuredSelection) part.getSite().getSelectionProvider().getSelection();
    if (selection != null) {
      for (Object elt : selection.toList()) {
        IResource resource = (IResource) Platform.getAdapterManager().getAdapter(elt, IResource.class);
        if (resource != null) {
          resources.add(resource);
        }
      }
    }
    return resources;
  }

  public static void waitCloseSessionJobs() {
    try {
      Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
    } catch (OperationCanceledException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static IStructuredSelection getCurrentSelectionInProjectExplorer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(CapellaCommonNavigator.ID);

        viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
      }
    });
    return viewer[0].getStructuredSelection();
  }
}
