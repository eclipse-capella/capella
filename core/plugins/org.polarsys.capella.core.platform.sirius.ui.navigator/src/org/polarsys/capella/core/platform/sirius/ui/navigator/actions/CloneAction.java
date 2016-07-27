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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand.ICloneListener;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Clone action.
 */
public class CloneAction extends BaseSelectionListenerAction {
  /**
   * Latest selection of representations.
   */
  private Collection<DRepresentation> _representations;

  /**
   * Common viewer reminder.
   */
  protected TreeViewer _viewer;

  /**
   * Constructor.
   */
  public CloneAction(TreeViewer viewer) {
    super(Messages.CloneAction_Title);
    _viewer = viewer;
  }

  /**
   * Get selected representations.
   * @param selectedElements A list of selected elements.
   * @return A not <code>null</code> (possibly empty) collection of representations.
   */
  protected Collection<DRepresentation> getSelectedRepresentations(List<?> selectedElements) {
    // Resulting collection.
    Collection<DRepresentation> result = null;
    // Cycle through selected elements.
    for (Object element : selectedElements) {
      if (element instanceof ItemWrapper) {
        element = ((ItemWrapper) element).getWrappedObject();
      }
      if (element instanceof DRepresentationDescriptor) {
        element = ((DRepresentationDescriptor) element).getRepresentation();
      }
      // Got a representation, store it.
      if (element instanceof DRepresentation) {
        // Lazy initialization.
        if (null == result) {
          result = new ArrayList<DRepresentation>(1);
        }
        // Add representation.
        result.add((DRepresentation) element);
      }
    }
    // Do not return a null collection.
    if (null == result) {
      result = Collections.emptyList();
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    CapellaCloneDiagramCommand command = new CapellaCloneDiagramCommand(_representations);
    // Add a listener that refreshes the capella explorer during execution/undo/redo operations.
    command.addCloneListener(new ICloneListener() {
      /**
       * @see org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand.ICloneListener#cloneAboutToBeRemoved(org.eclipse.sirius.DRepresentation,
       *      org.eclipse.sirius.business.api.session.Session)
       */
      public void cloneAboutToBeRemoved(final DRepresentation clone, Session session) {
        // Remove element from the viewer.
        if (null != _viewer) {
          Runnable removeRunnable = new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              _viewer.remove(clone);
            }
          };
          // Ensure execution in UI thread.
          if (null == Display.getCurrent()) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(removeRunnable);
          } else {
            removeRunnable.run();
          }
        }
      }

      /**
       * @see org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand.ICloneListener#cloneCreated(org.eclipse.sirius.DRepresentation,
       *      org.eclipse.sirius.business.api.session.Session)
       */
      public void cloneCreated(final DRepresentation clone, final Session session) {
        // Add element to the viewer.
        if (null != _viewer) {
          Runnable addRunnable = new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              // Add diagram to project explorer.
              Object parent = ((DSemanticDecorator) clone).getTarget();
              _viewer.add(parent, clone);
              // Refresh representations part.
              SessionManager.INSTANCE.notifyRepresentationCreated(session);
            }
          };
          // Ensure execution in UI thread.
          if (null == Display.getCurrent()) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(addRunnable);
          } else {
            addRunnable.run();
          }
        }
      }
    });
    // This is a special command that handles the undo/redo outside of the recording command implementation.
    // Thus is more adequate to execute it against the command stack directly, rather than use the default behavior.
    TransactionHelper.getEditingDomain(_representations).getCommandStack().execute(command);
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    List<?> selectedElements = selection.toList();
    _representations = getSelectedRepresentations(selectedElements);
    // Enable action only if all selected elements are representations.
    int size = selectedElements.size();
    return (size > 0) && (size == _representations.size());
  }
}
