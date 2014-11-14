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
package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class FunctionInputPortRule extends AbstractCapellaElementRule {

  public FunctionInputPortRule() {
    registerUpdatedReference(FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element_p.eContainer(), context_p);
    }
    return result;
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTION_INPUT_PORT;
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS,
        context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    FunctionInputPort element = (FunctionInputPort) source_p;

    result_p.addAll(element.getIncomingPortAllocations());
    result_p.addAll(element.getOutgoingPortAllocations());

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(element.getIncoming());
      result_p.addAll(element.getOutgoing());
      result_p.addAll(element.getIncomingExchangeItems());
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getIncoming(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOutgoing(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getIncomingExchangeItems(), context_p);
    }
  }

}
