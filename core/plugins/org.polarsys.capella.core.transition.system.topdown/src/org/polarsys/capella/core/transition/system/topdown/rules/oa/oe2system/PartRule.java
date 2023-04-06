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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oe2system;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PartRule extends org.polarsys.capella.core.transition.system.rules.cs.PartRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PART;
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    // We don't care traceability, we return default container
    return null;
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);

    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p)
        .getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
    Component cps = BlockArchitectureExt.getOrCreateSystem(target);
    Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, cps);
    if (!parts.isEmpty()) {
      return parts.iterator().next();
    }
    return super.transformDirectElement(element_p, context_p);
  }

}
