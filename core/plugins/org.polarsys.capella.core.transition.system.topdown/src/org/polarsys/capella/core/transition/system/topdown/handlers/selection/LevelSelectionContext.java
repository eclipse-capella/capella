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
package org.polarsys.capella.core.transition.system.topdown.handlers.selection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LevelSelectionContext implements ISelectionContext {

  protected Level index = null;

  public LevelSelectionContext(Level index_p) {
    this.index = index_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean match(EObject source_p, EObject target_p, IContext context_p) {
    EClass clazzLevel = LevelHandlerHelper.getInstance(context_p).getLevel(context_p, index);
    BlockArchitecture target = BlockArchitectureExt.getRootBlockArchitecture(target_p);
    if (clazzLevel == null) {
      return false;
    }
    return clazzLevel.isInstance(target);
  }

  @Override
  public String toString() {
    return "LevelSelectionContext:" + index.toString();
  }
}
