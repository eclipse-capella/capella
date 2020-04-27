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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class AssociationConsistencyBetweenLayers extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        Association ass = (Association) eObj;
        Collection<Classifier> linkedClassifiers = AssociationExt.getLinkedClassifiers(ass);
        for (Classifier classifier : linkedClassifiers) {
          if (!CapellaLayerCheckingExt.isElementFromCurrentOrUpperLayer(classifier, ass)) {
            IStatus status = ctx.createFailureStatus(ass.getName(), classifier.getName(), classifier.eClass().getName());
            statuses.add(status);
          }
        }
      }
    }
    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }

}
