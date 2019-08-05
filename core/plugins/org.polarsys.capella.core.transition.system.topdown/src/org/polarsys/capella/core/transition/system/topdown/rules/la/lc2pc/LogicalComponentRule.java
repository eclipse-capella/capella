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
package org.polarsys.capella.core.transition.system.topdown.rules.la.lc2pc;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class LogicalComponentRule extends org.polarsys.capella.core.transition.system.topdown.rules.cs.ComponentRule {

  @Override
  protected EClass getSourceType() {
    return LaPackage.Literals.LOGICAL_COMPONENT;
  }
  
  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {

    String value =
        OptionsHandlerHelper.getInstance(context_p).getStringValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
            ITopDownConstants.OPTIONS_TRANSITION__LCPC, ITopDownConstants.OPTIONS_TRANSITION__LCPC_DEFAULT);

    if (ITopDownConstants.OPTIONS_TRANSITION__LCPC_LEAF.equals(value)) {
      if (ComponentExt.isComposite((LogicalComponent) element_p) && !(ComponentExt.isComponentRoot(element_p))) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG;
      }
    }

    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }
}
