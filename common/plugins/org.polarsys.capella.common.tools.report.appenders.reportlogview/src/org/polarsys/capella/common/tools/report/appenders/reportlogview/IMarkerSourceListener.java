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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;

/**
 * A listener that gets notified by a marker source if 
 * a marker is added or removed from the source.
 * 
 */
public interface IMarkerSourceListener {
  
  /**
   * A marker was added.
   * @param marker_p
   */
  public void markerAdded(IMarker marker_p);
  
  /**
   * A marker was deleted.
   * @param marker_p
   */
  public void markerDeleted(IMarker marker_p);
  
  /**
   * Currently unused.
   * @param marker_p
   */
  public void markerChanged(IMarkerDelta marker_p);
}
