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
package org.polarsys.capella.core.projection.interfaces.ctx2la;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.LayersCreation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class TransformInterfacesCtx2La extends AbstractTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_INTERFACES_CTX2LA_RULES = "org.polarsys.capella.core.projection.interfaces.ctx2la.rules"; //$NON-NLS-1$

  public void setContext(EObject context_p) {
    addContext(context_p);
  }

  public void addContext(EObject context_p) {
    _context.add(context_p);
  }

  public List<EObject> getContext() {
    return _context;
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_INTERFACES_CTX2LA_RULES);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#retainContextElement(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean retainContextElement(EObject contextElement_p, ITransfo transfo_p) {
    SystemAnalysis ctx = (SystemAnalysis) EcoreUtil2.getFirstContainer(contextElement_p, CtxPackage.Literals.SYSTEM_ANALYSIS);
    if (ctx != null) {
      for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreateLaLayers(ctx)) {
        transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
        transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
      }
      return true;
    }
    return false;
  }

}
