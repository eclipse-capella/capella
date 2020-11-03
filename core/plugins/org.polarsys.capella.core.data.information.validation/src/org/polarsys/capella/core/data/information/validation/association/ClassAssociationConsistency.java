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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.PropertyExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class ClassAssociationConsistency extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Association) {
    	Association assoc = (Association) eObj;
    	List<Property> propsToCheck = new ArrayList<Property>();
    	
      if (AssociationExt.isUnidirectional(assoc)) {
		propsToCheck.addAll(assoc.getNavigableMembers());
		propsToCheck.addAll(assoc.getOwnedMembers());

      } else if (AssociationExt.isBidirectional(assoc)) {
    	  propsToCheck.addAll(assoc.getNavigableMembers());
      
      } else if(AssociationExt.isNondirectional(assoc)){
    	  propsToCheck.addAll(assoc.getOwnedMembers());
      }
      
      for (Property property : propsToCheck) {
		
      if (!PropertyExt.isTyped(property)) {
          IStatus status = ctx.createFailureStatus(new Object[] { property.getName(), assoc.getName()});
          statuses.add(status);
        }
      }
      
      if (!statuses.isEmpty()) {
        return ConstraintStatus.createMultiStatus(ctx, statuses);
      }
    }
    return ctx.createSuccessStatus();
  }
}
