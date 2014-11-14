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
package org.polarsys.capella.core.projection.actors.la2pa.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public class Rule_ActorPkg extends org.polarsys.capella.core.projection.common.rules.cs.Rule_ActorPkg {

  public Rule_ActorPkg() {
    super(LaPackage.Literals.LOGICAL_ACTOR_PKG, PaPackage.Literals.PHYSICAL_ACTOR_PKG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    LogicalActorPkg sourceElement = (LogicalActorPkg) source_p;

    if (isRelatedToSource(sourceElement, context_p)) {
      result_p.addAll(sourceElement.getOwnedLogicalActorPkgs());
      result_p.addAll(sourceElement.getOwnedLogicalActors());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof BlockArchitecture) {
      return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG;
    }
    return PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS;
  }

}
