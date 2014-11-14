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
