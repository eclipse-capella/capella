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

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Swimlane;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityPartitionHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class SwimlaneHelper {
  private static SwimlaneHelper instance;

  private SwimlaneHelper() {
    // do nothing
  }

  public static SwimlaneHelper getInstance() {
    if (instance == null)
      instance = new SwimlaneHelper();
    return instance;
  }

  public Object doSwitch(Swimlane element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY)) {
      ret = getRepresentedEntity(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = ActivityPartitionHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected Entity getRepresentedEntity(Swimlane element_p) {
    AbstractType represented = element_p.getRepresentedElement();
    if (represented instanceof Entity) {
      return (Entity) represented;
    }
    return null;
  }
}
