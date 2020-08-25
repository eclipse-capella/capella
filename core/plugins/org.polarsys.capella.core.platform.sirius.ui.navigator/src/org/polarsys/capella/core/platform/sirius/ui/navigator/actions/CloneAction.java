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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand.ICloneListener;

/**
 * Clone action.
 */
public class CloneAction extends BaseSelectionListenerAction {
  /**
   * Latest selection of representations.
   */
  private Collection<DRepresentationDescriptor> _descriptors;

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
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    
    CapellaCloneDiagramCommand command = new CapellaCloneDiagramCommand(_descriptors);
    // Add a listener that refreshes the capella explorer during execution/undo/redo operations.
    command.addCloneListener(new ICloneListener() {
      /**
       * @see org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand.ICloneListener#cloneAboutToBeRemoved(org.eclipse.sirius.DRepresentation,
       *      org.eclipse.sirius.business.api.session.Session)
       */
      @Override
      public void cloneAboutToBeRemoved(final DRepresentation clone, Session session) {
        // Remove element from the viewer.
        if (null != _viewer) {
          Runnable removeRunnable = new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            @Override
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
      @Override
      public void cloneCreated(final DRepresentation clone, final Session session) {
        // Add element to the viewer.
        if (null != _viewer) {
          Runnable addRunnable = new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            @Override
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
    TransactionHelper.getEditingDomain(_descriptors).getCommandStack().execute(command);
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    List<?> selectedElements = selection.toList();
    try {
      _descriptors = RepresentationHelper.getSelectedDescriptors(selectedElements).stream().filter(RepresentationHelper::isValid).collect(Collectors.toList());
      // Enable action only if all selected elements are valid representations.
      int size = selectedElements.size();
      return (size > 0) && (size == _descriptors.size());
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        return false;
      } else {
        throw e;
      }
    }
  }
}
