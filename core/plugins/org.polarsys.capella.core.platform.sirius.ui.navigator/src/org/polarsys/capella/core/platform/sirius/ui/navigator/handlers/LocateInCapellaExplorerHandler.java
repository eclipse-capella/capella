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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * Handler to locate active part selection in Capella Project Explorer.
 */
public class LocateInCapellaExplorerHandler extends AbstractLocateInViewPartHandler {
  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#getTargetedPartId()
   */
  @Override
  protected String getTargetedPartId() {
    return CapellaCommonNavigator.ID;
  }

  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#handleSelection(org.eclipse.jface.viewers.ISelection,
   *      org.eclipse.ui.IWorkbenchPart, org.eclipse.core.commands.ExecutionEvent)
   */
  @Override
  protected IViewPart handleSelection(ISelection selection, IWorkbenchPart activePart, ExecutionEvent event) {
    LocateInCapellaExplorerAction relatedAction = new LocateInCapellaExplorerAction();
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    relatedAction.selectionChanged(delegate, selection);
    relatedAction.setActivePart(delegate, activePart);
    relatedAction.run(delegate);
    return null;
  }
}
