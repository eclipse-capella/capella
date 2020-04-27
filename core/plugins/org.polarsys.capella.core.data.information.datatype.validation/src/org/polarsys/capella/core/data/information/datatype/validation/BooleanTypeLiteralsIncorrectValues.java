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
package org.polarsys.capella.core.data.information.datatype.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class BooleanTypeLiteralsIncorrectValues extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) 
	{
		// Get the target
		EObject eObj = ctx.getTarget();
		// if the target is a Boolean Type
		if(eObj instanceof BooleanType){
		  BooleanType booleanType = (BooleanType) eObj;
		  // collect owned literals of current boolean type
		  EList<LiteralBooleanValue> ownedLiterals = booleanType.getOwnedLiterals();
		  // check if size is 2 (if size is > 2, the default model validation is activated)
		  if (ownedLiterals.size()==2) {
	      LiteralBooleanValue literalBV1 = ownedLiterals.get(0);
	      LiteralBooleanValue literalBV2 = ownedLiterals.get(1);
	      if (literalBV1 != null && literalBV2 != null) {
	        // return failure message if  both values are either true or false
	        if (literalBV1.isValue() == literalBV2.isValue()) {
	          // return failure
	          return ctx.createFailureStatus(new Object[] { booleanType.getName() });	          
	        }  
        }
      }
		}
		// return success
		return ctx.createSuccessStatus();
	}

}
