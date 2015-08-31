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
package org.polarsys.capella.core.refinement.merge.exception;

/**
 * Exception raised during the merge process.
 * This exception can occur when an error is detected by the algorithm itself.
 */
public class MergeException extends Exception {

  /**
   * The serial version UID
   */
  private static final long serialVersionUID = 4605640153181626053L;

  /**
   * Constructor with message
   * @param messageString The message
   */
  public MergeException(String messageString) {
    super(messageString);
  }
  
  /**
   * Constructor with message and cause
   * @param messageString The message
   * @param causeThrowable The cause
   */
  public MergeException(String messageString, Throwable causeThrowable) {
    super(messageString, causeThrowable);
  }  
}
