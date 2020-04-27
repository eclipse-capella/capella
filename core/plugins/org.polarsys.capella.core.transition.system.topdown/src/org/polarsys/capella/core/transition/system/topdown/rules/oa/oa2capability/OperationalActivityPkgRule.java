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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class OperationalActivityPkgRule extends AbstractCapellaElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  @Override
  protected EClass getSourceType() {
    return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CtxPackage.Literals.CAPABILITY_PKG;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getAbstractCapabilityPkg(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;
    } else if (container_p instanceof CapabilityPkg) {
      return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS;
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {

    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
      BlockArchitecture target =
          (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE);

      if ((target != null) && (target instanceof SystemAnalysis)) {
        return BlockArchitectureExt.getAbstractCapabilityPkg(target);
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    //but we return children
    FunctionPkg element = (FunctionPkg) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(FunctionPkgExt.getOwnedFunctionPkgs(element));
      result_p.addAll(FunctionPkgExt.getOwnedFunctions(element));
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionPkgExt.getOwnedFunctionPkgs(element), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionPkgExt.getOwnedFunctions(element), context_p);
    }

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

  /**
   * {@inheritDoc}
   */
  @Override
  protected void premicesContainement(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesContainement(element_p, needed_p);
  }

  @Override
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    return EcoreUtil2.getFirstContainer(element_p, FaPackage.Literals.FUNCTION_PKG);
  }

}
