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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oa2capability;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class OperationalActivityRule extends AbstractCapellaElementRule {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  @Override
  protected EClass getSourceType() {
    return OaPackage.Literals.OPERATIONAL_ACTIVITY;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CtxPackage.Literals.CAPABILITY;
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES;
  }

  @Override
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    return EcoreUtil2.getFirstContainer(element_p, FaPackage.Literals.FUNCTION_PKG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    EObject parent = getSourceContainer(element_p, element_p, context_p);
    if (parent != null) {
      result_p.add(parent);
    }
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    //but we return children
    OperationalActivity element = (OperationalActivity) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(FunctionExt.getOwnedFunctionPkgs(element));
      result_p.addAll(element.getOwnedFunctions());
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionExt.getOwnedFunctionPkgs(element), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFunctions(), context_p);
    }

  }

}
