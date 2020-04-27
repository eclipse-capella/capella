/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.helpers;

import java.util.Comparator;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 */
public class ExtensionPriorityComparator implements Comparator<IConfigurationElement> {
  /**
   * The name of the attribute (in the extension point schema) representing the priority value.
   */
  private static final String _EXTENSION_ATT_PRIORITY = "priority"; //$NON-NLS-1$

  /**
   * The priority keywords (any other value will be considered as 'low').
   */
  private static final String _EXTENSION_PRIORITY_LOW = "low"; //$NON-NLS-1$
  private static final String _EXTENSION_PRIORITY_NORMAL = "normal"; //$NON-NLS-1$
  private static final String _EXTENSION_PRIORITY_HIGH = "high"; //$NON-NLS-1$
  private static final String _EXTENSION_PRIORITY_RESERVED = "reserved"; //$NON-NLS-1$

  /**
   * This method returns an integer conversion of the given priority string.
   *
   * @param priority This keyword must be either 'low', or 'normal', or 'high', or 'reserved'.
   *                 Any other value will be interpreted as a 'low' value.
   * @return the converted keyword
   */
  private int _toInt(String priority) {
    if (priority.equalsIgnoreCase(_EXTENSION_PRIORITY_LOW)) return 0;
    else if (priority.equalsIgnoreCase(_EXTENSION_PRIORITY_NORMAL)) return 1;
    else if (priority.equalsIgnoreCase(_EXTENSION_PRIORITY_HIGH)) return 2;
    else if (priority.equalsIgnoreCase(_EXTENSION_PRIORITY_RESERVED)) return 3;

    return 0;
  }

  /**
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   *
   * @param cnfElt1 the first compared element
   * @param cnfElt2 the second compared element
   * @return the result of the comparison
   */
  public int compare(IConfigurationElement cnfElt1, IConfigurationElement cnfElt2) {
    int p1 = _toInt(cnfElt1.getAttribute(_EXTENSION_ATT_PRIORITY));
    int p2 = _toInt(cnfElt2.getAttribute(_EXTENSION_ATT_PRIORITY));
    if (p1 > p2) return -1;
    else if (p1 == p2) return 0;
    else return 1;
  }
}
