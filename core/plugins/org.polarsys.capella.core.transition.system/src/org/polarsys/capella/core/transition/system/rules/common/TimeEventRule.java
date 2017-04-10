/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
