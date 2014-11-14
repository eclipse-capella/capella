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
 * An interface for validating any UI data input.
 * <p>
 * This interface should be implemented by classes that wish to act as UI data input validators.
 * </p>
 */
public interface IValidator {
  /**
   * Returns a string indicating whether the given value is valid; <code>null</code> means valid, and non-<code>null</code> means invalid, with the result
   * being the error message to display to the end-user.
   * <p>
   * It is the responsibility of the implementor to fully format the message before returning it.
   * </p>
   * @param value the value to be validated
   * @return the error message, or <code>null</code> indicating that the value is valid.
   */
  public String isValid(Object value);
}
