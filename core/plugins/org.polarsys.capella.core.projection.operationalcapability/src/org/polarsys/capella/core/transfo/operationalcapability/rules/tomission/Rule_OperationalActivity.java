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
package org.polarsys.capella.core.transfo.operationalcapability.rules.tomission;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.model.helpers.SystemAnalysisExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_OperationalActivity extends org.polarsys.capella.core.transfo.operationalcapability.rules.common.Rule_OperationalActivity {

  public Rule_OperationalActivity() {
    super(CtxPackage.Literals.MISSION);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    SystemAnalysis architecture = (SystemAnalysis) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    return SystemAnalysisExt.getMissionPkg(architecture);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    result_p.add(getSourceContainer(element_p, null, context_p));
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

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CtxPackage.Literals.MISSION_PKG__OWNED_MISSIONS;
  }

}
