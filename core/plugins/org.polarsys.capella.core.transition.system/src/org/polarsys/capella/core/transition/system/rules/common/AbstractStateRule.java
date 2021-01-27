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

package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class AbstractStateRule extends AbstractCapellaElementRule {

  public AbstractStateRule() {
    registerUpdatedReference(ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    registerUpdatedReference(CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE__DO_ACTIVITY);

  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.ABSTRACT_STATE;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    if (source instanceof State) {
      result.addAll(((State) source).getOwnedRegions());
      // but we return children
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
        if (source instanceof State) {
          State element = (State) source;
          // // Add Do Activity to scope
          List<AbstractEvent> activities = element.getDoActivity();
          for (AbstractEvent activity : activities) {
            if (shouldAddDoActivityInScope(activity)) {
              ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, activity, context);
              result.add(activity);
            }
          }
          
          // Add effect elements to scope
          for (AbstractEvent entry : element.getEntry()) {
            if (shouldAddEntryInScope(entry)) {
              ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, entry, context);
              result.add(entry);
            }
          }
          // Add trigger elements to scope
          for (AbstractEvent exit : element.getExit()) {
            if (shouldAddExitInScope(exit)) {
              ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, exit, context);
              result.add(exit);
            }
          }
          
        }
      }
    }
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS));

    if (element instanceof State) {
      needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.STATE__DO_ACTIVITY));
    }
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES, context);
    AttachmentHelper.getInstance(context)
        .attachTracedElements(element, result, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS, context);

    if (element instanceof State) {
      AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE__DO_ACTIVITY, context);
      AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE__ENTRY, context);
      AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.STATE__EXIT, context);
    }
  }
  
  /**
   * By default, only EI Do Activities should be added in to the scope
   * 
   * @param effect
   * @return
   */
  protected boolean shouldAddDoActivityInScope(AbstractEvent activity) {
    return activity instanceof ExchangeItem;
  }

  /**
   * By default, only EI entries should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  protected boolean shouldAddEntryInScope(AbstractEvent entry) {
    return entry instanceof ExchangeItem;
  }
  
  /**
   * By default, only EI exits should be added in to the scope
   * 
   * @param trigger
   * @return
   */
  protected boolean shouldAddExitInScope(AbstractEvent exit) {
    return exit instanceof ExchangeItem;
  }
}
