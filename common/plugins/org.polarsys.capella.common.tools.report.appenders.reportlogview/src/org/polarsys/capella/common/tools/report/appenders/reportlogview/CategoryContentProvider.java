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
   * @param viewer_p
   * @param helper_p 
   * @param comparator_p
   */
  public CategoryContentProvider(TreeViewer viewer_p, MarkerViewHelper helper_p, IViewerRefresh refresh_p) {
    super(viewer_p, helper_p, refresh_p);
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
  public synchronized Object[] getElements(Object inputElement_p) {
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
  public synchronized Object[] getChildren(Object parentElement_p) {
    List<Object> children = new ArrayList<Object>();
    if (parentElement_p instanceof Category){
      for (Category subcategory : ((Category) parentElement_p).getChildren()){
        if (isVisible(subcategory)){
          children.add(subcategory);
        }
      }
      List<IMarker> ownedMarkers = markers.get(parentElement_p);
      if (ownedMarkers != null && ownedMarkers.size() > 0){
        children.addAll(ownedMarkers);
      }
    } else if (parentElement_p == MarkerViewHelper.OTHER_CATEGORY){
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
  public synchronized Object getParent(Object element_p) {
    if (element_p instanceof IMarker){
      Category cat = MarkerViewHelper.getCategory((IMarker) element_p);
      if (cat != null){
        return cat;
      } 
      return viewer.getInput();
    } else if (element_p instanceof Category){
      Category parent = ((Category) element_p).getParent();
      if (parent != null){
        return parent;
      } 
      return viewer.getInput();
    } else if (element_p == MarkerViewHelper.OTHER_CATEGORY){
      return viewer.getInput();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element_p) {
    return getChildren(element_p).length > 0;
  }

  private void markerAddedIntern(IMarker marker_p){
    Category cat = MarkerViewHelper.getCategory(marker_p);
    if (cat != null){
      List<IMarker> children = markers.get(cat);
      if (children == null){
        children = new ArrayList<IMarker>();
        markers.put(cat, children);
      }
      children.add(marker_p);
    } else if (MarkerViewHelper.isEcore(marker_p)){
      ecoreMarkers.add(marker_p);
    } else {
      messages.add(marker_p);
    }
    viewerRefresh.refresh();
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized void markerAdded(IMarker marker_p) {
    markerAddedIntern(marker_p);
  }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerDeleted(IMarker marker_p) {
    boolean removed = false;
    
    if (MarkerViewHelper.isEcore(marker_p)){
      ecoreMarkers.remove(marker_p);
      removed = true;
    } 
    
    if (!removed){
      // can't get the category from a marker after it has been deleted, so we must search.
      for (Category c : markers.keySet()){
        List<IMarker> children = markers.get(c);
        if (children != null){
          removed = children.remove(marker_p);
          if (removed){
            break;
          }
        }
      }
    }
    
    if (!removed){
      removed = messages.remove(marker_p);
    }
    
    if (removed){
      viewerRefresh.refresh();
    }
  }

}
