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

package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
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
    registerUpdatedReference(FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.ABSTRACT_FUNCTION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    return result;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(element),
        false) == element) {
      return;
    }
    super.retrieveContainer(element, result, context);
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container,
      IContext context) {
    EClass targetType = getTargetType(element, context);

    if (container instanceof OperationalActivityPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS;
      }
    } else if (container instanceof OperationalActivity) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS;
      }

    } else if (container instanceof SystemFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS;
      }

    } else if (container instanceof SystemFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS;
      }

    } else if (container instanceof LogicalFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS;
      }

    } else if (container instanceof LogicalFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS;
      }

    } else if (container instanceof PhysicalFunctionPkg) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS;
      }

    } else if (container instanceof PhysicalFunction) {
      if (FaPackage.Literals.ABSTRACT_FUNCTION.isSuperTypeOf(targetType)) {
        return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
      } else if (FaPackage.Literals.FUNCTION_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS;
      }

    }
    return element.eContainingFeature();
  }

  @Override
  protected EObject getBestContainer(EObject element, EObject result, IContext context) {
    EObject currentContainer = element.eContainer();
    EObject bestContainer = null;
    while ((currentContainer != null)) {
      bestContainer = TransformationHandlerHelper.getInstance(context).getBestTracedElement(currentContainer, context,
          FaPackage.Literals.ABSTRACT_FUNCTION, element, result);
      if (bestContainer == null) {
        bestContainer = TransformationHandlerHelper.getInstance(context).getBestTracedElement(currentContainer, context,
            FaPackage.Literals.FUNCTION_PKG, element, result);
      }
      if (bestContainer != null) {
        break;
      }
      currentContainer = currentContainer.eContainer();
    }
    return bestContainer;
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
    if (element.eContainer() instanceof FunctionPkg && element.eContainer().eContainer() instanceof BlockArchitecture) {
      AbstractNamedElement result = BlockArchitectureExt.getRootFunction(target, true);
      if (result != null) {
        if (!BlockArchitectureExt.isDefaultNameRootFunction((AbstractNamedElement) element)) {
          ((AbstractNamedElement) result).setName(((AbstractNamedElement) element).getName());
        }
        return result;
      }
    }
    return super.transformDirectElement(element, context);
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);
    if (element.eContainer() instanceof FunctionPkg && element.eContainer().eContainer() instanceof BlockArchitecture) {
      return BlockArchitectureExt.getFunctionPkg(target);
    }
    return BlockArchitectureExt.getRootFunction(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    AbstractFunction element = (AbstractFunction) source;

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.addAll(FunctionExt.getOwnedFunctionPorts(element));
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          FunctionExt.getOwnedFunctionPorts(element), context);

      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getIncoming(),
          context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOutgoing(),
          context);

      result.addAll(element.getIncoming());
      result.addAll(element.getOutgoing());
    }
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES, context);
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    AbstractFunction element = (AbstractFunction) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES));
  }

}
