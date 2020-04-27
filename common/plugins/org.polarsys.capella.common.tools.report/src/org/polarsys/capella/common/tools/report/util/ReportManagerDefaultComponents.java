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
package org.polarsys.capella.common.tools.report.util;

import org.apache.log4j.Logger;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

/**
 * Report Managers for the default components.
 * @version 1.0.1
 */
public class ReportManagerDefaultComponents {
  /**
   * Report Manager for Model.
   */
  private static Logger __reportManagerForModel = null;

  /**
   * Get the Report Manager used to log message concerning the model.
   * @return the Report Manager used to log message concerning the model
   */
  public static Logger getReportManagerForModel() {
    if (__reportManagerForModel == null) {
      __reportManagerForModel = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
    }
    return __reportManagerForModel;
  }
}
