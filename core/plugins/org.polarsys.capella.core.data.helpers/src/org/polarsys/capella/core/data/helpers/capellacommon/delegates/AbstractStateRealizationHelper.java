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
package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

public class AbstractStateRealizationHelper {
	private static AbstractStateRealizationHelper instance;

	private AbstractStateRealizationHelper() {
	  //
	}

	public static AbstractStateRealizationHelper getInstance(){
		if(instance == null)
			instance = new AbstractStateRealizationHelper();
		return instance;
	}

	public Object doSwitch(AbstractStateRealization element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION__REALIZED_ABSTRACT_STATE)) {
			ret = getRealizedAbstractState(element_p);
		}
		else if (feature_p.equals(CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION__REALIZING_ABSTRACT_STATE)) {
      ret = getRealizingAbstractState(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

	protected AbstractState getRealizedAbstractState(AbstractStateRealization element_p) {
	  if (element_p.getTargetElement() instanceof AbstractState) {
	    return (AbstractState) element_p.getTargetElement();
	  }
	  return null;
	}

  protected AbstractState getRealizingAbstractState(AbstractStateRealization element_p) {
    if (element_p.getSourceElement() instanceof AbstractState) {
      return (AbstractState) element_p.getSourceElement();
    }
    return null;
  }
}
