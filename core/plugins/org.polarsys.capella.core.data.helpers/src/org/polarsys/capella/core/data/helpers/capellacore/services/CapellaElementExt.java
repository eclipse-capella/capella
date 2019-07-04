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
