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
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * This content provider simulates a table. All elements are leaves.
 * 
 */
class CanonicalContentProvider extends AbstractMarkerViewContentProvider implements ITreeContentProvider {
  
  private List<IMarker> markers;
  
  
  /**
   * @param viewer_p
   * @param helper_p
   * @param comparator_p
   */
  public CanonicalContentProvider(TreeViewer viewer_p, MarkerViewHelper helper_p, IViewerRefresh refresh_p) {
    super(viewer_p, helper_p, refresh_p);
    refillCache();
  }

 

  private void refillCache(){
    markers = new ArrayList<IMarker>();
    for (IMarker marker : findMarkers()){
      markerAddedIntern(marker);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element_p) {
    Object result = null;
    if (element_p != viewer.getInput()){
      result = viewer.getInput();
    }
    return result;
  }




  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement_p) {
    return markers.toArray();
  }

  /**
   * {@inheritDoc}
   */
  public Object[] getChildren(Object parentElement_p) {
    return noChildren;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean hasChildren(Object element_p) {
    return element_p == viewer.getInput();
  }

  private void markerAddedIntern(IMarker marker_p){
    markers.add(marker_p);
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
    markers.remove(marker_p);
    viewerRefresh.refresh();
  }
  
  

}
