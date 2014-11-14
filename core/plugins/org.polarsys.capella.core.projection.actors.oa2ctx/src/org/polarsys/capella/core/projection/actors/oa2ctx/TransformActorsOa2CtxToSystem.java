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

/**
 */
public class TransformActorsOa2CtxToSystem extends AbstractTransform {

  private EObject _startElementTransfo;

  private static final String ACTOR_TRANSFO_CONTEXT = "projection.actors.oa2ctx.bysystem"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#createTransfo(org.polarsys.capella.core.tiger.ITransfoRuleBase)
   */
  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws Exception {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, ACTOR_TRANSFO_CONTEXT);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);
    transfo.put(AbstractTransform.START_ELEMENT_TRANSFORMATION, _startElementTransfo);

    return transfo;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#retainContextElement(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean retainContextElement(EObject contextElement_p, ITransfo transfo_p) {
    OperationalAnalysis oa = (OperationalAnalysis) EcoreUtil2.getFirstContainer(contextElement_p, OaPackage.Literals.OPERATIONAL_ANALYSIS);
    if (oa != null) {
      for (BlockArchitecture sA : LayersCreation.getOrCreateSaLayers(oa)) {
        transfo_p.put(TransfoEngine.TRANSFO_SOURCE, contextElement_p);
        transfo_p.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, sA);
      }
      return true;
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if (context_p instanceof OperationalAnalysis) {
      EntityPkg entityPkg = ((OperationalAnalysis) context_p).getOwnedEntityPkg();
      if (entityPkg != null) {
        _context.add(entityPkg);
      }
    } else if ((context_p instanceof OperationalActor) || (context_p instanceof Entity) || (context_p instanceof EntityPkg)) {
      _context.add(context_p);
    }
  }

}
