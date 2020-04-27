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
package org.polarsys.capella.core.data.information.validation.association;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;


public class AssociationContainerCheck extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        // TODO: use AssociationExt.isInItsCorrectLocation
        Association ass = (Association) eObj;
        if (AssociationExt.isUnidirectional(ass)) {
          // check that association container is the association source class container
          Class sourceClass = AssociationExt.getSourceClass(ass);
          if (null != sourceClass) {
            EObject sourceClassContainer = sourceClass.eContainer();
            if (!sourceClassContainer.equals(ass.eContainer())) {
              return ctx.createFailureStatus(ass, sourceClassContainer);
            }
          }

        } else if (AssociationExt.isBidirectional(ass) || AssociationExt.isNondirectional(ass)) {
          // check that association container is source & target classe common ancestors
          boolean assoIsInLinkedClassifiersCommonAcestor = AssociationExt.isInCommonAncestorOf(ass, AssociationExt.getLinkedClassifiers(ass));
          if (!assoIsInLinkedClassifiersCommonAcestor) {
            return ctx.createFailureStatus(ass, AssociationExt.getLinkedClassifiersCommonAncestor(ass));

          }
        }
        return ctx.createSuccessStatus();
      }
    }
    return null;
  }
}
