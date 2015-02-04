/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
