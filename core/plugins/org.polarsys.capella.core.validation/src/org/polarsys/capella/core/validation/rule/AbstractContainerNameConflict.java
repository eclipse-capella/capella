/*******************************************************************************
 * Copyright (c) 2013, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * This class contains the execution logic of all naming conflict rules between an element and it's container.
 */
public abstract class AbstractContainerNameConflict extends AbstractValidationRule {

  /**
   * Verify that the rule is applicable to the given validated element
   * 
   * @param eObj
   *          the validated model element
   */
  protected abstract boolean isApplicable(EObject eObj);

  /**
   * get the model element's which name must be checked
   * 
   * @param eObj
   *          the validated model element
   */
  protected EObject target(EObject item) {
    return item;
  }

  /**
   * get the model element's which may be in conflict with the validated element
   * 
   * @param eObj
   *          the validated model element
   */
  protected EObject conflictingElement(EObject item) {
    return item.eContainer();
  }

  @Override
  public IStatus validate(IValidationContext ctx) {

    try {
      EObject eObj = ctx.getTarget();
      EObject conflicting = conflictingElement(eObj);
      EObject targetItem = target(eObj);

      // We explicitly disregard parts since they are also a Property
      // and it is not possible to enforce a different rule for Part
      // than for Property
      if (!isApplicable(eObj)) {
        return ctx.createSuccessStatus();
      }

      if (eObj instanceof AbstractNamedElement && conflicting != null) {

        AbstractNamedElement namedElt = (AbstractNamedElement) targetItem;
        AbstractNamedElement namedConflicting = (AbstractNamedElement) conflicting;
        String name = namedElt.getName();
        String conflictingName = namedConflicting.getName();

        boolean match = name.toLowerCase().equals(conflictingName.toLowerCase());

        if (match) {
          return ctx.createFailureStatus(new Object[] { eObj.eClass().getName(), name });
        }
      }

      return ctx.createSuccessStatus();
    } catch (Exception ex) {
      return ctx.createFailureStatus(new Object[] { ex.getClass().getName(), ex.toString() });
    }

  }

}
