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
   * @param marker
   */
  public void markerAdded(IMarker marker);
  
  /**
   * A marker was deleted.
   * @param marker
   */
  public void markerDeleted(IMarker marker);
  
  /**
   * Currently unused.
   * @param marker
   */
  public void markerChanged(IMarkerDelta marker);
}
