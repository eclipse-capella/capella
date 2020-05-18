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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class FunctionalChainRule extends AbstractCapellaElementRule {

  public FunctionalChainRule() {
    super();
    registerUpdatedReference(FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION);
    registerUpdatedReference(FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    // Nothing here. We don't want to add container
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);
    return BlockArchitectureExt.getRootFunction(target);
  }

  /**
   * @param element_p
   * @param result
   * @param context
   */
  protected void retrieveDeepValidChain(EObject source, List<EObject> result, IContext context) {
    FunctionalChain element = (FunctionalChain) source;
    result.addAll(element.getOwnedFunctionalChainInvolvements());
    result.addAll(element.getOwnedSequenceNodes());
    result.addAll(element.getOwnedSequenceLinks());
    result.add(element.getPreCondition());
    result.add(element.getPostCondition());
    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFunctionalChainInvolvements(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedSequenceNodes(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedSequenceLinks(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getPreCondition(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getPostCondition(), context);
    }
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION));
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION, context);
  }

}
