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
package org.polarsys.capella.core.data.information.datatype.validation.datatypepkg;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class DataValueUnitconsistency extends AbstractValidationRule 
{
  @Override
  public IStatus validate(IValidationContext ctx) 
  {
    // Get the target
    EObject eObj = ctx.getTarget();
    if(eObj instanceof NumericValue)
    {
      // Typing the DataValue
      boolean failure = false;
      NumericValue dataValue = (NumericValue)eObj;
      // retrieve the unit value
      Unit unit = dataValue.getUnit();
      // retrieve the type
      AbstractType abstractType = dataValue.getAbstractType();
      // continue if unit is not null and type is of kind PhysicalQuantity 
      if (unit != null && abstractType != null && abstractType instanceof PhysicalQuantity) {
    	  PhysicalQuantity pq = (PhysicalQuantity) abstractType; 
    	  // retrieve physical quantity unit value
    	  Unit pqunit = pq.getUnit();
    	  if (null != pqunit) {
    		// return failure message if unit value of current
    		// element is not equal to unit value of its type(which is physical quantity)  
			if (!pqunit.equals(unit)) {
				failure = true;
			}
    	  }else {
    		  failure = true;
    	  }
      }
      if(failure){
    	  return ctx.createFailureStatus(new Object[] {dataValue.getName()});
      }
    }
    // Validation success
    return ctx.createSuccessStatus();
  }

  


}
