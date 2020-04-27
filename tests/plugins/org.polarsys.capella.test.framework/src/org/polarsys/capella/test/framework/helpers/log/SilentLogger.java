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

/**
 * @author Erwan Brottier
 */
public class SilentLogger extends FormatedLogger {
    
  public SilentLogger() {}
    
  @Override
  public void printException(String message, Exception exception) {}

  @Override
  public void printException(Exception exception) {}

  @Override
  public void addTextLn(Object text) {}

  @Override
  public void addText(Object text) {}

  @Override
  public void carriageReturn() {}

  @Override
  public void incIndent() {}

  @Override
  public void incIndentLn() {}

  @Override
  public void decIndent() {}

  @Override
  public void decIndentLn() {}

  @Override
  protected void basicPrint(Object object) {}
}
