/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;

public class ExecutionEndHelper {
	private static ExecutionEndHelper instance;

	private ExecutionEndHelper() {
    // do nothing
	}

	public static ExecutionEndHelper getInstance() {
		if (instance == null)
			instance = new ExecutionEndHelper();
		return instance;
	}

	public Object doSwitch(ExecutionEnd element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.EXECUTION_END__EXECUTION)) {
      ret = getExecution(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AbstractEndHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected Execution getExecution(ExecutionEnd element) {
    if (element != null) {
      EObject owner = element.eContainer();
      if (owner instanceof Scenario) {
        for (TimeLapse timelapse : ((Scenario) owner).getOwnedTimeLapses()) {
          if ((timelapse instanceof Execution) &&
              (element.equals(timelapse.getStart())
            || element.equals(timelapse.getFinish())))
          {
            return (Execution) timelapse;
          }
        }
      }
    }
    return null;
  }
}
