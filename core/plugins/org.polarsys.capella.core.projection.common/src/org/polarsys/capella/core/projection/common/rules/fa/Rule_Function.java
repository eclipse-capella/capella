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

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

/**
 */
public class Rule_Function extends Rule_AbstractFunction {

  private HashMap<AbstractFunction, Integer> map;

  public Rule_Function(EClass source, EClass target) {
    super(source, target);
    map = new HashMap<AbstractFunction, Integer>();
  }

  public Rule_Function() {
    super(FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.ABSTRACT_FUNCTION);
    map = new HashMap<AbstractFunction, Integer>();
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    //transform if there is at least one child not previously transformed
    AbstractFunction sourceElement = (AbstractFunction) element_p;

    if (isRootFunction(element_p) || ((element_p instanceof AbstractFunction) && FunctionExt.isLeaf((AbstractFunction) element_p))
        || (getNbUntransitionedSubFunctions(sourceElement, context_p.getTransfo()) > 0)) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.SubFunctionAlreadyTransitioned);
  }

  /**
   * Compute number of no-transitioned sub functions which should be transitioned
   */
  protected int getNbUntransitionedSubFunctions(AbstractFunction function, ITransfo transfo_p) {
    if (!map.containsKey(function)) {
      int nb = 0;
      for (AbstractFunction sub : getCache(FunctionExt::getFirstLevelAbstractFunctions, function)) {
        boolean isTransform = Query.isElementTransformed(sub, transfo_p);
        if (FunctionExt.isLeaf(sub) && !isTransform) {
          nb++;
        }
        nb += getNbUntransitionedSubFunctions(sub, transfo_p);
      }
      map.put(function, Integer.valueOf(nb));
      return nb;
    }

    return map.get(function).intValue();
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    AbstractFunction sourceElement = (AbstractFunction) source_p;

    super.retrieveGoDeep(source_p, result_p, context_p);

    if (isRelatedToSource(sourceElement, context_p)) {

      // Deep transformation is need from the current element
      result_p.addAll(sourceElement.getOwnedFunctions());
      result_p.addAll(FunctionExt.getOwnedFunctionPkgs(sourceElement));
      result_p.addAll(sourceElement.getOwnedFunctionalExchanges());
      result_p.addAll(sourceElement.getInputs());
      result_p.addAll(sourceElement.getOutputs());
      // also adds the owned functional chains
      result_p.addAll(sourceElement.getOwnedFunctionalChains());

    }

  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container, IContext context_p) {

    if (container instanceof OperationalActivityPkg) {
      return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES;
    } else if (container instanceof SystemFunctionPkg) {
      return CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS;
    } else if (container instanceof LogicalFunctionPkg) {
      return LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS;
    } else if (container instanceof PhysicalFunctionPkg) {
      return PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS;
    } else if (container instanceof AbstractFunction) {
      return FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;
    }

    return super.getTargetContainementFeature(element_p, result_p, container, context_p);
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    if (isRootFunction(element_p)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        AbstractFunction result = BlockArchitectureExt.getRootFunction(architecture);
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

}
