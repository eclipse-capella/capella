/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwan Brottier
 */
public class LogBroadcaster extends FormatedLogger {

  protected List<FormatedLogger> loggers = new ArrayList<FormatedLogger>();
    
  public LogBroadcaster() {
  }
  
  public LogBroadcaster(List<FormatedLogger> loggers_) {
    loggers.addAll(loggers_);
  }

  public void addLogger(FormatedLogger logger) {
  	loggers.add(logger);
  }

  public void removeLogger(FormatedLogger logger) {
  	loggers.remove(logger);
  }
  
  @Override
  public void printException(String message, Exception exception) {
    for (IFormatedLogger logger : loggers) {
      logger.printException(message, exception);
    }
  }

  @Override
  public void printException(Exception exception) {
    for (IFormatedLogger logger : loggers) {
      logger.printException(exception);
    }
    print(exception);
  }

  @Override
  public void addTextLn(Object text) {
    for (IFormatedLogger logger : loggers) {
      logger.addTextLn(text);
    }
  }

  @Override
  public void addText(Object text) {
    for (IFormatedLogger logger : loggers) {
      logger.addText(text);
    }
  }

  @Override
  public void carriageReturn() {
    for (IFormatedLogger logger : loggers) {
      logger.carriageReturn();
    }
  }

  @Override
  public void incIndent() {
    for (IFormatedLogger logger : loggers) {
      logger.incIndent();
    }
  }

  @Override
  public void incIndentLn() {
    for (IFormatedLogger logger : loggers) {
      logger.incIndentLn();
    }
  }

  @Override
  public void decIndent() {
    for (IFormatedLogger logger : loggers) {
      logger.decIndent();
    }
  }

  @Override
  public void decIndentLn() {
    for (IFormatedLogger logger : loggers) {
      logger.decIndentLn();
    }
  }

  @Override
  protected void basicPrint(Object object) {
    for (FormatedLogger logger : loggers) {
      logger.basicPrint(object);
    }
  }
}
