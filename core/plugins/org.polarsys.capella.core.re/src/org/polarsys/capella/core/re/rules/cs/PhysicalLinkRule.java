/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PhysicalLinkRule extends AbstractCapellaElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }

  public PhysicalLinkRule() {

  }

  @Override
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);
    result_p.addAll(((PhysicalLink) element_p).getLinkEnds());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    if (root.equals(element_p.eContainer())) {
      return target;
    }
    PhysicalLink exchange = (PhysicalLink) element_p;
    if (PhysicalLinkExt.isLinkToAnActor(exchange)) {
      return BlockArchitectureExt.getComponentPkg(target, true);
    }
    return BlockArchitectureExt.getOrCreateSystem(target);
  }

}
