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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.cs.services.Messages;

public class PartNamingHelper {
  /**
   * @param element
   *          element whose value is requested
   */
  public static String getValue(Part element) {
    if (element != null) {
      if (element.getName() != null && element.getName() != "") {
        return element.getName() + " : " + element.getAbstractType().getName();
      }
      return Messages.getString("Part") + element.getAbstractType().getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
