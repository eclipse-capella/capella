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
package org.polarsys.capella.common.mdsofa.common.helper;

/**
 * Extra mathematical functions.
 * 
 */
public class MathHelper {
  /**
   * Multiply given integer and float value, and return result as an int.
   * @param sourceValue_p
   * @param multiplier_p
   * @return
   */
  public static int multiply(int sourceValue_p, float multiplier_p) {
    return (int) (sourceValue_p * multiplier_p);
  }
}
