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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;


public class MDCHK_RealizationsIrregular extends AbstractModelConstraint
{
   protected List<CSConfiguration> configListFiltered = new ArrayList<CSConfiguration>();
   Collection<IStatus> objectsIrregularList = new ArrayList<IStatus>();
   
  @Override
  public IStatus validate(IValidationContext ctx) {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    objectsIrregularList.clear();
    List<AbstractFunction> functionListIncluded = new ArrayList<AbstractFunction>();
    List<AbstractFunction> functionListExcluded = new ArrayList<AbstractFunction>();
    
    
    if (ctx.getTarget() instanceof Component) {
      Component logComp = (Component) ctx.getTarget();
      for(EObject toto: logComp.eContents()){
        if(toto instanceof Comparison){
          configListFiltered.clear();
          //objectsIrregularList.clear();
          Comparison compareTo = (Comparison)toto; 
          if (compareTo.getConfiguration1().size()>0){
            CSConfiguration config1 = compareTo.getConfiguration1().get(0);
            CSConfiguration config2 = null;
           if (compareTo.getConfiguration2().size() >0){
              config2 = compareTo.getConfiguration2().get(0);
              if(config2.getFunctions().size()>0){
                AbstractFunction fctObj= config2.getFunctions().get(0);
                CSConfiguration config3 = null;
                if(fctObj instanceof PhysicalFunction){
                  config3 = config1;
                  config1 = config2;
                  config2 = config3;
                }
                else if(fctObj instanceof LogicalFunction){
                  if(config1.eContainer() instanceof org.polarsys.capella.core.data.ctx.System){
                    config3 = config1;
                    config1 = config2;
                    config2 = config3;
                  }
                }
               }
            }
           
           for (AbstractFunction jObj: config1.getFunctions()){
             functionListIncluded.clear();
             functionListExcluded.clear();
             if(config1.getSelector().equals(config1.getSelector().INCLUSION)){
               EList<FunctionRealization> fctReal = jObj.getOwnedFunctionRealizations();
               if(fctReal.size()>0){
                 for(FunctionRealization tObj : fctReal){
                   AbstractFunction alocFct = tObj.getAllocatedFunction();
                   functionListIncluded.add(alocFct);
                   
                 }
               }
             }
             else{
               EList<FunctionRealization> fctReal = jObj.getOwnedFunctionRealizations();
               if(fctReal.size()>0){
                 for(FunctionRealization tObj : fctReal){
                   AbstractFunction alocFct = tObj.getAllocatedFunction();
                   functionListExcluded.add(alocFct);
                 }
               }
             }
            }
           
             if(config2!=null){
               EList<AbstractFunction> fctList = config2.getFunctions();
             if(config2.getSelector().equals(config2.getSelector().INCLUSION)){
               for(AbstractFunction alocFct : functionListExcluded){
                 if(fctList.contains(alocFct)){
                   objectsIrregularList.add(ctx.createFailureStatus(alocFct.getName(),config1.getName(),config2.getName(),compareTo.getName()));
                 }
               }
             }
             else {
               for(AbstractFunction alocFct : functionListIncluded){
                 if(fctList.contains(alocFct)){
                   objectsIrregularList.add(ctx.createFailureStatus(alocFct.getName(),config1.getName(),config2.getName(),compareTo.getName()));
                 }
               }
              }
           }
          }
        }
      }
    }
    if(!objectsIrregularList.isEmpty()){
      return ConstraintStatus.createMultiStatus(ctx, objectsIrregularList);
    }
    else return null;
  }

}
