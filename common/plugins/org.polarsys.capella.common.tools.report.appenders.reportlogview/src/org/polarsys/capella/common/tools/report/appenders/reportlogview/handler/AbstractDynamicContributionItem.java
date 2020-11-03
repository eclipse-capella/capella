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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;

/**
 */
class AbstractDynamicContributionItem extends CompoundContributionItem {

  /**
   * No contribution.
   */
  protected static final IContributionItem[] NO_CONTRIBUTION_ITEM = new IContributionItem[0];

  /**
   * @see org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    return NO_CONTRIBUTION_ITEM;
  }

  protected boolean hasContributionItems() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return hasContributionItems();
  }
  
  protected IMarker getCurrentMarker() {
    IMarker result = null;
    List<IMarker> markers = getSelectedMarkers();
    if (markers.size() > 0){
      result = markers.iterator().next();
    }
    return result;
  }
  
  
  protected List<IMarker> getSelectedMarkers(){

    List<IMarker> result = new ArrayList<IMarker>();
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkbenchPart activePart = activePage.getActivePart();
    if (activePart instanceof MarkerView) {
      MarkerView view = (MarkerView) activePart;
      ISelection selection = view.getViewer().getSelection();
      if (selection instanceof StructuredSelection) {
        if (((StructuredSelection) selection).size() > 0) {
          for (Iterator<?> it = ((StructuredSelection)selection).iterator(); it.hasNext();){
            Object element = it.next();
            if (element instanceof IMarker){
              result.add((IMarker) element);
            }
          }
        }
      }
    }
    return result;
  }


}
