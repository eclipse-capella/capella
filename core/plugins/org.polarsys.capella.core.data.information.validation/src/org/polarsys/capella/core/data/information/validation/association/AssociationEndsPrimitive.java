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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This rule ensure that the cardinality of the CompositionAssociation is 0..1 or 1..1
 * 
 */
public class AssociationEndsPrimitive extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        // current Association
        Association association = (Association) eObj;
        List<Property> ownedRoles = new ArrayList<Property>(1);
        ownedRoles.addAll(association.getNavigableMembers());
        
        ownedRoles.addAll(association.getOwnedMembers());
        boolean isPrimitive =  false;
        // collect list of isPrimitive Elements
        List<NamedElement> primitiveEles = new ArrayList<NamedElement>();
        // iterate over all Association ends 
        for (Property property : ownedRoles) {
          AbstractType abstractType = property.getAbstractType();
          if (null != abstractType) {
            // filter Class
            if (abstractType instanceof Class ) {
              Class cls = (Class) abstractType;
              if (cls.isIsPrimitive()) {
                isPrimitive = true;
                primitiveEles.add(cls);
              }
              
            }// filter Collection
            else if (abstractType instanceof Collection) {
              Collection collection = (Collection) abstractType;
              if (collection.isIsPrimitive()) {
                  isPrimitive = true;
                  primitiveEles.add(collection);
              }
            }
          }
        }
        
        // if association ends contain any primitive elements, return err message
        if (isPrimitive) {
          String strPrimitiveEls = ICommonConstants.EMPTY_STRING;
          Iterator<NamedElement> iterator = primitiveEles.iterator();
          while(iterator.hasNext()){
            NamedElement next = iterator.next();
            strPrimitiveEls = strPrimitiveEls + next.getName();
            if (iterator.hasNext()) {
              strPrimitiveEls  = strPrimitiveEls + " , "; //$NON-NLS-1$
            }
          }
          // create err message
          return ctx.createFailureStatus(new Object[] { association.getName(), strPrimitiveEls});
        }
 
      }
    }
    return ctx.createSuccessStatus();
  }
  
}
