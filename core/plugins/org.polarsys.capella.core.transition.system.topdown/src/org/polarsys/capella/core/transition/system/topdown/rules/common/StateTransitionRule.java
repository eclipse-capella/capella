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
package org.polarsys.capella.core.transition.system.topdown.rules.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class StateTransitionRule extends org.polarsys.capella.core.transition.system.rules.common.StateTransitionRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  /**
   * For topdown transition, all effected elements should be added in to the scope
   * 
   * @param effect
   * @return
   */
  @Override
  protected boolean shouldAddEffectInScope(AbstractEvent effect) {
    return true;
  }

  /**
   * For topdown transition, all triggering elements should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  @Override
  protected boolean shouldAddTriggerInScope(AbstractEvent trigger) {
    return true;
  }
}
