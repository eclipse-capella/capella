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

  public Object doSwitch(EntityOperationalCapabilityInvolvement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(OaPackage.Literals.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__ENTITY)) {
      ret = getEntity(element_p);
    } else if (feature_p.equals(OaPackage.Literals.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__CAPABILITY)) {
      ret = getOperationalCapability(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected Entity getEntity(EntityOperationalCapabilityInvolvement element_p) {
    InvolvedElement elt = element_p.getInvolved();
    if (elt instanceof Entity)
      return (Entity) elt;
    return null;
  }

  protected OperationalCapability getOperationalCapability(EntityOperationalCapabilityInvolvement element_p) {
    InvolverElement elt = element_p.getInvolver();
    if (elt instanceof OperationalCapability)
      return (OperationalCapability) elt;
    return null;
  }
}
