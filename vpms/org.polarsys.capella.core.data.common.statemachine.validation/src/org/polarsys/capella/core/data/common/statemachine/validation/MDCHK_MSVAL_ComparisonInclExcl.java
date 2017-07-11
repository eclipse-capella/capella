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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.selector_Type;

import ms.configuration.services.cs.CalculatedConfiguration;
import ms.configuration.services.cs.CalculatedConfiguration.ConfList;
import ms.configuration.services.cs.CalculatedConfiguration.LightConfiguration;
// 62
public class MDCHK_MSVAL_ComparisonInclExcl extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  // check that no element (function, component, port, funct chain, child conf) are not
  // included and exluded by the compared configurations
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    Collection<IStatus> failureStatusList = new ArrayList<IStatus>();
    ArrayList<String> failureMessageArgument1 = new ArrayList<String>(); // type of the object included & excluded
    ArrayList<String> failureMessageArgument2 = new ArrayList<String>(); // name of the object included & excluded
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
        CSConfiguration a = comparison.getConfiguration1().get(0);
        CSConfiguration b = comparison.getConfiguration2().get(0);
        if (!a.getSelector().equals(b.getSelector())) // if both config the same mode (inclusion,exclusion), the rule is not applicable
        {
          // elements listed in both configurations :
          for(AbstractFunction jObj: a.getFunctions())
            for(AbstractFunction kObj: b.getFunctions())
              if(jObj.equals(kObj)){
                failureMessageArgument1.add("Function");
                failureMessageArgument2.add(kObj.getName());
              }
          for(FunctionalChain jObj: a.getFunctionalChains())
            for(FunctionalChain kObj: b.getFunctionalChains())
              if(jObj.equals(kObj)){
                failureMessageArgument1.add("Functional Chain");
                failureMessageArgument2.add(kObj.getName());
              }
          for(Component jObj: a.getComponents())
            for(Component kObj: b.getComponents())
              if(jObj.equals(kObj)){
                failureMessageArgument1.add("Component");
                failureMessageArgument2.add(kObj.getName());
              }
          for(Port jObj: a.getPorts())
            for(Port kObj: b.getPorts())
              if(jObj.equals(kObj)){
                failureMessageArgument1.add("Port");
                failureMessageArgument2.add(kObj.getName());
              }
          for(CSConfiguration jObj: a.getChildConfigurations())
            for(CSConfiguration kObj: b.getChildConfigurations())
              if(jObj.equals(kObj)){
                failureMessageArgument1.add("Child Configuration");
                failureMessageArgument2.add(kObj.getName());
              }
        }
      }
      if(ComparisonType == 2) // comparison betwen a CSConfigurations and a calculatedConfiguration
      {
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
          CalculatedConfiguration cC = new CalculatedConfiguration(result); // JVS
          ConfList conflist = cC.Calculate(); // JVS
          /*
        NamedElement ne = (NamedElement) o;
        FailureMessageArgument1.add("eObject");
        FailureMessageArgument2.add(ne.getName());
           */
          // elements listed in both configurations :
          CSConfiguration a = comparison.getConfiguration1().get(0);
          boolean a_is_exclusive = false;
          comparison.getConfiguration1().get(0).getSelector();
          if(comparison.getConfiguration1().get(0).getSelector().equals(selector_Type.EXCLUSION))
            a_is_exclusive = true;
          // compare the configuration 1 with the calculated configuration (the list of 'lightconfigurations' that belongs to confList)
          // included and excluded functions
          if(!a_is_exclusive){ // if config a is inclusive, search for excluded elements in calculated configurations
            for(AbstractFunction jObj: a.getFunctions())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(AbstractFunction kObj : lc.getExclFct())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Function");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          else {  // if config a is exclusive, search for included elements in calculated configurations
            for(AbstractFunction jObj: a.getFunctions())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(AbstractFunction kObj : lc.getExclFct())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Function");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          // included and excluded components
          if(!a_is_exclusive){ // if config a is inclusive, search for excluded elements in calculated configurations
            for(Component jObj: a.getComponents())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(Component kObj : lc.getExclComponents())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Component");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          else {  // if config a is exclusive, search for included elements in calculated configurations
            for(Component jObj: a.getComponents())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(Component kObj : lc.getComponents())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Component");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          // included and excluded ports  : TO BE IMPLEMENTED (PORTS ARE NOT YET SUPPORTED IN CALCULATED CONF)
          /*
        if(!a_is_exclusive){ // if config a is inclusive, search for excluded elements in calculated configurations
          for(AbstractFunction jObj: a.getFunctions())
            for(LightConfiguration lc:conflist.getLightConfigurationList())
              for(AbstractFunction kObj : lc.())
              if(jObj.equals(kObj)){
                FailureMessageArgument1.add("Function");
                FailureMessageArgument2.add(kObj.getName());
              }
        }
        else {  // if config a is exclusive, search for included elements in calculated configurations
          for(AbstractFunction jObj: a.getFunctions())
            for(LightConfiguration lc:conflist.getLightConfigurationList())
              for(AbstractFunction kObj : lc.getFct())
              if(jObj.equals(kObj)){
                FailureMessageArgument1.add("Function");
                FailureMessageArgument2.add(kObj.getName());
              }
        }
           */
          // included and excluded functionalchains
          if(!a_is_exclusive){ // if config a is inclusive, search for excluded elements in calculated configurations
            for(FunctionalChain jObj: a.getFunctionalChains())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(FunctionalChain kObj : lc.getExclFctChain())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Functional Chain");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          else {  // if config a is exclusive, search for included elements in calculated configurations
            for(FunctionalChain jObj: a.getFunctionalChains())
              for(LightConfiguration lc:conflist.getLightConfigurationList())
                for(FunctionalChain kObj : lc.getFctChain())
                  if(jObj.equals(kObj)){
                    failureMessageArgument1.add("Functional Chain");
                    failureMessageArgument2.add(kObj.getName());
                  }
          }
          // included and excluded child config     : TO BE IMPLEMENTED
        }else{
          System.out.println("not found result = =");
        }
      } // comparison type 2 (Configuration vs. CalculatedConfiguration)
      if(failureMessageArgument1.size() > 0)
      {
        for(int i = 0; i < failureMessageArgument1.size(); i++)
          failureStatusList.add(ctx.createFailureStatus(comparison.getName(),failureMessageArgument1.get(i),failureMessageArgument2.get(i)));
        return ConstraintStatus.createMultiStatus(ctx, failureStatusList);
      }else
        return ctx.createSuccessStatus();
    }
    return null;
  }
}
