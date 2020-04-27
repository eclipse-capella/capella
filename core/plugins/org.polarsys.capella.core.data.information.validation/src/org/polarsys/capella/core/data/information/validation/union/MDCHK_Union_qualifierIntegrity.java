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
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that every union property of an union (except discriminant and default property) has at least one qualifier. [MultiStatus Message]
 */
public class MDCHK_Union_qualifierIntegrity extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Union) {
        // Typing union
        Union union = (Union) eObj;
        List<IStatus> statuses = new ArrayList<IStatus>();
        // make sure the kind is UNION
        if (union.getKind() == UnionKind.UNION) {
          // get discriminant from union
          UnionProperty discriminant = union.getDiscriminant();
          if (null != discriminant) {
            // get default property from union
            UnionProperty defaultPro = union.getDefaultProperty();
            // get all properties
            EList<UnionProperty> properties = union.getContainedUnionProperties();
            for (UnionProperty property : properties) {
              if ((discriminant != property) && (defaultPro != property)) {
                EList<DataValue> qualifier = property.getQualifier();
                // union property (other then default and discriminant) should have at least one qualifier
                if (qualifier.isEmpty()) {
                  IStatus status = ctx.createFailureStatus(new Object[] { property.getName(), union.getName() });
                  statuses.add(status);
                }
              }
            }
          }

        }
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }

}
