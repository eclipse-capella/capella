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

  protected EObject getSource(EObject source_p, IContext context_p) {
    FunctionalExchange element = (FunctionalExchange) source_p;
    return element.getSource();
  }

  protected EObject getTarget(EObject source_p, IContext context_p) {
    FunctionalExchange element = (FunctionalExchange) source_p;
    return element.getTarget();
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);

    if (result.isOK()) {
      FunctionalExchange element = (FunctionalExchange) source_p;
      EObject sourceElement = element.getSource();
      EObject targetElement = element.getTarget();

      result = TransformationHandlerHelper.getInstance(context_p).checkTransformRequired(element, context_p, sourceElement, targetElement);
    }
    return result;
  }

  @Override
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);

    FunctionalExchange element = (FunctionalExchange) element_p;
    EObject sourceElement = element.getSource();
    EObject targetElement = element.getTarget();

    result_p.add(sourceElement);
    result_p.add(targetElement);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here. We don't want to add container
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getRootFunction(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      FunctionalExchange element = (FunctionalExchange) source_p;

      result_p.add(getSource(source_p, context_p));
      result_p.add(getTarget(source_p, context_p));

      if (!(element.getSource() instanceof AbstractFunction)) {
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, element.getSource(), context_p);
      }
      if (!(element.getTarget() instanceof AbstractFunction)) {
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, element.getTarget(), context_p);
      }

      result_p.addAll(element.getExchangedItems());
      result_p.addAll(element.getCategories());
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getExchangedItems(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getCategories(), context_p);
    }
  }

  protected void premicesExchangeRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET));
  }

  protected void attachExchangeRelated(EObject element_p, EObject result_p, IContext context_p) {
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET, context_p);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    attachExchangeRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    premicesExchangeRelated(element_p, needed_p);
    FunctionalExchange element = (FunctionalExchange) element_p;
    needed_p.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS));
    needed_p.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES));
  }
}
