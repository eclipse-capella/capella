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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule allows to verify that the discriminant of an union do not have any qualifier.
 */
public class MDCHK_Union_DiscriminantUnionPropertyQualifier extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Union) {
    	// Typing by Union  
        Union union = (Union) eObj;
        List<IStatus> statuses = new ArrayList<IStatus>();
        // get discriminant from union
        UnionProperty discriminant = union.getDiscriminant();
        String unionName = union.getName();
        if (null != discriminant){
        	// get qualifier from discriminant
	        EList<DataValue> discriminantQualifiers = discriminant.getQualifier();
	        // if any qualifier is set return failure status
	        // because the union property which is discriminant of union can not have any qualifier 
	        if (!discriminantQualifiers.isEmpty()) {
	          statuses.add(ctx.createFailureStatus(new Object[] { discriminant.getName(), unionName }));
	        }
	        if (statuses.size() > 0) {
	          return ConstraintStatus.createMultiStatus(ctx, statuses);
	        }
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }

}
