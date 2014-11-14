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
package org.polarsys.capella.core.dashboard.viewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointsFolderItemImpl;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

/**
 * Content provider used to display diagrams in the {@link TreeViewer} of Architecture pages.
 */
public class CapellaArchitectureContentProvider extends AdapterFactoryContentProvider {
  /**
   * No Child constant.
   */
  private static final Object[] NO_CHILD = new Object[0];
  /**
   * Filtering metaclass for representations contained in Common viewpoint.
   */
  private EClass _filteringMetaClassForCommonViewpoint;
  /**
   * Handled viewpoint.
   */
  private String _handledViewpoint;

  /**
   * Constructor.
   * @param handledViewpoint_p name of the viewpoint displayed here.
   * @param filteringMetaClassForCommonViewpoint_p EClass used to filter Common viewpoint.
   */
  public CapellaArchitectureContentProvider(String handledViewpoint_p, EClass filteringMetaClassForCommonViewpoint_p) {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
    _handledViewpoint = handledViewpoint_p;
    _filteringMetaClassForCommonViewpoint = filteringMetaClassForCommonViewpoint_p;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object parentElement_p) {
    Object[] result = NO_CHILD;
    // Handle Session.
    if (parentElement_p instanceof Session) {
      result = getSessionChildren((Session) parentElement_p);
    }
    // Handle ViewpointItem.
    else if (parentElement_p instanceof ViewpointItem) {
      ViewpointItem viewpointItem = (ViewpointItem) parentElement_p;
      result = handleViewPointItem(parentElement_p, viewpointItem.getChildren());
    }
    // Handle all wrapped objects
    else if (parentElement_p instanceof RepresentationDescriptionItem) {
      RepresentationDescriptionItem representationDescriptionItem = (RepresentationDescriptionItem) parentElement_p;
      // Get children for current representation description item.
      Collection<?> representationItems = representationDescriptionItem.getChildren();
      // Get its parent i.e its viewpoint.
      RepresentationDescription representationDescription = (RepresentationDescription) representationDescriptionItem.getWrappedObject();
      Viewpoint viewpoint = (Viewpoint) representationDescription.eContainer();
      // Handle Common viewpoint children in a specific way: we don't want to see Common diagrams hold by another Analysis level.
      // For instance in SA page we don't want Common diagrams from LA, PA, ...
      if (IViewpointNameConstants.COMMON_VIEWPOINT_NAME.equals(viewpoint.getName())) {
        Iterator<?> iterator = representationItems.iterator();
        while (iterator.hasNext()) {
          Object item = iterator.next();
          if (item instanceof ItemWrapper) {
            item = ((ItemWrapper) item).getWrappedObject();
          }
          // Only considering semantic diagrams & tables, what else ?
          if ((item instanceof DSemanticDiagram) || (item instanceof DTable)) {
            DSemanticDecorator representationInCommon = (DSemanticDecorator) item;
            // Remove if target element is either the filtering metaclass or contain in something related to the filtering metaclass.
            if (!_filteringMetaClassForCommonViewpoint.isSuperTypeOf(representationInCommon.getTarget().eClass())
                && !EcoreUtil2.isContainedBy(representationInCommon.getTarget(), _filteringMetaClassForCommonViewpoint)) {
              iterator.remove();
            }
          }
        }
      }
      if (!representationItems.isEmpty()) {
        result = representationItems.toArray();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement_p) {
    return getChildren(inputElement_p);
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element_p) {
    return null;
  }

  /**
   * Get session children.<br>
   * Only not empty {@link ViewpointItem} are considered.
   * @param session
   * @return a not <code>null</code> array.
   */
  private Object[] getSessionChildren(final Session session) {
    ViewpointsFolderItem viewpointsFolderItem = new ViewpointsFolderItemImpl(session, session);
    Collection<?> viewpointItems = viewpointsFolderItem.getChildren();
    Iterator<?> iterator = viewpointItems.iterator();
    while (iterator.hasNext()) {
      Object item = iterator.next();
      if (item instanceof ViewpointItem) {
        ViewpointItem viewpointItem = (ViewpointItem) item;
        Viewpoint viewpoint = (Viewpoint) viewpointItem.getWrappedObject();
        String viewpointName = viewpoint.getName();
        // Remove non handled viewpoint item by the related dashboard page.
        if (!IViewpointNameConstants.COMMON_VIEWPOINT_NAME.equals(viewpointName) && !viewpointName.equals(_handledViewpoint)) {
          iterator.remove();
        }
      }
    }
    return viewpointItems.toArray();
  }

  /**
   * Handle ViewPointItem case.
   * @param element_p
   * @param children_p
   * @return
   */
  private Object[] handleViewPointItem(Object element_p, Collection<? extends Object> children_p) {
    List<Object> result = new ArrayList<Object>(children_p);
    // Add a specific handling on Sirius org.polarsys.capella.core.dashboard.internal type ViewpointItem .
    // Model Content view handles that case with a filter. The disadvantage of the filter is : an expandable icon is displayed because hasChildren is based
    // on doGetChildren().length. Thus, after clicking on the expandable nothing is displayed, this is a weird behavior for the end-user.
    if (element_p instanceof ViewpointItem) {
      ArrayList<Object> selectedChildren = new ArrayList<Object>(0);
      // Only RepresentationDescriptionItem with children are considered.
      for (Object representationDescriptionItem : result) {
        ItemWrapper item = (ItemWrapper) representationDescriptionItem;
        Collection<?> itemChildren = item.getChildren();
        if (!itemChildren.isEmpty()) {
          // In addition, check its children are not filtered out.
          if (getChildren(representationDescriptionItem).length > 0) {
            selectedChildren.add(representationDescriptionItem);
          }
        }
      }
      result = selectedChildren;
    }
    return result.toArray();
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  @Override
  public boolean hasChildren(Object element_p) {
    return getChildren(element_p).length > 0;
  }
}
