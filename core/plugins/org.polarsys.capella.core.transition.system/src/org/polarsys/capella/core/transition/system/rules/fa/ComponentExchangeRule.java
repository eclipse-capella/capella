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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ComponentExchangeRule extends AbstractCapellaElementRule {

  public ComponentExchangeRule() {
    super();
    registerUpdatedAttribute(FaPackage.Literals.COMPONENT_EXCHANGE__KIND);
    registerUpdatedAttribute(FaPackage.Literals.COMPONENT_EXCHANGE__ORIENTED);

    registerUpdatedReference(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here. We don't want to add container
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture targetA =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);

    Component target =
        (Component) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(element_p.eContainer(), context_p, CsPackage.Literals.COMPONENT);
    if ((target == null) || (target instanceof AbstractActor)) {
      return null;
    }
    return super.getBestContainer(element_p, result_p, context_p);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getContext(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    retrieveSource(source_p, result_p, context_p);
    retrieveTarget(source_p, result_p, context_p);

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      ComponentExchange element = (ComponentExchange) source_p;
      result_p.addAll(element.getOutgoingComponentExchangeFunctionalExchangeAllocations());
      result_p.addAll(element.getConvoyedInformations());

      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE,
          element.getOutgoingComponentExchangeFunctionalExchangeAllocations(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getConvoyedInformations(), context_p);
    }
  }

  /**
   * @param source_p
   * @param result_p
   * @param context_p
   */
  protected void retrieveSource(EObject source_p, List<EObject> result_p, IContext context_p) {
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      ComponentExchange element = (ComponentExchange) source_p;
      EObject source = element.getSource();
      result_p.add(source);
      if (!(source instanceof Entity)) {
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, source, context_p);
      }
    }
  }

  /**
   * @param source_p
   * @param result_p
   * @param context_p
   */
  protected void retrieveTarget(EObject source_p, List<EObject> result_p, IContext context_p) {
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      ComponentExchange element = (ComponentExchange) source_p;
      EObject target = element.getTarget();
      result_p.add(target);
      if (!(target instanceof Entity)) {
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, target, context_p);
      }
    }
  }

  protected void premicesExchangeRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET));
  }

  protected void attachExchangeRelated(EObject element_p, EObject result_p, IContext context_p) {
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE,
        context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET,
        context_p);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    attachExchangeRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    premicesExchangeRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS));
  }
}
