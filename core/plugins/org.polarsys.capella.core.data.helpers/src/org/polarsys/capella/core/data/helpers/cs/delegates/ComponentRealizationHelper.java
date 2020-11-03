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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class ComponentRealizationHelper {
  private static ComponentRealizationHelper instance;

  private ComponentRealizationHelper() {
    // do nothing
  }

  public static ComponentRealizationHelper getInstance() {
    if (instance == null) {
      instance = new ComponentRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(ComponentRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.COMPONENT_REALIZATION__REALIZED_COMPONENT)) {
      ret = getRealizedComponent(element);
    } else if (feature.equals(CsPackage.Literals.COMPONENT_REALIZATION__REALIZING_COMPONENT)) {
      ret = getRealizingComponent(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Component getRealizedComponent(ComponentRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (null != ret && ret instanceof Component)
      return (Component) ret;
    return null;
  }

  protected Component getRealizingComponent(ComponentRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (null != ret && ret instanceof Component)
      return (Component) ret;
    return null;
  }
}
