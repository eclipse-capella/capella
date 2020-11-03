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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
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
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class FunctionPkgRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTION_PKG;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
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

    } else if (container instanceof AbstractFunctionalArchitecture) {
      return FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG;
      
    } 
    return element.eContainingFeature();
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (element.eContainer() instanceof BlockArchitecture) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
      
      Structure result = BlockArchitectureExt.getFunctionPkg(target, true);
      if (result != null) {
        if (!BlockArchitectureExt.isDefaultNameFunctionPkg((AbstractNamedElement) element)) {
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
    if (element.eContainer() instanceof BlockArchitecture) {
      return target;
    }
    return BlockArchitectureExt.getFunctionPkg(target);
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

}
