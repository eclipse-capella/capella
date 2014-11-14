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
package org.polarsys.capella.core.transfo.logicalfunction;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
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
 * This static refinement will transform LogicalFunction to the PhysicalFunction in PA layer. It can be applied on a SystemFunction or Function Pkg.
 */
public class TransformLogicalFunction extends AbstractTransform {
  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_LOGICALFUNCTIONS_RULES = "capella.logicalfunctions.rules"; //$NON-NLS-1$

  /**
   * PA reminder.
   */
  private PhysicalArchitecture _physicalArchitecture;

  public void setContext(EObject context_p) {
    if (isValidElement(context_p)) {
      _context.add(context_p);
    } else if (context_p instanceof LogicalArchitecture) {
      _context.add(((LogicalArchitecture) context_p).getOwnedFunctionPkg());
    }
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_LOGICALFUNCTIONS_RULES);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    if ((null == _physicalArchitecture) && (contextElement instanceof CapellaElement)) {
      _physicalArchitecture = getOrCreatePaLayer((CapellaElement) contextElement);
    }
    if (isValidElement(contextElement)) {
      transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
      transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, _physicalArchitecture);
      return true;
    }
    return false;
  }

  /**
   * Return the 'PhysicalArchitecture' package Layer The 'PhysicalArchitecture' layer shall be create if doesnt exist
   */
  private PhysicalArchitecture getOrCreatePaLayer(CapellaElement elt) {
    // Get or Create 'Physical Architecture'
    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(elt);
    PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(se);
    if (pa == null) {
      pa = PaFactory.eINSTANCE.createPhysicalArchitecture("Physical Architecture"); //$NON-NLS-1$
      se.getOwnedArchitectures().add(pa);
    }
    return pa;
  }

  public boolean isValidElement(EObject context_p) {
    return (context_p != null)
           && CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) context_p)
           &&

           ((context_p instanceof FunctionPkg) || (context_p instanceof AbstractFunction) || (context_p instanceof FunctionalExchange)
            || (context_p instanceof FunctionPort) || (context_p instanceof FunctionalChain));

  }

}
