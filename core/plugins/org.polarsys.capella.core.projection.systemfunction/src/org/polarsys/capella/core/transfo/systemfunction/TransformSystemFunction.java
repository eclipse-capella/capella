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
package org.polarsys.capella.core.transfo.systemfunction;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 * This static refinement will transform SystemFunction to the LogicalFunction in LA layer. It can be applied on a SystemFunction or Function Pkg.
 */
public class TransformSystemFunction extends AbstractTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_SYSTEMFUNCTION_RULES = "capella.systemfunction.rules"; //$NON-NLS-1$

  /**
   * LA reminder.
   */
  private LogicalArchitecture _logicalArchitecture;

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if (isValidElement(context_p)) {
      _context.add(context_p);
    } else if (context_p instanceof SystemAnalysis) {
      _context.add(((SystemAnalysis) context_p).getOwnedFunctionPkg());
    }
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#createTransfo(org.polarsys.capella.core.tiger.ITransfoRuleBase)
   */
  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_SYSTEMFUNCTION_RULES);
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
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    if ((null == _logicalArchitecture) && (contextElement instanceof CapellaElement)) {
      _logicalArchitecture = getOrCreateLaLayer((CapellaElement) contextElement);
    }
    if (isValidElement(contextElement)) {
      transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
      transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, _logicalArchitecture);
      return true;
    }
    return false;
  }

  /**
   * Return the 'Function Pkg' (under the 'LA' layer) The 'LogicalArchitecture' layer and the 'Function Pkg' shall be create if doesn't exist
   */
  private LogicalArchitecture getOrCreateLaLayer(CapellaElement elt) {
    // Get or Create 'Logical Architecture'
    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(elt);
    LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(se);
    if (la == null) {
      la = LaFactory.eINSTANCE.createLogicalArchitecture("Logical Architecture"); //$NON-NLS-1$
      se.getOwnedArchitectures().add(la);
    }
    return la;
  }

  public boolean isValidElement(EObject context_p) {
    return (context_p != null)
           && CapellaLayerCheckingExt.isInContextLayer((CapellaElement) context_p)
           &&

           ((context_p instanceof FunctionPkg) || (context_p instanceof AbstractFunction) || (context_p instanceof FunctionalExchange)
            || (context_p instanceof FunctionPort) || (context_p instanceof FunctionalChain));

  }
}
