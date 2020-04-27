/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.union;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This check ensures that the values used as qualifiers of Union Properties 
 *    are consistent with the Discriminant of the Union. 
 * That is data value used as qualifier are valid or not.
 */
public class UnionPropertyQualifierConsistency extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // Handles <code>Union</code> instances
      if (eObj instanceof UnionProperty) {
        UnionProperty unionPro = (UnionProperty)eObj;
        EObject container = unionPro.eContainer();
        List<IStatus> statuses = new ArrayList<IStatus>();
        // get Union
        if (container != null && container instanceof Union) {
          Union union = (Union) container;
          // get discriminant_unionroperty
          UnionProperty discrPro = union.getDiscriminant();
          if (null !=  discrPro) {
            // get desiriminant_unionroperty Type
            AbstractType discrProType = discrPro.getAbstractType();
            if (null != discrProType && discrProType instanceof DataType) {
              // get all the sub type of the 'discrProType'
              List<GeneralizableElement> allSubGeneralizableElements = GeneralizableElementExt.getAllSubGeneralizableElements((DataType)discrProType);
              allSubGeneralizableElements.add((DataType)discrProType);
              
              // get desiriminant_unionroperty Type EClass
              EClass discrProTypeEClass = discrProType.eClass();
              // get qualifiers
              EList<DataValue> qualifier = unionPro.getQualifier();
              for (DataValue dataValue : qualifier) {
                // get qualifier Type
                AbstractType dataValueAbsType = dataValue.getAbstractType();
                if (null != dataValueAbsType) {
                  // check that dataValue type is equal to all the current discrProType and all its subtype
                  boolean typeCheck = false;
                  for (GeneralizableElement genEle : allSubGeneralizableElements) {
                    EClass genEleEClass = genEle.eClass();
                    if(dataValueAbsType.eClass().getName().equalsIgnoreCase(genEleEClass.getName())){
                      typeCheck = true;
                    }
                  }
                  
                  if(!typeCheck){
                    // create failure message
                    statuses.add(ctx.createFailureStatus(new Object[] { dataValue.getName(), dataValue.eClass().getName(),
                                                                           unionPro.getName(), 
                                                                           discrProTypeEClass.getName()}));
                  }
                }else{
                  if(!DataValueExt.isDataValueConsitantWithDataType(dataValue, (DataType)discrProType)){
                    statuses.add(ctx.createFailureStatus(new Object[] { dataValue.getName(), dataValue.eClass().getName(),
                                                                           unionPro.getName(), 
                                                                           discrProTypeEClass.getName()}));
                  }
                  
                }
              }
            }
          } 
        }
        if (statuses.size() > 0) {
          // problem encountered
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }

}
