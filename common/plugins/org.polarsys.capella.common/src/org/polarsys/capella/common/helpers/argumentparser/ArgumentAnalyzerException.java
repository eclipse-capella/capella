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
package org.polarsys.capella.common.helpers.argumentparser;

/**
 * Exception.
 */
public class ArgumentAnalyzerException extends Exception {

  /**
   * The serial version UID
   */
  private static final long serialVersionUID = 4605640153181626053L;

  /**
   * Constructor with message
   * @param messageString_p The message
   */
  public ArgumentAnalyzerException(String messageString_p) {
    super(messageString_p);
  }
  
  /**
   * Constructor with message and cause
   * @param messageString_p The message
   * @param causeThrowable_p The cause
   */
  public ArgumentAnalyzerException(String messageString_p, Throwable causeThrowable_p) {
    super(messageString_p, causeThrowable_p);
  }  
}
