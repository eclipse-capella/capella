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

package org.polarsys.capella.common.tools.report.config.persistence;

public class ObjectFactory {

  public ObjectFactory() {
    //
  }

  /**
   * Create an instance of {@link ConfigurationInstance }
   * 
   */
  public ConfigurationInstance createConfigurationInstance() {
    return new ConfigurationInstance();
  }

  /**
   * Create an instance of {@link LogLevel }
   * 
   */
  public LogLevel createLogLevel() {
    return new LogLevel();
  }

  /**
   * Create an instance of {@link OutputConfiguration }
   * 
   */
  public OutputConfiguration createOutputConfiguration() {
    return new OutputConfiguration();
  }

  /**
   * Create an instance of {@link ReportConfigurationFile }
   * 
   */
  public ReportConfigurationFile createReportConfigurationFile() {
    return new ReportConfigurationFile();
  }

}
