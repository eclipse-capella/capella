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
package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvedElementHelper;

public class AbstractActorHelper {
  private static AbstractActorHelper instance;

  private AbstractActorHelper() {
    // do nothing
  }

  public static AbstractActorHelper getInstance() {
    if (instance == null)
      instance = new AbstractActorHelper();
    return instance;
  }

  public Object doSwitch(AbstractActor element_p, EStructuralFeature feature_p) {
    Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ComponentHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }
}
