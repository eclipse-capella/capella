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
 * The numeric input behavior.
 * @deprecated
 */
public class NumericInputBehavior extends RegExpInputBehavior {

  /**
   * Constructs the numeric input behavior.
   */
  public NumericInputBehavior() {
    super("([-]\\d+|\\d*)(([.]|[,]){0,1}\\d+)"); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.RegExpInputBehavior#getDefaultValues()
   */
  public String[] getDefaultValues() {
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#getValue(java.lang.String)
   */
  public Object getValue(String input) {
    return null;
  }
}
