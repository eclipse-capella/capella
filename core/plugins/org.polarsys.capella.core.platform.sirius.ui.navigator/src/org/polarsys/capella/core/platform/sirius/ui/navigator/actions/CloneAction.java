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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand;

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

  @Override
  public void run() {
    TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(_descriptors);
    CapellaCloneDiagramCommand command = new CapellaCloneDiagramCommand(editingDomain, _descriptors);
    editingDomain.getCommandStack().execute(command);
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    List<?> selectedElements = selection.toList();
    _descriptors = RepresentationHelper.getSelectedDescriptors(selectedElements);
    // Enable action only if all selected elements are representations.
    int size = selectedElements.size();
    return (size > 0) && (size == _descriptors.size());
  }
}
