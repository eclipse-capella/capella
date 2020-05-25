/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;

/**
 * Delete physical path involvement
 */
public class SwitchIsActorIsHumanFlag_ResolverAll extends SwitchIsActorIsHumanFlag_Resolver {
  public SwitchIsActorIsHumanFlag_ResolverAll(String label, boolean isActor, boolean isHuman) {
    super(label, isActor, isHuman);
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    return (markers.size() > 1);
  }

  @Override
  protected boolean canResolve(IMarker marker) {
    String ruleId = "org.polarsys.capella.core.data.cs.validation.DWF_DC_36";
    String markerRuleId = MarkerViewHelper.getRuleID(marker, true);
    return ruleId.equals(markerRuleId);
  }
}
