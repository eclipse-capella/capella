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
 * This rule allows to verify that a data value used as a qualifier for a union property cannot be used as a qualifier of another union property of the same
 * union. That is a data value is only used once.
 */
public class MDCHK_Union_QualifierCardinality extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // Handles <code>Union</code> instances
      if (eObj instanceof Union) {
        Union union = (Union) eObj;
        List<IStatus> statuses = new ArrayList<IStatus>();
        if (union.getKind() == UnionKind.UNION) {
          UnionProperty discriminant = union.getDiscriminant();
          UnionProperty defaultProperty = union.getDefaultProperty();
          EList<UnionProperty> properties = union.getContainedUnionProperties();
          List<DataValue> qualifiersList = new ArrayList<DataValue>();
          for (UnionProperty property : properties) {
            if ((discriminant != property) && (defaultProperty != property)) {
              EList<DataValue> qualifiers = property.getQualifier();
              for (DataValue qualifier : qualifiers) {
                // if qualifier is already used by another property : return failure message
                if (qualifiersList.contains(qualifier)) {
                  statuses.add(ctx.createFailureStatus(new Object[] { property.getName(), union.getName(), qualifier.getName() }));
                } else {
                  qualifiersList.add(qualifier);
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
