/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import static org.polarsys.capella.core.data.helpers.DataHelpers.FunctionExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractFunctionRule extends org.polarsys.capella.core.transition.system.rules.fa.AbstractFunctionRule {

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    super.retrieveGoDeep(source_p, result_p, context_p);

    //but we return children
    AbstractFunction element = (AbstractFunction) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.removeAll(FunctionExt.getOwnedFunctionPorts(element));
      result_p.removeAll(FunctionExt.getOwnedFunctionPkgs(element));
      result_p.removeAll(element.getOwnedFunctionalChains());
      result_p.removeAll(element.getIncoming());
      result_p.removeAll(element.getOutgoing());
    }
  }

}
