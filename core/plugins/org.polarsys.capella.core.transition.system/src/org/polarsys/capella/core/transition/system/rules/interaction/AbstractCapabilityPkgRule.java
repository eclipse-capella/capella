/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.interaction;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class AbstractCapabilityPkgRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (!(element.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element, result, context);
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (element.eContainer() instanceof BlockArchitecture) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
      Structure result = BlockArchitectureExt.getAbstractCapabilityPkg(target, true);
      if (result != null) {
        if (!BlockArchitectureExt.isDefaultNameAbstractCapabilityPkg((AbstractNamedElement) element)) {
          ((AbstractNamedElement) result).setName(((AbstractNamedElement) element).getName());
        }
        return result;
      }
    }
    return super.transformDirectElement(element, context);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    AbstractCapabilityPkg pkg = (AbstractCapabilityPkg) source;
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      result.addAll(AbstractCapabilityPkgExt.getOwnedCapabilities(pkg));
      result.addAll(AbstractCapabilityPkgExt.getOwnedCapabilityPkgs(pkg));
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, AbstractCapabilityPkgExt.getOwnedCapabilities(pkg), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, AbstractCapabilityPkgExt.getOwnedCapabilityPkgs(pkg),
          context);
    }
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    if (element.eContainer() instanceof BlockArchitecture) {
      return target;
    }
    return BlockArchitectureExt.getAbstractCapabilityPkg(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    if (container instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;
    } else if (container instanceof CapabilityPkg) {
      return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS;
  }

}
