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
package org.polarsys.capella.common.lib;

import java.util.Random;

/**
 * Generate pseudo-random values.
 * @version 0.4.0
 */
public class PseudoRandomGenerator {
  /**
   * Random number generator (based on the launch time).
   */
  private static Random __random = new Random();

  /**
   * Generate a pseudo-random long value depending on current time.
   * @return a pseudo-random long value depending on current time
   */
  public static long getPseudoRandomFromTime() {
    return Math.abs(System.currentTimeMillis());
  }

  /**
   * Generate a pseudo-random long value using a mathematical suite. The seed is based on the launch time.
   * @return a pseudo-random long value using a mathematical suite
   */
  public static long getPseudoRandom() {
    return Math.abs(__random.nextLong());
  }
}
