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

package org.polarsys.capella.core.data.helpers.cs.services;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;

/**
 */
public class CompositeStructureNamingHelper {
  /**
   * @param element element whose value is requested
   */
  public static String getValue(ExchangeItemAllocation element) {
    if (element != null) {
      return element.getAllocatedItem().getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
