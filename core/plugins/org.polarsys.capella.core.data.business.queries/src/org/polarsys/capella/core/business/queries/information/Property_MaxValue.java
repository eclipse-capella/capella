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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.StringType;

/**
 * This is the query for the Properties max values
 */
public class Property_MaxValue  extends AbstractMultiplicityElement_MaxValue{
  
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.PROPERTY;
  }
  
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture, org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>(1);
    if (capellaElement_p instanceof Property) {
      Property pro = (Property) capellaElement_p;
      AbstractType abstractType = pro.getAbstractType();
      if (null != abstractType) {
        // if its StringType
        // the query should return the same values required for cardinalities
        // below code is copied from MultiplicityElement_AbstractValue Abstract Class
        if (abstractType instanceof StringType) {
          returnValue = CapellaElementsHelperForBusinessQueries.getStandardApplicableValuesForMultiplicityElementInLevel(dataPkg_p,(MultiplicityElement) capellaElement_p, getEStructuralFeatures());
          returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesWithTypeOf(dataPkg_p, capellaElement_p));
        }else{
          returnValue = super.getDataFromLevel(dataPkg_p, capellaElement_p);
        }
      }
    }
    
    return returnValue;
  } 
}
