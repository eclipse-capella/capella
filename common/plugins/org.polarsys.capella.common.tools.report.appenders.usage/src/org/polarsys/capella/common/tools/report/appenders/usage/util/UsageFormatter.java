/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.usage.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;

public class UsageFormatter {
  private static final String ENCODING = StandardCharsets.UTF_8.toString(); //$NON-NLS-1$
  private static final int DATA_COUNT = 7;
  private static final String DATA_SEPARATOR = ";"; //$NON-NLS-1$

  public static String format(UsageMonitoring monitoring) {
    StringBuilder sb = new StringBuilder(UsageFormatter.DATA_COUNT);

    sb.append(encode(monitoring.getApplicationName()) + UsageFormatter.DATA_SEPARATOR);
    sb.append(encode(monitoring.getApplicationVersion()) + UsageFormatter.DATA_SEPARATOR);
    sb.append(encode(monitoring.getEventName()) + UsageFormatter.DATA_SEPARATOR);
    sb.append(encode(monitoring.getEventContext()) + UsageFormatter.DATA_SEPARATOR);
    sb.append(encode(monitoring.getEventStatus().toString()) + UsageFormatter.DATA_SEPARATOR);
    sb.append(encode(monitoring.getAddendum()));

    return sb.toString();
  }

  public static UsageMonitoring parse(String message) throws Exception {
    final String[] parts = message.split(UsageFormatter.DATA_SEPARATOR, -1);
    if (parts.length != UsageFormatter.DATA_COUNT) {
      throw new ParseException("The monitoring message must contains " + //$NON-NLS-1$
          UsageFormatter.DATA_COUNT + " elements", 0); //$NON-NLS-1$
    }
    final String applicationName = UsageFormatter.decode(parts[1]);
    final String applicationVersion = UsageFormatter.decode(parts[2]);
    final String eventName = UsageFormatter.decode(parts[3]);
    final String eventContext = UsageFormatter.decode(parts[4]);
    final String eventStatusValue = UsageFormatter.decode(parts[5]);
    final String addendum = UsageFormatter.decode(parts[6]);
    
    EventStatus eventStatus = null;
    for(EventStatus constant : EventStatus.values()) {
      if (eventStatusValue == constant.toString()) {
        eventStatus = constant;
        break;
      }
    }
    
    return new UsageMonitoring(applicationName, applicationVersion, eventName, eventContext, eventStatus, addendum);
  }

  private static String encode(String text) {
    try {
    return URLEncoder.encode(text, UsageFormatter.ENCODING);
    } catch (Exception e) {
      return ICommonConstants.EMPTY_STRING;
  }
  }

  private static String decode(String text) {
    try {
    return URLDecoder.decode(text, UsageFormatter.ENCODING);
    } catch (Exception e) {
      return ICommonConstants.EMPTY_STRING;
  }
  }

}
