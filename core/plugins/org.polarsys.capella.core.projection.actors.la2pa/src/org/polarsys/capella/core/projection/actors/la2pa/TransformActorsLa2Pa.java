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
package org.polarsys.capella.core.projection.actors.la2pa;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.LayersCreation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class TransformActorsLa2Pa extends AbstractTransform {

  private static final String PROJECTION_ACTORS_LA2PA = "projection.actors.la2pa"; //$NON-NLS-1$

  public void setContext(EObject context_p) {
    if (context_p instanceof LogicalArchitecture) {
      LogicalActorPkg logActorPkg = ((LogicalArchitecture) context_p).getOwnedLogicalActorPkg();
      if (logActorPkg != null) {
        _context.add(logActorPkg);
      }

    } else if ((context_p instanceof LogicalActor) || (context_p instanceof LogicalActorPkg) || (context_p instanceof ComponentExchange)) {
      _context.add(context_p);

    } else if (context_p instanceof Part) {
      AbstractType type = ((Part) context_p).getAbstractType();
      if (type instanceof LogicalActor) {
        _context.add(context_p);
      }
    }
  }

  public List<EObject> getContext() {
    return _context;
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, PROJECTION_ACTORS_LA2PA);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(contextElement, LaPackage.Literals.LOGICAL_ARCHITECTURE);
    if (la != null) {
      for (BlockArchitecture logicalArchitecture : LayersCreation.getOrCreatePaLayers(la)) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, logicalArchitecture);
      }
      return true;
    }
    return false;
  }

}
