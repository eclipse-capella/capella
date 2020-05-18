/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorFilterService;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * This action will be executed if the navigated elements are not visible in the project explorer. Its responsibilities
 * is to find the effective filters applied on the navigated elements and deactivate them, so that the navigated
 * elements will be visible and focused on the project explorer.
 */
public class LocateFilteredElementsInCommonNavigatorAction {
  
  private String commonNavigatorID;
  
  public LocateFilteredElementsInCommonNavigatorAction(String commonNavigatorID){
    Assert.isLegal(commonNavigatorID != null && !commonNavigatorID.isEmpty());
    this.commonNavigatorID = commonNavigatorID;
  }

  /**
   * 
   * @param selection The structured selection on others views differ from the project explorer view.
   */
  public void run(IStructuredSelection selection) {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    // Get the View.
    IViewPart viewPart = activePage.findView(commonNavigatorID);
    Assert.isLegal(viewPart instanceof CommonNavigator);
    
    CommonNavigator commonNavigator = (CommonNavigator) viewPart;
    INavigatorContentService capellaNavigatorContentService = commonNavigator.getNavigatorContentService();
    NavigatorFilterService capellaNavigatorFilterService = (NavigatorFilterService) capellaNavigatorContentService
        .getFilterService();

    // The active filter makes the element hidden.
    Collection<ICommonFilterDescriptor> effectiveFilterDescriptors = new LinkedHashSet<>();
    // The active filter id makes the element hidden.
    Collection<String> effectiveFilterIds = new LinkedHashSet<>();
    // The active filter
    Collection<String> activeFilterIds = new LinkedHashSet<>();

    ICommonFilterDescriptor[] visibleFilterDescriptors = capellaNavigatorFilterService.getVisibleFilterDescriptors();
    for (ICommonFilterDescriptor filterDescriptor : visibleFilterDescriptors) {
      ViewerFilter viewerFilter = capellaNavigatorFilterService.getViewerFilter(filterDescriptor);
      for (Object element : selection.toArray()) {
        if (capellaNavigatorFilterService.isActive(filterDescriptor.getId())) {
          activeFilterIds.add(filterDescriptor.getId());
          if (!select(commonNavigator, viewerFilter, element)) {
            effectiveFilterDescriptors.add(filterDescriptor);
            effectiveFilterIds.add(filterDescriptor.getId());
          }
        }
      }
    }

    if (!effectiveFilterDescriptors.isEmpty()) {
      StringBuilder dialogMessageBuilder = new StringBuilder();
      dialogMessageBuilder.append(Messages.LocateInCommonNavigator_SelectedElementNotVisible_0);
      for (ICommonFilterDescriptor filterDescriptor : effectiveFilterDescriptors) {
        dialogMessageBuilder
            .append(" - " + filterDescriptor.getName() + ": " + filterDescriptor.getDescription() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      dialogMessageBuilder.append(Messages.LocateInCommonNavigator_SelectedElementNotVisible_1);

      boolean openFilterSelectionDialog = MessageDialog.openQuestion(
          PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
          NLS.bind(Messages.LocateInCommonNavigator_SelectedElementNotVisible_Title, commonNavigator.getPartName()),
          dialogMessageBuilder.toString());
      if (openFilterSelectionDialog) {
        activeFilterIds.removeAll(effectiveFilterIds);
        capellaNavigatorFilterService.activateFilterIdsAndUpdateViewer(activeFilterIds.toArray(new String[0]));
      }
    }
  }

  protected boolean select(CommonNavigator commonNavigator, ViewerFilter viewerFilter, Object element) {
    boolean select = true;
    Object parent = element;
    while (select && parent != null && !(parent instanceof IFile)) {
      select = viewerFilter.select(commonNavigator.getCommonViewer(), null, parent);
      ITreeContentProvider contentProvider = (ITreeContentProvider) commonNavigator.getCommonViewer()
          .getContentProvider();
      parent = contentProvider.getParent(parent);
    }
    return select;
  }
}
