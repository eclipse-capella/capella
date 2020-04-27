/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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


package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.platform.sirius.ui.commands.PortRealizationPropagationCommand;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 * The action allowing to cut Capella elements.
 */
public class PortRealizationPropagationAction extends AbstractFixAction {

  @Override
  protected ICommand createCommand(IProgressMonitor progressMonitor) {
    return new PortRealizationPropagationCommand(getSelectedElements());
  }

}
