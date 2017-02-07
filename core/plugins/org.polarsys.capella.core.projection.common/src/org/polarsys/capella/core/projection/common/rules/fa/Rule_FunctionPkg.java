/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
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
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_FunctionPkg extends Rule_CapellaElement {

  public Rule_FunctionPkg() {
    super(FaPackage.Literals.FUNCTION_PKG, FaPackage.Literals.FUNCTION_PKG);
  }

  public Rule_FunctionPkg(EClass source, EClass target) {
    super(source, target);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    //Return the root function pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        FunctionPkg result = BlockArchitectureExt.getFunctionPkg(architecture);
        if (result != null) {
          LogHelper.getInstance().info(
              NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                  EObjectLabelProviderHelper.getText(result)), new Object[] { element_p, result }, ProjectionMessages.Activity_Transformation);
          return result;
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return (EObject) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {
      EObject element =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p,
              TransformationHandlerHelper.getInstance(context_p).getTargetType(parent, context_p));
      if (element != null) {
        return element;
      }
      parent = parent.eContainer();
    }
    return null;
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container, IContext context_p) {

    if (container instanceof OperationalActivityPkg) {
      return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS;
    } else if (container instanceof SystemFunctionPkg) {
      return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS;
    } else if (container instanceof LogicalFunctionPkg) {
      return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS;
    } else if (container instanceof PhysicalFunctionPkg) {
      return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS;

    } else if (container instanceof OperationalActivity) {
      return OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS;
    } else if (container instanceof SystemFunction) {
      return CtxPackage.Literals.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS;
    } else if (container instanceof LogicalFunction) {
      return LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS;
    } else if (container instanceof PhysicalFunction) {
      return PaPackage.Literals.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS;

    } else if (container instanceof AbstractFunctionalArchitecture) {
      return FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG;
    }

    return super.getTargetContainementFeature(element_p, result_p, container, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    FunctionPkg sourceElement = (FunctionPkg) source_p;

    super.retrieveGoDeep(source_p, result_p, context_p);

    if (isRelatedToSource(sourceElement, context_p)) {
      result_p.addAll(FunctionPkgExt.getOwnedFunctions(sourceElement));
      result_p.addAll(FunctionPkgExt.getOwnedFunctionPkgs(sourceElement));
    }
  }

}
