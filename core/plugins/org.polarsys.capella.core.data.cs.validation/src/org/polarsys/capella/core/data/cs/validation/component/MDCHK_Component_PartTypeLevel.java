/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that each parts of a component have the same level (i.e context, logical, physical,...) that the
 * component itself.
 */
public class MDCHK_Component_PartTypeLevel extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Component cpnt = (Component) eObj;
        for (Part part : cpnt.getContainedParts()) {

          AbstractType abstractType = part.getAbstractType();
          if (null != abstractType) {
            // Cannot test the ports levels...
            if (cpnt instanceof Entity && !(abstractType instanceof Entity)) {
              return ctx.createFailureStatus(cpnt.getName(), cpnt.eClass().getName(), part.getName(),
                  part.getAbstractType().eClass().getName(), part.getAbstractType().getName());
            }
            if (cpnt instanceof LogicalComponent && !(abstractType instanceof LogicalComponent)) {
              return ctx.createFailureStatus(cpnt.getName(), cpnt.eClass().getName(), part.getName(),
                  part.getAbstractType().eClass().getName(), part.getAbstractType().getName());
            }
            if (cpnt instanceof PhysicalComponent && !(abstractType instanceof PhysicalComponent)) {
              return ctx.createFailureStatus(cpnt.getName(), cpnt.eClass().getName(), part.getName(),
                  part.getAbstractType().eClass().getName(), part.getAbstractType().getName());
            }
            if (cpnt instanceof ConfigurationItem && !(abstractType instanceof ConfigurationItem)) {
              return ctx.createFailureStatus(cpnt.getName(), cpnt.eClass().getName(), part.getName(),
                  part.getAbstractType().eClass().getName(), part.getAbstractType().getName());
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
