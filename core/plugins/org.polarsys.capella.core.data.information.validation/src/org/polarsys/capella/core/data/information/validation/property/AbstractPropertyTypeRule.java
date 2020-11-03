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
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.model.helpers.PropertyExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This check ensures that properties are of type primitive if not bound to an Association.
 */
public abstract class AbstractPropertyTypeRule extends AbstractValidationRule {
	  /**
	   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	   */
	  @Override
	  public IStatus validate(IValidationContext ctx) {
	    EObject eObj = ctx.getTarget();
	    EMFEventType eType = ctx.getEventType();

	    if (eType == EMFEventType.NULL) {
	      if (eObj instanceof Property) {
	        Property property = (Property) eObj;
	        EObject eContainer = property.eContainer();
	        // decide weather to continue the validation rule: based on primitive type check 
	        // of a container
	        if(isParentPrimitiveCheckApplied(eContainer))
	        	return  ctx.createSuccessStatus();
	        
	        // Filter the property bound to an Association
	        if (PropertyExt.getRegardingAssociation(property) == null) {
	          AbstractType abstractType = property.getAbstractType();
	          if (abstractType instanceof Class) {
	            Class cls = (Class) abstractType;
	            if (!cls.isIsPrimitive()) {
	              return ctx.createFailureStatus(new Object[] { property.getName(), cls.getName(),cls.eClass().getName() });
	            }
	          }else if (abstractType instanceof Collection) {
	            Collection collection = (Collection) abstractType;
	            if (!collection.isIsPrimitive()) {
	              return ctx.createFailureStatus(new Object[] { property.getName(), collection.getName(),collection.eClass().getName()});
	            }
	          }
	        }
	      }
	    }
	    return ctx.createSuccessStatus();
	  }

	/**
	 * @param ctx
	 * @param eContainer
	 * @return
	 */
	public abstract boolean isParentPrimitiveCheckApplied(EObject eContainer);
}
