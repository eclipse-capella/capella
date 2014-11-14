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
package org.polarsys.capella.core.projection.actorsandinterfaces.ctx2la;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.LayersCreation;
import org.polarsys.capella.core.tiger.ITransfoEngine;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class TransformActorsCtx2La {

  private List<ModelElement> _context = null;
  
  public TransformActorsCtx2La() {
	_context = new ArrayList<ModelElement>();
  }

  
  public void setContext(ModelElement context_p) {
	  if (context_p instanceof Actor) {
		  _context.add(context_p);
	  } else if (context_p instanceof ActorPkg && CapellaLayerCheckingExt.isInContextLayer((CapellaElement)context_p) || context_p instanceof SystemAnalysis) {
		  for (EObject actor : EObjectExt.getAll(context_p, CtxPackage.Literals.ACTOR)) {
			  if (!_context.contains(actor)) {
				  _context.add((Actor) actor);
			  }
		  }
	  }
  }
  

  public void execute() {
    try {
    	ITransfoRuleBase ruleBase = new ActorTransfoRuleBase();
        ITransfoEngine engine = new ActorTransfoEngine();
        Transfo transfo = new ActorTransfo(ruleBase);
        for (ModelElement elt : _context) {
        	SystemAnalysis ctx = (SystemAnalysis) EObjectExt.getFirstContainer(elt, CtxPackage.Literals.SYSTEM_ANALYSIS);
        	if (ctx != null) {
	        	for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreateLaLayers(ctx)) {
	        		transfo.put(TransfoEngine.TRANSFO_SOURCE, elt);
	        		transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
	        		engine.execute(transfo);
	        	}
        	}
        }
    }
    catch (ClassNotFoundException ex) {
    	ex.printStackTrace();
    }
    catch (TransfoException ex) {
    	ex.printStackTrace();
    }
  }


  public List<ModelElement> getContext() {
  	return _context;
  }


}
