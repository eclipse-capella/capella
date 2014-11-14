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
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * This class is an abstraction for XXXReference element referenced value queries
 */
public abstract class AbstractReference_ReferencedValue extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = Collections.emptyList();

    if (capellaElement_p instanceof DataValue) {
      DataValue dataValue = (DataValue) capellaElement_p;
      AbstractType type = dataValue.getAbstractType();
      if (null == type) {
        // If the type is not set, returns all data values
        List<CapellaElement> dataValues = CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, getSearchedEClassForValues(), true, true);
        // Filters the expressions in order to avoid expression typed by a type which would not correspond to the current element
        returnValue = filterExpressions(dataValues);
      } else if (type instanceof GeneralizableElement){
        returnValue = CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, (GeneralizableElement) type, true, true, getSearchedEClassForValues(), capellaElement_p);
      }
    }
    // filters the returnValue to remove the current element if it is ever in the list:
    if (returnValue.contains(capellaElement_p)) {
      int indexOfCurrentElement = returnValue.indexOf(capellaElement_p);
      returnValue.remove(indexOfCurrentElement);
    }
    return returnValue;
  }

  /**
   * Returns the <code>EClass</code> you have to match for the returned data values in the
   * <code>getDataFromLevel(BlockArchitecture blockArchitecture_p, CapellaElement capellaElement_p)</code> method.
   * @return
   */
  protected abstract EClass getSearchedEClassForValues();

  /**
   * Filters the expressions in the list in order to avoid those which should not be returned by the query.
   * @param list_p the list to filter
   * @return the filtered list
   */
  protected List<CapellaElement> filterExpressions(List<CapellaElement> list_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement element : list_p) {
      if (!(element instanceof AbstractExpressionValue)) {
        returnValue.add(element);
      } else {
        DataType expressionType = ((AbstractExpressionValue) element).getExpressionType();
        if (null == expressionType) {
          returnValue.add(element);
        } else if (isAValidExpressionType(expressionType)) {
          returnValue.add(element);
        }
      }
    }
    return returnValue;
  }

  /**
   * Allows to know if an expression typed by the given data type shall be returned by the current query.
   * @param dataType_p the data type
   * @return <code>true</code> if it should be returned by the query, <code>false</code> otherwise
   */
  protected abstract boolean isAValidExpressionType(DataType dataType_p);
}
