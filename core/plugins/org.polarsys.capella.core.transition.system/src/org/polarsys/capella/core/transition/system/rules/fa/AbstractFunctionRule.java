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
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class AbstractFunctionRule extends AbstractCapellaElementRule {

  public AbstractFunctionRule() {
    super();
    registerUpdatedAttribute(FaPackage.Literals.ABSTRACT_FUNCTION__KIND);
    registerUpdatedAttribute(FaPackage.Literals.ABSTRACT_FUNCTION__CONDITION);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.ABSTRACT_FUNCTION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);

    return result;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(element_p)) == element_p) {
      return;
    }
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    EClass targetType = getTargetType(element_p, context_p);

    if (container_p instanceof OperationalActivityPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS;
      }
    } else if (container_p instanceof OperationalActivity) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS;
      }

    } else if (container_p instanceof SystemFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS;
      }

    } else if (container_p instanceof SystemFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS;
      }

    } else if (container_p instanceof LogicalFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS;
      }

    } else if (container_p instanceof LogicalFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS;
      }

    } else if (container_p instanceof PhysicalFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS;
      }

    } else if (container_p instanceof PhysicalFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS;
      }

    }
    return element_p.eContainingFeature();
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject currentContainer = element_p.eContainer();
    EObject bestContainer = null;
    while ((currentContainer != null)) {
      bestContainer =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(currentContainer, context_p, FaPackage.Literals.ABSTRACT_FUNCTION, element_p,
              result_p);
      if (bestContainer == null) {
        bestContainer =
            TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(currentContainer, context_p, FaPackage.Literals.FUNCTION_PKG, element_p,
                result_p);
      }

      if (bestContainer != null) {
        break;
      }
      currentContainer = currentContainer.eContainer();
    }
    return bestContainer;
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
    AbstractFunction element = (AbstractFunction) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(FunctionExt.getOwnedFunctionPorts(element));
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionExt.getOwnedFunctionPorts(element), context_p);

      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getIncoming(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOutgoing(), context_p);

      result_p.addAll(element.getIncoming());
      result_p.addAll(element.getOutgoing());
    }
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    AbstractFunction element = (AbstractFunction) element_p;
    needed_p.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES));
  }

}
