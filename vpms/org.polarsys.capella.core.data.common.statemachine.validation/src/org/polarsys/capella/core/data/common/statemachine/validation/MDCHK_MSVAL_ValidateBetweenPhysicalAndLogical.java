package org.polarsys.capella.core.data.common.statemachine.validation;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.selector_Type;

public class MDCHK_MSVAL_ValidateBetweenPhysicalAndLogical extends AbstractModelConstraint{


  ArrayList<IStatus> failureMessageArgument1 = new ArrayList<>();

  @Override
  public IStatus validate(IValidationContext ctx){
    failureMessageArgument1.clear();
    EObject eObj = ctx.getTarget();
    Comparison comparison = (Comparison) eObj;
    CSConfiguration config1 = comparison.getConfiguration1().get(0);
    CSConfiguration config2 = comparison.getConfiguration2().get(0);
    CSConfiguration physicalConfig = config1.eContainer() instanceof System ? config1 : config2;
    CSConfiguration logicalConfig = config2.eContainer() instanceof LogicalComponent ? config2 : config1;
    ArrayList<FunctionRealization> logicalFunctionList = new ArrayList<>();
    ArrayList<LogicalComponentRealization> logicalComponentList = new ArrayList<>();
    for(AbstractFunction fct : physicalConfig.getFunctions()){
      if(!fct.getOwnedFunctionRealizations().isEmpty()){
        logicalFunctionList.addAll(fct.getOwnedFunctionRealizations());
      }
    }
    for(Component component : physicalConfig.getComponents()){
      PhysicalComponent myComp = (PhysicalComponent)component;
      if(!myComp.getOwnedLogicalComponentRealizations().isEmpty()){
        logicalComponentList.addAll(myComp.getOwnedLogicalComponentRealizations());
      }
    }
    if (physicalConfig.getSelector().equals(selector_Type.INCLUSION) && logicalConfig.getSelector().equals(selector_Type.EXCLUSION) && logicalConfig.eContainer() instanceof LogicalComponent && physicalConfig.eContainer() instanceof PhysicalComponent){
      for(FunctionRealization function : logicalFunctionList){
        for(AbstractFunction fct : logicalConfig.getFunctions()){
          if(function.getTargetElement().getId().equals(fct.getId())){
            failureMessageArgument1.add(ctx.createFailureStatus(logicalConfig.getName(), function.getTargetElement().getLabel(), physicalConfig.getName(), function.getTargetElement().getLabel(), "function"));
          }
        }
      }
      for(LogicalComponentRealization lc : logicalComponentList){
        for(Component cpt : logicalConfig.getComponents()){
          if(lc.getTargetElement().getId().equals(cpt.getId())){
            failureMessageArgument1.add(ctx.createFailureStatus(logicalConfig.getName(), lc.getTargetElement().getLabel(), physicalConfig.getName(), lc.getTargetElement().getLabel(), "component"));
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
