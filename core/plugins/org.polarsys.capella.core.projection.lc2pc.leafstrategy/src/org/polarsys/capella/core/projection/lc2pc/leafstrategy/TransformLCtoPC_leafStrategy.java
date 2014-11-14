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
package org.polarsys.capella.core.projection.lc2pc.leafstrategy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class TransformLCtoPC_leafStrategy extends AbstractTransform {

  public void setContext(EObject context_p) {
    if (context_p instanceof LogicalComponent) {
      _context.add(context_p);
    } else if (context_p instanceof LogicalArchitecture) {
      LogicalComponent lcRoot = ((LogicalArchitecture) context_p).getOwnedLogicalComponent();
      if (null != lcRoot) {
        _context.add(lcRoot);
      }
    } else if (context_p instanceof LogicalComponentPkg) {
      _context.add(context_p);
    } else if (context_p instanceof ComponentExchange) {
      _context.add(context_p);
    } else if (context_p instanceof Part) {
      _context.add(context_p);
    }
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    return new LogicalComponentTransfo(ruleBase_p);
  }

  @Override
  protected boolean retainContextElement(EObject contextElement_p, ITransfo transfo) {
    LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(contextElement_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
    for (PhysicalArchitecture physicalArchitecture : getOrCreatePaLayers(la)) {
      // Lc to Pc projection
      transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
      transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, physicalArchitecture);
    }
    return true;
  }

  /**
   * Return the 'PhysicalArchitecture' package Layer The 'PhysicalArchitecture' layer shall be create if doesnt exist
   */
  private List<PhysicalArchitecture> getOrCreatePaLayers(LogicalArchitecture la) {
    List<PhysicalArchitecture> lst = new ArrayList<PhysicalArchitecture>();

    for (AbstractTrace trace : la.getIncomingTraces()) {
      if ((trace instanceof LogicalArchitectureRealization) && (trace.getSourceElement() instanceof PhysicalArchitecture)) {
        lst.add((PhysicalArchitecture) trace.getSourceElement());
      }
    }

    if (lst.isEmpty()) {
      PhysicalArchitecture pa = PaFactory.eINSTANCE.createPhysicalArchitecture("Physical Architecture"); //$NON-NLS-1$
      SystemEngineering se = SystemEngineeringExt.getSystemEngineering(la);
      se.getOwnedArchitectures().add(pa);

      LogicalArchitectureRealization realisationLink = PaFactory.eINSTANCE.createLogicalArchitectureRealization();
      realisationLink.setTargetElement(la);
      realisationLink.setSourceElement(pa);
      pa.getOwnedLogicalArchitectureRealizations().add(realisationLink);

      lst.add(pa);
    }
    return lst;
  }

}
