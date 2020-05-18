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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class OperationalCapabilityHelper {
  private static OperationalCapabilityHelper instance;

  private OperationalCapabilityHelper() {
    // do nothing
  }

  public static OperationalCapabilityHelper getInstance() {
    if (instance == null)
      instance = new OperationalCapabilityHelper();
    return instance;
  }

  public Object doSwitch(OperationalCapability element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES)) {
      ret = getRealizingCapabilities(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES)) {
      ret = getInvolvedEntities(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractCapabilityHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<Capability> getRealizingCapabilities(OperationalCapability element) {
    List <Capability> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof AbstractCapabilityRealization){
        AbstractCapability capability = ((AbstractCapabilityRealization) trace).getRealizingCapability();
        if (capability instanceof Capability) {
          ret.add((Capability) capability);
        }
      }
    }
    return ret;
  }

  protected List<Entity> getInvolvedEntities(OperationalCapability element) {
    List<Entity> ret = new ArrayList<>();
    for (Involvement trace : element.getInvolvedInvolvements()) {
      if (trace instanceof EntityOperationalCapabilityInvolvement) {
        Entity entity = ((EntityOperationalCapabilityInvolvement) trace).getEntity();
        if (null != entity) {
          ret.add(entity);
        }
      }
    }
    return ret;
  }
}
