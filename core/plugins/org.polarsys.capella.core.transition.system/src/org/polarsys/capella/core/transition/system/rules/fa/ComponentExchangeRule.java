/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
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
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    if (container instanceof AbstractFunctionalBlock) {
      return FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES;
    }
    if (container instanceof ComponentPkg) {
      return CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES;
    }
    return element.eContainingFeature();
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
    return BlockArchitectureExt.getContext(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    retrieveSource(source, result, context);
    retrieveTarget(source, result, context);

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ComponentExchange element = (ComponentExchange) source;
      result.addAll(element.getOutgoingComponentExchangeFunctionalExchangeAllocations());
      result.addAll(element.getConvoyedInformations());

      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          element.getOutgoingComponentExchangeFunctionalExchangeAllocations(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getConvoyedInformations(), context);
    }
  }

  /**
   * @param eObject1
   * @param result
   * @param context
   */
  protected void retrieveSource(EObject eObject1, List<EObject> result, IContext context) {
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, eObject1, context)) {
      ComponentExchange element = (ComponentExchange) eObject1;
      EObject source = element.getSource();
      result.add(source);
      if (!(source instanceof Entity)) {
        ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, source, context);
      }
    }
  }

  /**
   * @param source
   * @param result
   * @param context
   */
  protected void retrieveTarget(EObject source, List<EObject> result, IContext context) {
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ComponentExchange element = (ComponentExchange) source;
      EObject target = element.getTarget();
      result.add(target);
      if (!(target instanceof Entity)) {
        ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, target, context);
      }
    }
  }

  protected void premicesExchangeRelated(EObject element, ArrayList<IPremise> needed) {
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE));
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET));
  }

  protected void attachExchangeRelated(EObject element, EObject result, IContext context) {
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE,
        context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET,
        context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    attachExchangeRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    premicesExchangeRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS));
  }
}
