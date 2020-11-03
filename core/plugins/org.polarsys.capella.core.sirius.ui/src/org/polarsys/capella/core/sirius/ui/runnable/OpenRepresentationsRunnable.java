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

package org.polarsys.capella.core.sirius.ui.runnable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

/**
 * The runnable allowing to open Sirius representations.
 */
public class OpenRepresentationsRunnable implements IRunnableWithProgress {

  // The list of representations to open.
  private Collection<DRepresentationDescriptor> _descriptors;

  // The arrange all flag.
  private boolean _arrangeAll;

  /**
   * Constructs the runnable allowing to open Sirius representations.
   * 
   * @param descriptors
   *          The list of representations to open.
   * @param arrangeAll
   *          <code>True</code> If we need to arrange all diagram elements immediately after opening representations
   *          else <code>false</code>.
   */
  public OpenRepresentationsRunnable(Collection<DRepresentationDescriptor> descriptors, boolean arrangeAll) {
    _descriptors = descriptors;
    _arrangeAll = arrangeAll;
  }

  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    SubMonitor progress = SubMonitor.convert(monitor, 1);
    SubMonitor loopProgress = progress.newChild(1).setWorkRemaining(_descriptors.size());
    for (DRepresentationDescriptor representation : _descriptors) {

      // Gets the corresponding session.
      EObject semantic = representation.getTarget();
      Session session = SessionManager.INSTANCE.getSession(semantic);

      // Opens the editor.
      if (null != session) {
        IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, representation.getRepresentation(),
            new NullProgressMonitor());
        if (null != part) {
          // Arrange all.
          if (_arrangeAll && (part instanceof DiagramEditor)) {
            arrangeAll((DiagramEditor) part);
          }
        }
      }
      loopProgress.worked(1);
    }
  }

  /**
   * Trigger a ArrangeRequest on the given editor
   * @param part
   */
  @SuppressWarnings("unchecked")
  private void arrangeAll(DiagramEditor part) {
    DiagramEditor editor = (DiagramEditor) part;
    final DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
    ArrayList<EditPart> editParts = new ArrayList<EditPart>();
    editParts.addAll(diagramEditPart.getChildren());

    ArrangeRequest arrangeRequest = new ArrangeRequest(RequestConstants.REQ_ARRANGE_DEFERRED);
    arrangeRequest.setViewAdaptersToArrange(editParts);
    diagramEditPart.deactivate();
    diagramEditPart.performRequest(arrangeRequest);
    Display.getDefault().syncExec(new Runnable() {
      public void run() {
        diagramEditPart.activate();
      }
    });
  }
}
