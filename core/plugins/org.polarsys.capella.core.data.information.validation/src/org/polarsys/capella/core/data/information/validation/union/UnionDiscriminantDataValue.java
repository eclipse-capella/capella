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
package org.polarsys.capella.core.data.information.validation.union;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This rule allows to verify that a data value used as a qualifier for a union property cannot be used as a qualifier of another union property of the same
 * union. That is a data value is only used once.
 */
public class UnionDiscriminantDataValue extends AbstractValidationRule {
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
          EList<UnionProperty> properties = union.getContainedUnionProperties();
          // if no discriminant discontinue the rule
          if (discriminant == null) {
            return ctx.createSuccessStatus();
          }

          AbstractType abstractType = discriminant.getAbstractType();
          if ((null != abstractType) && (abstractType instanceof DataType)) {
            DataType dataType = (DataType) abstractType;
            EList<DataValue> ownedDataValues = dataType.getOwnedDataValues();
            for (DataValue dataValue : ownedDataValues) {
              Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(dataValue);
              if (!inverseReferencesOfEObject.isEmpty()) {
                boolean failureStaus = true;
                for (Setting setting : inverseReferencesOfEObject) {
                  EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                  EObject eObject = setting.getEObject();
                  boolean equals = eStructuralFeature.equals(InformationPackage.Literals.UNION_PROPERTY__QUALIFIER);
                  if ((null != eObject) && (eObject instanceof UnionProperty) && equals) {
                    UnionProperty uPro = (UnionProperty) eObject;
                    if (properties.contains(uPro) && uPro.getQualifier().contains(dataValue)) {
                      failureStaus = false;
                    }
                  }
                }
                if (failureStaus) {
                  // failure message
                  statuses.add(ctx.createFailureStatus(new Object[] { dataValue.getName(), discriminant.getName(), union.getName() }));
                }
              } else {
                // failure message
                statuses.add(ctx.createFailureStatus(new Object[] { dataValue.getName(), discriminant.getName(), union.getName() }));
              }
            }
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
