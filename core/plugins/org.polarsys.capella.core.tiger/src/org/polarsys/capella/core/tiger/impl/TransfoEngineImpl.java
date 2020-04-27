/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.tiger.ITransfoEngine;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 *
 */
public class TransfoEngineImpl extends TransfoEngine {

  /**
   * The singleton instance
   */
  private static ITransfoEngine __instance = null;
  
  /**
   * Singleton
   * @return 
   */
  public static ITransfoEngine getInstance() {
    if(__instance==null) {
      __instance = new TransfoEngineImpl();
    }
    
    return __instance;
  }  
  
  @Override
  protected void initialize_() {    
    EObject transfoSource 
      = (EObject) _transfo.get(TRANSFO_SOURCE);
    
    List<EObject> bootstrap = new ArrayList<EObject>();
    bootstrap.add(transfoSource);
    
    _transfo.put(BOOTSTRAP, bootstrap);
    
    List<GenericTrace> links = new ArrayList<GenericTrace>();
    _transfo.put(NEW_LINKS, links);
    
    _transfo.setUid("Default"); //$NON-NLS-1$
  }
  
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#doProcessDependingModels(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public void doProcessDependingModels(List<EObject> object) {
    
  }
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#finalize(org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  protected void finalize_() {  
    if (_logger.isDebugEnabled()){
      _logger.debug("====================================");//$NON-NLS-1$
      _logger.debug("Finalizing..."); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }
    
    EObject transfoSource = (EObject) _transfo.get(TRANSFO_SOURCE);
    EObject transfoTarget = Query.retrieveFirstTransformedElement(transfoSource, _transfo);
    
    _transfo.put(TRANSFO_TARGET, transfoTarget);
  }
}
