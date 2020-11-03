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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 *
 */
public class EI01_ExchangeItemMechanism extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof ExchangeItem) {
      ArrayList<Object> listExclude = new ArrayList<Object>();
      listExclude.add(ExchangeMechanism.UNSET);
      boolean hasSameMechanism = !RefinementLinkExt.hasMissingValuesFromRefined(eObj, InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM, listExclude);
      
      if (!hasSameMechanism) {
        return ctx.createFailureStatus(new Object[] { ((AbstractNamedElement)eObj.eContainer()).getName(), CapellaElementExt.getName(eObj)});
      }
    }
    return ctx.createSuccessStatus();
  }

}
