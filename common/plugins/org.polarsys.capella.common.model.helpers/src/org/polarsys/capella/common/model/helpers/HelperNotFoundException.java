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
