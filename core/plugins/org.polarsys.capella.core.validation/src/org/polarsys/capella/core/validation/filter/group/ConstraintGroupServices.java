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
package org.polarsys.capella.core.validation.filter.group;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.service.IConstraintDescriptor;

import org.polarsys.capella.core.validation.utils.ValidationHelper;

/**
 * Utility Class for constraint groups.
 *
 */
public class ConstraintGroupServices {

  /**
   * Return all the matching constraint for a given {@link ConstraintGroupEnum}.
   * @param group_p the target {@link ConstraintGroupEnum}
   * @return an empty list whether no constraints are matching.
   * @see ConstraintGroupEnum
   */
  public static List<IConstraintDescriptor> getAllMatchingConstraints(ConstraintGroupEnum group_p) {
    
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();
    
    for (IConstraintDescriptor icd: ValidationHelper.getAllConstraintDescriptors()) {
      if (group_p.isConstraintContainedInto(icd)) {
        result.add(icd);
      }
    }
    return result;
  }
  
}
