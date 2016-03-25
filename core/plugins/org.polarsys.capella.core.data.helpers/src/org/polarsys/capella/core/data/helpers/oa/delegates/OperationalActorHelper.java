/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.oa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class OperationalActorHelper {
	private static OperationalActorHelper instance;

	private OperationalActorHelper() {
	  // do nothing
	}

	public static OperationalActorHelper getInstance() {
		if (instance == null)
			instance = new OperationalActorHelper();
		return instance;
	}

	public Object doSwitch(OperationalActor element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(OaPackage.Literals.OPERATIONAL_ACTOR__REALIZING_SYSTEM_ACTORS)) {
			ret = getRealizingSystemActors(element);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = EntityHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<Actor> getRealizingSystemActors(OperationalActor element) {
    List<Actor> ret = new ArrayList<Actor>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof OperationalActorRealization) {
        Component cpnt = ((OperationalActorRealization)trace).getAllocatingComponent();
        if (cpnt instanceof Actor) {
          ret.add((Actor) cpnt);
        }
      }
    }
    return ret;
  }
}
