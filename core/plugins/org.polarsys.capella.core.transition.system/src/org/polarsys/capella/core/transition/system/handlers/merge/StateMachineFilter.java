/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class StateMachineFilter extends EObjectCategoryFilter {

  public StateMachineFilter(IContext context) {
    super(context, CapellacommonPackage.Literals.STATE, PreferenceConstants.P_MS_TEXT);
  }

  @Override
  public boolean keepElement(Object element) {
    return (element instanceof Region) || (element instanceof Mode) || (element instanceof StateMachine)
        || (element instanceof State) || (element instanceof StateTransition);
  }

}
