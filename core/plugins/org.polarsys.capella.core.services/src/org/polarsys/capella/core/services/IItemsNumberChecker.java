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
package org.polarsys.capella.core.services;

/**
 * This interface should be implemented by any class which purpose is to count some items and check whether the number of those items has reached a given value
 * or not.
 * 
 * 
 */
public interface IItemsNumberChecker {
  /**
   * Iterates the counted items number
   */
  public void iterateItemsNumber();

  /**
   * Waits for the expected count to be reached.
   */
  public void waitForExpectedCountToBeReached();

  /**
   * Updates the expected count to a new value
   * @param newCount_p
   */
  public void updateCountToReach(int newCount_p);

  /**
   * Gets the expected count value
   * @return the expected count
   */
  public int getCountToReach();
}
