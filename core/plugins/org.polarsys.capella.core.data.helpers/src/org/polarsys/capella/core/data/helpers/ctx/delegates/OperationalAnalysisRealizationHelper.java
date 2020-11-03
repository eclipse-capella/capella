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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.helpers.cs.delegates.ArchitectureAllocationHelper;

public class OperationalAnalysisRealizationHelper {
  private static OperationalAnalysisRealizationHelper instance;

  private OperationalAnalysisRealizationHelper() {
    // do nothing
  }

  public static OperationalAnalysisRealizationHelper getInstance() {
    if (instance == null) {
    	instance = new OperationalAnalysisRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(OperationalAnalysisRealization element, EStructuralFeature feature) {

    // no helper found... searching in super classes...
    return ArchitectureAllocationHelper.getInstance().doSwitch(element, feature);
  }
}
