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
package org.polarsys.capella.core.projection.data;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
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
public class TransformPropertyValues extends AbstractTransform {

  private static final String TRANSFO_CONTEXT = "org.polarsys.capella.core.projection.global"; //$NON-NLS-1$

  public void setContext(EObject context_p) {
    if ((context_p instanceof PropertyValuePkg) || (context_p instanceof PropertyValueGroup) || (context_p instanceof AbstractPropertyValue)
        || (context_p instanceof EnumerationPropertyType)) {
      _context.add(context_p);
    }
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, TRANSFO_CONTEXT);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  @Override
  protected boolean retainContextElement(EObject contextElement_p, ITransfo transfo_p) {
    if ((contextElement_p instanceof OperationalAnalysis) || EcoreUtil2.isContainedBy(contextElement_p, OaPackage.Literals.OPERATIONAL_ANALYSIS)) {
      SystemAnalysis contextArch = getOrCreateSystemAnalysis((CapellaElement) contextElement_p);
      transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, contextArch);
      transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
      return true;
    } else if ((contextElement_p instanceof SystemAnalysis) || EcoreUtil2.isContainedBy(contextElement_p, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
      SystemAnalysis ctx = (SystemAnalysis) EcoreUtil2.getFirstContainer(contextElement_p, CtxPackage.Literals.SYSTEM_ANALYSIS);
      if (ctx != null) {
        for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreateLaLayers(ctx)) {
          transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
          transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
        }
        return true;
      }
    } else if ((contextElement_p instanceof LogicalArchitecture) || EcoreUtil2.isContainedBy(contextElement_p, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
      LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(contextElement_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
      if (la != null) {
        for (BlockArchitecture physicalArchitecture : LayersCreation.getOrCreatePaLayers(la)) {
          transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
          transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, physicalArchitecture);
        }
        return true;
      }
    } else if ((contextElement_p instanceof PhysicalArchitecture) || EcoreUtil2.isContainedBy(contextElement_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
      PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(contextElement_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
      if (pa != null) {
        for (BlockArchitecture epbsArchitecture : LayersCreation.getOrCreateEpbsLayers(pa)) {
          transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
          transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, epbsArchitecture);
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Return the 'SystemAnalysis' layer. The 'Context' layer shall be create if doesnt exist
   */
  private SystemAnalysis getOrCreateSystemAnalysis(CapellaElement elt) {
    SystemAnalysis contextArch = null;

    // Get or Create 'System Analysis'
    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(elt);
    contextArch = SystemEngineeringExt.getOwnedSystemAnalysis(se);
    if (contextArch == null) {
      contextArch = CtxFactory.eINSTANCE.createSystemAnalysis(Messages.LayerName_0);
      se.getOwnedArchitectures().add(contextArch);
    }
    return contextArch;
  }
}
