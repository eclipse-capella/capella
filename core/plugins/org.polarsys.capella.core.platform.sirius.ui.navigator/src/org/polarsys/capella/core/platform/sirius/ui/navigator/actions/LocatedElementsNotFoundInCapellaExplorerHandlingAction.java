/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorFilterService;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * This action will be executed if the navigated elements are not visible in the project explorer. Its responsibilities
 * is to find the effective filters applied on the navigated elements and deactivate them, so that the navigated
 * elements will be visible and focused on the project explorer.
 * 
 * @author Cong Bang DO
 *
 */
public class LocatedElementsNotFoundInCapellaExplorerHandlingAction {

  /**
   * 
   * @param selection The structured selection on others views differ from the project explorer view.
   */
  public void run(IStructuredSelection selection) {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    // Get the Capella Explorer.
    CapellaCommonNavigator capellaNavigator = (CapellaCommonNavigator) activePage.findView(CapellaCommonNavigator.ID);
    INavigatorContentService capellaNavigatorContentService = capellaNavigator.getNavigatorContentService();
    NavigatorFilterService capellaNavigatorFilterService = (NavigatorFilterService) capellaNavigatorContentService
        .getFilterService();

    // The active filter makes the element hidden.
    List<ICommonFilterDescriptor> effectiveFilterDescriptors = new ArrayList<ICommonFilterDescriptor>();
    // The active filter id makes the element hidden.
    List<String> effectiveFilterIds = new ArrayList<String>();
    // The active filter
    List<String> activeFilterIds = new ArrayList<String>();

    ICommonFilterDescriptor[] visibleFilterDescriptors = capellaNavigatorFilterService.getVisibleFilterDescriptors();
    for (ICommonFilterDescriptor filterDescriptor : visibleFilterDescriptors) {
      ViewerFilter viewerFilter = capellaNavigatorFilterService.getViewerFilter(filterDescriptor);
      for (Object element : selection.toArray()) {
        if (capellaNavigatorFilterService.isActive(filterDescriptor.getId())) {
          activeFilterIds.add(filterDescriptor.getId());
          if (!viewerFilter.select(capellaNavigator.getCommonViewer(), null, element)) {
            effectiveFilterDescriptors.add(filterDescriptor);
            effectiveFilterIds.add(filterDescriptor.getId());
          }
        }
      }
    }

    if (effectiveFilterDescriptors.size() > 0) {
      StringBuilder dialogMessageBuilder = new StringBuilder();
      dialogMessageBuilder.append(Messages.LocateInCapellaExplorerAction_SelectedElementNotVisible_0);
      for (ICommonFilterDescriptor filterDescriptor : effectiveFilterDescriptors) {
        dialogMessageBuilder
            .append(" - " + filterDescriptor.getName() + ": " + filterDescriptor.getDescription() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      dialogMessageBuilder.append(Messages.LocateInCapellaExplorerAction_SelectedElementNotVisible_4);

      boolean openFilterSelectionDialog = MessageDialog.openQuestion(
          PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
          Messages.LocateInCapellaExplorerAction_SelectedElementNotVisible_Title, dialogMessageBuilder.toString());
      if (openFilterSelectionDialog) {
        activeFilterIds.removeAll(effectiveFilterIds);
        capellaNavigatorFilterService.activateFilterIdsAndUpdateViewer(activeFilterIds.toArray(new String[0]));
      }
    } else {
      MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
          Messages.LocateInCapellaExplorerAction_SelectedElementNotVisible_Title,
          Messages.LocateInCapellaExplorerAction_SelectedElementNotVisible_5);
    }
  }
}
