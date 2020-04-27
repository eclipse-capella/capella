/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.capellacore.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class CapellaElementExt {

  /**
   * Apply a subtraction
   * 
   * 
   * @param dataDefListExisting2
   * @param dataDefListExisting
   * @return
   */
  public static List<CapellaElement> setSubtraction(List<? extends CapellaElement> dataDefListExisting2, List<? extends CapellaElement> dataDefListExisting) {
    List<CapellaElement> listResult = new ArrayList<>();
    for (CapellaElement eltA : dataDefListExisting2) {
      if (!(dataDefListExisting.contains(eltA))) {
        listResult.add(eltA);
      }
    }
    return listResult;
  }
}
