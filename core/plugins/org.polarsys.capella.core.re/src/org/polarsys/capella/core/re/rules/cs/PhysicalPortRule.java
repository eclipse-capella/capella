/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PhysicalPortRule extends org.polarsys.capella.core.transition.system.rules.cs.PhysicalPortRule {

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(element);
    EObject container = element.eContainer();
    if (container != null && container.equals(BlockArchitectureExt.getFirstComponent(architecture, false))) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);
      return BlockArchitectureExt.getFirstComponent(target, true);
    }
    return super.getDefaultContainer(element, result, context);
  }

}
