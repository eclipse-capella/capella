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
package org.polarsys.capella.core.transfo.operationalcapability.rules.tomission;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.SystemAnalysisExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.rules.interaction.Rule_AbstractCapabilityPkg;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_OperationalCapabilityPkg extends Rule_AbstractCapabilityPkg {

  public Rule_OperationalCapabilityPkg() {
    super(OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG, CtxPackage.Literals.MISSION_PKG);
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof BlockArchitecture) {
      return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_MISSION_PKG;
    }
    return CtxPackage.Literals.MISSION_PKG__OWNED_MISSION_PKGS;
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {

    //Return the root function pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      SystemAnalysis architecture = (SystemAnalysis) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        MissionPkg result = SystemAnalysisExt.getMissionPkg(architecture);
        if (result != null) {
          LogHelper.getInstance().info(
              NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                  EObjectLabelProviderHelper.getText(result)), new Object[] { element_p, result }, ProjectionMessages.Activity_Transformation);
          return result;
        }
      }
    }

    return CtxFactory.eINSTANCE.createMissionPkg();
  }

}
