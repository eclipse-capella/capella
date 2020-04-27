/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.appenders;

/**
 *
 */
public interface IFlushableAppenders {
  
  public static final String ALL = "ALL";  //$NON-NLS-1$
  
  /**
   * flushes the appender's output content concerning a given logging source (data element where the logging command has been launched) for a given logging business component
   * 
   * @param componentName logging business component
   * @param loggingSourceElement logging source
   */
  public void flush(String componentName, Object loggingSourceElement);
}
