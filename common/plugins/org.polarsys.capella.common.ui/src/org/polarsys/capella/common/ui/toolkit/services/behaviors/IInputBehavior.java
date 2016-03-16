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

package org.polarsys.capella.common.ui.toolkit.services.behaviors;

/**
 * The input behavior interface.
 */
public interface IInputBehavior {
  /**
   * Checks if the specified input value is valid.
   * @param value The input value.
   * @return <code>True</code> if it's a valid input value else <code>false</code>.
   */
  public boolean isValid(String value);

  /**
   * Formats the input value.
   * @param input The input value.
   * @return The formatted value.
   */
  public String format(String input);

  /**
   * Returns the default values list.
   * @return The default values array.
   */
  public String[] getDefaultValues();
  
  /**
   * Gets the value from the specified input.
   * @param input The input value.
   * @return The field value.
   */
  public Object getValue(String input);
}
