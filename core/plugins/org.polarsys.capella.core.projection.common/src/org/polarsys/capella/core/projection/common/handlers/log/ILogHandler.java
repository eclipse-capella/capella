/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.handlers.log;

import org.polarsys.capella.core.projection.common.handlers.IHandler;

/**
 * An interface for log
 */
public interface ILogHandler extends IHandler {

  public String getText(Object object);

  public void log(String message, String priority, Object relatedObjects, String source_p);

  public void log(String message, String priority, String source_p);

  public void debug(String message, Object relatedObjects, String source_p);

  public void debug(String message, String source_p);

  public void info(String message, Object relatedObjects, String source_p);

  public void info(String message, String source_p);

  public void warn(String message, Object relatedObjects, String source_p);

  public void warn(String message, String source_p);

  public void error(String message, Object relatedObjects, String source_p);

  public void error(String message, String source_p);

  public void fatal(String message, Object relatedObjects, String source_p);

  public void fatal(String message, String source_p);

}
