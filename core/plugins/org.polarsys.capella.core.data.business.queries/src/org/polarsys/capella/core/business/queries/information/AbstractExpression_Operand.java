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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.StringReference;


/**
 * 
 */
public abstract class AbstractExpression_Operand extends CapellaElement_CurrentAndHigherLevelsQuery{
  
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
   List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

   if (capellaElement_p instanceof AbstractExpressionValue) {
     if (dataPkg_p != null) {
       AbstractType abstractType = ((AbstractExpressionValue)capellaElement_p).getAbstractType();
       if (null != abstractType && abstractType instanceof DataType) {
         // propose dataValue consistance with given DataType  
         List<CapellaElement> dataValues = CapellaElementsHelperForBusinessQueries.
           getDataValuesConsistantWithDataType(dataPkg_p,(DataType) abstractType);
           
         availableElements.addAll(dataValues);
       }else{
         // propose all the elements (?)
         for (EObject obj : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
           availableElements.add((CapellaElement) obj);
         }
       }
     }
   }

   return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof AbstractExpressionValue) {
      Object data = element_p.eGet(getEStructuralFeatures().get(0));
      if (data instanceof BooleanReference) {
        currentElements.add(((BooleanReference) data).getReferencedValue());
      }
      else if (data instanceof ComplexValueReference) {
        currentElements.add(((ComplexValueReference) data).getReferencedValue());
      }
      else if (data instanceof EnumerationReference) {
        currentElements.add(((EnumerationReference) data).getReferencedValue());
      }
      else if (data instanceof NumericReference) {
        currentElements.add(((NumericReference) data).getReferencedValue());
      }
      else if (data instanceof StringReference) {
        currentElements.add(((StringReference) data).getReferencedValue());
      }else if(null != data && data instanceof CapellaElement){
        currentElements.add((CapellaElement) data);
      }
    }

    return currentElements;
  }
}
