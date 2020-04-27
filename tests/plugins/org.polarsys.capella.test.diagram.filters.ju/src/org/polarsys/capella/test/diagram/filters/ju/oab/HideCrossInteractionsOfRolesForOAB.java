/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.oab;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideCrossInteractionsOfRolesForOAB extends FiltersForOAB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_OAB_HIDE_CROSS_INTEREACTIONS_OF_ROLES_ID;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] {INTERACTION_6_ID});
  }

  @Override
  protected void setFilteredObjectMaps() {
    for (DDiagramElement elt : diagram.getDiagramElements()) {

      EObject target = elt.getTarget();
      if (target != null && target instanceof CapellaElement) {

        String targetId = ((CapellaElement) target).getId();
        String elementMappingName = elt.getMapping().getName();
        if (!toBeFiltered.values().contains(targetId) && filteredObjetIDs.contains(targetId) && filteredMappingNames.contains(elementMappingName)) {
          toBeFiltered.put(elt, targetId);
        } else {
          notToFilter.put(elt, targetId);
        }
      }
    }
  }

}
