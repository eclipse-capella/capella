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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class AbstractCapabilityPkgRule extends org.polarsys.capella.core.transition.system.rules.interaction.AbstractCapabilityPkgRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {

    if (OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG.isInstance(element_p)) {
      return CtxPackage.Literals.CAPABILITY_PKG;

    } else if (CtxPackage.Literals.CAPABILITY_PKG.isInstance(element_p)) {
      return LaPackage.Literals.CAPABILITY_REALIZATION_PKG;

    } else if (LaPackage.Literals.CAPABILITY_REALIZATION_PKG.isInstance(element_p)) {
      return LaPackage.Literals.CAPABILITY_REALIZATION_PKG;
    }

    return super.getTargetType(element_p, context_p);
  }

}
