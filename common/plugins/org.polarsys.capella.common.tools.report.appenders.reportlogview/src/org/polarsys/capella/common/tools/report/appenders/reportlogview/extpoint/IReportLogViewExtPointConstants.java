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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.extpoint;

/**
 * Useful constants for the filteronreportview extension point.
 *
 */
public interface IReportLogViewExtPointConstants {

  /** the marker filter extension point ID */
  public final static String EXT_POINT_ID = "filterOnReportView"; //$NON-NLS-1$
  
  /** the report view node */
  public final static String REPORT_VIEW_NODE = "reportView"; //$NON-NLS-1$
  
  /** the view ID attribute */
  public final static String VIEW_ID_ATT = "viewID"; //$NON-NLS-1$
  
  /** the rule filter provider ID attribute */
  public final static String FILTER_PROVIDER_ATT = "markerIDsProvider"; //$NON-NLS-1$
  
}
