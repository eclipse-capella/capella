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
package org.polarsys.capella.core.data.cs.validation.componentcontext;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This check insures that a Component Context cannot have a part typed by an abstract actor type.
 * 
 */
public class MDCHK_ComponentContext_AbstractActor_Part extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PartitionableElement) {
        //The current object may have partitions
        PartitionableElement partitionableElement = (PartitionableElement) eObj;
        EList<Partition> ownedPartitions = partitionableElement.getOwnedPartitions();
        Iterator<Partition> iterator = ownedPartitions.iterator();
        while (iterator.hasNext()) {
          Partition partition = iterator.next();
          AbstractType abstractType = partition.getAbstractType();
          //Checks whether the current partition is typed by an abstract actor or not
          if (null != abstractType && (abstractType instanceof AbstractActor)) {
            AbstractActor aa = (AbstractActor) abstractType;
            if (aa.isAbstract()) {
              return createFailureStatus(ctx, new Object[] { partitionableElement.getName(), aa.getName() });
            }
          } else if (null != abstractType && (abstractType instanceof OperationalActor)) {
            OperationalActor oa = (OperationalActor) abstractType;
            if (oa.isAbstract()) {
              return createFailureStatus(ctx, new Object[] { partitionableElement.getName(), oa.getName() });
            }
          }
        }
      }
    }
    //No partition typed by an abstract actor
    return ctx.createSuccessStatus();
  }
}
