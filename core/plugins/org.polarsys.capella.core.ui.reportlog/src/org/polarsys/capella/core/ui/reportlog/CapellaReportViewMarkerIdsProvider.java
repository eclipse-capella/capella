/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.reportlog;

import org.eclipse.emf.ecore.EValidator;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.IReportLogViewMarkerIdsProvider;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants;

/**
 *
 */
public class CapellaReportViewMarkerIdsProvider implements IReportLogViewMarkerIdsProvider {
  /**
   * {@inheritDoc}
   */
  public String[] getMarkerIds() {
    return new String[] { MarkerView.MARKER_ID, EValidator.MARKER, "org.eclipse.sirius.diagram.ui.diagnostic", //$NON-NLS-1$
        ICapellaValidationConstants.CAPELLA_MARKER_ID };
  }

}
