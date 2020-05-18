/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.cs.delegates.ArchitectureAllocationHelper;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;

public class LogicalArchitectureRealizationHelper {
  private static LogicalArchitectureRealizationHelper instance;

  private LogicalArchitectureRealizationHelper() {
    // do nothing
  }

  public static LogicalArchitectureRealizationHelper getInstance() {
    if (instance == null) {
    	instance = new LogicalArchitectureRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(LogicalArchitectureRealization element, EStructuralFeature feature) {
	  // no helper found... searching in super classes...
      return ArchitectureAllocationHelper.getInstance().doSwitch(element, feature);
  }
}
