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
package org.polarsys.capella.common.ui.toolkit.services.formats;

import java.util.regex.Pattern;

/**
 * The input format interface.
 */
public interface IFormat {
  /**
   * Formats the specified input value according to the specified pattern.
   * @param input_p The input value.
   * @param pattern_p The pattern.
   * @return The formatted value else the input value.
   */
  public String format(String input_p, Pattern pattern_p);
}
