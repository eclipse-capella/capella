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
package org.polarsys.capella.common.tools.report.appenders.usage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.appenders.usage.preferences.IUsagePreferences;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;

public class UsageMonitoringLogger {

  private static UsageMonitoringLogger instance;
  private final UsageLogger logger;
  public static String USAGE_PATH = "UsagePath";

  private final String pathRegex = ".*\\$\\{[^\\}]+\\}.*";
  private final String varRegex = "(\\$\\{[^\\}]+\\})";

  /**
   * @return a MonitoringLogger instance
   */
  public static UsageMonitoringLogger getInstance() {
    if (UsageMonitoringLogger.instance == null) {
      UsageMonitoringLogger.instance = new UsageMonitoringLogger();
    }
    return UsageMonitoringLogger.instance;
  }

  /**
   * Set by default the workspace as usage path if vmarg -DUsagePath is NOT provided.
   * Resolve environment variables and system properties in the provided path if needed.
   */
  private void setUsagePath() {
    String usagePathProperty = System.getProperty(UsageMonitoringLogger.USAGE_PATH);
    if (null == usagePathProperty || "".equals(usagePathProperty)) {
      System.setProperty(UsageMonitoringLogger.USAGE_PATH,
          ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
    } else if (usagePathProperty.matches(pathRegex)) {
      boolean unresolvableVar = false;
      final Matcher matcher = Pattern.compile(varRegex).matcher(usagePathProperty);
      while (matcher.find()) {
        String variableName = getVariableName(matcher.group());
        String varValue = getVariableValue(variableName);
        if (varValue == null) {
          // Log warning about undefined environment variable or system property
          UsageAppenderPlugin.getDefault().getLog()
              .log(new Status(IStatus.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), "Undefined environment variable: "
                  + variableName + " found in -DUsagePath configuration. The log file will be put in the workspace."));
          unresolvableVar = true;
          break;
        }
        usagePathProperty = usagePathProperty.replaceFirst(varRegex, Matcher.quoteReplacement(varValue));
      }
      // If there is an unresolvable variable, log in the workspace
      if (!unresolvableVar)
        System.setProperty(UsageMonitoringLogger.USAGE_PATH, usagePathProperty);
      else
        System.setProperty(UsageMonitoringLogger.USAGE_PATH,
            ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
    }
  }

  private String getVariableName(String match) {
    return match.substring(2, match.lastIndexOf('}'));
  }

  private String getVariableValue(String varName) {
    String value = System.getenv(varName);
    return value != null ? value : System.getProperty(varName);
  }

  private UsageMonitoringLogger() {
    setUsagePath();

    // Configure the logger
    if (Platform.getProduct() != null) {
      final String productName = Platform.getProduct().getName().toString();
      final String productVersion = Platform.getProduct().getDefiningBundle().getVersion().toString();
      logger = new UsageLogger(productName, productVersion);
    } else {
      logger = new UsageLogger(ICommonConstants.EMPTY_STRING, ICommonConstants.EMPTY_STRING);
    }
  }

  public void log(final String eventName, final EventStatus eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventStatus);
    }
  }

  public void log(final String eventName, final String eventContext, final EventStatus eventStatus) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus);
    }
  }

  public void log(final String eventName, final String eventContext, final EventStatus eventStatus,
      final String addendum) {
    if (isUsageMonitoringActivated()) {
      logger.log(eventName, eventContext, eventStatus, addendum);
    }
  }

  public boolean isUsageMonitoringActivated() {
    return AbstractPreferencesInitializer.getBoolean(IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING,
        IUsagePreferences.PREFERENCES_ACTIVATE_USAGE_MONITORING_DEFAULT.booleanValue());
  }
}