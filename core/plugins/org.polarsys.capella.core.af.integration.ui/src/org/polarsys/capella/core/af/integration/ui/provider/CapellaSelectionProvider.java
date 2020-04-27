/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.kitalpha.ad.viewpoint.ui.provider.AFSelectionProvider;
import org.polarsys.kitalpha.ad.viewpoint.ui.views.ViewpointView;

/**
 * 
 */
public class CapellaSelectionProvider implements AFSelectionProvider {
  private final List<Object> selectedObjects = new ArrayList<Object>();
  private final List<ISelectionListener> listeners = new ArrayList<ISelectionListener>();
  private final ISelectionListener listener;

  public CapellaSelectionProvider() {
    super();
    ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
    selectionService.addSelectionListener(listener = new ISelectionListener() {

      public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part instanceof ViewpointView) {
          return;
        }
        selectedObjects.clear();

        if (selection instanceof IStructuredSelection) {
          for (Object obj : ((IStructuredSelection) selection).toArray()) {
            selectedObjects.add(obj);
          }
        }
        for (ISelectionListener listener : listeners) {
          listener.selectionChanged(part, selection);
        }
      }
    });
  }

  public List<Object> getSelection() {
    List<Object> result = new ArrayList<Object>();
    for (Object obj : selectedObjects) {
      if (obj instanceof AbstractEditPart) {
        obj = ((AbstractEditPart) obj).getModel();
        if (obj instanceof Node) {
          obj = ((Node) obj).getElement();
          if (obj instanceof DRepresentationElement) {
            obj = ((DRepresentationElement) obj).getTarget();
          }
        }
      }
      result.add(obj);
    }
    return result;

  }

  public void addListener(ISelectionListener listener) {
    listeners.add(listener);

  }

  public void removeListener(ISelectionListener listener) {
    listeners.remove(listener);

  }

  public void dispose() {
    ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
    selectionService.removeSelectionListener(listener);
  }

}
