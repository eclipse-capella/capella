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

	public Object doSwitch(ExecutionEnd element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.EXECUTION_END__EXECUTION)) {
      ret = getExecution(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AbstractEndHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected Execution getExecution(ExecutionEnd element_p) {
    if (element_p != null) {
      EObject owner = element_p.eContainer();
      if (owner instanceof Scenario) {
        for (TimeLapse timelapse : ((Scenario) owner).getOwnedTimeLapses()) {
          if ((timelapse instanceof Execution) &&
              (element_p.equals(timelapse.getStart())
            || element_p.equals(timelapse.getFinish())))
          {
            return (Execution) timelapse;
          }
        }
      }
    }
    return null;
  }
}
