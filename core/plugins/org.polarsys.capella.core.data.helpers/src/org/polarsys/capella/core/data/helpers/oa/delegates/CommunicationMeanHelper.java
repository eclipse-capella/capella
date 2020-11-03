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

import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedRelationshipHelper;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;

public class CommunicationMeanHelper {
  private static CommunicationMeanHelper instance;

  private CommunicationMeanHelper() {
    // do nothing
  }

  public static CommunicationMeanHelper getInstance() {
    if (instance == null)
      instance = new CommunicationMeanHelper();
    return instance;
  }

  public Object doSwitch(CommunicationMean element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.COMMUNICATION_MEAN__SOURCE_ENTITY)) {
      ret = getSourceEntity(element);
    } else if (feature.equals(OaPackage.Literals.COMMUNICATION_MEAN__TARGET_ENTITY)) {
      ret = getTargetEntity(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ComponentExchangeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = NamedRelationshipHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Entity getSourceEntity(CommunicationMean element) {
    InformationsExchanger source = element.getSource();
    if (source instanceof Entity) {
      return (Entity) source;
    }
    return null;
  }

  protected Entity getTargetEntity(CommunicationMean element) {
    InformationsExchanger target = element.getTarget();
    if (target instanceof Entity) {
      return (Entity) target;
    }
    return null;
  }
}
