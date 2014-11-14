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

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
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
 * This static refinement will transform OperationalCapability to the SystemAnalysis layer. It can be applied on an OperationalCapability or an
 * OperationalAnalysis.
 */
public abstract class TransformOperationalCapability extends AbstractTransform {

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if ((context_p instanceof AbstractCapabilityPkg) || (context_p instanceof AbstractCapability)) {
      _context.add(context_p);
    } else if (context_p instanceof BlockArchitecture) {
      _context.add(((BlockArchitecture) context_p).getOwnedAbstractCapabilityPkg());
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

    if (contextElement_p instanceof CapellaElement) {

      CapellaElement contextElement = (CapellaElement) contextElement_p;

      if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(contextElement)) {
        OperationalAnalysis ctx = (OperationalAnalysis) BlockArchitectureExt.getRootBlockArchitecture(contextElement);
        if (ctx != null) {
          for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreateSaLayers(ctx)) {
            transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
            transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
          }
          return true;
        }
      } else if (CapellaLayerCheckingExt.isAOrInContextLayer(contextElement)) {
        SystemAnalysis ctx = (SystemAnalysis) BlockArchitectureExt.getRootBlockArchitecture(contextElement);
        if (ctx != null) {
          for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreateLaLayers(ctx)) {
            transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
            transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
          }
          return true;
        }
      } else if (CapellaLayerCheckingExt.isAOrInLogicalLayer(contextElement)) {
        LogicalArchitecture la = (LogicalArchitecture) BlockArchitectureExt.getRootBlockArchitecture(contextElement);
        if (la != null) {
          for (BlockArchitecture physicalArchitecture : LayersCreation.getOrCreatePaLayers(la)) {
            transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
            transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, physicalArchitecture);
          }
          return true;
        }
      }

    }
    return false;
  }

}
