/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.ConcretizeFunctionAllocationCommand;

/**
 * Action for the ConcretizeFunctionAllocation accelerator
 * 
 * @author ebausson
 */
public class ConcretizeFunctionAllocationAction extends AbstractFixAction {

  @Override
  protected ICommand createCommand(IProgressMonitor progressMonitor) {
    return new ConcretizeFunctionAllocationCommand(getSelectedElements());
  }

}
