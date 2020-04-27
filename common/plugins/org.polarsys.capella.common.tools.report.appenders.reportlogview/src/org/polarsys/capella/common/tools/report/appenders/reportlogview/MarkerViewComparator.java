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

import java.util.Comparator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * A ViewerComparator that delegates comparison of IMarkers 
 * to a java.util.Comparator.
 * 
 */
class MarkerViewComparator extends ViewerComparator {

  /* the comparator we use to compare IMarkers */
  private Comparator<IMarker> comparator;
  
  /**
   * Set the comparator for IMarkers
   * @param comparator
   */
  public void setMarkerComparator(Comparator<IMarker> comparator){
    this.comparator = comparator;
  }
  
  @Override
  public int compare(Viewer v, Object e1, Object e2){
    if (e1 instanceof IMarker && e2 instanceof IMarker){
      if (comparator != null){
        return comparator.compare((IMarker) e1, (IMarker) e2);
      }
    } 
    return super.compare(v, e1, e2);
  }
  
  /**
   * @return the Comparator we use to compare IMarkers
   */
  public Comparator<IMarker> getMarkerComparator(){
    return comparator;
  }

}
