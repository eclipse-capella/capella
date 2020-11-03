/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.rules.oa;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EntityRule extends org.polarsys.capella.core.transition.system.topdown.rules.cs.ComponentRule {

  public EntityRule() {

    unregisterUpdatedAttribute(CsPackage.Literals.COMPONENT__ACTOR);
    unregisterUpdatedAttribute(CsPackage.Literals.COMPONENT__HUMAN);
  }
  
  @Override
  protected EClass getSourceType() {
    return OaPackage.Literals.ENTITY;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CtxPackage.Literals.SYSTEM_COMPONENT;
  }

  @Override
  protected boolean transformAsRootComponent(EObject object, IContext context) {
    return false;
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
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(currentContainer, context_p, CtxPackage.Literals.SYSTEM_COMPONENT_PKG, element_p,
              result_p);
      if (bestContainer != null) {
        break;
      }
      currentContainer = currentContainer.eContainer();
    }
    return bestContainer;
  }
}
