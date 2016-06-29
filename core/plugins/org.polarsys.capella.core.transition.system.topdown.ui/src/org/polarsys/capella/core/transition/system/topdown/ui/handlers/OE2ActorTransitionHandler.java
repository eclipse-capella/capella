/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.ui.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.transition.system.topdown.ui.commands.TransitionUICommandHelper;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 *
 */
public class OE2ActorTransitionHandler extends IntramodelTransitionHandler {

  @Override
  protected ICommand createCommand(Collection<Object> selection, IProgressMonitor progressMonitor) {
    return TransitionUICommandHelper.getInstance().getOE2ActorTransitionCommand(selection, progressMonitor);
  }
}
