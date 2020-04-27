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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Physical Layer ExchangeItem mechanism can not be UNSET Applay's same for other layer ExchangeItem if referred by any element contained in Physical Layer
 */
public abstract class AbstractExchangeItemMechanismRestriction extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof ExchangeItem) {
      ExchangeItem item = (ExchangeItem) eObj;
      ExchangeMechanism exchangeMechanism = item.getExchangeMechanism();
      // check if element is in physical layer
      boolean elementInRequiredLayer = isInCurrentLayer(item);
      boolean createFailureStatus = false;
      // continue if element in physical layer and exchangeMecanism not null
      if ((null != exchangeMechanism)) {
        // check if exchange mechanism is UNSET
        if (elementInRequiredLayer) {
          createFailureStatus = isExchangeMechanismUnSet(exchangeMechanism);
        }
        // check if exchangeItem of other layer is referred by any physicalLayer element}
        if (checkIfReferedByOtherLayer()) {
          // cross references objects
          Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(item);
          for (Setting setting : inverseReferencesOfEObject) {
            // get the object
            EObject eObject = setting.getEObject();
            // if object in
            if ((null != eObject) && (eObject instanceof CapellaElement) && isInCurrentLayer((CapellaElement) eObject)) {
              createFailureStatus = isExchangeMechanismUnSet(exchangeMechanism);
              break;
            }
          }
        }
      }
      // return failure message
      if (createFailureStatus) {
        return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(item));
      }
    }

    return ctx.createSuccessStatus();
  }

  /**
   * filter element for each layer
   * @param item : capella element
   * @return
   */
  public abstract boolean isInCurrentLayer(CapellaElement element);

  /**
   * Reference to other layer ExchangeItem check required or not
   * @return
   */
  public abstract boolean checkIfReferedByOtherLayer();

  private boolean isExchangeMechanismUnSet(ExchangeMechanism exchangeMechanism) {
    if (exchangeMechanism.equals(ExchangeMechanism.UNSET)) {
      // failure status return if exchangeMecanism is 'UNSET'
      return true;
    }
    return false;
  }

}
