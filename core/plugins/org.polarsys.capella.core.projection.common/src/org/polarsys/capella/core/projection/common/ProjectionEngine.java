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
package org.polarsys.capella.core.projection.common;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * The interface transformation engine.
 * It computes the bootstrap and context of the transformation.
 *
 */
public class ProjectionEngine extends CapellaEngine {


  /**
   * Default constructor
   */
  public ProjectionEngine() {
    super();
  }

  public ProjectionEngine(Logger logger) {
    super(logger);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#initialize_()
   */
  @Override
  protected void initialize_() {
	ModelElement transfoSource = (ModelElement) _transfo.get(TRANSFO_SOURCE);
    computeContext(transfoSource);
  }
  
  private void computeContext(ModelElement transfoSource) {
    List<EObject> bootstrap = new ArrayList<EObject>();
    bootstrap.add(transfoSource);
    _transfo.put(BOOTSTRAP, bootstrap);
  }

  /**
   * @throws TransfoException 
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#doProcessDependingModels(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public void doProcessDependingModels(List<EObject> dependingModels_p) throws TransfoException {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#finalize_()
   */
  @Override
  protected void finalize_() {  
	super.finalize_();
  }
}
