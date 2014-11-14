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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.platform.sirius.ui.commands.ComponentExchangeThroughDelegationsCommand;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 * The action allowing to cut Capella elements.
 */
public class ComponentExchangeThroughDelegationsAction extends AbstractFixAction {

  @Override
  protected ICommand createCommand(IProgressMonitor progressMonitor_p) {
    return new ComponentExchangeThroughDelegationsCommand(getSelectedElements());
  }

}
