/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.sirius.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Open a diagram and select/reveal in this open diagram, the element displayed as root one in the current viewer.
 */
public class DiagramOpenAction {
  /**
   * Current selected diagram.
   */
  private ISelection selection;
  /**
   * Semantic browser view.
   */
  private SemanticBrowserView semanticBrowserView;

  /**
   * 
   */
  public void init(IWorkbenchPart part) {
    semanticBrowserView = (SemanticBrowserView) part;
  }

  /**
   * Post editor open handling method.<br>
   * 
   * @param element
   * @param openEditor
   */
  protected void postEditorOpen(Object element, IEditorPart openEditor) {
    // Nothing here
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    // This action is only available for DSemanticDiagram with at least one element.
    DRepresentationDescriptor representation = (DRepresentationDescriptor) structuredSelection.getFirstElement();
    // Get the current element before opening the diagram that changes the selection.
    Object currentElement = semanticBrowserView.getCurrentViewer().getInput();
    // Deactivate listening events during diagram opening since the open editor will change it to something that is not
    // the end-user concern.
    boolean listeningToPageSelectionEvents = semanticBrowserView.getModel().isListeningToPageSelectionEvents();
    EObject target = representation.getTarget();
    if (null != target) {
      try {
        if (listeningToPageSelectionEvents) {
          semanticBrowserView.deactivateListeningToPageSelectionEvents();
        }
        Session session = SessionManager.INSTANCE.getSession(target);
        IEditorPart openEditor = DialectUIManager.INSTANCE.openEditor(session, representation.getRepresentation(),
            new NullProgressMonitor());
        postEditorOpen(currentElement, openEditor);

      } finally {
        // Enable again the listening if needed.
        if (listeningToPageSelectionEvents) {
          semanticBrowserView.activateListeningToPageSelectionEvents();
        }
      }
    }
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection) {
    if (selection.isEmpty()) {
      this.selection = null;
    } else {
      this.selection = selection;
    }
  }
}
