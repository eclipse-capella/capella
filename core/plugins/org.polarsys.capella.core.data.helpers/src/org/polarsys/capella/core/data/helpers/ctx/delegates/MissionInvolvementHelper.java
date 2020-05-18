/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;

public class MissionInvolvementHelper {
  private static MissionInvolvementHelper instance;

  private MissionInvolvementHelper() {
    // do nothing
  }

  public static MissionInvolvementHelper getInstance() {
    if (instance == null)
      instance = new MissionInvolvementHelper();
    return instance;
  }

  public Object doSwitch(MissionInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CtxPackage.Literals.MISSION_INVOLVEMENT__SYSTEM_COMPONENT)) {
      ret = getSystemComponent(element);
    } else if (feature.equals(CtxPackage.Literals.MISSION_INVOLVEMENT__MISSION)) {
      ret = getMission(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected SystemComponent getSystemComponent(MissionInvolvement element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof SystemComponent)
      return (SystemComponent) elt;
    return null;
  }

  protected Mission getMission(MissionInvolvement element) {
    InvolverElement elt = element.getInvolver();
    if (elt instanceof Mission)
      return (Mission) elt;
    return null;
  }
}
