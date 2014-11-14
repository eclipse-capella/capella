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
package org.polarsys.capella.core.projection.common.rules.oa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.cs.Rule_ActorPkg;

/**
 */
public class Rule_EntityPkg extends Rule_ActorPkg {

  public Rule_EntityPkg() {
    super(OaPackage.Literals.ENTITY_PKG, CtxPackage.Literals.ACTOR_PKG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    EntityPkg sourceElement = (EntityPkg) source_p;

    if (isRelatedToSource(sourceElement, context_p)) {
      result_p.addAll(sourceElement.getOwnedEntityPkgs());
      for (Entity entity : sourceElement.getOwnedEntities()) {
        result_p.add(entity);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof BlockArchitecture) {
      return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG;
    }
    return CtxPackage.Literals.ACTOR_PKG__OWNED_ACTOR_PKGS;
  }

}
