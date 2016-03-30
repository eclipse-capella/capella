/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
