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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * A marker view content provider that groups markers by their validation rule id.
 * Markers that cannot be associated to a validation rule id appear as top level 
 * elements in the tree. 
 * 
 *
 */
class RuleIdContentProvider extends AbstractMarkerViewContentProvider implements ITreeContentProvider {
  
  private Map<Object, List<IMarker>> markers; // markers that live under a validation rule id
  private List<IMarker> messages;             // markers that live directly under the root (e.g logs from transition)
  
  public RuleIdContentProvider(TreeViewer viewer_p, MarkerViewHelper helper_p, IViewerRefresh refresh_p){
    super(viewer_p, helper_p, refresh_p);
    refillCache();
  }
  
  private void refillCache(){
    markers = new LinkedHashMap<Object, List<IMarker>>();
    messages = new ArrayList<IMarker>();
    
    for (IMarker marker : findMarkers()){
      markerAddedIntern(marker);
    }
  } 

  // retrieve the key for a marker if it is associated to a 
  // constraint descriptor or diagnostic,
  // null otherwise.
  private Object findKey(IMarker marker_p){
    Object key = MarkerViewHelper.getConstraintDescriptor(marker_p);
    if (key == null){
      Diagnostic d = MarkerViewHelper.getDiagnostic(marker_p);
      if (d != null){
        key = MarkerViewHelper.OTHER_RULES;
      }
    }
    return key;
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element_p) {
    Object parent = null;
     if (element_p instanceof IMarker){
       parent = MarkerViewHelper.getConstraintDescriptor((IMarker) element_p);
       if (parent == null){
         Diagnostic d = MarkerViewHelper.getDiagnostic((IMarker) element_p);
         if (d != null){
           parent = MarkerViewHelper.OTHER_RULES;
         }
       }
     }
     return parent;
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement_p) {
    Object[] elems = new Object[markers.size() + messages.size()];
    int i = 0;
    Set<?> keys = markers.keySet();
    for (Object o : keys){
      elems[i++] = o;
    }
    for (Object o : messages){
      elems[i++] = o;
    }
    return elems;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getChildren(Object parentElement_p) {
    List<IMarker> children = markers.get(parentElement_p);
    if (children != null){
      return children.toArray();
    }  
    return noChildren;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element_p) {
    if (element_p == viewer.getInput()){
      Set<?> keys = markers.keySet();
      for (Object key : keys){
        List<IMarker> children = markers.get(key);
        if (children != null && children.size() > 0){
          return true;
        }
      }
      return !messages.isEmpty();
    } 

    List<IMarker> children = markers.get(element_p);
    if (children != null && children.size() > 0){
      return true;
    }
    return false;
  }

  private void markerAddedIntern(IMarker marker_p){
    Object key = findKey(marker_p);
    if (key != null){
      List<IMarker> markersForKey = markers.get(key);
      if (markersForKey == null){
        markersForKey = new ArrayList<IMarker>();
        markers.put(key, markersForKey);
      }
      markersForKey.add(marker_p);
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
    for (Object key : markers.keySet()){
      List<IMarker> children = markers.get(key);
      if (children != null){
        removed = children.remove(marker_p);
        if (removed){
          break;
        }
      }
    }
    if (!removed){
      messages.remove(marker_p);
    }
    viewerRefresh.refresh();
  }
  
}
