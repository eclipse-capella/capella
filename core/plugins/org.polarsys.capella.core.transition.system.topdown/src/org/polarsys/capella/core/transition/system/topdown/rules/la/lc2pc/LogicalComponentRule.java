/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.la.lc2pc;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
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
  public EClass getTargetType(EObject element, IContext context) {

    String value =
        OptionsHandlerHelper.getInstance(context).getStringValue(context, ITopDownConstants.OPTIONS_SCOPE,
            ITopDownConstants.OPTIONS_TRANSITION__LCPC, ITopDownConstants.OPTIONS_TRANSITION__LCPC_DEFAULT);

    if (ITopDownConstants.OPTIONS_TRANSITION__LCPC_LEAF.equals(value)) {
      LogicalComponent component = (LogicalComponent) element;
      if (ComponentExt.isComposite(component) && !(BlockArchitectureExt.isRootComponent(component))) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG;
      }
    }

    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }
}
