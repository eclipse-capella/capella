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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.selector_Type;

import ms.configuration.services.cs.CalculatedConfiguration;
import ms.configuration.services.cs.CalculatedConfiguration.ConfList;
import ms.configuration.services.cs.CalculatedConfiguration.LightConfiguration;

public class MDCHK_MSVAL_ComparisonIncl extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    ArrayList<IStatus> failureMessageArgument1 = new ArrayList<>();
    if (eObj instanceof Comparison) {
      Comparison comparison = (Comparison)eObj;
      // check that the comparison elements have references to two configuration elements, or one config and one situation
      int ComparisonType = -1; // -1 : comparison element is not correct. 1 : comparison  between two config. 2 : comparison  between one config and one situation
      if ((comparison.getConfiguration1().size() == 1) && (comparison.getConfiguration2().size() == 1)  && (comparison.getSituation().size() == 0) )
        ComparisonType = 1;
      if ((comparison.getConfiguration1().size() == 1) && (comparison.getConfiguration2().size() == 0) && (comparison.getSituation().size() == 1))
        ComparisonType = 2;
      if(ComparisonType == 1) // comparison between two CSConfigurations
      {
        CSConfiguration config1 = comparison.getConfiguration1().get(0);
        CSConfiguration config2 = comparison.getConfiguration2().get(0);
        config1.getSelector();
        config2.getSelector();
        if (config1.getSelector().equals(selector_Type.INCLUSION) && config2.getSelector().equals(selector_Type.INCLUSION) && config1.eContainer() instanceof LogicalComponent && config2.eContainer() instanceof LogicalComponent){
          for(AbstractFunction function : config1.getFunctions()){
            if(!config2.getFunctions().contains(function)){
              failureMessageArgument1.add(ctx.createFailureStatus("Function", function.getName(), config2.getName(), comparison.getName()));
            }
          }
          for(AbstractFunction function : config2.getFunctions()){
            if(!config1.getFunctions().contains(function)){
              failureMessageArgument1.add(ctx.createFailureStatus("Function", function.getName(), config1.getName(), comparison.getName()));
            }
          }
          for(FunctionalChain functionalChain : config1.getFunctionalChains()){
            if(!config2.getFunctionalChains().contains(functionalChain)){
              failureMessageArgument1.add(ctx.createFailureStatus("Functional Chain", functionalChain.getName(), config2.getName(), comparison.getName()));
            }
          }
          for(FunctionalChain functionalChain : config2.getFunctionalChains()){
            if(!config1.getFunctionalChains().contains(functionalChain)){
              failureMessageArgument1.add(ctx.createFailureStatus("Functional Chain", functionalChain.getName(), config1.getName(), comparison.getName()));
            }
          }
          for(Port port : config1.getPorts()){
            if(!config2.getPorts().contains(port)){
              failureMessageArgument1.add(ctx.createFailureStatus("Port", port.getName(), config2.getName(), comparison.getName()));
            }
          }
          for(Port port : config2.getPorts()){
            if(!config1.getPorts().contains(port)){
              failureMessageArgument1.add(ctx.createFailureStatus("Port", port.getName(), config1.getName(), comparison.getName()));
            }
          }
          for(Component component : config1.getComponents()){
            if(!config2.getComponents().contains(component)){
              failureMessageArgument1.add(ctx.createFailureStatus("Component", component.getName(), config2.getName(), comparison.getName()));
            }
          }
          for(Component component : config2.getComponents()){
            if(!config1.getComponents().contains(component)){
              failureMessageArgument1.add(ctx.createFailureStatus("Component", component.getName(), config1.getName(), comparison.getName()));
            }
          }
          for(CSConfiguration csConf : config1.getChildConfigurations()){
            if(!config2.getChildConfigurations().contains(csConf)){
              failureMessageArgument1.add(ctx.createFailureStatus("Child Configuration", csConf.getName(), config2.getName(), comparison.getName()));
            }
          }
          for(CSConfiguration csConf : config2.getChildConfigurations()){
            if(!config1.getChildConfigurations().contains(csConf)){
              failureMessageArgument1.add(ctx.createFailureStatus("Child Configuration", csConf.getName(), config1.getName(), comparison.getName()));
            }
          }
        }
      }
      if(ComparisonType == 2)
      {
        CSConfiguration a1 = comparison.getConfiguration1().get(0);
        Situation s = comparison.getSituation().get(0);
        Result result = null; // the comparison will be made on the result element associated to the situation (if this result is found)
        Boolean result_found = false;
        // try to find a result element that refers to the situation used for comparison
        BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(s);
        TreeIterator<EObject> it  = archi.eAllContents();
        while(it.hasNext())
        {
          EObject eo = it.next();
          if(eo instanceof Result)
          {
            if (((Result)eo).getSituation().get(0).equals(s)){
              result = (Result)eo;
              result_found = true;
            }
          }
        }
        if(result_found)
        {
          CalculatedConfiguration cC = new CalculatedConfiguration(result);
          ConfList conflist = cC.Calculate();
          comparison.getConfiguration1().get(0).getSelector();
          if(comparison.getConfiguration1().get(0).getSelector().equals(selector_Type.INCLUSION)){
            for(AbstractFunction jObj : a1.getFunctions()){
              for(LightConfiguration lc : conflist.getLightConfigurationList()){
                if(!lc.getExclFct().contains(jObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Function", jObj.getName(), lc.getName(), comparison.getName()));
                }
              }
            }
            for(LightConfiguration lc : conflist.getLightConfigurationList()){
              for(AbstractFunction kObj : lc.getExclFct()){
                if(!a1.getFunctions().contains(kObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Function", kObj.getName(), a1.getName(), comparison.getName()));
                }
              }
            }
            for(Component jObj : a1.getComponents()){
              for(LightConfiguration lc : conflist.getLightConfigurationList()){
                if(!lc.getExclComponents().contains(jObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Component", jObj.getName(), lc.getName(), comparison.getName()));
                }
              }
            }
            for(LightConfiguration lc : conflist.getLightConfigurationList()){
              for(Component kObj : lc.getComponents()){
                if(!a1.getComponents().contains(kObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Component", kObj.getName(), a1.getName(), comparison.getName()));
                }
              }
            }
            for(FunctionalChain jObj : a1.getFunctionalChains()){
              for(LightConfiguration lc : conflist.getLightConfigurationList()){
                if(!lc.getExclFctChain().contains(jObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Functional Chain", jObj.getName(), lc.getName(), comparison.getName()));
                }
              }
            }
            for(LightConfiguration lc : conflist.getLightConfigurationList()){
              for(FunctionalChain kObj : lc.getExclFctChain()){
                if(!a1.getFunctionalChains().contains(kObj)){
                  failureMessageArgument1.add(ctx.createFailureStatus("Functional Chain", kObj.getName(), a1.getName(), comparison.getName()));
                }
              }
            }
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
