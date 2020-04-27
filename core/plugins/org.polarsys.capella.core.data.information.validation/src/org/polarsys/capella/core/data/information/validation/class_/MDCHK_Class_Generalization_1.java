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
package org.polarsys.capella.core.data.information.validation.class_;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check weather Class generalize itself.
 */
public class MDCHK_Class_Generalization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Class) {
    	Class clz = (Class) eObj;
        EList<GeneralizableElement> superClasses = clz.getSuper();
        Iterator<GeneralizableElement> iterator = superClasses.iterator();
        while (iterator.hasNext()) {
          GeneralizableElement superClass = iterator.next();
          if (superClass == clz) {
            return ctx.createFailureStatus(new Object[] { clz.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
