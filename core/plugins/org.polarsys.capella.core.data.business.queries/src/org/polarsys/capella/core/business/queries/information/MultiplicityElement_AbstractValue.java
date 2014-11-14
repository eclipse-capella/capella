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

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;

/**
 * This is an abstract class used for <code>Property</code> and <code>Parameter</code> queries such as null or default values.
 */
public abstract class MultiplicityElement_AbstractValue extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    if (capellaElement_p instanceof MultiplicityElement) {

      List<CapellaElement> returnValue =
          CapellaElementsHelperForBusinessQueries.getStandardApplicableValuesForMultiplicityElementInLevel(dataPkg_p, (MultiplicityElement) capellaElement_p,
              getEStructuralFeatures());
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesWithTypeOf(dataPkg_p, capellaElement_p));
      return returnValue;
    }
    return Collections.emptyList();
  }

  @Override
  public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement_p) {

    // Gets the owned literals of the current boolean type root ancestors
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof Property) {
      AbstractType type = ((Property) capellaElement_p).getAbstractType();
      if (type != null) {
        if (type instanceof Enumeration) {
          List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((Enumeration) type);
          rootSupertypes.add((GeneralizableElement) type);
          for (CapellaElement melElem : rootSupertypes) {
            if (melElem instanceof Enumeration) {
              Enumeration rootBooleanType = (Enumeration) melElem;
              returnValue.addAll(rootBooleanType.getOwnedLiterals());
            }
          }

        } else if (type instanceof BooleanType) {
          List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((BooleanType) type);
          rootSupertypes.add((GeneralizableElement) type);
          for (CapellaElement melElem : rootSupertypes) {
            if (melElem instanceof BooleanType) {
              BooleanType rootBooleanType = (BooleanType) melElem;
              returnValue.addAll(rootBooleanType.getOwnedLiterals());
            }
          }
        }
      }
    }
    return returnValue;
  }

}
