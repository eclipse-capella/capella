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
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

/**
 * A ResourceChangeListener that bridges between Resource notifications
 * and IMarkerSource notifications.
 * 
 * Note that the classic marker system was replaced with a more
 * lightweight mechanism in Capella to overcome performance
 * issues in combination with EMF CDO. You should therefore always use
 * 
 * LightMarkerRegistry.createMarker instead of the classic IResource.createMarker
 * 
 * 
 */
class ResourceMarkers implements IResourceChangeListener, IMarkerSource {
  IResource res;
 
  final List<IMarkerSourceListener> listeners = new ArrayList<IMarkerSourceListener>();
  private final String[] markerTypes;
  final IResource resource;
  
  
  public ResourceMarkers(IResource resource_p, String[] markerTypes_p){
    resource = resource_p;
    markerTypes = markerTypes_p;
  }
  
  /**
   * Transform events from the Resources API to IMarkerSource notifications
   */
  public void resourceChanged(IResourceChangeEvent event) {

      // Get filtered list of marker deltas.
      Collection<IMarkerDelta> deltas = retainMarkerDeltas(event);
      // If there are any markers of interest in the delta we refresh the viewer
      if (deltas.size() != 0) {
        for (IMarkerDelta markerDelta : deltas) {
          final IMarker marker = markerDelta.getMarker();
          switch (markerDelta.getKind()) {
            case IResourceDelta.ADDED:
              for (IMarkerSourceListener l : listeners){
                l.markerAdded(marker);
              }
              break;
            case IResourceDelta.REMOVED:
              for (IMarkerSourceListener l : listeners){
                l.markerDeleted(marker);
              }
              break;
            default:
            break;
          }
        }
      }
    }
  
  /**
   * Get marker deltas that are of interest for the problem view.
   * @param event_p
   * @return
   */
  protected Collection<IMarkerDelta> retainMarkerDeltas(IResourceChangeEvent event_p) {
    Collection<IMarkerDelta> deltas = new ArrayList<IMarkerDelta>(0);
    for (String type : markerTypes) {
      retainMarkerDeltasFor(type, event_p, deltas);
    }
    return deltas;
  }

  /**
   * Retain specified event marker deltas that match specified marker type.
   * @param markerType_p
   * @param event_p
   * @param resultingCollection_p A not <code>null</code> collection that should host retained marker deltas.
   */
  protected void retainMarkerDeltasFor(String markerType_p, IResourceChangeEvent event_p, Collection<IMarkerDelta> resultingCollection_p) {
    IMarkerDelta[] markerDeltas = event_p.findMarkerDeltas(markerType_p, false);
    if (null == markerDeltas) {
      return;
    }
    for (IMarkerDelta markerDelta : markerDeltas) {
      resultingCollection_p.add(markerDelta);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IMarker> getMarkers() {
    List<IMarker> result = new ArrayList<IMarker>();
    for (String t : markerTypes){
      try {
        for (IMarker m : resource.findMarkers(t, false, IResource.DEPTH_INFINITE)){
          result.add(m);
        }
      } catch (CoreException exception_p) {
          exception_p.printStackTrace();
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public void addListener(IMarkerSourceListener listener_p) {
    listeners.add(listener_p);
  }


  /**
   * {@inheritDoc}
   */
  public void removeListener(IMarkerSourceListener listener_p) {
    listeners.remove(listener_p);
  }
  
}
