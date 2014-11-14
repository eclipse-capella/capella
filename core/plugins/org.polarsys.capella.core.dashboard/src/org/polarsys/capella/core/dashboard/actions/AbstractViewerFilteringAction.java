/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.dashboard.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;

/**
 */
public abstract class AbstractViewerFilteringAction extends Action {
  /**
   * {@link AbstractCapellaArchitectureDashboardPage} reference.
   */
  private AbstractCapellaArchitectureDashboardPage _capellaArchitecturePage;

  /**
   * Constructor.
   */
  public AbstractViewerFilteringAction(AbstractCapellaArchitectureDashboardPage capellaArchitecturePage_p) {
    super(null, AS_CHECK_BOX);
    setImageDescriptor(CapellaDashboardActivator.getDefault().getImageDescriptor(IImageKeys.IMG_SYNCRONIZE_VIEWER));
    _capellaArchitecturePage = capellaArchitecturePage_p;
  }

  /**
   * Get diagram viewer filter based on {@link #getFilteredViewpoint()} and {@link #getRetainedRepresentationDescriptions()}.
   * @return
   */
  protected ViewerFilter getDiagramViewerFilter() {
    return new ViewerFilter() {
      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        boolean selected = true;
        // Filter out viewpoint if any.
        if (element_p instanceof ViewpointItem) {
          ViewpointItem viewpointItem = (ViewpointItem) element_p;
          Viewpoint viewpoint = (Viewpoint) viewpointItem.getWrappedObject();
          if (viewpoint.getName().equals(getFilteredViewpoint())) {
            selected = false;
          }
        }
        // Filter representations.
        else if (element_p instanceof RepresentationDescriptionItem) {
          selected = isRepresentationDescriptionItemTypeSelected((RepresentationDescriptionItem) element_p, getRetainedRepresentationDescriptions());
        }
        // Filter diagrams.
        else if (element_p instanceof DSemanticDiagram) {
          DSemanticDiagram semanticDiagram = (DSemanticDiagram) element_p;
          selected = isDiagramSelected(semanticDiagram.getTarget(), semanticDiagram.getDescription());
        }
        return selected;
      }
    };
  }

  /**
   * Get Filtered viewpoint.<br>
   * Default implementation returns <code>null</code>.
   * @return
   */
  protected String getFilteredViewpoint() {
    return null;
  }

  /**
   * Get the Capella architecture page this action is working on.
   * @return the capellaArchitecturePage
   */
  protected AbstractCapellaArchitectureDashboardPage getCapellaArchitecturePage() {
    return _capellaArchitecturePage;
  }

  /**
   * Get retained {@link RepresentationDescriptionItem} using name attribute.<br>
   * @return a not <code>null</code> list.
   */
  protected abstract List<String> getRetainedRepresentationDescriptions();

  /**
   * Is diagram selected ?<br>
   * Default implementation relies on {@link AbstractCapellaArchitectureDashboardPage#getFilteringMetaClassForCommonViewpoint()}
   * @param semanticElement_p
   * @param diagramDescription_p
   * @return
   */
  protected boolean isDiagramSelected(EObject semanticElement_p, DiagramDescription diagramDescription_p) {
    return EcoreUtil2.isContainedBy(semanticElement_p, getCapellaArchitecturePage().getFilteringMetaClassForCommonViewpoint());
  }

  /**
   * Is a {@link RepresentationDescriptionItem} selected ?
   * @param representationDescriptionItem_p
   * @return <code>true</code> means given <code>representationDescriptionItem_p</code> is selected i.e not filtered.
   */
  protected boolean isRepresentationDescriptionItemTypeSelected(RepresentationDescriptionItem representationDescriptionItem_p,
      List<String> retainedDiagramTypeNames_p) {
    boolean selected = true;
    RepresentationDescription representationDescription = (RepresentationDescription) representationDescriptionItem_p.getWrappedObject();
    String diagramTypeName = representationDescription.getName();
    if (retainedDiagramTypeNames_p.contains(diagramTypeName)) {
      selected = true;
    } else {
      selected = false;
    }
    return selected;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    ViewerFilter diagramViewerFilter = getDiagramViewerFilter();
    if (null != diagramViewerFilter) {
      if (isChecked()) {
        // End-user has just clicked the action to enable filter.
        _capellaArchitecturePage.setViewerFilter(diagramViewerFilter, this);
      } else {
        // End-user has just clicked the action to disable filter.
        // Don't provide this current action because we are deactivating the same filter.
        // The page already handles to uncheck this action.
        _capellaArchitecturePage.setViewerFilter(null, null);
      }
    }
  }
}
