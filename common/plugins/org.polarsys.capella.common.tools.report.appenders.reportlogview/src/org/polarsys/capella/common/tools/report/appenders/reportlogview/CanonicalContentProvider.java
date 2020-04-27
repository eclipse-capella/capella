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
   * @param viewer
   * @param helper
   * @param refresh
   */
  public CanonicalContentProvider(TreeViewer viewer, MarkerViewHelper helper, IViewerRefresh refresh) {
    super(viewer, helper, refresh);
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
  public Object getParent(Object element) {
    Object result = null;
    if (element != viewer.getInput()){
      result = viewer.getInput();
    }
    return result;
  }




  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement) {
    return markers.toArray();
  }

  /**
   * {@inheritDoc}
   */
  public Object[] getChildren(Object parentElement) {
    return noChildren;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean hasChildren(Object element) {
    return element == viewer.getInput();
  }

  private void markerAddedIntern(IMarker marker){
    markers.add(marker);
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
    markers.remove(marker);
    viewerRefresh.refresh();
  }
  
  

}
