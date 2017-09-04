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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.selector_Type;



public class MDCHK_MSVAL_ValidateBetweenSystemAndLogical extends AbstractModelConstraint {

  ArrayList<IStatus> failureMessageArgument1 = new ArrayList<>();

  @Override
  public IStatus validate(IValidationContext ctx) {
    failureMessageArgument1.clear();
    EObject eObj = ctx.getTarget();
    Comparison comparison = (Comparison) eObj;
    CSConfiguration config1 = comparison.getConfiguration1().get(0);
    CSConfiguration config2 = comparison.getConfiguration2().get(0);
    CSConfiguration systemConfig = config1.eContainer() instanceof System ? config1 : config2;
    CSConfiguration logicalConfig = config2.eContainer() instanceof LogicalComponent ? config2 : config1;
    ArrayList<FunctionRealization> systemFunctionList = new ArrayList<>();
    for(AbstractFunction fct : logicalConfig.getFunctions()){
      if(!fct.getOwnedFunctionRealizations().isEmpty()){
        systemFunctionList.addAll(fct.getOwnedFunctionRealizations());
      }
    }
    systemFunctionList.get(0).getTargetElement().getId();
    if (systemConfig.getSelector().equals(selector_Type.INCLUSION) && logicalConfig.getSelector().equals(selector_Type.EXCLUSION) && logicalConfig.eContainer() instanceof LogicalComponent && systemConfig.eContainer() instanceof System){
      for(FunctionRealization function : systemFunctionList){
        for(AbstractFunction fct : systemConfig.getFunctions()){
          if(function.getTargetElement().getId().equals(fct.getId())){
            failureMessageArgument1.add(ctx.createFailureStatus(systemConfig.getName(), function.getTargetElement().getLabel(), logicalConfig.getName(), function.getTargetElement().getLabel()));
          }
        }
      }
    }
    if(failureMessageArgument1.size() > 0){
      return ConstraintStatus.createMultiStatus(ctx, failureMessageArgument1);
    }
    else {
      return ctx.createSuccessStatus();
    }
  }

}