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
package org.polarsys.capella.core.transition.system.topdown.rules.pa;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.transition.system.topdown.rules.cs.BlockArchitectureRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PhysicalArchitectureRule extends BlockArchitectureRule{

  @Override
  protected EClass getSourceType() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }

  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return EpbsPackage.Literals.EPBS_ARCHITECTURE;
  }
}
