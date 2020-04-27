/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.rules.common;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

public class TimeEventRule extends StateEventRule {

  public TimeEventRule() {
    super();
    registerUpdatedAttribute(CapellacommonPackage.Literals.TIME_EVENT__KIND);
  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.TIME_EVENT;
  }
}
