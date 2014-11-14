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
package org.polarsys.capella.common.tig.model;

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
   * @param message_p
   * @param cause_p
   */
  public HelperNotFoundException(String message_p, Throwable cause_p) {
    super(message_p, cause_p);
  }

  /**
   * Constructor.
   * @param message_p
   */
  public HelperNotFoundException(String message_p) {
    super(message_p);
  }

  /**
   * Constructor.
   * @param cause_p
   */
  public HelperNotFoundException(Throwable cause_p) {
    super(cause_p);
  }
}
