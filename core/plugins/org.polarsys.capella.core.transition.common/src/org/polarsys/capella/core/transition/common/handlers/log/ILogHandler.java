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
package org.polarsys.capella.core.transition.common.handlers.log;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;

/**
 * An interface for log
 */
public interface ILogHandler extends IHandler {

  public void setLevel(Level level_p);

  public String getText(Object object);

  public boolean hasDebug();

  public boolean hasInfo();

  public boolean hasWarn();

  public boolean hasError();

  public boolean hasFatal();

  public void log(String message, IStatus status, String source_p);

  public void log(String message, IStatus status, Object relatedObjects, String source_p);

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

  public void flush();

  public String getIdentifier(EObject me_p);

}
