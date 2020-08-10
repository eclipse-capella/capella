/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.rules.cs;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PartRule extends org.polarsys.capella.core.transition.system.rules.cs.PartRule {

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p)
        .getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE, element_p, result_p);

    Part part = (Part) result_p;
    if (part != null) {
      Component cps = (Component) part.getAbstractType();
      if (cps != null) {
        if (cps instanceof Entity || BlockArchitectureExt.isRootComponent(cps) || ComponentExt.isExternalActor(cps)) {
          return BlockArchitectureExt.getComponentPkg(target, true);
        }
      }
    }
    return BlockArchitectureExt.getOrCreateSystem(target);
  }

}
