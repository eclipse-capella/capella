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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * This is an abstract base class for data values' types queries.<br>
 * Basically, it gets every Data Types corresponding to a given <code>EClass</code>.<br>
 * This <code>EClass</code> is given by concrete implementations of this class via the <code>getAvailableEClassForType()</code> method.
 */
public abstract class AbstractValue_Type extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (!systemEngineeringExists(element_p)) {
      return currentElements;
    }
    if (element_p instanceof DataValue) {
      DataValue currentValue = (DataValue) element_p;
      if (currentValue.getAbstractType() != null)
        currentElements.add((CapellaElement) currentValue.getAbstractType());
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    return CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, getAvailableEClassForType(), null);
  }

  /**
   * Returns the available <code>EClass</code> for the current value type search.
   * @return an <code>EClass</code> list instance
   */
  protected abstract List<EClass> getAvailableEClassForType();
}
