/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.data.helpers.activity.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;

public class ActivityExchangeHelper {
  private static ActivityExchangeHelper instance;

  private ActivityExchangeHelper() {
    // do nothing
  }

  public static ActivityExchangeHelper getInstance() {
    if (instance == null)
      instance = new ActivityExchangeHelper();
    return instance;
  }

  public Object doSwitch(ActivityExchange element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ActivityPackage.Literals.ACTIVITY_EXCHANGE__REALIZING_ACTIVITY_FLOWS)) {
      ret = getRealizingActivityFlows(element);
    }

    return ret;
  }

  protected List<ActivityEdge> getRealizingActivityFlows(ActivityExchange element) {
    List<ActivityEdge> ret = new ArrayList<>();

    for (AbstractRelationship relationship : element.getRealizations()) {
      if (relationship instanceof ActivityEdge) {
        ret.add((ActivityEdge) relationship);
      }
    }

    return ret;
  }
}
