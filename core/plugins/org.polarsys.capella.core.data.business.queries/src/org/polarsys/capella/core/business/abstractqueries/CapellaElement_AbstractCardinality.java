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
package org.polarsys.capella.core.business.abstractqueries;

import java.util.List;

import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * The rule to get the cardinalities possible values is always the same. So it is written in this abstract class.
 */
public abstract class CapellaElement_AbstractCardinality extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.information.datatype.DataType)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = CapellaElementsHelperForBusinessQueries.getApplicableValuesForCardinalitiesInLevel(dataPkg_p);
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getApplicablePropertiesForCardinalitiesInLevel(dataPkg_p));
    return returnValue;
  }
}
