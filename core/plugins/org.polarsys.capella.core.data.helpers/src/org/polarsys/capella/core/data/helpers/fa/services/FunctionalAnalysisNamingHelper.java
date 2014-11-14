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
package org.polarsys.capella.core.data.helpers.fa.services;

import org.polarsys.capella.core.data.fa.FunctionalExchange;

/**
 */
public class FunctionalAnalysisNamingHelper {
  /**
   * @param element_p element whose value is requested
   */
  public static String getValue(FunctionalExchange element_p) {
    if (element_p != null) {
      return element_p.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
