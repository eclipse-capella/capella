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

package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class FunctionalExchangeRule extends AbstractCapellaElementRule {

  public FunctionalExchangeRule() {
    super();
    registerUpdatedReference(FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  protected EObject getSource(EObject source, IContext context) {
    FunctionalExchange element = (FunctionalExchange) source;
    return element.getSource();
  }

  protected EObject getTarget(EObject source, IContext context) {
    FunctionalExchange element = (FunctionalExchange) source;
    return element.getTarget();
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      FunctionalExchange element = (FunctionalExchange) source;
      EObject sourceElement = element.getSource();
      EObject targetElement = element.getTarget();

      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;
  }

  @Override
  protected void retrieveRequired(EObject eObject1, List<EObject> result, IContext iContext1) {
    super.retrieveRequired(eObject1, result, iContext1);

    FunctionalExchange element = (FunctionalExchange) eObject1;
    EObject sourceElement = element.getSource();
    EObject targetElement = element.getTarget();

    result.add(sourceElement);
    result.add(targetElement);
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    // Nothing here. We don't want to add container
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getRootFunction(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      FunctionalExchange element = (FunctionalExchange) source;

      result.add(getSource(source, context));
      result.add(getTarget(source, context));

      if (!(element.getSource() instanceof AbstractFunction)) {
        ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getSource(), context);
      }
      if (!(element.getTarget() instanceof AbstractFunction)) {
        ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getTarget(), context);
      }

      result.addAll(element.getExchangedItems());
      result.addAll(element.getCategories());
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getExchangedItems(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getCategories(), context);
    }
  }

  protected void premicesExchangeRelated(EObject element, ArrayList<IPremise> needed) {
    needed.addAll(createDefaultPrecedencePremices(element, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE));
    needed.addAll(createDefaultPrecedencePremices(element, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET));
  }

  protected void attachExchangeRelated(EObject element, EObject result, IContext context) {
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET, context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    attachExchangeRelated(element, result, context);

    AttachmentHelper.getInstance(context).attachTracedElements(element, result, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS, context);
    
    AttachmentHelper.getInstance(context).invertedAttachTracedElements(element, result, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES, context);
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    premicesExchangeRelated(eObject1, needed);
    FunctionalExchange element = (FunctionalExchange) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS));
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES));
  }
}
