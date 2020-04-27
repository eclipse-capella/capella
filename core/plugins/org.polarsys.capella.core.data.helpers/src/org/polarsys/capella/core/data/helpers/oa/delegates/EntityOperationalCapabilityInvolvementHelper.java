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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;

public class EntityOperationalCapabilityInvolvementHelper {
  private static EntityOperationalCapabilityInvolvementHelper instance;

  private EntityOperationalCapabilityInvolvementHelper() {
    // do nothing
  }

  public static EntityOperationalCapabilityInvolvementHelper getInstance() {
    if (instance == null)
      instance = new EntityOperationalCapabilityInvolvementHelper();
    return instance;
  }

  public Object doSwitch(EntityOperationalCapabilityInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__ENTITY)) {
      ret = getEntity(element);
    } else if (feature.equals(OaPackage.Literals.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getCapability(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Entity getEntity(EntityOperationalCapabilityInvolvement element) {
    InvolvedElement elt = element.getInvolved();
    if (elt instanceof Entity)
      return (Entity) elt;
    return null;
  }

  protected OperationalCapability getCapability(EntityOperationalCapabilityInvolvement element) {
    InvolverElement elt = element.getInvolver();
    if (elt instanceof OperationalCapability)
      return (OperationalCapability) elt;
    return null;
  }
}
