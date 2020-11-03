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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * Value Reference shall reference a value with a name
 */
@Deprecated
public class MultipilictyElementOwnedDataVlueType extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof DataValue) {
      DataValue element = (DataValue) eObj;
      boolean isTyped = isDataValueTyped(element);
      List<String> featuresWithOutType = new ArrayList<String>(0);

      Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(eObj);
      for (Setting setting : inverseReferencesOfEObject) {
        EStructuralFeature eStrFea = setting.getEStructuralFeature();
        if (null != eStrFea) {
          if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          } else if (eStrFea.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)) {
            if (!isTyped) {
              featuresWithOutType.add(getReableFeatureName(eStrFea));
            }
          }
        }
      }

      if (!featuresWithOutType.isEmpty()) {
        return ctx.createFailureStatus(new Object[] { CapellaElementExt.getCapellaExplorerLabel(element), featuresWithOutType.toString() });
      }
    }

    return ctx.createSuccessStatus();
  }

  private String getReableFeatureName(EStructuralFeature eStrFea) {
    String eStrFeaName = eStrFea.getName();
    String readableFeatureName = eStrFeaName.replaceAll("owned", ICommonConstants.EMPTY_STRING); //$NON-NLS-1$
    return readableFeatureName;
  }

  private boolean isDataValueTyped(DataValue ownedDefaultValue) {
    if (null != ownedDefaultValue) {
      AbstractType type = ownedDefaultValue.getAbstractType();
      if (null == type) {
        return false;
      }
    }
    return true;
  }

}
