/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Date;

import org.apache.log4j.helpers.AbsoluteTimeDateFormat;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * 
 *
 */
public class ImpactDateFormater {
  private static AbsoluteTimeDateFormat _sdt = new AbsoluteTimeDateFormat();

  /**
   * Format the specified marker.
   * @param marker The marker.
   * @return the formatted string.
   */
  public static String format(IMarker marker) {
    try {
      long time = marker.getCreationTime();
      Date d = new Date(time);
      return _sdt.format(d);
    } catch (CoreException ce) {
      return ICommonConstants.EMPTY_STRING;
    }
  }
}
