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

import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public abstract class AbstractDataValueTypeCheck extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (isInstanceOf(eObj) && (eObj instanceof DataValue)) {
      DataValue value = (DataValue) eObj;
      AbstractType abstractType = value.getAbstractType();
      if (null == abstractType) {
        return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(value), "[" + DataValueExt.getContainementFeatureofDataValue(value) + "]"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    return ctx.createSuccessStatus();
  }

  /**
   * @param eObj
   * @return
   */
  public abstract boolean isInstanceOf(EObject eObj);
}
