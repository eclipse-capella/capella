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

package org.polarsys.capella.common.model.helpers;

/**
 * Thrown when an Helper implementation is not found on the platform.
 * @see IHelper
 */
public class HelperNotFoundException extends RuntimeException {
  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1020532179563924386L;

  /**
   * Constructor.
   */
  public HelperNotFoundException() {
    super();
  }

  /**
   * Constructor.
   * @param message
   * @param cause
   */
  public HelperNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor.
   * @param message
   */
  public HelperNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructor.
   * @param cause
   */
  public HelperNotFoundException(Throwable cause) {
    super(cause);
  }
}
