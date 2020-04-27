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
package org.polarsys.capella.common.lib;

import org.apache.log4j.Logger;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * A library about memory.
 * @version 0.4.0
 */
public class Memory {
  /**
   * Log the used memory with Report Manager in the debug mode.
   * @param componentName the component wanting to log
   */
  @SuppressWarnings("nls")
  public static void logMemory(String componentName) {
    long usedMemory = getUsedMemory();
    Logger reportManager = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
    if (reportManager.isDebugEnabled()) {
      StringBuilder loggerMessage = new StringBuilder();
      loggerMessage.append(usedMemory);
      loggerMessage.append(" bytes used.");
      reportManager.debug(new EmbeddedMessage(loggerMessage.toString(), (componentName == null ? ICommonComponent.COMPONENT_NAME : componentName)));
    }
  }

  /**
   * Get the used memory.
   * @return the used memory
   */
  public static long getUsedMemory() {
    Runtime.getRuntime().gc(); // To try to have a more precise measure of memory
    long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    return usedMemory;
  }
}
