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
	private final String applicationName;
	private final String applicationVersion;
	private final Logger logger = Logger.getLogger(UsageLogger.USAGE_LOGGER);

	public final static String NONE = "";
	public final static String OK = "OK";
	public final static String ERROR = "ERROR";

	public UsageLogger(final String applicationName, final String applicationVersion) {
		this.applicationName = applicationName;
		this.applicationVersion = applicationVersion;
	}

	public void log(final String eventName, final String eventStatus) {
		log(eventName, NONE, eventStatus, NONE);
	}

	public void log(final String eventName, final String eventContext, final String eventStatus) {
		log(eventName, eventContext, eventStatus, NONE);
	}
	
	public void log(final String eventName, final String eventContext, final String eventStatus, final String addendum) {
		try {
			final String formattedUsageMonitoring = UsageFormatter
					.format(new UsageMonitoring(applicationName, applicationVersion, eventName, eventContext, eventStatus, addendum)); // $NON-NLS-1$
			logger.info(formattedUsageMonitoring);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}