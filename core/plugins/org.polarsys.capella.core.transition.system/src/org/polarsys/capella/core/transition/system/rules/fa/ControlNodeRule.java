/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.fa;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;

public class ControlNodeRule extends AbstractCapellaElementRule {
  
  public ControlNodeRule() {
    super();
    registerUpdatedAttribute(FaPackage.Literals.CONTROL_NODE__KIND);
  }
  
  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.CONTROL_NODE;
  }
}
