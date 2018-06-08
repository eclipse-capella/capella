/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  private ISelection _selection;
  /**
   * Semantic browser view.
   */
  private SemanticBrowserView _semanticBrowserView;

  /**
   * 
   */
  public void init(IWorkbenchPart part_p) {
    _semanticBrowserView = (SemanticBrowserView) part_p;
  }

  /**
   * Post editor open handling method.<br>
   * @param element_p
   * @param openEditor_p
   */
  protected void postEditorOpen(Object element_p, IEditorPart openEditor_p) {
    //Nothing here
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action_p) {
    IStructuredSelection structuredSelection = (IStructuredSelection) _selection;
    // This action is only available for DSemanticDiagram with at least one element.
    DRepresentationDescriptor representation = (DRepresentationDescriptor) structuredSelection.getFirstElement();
    // Get the current element before opening the diagram that changes the selection.
    Object currentElement = _semanticBrowserView.getCurrentViewer().getInput();
    // Deactivate listening events during diagram opening since the open editor will change it to something that is not the end-user concern.
    boolean listeningToPageSelectionEvents = SemanticBrowserView.isListeningToPageSelectionEvents();
    EObject target = representation.getTarget();
    if (null != target) {
      try {
        if (listeningToPageSelectionEvents) {
          _semanticBrowserView.deactivateListeningToPageSelectionEvents();
        }
        Session session = SessionManager.INSTANCE.getSession(target);
        IEditorPart openEditor = DialectUIManager.INSTANCE.openEditor(session, representation.getRepresentation(), new NullProgressMonitor());
        postEditorOpen(currentElement, openEditor);

      } finally {
        // Enable again the listening if needed.
        if (listeningToPageSelectionEvents) {
          _semanticBrowserView.activateListeningToPageSelectionEvents();
        }
      }
    }
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    if (selection_p.isEmpty()) {
      _selection = null;
    } else {
      _selection = selection_p;
    }
  }
}
