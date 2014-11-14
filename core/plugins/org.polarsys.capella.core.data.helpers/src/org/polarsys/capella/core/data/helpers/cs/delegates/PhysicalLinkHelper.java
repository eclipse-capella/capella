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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeAllocatorHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PhysicalLinkHelper {
	private static PhysicalLinkHelper instance;

	private PhysicalLinkHelper() {
    // do nothing
	}

	public static PhysicalLinkHelper getInstance() {
		if (instance == null)
			instance = new PhysicalLinkHelper();
		return instance;
	}

	public Object doSwitch(PhysicalLink element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT)) {
      ret = getSourcePhysicalPort(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT)) {
      ret = getTargetPhysicalPort(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS)) {
      ret = getRealizedPhysicalLinks(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS)) {
      ret = getRealizingPhysicalLinks(element_p);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPathInvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element_p, feature_p);
    }
		if (null == ret) {
			ret = ComponentExchangeAllocatorHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected PhysicalPort getSourcePhysicalPort(PhysicalLink element_p) {
    Port port = PhysicalLinkExt.getSourcePort(element_p);
    if (port instanceof PhysicalPort) {
      return (PhysicalPort) port;
    }
    return null;
  }

  protected PhysicalPort getTargetPhysicalPort(PhysicalLink element_p) {
    Port port = PhysicalLinkExt.getTargetPort(element_p);
    if (port instanceof PhysicalPort) {
      return (PhysicalPort) port;
    }
    return null;
  }

  protected List<PhysicalLink> getRealizedPhysicalLinks(PhysicalLink element_p) {
    List<PhysicalLink> links = new ArrayList<PhysicalLink>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof PhysicalLinkRealization) {
        TraceableElement link = ((PhysicalLinkRealization) trace).getTargetElement();
        if (link instanceof PhysicalLink) {
          links.add((PhysicalLink) link);
        }
      }
    }
    return links;
  }

  protected List<PhysicalLink> getRealizingPhysicalLinks(PhysicalLink element_p) {
    List<PhysicalLink> links = new ArrayList<PhysicalLink>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof PhysicalLinkRealization) {
        TraceableElement link = ((PhysicalLinkRealization) trace).getSourceElement();
        if (link instanceof PhysicalLink) {
          links.add((PhysicalLink) link);
        }
      }
    }
    return links;
  }
}
