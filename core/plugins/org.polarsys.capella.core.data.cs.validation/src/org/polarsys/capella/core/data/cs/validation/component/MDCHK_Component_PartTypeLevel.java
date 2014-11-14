/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This rule ensures that each parts of a component have the same level (i.e context, logical, physical,...) that the component itself.
 */
public class MDCHK_Component_PartTypeLevel extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Component cpnt = (Component) eObj;
        EList<Partition> ownedPartitions = cpnt.getOwnedPartitions();
        Iterator<Partition> iterator = ownedPartitions.iterator();
        while (iterator.hasNext()) {
          Partition partition = iterator.next();
          AbstractType abstractType = partition.getAbstractType();
          if (null != abstractType && !(abstractType instanceof Port)) {
            // Cannot test the ports levels...
            if (cpnt instanceof Entity && !(abstractType instanceof Entity)) {
              return createFailureStatus(ctx_p, new Object[] { cpnt.getName(), cpnt.eClass().getName(), partition.getName(),
                                                              partition.getAbstractType().eClass().getName(), partition.getAbstractType().getName() });
            }
            if (cpnt instanceof LogicalComponent && !(abstractType instanceof LogicalComponent)) {
              return createFailureStatus(ctx_p, new Object[] { cpnt.getName(), cpnt.eClass().getName(), partition.getName(),
                                                              partition.getAbstractType().eClass().getName(), partition.getAbstractType().getName() });
            }
            if (cpnt instanceof PhysicalComponent && !(abstractType instanceof PhysicalComponent)) {
              return createFailureStatus(ctx_p, new Object[] { cpnt.getName(), cpnt.eClass().getName(), partition.getName(),
                                                              partition.getAbstractType().eClass().getName(), partition.getAbstractType().getName() });
            }
            if (cpnt instanceof ConfigurationItem && !(abstractType instanceof ConfigurationItem)) {
              return createFailureStatus(ctx_p, new Object[] { cpnt.getName(), cpnt.eClass().getName(), partition.getName(),
                                                              partition.getAbstractType().eClass().getName(), partition.getAbstractType().getName() });
            }
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
