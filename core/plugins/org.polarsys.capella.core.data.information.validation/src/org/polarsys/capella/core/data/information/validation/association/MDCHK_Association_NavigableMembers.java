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
package org.polarsys.capella.core.data.information.validation.association;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 *
 */
public class MDCHK_Association_NavigableMembers extends AbstractValidationRule {

  /**
   * An <code>Association</code> should have at least one navigable member
   * 
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association){
        Association currentAssociation = (Association) eObj;
        if ((currentAssociation.getNavigableMembers() == null) 
            || (currentAssociation.getNavigableMembers().size() < 1)){
          return ctx.createFailureStatus(new Object[] { currentAssociation.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
