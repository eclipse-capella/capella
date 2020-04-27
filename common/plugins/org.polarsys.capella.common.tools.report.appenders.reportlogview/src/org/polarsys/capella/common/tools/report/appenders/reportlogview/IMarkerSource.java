/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

/**
 * Something that produces, consumes, and stores markers.
 */
public interface IMarkerSource {
  public Collection<IMarker> getMarkers();
  public void addListener(IMarkerSourceListener listener);
  public void removeListener(IMarkerSourceListener listener);
}
