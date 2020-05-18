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

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeAllocatorHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;

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

	public Object doSwitch(PhysicalLink element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT)) {
      ret = getSourcePhysicalPort(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT)) {
      ret = getTargetPhysicalPort(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS)) {
      ret = getRealizedPhysicalLinks(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS)) {
      ret = getRealizingPhysicalLinks(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_LINK__CATEGORIES)) {
      ret = getCategories(element);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPathInvolvedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element, feature);
    }
		if (null == ret) {
			ret = ComponentExchangeAllocatorHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<EObject> getCategories(PhysicalLink element) {
    return EObjectExt.getReferencers(element, CsPackage.Literals.PHYSICAL_LINK_CATEGORY__LINKS);
  }
  
  protected PhysicalPort getSourcePhysicalPort(PhysicalLink element) {
    Port port = PhysicalLinkExt.getSourcePort(element);
    if (port instanceof PhysicalPort) {
      return (PhysicalPort) port;
    }
    return null;
  }

  protected PhysicalPort getTargetPhysicalPort(PhysicalLink element) {
    Port port = PhysicalLinkExt.getTargetPort(element);
    if (port instanceof PhysicalPort) {
      return (PhysicalPort) port;
    }
    return null;
  }

  protected List<PhysicalLink> getRealizedPhysicalLinks(PhysicalLink element) {
    List<PhysicalLink> links = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof PhysicalLinkRealization) {
        TraceableElement link = ((PhysicalLinkRealization) trace).getTargetElement();
        if (link instanceof PhysicalLink) {
          links.add((PhysicalLink) link);
        }
      }
    }
    return links;
  }

  protected List<PhysicalLink> getRealizingPhysicalLinks(PhysicalLink element) {
    List<PhysicalLink> links = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
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
