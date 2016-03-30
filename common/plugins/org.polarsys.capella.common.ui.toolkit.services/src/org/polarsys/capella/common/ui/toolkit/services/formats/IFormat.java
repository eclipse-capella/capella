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

package org.polarsys.capella.common.ui.toolkit.services.formats;

import java.util.regex.Pattern;

/**
 * The input format interface.
 */
public interface IFormat {
  /**
   * Formats the specified input value according to the specified pattern.
   * @param input The input value.
   * @param pattern The pattern.
   * @return The formatted value else the input value.
   */
  public String format(String input, Pattern pattern);
}
