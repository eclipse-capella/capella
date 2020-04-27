/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.physicalLink;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures correct location for a physical link
 */
public class PhysicalLink_Location extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Raise a warning if physical link is not located in the common ancestor between both physical link bounds
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof PhysicalLink) {
      AbstractNamedElement container = PhysicalLinkExt.getDefaultContainer((PhysicalLink) eObj);
      if ((container != null) && !(container.equals(eObj.eContainer()))) {
        return ctx.createFailureStatus(((PhysicalLink) eObj).getName(), container.getName());
      }
    }
    return ctx.createSuccessStatus();
  }
}
