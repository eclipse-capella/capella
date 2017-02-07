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
package org.polarsys.capella.core.transfo.operationalcapability.rules.tocapability;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_FunctionPkg;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_OperationalActivityPkg extends Rule_FunctionPkg {

  public Rule_OperationalActivityPkg() {
    super(OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG, CtxPackage.Literals.CAPABILITY_PKG);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    result_p.add(getSourceContainer(element_p, null, context_p));
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {

    //Return the root function pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        AbstractCapabilityPkg result = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
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
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {
      if (parent instanceof FunctionPkg) {
        return parent;
      }
      parent = parent.eContainer();
    }
    return null;
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject bestContainer = null;
    EObject container = getSourceContainer(element_p, result_p, context_p);
    if (container != null) {
      bestContainer =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(container, context_p,
              TransformationHandlerHelper.getInstance(context_p).getTargetType(container, context_p));
    }
    return bestContainer;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    if (architecture != null) {
      return BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;
    }
    return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS;
  }

}
