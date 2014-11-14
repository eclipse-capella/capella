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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * This class is an abstraction for XXXReference element referenced property queries
 */
public abstract class AbstractReference_ReferencedProperty extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    if (capellaElement_p instanceof DataValue) {
      DataValue dataValue = (DataValue) capellaElement_p;
      AbstractType type = dataValue.getAbstractType();
      if (type != null && type instanceof GeneralizableElement) {
        return filterPropertiesTypesWithAvailableEClass(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, (GeneralizableElement) type, false));
      }
      // If the type is not set, returns all data values
      return CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, null, true);
    }
    return Collections.emptyList();
  }

  /**
   * Returns the available eclass for the properties type.
   * @return an <code>EClass</code> instance
   */
  protected abstract List<EClass> getAvailableEClassForPropertiesTypes();

  /**
   * Filters the given properties list in order to check the properties types in order to be sure they match the available types for the current element.
   * @param list_p the properties list
   * @return the filtered list
   */
  protected List<CapellaElement> filterPropertiesTypesWithAvailableEClass(List<CapellaElement> list_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    List<EClass> availableEClass = getAvailableEClassForPropertiesTypes();
    for (CapellaElement element : list_p) {
      if (element instanceof Property) {
        Type propertyType = ((Property) element).getType();
        if (CapellaElementsHelperForBusinessQueries.canBeInstanciatedAs(propertyType, availableEClass)) {
          returnValue.add(element);
        }
      }
    }
    return returnValue;
  }
}
