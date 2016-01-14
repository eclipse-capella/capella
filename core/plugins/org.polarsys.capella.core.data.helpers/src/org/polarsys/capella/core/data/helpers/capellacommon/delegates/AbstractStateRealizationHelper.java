/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class AbstractStateRealizationHelper {
  private static AbstractStateRealizationHelper instance;

  private AbstractStateRealizationHelper() {
  }

  public static AbstractStateRealizationHelper getInstance() {
    if (instance == null) {
      instance = new AbstractStateRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractStateRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION__REALIZED_ABSTRACT_STATE)) {
      ret = getRealizedAbstractState(element);
    } else if (feature.equals(CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION__REALIZING_ABSTRACT_STATE)) {
      ret = getRealizingAbstractState(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected AbstractState getRealizedAbstractState(AbstractStateRealization element) {
    if (element.getTargetElement() instanceof AbstractState) {
      return (AbstractState) element.getTargetElement();
    }
    return null;
  }

  protected AbstractState getRealizingAbstractState(AbstractStateRealization element) {
    if (element.getSourceElement() instanceof AbstractState) {
      return (AbstractState) element.getSourceElement();
    }
    return null;
  }
}
