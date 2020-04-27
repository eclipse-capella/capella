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
package org.polarsys.capella.core.data.information.validation.dataValue;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Value Reference shall reference a value with a name
 */
public class DataValueReferenceReferencedValue extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof BooleanReference
    		|| eObj instanceof NumericReference
    		|| eObj instanceof StringReference
    		|| eObj instanceof EnumerationReference
    		|| eObj instanceof ComplexValueReference
    		|| eObj instanceof CollectionValueReference) {
    	DataValue referencedValue =  DataValueExt.getReferencedValue(eObj);
        // check the null value
        if (null != referencedValue) {
        	// if referenced value is not named raise a failure status.
			if (null == referencedValue.getName() || referencedValue.getName().equals(ICommonConstants.EMPTY_STRING)) {
				return ctx.createFailureStatus(new Object[] {((DataValue)eObj).getName(),eObj.eClass().getName()});
			}
		}
    }

    return ctx.createSuccessStatus();
  }

}
