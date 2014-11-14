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
package org.polarsys.capella.core.transfo.operationalcapability;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 * This static refinement will transform OperationalCapability to the SystemAnalysis layer. It can be applied on an OperationalCapability or an
 * OperationalAnalysis.
 */
public abstract class TransformOperationalActivity extends AbstractTransform {

  /**
   * SystemAnalysis reminder.
   */
  private SystemAnalysis _CtxArchitecture;

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if ((context_p instanceof OperationalActivity) || (context_p instanceof OperationalActivityPkg)) {
      _context.add(context_p);
    } else if (context_p instanceof OperationalAnalysis) {
      _context.add(((OperationalAnalysis) context_p).getOwnedFunctionPkg());
    }
  }

  /**
   *
   */
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p, String context_p) {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, context_p);
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
    if ((_CtxArchitecture == null) && (contextElement_p instanceof CapellaElement)) {
      _CtxArchitecture = getSystemAnalysis((CapellaElement) contextElement_p);
    }
    if ((contextElement_p instanceof OperationalActivity) || (contextElement_p instanceof OperationalActivityPkg)) {
      transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
      transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, _CtxArchitecture);
      return true;
    }
    return false;
  }

  /**
   * Get or Create 'System Analysis'
   */
  private SystemAnalysis getSystemAnalysis(CapellaElement elt) {
    SystemAnalysis contextArch = null;

    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(elt);
    contextArch = SystemEngineeringExt.getOwnedSystemAnalysis(se);
    if (contextArch == null) {
      contextArch = CtxFactory.eINSTANCE.createSystemAnalysis("System Analysis"); //$NON-NLS-1$
      se.getOwnedArchitectures().add(contextArch);
    }

    return contextArch;
  }
}
