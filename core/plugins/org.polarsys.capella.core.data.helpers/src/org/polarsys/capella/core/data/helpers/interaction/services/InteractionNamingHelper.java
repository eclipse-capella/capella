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

package org.polarsys.capella.core.data.helpers.interaction.services;

import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 */
public class InteractionNamingHelper {
  /**
   * @param element element whose value is requested
   */
  public static String getValue(SequenceMessage element) {
    if (element != null) {
      return element.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
