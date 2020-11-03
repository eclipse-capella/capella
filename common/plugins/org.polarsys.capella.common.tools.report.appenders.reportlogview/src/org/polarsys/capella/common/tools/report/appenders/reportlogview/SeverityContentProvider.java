/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  
  public SeverityContentProvider(TreeViewer viewer, MarkerViewHelper helper, IViewerRefresh refresh){
    super(viewer, helper, refresh);
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
  public synchronized Object[] getElements(Object inputElement) {
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
  public synchronized Object[] getChildren(Object parentElement) {
    Object[] result = noChildren;
    List<IMarker> children = markers.get(parentElement);
    if (children != null){
      result = children.toArray();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element) {
    Object parent = null;
    if (element instanceof IMarker){
      parent = SeverityLevel.getLevel((IMarker) element);
    }
    return parent; 
  }
  
  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element) {
    List<IMarker> children = markers.get(element);
    return children != null && children.size() > 0;
  }


  /**
   * {@inheritDoc}
   */
  public synchronized void markerAdded(IMarker marker) {
    SeverityLevel level = SeverityLevel.getLevel(marker);
    if (level != null){
      markers.get(level).add(marker);
      viewerRefresh.refresh();
    }
   }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerDeleted(IMarker marker) {
    for (SeverityLevel level : SeverityLevel.values()){
      if (markers.get(level).remove(marker)){
        viewerRefresh.refresh();
        break;
      }
    }
  }  
}
