/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;

public class MDHCK_ComparisonPort extends AbstractModelConstraint {

  Collection<IStatus> objectsIrregularList = new ArrayList<IStatus>();

  @Override
  public IStatus validate(IValidationContext ctx) {
    objectsIrregularList.clear();
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Comparison){
      Comparison comparison = (Comparison) eObj;
      CSConfiguration config1 = comparison.getConfiguration1().get(0);
      CSConfiguration config2 = comparison.getConfiguration2().get(0);
      for (Port port : config1.getPorts()){
        Component cpnt = (Component) port.eContainer();
        if (!config2.getComponents().contains(cpnt)) {
          objectsIrregularList.add(ctx.createFailureStatus(cpnt.getName(), config2.getName(), config1.getName()));
        }
      }
      for (Port port : config2.getPorts()){
        Component cpnt = (Component) port.eContainer();
        if (!config1.getComponents().contains(cpnt)) {
          objectsIrregularList.add(ctx.createFailureStatus(cpnt.getName(), config1.getName(), config2.getName()));
        }
      }
      if (objectsIrregularList.size() > 0){
        return ConstraintStatus.createMultiStatus(ctx, objectsIrregularList);
      } else {
        return ctx.createSuccessStatus();
      }
    }
    return null;
  }

}
