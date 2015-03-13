/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IMarkerHelpRegistry;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.Messages;

public class QuickFixAllConstraintMarkersHandler extends QuickfixHandler {

  private final String menuId = "org.polarsys.capella.core.validation.quickfixAllSimilar"; //$NON-NLS-1$
  private final String menuImage = "quickfixAll-repository.png"; //$NON-NLS-1$

  public static IMarkerHelpRegistry getMarkerRegistry() {
    return IDE.getMarkerHelpRegistry();
  }

  @Override
  protected IContributionItem[] getContributionItems() {

    List<IMarker> selection = getSelectedMarkers();

    if (selection.isEmpty()) {
      return NO_CONTRIBUTION_ITEM;
    }

    Collection<IContributionItem> items = new ArrayList<IContributionItem>();

    IConstraintDescriptor selectedMarkerDesc = MarkerViewHelper.getConstraintDescriptor(selection.get(0));

    if (selectedMarkerDesc != null) {

      List<IMarker> sameConstraintMarkers = new ArrayList<IMarker>();

      Collection<IMarker> allMarkers = LightMarkerRegistry.getInstance().getMarkers();

      // get all markers that has the same constraint id
      for (IMarker marker : allMarkers) {

        if (MarkerViewHelper.getConstraintDescriptor(marker) == selectedMarkerDesc) {
          sameConstraintMarkers.add(marker);
        }
      }
      // no other similar markers
      if (sameConstraintMarkers.size() <= 1) {
        return NO_CONTRIBUTION_ITEM;
      }

      // check if the resolutions can solve all the markers (normally it
      // should, since they have the same constraint id)
      for (IMarkerResolution res : getMarkerRegistry().getResolutions(selection.get(0))) {

        Set<IMarker> markersToFix = new HashSet<IMarker>();
        if (res instanceof WorkbenchMarkerResolution) {

          IMarker[] otherMarkers = ((WorkbenchMarkerResolution) res).findOtherMarkers(sameConstraintMarkers
              .toArray(new IMarker[0]));
          for (IMarker otherMarker : otherMarkers) {
            markersToFix.add(otherMarker);
          }
        }

        // only create a menu item for the resolution that can handle
        // all the similar markers (i.e. having the same
        // constraintDescriptor)
        if (markersToFix.containsAll(sameConstraintMarkers) && isInQuickFixAllSimilar(res, markersToFix)) {
          items.add(createContributionItem(markersToFix, res));
        }
      }
    }

    if (items.size() > 0) {
      ImageDescriptor image = MarkerViewPlugin.getDefault().getImageDescriptor(menuImage);
      String text = Messages.MarkerView_QuickfixAll_Command_Name;
      MenuManager manager = new MenuManager(text, image, menuId);
      for (IContributionItem item : items) {
        manager.add(item);
      }
      return new IContributionItem[] { manager };
    }

    return NO_CONTRIBUTION_ITEM;
  }

  // @Deprecated
  // private ISelection getSelection() {
  // ISelection selection = null;
  //
  // IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
  // IWorkbenchPart activePart = activePage.getActivePart();
  // if (activePart instanceof MarkerView) {
  // MarkerView view = (MarkerView) activePart;
  // selection = view.getViewer().getSelection();
  // }
  // return selection;
  // }

  @Override
  protected List<IMarker> getSelectedMarkers() {

    List<IMarker> result = new ArrayList<IMarker>();
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkbenchPart activePart = activePage.getActivePart();
    if (activePart instanceof MarkerView) {
      MarkerView view = (MarkerView) activePart;
      ISelection selection = view.getViewer().getSelection();
      if (selection instanceof StructuredSelection) {
        if (((StructuredSelection) selection).size() > 0) {
          for (Iterator<?> it = ((StructuredSelection) selection).iterator(); it.hasNext();) {
            Object element = it.next();
            if (element instanceof IMarker) {
              result.add((IMarker) element);
            }
          }
        }
      }
    }
    return result;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  // private IConstraintDescriptor getConstraintDescriptor(ISelection selection) {
  // IConstraintDescriptor descriptor = null;
  // if (selection instanceof IStructuredSelection) {
  // IStructuredSelection ssel = (IStructuredSelection) selection;
  // Object first = ssel.getFirstElement();
  // if ((first != null) && (first instanceof IMarker)) {
  // IMarker marker = (IMarker) first;
  // descriptor = MarkerViewHelper.getConstraintDescriptor(marker);
  // }
  // }
  // return descriptor;
  // }

}
