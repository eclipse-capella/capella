/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.usage.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;

import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;

public class UsageFormatter {
  private static final String ENCODING = "utf8"; //$NON-NLS-1$
  private static final int DATA_COUNT = 7;
  private static final String DATA_SEPARATOR = ";"; //$NON-NLS-1$

  public UsageFormatter() {

  }

  /**
   * @param monitoring
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String format(final UsageMonitoring monitoring) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder(UsageFormatter.DATA_COUNT);
    sb.append(UsageFormatter.encode(monitoring.getApplicationName() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getApplicationVersion() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getEventName() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getEventContext() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getEventStatus() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getAddendum()));
    return sb.toString();
  }

  // private static String formatDate(final Date date) {
  // final DateFormat formatter = new SimpleDateFormat(UsageMonitoringFormatter.ISO_8601_DATE_FORMAT);
  // return formatter.format(date);
  // }

  public static UsageMonitoring parse(final String message) throws Exception {
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

  // private static Date parseDate(final String dateString) throws ParseException {
  // final DateFormat formatter = new SimpleDateFormat(UsageMonitoringFormatter.ISO_8601_DATE_FORMAT);
  // return formatter.parse(dateString);
  // }

  private static String encode(final String text) throws UnsupportedEncodingException {
    return URLEncoder.encode(text, UsageFormatter.ENCODING);
  }

  private static String decode(final String text) throws UnsupportedEncodingException {
    return URLDecoder.decode(text, UsageFormatter.ENCODING);
  }

}
