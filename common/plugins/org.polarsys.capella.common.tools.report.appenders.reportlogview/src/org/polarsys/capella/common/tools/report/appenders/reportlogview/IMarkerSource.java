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
