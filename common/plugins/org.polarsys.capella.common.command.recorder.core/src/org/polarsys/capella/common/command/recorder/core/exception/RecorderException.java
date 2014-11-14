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
package org.polarsys.capella.common.command.recorder.core.exception;

/**
 * Exception raised .
 */
public class RecorderException extends Exception {

  /**
   * Default constructor
   * @param messageString_p
   */
  public RecorderException(String messageString_p) {
    super(messageString_p);
  }

  /**
   * The serial version UID
   */
  private static final long serialVersionUID = 4605640123181622105L;
  
}
