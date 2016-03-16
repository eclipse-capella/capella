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

package org.polarsys.capella.common.command.recorder.core.exception;

/**
 * Exception raised .
 */
public class RecorderException extends Exception {

  /**
   * Default constructor
   * @param messageString
   */
  public RecorderException(String messageString) {
    super(messageString);
  }

  /**
   * The serial version UID
   */
  private static final long serialVersionUID = 4605640123181622105L;
  
}
