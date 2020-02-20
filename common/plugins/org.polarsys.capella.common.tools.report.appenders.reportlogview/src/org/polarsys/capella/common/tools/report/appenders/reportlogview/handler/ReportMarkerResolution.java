/*******************************************************************************
 * Copyright (c) 2006, 2015, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;

/**
 * A resolution that allows to add precondition on markers
 */
public abstract class ReportMarkerResolution extends WorkbenchMarkerResolution {

  /**
   * 
   * @param markers
   * @return true if the resolution is compatible with markers
   */
  public boolean enabled(Collection<IMarker> markers) {
    return true;
  }

  /**
   * 
   * @param markers
   * @return true if the resolution should appear in Quickfix All Similars
   */
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return true;
  }
}
