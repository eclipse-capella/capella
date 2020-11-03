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

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * The abstract base class for all content providers for the MarkerView in this package.
 * 
 * 
 */
abstract class AbstractMarkerViewContentProvider implements ITreeContentProvider, IMarkerSourceListener {

  protected final MarkerViewHelper helper;
  protected IViewerRefresh viewerRefresh;  
  protected TreeViewer viewer;

  
  protected Object input;
  protected final Object[] noChildren = {};
  
  public AbstractMarkerViewContentProvider(TreeViewer viewer, MarkerViewHelper helper, IViewerRefresh viewerRefresh){
    this.helper = helper;
    this.viewer = viewer;
    setViewerRefresh(viewerRefresh);
  }
  
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    input = newInput;
  }
  
  @SuppressWarnings("synthetic-access")
  public void setViewerRefresh(IViewerRefresh refresh){
    if (refresh != null){
      viewerRefresh = refresh;
    } else {
      viewerRefresh = new NullViewerRefresh();
    }
  }
  
  public IViewerRefresh getViewerRefresh(){
    if (viewerRefresh instanceof NullViewerRefresh){
      return null;
    }
    return viewerRefresh;
  }
  
  
  /**
   * Deletes all markers that this content provider knows about.
   */
  public void clear(){
    for (IMarker marker : helper.findMarkers((IResource) viewer.getInput())) {
      try {
        marker.delete();
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
      }
    }
    viewerRefresh.refresh();
  }

  
  protected Collection<IMarker> findMarkers(){
    IResource markerResource = null;
    if (input instanceof IResource){
      markerResource = (IResource) viewer.getInput();
    }
    return helper.findMarkers(markerResource, null);
  }

  /**
   * {@inheritDoc}
   */
  public void markerChanged(IMarkerDelta marker) {
    /* currently unused */
  }
  
  public void dispose(){
    /* do nothing */
  }
  
  /* 
   * a viewer refresh that does not refresh the viewer :)
   * this is useful if clients execute bulk operations
   * and want to refresh the viewer manually
   */
  private class NullViewerRefresh implements IViewerRefresh {
    public void refresh() { /* do nothing */ }
  }
  
}
