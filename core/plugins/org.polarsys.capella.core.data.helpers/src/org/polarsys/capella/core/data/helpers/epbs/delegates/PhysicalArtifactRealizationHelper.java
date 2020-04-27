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

package org.polarsys.capella.core.data.helpers.epbs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class PhysicalArtifactRealizationHelper {
  private static PhysicalArtifactRealizationHelper instance;

  private PhysicalArtifactRealizationHelper() {
    // do nothing
  }

  public static PhysicalArtifactRealizationHelper getInstance() {
    if (instance == null) {
    	instance = new PhysicalArtifactRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(PhysicalArtifactRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT)) {
      ret = getRealizedPhysicalArtifact(element);
    } else if (feature.equals(EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM)) {
      ret = getRealizingConfigurationItem(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }
  
  protected AbstractPhysicalArtifact getRealizedPhysicalArtifact(PhysicalArtifactRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (null != ret && ret instanceof AbstractPhysicalArtifact)
      return (AbstractPhysicalArtifact) ret;
    return null;
  }

  protected ConfigurationItem getRealizingConfigurationItem(PhysicalArtifactRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (null != ret && ret instanceof ConfigurationItem)
      return (ConfigurationItem) ret;
    return null;
  }
}
