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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class AbstractPhysicalArtifactHelper {
  private static AbstractPhysicalArtifactHelper instance;

  private AbstractPhysicalArtifactHelper() {
    // do nothing
  }

  public static AbstractPhysicalArtifactHelper getInstance() {
    if (instance == null)
      instance = new AbstractPhysicalArtifactHelper();
    return instance;
  }

  public Object doSwitch(AbstractPhysicalArtifact element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS)) {
      ret = getAllocatorConfigurationItems(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ConfigurationItem> getAllocatorConfigurationItems(AbstractPhysicalArtifact element) {
    List<ConfigurationItem> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof PhysicalArtifactRealization) {
        Component cpnt = ((PhysicalArtifactRealization) trace).getRealizingConfigurationItem();
        if (cpnt instanceof ConfigurationItem) {
          ret.add((ConfigurationItem) cpnt);
        }
      }
    }
    return ret;
  }
}
