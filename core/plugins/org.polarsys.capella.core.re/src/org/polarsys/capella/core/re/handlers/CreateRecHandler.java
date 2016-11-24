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
package org.polarsys.capella.core.re.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.re.commands.CreateRecCommand;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 */
public class CreateRecHandler extends org.polarsys.capella.common.re.handlers.CreateRecHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<?> selection_p, IProgressMonitor progressMonitor_p) {
    return new CreateRecCommand(selection_p, progressMonitor_p) {

    };
  }

}
