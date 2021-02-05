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
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AbstractStateRule extends org.polarsys.capella.core.transition.system.rules.common.AbstractStateRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    super.retrieveGoDeep(source_p, result_p, context_p);

    //but we return children
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      if (source_p instanceof IState) {
        IState state = (IState) source_p;
        EObject containerCurrent = EcoreUtil2.getFirstContainer(state, CsPackage.Literals.COMPONENT);

        for (IState s : state.getReferencedStates()) {
          EObject containerState = EcoreUtil2.getFirstContainer(s, CsPackage.Literals.COMPONENT);
          if (containerCurrent.equals(containerState)) {
            result_p.add(s);

          } else if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(s, context_p)) {
            result_p.add(s);
            ContextScopeHandlerHelper.getInstance(context_p).add(ITopDownConstants.CONTEXT_SCOPE__AVOID_DIFF_ELEMENTS, s, context_p);
          }
        }
      }

    }

  }
  
  /**
   * For topdown transition, all Do Activities should be added in to the scope
   * 
   * @param effect
   * @return
   */
  @Override
  protected boolean shouldAddDoActivityInScope(AbstractEvent effect) {
    return true;
  }

  /**
   * For topdown transition, all Entries should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  @Override
  protected boolean shouldAddEntryInScope(AbstractEvent trigger) {
    return true;
  }
  
  /**
   * For topdown transition, all Exits should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  @Override
  protected boolean shouldAddExitInScope(AbstractEvent trigger) {
    return true;
  }
  
  /**
   * For topdown transition, all available functions should be added to the scope
   * 
   * @param function
   * @return
   */
  @Override
  protected boolean shouldAddAvailableFunctionsInScope(AbstractFunction function) {
    return true;
  }
}
