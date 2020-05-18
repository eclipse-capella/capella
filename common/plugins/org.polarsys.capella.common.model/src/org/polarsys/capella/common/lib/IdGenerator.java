/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.lib;

import java.util.UUID;

/**
 * Generate pseudo-unique IDs.
 * @version 0.8.0
 */
public class IdGenerator {
  /**
   * Unique number inside a Java session.
   */
  private static long __sessionUniqueNumber = 0;

  /**
   * Generate a pseudo-unique ID.
   * @return a pseudo-unique ID
   */
  public static String createId() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  /**
   * Generate an unique long value (unique during one Java session).
   * @return an unique long value (unique during one Java session)
   */
  public static long getSessionUniqueNumber() {
    return __sessionUniqueNumber++;
  }
}
