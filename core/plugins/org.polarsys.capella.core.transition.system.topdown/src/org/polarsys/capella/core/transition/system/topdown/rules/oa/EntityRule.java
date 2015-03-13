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
package org.polarsys.capella.core.transition.system.topdown.rules.oa;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EntityRule extends org.polarsys.capella.core.transition.system.topdown.rules.cs.ComponentRule {

  @Override
  protected EClass getSourceType() {
    return OaPackage.Literals.ENTITY;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    // For an entity already transitioned in the target architecture, we need to retrieve it's type since it can be transitioned to System or Actor
    EObject object =
        TopDownTransformationHelper.getInstance(context_p).getBestTracedElement(element_p, context_p, new EClassSelectionContext(CsPackage.Literals.COMPONENT));
    if (object != null) {
      return object.eClass();
    }
    Collection<EObject> objects = TopDownTransformationHelper.getInstance(context_p).retrieveTargetTracedElements(element_p, context_p);
    if (!objects.isEmpty()) {
      return objects.iterator().next().eClass();
    }
    return CtxPackage.Literals.ACTOR;
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EClass targetType = getTargetType(element_p, context_p);
    if (CtxPackage.Literals.SYSTEM.isSuperTypeOf(targetType)) {
      // Retrieve the existing architecture if any
      EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);

      BlockArchitecture target =
          (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
      if (target instanceof SystemAnalysis) {
        SystemAnalysis analysis = (SystemAnalysis) target;
        if (analysis.getOwnedSystem() != null) {
          return analysis.getOwnedSystem();
        }
      }
    }
    EObject res = super.transformDirectElement(element_p, context_p);
    return res;
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject currentContainer = element_p.eContainer();
    if ((currentContainer instanceof Entity) && (element_p instanceof Entity)) {
      return null;
    }
    EObject bestContainer = null;
    while ((currentContainer != null)) {
      bestContainer =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(currentContainer, context_p, CtxPackage.Literals.ACTOR_PKG, element_p,
              result_p);

      if (bestContainer != null) {
        break;
      }
      currentContainer = currentContainer.eContainer();
    }
    return bestContainer;
  }
}
