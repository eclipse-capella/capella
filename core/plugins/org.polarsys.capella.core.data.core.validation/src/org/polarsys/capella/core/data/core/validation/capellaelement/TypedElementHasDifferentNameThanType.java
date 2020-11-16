/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class TypedElementHasDifferentNameThanType extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext validationContext) {
    EObject target = validationContext.getTarget();
    EMFEventType eventType = validationContext.getEventType();

    if (eventType == EMFEventType.NULL && target instanceof AbstractTypedElement) {
      AbstractTypedElement typedElement = (AbstractTypedElement) target;
      AbstractType type = typedElement.getAbstractType();

      // another validation rules checks if the name is null
      // we only raise an error if there is a name against which we can compare
      if (type != null && type.getName() != null && !type.getName().equals(typedElement.getName())) {
        if (typedElement instanceof Part
            && TriStateBoolean.True.equals(CapellaProjectHelper.isSingletonComponentsDriven(typedElement))) {
          return constructFailureStatus(validationContext, typedElement, type);
        }

        else if (typedElement instanceof ExchangeItemInstance && type instanceof ExchangeItem) {
          ExchangeItem exchangeItem = (ExchangeItem) type;
          if (ExchangeItemExt.getTypedExchangeItemInstances(exchangeItem).size() == 1) {
            return constructFailureStatus(validationContext, typedElement, type);
          }

        }
      }
    }

    return validationContext.createSuccessStatus();
  }

  private IStatus constructFailureStatus(IValidationContext validationContext, AbstractTypedElement typedElement,
      AbstractType type) {
    return validationContext.createFailureStatus(typedElement.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(typedElement, false), type.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(type, false));
  }

}
