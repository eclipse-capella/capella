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

import org.apache.log4j.Logger;

public class UsageLogger {
	private static final String USAGE_LOGGER = "Usage";
	private final String productName;
	private final String productVersion;
	private final Logger logger = Logger.getLogger(UsageLogger.USAGE_LOGGER);

	public final static String START = "START";
	public final static String STOP = "STOP";
	public final static String OK = "OK";
	public final static String ERROR = "ERROR";
	public final static String WARNING = "WARNING";

	private final static String NONE = "";

	public UsageLogger(final String toolName, final String toolVersion) {
		this.productName = toolName;
		this.productVersion = toolVersion;
	}

	public void log(final String event, final String context, final String status) {
		try {
			final String formattedUsageMonitoring = UsageFormatter
					.format(new UsageMonitoring(productName, productVersion, event, context, status)); // $NON-NLS-1$
			logger.info(formattedUsageMonitoring);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void log(final String event, final String context) {
		try {
			final String formattedUsageMonitoring = UsageFormatter
					.format(new UsageMonitoring(productName, productVersion, event, context, UsageLogger.NONE)); // $NON-NLS-1$
			logger.info(formattedUsageMonitoring);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}