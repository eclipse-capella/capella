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
package org.polarsys.capella.core.model.utils;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.util.CsSwitch;
import org.polarsys.capella.core.data.ctx.util.CtxSwitch;
import org.polarsys.capella.core.data.epbs.util.EpbsSwitch;
import org.polarsys.capella.core.data.fa.util.FaSwitch;
import org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch;
import org.polarsys.capella.core.data.information.util.InformationSwitch;
import org.polarsys.capella.core.data.interaction.util.InteractionSwitch;
import org.polarsys.capella.core.data.la.util.LaSwitch;
import org.polarsys.capella.core.data.capellacommon.util.CapellacommonSwitch;
import org.polarsys.capella.core.data.capellacore.util.CapellacoreSwitch;
import org.polarsys.capella.core.data.pa.util.PaSwitch;
import org.polarsys.capella.common.data.modellingcore.util.ModellingcoreSwitch;

/** 
 * A dispatcher for EMF-generated Switch Classes for the capella 
 * metamodel. Such a dispatcher is useful because the capella model is 
 * distributed across several EPackages, and EMF generates a 
 * separate switch class for each EPackage. This dispatcher
 * calls out to each EMF generated switch in the correct
 * order (most specific to most general). If a generated
 * switch does not return a result, we continue with the
 * next switch in the package hierarchy.
 * 
 * @param <T> the switch type parameter
 * // FIXME: did I get all packages and the order of invocation (most specific to least specific) correct?
 */
public class CapellaSwitch<T> {
  
  T defaultResult;
  
  private ModellingcoreSwitch<? extends T> modellingcore;
  private CapellacoreSwitch<? extends T> capellacore;
  private CapellacommonSwitch<? extends T> capellacommon;
  private InformationSwitch<? extends T> information;
  private LaSwitch<? extends T> la;
  private FaSwitch<? extends T> fa;
  private CsSwitch<? extends T> cs;
  private CtxSwitch<? extends T> ctx;
  private EpbsSwitch<? extends T> epbs;
  private InteractionSwitch<? extends T> interaction;
  private PaSwitch<? extends T> pa;
  private DatavalueSwitch<? extends T> datavalue;
  
  public CapellaSwitch(){
    this(null);
  }
  
  public CapellaSwitch(T defaultResult_p){
    defaultResult = defaultResult_p;
  }
  
  public T doSwitch(EObject e){
    T result = null;
    if (result == null && datavalue != null){
      result = datavalue.doSwitch(e);
    }
    if (result == null && pa != null){
      result = pa.doSwitch(e);
    }
    if (result == null && interaction != null){
      result = interaction.doSwitch(e);
    }
    if (result == null && epbs != null){
      result = epbs.doSwitch(e);
    }
    if (result == null && ctx != null){
      result = ctx.doSwitch(e);
    }
    if (result == null && cs != null){
      result = cs.doSwitch(e);
    }
    if (result == null &&  fa != null){
      result = fa.doSwitch(e);
    }
    if (result == null && la != null){
      result = la.doSwitch(e);
    }
    if (result == null && information != null){
      result = information.doSwitch(e);
    }
    if (result == null && capellacommon != null){
      result = capellacommon.doSwitch(e);
    }
    if (result == null && capellacore != null){
      result = capellacore.doSwitch(e);
    }
    if (result == null && modellingcore != null){
      result = modellingcore.doSwitch(e);
    }
    if (result == null){
      result = defaultCase(e);
    }
    
    return result;
  }
  
  /**
   * A cross package default case. This is called if all
   * package switches returned null for the current EObject.
   * @param e
   */
  protected T defaultCase(EObject e){
    return defaultResult;
  }

  /**
   * @param modellingcore_p the modellingcore to set
   */
  public void setModellingcore(ModellingcoreSwitch<? extends T> modellingcore_p) {
    modellingcore = modellingcore_p;
  }

  /**
   * @param capellacore_p the capellacore to set
   */
  public void setCapellacore(CapellacoreSwitch<? extends T> capellacore_p) {
    capellacore = capellacore_p;
  }

  /**
   * @param capellacommon_p the capellacommon to set
   */
  public void setCapellacommon(CapellacommonSwitch<? extends T> capellacommon_p) {
    capellacommon = capellacommon_p;
  }

  /**
   * @param information_p the information to set
   */
  public void setInformation(InformationSwitch<? extends T> information_p) {
    information = information_p;
  }

  /**
   * @param la_p the la to set
   */
  public void setLa(LaSwitch<? extends T> la_p) {
    la = la_p;
  }

  /**
   * @param fa_p the fa to set
   */
  public void setFa(FaSwitch<? extends T> fa_p) {
    fa = fa_p;
  }

  /**
   * @param cs_p the cs to set
   */
  public void setCs(CsSwitch<? extends T> cs_p) {
    cs = cs_p;
  }

  /**
   * @param ctx_p the ctx to set
   */
  public void setCtx(CtxSwitch<? extends T> ctx_p) {
    ctx = ctx_p;
  }

  /**
   * @param epbs_p the epbs to set
   */
  public void setEpbs(EpbsSwitch<? extends T> epbs_p) {
    epbs = epbs_p;
  }

  /**
   * @param interaction_p the interaction to set
   */
  public void setInteraction(InteractionSwitch<? extends T> interaction_p) {
    interaction = interaction_p;
  }
  
  /**
   * @param pa_p the pa to set
   */
  public void setPa(PaSwitch<? extends T> pa_p) {
    pa = pa_p;
  }
  
  /**
   * @param datavalue_p the datavalue to set
   */
  public void setDatavalue(DatavalueSwitch<? extends T> datavalue_p) {
    datavalue = datavalue_p;
  }
  
}
