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
package org.polarsys.capella.core.projection.common.rules.interaction;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_AbstractCapabilityPkg extends Rule_CapellaElement {

  public Rule_AbstractCapabilityPkg() {
    super(CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
  }

  public Rule_AbstractCapabilityPkg(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (element_p instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;
    } else if (container_p instanceof CapabilityPkg) {
      return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS;
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    //Return the root pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        Structure result = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
        if (result != null) {
          LogHelper.getInstance().info(
              NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                  EObjectLabelProviderHelper.getText(result)), new Object[] { element_p, result }, ProjectionMessages.Activity_Transformation);
          return result;
        }
      }

    }

    if (element_p instanceof OperationalCapabilityPkg) {
      return CtxFactory.eINSTANCE.createCapabilityPkg();
    }
    return LaFactory.eINSTANCE.createCapabilityRealizationPkg();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    AbstractCapabilityPkg sourceElement = (AbstractCapabilityPkg) source_p;

    if (isRelatedToSource(sourceElement, context_p)) {

      // Deep transformation is need from the current element
      for (EObject content : sourceElement.eContents()) {
        if (content instanceof AbstractCapability || content instanceof AbstractCapabilityPkg) {
          result_p.add(content);
        }
      }
    }
  }
}
