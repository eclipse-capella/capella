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

  public Object doSwitch(Swimlane element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY)) {
      ret = getRepresentedEntity(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = ActivityPartitionHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Entity getRepresentedEntity(Swimlane element) {
    AbstractType represented = element.getRepresentedElement();
    if (represented instanceof Entity) {
      return (Entity) represented;
    }
    return null;
  }
}
