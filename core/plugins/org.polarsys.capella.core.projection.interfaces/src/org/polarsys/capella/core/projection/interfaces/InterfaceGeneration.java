/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.interfaces;

import java.util.Iterator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.ProjectionEngine;
import org.polarsys.capella.core.projection.interfaces.generateInterfaces.InterfaceGenerationResult;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoEngine;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class InterfaceGeneration extends AbstractTransform {

  /*
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_INTERFACE_GENERATION_RULES = "org.polarsys.capella.core.projection.interfaces.generation"; //$NON-NLS-1$

  public static final String KEY_PREFS = "interfaceGenerationPreferences"; //$NON-NLS-1$
  public static final String KEY_DRY_RUN = "interfaceGenerationIsDryRun"; //$NON-NLS-1$
  public static final String KEY_RESULT = "interfaceGenerationResult"; //$NON-NLS-1$

  private final InterfaceGenerationPreferences prefs;
  private final boolean dryRun;
  private final InterfaceGenerationResult result;
  
  /**
   * Create a new instance with the given preferences. If dryRun is true, the transformation
   * will not actually update the model, and also try to be quiet in the log.
   * Clients may afterwards inspect the result of the
   * transformation via {@link #getResult(ITransfo)}. This is used for example during validation.
   * @param preferences
   * @param dryRun
   */
  public InterfaceGeneration(InterfaceGenerationPreferences preferences, boolean dryRun) {
    this.prefs = preferences;
    this.dryRun = dryRun;
    this.result = new InterfaceGenerationResult(prefs);
  }

  public InterfaceGeneration(){
    this(new InterfaceGenerationPreferences(), false);
  }

  public static InterfaceGenerationPreferences getPreferences(ITransfo transfo){
    InterfaceGenerationPreferences prefs = (InterfaceGenerationPreferences) transfo.get(KEY_PREFS);
    if (prefs == null) {
      prefs = new InterfaceGenerationPreferences();
      transfo.put(KEY_PREFS, prefs);
    }
    return prefs;
  }
  
  public static InterfaceGenerationResult getResult(ITransfo transfo){
    InterfaceGenerationResult result = (InterfaceGenerationResult) transfo.get(KEY_RESULT);
    
    // happens when invoked from a different transfo (ES2IS for example)
    if (result == null) {
      result = new InterfaceGenerationResult(getPreferences(transfo));
      transfo.put(KEY_RESULT, result);
    }
    return result;
  }


  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if (context_p instanceof Component || context_p instanceof ComponentPort) {
      _context.add(context_p);
    }
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#createTransfo(org.polarsys.capella.core.tiger.ITransfoRuleBase)
   */
  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_INTERFACE_GENERATION_RULES);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);
    transfo.put(KEY_PREFS, prefs);
    transfo.put(KEY_DRY_RUN, dryRun);
    transfo.put(KEY_RESULT, result);
    
    return transfo;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#retainContextElement(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    if (contextElement instanceof Component || contextElement instanceof ComponentPort) {
      BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(contextElement);

      if (sourceBlock != null) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, sourceBlock);
        return true;
      }
    }
    return false;
  }

  /**
   * During a dry-run generation the model is not updated. After a dry-run
   * generation completes, clients can inspect and/or apply the generation result via
   * {@link #getResult(ITransfo)}
   * 
   * @param transfo
   * @return
   */
  public static boolean isDryRun(ITransfo transfo) {
    return transfo.get(KEY_DRY_RUN) == Boolean.TRUE;
  }

  public InterfaceGenerationResult getResult() {
    return result;
  }

  /**
   * Avoid logging during dry runs
   */
  protected ITransfoEngine createTransfoEngine() {
    if (dryRun){
      Logger quietLogger = Logger.getLogger(CAPELLA_INTERFACE_GENERATION_RULES);
      quietLogger.setAdditivity(false);
      quietLogger.setLevel(Level.OFF);
      return new ProjectionEngine(quietLogger);
    }
    return new ProjectionEngine();
  }

}
