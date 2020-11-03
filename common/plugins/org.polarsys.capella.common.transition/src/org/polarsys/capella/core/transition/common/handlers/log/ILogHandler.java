/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.log;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;

/**
 * An interface for log
 */
public interface ILogHandler extends IHandler {

  void setLevel(Level level);

  String getText(Object object);

  boolean hasDebug();

  boolean hasInfo();

  boolean hasWarn();

  boolean hasError();

  boolean hasFatal();

  void log(String message, IStatus status, String source);

  void log(String message, IStatus status, Object relatedObjects, String source);

  void debug(String message, Object relatedObjects, String source);

  void debug(String message, String source);

  void info(String message, Object relatedObjects, String source);

  void info(String message, String source);

  void warn(String message, Object relatedObjects, String source);

  void warn(String message, String source);

  void error(String message, Object relatedObjects, String source);

  void error(String message, String source);

  void fatal(String message, Object relatedObjects, String source);

  void fatal(String message, String source);

  void flush();

  String getIdentifier(EObject me);

}
