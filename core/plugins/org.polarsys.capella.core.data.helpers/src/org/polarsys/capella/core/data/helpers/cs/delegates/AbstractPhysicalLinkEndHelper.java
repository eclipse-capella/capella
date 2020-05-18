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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;

public class AbstractPhysicalLinkEndHelper {
	private static AbstractPhysicalLinkEndHelper instance;

	private AbstractPhysicalLinkEndHelper() {
    // do nothing
	}

	public static AbstractPhysicalLinkEndHelper getInstance() {
		if (instance == null)
			instance = new AbstractPhysicalLinkEndHelper();
		return instance;
	}

	public Object doSwitch(AbstractPhysicalLinkEnd element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS)) {
      ret = getInvolvedLinks(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
		}
		return ret;
	}

  protected List<PhysicalLink> getInvolvedLinks(AbstractPhysicalLinkEnd element) {
    List<PhysicalLink> ret = new ArrayList<>();

    for (EObject ref: EObjectExt.getReferencers(element, CsPackage.Literals.PHYSICAL_LINK__LINK_ENDS)) {
      ret.add((PhysicalLink) ref);
    }

    if (element instanceof PhysicalPort) {
      for (EObject ref1 : EObjectExt.getReferencers(element, CsPackage.Literals.PHYSICAL_LINK_END__PORT)) {
        for (EObject ref2 : EObjectExt.getReferencers(ref1, CsPackage.Literals.PHYSICAL_LINK__LINK_ENDS)) {
          ret.add((PhysicalLink) ref2);
        }
      }
    }

    return ret;
  }
}
