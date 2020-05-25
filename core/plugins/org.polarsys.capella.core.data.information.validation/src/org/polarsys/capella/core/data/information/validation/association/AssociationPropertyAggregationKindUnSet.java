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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Assocation navigation members
 */
public class AssociationPropertyAggregationKindUnSet extends AbstractValidationRule {

  /**
   * an <code>Association</code> must have at least one member with kind = ASSOCIATION
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        Association currentAssociation = (Association) eObj;
        if ((currentAssociation.getNavigableMembers() != null) && (currentAssociation.getOwnedMembers() != null)) {
          List<String> assocationEnds = new ArrayList<String>(0);
          if (currentAssociation.getOwnedMembers() != null) {
            for (Property aProperty : currentAssociation.getOwnedMembers()) {
              if (aProperty.getAggregationKind().equals(AggregationKind.UNSET)) {
                assocationEnds.add(aProperty.getName());
              }
            }
          }
          if (currentAssociation.getNavigableMembers() != null) {
            for (Property aProperty : currentAssociation.getNavigableMembers()) {
              if (aProperty.getAggregationKind().equals(AggregationKind.UNSET)) {
                assocationEnds.add(aProperty.getName());
              }
            }
          }
          if (!assocationEnds.isEmpty()) {
            return ctx.createFailureStatus(new Object[] { currentAssociation.getName(), assocationEnds.toString() });
          }
        }

      }
    }
    return ctx.createSuccessStatus();
  }

}
