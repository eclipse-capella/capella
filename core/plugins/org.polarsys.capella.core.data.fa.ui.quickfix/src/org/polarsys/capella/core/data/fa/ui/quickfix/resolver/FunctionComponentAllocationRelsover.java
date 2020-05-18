/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.sirius.ui.actions.AllocationManagementWizardAction;

/**
 * Allocate Function to a Component
 */
public class FunctionComponentAllocationRelsover extends ActionResolver {
	 
	
  @Override
  protected AbstractTigAction createAction() {
    return new AllocationManagementWizardAction();
  }
  
  @Override
  protected String[] getResolvableRuleIds(){
    return noRuleIds;
  }

}
