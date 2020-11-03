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
package org.polarsys.capella.core.transition.system.topdown.rules.la;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
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
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }
}
