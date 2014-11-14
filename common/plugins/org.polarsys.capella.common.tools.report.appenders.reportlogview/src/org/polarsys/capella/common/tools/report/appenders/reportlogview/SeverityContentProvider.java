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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;


/**
 * A marker view content provider that groups IMarkers by their severity.
 * 
 */
class SeverityContentProvider extends AbstractMarkerViewContentProvider implements ITreeContentProvider {
  
  /**
   * Maps severity levels to markers. 
   */
  private Map<SeverityLevel, List<IMarker>> markers = new HashMap<SeverityLevel,List<IMarker>>();
  
  public SeverityContentProvider(TreeViewer viewer_p, MarkerViewHelper helper_p, IViewerRefresh refresh_p){
    super(viewer_p, helper_p, refresh_p);
    for (SeverityLevel level : SeverityLevel.values()){
      markers.put(level, new ArrayList<IMarker>());      
    }
    refillCache();
  }
  
  private void clearCache(){
    for (SeverityLevel level : SeverityLevel.values()){
      markers.get(level).clear();
    }
  }
  
  private void refillCache(){
    clearCache();
    Collection<IMarker> found = findMarkers();
    for (IMarker marker : found){
      SeverityLevel level = SeverityLevel.getLevel(marker);
      if (level != null){
        markers.get(level).add(marker);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement_p) {
    List<SeverityLevel> result = new ArrayList<SeverityLevel>();
    for (SeverityLevel level : SeverityLevel.values()){
       if (!markers.get(level).isEmpty()){
         result.add(level);
       }
    }
    return result.toArray();
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getChildren(Object parentElement_p) {
    Object[] result = noChildren;
    List<IMarker> children = markers.get(parentElement_p);
    if (children != null){
      result = children.toArray();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element_p) {
    Object parent = null;
    if (element_p instanceof IMarker){
      parent = SeverityLevel.getLevel((IMarker) element_p);
    }
    return parent; 
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element_p) {
    List<IMarker> children = markers.get(element_p);
    return children != null && children.size() > 0;
  }


  /**
   * {@inheritDoc}
   */
  public synchronized void markerAdded(IMarker marker_p) {
    SeverityLevel level = SeverityLevel.getLevel(marker_p);
    if (level != null){
      markers.get(level).add(marker_p);
      viewerRefresh.refresh();
    }
   }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerDeleted(IMarker marker_p) {
    for (SeverityLevel level : SeverityLevel.values()){
      if (markers.get(level).remove(marker_p)){
        viewerRefresh.refresh();
        break;
      }
    }
  }  
}
