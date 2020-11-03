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
package org.polarsys.capella.core.data.information.validation.dataValue;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * All direct dataValue should have a name
 */
public class DirectDataValueNameCheck extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof DataValue) {
      DataValue dataValue = (DataValue) eObj;

      if (new DataValueDefinedInItsNamespace().isSatisfiedBy(dataValue)) {
        // the name should not be null or empty_string
        String dataValueName = dataValue.getName();
        if ((null == dataValueName) || dataValueName.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          return ctx.createFailureStatus("Data Value should be named"); //$NON-NLS-1$
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  class DataValueDefinedInItsNamespace implements Specification<DataValue> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(DataValue candidate) {
      // consider [MultipilicityElement, DataType, Class, DataPkg]
      Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(candidate);
      for (Setting setting : inverseReferencesOfEObject) {
        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
        if (eStructuralFeature.equals(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES)
            || eStructuralFeature.equals(DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE)
            || eStructuralFeature.equals(InformationPackage.Literals.CLASS__OWNED_DATA_VALUES)) {
          return true;
        }
      }
      return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<DataValue> or(Specification<DataValue> specification) {
      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<DataValue> and(Specification<DataValue> specification) {
      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<DataValue> not() {
      return null;
    }
  }
}
