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
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;

import ms.configuration.services.cs.CalculatedConfiguration;
import ms.configuration.services.cs.CalculatedConfiguration.ConfList;
import ms.configuration.services.cs.CalculatedConfiguration.LightConfiguration;
import ms.configuration.services.cs.ElementConf;

public class MDCHK_Functional_Conflits extends AbstractModelConstraint{
  
  protected List<CSConfiguration> configListFiltered = new ArrayList<CSConfiguration>();
  protected List<CSConfiguration> configList = new ArrayList<CSConfiguration>();
  ConfList conflist = null;
  

  @Override
  public IStatus validate(IValidationContext ctx) {
    // TODO Auto-generated method stub
    this.configList.clear();
    this.configListFiltered.clear();
    List<ElementConf> functionListIncluded = new ArrayList<ElementConf>();
    List<ElementConf> functionListExcluded = new ArrayList<ElementConf>();
    List<ElementConf> componentListIncluded = new ArrayList<ElementConf>();
    List<ElementConf> componentListExcluded = new ArrayList<ElementConf>();
    Collection<IStatus> objectsIrregularList = new ArrayList<IStatus>();
    Collection<IStatus> objectsIrregularString = new ArrayList<IStatus>();
    Situation situCompare = null;
    
    if (ctx.getTarget() instanceof Component) {
      Component logComp = (Component) ctx.getTarget();
      for(EObject toto: logComp.eContents()){
        ArrayList<String> irregFault = new ArrayList<String>();
        if(toto instanceof Result){
          irregFault.clear();
          configListFiltered.clear();
          objectsIrregularList.clear();
          Result result = (Result)toto;
          situCompare = result.getSituation().get(0);
          
          
          for (EObject iObj : situCompare.eContents()){
            if(iObj instanceof BooleanOperation){
              BooleanOperation boolObj = (BooleanOperation)iObj;
              EObject tObj = boolObj.eContainer();
              EObject vObj = tObj.eContainer();
              for(EObject jObj : vObj.eContents()){
                if(jObj instanceof CSConfiguration){
                  if(!configList.contains((CSConfiguration) jObj)){
                    configList.add((CSConfiguration) jObj);
                  }
                }
                else if(jObj instanceof Component){
                  for(EObject childComponent : ((Component)jObj).eContents()){
                    if(childComponent instanceof CSConfiguration){
                      if(!configList.contains((CSConfiguration) childComponent)){
                        configList.add((CSConfiguration) childComponent);
                      }
                    }
                  }
                }
              }
              for(EObject jObj : boolObj.eContents()){
                if (jObj instanceof InStateExpression){
                  for(CSConfiguration configObject:  configList){
                    for( AbstractState pObj:configObject.getSupportedModes()){
                      if(((InStateExpression)jObj).getState() instanceof Mode){
                        Mode modeState = (Mode)((InStateExpression)jObj).getState();
                        if(modeState.equals(pObj)){
                          if(!configListFiltered.contains(configObject)){
                            configListFiltered.add(configObject);
                          }
                        }
                      }
                      else if(((InStateExpression)jObj).getState() instanceof State){
                        State modeState = (State)((InStateExpression)jObj).getState();
                        if(modeState.equals(pObj)){
                          if(!configListFiltered.contains(configObject)){
                            configListFiltered.add(configObject);
                          }
                        }
                      } 
                    }
                  }
                }
              }
            }   
            for(CSConfiguration configObject:  configListFiltered){
              for (AbstractFunction jObj: configObject.getFunctions()){
                if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                  ElementConf elemConf = new ElementConf(jObj,configObject);
                  Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                  EList<ComponentFunctionalAllocation> test = jObj.getComponentFunctionalAllocations();
                  //listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                  
                 for (ComponentFunctionalAllocation comp: test){
                   ElementConf elemConfDD = new ElementConf(comp.getSourceElement(),configObject);
                   listResult = contentComponentOfFunction(elemConfDD.getElement(),listResult, configObject);
                   for (ElementConf elem : listResult){
                     if (!isContain(componentListIncluded,elem) && elem.getElement().eContainer() instanceof Component){
                     componentListIncluded.add(elem);
                     }
                   }
                   if (!isContain(componentListIncluded,elemConfDD)){
                    componentListIncluded.add(elemConfDD);
                   }
                  }
                 if (!isContain(functionListIncluded,elemConf)){
                  functionListIncluded.add(elemConf);
                 }
                }
                else{
                  ElementConf elemConf = new ElementConf(jObj,configObject);
                  EList<ComponentFunctionalAllocation> test = jObj.getComponentFunctionalAllocations();
                  if (!isContain(functionListExcluded,elemConf)){
                  functionListExcluded.add(elemConf);
                  }
                }
              }
              for (FunctionalChain jObj: configObject.getFunctionalChains()){
                if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                  for(AbstractFunction tObj: jObj.getInvolvedFunctions()){
                    ElementConf elemConf = new ElementConf(tObj,configObject);
                    EList<ComponentFunctionalAllocation> test = tObj.getComponentFunctionalAllocations();
                    //listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                    for (ComponentFunctionalAllocation comp: test){
                      if (comp.getSourceElement().eContainer() instanceof Component){
                        ElementConf elemConfDD = new ElementConf(comp.getSourceElement(),configObject);
                        componentListIncluded.add(elemConfDD);
                      }
                     }
                    if (!isContain(functionListIncluded,elemConf)){
                      functionListIncluded.add(elemConf);
                    }
                  }
                }
                else{
                  for(AbstractFunction tObj: jObj.getInvolvedFunctions()){
                    ElementConf elemConf = new ElementConf(jObj,configObject);
                    EList<ComponentFunctionalAllocation> test = tObj.getComponentFunctionalAllocations();
                    if (!isContain(functionListExcluded,elemConf)){
                      functionListExcluded.add(elemConf);
                    }
                  }
                }
              }
              
              for (Component jObj: configObject.getComponents()){
                if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                  ElementConf elemConf = new ElementConf(jObj,configObject);
                  Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                  listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                  for (ElementConf elem : listResult){
                    if (!isContain(componentListIncluded,elem) && elem.getElement().eContainer() instanceof Component){
                    componentListIncluded.add(elem);
                    }
                  }
                  if (!isContain(componentListIncluded,elemConf)){
                    componentListIncluded.add(elemConf);
                  }
                }
                else{
                  ElementConf elemConf = new ElementConf(jObj,configObject);
                  Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                  listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                  for (ElementConf elem : listResult){
                    if (!isContain(componentListExcluded,elem) && elem.getElement().eContainer() instanceof Component){
                      componentListExcluded.add(elem);
                    }
                  }
                  if (!isContain(componentListExcluded,elemConf)){
                    componentListExcluded.add(elemConf);
                  }
                }
              }
            }
          }
          
        }
        else if(toto instanceof Comparison){
          irregFault.clear();
          configListFiltered.clear();
          //objectsIrregularList.clear();
          Comparison compareTo = (Comparison)toto; 
          if (compareTo.getConfiguration1().size()>0){
            CSConfiguration config1 = compareTo.getConfiguration1().get(0);
            if(!configListFiltered.contains(config1)){
              configListFiltered.add(config1);
            }
           if (compareTo.getConfiguration2().size() >0){
              CSConfiguration config2 = compareTo.getConfiguration2().get(0);
              if(!configListFiltered.contains(config2)){
                configListFiltered.add(config2);
              }
            }
           
           if (compareTo.getSituation().size() >0){
             for(EObject iObj: logComp.eContents()){
               if(iObj instanceof Result){
                 Result result = (Result)iObj;
                 for (Situation situ : result.getSituation()){
                   if(compareTo.getSituation().contains(situ)){
                     CalculatedConfiguration cC = new CalculatedConfiguration(result); // JVS
                     conflist = cC.Calculate(); // JVS
                     irregFault = cC.getErrorListText();
                 }
               }
               for(String errorToShow : irregFault){
                 objectsIrregularList.add(ctx.createFailureStatus(errorToShow));
               }
             }
           }
           
           }
           
           
           
           for(CSConfiguration configObject:  configListFiltered){
             for (AbstractFunction jObj: configObject.getFunctions()){
               if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                 ElementConf elemConf = new ElementConf(jObj,configObject);
                 Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                 EList<ComponentFunctionalAllocation> test = jObj.getComponentFunctionalAllocations();
                 //listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                 
                for (ComponentFunctionalAllocation comp: test){
                  ElementConf elemConfDD = new ElementConf(comp.getSourceElement(),configObject);
                  listResult = contentComponentOfFunction(elemConfDD.getElement(),listResult, configObject);
                  for (ElementConf elem : listResult){
                    if (!isContain(componentListIncluded,elem) && elem.getElement().eContainer() instanceof Component){
                    componentListIncluded.add(elem);
                    }
                  }
                   componentListIncluded.add(elemConfDD);
                 }
                if (!isContain(functionListIncluded,elemConf)){
                 functionListIncluded.add(elemConf);
                }
               }
               else{
                 ElementConf elemConf = new ElementConf(jObj,configObject);
                 Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                 EList<ComponentFunctionalAllocation> test = jObj.getComponentFunctionalAllocations();
                 if (!isContain(functionListExcluded,elemConf)){
                   functionListExcluded.add(elemConf);
                 }
               }
             }
             for (FunctionalChain jObj: configObject.getFunctionalChains()){
               if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                 for(AbstractFunction tObj: jObj.getInvolvedFunctions()){
                   ElementConf elemConf = new ElementConf(tObj,configObject);
                   EList<ComponentFunctionalAllocation> test = tObj.getComponentFunctionalAllocations();
                   //listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                   for (ComponentFunctionalAllocation comp: test){
                     if (comp.getSourceElement().eContainer() instanceof Component){
                       ElementConf elemConfDD = new ElementConf(comp.getSourceElement(),configObject);
                       componentListIncluded.add(elemConfDD);
                     }
                    }
                   if (!isContain(functionListIncluded,elemConf)){
                     functionListIncluded.add(elemConf);
                   }
                 }
               }
               else{
                 for(AbstractFunction tObj: jObj.getInvolvedFunctions()){
                   ElementConf elemConf = new ElementConf(jObj,configObject);
                   EList<ComponentFunctionalAllocation> test = tObj.getComponentFunctionalAllocations();
                   if (!isContain(functionListExcluded,elemConf)){
                     functionListExcluded.add(elemConf);
                   }
                 }
               }
             }
             
             for (Component jObj: configObject.getComponents()){
               if(configObject.getSelector().equals(configObject.getSelector().INCLUSION)){
                 ElementConf elemConf = new ElementConf(jObj,configObject);
                 Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                 listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                 for (ElementConf elem : listResult){
                   if (!isContain(componentListIncluded,elem) && elem.getElement().eContainer() instanceof Component){
                   componentListIncluded.add(elem);
                   }
                 }
                 if (!isContain(componentListIncluded,elemConf)){
                   componentListIncluded.add(elemConf);
                 }
               }
               else{
                 ElementConf elemConf = new ElementConf(jObj,configObject);
                 Collection<ElementConf> listResult= new ArrayList<ElementConf>();
                 listResult = contentComponentOfFunction(elemConf.getElement(),listResult, configObject);
                 for (ElementConf elem : listResult){
                   if (!isContain(componentListExcluded,elem) && elem.getElement().eContainer() instanceof Component){
                     componentListExcluded.add(elem);
                   }
                 }
                 if (!isContain(componentListExcluded,elemConf)){
                   componentListExcluded.add(elemConf);
                 }
               }
             }
           }
          }
          //ArrayList<String> irregFault = cC.getErrorListText();
          List<LightConfiguration> lightListConfig = conflist.getLightConfigurationList();
          for (LightConfiguration lightconfig : lightListConfig){
            
            List<AbstractFunction> fctListIncluded_calculated = new ArrayList<AbstractFunction>();
            List<AbstractFunction> fctListExcluded_calculated = new ArrayList<AbstractFunction>();
            List<Component> cptListIncluded_calculated = new ArrayList<Component>();
            List<Component> cptListExcluded_calculated = new ArrayList<Component>();
            
            for(AbstractFunction logFct : lightconfig.getLogFct()){
              if (!fctListIncluded_calculated.contains(logFct)){
                fctListIncluded_calculated.add(logFct);
              }
            }
            
            for(Component logcomp : lightconfig.getComponents()){
              if (!cptListIncluded_calculated.add(logcomp)){
                cptListIncluded_calculated.add(logcomp);
              }
            }
            
            for(FunctionalChain fctChain : lightconfig.getFctChain()){
              for(AbstractFunction logFct : fctChain.getInvolvedFunctions()){
                EList<ComponentFunctionalAllocation> test = logFct.getComponentFunctionalAllocations();
                fctListIncluded_calculated.add(logFct);
                for (ComponentFunctionalAllocation comp: test){
                  if (comp.getSourceElement().eContainer() instanceof Component){
                    if(comp.getSourceElement() instanceof Component){
                      cptListIncluded_calculated.add((Component)comp.getSourceElement());
                    }
                  }
                 }
              }
            }
            
            for(AbstractFunction logFct : lightconfig.getExclLogFct()){
              if (!fctListExcluded_calculated.add(logFct)){
                fctListExcluded_calculated.add(logFct);
              }
            }
            
            for(Component logcomp : lightconfig.getExclComponents()){
              if (!cptListExcluded_calculated.add(logcomp)){
                cptListExcluded_calculated.add(logcomp);
              }
            }
            
            for(FunctionalChain fctChain : lightconfig.getExclFctChain()){
              for(AbstractFunction logFct : fctChain.getInvolvedFunctions()){
                EList<ComponentFunctionalAllocation> test = logFct.getComponentFunctionalAllocations();
                fctListExcluded_calculated.add(logFct);
                for (ComponentFunctionalAllocation comp: test){
                  if (comp.getSourceElement().eContainer() instanceof Component){
                    if(comp.getSourceElement() instanceof Component){
                      cptListExcluded_calculated.add((Component)comp.getSourceElement());
                    }
                  }
                 }
              }
            }
            
            for (ElementConf elem: functionListIncluded){
              if(fctListExcluded_calculated.contains((AbstractFunction)elem.getElement())){
                objectsIrregularList.add(ctx.createFailureStatus(((AbstractFunction)elem.getElement()).getName(),elem.getConfiguration().getName(),"calculated configuration"));
              }
            }
            
            for (ElementConf elem: functionListExcluded){
              if(fctListIncluded_calculated.contains((AbstractFunction)elem.getElement())){
                objectsIrregularList.add(ctx.createFailureStatus(((AbstractFunction)elem.getElement()).getName(),elem.getConfiguration().getName(),"calculated configuration"));
              }
             }
            
            for (ElementConf elem: componentListIncluded){
              if(cptListExcluded_calculated.contains((Component)elem.getElement())){
                objectsIrregularList.add(ctx.createFailureStatus(((Component)elem.getElement()).getName(),elem.getConfiguration().getName(),"calculated configuration"));
              }
            }
            
            for (ElementConf elem: componentListExcluded){
              if(cptListIncluded_calculated.contains((Component)elem.getElement())){
                objectsIrregularList.add(ctx.createFailureStatus(((Component)elem.getElement()).getName(),elem.getConfiguration().getName(),"calculated configuration"));
              }
            }
          }
        } 
        for(String errorToShow : irregFault){
          objectsIrregularList.add(ctx.createFailureStatus(errorToShow));
        }
      }
      //if (compareTo.getConfiguration2().size() >0){
      
      for(ElementConf fct : functionListIncluded){
        for (ElementConf fct2 : functionListExcluded){
          if(fct.getElement().equals(fct2.getElement())){
            objectsIrregularList.add(ctx.createFailureStatus(((AbstractFunction)fct.getElement()).getName(),fct.getConfiguration().getName(),fct2.getConfiguration().getName()));
          }
        }
      }
      
      for(ElementConf jObj : componentListIncluded){
        for (ElementConf jObj2 : componentListExcluded){
          if(jObj.getElement().equals(jObj2.getElement())&& !(jObj.getConfiguration().equals(jObj2.getConfiguration()))){
            objectsIrregularList.add(ctx.createFailureStatus(((Component)jObj.getElement()).getName(),jObj.getConfiguration().getName(),jObj2.getConfiguration().getName()));
          }
        }
      }
      if(!objectsIrregularList.isEmpty()){
        return ConstraintStatus.createMultiStatus(ctx, objectsIrregularList);
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
  
  private boolean isContain(List<ElementConf> componentListIncluded, ElementConf elem) {
    // TODO Auto-generated method stub
    for(ElementConf eltConf : componentListIncluded){
      if (eltConf.getElement().equals(elem.getElement())){
        return true;
      }
    }
    return false;
  }

  public Collection<ElementConf> contentComponentOfFunction(EObject fct, Collection<ElementConf> resultList, CSConfiguration config){

    
    if (fct.eContainer() instanceof SystemEngineering){
      return resultList;
    }
    else if (fct.eContainer() instanceof Component){
      ElementConf newElem = new ElementConf((Component)fct.eContainer(), config);
      resultList.add(newElem);
      contentComponentOfFunction(fct.eContainer(), resultList, config);
    }
    else{
      contentComponentOfFunction(fct.eContainer(), resultList, config);
    }
    
    return resultList;
  }

}
