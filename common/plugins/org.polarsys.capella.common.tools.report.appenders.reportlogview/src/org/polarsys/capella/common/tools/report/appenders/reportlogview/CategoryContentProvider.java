/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * A content provider that groups markers into emf constraint categories.
 * 
 */
class CategoryContentProvider extends AbstractMarkerViewContentProvider implements ITreeContentProvider {
  
  // markers that can be associated with a constraint category
  private Map<Category, List<IMarker>> markers;
  
  // special workaround for ecore markers..
  private List<IMarker> ecoreMarkers;
  
  // markers that can not be associated with a category
  private List<IMarker> messages;
  
  /**
   * @param viewer
   * @param helper 
   * @param refresh
   */
  public CategoryContentProvider(TreeViewer viewer, MarkerViewHelper helper, IViewerRefresh refresh) {
    super(viewer, helper, refresh);
    markers = new HashMap<Category, List<IMarker>>();
    ecoreMarkers = new ArrayList<IMarker>();
    messages = new ArrayList<IMarker>();
    
    for (IMarker m : findMarkers()){
      markerAdded(m);
    }
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement) {
    Set<Object> elements = new LinkedHashSet<Object>();
    for (Category cat : markers.keySet()){
      if (!markers.get(cat).isEmpty()){
        Category toplevel = cat;
        while (toplevel.getParent() != null){
          toplevel = toplevel.getParent();
        }
        elements.add(toplevel);
      }
    }
    
    // ecore diagnostics is a quite well workaround.
    elements.add(MarkerViewHelper.OTHER_CATEGORY);
    
    elements.addAll(messages);
    return elements.toArray();
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getChildren(Object parentElement) {
    List<Object> children = new ArrayList<Object>();
    if (parentElement instanceof Category){
      for (Category subcategory : ((Category) parentElement).getChildren()){
        if (isVisible(subcategory)){
          children.add(subcategory);
        }
      }
      List<IMarker> ownedMarkers = markers.get(parentElement);
      if (ownedMarkers != null && ownedMarkers.size() > 0){
        children.addAll(ownedMarkers);
      }
    } else if (parentElement == MarkerViewHelper.OTHER_CATEGORY){
      children.addAll(ecoreMarkers);
    }
    return children.toArray();
  }

  private boolean isVisible(Category category){
    List<IMarker> children = markers.get(category);
    if (children != null && children.size() > 0){
      return true;
    }
    for (Category subcategory : category.getChildren()){
      if (isVisible(subcategory)){
        return true;
      }
    }
    return false;
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized Object getParent(Object element) {
    if (element instanceof IMarker){
      Category cat = MarkerViewHelper.getCategory((IMarker) element);
      if (cat != null){
        return cat;
      } 
      return viewer.getInput();
    } else if (element instanceof Category){
      Category parent = ((Category) element).getParent();
      if (parent != null){
        return parent;
      } 
      return viewer.getInput();
    } else if (element == MarkerViewHelper.OTHER_CATEGORY){
      return viewer.getInput();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }

  private void markerAddedIntern(IMarker marker){
    Category cat = MarkerViewHelper.getCategory(marker);
    if (cat != null){
      List<IMarker> children = markers.get(cat);
      if (children == null){
        children = new ArrayList<IMarker>();
        markers.put(cat, children);
      }
      children.add(marker);
    } else if (MarkerViewHelper.isEcore(marker)){
      ecoreMarkers.add(marker);
    } else {
      messages.add(marker);
    }
    viewerRefresh.refresh();
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized void markerAdded(IMarker marker) {
    markerAddedIntern(marker);
  }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerDeleted(IMarker marker) {
    boolean removed = false;
    
    if (MarkerViewHelper.isEcore(marker)){
      ecoreMarkers.remove(marker);
      removed = true;
    } 
    
    if (!removed){
      // can't get the category from a marker after it has been deleted, so we must search.
      for (Category c : markers.keySet()){
        List<IMarker> children = markers.get(c);
        if (children != null){
          removed = children.remove(marker);
          if (removed){
            break;
          }
        }
      }
    }
    
    if (!removed){
      removed = messages.remove(marker);
    }
    
    if (removed){
      viewerRefresh.refresh();
    }
  }

}
