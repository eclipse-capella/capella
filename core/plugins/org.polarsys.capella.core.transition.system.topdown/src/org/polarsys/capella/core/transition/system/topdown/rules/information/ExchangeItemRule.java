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
package org.polarsys.capella.core.transition.system.topdown.rules.information;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.options.IOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ExchangeItemRule extends org.polarsys.capella.core.transition.system.rules.information.ExchangeItemRule {

  @Override
  protected void retrieveGoDeepElements(EObject source_p, List<EObject> result_p, IContext context_p) {
    ExchangeItem item = (ExchangeItem) source_p;

    String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
    IOptionsHandler options = OptionsHandlerHelper.getInstance(context_p);

    //We put in targetArchi only if ExchangeItem transition or preference is enabled
    boolean transition =
        ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(transitionKind)
            || options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM,
                ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM_DEFAULT.booleanValue());

    if (transition) {
      result_p.addAll(item.getOwnedElements());
      result_p.addAll(item.getOwnedExchangeItemInstances());

      IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
      if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, item.getOwnedElements(), context_p);
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, item.getOwnedExchangeItemInstances(), context_p);
      }
    }
  }

}
