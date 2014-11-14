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
package org.polarsys.capella.core.projection.interfaces.ctx2pa;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
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
public class TransformInterfacesCtx2Pa extends AbstractTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_INTERFACES_CTX2PA_RULES = "org.polarsys.capella.core.projection.interfaces.ctx2pa.rules"; //$NON-NLS-1$

  public void setContext(EObject context_p) {
    if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) context_p)) {
      if (context_p instanceof SystemAnalysis) {
        InterfacePkg ctxItfPkg = ((SystemAnalysis) context_p).getOwnedInterfacePkg();
        if (ctxItfPkg != null) {
          _context.add(ctxItfPkg);
        }
      } else if (context_p instanceof Component) {
        InterfacePkg itfPkg = ((Component) context_p).getOwnedInterfacePkg();
        if (itfPkg != null) {
          _context.add(itfPkg);
        }
      } else if ((context_p instanceof Interface) || (context_p instanceof InterfacePkg) || (context_p instanceof ExchangeItem)) {
        _context.add(context_p);
      }
    }
  }

  @Override
  protected boolean retainContextElement(EObject contextElement_p, ITransfo transfo) {
    SystemAnalysis ctx = (SystemAnalysis) EcoreUtil2.getFirstContainer(contextElement_p, CtxPackage.Literals.SYSTEM_ANALYSIS);
    if ((ctx != null) || (contextElement_p instanceof Interface) || (contextElement_p instanceof InterfacePkg)) {
      SystemEngineering se = SystemEngineeringExt.getSystemEngineering(ctx);
      LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(se);
      for (BlockArchitecture physicalArchitecture : LayersCreation.getOrCreatePaLayers(la)) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, physicalArchitecture);
      }
      return true;
    }
    return false;
  }

  public List<EObject> getContext() {
    return _context;
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_INTERFACES_CTX2PA_RULES);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

}
