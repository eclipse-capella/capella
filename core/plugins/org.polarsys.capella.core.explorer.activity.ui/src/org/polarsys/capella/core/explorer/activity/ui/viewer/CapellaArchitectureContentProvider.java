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
package org.polarsys.capella.core.explorer.activity.ui.viewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointsFolderItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 * Content provider used to display diagrams in the {@link TreeViewer} of Architecture pages.
 */
@SuppressWarnings("restriction")
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
   * Constructor.
   * 
   * @param handledViewpoint
   *          names of the viewpoints displayed here.
   * @param filteringMetaClassForCommonViewpoint
   *          EClass used to filter Common viewpoint.
   */
  public CapellaArchitectureContentProvider(EClass filteringMetaClassForCommonViewpoint) {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
    _filteringMetaClassForCommonViewpoint = filteringMetaClassForCommonViewpoint;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    // Handle Session.
    if (parentElement instanceof Session) {
      Session session = (Session) parentElement;
      return getSessionChildren(session);
    }
    // Handle ViewpointItem.
    else if (parentElement instanceof ViewpointItem) {
      ViewpointItem viewpointItem = (ViewpointItem) parentElement;
      return handleViewPointItem(viewpointItem, viewpointItem.getChildren());
    }
    // Handle all wrapped objects
    else if (parentElement instanceof RepresentationDescriptionItem) {
      RepresentationDescriptionItem representationDescriptionItem = (RepresentationDescriptionItem) parentElement;
      return handleRepresentationDescriptionItem(representationDescriptionItem);
    }
    return NO_CHILD;
  }

  /**
   * Handle RepresentationDescriptionItem case.
   * 
   * @param representationDescriptionItem
   * @return
   */
  private Object[] handleRepresentationDescriptionItem(RepresentationDescriptionItem representationDescriptionItem) {
    // Get children for current representation description item.
    Collection<?> representationItems = representationDescriptionItem.getChildren();
    // Handle Common viewpoint children in a specific way:
    // We don't want to see Common diagrams hold by another Analysis level.
    // For instance in SA page we don't want Common diagrams from LA, PA, ...
    Iterator<?> iterator = representationItems.iterator();
    while (iterator.hasNext()) {
      Object item = iterator.next();
      if (item instanceof ItemWrapper) {
        item = ((ItemWrapper) item).getWrappedObject();
      }
      if (item instanceof DRepresentationDescriptor) {
        DRepresentationDescriptor representationDescriptor = (DRepresentationDescriptor) item;
        BlockArchitecture rootBlockArchitecture = BlockArchitectureExt
            .getRootBlockArchitecture(representationDescriptor.getTarget());
        if (rootBlockArchitecture == null
            || !_filteringMetaClassForCommonViewpoint.isSuperTypeOf(rootBlockArchitecture.eClass())) {
          iterator.remove();
        }
      }
    }
    if (!representationItems.isEmpty()) {
      return representationItems.toArray();
    }
    return NO_CHILD;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  /**
   * Get session children.<br>
   * Only not empty {@link ViewpointItem} are considered.
   * 
   * @param session
   * @return a not <code>null</code> array.
   */
  private Object[] getSessionChildren(final Session session) {
    Collection<Object> viewpointItems = new ArrayList<>();

    ViewpointsFolderItem viewpointsFolderItem = new ViewpointsFolderItemImpl(session, session);
    for (Object viewpointItem : viewpointsFolderItem.getChildren()) {
      // check if the viewpointItem has diagrams. If so, the viewpointItem is displayed.
      if (getChildren(viewpointItem).length > 0) {
        viewpointItems.add(viewpointItem);
      }
    }

    return viewpointItems.toArray();
  }

  /**
   * Handle ViewPointItem case.
   * 
   * @param element
   * @param children
   * @return
   */
  private Object[] handleViewPointItem(ViewpointItem element, Collection<? extends Object> children) {
    ArrayList<Object> selectedChildren = new ArrayList<Object>(0);
    // Only RepresentationDescriptionItem with children are considered.
    for (Object representationDescriptionItem : new ArrayList<Object>(children)) {
      ItemWrapper item = (ItemWrapper) representationDescriptionItem;
      Collection<?> itemChildren = item.getChildren();
      if (!itemChildren.isEmpty()) {
        // In addition, check its children are not filtered out.
        if (getChildren(representationDescriptionItem).length > 0) {
          selectedChildren.add(representationDescriptionItem);
        }
      }
    }
    return selectedChildren.toArray();
  }

  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }
}
