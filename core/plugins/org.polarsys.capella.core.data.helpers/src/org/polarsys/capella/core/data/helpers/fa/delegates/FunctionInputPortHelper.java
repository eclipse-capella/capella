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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityNodeHelper;

public class FunctionInputPortHelper {
	private static FunctionInputPortHelper instance;

	private FunctionInputPortHelper() {//
	}

	public static FunctionInputPortHelper getInstance() {
		if (instance == null)
			instance = new FunctionInputPortHelper();
		return instance;
	}

	public Object doSwitch(FunctionInputPort element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_FUNCTIONAL_EXCHANGES)) {
      ret = getIncomingFunctionalExchanges(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = FunctionPortHelper.getInstance().doSwitch(element, feature);
		}
    if (null == ret) {
      ret = ActivityNodeHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected List<FunctionalExchange> getIncomingFunctionalExchanges(FunctionInputPort element) {
    List <FunctionalExchange> ret = new ArrayList<>();
    for (ActivityEdge activityEdge : element.getIncoming()) {
      if (activityEdge instanceof FunctionalExchange){
        ret.add((FunctionalExchange) activityEdge);
      }
    }
    return ret;
  }
}
