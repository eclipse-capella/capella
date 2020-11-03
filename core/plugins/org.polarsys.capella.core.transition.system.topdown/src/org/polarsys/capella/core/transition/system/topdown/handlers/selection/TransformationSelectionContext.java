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
package org.polarsys.capella.core.transition.system.topdown.handlers.selection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationSelectionContext extends org.polarsys.capella.core.transition.common.handlers.selection.TransformationSelectionContext {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean match(EObject source_p, EObject target_p, IContext context_p) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    BlockArchitecture architecture2 = BlockArchitectureExt.getRootBlockArchitecture(target_p);

    EClass targetType = TransformationHandlerHelper.getInstance(context_p).getTargetType(source_p, context_p);
    if (!((targetType == null) || targetType.isInstance(target_p))) {
      return false;
    }
    if (architecture == null) {
      return true;
    }

    EClass clazz = TransformationHandlerHelper.getInstance(context_p).getTargetType(architecture, context_p);
    if (!(clazz == null) && clazz.isInstance(architecture2)) {
      return true;
    }

    return false;

  }
}
