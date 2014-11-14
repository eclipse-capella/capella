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
package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityNodeHelper;

public class FunctionOutputPortHelper {
	private static FunctionOutputPortHelper instance;

	private FunctionOutputPortHelper() {//
	}

	public static FunctionOutputPortHelper getInstance() {
		if (instance == null)
			instance = new FunctionOutputPortHelper();
		return instance;
	}

	public Object doSwitch(FunctionOutputPort element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_FUNCTIONAL_EXCHANGES)) {
      ret = getOutgoingFunctionalExchanges(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = FunctionPortHelper.getInstance().doSwitch(element_p, feature_p);
		}
    if (null == ret) {
      ret = ActivityNodeHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  protected List<FunctionalExchange> getOutgoingFunctionalExchanges(FunctionOutputPort element_p) {
    List <FunctionalExchange> ret = new ArrayList<FunctionalExchange>();
    for (ActivityEdge activityEdge : element_p.getOutgoing()) {
      if (activityEdge instanceof FunctionalExchange){
        ret.add((FunctionalExchange) activityEdge);
      }
    }
    return ret;
  }
}
