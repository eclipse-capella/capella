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
package org.polarsys.capella.common.ui.toolkit.services.validators;

/**
 * The abstract implementation of the IValidator interface.
 */
public abstract class AbstractValidator implements IValidator {
  /**
   * Returned formatted message when isValid method returned false.
   */
  private String _errorMessage;

  /**
   * Constructs the abstract implementation of the validator..
   * <p>
   * It is the responsibility of the caller to fully format the message.
   * </p>
   * @param errorMessage_p The message displayed when {@link #isValid(Object)} returned <code>false</code>.
   */
  protected AbstractValidator(String errorMessage_p) {
    _errorMessage = errorMessage_p;
  }

  /**
   * Returns the error message.
   * @return a not <code>null</code> string.
   */
  protected String getErrorMessage() {
    return _errorMessage;
  }

  /**
   * Set the error message to given one.
   * @param errorMessage_p the errorMessage to set
   */
  protected void setErrorMessage(String errorMessage_p) {
    _errorMessage = errorMessage_p;
  }
}
