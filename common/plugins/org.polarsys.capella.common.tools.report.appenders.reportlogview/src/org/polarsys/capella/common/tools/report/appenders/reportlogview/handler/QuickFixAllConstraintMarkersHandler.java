/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
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

  private static final String menuId = "org.polarsys.capella.core.validation.quickfixAllSimilar"; //$NON-NLS-1$
  private static final String menuImage = "quickfixAll-repository.png"; //$NON-NLS-1$

  public static IMarkerHelpRegistry getMarkerRegistry() {
    return IDE.getMarkerHelpRegistry();
  }

  @Override
  protected IContributionItem[] getContributionItems() {

    List<IMarker> selection = getSelectedMarkers();

    if (selection.isEmpty()) {
      return NO_CONTRIBUTION_ITEM;
    }

    Collection<IContributionItem> items = new ArrayList<>();
    
    IConstraintDescriptor selectedMarkerDesc = MarkerViewHelper.getConstraintDescriptor(selection.get(0));
    Diagnostic diagnostic = selection.get(0).getAdapter(Diagnostic.class);
    Diagnostic basicDiagnostic = null;
    
    if(diagnostic instanceof BasicDiagnostic) {
      basicDiagnostic = (BasicDiagnostic) diagnostic;
    }

    if (selectedMarkerDesc != null || basicDiagnostic != null) {

      List<IMarker> sameConstraintMarkers = new ArrayList<>();

      Collection<IMarker> allMarkers = LightMarkerRegistry.getInstance().getMarkers();

      // get all markers that has the same constraint id
      for (IMarker marker : allMarkers) {

        if ((selectedMarkerDesc != null) && (MarkerViewHelper.getConstraintDescriptor(marker) == selectedMarkerDesc)) {
          sameConstraintMarkers.add(marker);
        }
        else if (basicDiagnostic != null) {
          Diagnostic diag = marker.getAdapter(Diagnostic.class);
          if (diag != null && diag.getSource() != null && diag.getSource().equals(basicDiagnostic.getSource())
              && (diag.getCode() == basicDiagnostic.getCode())) {
            sameConstraintMarkers.add(marker);
          }
        }
      }
      // no other similar markers
      if (sameConstraintMarkers.size() <= 1) {
        return NO_CONTRIBUTION_ITEM;
      }

      // check if the resolutions can solve all the markers (normally it
      // should, since they have the same constraint id)
      for (IMarkerResolution res : getMarkerRegistry().getResolutions(selection.get(0))) {

        Set<IMarker> markersToFix = new HashSet<>();
        if (res instanceof WorkbenchMarkerResolution) {

          IMarker[] otherMarkers = ((WorkbenchMarkerResolution) res).findOtherMarkers(sameConstraintMarkers
              .toArray(new IMarker[0]));
          for (IMarker otherMarker : otherMarkers) {
            markersToFix.add(otherMarker);
          }
        }

        // only create a menu item for the resolution that can handle
        // all the similar markers (i.e. having the same constraintDescriptor)
        if (markersToFix.size()>1 && isInQuickFixAllSimilar(res, markersToFix)) {
          items.add(createContributionItem(markersToFix, res));
        }
      }
    }

    if (!items.isEmpty()) {
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

  @Override
  protected List<IMarker> getSelectedMarkers() {

    List<IMarker> result = new ArrayList<>();
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkbenchPart activePart = activePage.getActivePart();
    if (activePart instanceof MarkerView) {
      MarkerView view = (MarkerView) activePart;
      ISelection selection = view.getViewer().getSelection();
      if (selection instanceof StructuredSelection && ((StructuredSelection) selection).size() > 0) {
        for (Iterator<?> it = ((StructuredSelection) selection).iterator(); it.hasNext();) {
          Object element = it.next();
          if (element instanceof IMarker) {
            result.add((IMarker) element);
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
}
