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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;

public class FragmentEndHelper {
	private static FragmentEndHelper instance;

	private FragmentEndHelper() {
    // do nothing
	}

	public static FragmentEndHelper getInstance() {
		if (instance == null)
			instance = new FragmentEndHelper();
		return instance;
	}

	public Object doSwitch(FragmentEnd element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.FRAGMENT_END__ABSTRACT_FRAGMENT)) {
      ret = getAbstractFragment(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected AbstractFragment getAbstractFragment(FragmentEnd element) {
    if (element != null) {
      EObject owner = element.eContainer();
      if (owner instanceof InteractionOperand) {
        owner = owner.eContainer();
      }
      if (owner instanceof CombinedFragment) {
        owner = owner.eContainer();
      }
      if (owner instanceof Scenario) {
        for (TimeLapse timelapse : ((Scenario) owner).getOwnedTimeLapses()) {
          if ((timelapse instanceof AbstractFragment) &&
              (element.equals(timelapse.getStart())
            || element.equals(timelapse.getFinish())))
          {
            return (AbstractFragment) timelapse;
          }
        }
      }
    }
    return null;
  }
}
