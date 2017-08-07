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
package org.polarsys.capella.common.tools.report.appenders.file.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;

public class UsageFormatter {
  private static final String ENCODING_UTF8 = "utf8"; //$NON-NLS-1$
  private static final int MIN_DATA_COUNT = 8;
  private static final String DATA_SEPARATOR = ";"; //$NON-NLS-1$

  public UsageFormatter() {

  }

  /**
   * @param monitoring
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String format(final UsageMonitoring monitoring) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder(UsageFormatter.MIN_DATA_COUNT);
    sb.append(UsageFormatter.encode(monitoring.getToolName() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getToolVersion() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getEvent() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getContext() + UsageFormatter.DATA_SEPARATOR));
    sb.append(UsageFormatter.encode(monitoring.getStatus() + UsageFormatter.DATA_SEPARATOR));
    return sb.toString();
  }

  // private static String formatDate(final Date date) {
  // final DateFormat formatter = new SimpleDateFormat(UsageMonitoringFormatter.ISO_8601_DATE_FORMAT);
  // return formatter.format(date);
  // }

  public static UsageMonitoring parse(final String message) throws Exception {
    final String[] parts = message.split(UsageFormatter.DATA_SEPARATOR, -1);
    if (parts.length < UsageFormatter.MIN_DATA_COUNT) {
      throw new ParseException("The monitoring message must contains atleast " + //$NON-NLS-1$
          UsageFormatter.MIN_DATA_COUNT + " elements", 0); //$NON-NLS-1$
    }
    int i = 0;
    final String toolName = UsageFormatter.decode(parts[i]);
    final String toolVersion = UsageFormatter.decode(parts[i]);
    final String event = UsageFormatter.decode(parts[i]);
    final String context = UsageFormatter.decode(parts[i]);
    final String status = UsageFormatter.decode(parts[i]);

    return new UsageMonitoring(toolName, toolVersion, event, context, status);

  }

  // private static Date parseDate(final String dateString) throws ParseException {
  // final DateFormat formatter = new SimpleDateFormat(UsageMonitoringFormatter.ISO_8601_DATE_FORMAT);
  // return formatter.parse(dateString);
  // }

  private static String encode(final String text) throws UnsupportedEncodingException {
    return URLEncoder.encode(text, UsageFormatter.ENCODING_UTF8);
  }

  private static String decode(final String text) throws UnsupportedEncodingException {
    final String decodedData = URLDecoder.decode(text, UsageFormatter.ENCODING_UTF8);
    return decodedData;
  }

}
