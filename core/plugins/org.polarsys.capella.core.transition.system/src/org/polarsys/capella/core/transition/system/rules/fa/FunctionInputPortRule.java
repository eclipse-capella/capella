/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
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
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class FunctionInputPortRule extends AbstractCapellaElementRule {

  public FunctionInputPortRule() {
    registerUpdatedReference(FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS);
  }

  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    IStatus result = super.transformRequired(element, context);
    if (result.isOK()) {
      result = TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(element.eContainer(), context);
    }
    return result;
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTION_INPUT_PORT;
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    FunctionInputPort element = (FunctionInputPort) source;

    result.addAll(element.getIncomingPortAllocations());
    result.addAll(element.getOutgoingPortAllocations());

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.addAll(element.getIncoming());
      result.addAll(element.getOutgoing());
      result.addAll(element.getIncomingExchangeItems());
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getIncoming(),
          context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOutgoing(),
          context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          element.getIncomingExchangeItems(), context);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(
        createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS));
  }
}
