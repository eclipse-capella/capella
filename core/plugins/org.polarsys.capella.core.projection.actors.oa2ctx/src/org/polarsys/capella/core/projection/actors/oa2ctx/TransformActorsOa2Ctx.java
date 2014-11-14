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
package org.polarsys.capella.core.projection.actors.oa2ctx;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
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
public class TransformActorsOa2Ctx extends AbstractTransform {

  private static final String ACTOR_TRANSFO_CONTEXT = "projection.actors.oa2ctx.byactor"; //$NON-NLS-1$

  public void setContext(EObject context_p) {
    if (context_p instanceof OperationalAnalysis) {
      EntityPkg entityPkg = ((OperationalAnalysis) context_p).getOwnedEntityPkg();
      if (entityPkg != null) {
        _context.add(entityPkg);
      }

    } else if ((context_p instanceof OperationalActor) || (context_p instanceof Entity) || (context_p instanceof EntityPkg)) {
      _context.add(context_p);

    } else if (context_p instanceof Part) {
      AbstractType type = ((Part) context_p).getAbstractType();
      if ((type instanceof OperationalActor) || (context_p instanceof Entity)) {
        _context.add(context_p);
      }
    }
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, ACTOR_TRANSFO_CONTEXT);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    OperationalAnalysis oa = (OperationalAnalysis) EcoreUtil2.getFirstContainer(contextElement, OaPackage.Literals.OPERATIONAL_ANALYSIS);
    if (oa != null) {
      for (BlockArchitecture sA : LayersCreation.getOrCreateSaLayers(oa)) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, sA);
      }
      return true;
    }
    return false;
  }

}
