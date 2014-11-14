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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that interfaces requires by standard port is used by its owner Component.
 */
public class MDCHK_ComponentPort_requiredItf_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) eObj;

        PartitionableElement block = (PartitionableElement) port.eContainer();
        if (block instanceof LogicalComponent) {
          LogicalComponent lcpnt = (LogicalComponent) block;
          
          List<LogicalComponent> subLCs = new ArrayList<LogicalComponent>();
      	
      	if (ComponentExt.isComposite(lcpnt)) {
              EList<Partition> ownedPartitions = lcpnt.getOwnedPartitions();
              Iterator<Partition> iterator = ownedPartitions.iterator();
              while (iterator.hasNext()) {
                Partition next = iterator.next();
                if (next instanceof Part) {
                  Part part = (Part) next;
                  AbstractType abstractType = part.getAbstractType();
                  if (abstractType instanceof LogicalComponent) {
                    subLCs.add((LogicalComponent) abstractType);
                  }
                }
              }
              }
      	
          for (Interface itf : port.getRequiredInterfaces()) {
        	  if(!subLCs.isEmpty() && port.getDelegatedComponentPorts().size()==0){
        		  return createFailureStatus(ctx, new Object[] { port.getName(), lcpnt.getName(), itf.getName() });
        	  }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
